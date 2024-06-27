<template>
  <q-item clickable v-ripple>
    <q-item-section avatar>
      <q-avatar color="primary" text-color="white"> Q </q-avatar>
    </q-item-section>

    <q-item-section>
      <q-item-label>{{ topicName }}</q-item-label>
    </q-item-section>

    <q-item-section side>
      <q-icon name="chat_bubble" color="green" />
    </q-item-section>
  </q-item>
</template>

<style lang="sass" scoped>
.example-item
{
  height: 56px
}
</style>

<script setup lang="ts">
import { defineProps } from "vue";

interface Props {
  topicName: string;
}

const props = defineProps<Props>();
</script>
