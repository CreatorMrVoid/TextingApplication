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
import { getSocket } from "src/sockets/sockets";

const route = useRoute();
const text = ref("");
const topicId = route.query.topicid as string;
const username = route.query.username as string;

const onSend = () => {
  const socket = getSocket();
  if (socket) {
    socket.emit("messageSendToUser", {
      text: text.value,
      writer: username,
      topicId: topicId,
    });
  }
  text.value = "";
};
</script>

<style scoped>
.q-pa-md {
  padding: 16px;
}
</style>
