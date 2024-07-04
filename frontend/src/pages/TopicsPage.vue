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
      @topic-click="navigateToTopic"
    />
    <TopicOptionsCard />
  </q-page>
</template>

<script setup lang="ts">
import TopicsCard, { TopicsCardProps } from "components/TopicsCard.vue";
import TopicOptionsCard from "src/components/TopicOptionsCard.vue";
import { ref, onMounted } from "vue";
import { api } from "boot/axios";
import { useRouter } from "vue-router";

const topics = ref([] as TopicsCardProps[]);
const router = useRouter();

onMounted(async () => {
  try {
    let response = await api.get("forum/topics");
    topics.value = response.data;
  } catch (error) {
    alert("Error: " + error);
  }
});

const navigateToTopic = (topicId: string) => {
  // Navigate to the topic page with the topic id
  router.push("forum/topic/message");
};
</script>
