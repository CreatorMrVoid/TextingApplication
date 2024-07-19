<template>
  <q-page>
    <div class="q-pa-md" style="max-width: 300px">
      <q-toolbar class="bg-secondary text-white shadow-2">
        <q-toolbar-title>My Topics</q-toolbar-title>
      </q-toolbar>
    </div>
    <MyTopicsCard
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
import MyTopicsCard from "components/MyTopicsCard.vue";
import TopicOptionsCard from "src/components/TopicOptionsCard.vue";
import { ref, onMounted } from "vue";
import { api } from "boot/axios";
import { MyTopicsCardProps } from "src/types/types";

const topics = ref([] as MyTopicsCardProps[]);

onMounted(async () => {
  try {
    let response = await api.get("forum/topics/created");
    topics.value = response.data;
  } catch (error) {
    alert("Error: " + error);
  }
});
</script>
