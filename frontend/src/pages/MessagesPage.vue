<template>
  <div class="fullscreen custom-background">
    <q-page-sticky
      position="top"
      expand
      class="bg-primary text-white"
      style="z-index: 1"
    >
      <q-toolbar class="bg-secondary text-white shadow-2">
        <q-btn flat round dense icon="tornado" />
        <q-toolbar-title :title="topicName" />
      </q-toolbar>
    </q-page-sticky>
  </div>
  <q-page-container>
    <MessagesCard
      v-for="message in messages"
      :key="message.id"
      :id="message.id"
      :text="message.text"
      :writer="message.writer"
    />
  </q-page-container>
  <q-container>
    <q-page-sticky position="bottom" expand class="col">
      <q-card>
        <SendMessage />
      </q-card>
    </q-page-sticky>
  </q-container>
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
import { TopicsCardProps } from "src/types/types";

const route = useRoute();
const messages = ref([] as MessagesCardProps[]);
const topicId = route.query.topicid as string;
const topicName = ref("Yükleniyor...");
const connected = ref(false);
// const from = ref("");
// const text = ref("");
let stompClient: CompatClient | null = null;
const topic = ref<TopicsCardProps>();

api.get("/api/forum/messages").then((resp) => {
  topic.value = resp.data;
  topicName.value = topic.value?.topicName ?? "Fallback Value"; // başka türlü error veriyor.
});

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
  stompClient.connect({ JsonWebToken: token }, (frame: string) => {
    setConnected(true);
    console.log("Connected: " + frame);
    stompClient?.subscribe(`/api/ws/messages/${topicId}`, (messageOutput) => {
      console.log("Got message: " + messageOutput.body);
      showMessageOutput(JSON.parse(messageOutput.body));
    });
  });
};

const disconnect = () => {
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  setConnected(false);
  console.log("Disconnected");
};

/*
const sendMessage = (message: string) => {
  if (stompClient && from.value && message) {
    stompClient.send(
      `/api/ws/chat/${topicId}`,
      {},
      JSON.stringify({ from: from.value, text: message })
    );
    text.value = "";
  }
};
*/

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
.page-sticky {
  background-color: rgb(7, 206, 140);
}
</style>
<style>
.custom-background {
  color: rgb(182, 12, 35);
}
</style>
