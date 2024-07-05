<template>
  <q-page class="page">
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
import { ref, onMounted } from "vue";
import { api } from "boot/axios";
import { MessagesCardProps } from "src/types/types";
import SendMessage from "src/layouts/SendMessage.vue";

const messages = ref([] as MessagesCardProps[]);

onMounted(async () => {
  try {
    let response = await api.get("forum/messages");
    messages.value = response.data;
  } catch (error) {
    console.error("Failed to fetch messages:", error);
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
