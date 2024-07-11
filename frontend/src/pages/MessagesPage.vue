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
    <SendMessage />
  </q-page>
</template>

<script setup lang="ts">
import { ref, onMounted, onUnmounted } from "vue";
import { useRoute } from "vue-router";
import { api } from "boot/axios";
import { MessagesCardProps } from "src/types/types";
import SendMessage from "src/layouts/SendMessage.vue";
import MessagesCard from "src/components/MessagesCard.vue";
import { connect, getSocket } from "src/sockets/sockets";

const route = useRoute();
const messages = ref([] as MessagesCardProps[]);
const topicId = route.query.topicid as string;
const topicName = route.query.topicName as string;

const addMessage = (message: MessagesCardProps) => {
  messages.value.push(message);
};

onMounted(async () => {
  try {
    let response = await api.get(`forum/messages/${topicId}`);
    messages.value = response.data;
  } catch (error) {
    console.error("Failed to fetch messages:", error);
  }
  connect(topicId, "join_group").then(() => {
    getSocket().on("message_sent", (msg: any) => {
      console.log(msg);
      addMessage(msg);
    });
  });
});

onUnmounted(() => {
  // Disconnect from the Socket.IO server
  const socket = getSocket();
  if (socket) {
    socket.disconnect();
  }
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
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
}
</style>
