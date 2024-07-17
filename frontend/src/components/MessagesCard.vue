<template>
  <div class="q-pa-md row justify-center">
    <div style="width: 100%; max-width: 400px">
      <p class="text-accent">
        <q-chat-message
          :name="props.writer"
          :text="[props.text]"
          sent
          bg-color="positive"
        />
      </p>
    </div>
  </div>
</template>

<script setup lang="ts">
import { MessagesCardProps } from "src/types/types";

const props = defineProps<MessagesCardProps>();
</script>
