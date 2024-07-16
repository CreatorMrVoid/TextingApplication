<template>
  <q-page class="page">
    <div>
      <q-toolbar class="bg-secondary text-white shadow-2">
        <q-toolbar-title :title="topicName" />
      </q-toolbar>
    </div>
    <div class="messages-container">
      <MessagesCard
        v-for="message in messages"
        :key="message.id"
        :id="message.id"
        :text="message.text"
        :writer="message.writer"
      />
    </div>
    <SendMessage @send-message="sendMessage" />
  </q-page>
</template>

<script setup lang="ts">
import { ref, onMounted, onUnmounted } from "vue";
import { useRoute } from "vue-router";
import { api } from "boot/axios";
import { MessagesCardProps } from "src/types/types";
import SendMessage from "src/layouts/SendMessage.vue";
import MessagesCard from "src/components/MessagesCard.vue";
import SockJS from "sockjs-client/dist/sockjs.js";
import { CompatClient, Stomp } from "@stomp/stompjs";
import { LocalStorage } from "quasar";

const route = useRoute();
const messages = ref([] as MessagesCardProps[]);
const topicId = route.query.topicid as string;
const topicName = route.query.topicName as string;

const connected = ref(false);
const from = ref("");
const text = ref("");
let stompClient: CompatClient | null = null;

const setConnected = (value: boolean) => {
  connected.value = value;
  if (!value) {
    messages.value = [];
  }
};

const connect = () => {
  const token = LocalStorage.getItem("jwt");
  const socket = SockJS("http://localhost:8080/api/ws?token=" + token);
  stompClient = Stomp.over(socket);
  stompClient.connect({}, (frame: string) => {
    setConnected(true);
    console.log("Connected: " + frame);
    stompClient?.subscribe(
      `http://localhost:8080/api/forum/messages/${topicId}`,
      (messageOutput) => {
        showMessageOutput(JSON.parse(messageOutput.body));
      }
    );
  });
};

const disconnect = () => {
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  setConnected(false);
  console.log("Disconnected");
};

const sendMessage = (message: string) => {
  if (stompClient && from.value && message) {
    stompClient.send(
      `http://localhost:8080/api/forum/messages/${topicId}`,
      {},
      JSON.stringify({ from: from.value, text: message })
    );
    text.value = "";
  }
};

const showMessageOutput = (messageOutput: MessagesCardProps) => {
  messages.value.push(messageOutput);
};

onMounted(async () => {
  try {
    const response = await api.get(`forum/messages/${topicId}`);
    messages.value = response.data;
    connect();
  } catch (error) {
    console.error("Failed to fetch messages:", error);
  }
});

onUnmounted(() => {
  disconnect();
});
</script>

<style scoped>
.q-pa-md {
  padding: 16px;
}
.page {
  display: flex;
  flex-direction: column;
  height: 100%;
  position: relative;
}
.messages-container {
  flex: 1;
  overflow-y: auto;
  padding-bottom: 60px;
}
.send-message {
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
}
#conversationDiv {
  visibility: visible;
}

p {
  word-wrap: break-word;
}
</style>
