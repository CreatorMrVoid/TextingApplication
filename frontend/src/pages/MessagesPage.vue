<template>
  <q-page>
    <div>
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

<script lang="ts">
import { ref, onMounted } from "vue";
import { api } from "boot/axios";
import { MessagesCardProps } from "src/types/types";

const messages = ref([] as MessagesCardProps[]);

onMounted(async () => {
  try {
    let response = await api.get("forum/topics/messages");
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
</style>
