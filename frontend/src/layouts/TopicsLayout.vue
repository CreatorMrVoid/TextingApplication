<template>
  <q-layout>
    <q-page-container>
      <q-page>
        <div
          class="fullscreen row wrap justify-center items-center content-center"
        >
          <TopicsPage />
        </div>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script setup lang="ts">
import TopicsPage from "components/TopicsPage.vue";
</script>
