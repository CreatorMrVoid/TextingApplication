<template>
  <q-form
    @submit="onSend"
    class="fit row wrap justify-center items-center content-center"
    style="width: 100%"
  >
    <q-input
      v-model="text"
      color="primary"
      label="Enter Text"
      style="width: 70%"
      filled
      clearable
      :autocomplete="false"
      autogrow
      class="q-ma-md"
    />
    <q-btn label="Send" type="submit" color="green" class="q-mx-lg" />
  </q-form>
</template>

<script setup lang="ts">
import { ref, defineEmits } from "vue";
import { useRoute } from "vue-router";
import { api } from "src/boot/axios";

const emits = defineEmits(["send-message"]);
const route = useRoute();
const text = ref("");
const topicId = route.query.topicid as string;

const onSend = async () => {
  await api.post(`forum/messages/${topicId}`, text.value, {
    headers: {
      "Content-Type": "text/plain",
    },
  });

  emits("send-message", text.value);
  text.value = "";
};
</script>

<style scoped>
.q-pa-md {
  padding: 16px;
}
.q-page-sticky.bottom-sticky {
  width: 100%;
  z-index: 10;
  pointer-events: auto;
}

.q-page-sticky.bottom-sticky .q-card {
  width: 100%;
  background-color: white; /* Adjust as needed */
}

.q-input__inner {
  min-width: 100%;
  max-width: 100%;
}
</style>
