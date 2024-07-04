<template>
  <q-layout>
    <q-page-container>
      <q-page>
        <div
          class="fullscreen row wrap justify-center items-center content-center"
        >
          <LikedTopicsPage />
        </div>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script setup lang="ts">
import LikedTopicsPage from "components/LikedTopicsPage.vue";
</script>
