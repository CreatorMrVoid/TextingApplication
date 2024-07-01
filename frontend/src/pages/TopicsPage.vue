<template>
  <q-page class="column items-center justify-evenly">
    <TopicsCard
      v-for="topic in topics"
      :key="topic.id"
      :id="topic.id"
      :topic-name="topic.topicName"
      :topic-title="topic.topicTitle"
      :topic-creator-name="topic.topicCreatorName"
    />
  </q-page>
</template>

<script setup lang="ts">
import TopicsCard, { TopicsCardProps } from "components/TopicsCard.vue";
import { ref, onMounted } from "vue";
import { api } from "boot/axios";

const topics = ref([] as TopicsCardProps[]);

onMounted(async () => {
  try {
    let response = await api.get("forum/topics");
    topics.value = response.data;
  } catch (error) {
    alert("Error: " + error);
  }
});
</script>
