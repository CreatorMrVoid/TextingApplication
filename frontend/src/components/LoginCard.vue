<template>
  <q-card class="q-pa-xl" style="width: 300px">
    <div class="q-gutter-md">
      <!-- Bind the username property to this input -->
      <q-input
        v-model="username"
        label="Kullanıcı Adı"
        outlined
        class="fixed-size-input"
      ></q-input>

      <!-- Bind the password property to this input -->
      <q-input
        v-model="password"
        label="Şifre"
        outlined
        type="password"
        class="fixed-size-input"
      ></q-input>
      <q-btn color="primary" label="Giriş Yap" @click="login" />
    </div>
  </q-card>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { api } from "boot/axios";

// Define the data properties
const username = ref("");
const password = ref("");

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
