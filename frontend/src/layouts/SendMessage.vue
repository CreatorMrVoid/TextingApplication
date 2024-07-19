<template>
  <div>
    <q-form
      @submit="onSend"
      class="fit row wrap justify-start items-start content-start"
    >
      <q-item>
        <q-item-section>
          <q-input
            v-model="text"
            color="primary"
            label="Enter Text"
            filled
            clearable
            :autocomplete="false"
            autogrow
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
}

.q-page-sticky.bottom-sticky .q-card {
  width: 100%;
  background-color: white; /* Adjust as needed */
}

.q-input__inner {
  min-width: 100%;
}
.q-input__inner {
  max-width: 100%;
}
</style>
