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
        <q-btn color="primary" label="Delete Topic" @click="DeleteTopic" />
      </q-item>
    </q-list>
  </div>
</template>

<script setup lang="ts">
import { api } from "boot/axios";
import { MyTopicsCardProps } from "src/types/types";
import { useRouter, useRoute } from "vue-router";

const props = defineProps<MyTopicsCardProps>();
const router = useRouter();
const route = useRoute();
route.query.topicid;

async function view() {
  router.push("/forum/messages?topicid=" + props.id);
}

async function DeleteTopic() {
  try {
    const body = { props: props.id.valueOf };
    let response = await api.post("forum/topics/delete", body);
    alert(response);
  } catch (error) {
    alert("Error: " + error);
  }
}
</script>
