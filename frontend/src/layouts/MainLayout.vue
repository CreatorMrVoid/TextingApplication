<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>
        <q-btn
          flat
          dense
          round
          icon="menu"
          aria-label="Menu"
          @click="toggleLeftDrawer"
        />

        <q-toolbar-title> The Message App </q-toolbar-title>

        <div>Quasar v{{ $q.version }}</div>
      </q-toolbar>
    </q-header>

    <q-drawer v-model="leftDrawerOpen" show-if-above bordered>
      <q-list>
        <q-item-label header> Essential Links </q-item-label>

        <EssentialLink
          v-for="link in linksList"
          :key="link.title"
          v-bind="link"
        />
        <EssentialLink
          :title="title"
          :caption="description"
          icon="bug_report"
          link="http://www.example.com"
        ></EssentialLink>
      </q-list>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script setup lang="ts">
import { ref } from "vue";
import EssentialLink, {
  EssentialLinkProps,
} from "components/EssentialLink.vue";
import { LocalStorage } from "quasar";
import { api } from "boot/axios";
import { useRouter } from "vue-router";

const token = LocalStorage.getItem("jwt");
const router = useRouter();

if (token) {
  api.defaults.headers.common["Authorization"] = "Bearer " + token;
  console.log("succes");
} else {
  router.replace("/");
}

defineOptions({
  name: "MainLayout",
});

let title = "Test";
let description = ref("testing");

setTimeout(() => {
  description.value = "testing 2";
}, 1000);

const linksList: EssentialLinkProps[] = [
  {
    title: "My Topics",
    caption: "Display My Topics",
    icon: "chat",
    link: "http://localhost:9000/#/forum/topics/mytopics",
  },
  {
    title: "All Topics Of The Forum",
    caption: "Display All The Topics",
    icon: "chat",
    link: "http://localhost:9000/#/forum/topics",
  },
  {
    title: "Developer",
    caption: "Özgün Bey",
    icon: "school",
    link: "https://instagram.com/ozgun_by",
  },
  {
    title: "Github",
    caption: "github.com/quasarframework",
    icon: "code",
    link: "https://github.com/quasarframework",
  },
  {
    title: "Discord Chat Channel",
    caption: "chat.quasar.dev",
    icon: "chat",
    link: "https://chat.quasar.dev",
  },
  {
    title: "Forum",
    caption: "forum.quasar.dev",
    icon: "record_voice_over",
    link: "https://forum.quasar.dev",
  },
  {
    title: "Twitter",
    caption: "@quasarframework",
    icon: "rss_feed",
    link: "https://twitter.quasar.dev",
  },
  {
    title: "Facebook",
    caption: "@QuasarFramework",
    icon: "public",
    link: "https://facebook.quasar.dev",
  },
  {
    title: "Quasar Awesome",
    caption: "Community Quasar projects",
    icon: "favorite",
    link: "https://awesome.quasar.dev",
  },
];

const leftDrawerOpen = ref(false);

function toggleLeftDrawer() {
  leftDrawerOpen.value = !leftDrawerOpen.value;
}
</script>
