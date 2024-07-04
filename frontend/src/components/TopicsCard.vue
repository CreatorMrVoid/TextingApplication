<template>
  <div class="q-pa-md" style="max-width: 3000px">
    <q-list bordered>
      <q-item class="q-my-sm" clickable v-ripple @click="view">
        <q-item-section avatar>
          <q-avatar color="primary" text-color="white">
            {{ props.topicName.charAt(0) }}
          </q-avatar>
        </q-item-section>

        <q-item-section>
          <q-item-label>{{ props.topicName }}</q-item-label>
          <q-item-label caption lines="1">{{}}</q-item-label>
        </q-item-section>

        <q-item-section side>
          <q-icon name="chat_bubble" color="green" />
        </q-item-section>

        <q-separator> </q-separator>

        <q-item-section>
          <q-item-label caption lines="1">{{ "Description:" }} </q-item-label>
          <q-item-label> {{ props.topicDescription }}</q-item-label>
        </q-item-section>
        <q-item-section side>
          <q-item-label>
            {{ "Created By: " + props.topicCreatorName }}</q-item-label
          >
        </q-item-section>
      </q-item>
      <q-item>
        <q-btn color="primary" label="Like" @click="likeTopic" />
      </q-item>
    </q-list>
  </div>
</template>

<script setup lang="ts">
import { api } from "boot/axios";
import { useRouter } from "vue-router";

const router = useRouter();

export interface TopicsCardProps {
  id: string;
  topicName: string;
  topicDescription: string;
  topicCreatorName: string;
}

const props = defineProps<TopicsCardProps>();

async function view() {
  try {
    await api.get("forum/topics/topicmessages");
  } catch (error) {
    alert("Error: " + error);
  }
}

async function likeTopic() {
  try {
    await api.post("/auth/like");
    router.push("/forum/topics/topic-id"); // burada yönlendirme nasıl olacak
  } catch (error) {
    alert("Error: " + error);
  }
}
</script>
