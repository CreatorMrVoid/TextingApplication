<template>
  <div class="q-pa-md">
    <q-form @send="onSend" class="q-gutter-md">
      <q-item>
        <q-item-section>
          <q-input
            text="text"
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

<script setup>
import { ref } from "vue";
import { api } from "src/boot/axios";
import { useRoute } from "vue-router";
import { Client, Message } from "@stomp/stompjs";

const route = useRoute();
const text = ref("");
let topicId = route.query.topicid;
const client = new StompJs.Client();
client.brokerURL = "ws://localhost:8080/ws";

const onSend = async (evt) => {
  evt.preventDefault();

  const body = { text: text.value };

  try {
    client.publish({
      destination: "/forum/message" + topicId,
      body,
    });

    await api.post("/forum/message" + topicId, body);
    text.value = "";
  } catch (error) {
    alert("Error: " + error);
  }
};
</script>

<style scoped>
.send-message {
  width: 100%;
  position: absolute;
  bottom: 0;
  left: 0;
  background: white;
  box-shadow: 0 -2px 10px rgba(0, 0, 0, 0.1);
}
</style>
