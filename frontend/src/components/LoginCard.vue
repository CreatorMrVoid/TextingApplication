<template>
  <q-card>
    <q-tabs
      v-model="tab"
      dense
      class="text-grey"
      active-color="primary"
      indicator-color="primary"
      narrow-indicator
    >
      <q-tab name="login" label="Login"></q-tab>
      <q-tab name="register" label="Register"></q-tab>
    </q-tabs>

    <q-separator></q-separator>

    <q-tab-panels v-model="tab" animated>
      <q-tab-panel name="login">
        <div class="text-h6">Mails</div>
        Lorem ipsum dolor sit amet consectetur adipisicing elit.
        <div class="text-h6">Mails</div>
        Lorem ipsum dolor sit amet consectetur adipisicing elit.
      </q-tab-panel>

      <q-tab-panel name="register">
        <div class="text-h6">Alarms</div>
        Lorem ipsum dolor sit amet consectetur adipisicing elit.
      </q-tab-panel>
    </q-tab-panels>

    <q-separator></q-separator>

    <q-tabs
      v-model="tab"
      dense
      :class="$q.dark.isActive ? 'bg-grey-9' : 'bg-grey-3'"
      narrow-indicator
    >
    </q-tabs>
  </q-card>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { api } from "boot/axios";

// Define the data properties
const username = ref("");
const password = ref("");
const tab = ref("login");

async function login() {
  //TODO
  const body = { password: password, username: username };
  try {
    let response = await api.get("/auth/login", { data: body });
    alert(response.data);
  } catch (error) {
    alert("Error: " + error);
  }
}
</script>
