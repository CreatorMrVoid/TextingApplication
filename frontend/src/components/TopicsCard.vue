<template>
  <div class="q-pa-md" style="max-width: 3000px">
    <q-list bordered style="background-color: lightgray">
      <q-item class="q-my-sm" clickable v-ripple @click="view">
        <q-item-section avatar>
          <q-avatar color="green" text-color="white">
            {{ props.topicName.charAt(0) }}
          </q-avatar>
        </q-item-section>

        <q-item-section>
          <q-item-label caption lines="1">{{ "Name:" }}</q-item-label>
          <q-item-label>{{ props.topicName }}</q-item-label>
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
import { useRouter, useRoute } from "vue-router";
import { TopicsCardProps } from "src/types/types";

const router = useRouter();
const route = useRoute();
route.query.topicid;

const props = defineProps<TopicsCardProps>();

async function view() {
  //router.push("/forum/messages/" + props.id);
  router.push("/forum/messages?topicid=" + props.id);
}

async function likeTopic() {
  try {
    await api.post("/forum/topics/like?topicid=" + props.id);
    //router.get("/forum/topics/like?topicid=" + props.id);
  } catch (error) {
    alert("Error: " + error);
  }
}
</script>
