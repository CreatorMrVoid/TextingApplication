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

        <div>
          Quasar v{{ $q.version }}
          <section>
            <q-avatar
              label="Send"
              type="submit"
              color="green"
              class="q-mx-lg"
              clickable
              v-ripple
              @click="viewProfile"
            ></q-avatar>
          </section>
        </div>
      </q-toolbar>
    </q-header>

    <q-drawer
      v-model="leftDrawerOpen"
      show-if-above
      bordered
      style="background-color: lightgray"
    >
      <q-list>
        <q-item-label header> Essential Links </q-item-label>

        <EssentialLink
          v-for="link in essentialLinks"
          :key="link.title"
          v-bind="link"
        />
        <q-item-label header> External Links </q-item-label>

        <ExternalLink
          v-for="link in externalLinks"
          :key="link.title"
          v-bind="link"
        />
      </q-list>
    </q-drawer>

    <q-page-container class="content-container">
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script setup lang="ts">
import { ref } from "vue";
import EssentialLink, {
  EssentialLinkProps,
} from "components/EssentialLink.vue";
import ExternalLink, { ExternalLinkProps } from "components/ExternalLink.vue";
import { LocalStorage } from "quasar";
import { api } from "boot/axios";
import { useRouter } from "vue-router";
import { Writer } from "src/types/types";

const token = LocalStorage.getItem("jwt");
const router = useRouter();
let user = ref<Writer>();
const avatarUrl = ref("Yükleniyor...");

api.get("/api/forum/messages").then((resp) => {
  user.value = resp.data;
  avatarUrl.value = user.value?.name ?? "Fallback Value"; // başka türlü error veriyor.
});

async function viewProfile() {
  // api.get("getuserprofileNavigation"); /** Profile information can be added at next versions  */
}

if (token) {
  api.defaults.headers.common["Authorization"] = "Bearer " + token;
  console.log("succes");
} else {
  router.replace("/");
}

defineOptions({
  name: "MainLayout",
});

const externalLinks: ExternalLinkProps[] = [
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

const essentialLinks: EssentialLinkProps[] = [
  {
    title: "My Topics",
    caption: "Display My Topics",
    icon: "chat",
    link: "/forum/topics/mytopics",
  },
  {
    title: "Liked Topics",
    caption: "Display All Liked Topics",
    icon: "favorite",
    link: "/forum/topics/likedtopics",
  },
  {
    title: "All Topics Of The Forum",
    caption: "Display All The Topics",
    icon: "list",
    link: "/forum/topics",
  },
];
const leftDrawerOpen = ref(false);

function toggleLeftDrawer() {
  leftDrawerOpen.value = !leftDrawerOpen.value;
}
</script>
<style scoped>
.q-header {
  z-index: 2;
}

.content-container {
  padding-top: 56px; /* Adjust based on your toolbar height */
  height: calc(100vh - 56px); /* Full height minus header height */
  position: relative;
  z-index: 1;
}
</style>
