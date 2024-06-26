<template>
  <q-card class="q-pa-md">
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
      </q-tab-panel>

      <q-tab-panel name="register">
        <div class="q-gutter-md">
          <!-- Bind the username property to this input -->
          <q-input
            v-model="username"
            label="Kullanıcı Adı"
            outlined
            class="fixed-size-input"
          ></q-input>

          <q-input
            v-model="passwordCheck"
            label="Şifre"
            outlined
            type="password"
            class="fixed-size-input"
            :rules="[
              (val) => !!val || '* Required',
              (val) => val != passwordRegister || 'Passwords must match!',
            ]"
          ></q-input>

          <!-- Bind the password property to this input -->
          <q-input
            v-model="passwordRegister"
            label="Şifre Tekrar"
            outlined
            type="password"
            class="fixed-size-input"
            :rules="[
              (val) => !!val || '* Required',
              (val) => val != passwordCheck || 'Passwords must match!',
            ]"
          ></q-input>
          <q-btn color="primary" label="Kayıt Ol" @click="register" />
        </div>
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
const passwordCheck = ref("");
const passwordRegister = ref("");
const tab = ref("login");

async function login() {
  const body = { password: password, username: username };
  try {
    let response = await api.get("/auth/login", { data: body });
    alert(response.data);
  } catch (error) {
    alert("Error: " + error);
  }
}

async function register() {
  const body = { password: password, username: username };
  try {
    let response = await api.get("/auth/register", { data: body });
    alert(response.data);
  } catch (error) {
    alert("Error: " + error);
  }
}
</script>
