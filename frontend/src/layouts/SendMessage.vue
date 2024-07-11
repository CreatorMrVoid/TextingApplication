<template>
  <div class="q-pa-md">
    <q-form @submit="onSend" class="q-gutter-md">
      <q-item>
        <q-item-section>
          <q-input
            v-model="text"
            color="primary"
            label="Enter Text"
            filled
            clearable
            :autocomplete="false"
          />
        </q-item-section>
        <q-item-section side>
          <q-btn label="Send" type="submit" color="green" />
        </q-item-section>
      </q-item>
    </q-form>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { useRoute } from "vue-router";
import { api } from "src/boot/axios";

const route = useRoute();
const text = ref("");
const topicId = route.query.topicid as string;

const onSend = async () => {
  await api.post("forum/messages/" + topicId, text.value, {
    headers: {
      "Content-Type": "text/plain",
    },
  });
  text.value = "";
};
</script>

<style scoped>
.q-pa-md {
  padding: 16px;
}
</style>
