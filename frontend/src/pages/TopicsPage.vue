<template>
  <q-page>
    <div class="q-pa-md" style="max-width: 300px">
      <q-toolbar class="bg-primary text-white shadow-2">
        <q-toolbar-title>Topics</q-toolbar-title>
      </q-toolbar>
    </div>
    <TopicsCard
      v-for="topic in topics"
      :key="topic.id"
      :id="topic.id"
      :topic-name="topic.topicName"
      :topic-description="topic.topicDescription"
      :topic-creator-name="topic.topicCreatorName"
    />
    <TopicOptionsCard />
  </q-page>
</template>

<script setup lang="ts">
import TopicsCard from "components/TopicsCard.vue";
import TopicOptionsCard from "src/components/TopicOptionsCard.vue";
import { ref, onMounted } from "vue";
import { api } from "boot/axios";
import { TopicsCardProps } from "src/types/types";

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
