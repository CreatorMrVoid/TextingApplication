<template>
  <div class="q-pa-md row justify-center">
    <div style="width: 100%; max-width: 400px">
      <div v-for="message in messages" :key="message.id">
        <q-chat-message
          :name="message.writer.name"
          :avatar="message.writer.avatarUrl"
          :text="[message.text]"
          :stamp="new Date(message.timestamp).toLocaleString()"
          sent
        />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { MessagesCardProps } from "src/types/types";

const props = defineProps<MessagesCardProps>();
</script>
