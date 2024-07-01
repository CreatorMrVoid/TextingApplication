<template>
  <q-item clickable v-ripple>
    <q-item-section avatar>
      <q-avatar color="primary" text-color="white"> Q </q-avatar>
    </q-item-section>

    <q-item-section>
      <q-item-label>{{ props.topicName }}</q-item-label>
      <q-item-label>{{ props.topicTitle }}</q-item-label>
      <q-item-label>{{ props.topicCreatorName }}</q-item-label>
    </q-item-section>

    <q-item-section side>
      <q-icon name="chat_bubble" color="green" />
    </q-item-section>
  </q-item>
</template>

<script setup lang="ts">
// import { defineProps } from "vue";

export interface TopicsCardProps {
  id: string;
  topicName: string;
  topicTitle: string;
  topicCreatorName: string;
}

const props = defineProps<TopicsCardProps>();

/*
const props = withDefaults(defineProps<TopicsCardProps>(), {
  id: await api.get
  topicName:
  topicTitle:
  topicCreatorName:
});
*/
</script>
