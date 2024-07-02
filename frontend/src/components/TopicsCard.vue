<template>
  <div class="q-pa-md" style="max-width: 6000px">
    <q-list bordered>
      <q-item class="q-my-sm" clickable v-ripple>
        <q-item-section avatar>
          <q-avatar color="primary" text-color="white">
            {{ props.topicName.charAt(0) }}
          </q-avatar>
        </q-item-section>

        <q-item-section>
          <q-item-label>{{ props.topicName }}</q-item-label>
          <q-item-label caption lines="1">{{
            "Created By: " + props.topicCreatorName
          }}</q-item-label>
        </q-item-section>

        <q-item-section side>
          <q-icon name="chat_bubble" color="green" />
        </q-item-section>

        <q-separator> </q-separator>

        <q-item-section>
          <q-item-label caption lines="1">{{ "Description:" }} </q-item-label>
          <q-item-label> {{ props.topicDescription }}</q-item-label>
        </q-item-section>
      </q-item>
    </q-list>
  </div>
</template>

<script setup lang="ts">
// import { title } from "process";

export interface TopicsCardProps {
  id: string;
  topicName: string;
  topicDescription: string;
  topicCreatorName: string;
}

const props = defineProps<TopicsCardProps>();
</script>
