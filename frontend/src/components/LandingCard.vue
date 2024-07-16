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
            v-model="usernameRegister"
            label="Kullanıcı Adı"
            outlined
            :rules="[(val) => !!val || '* Required']"
            class="fixed-size-input"
          ></q-input>

          <q-input
            v-model="passwordCheck"
            label="Şifre"
            outlined
            type="password"
            class="fixed-size-input"
            :rules="[(val) => !!val || '* Required']"
          ></q-input>

          <!-- Bind the password property to this input -->
          <q-input
            v-model="passwordRegister"
            label="Şifre Tekrar"
            outlined
            type="password"
            class="fixed-size-input"
            :rules="[(val) => !!val || '* Required', validatePasswordsMatch]"
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
import { useRouter } from "vue-router";
//import { useQuasar } from "quasar"; // localStorage kullanımı için eklendi
import { LocalStorage } from "quasar";
//import { Client, Message } from "@stomp/stompjs";

const username = ref("");
const usernameRegister = ref("");

const password = ref("");
const passwordCheck = ref("");
const passwordRegister = ref("");
const tab = ref("login");
const router = useRouter();

/*  const client = new StompJs.Client({
    brokerURL: "ws://localhost:8080/ws",
    connectHeaders: {
      login: "user",
      passcode: "password",
    },
    debug: function (str) {
      console.log(str);
    },
    reconnectDelay: 5000,
    heartbeatIncoming: 4000,
    heartbeatOutgoing: 4000,
  });
*/

async function login() {
  const body = { username: username.value, password: password.value };
  try {
    let response = await api.post("/auth/login", body, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    api.defaults.headers.common["Authorization"] = "Bearer " + response.data;
    LocalStorage.set("jwt", response.data);
    router.push("/forum/topics");
    /*  client.onConnect = function (frame) {
        // Do something, all subscribes must be done is this callback
        // This is needed because this will be executed after a (re)connect
      };
      client.onStompError = function (frame) {
        // Will be invoked in case of error encountered at Broker
        // Bad login/passcode typically will cause an error
        // Complaint brokers will set `message` header with a brief message. Body may contain details.
        // Compliant brokers will terminate the connection after any error
        console.log("Broker reported error: " + frame.headers["message"]);
        console.log("Additional details: " + frame.body);
      };
      client.activate();
    */
  } catch (error) {
    alert("Error: " + error);
  }
}

async function register() {
  const body = {
    username: usernameRegister.value,
    password: passwordRegister.value,
  };
  try {
    let response = await api.post("/auth/register", body, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    alert(response.data);
  } catch (error) {
    alert("Error: " + error);
  }
}

const validatePasswordsMatch = (val: string) => {
  return val === passwordCheck.value ? true : "Passwords must match!";
};
</script>
