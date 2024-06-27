<template>
  <q-page class="column items-center justify-evenly">
    <TopicsCard v-for="n in 50" :key="n" :topic-name="`Topic ${n}`" />
  </q-page>
</template>

<script setup lang="ts">
import TopicsCard from "components/TopicsCard.vue";
import { ref, onMounted } from "vue";
import axios from "axios";

const topics = ref([]);

onMounted(async () => {
  try {
    const response = await axios.get("/api/topics");
    topics.value = response.data;
  } catch (error) {
    console.error("Error fetching topics:", error);
  }
});
</script>
