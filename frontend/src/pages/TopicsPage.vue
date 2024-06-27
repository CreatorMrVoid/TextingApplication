<template>
  <q-page class="column items-center justify-evenly">
    <TopicsCard v-for="n in 50" :key="n"> </TopicsCard>
  </q-page>
</template>

<script setup lang="ts">
import { ref } from "vue";
import TopicsCard from "components/TopicsCard.vue";
</script>
