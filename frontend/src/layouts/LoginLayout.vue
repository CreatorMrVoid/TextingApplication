<template>
  <q-layout>
    <q-page-container>
      <q-page>
        <div
          class="fullscreen row wrap justify-center items-center content-center"
        >
          <LandingCard />
        </div>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script setup lang="ts">
import LandingCard from "components/LandingCard.vue";
</script>
