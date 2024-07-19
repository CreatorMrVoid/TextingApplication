<template>
  <div class="q-pa-md q-gutter-sm">
    <q-page-sticky position="bottom-right" :offset="[18, 18]">
      <q-fab
        v-model="fab1"
        label="Topic options"
        label-position="left"
        color="purple"
        icon="keyboard_arrow_up"
        direction="up"
      >
        <q-fab-action
          color="primary"
          @click="openDialog"
          icon="edit_document"
          label="New Topic"
        />
        <q-fab-action
          color="primary"
          @click="openSearch"
          icon="search"
          label="Search Topic"
        />
      </q-fab>
    </q-page-sticky>

    <q-dialog v-model="isDialogOpen">
      <q-card style="min-width: 350px">
        <q-card-section>
          <div class="text-h6">New Topic</div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          <q-input
            dense
            v-model="topicName"
            label="Name: "
            outlined
            class="fixed-size-input"
            :rules="[(val) => !!val || '* Required']"
            autofocus
          />
        </q-card-section>

        <q-card-section class="q-pt-none">
          <q-input
            dense
            v-model="topicDescription"
            label="Description: "
            outlined
            class="fixed-size-input"
            :rules="[(val) => !!val || '* Required']"
            autofocus
          />
        </q-card-section>

        <q-card-actions align="left" class="text-primary">
          <q-btn flat label="Cancel" v-close-popup @click="closeDialog" />
          <q-btn flat label="Create Topic" v-close-popup @click="createTopic" />
        </q-card-actions>
      </q-card>
    </q-dialog>

    <!-- Search Topic Part-->
    <q-dialog v-model="isSearchDialogOpen">
      <q-card style="min-width: 350px">
        <q-card-section>
          <div class="text-h6">Search Topic</div>
        </q-card-section>

        <q-card-section class="q-pt-none">
          <q-input
            dense
            v-model="topicName"
            label="Name: "
            outlined
            class="fixed-size-input"
            :rules="[(val) => !!val || '* Required']"
            autofocus
          />
        </q-card-section>

        <q-card-actions align="left" class="text-primary">
          <q-btn flat label="Cancel" v-close-popup @click="closeSearch" />
          <q-btn flat label="Search Topic" v-close-popup @click="searchTopic" />
          <q-icon icon="search" />
          <!-- Neden item gözükmüyor-->
        </q-card-actions>
      </q-card>
    </q-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { api } from "boot/axios";
import { useRouter } from "vue-router";

const router = useRouter();
const topicName = ref("");
const topicDescription = ref("");
const fab1 = ref(false);
const isDialogOpen = ref(false);
const isSearchDialogOpen = ref(false);

const openDialog = () => {
  isDialogOpen.value = true;
};

const closeDialog = () => {
  isDialogOpen.value = false;
};

const openSearch = () => {
  isSearchDialogOpen.value = true;
};

const closeSearch = () => {
  isSearchDialogOpen.value = false;
};

const createTopic = async () => {
  try {
    let response = await api.post("forum/topics", {
      name: topicName.value,
      description: topicDescription.value,
    });
    console.log(response.data); // + " BURASI BİLDİRİM OLARAK GÖZÜKMELİ, ALERT KALDIRILMALI";
  } catch (error) {
    alert("Error: " + error);
  }
  isDialogOpen.value = false;
  router.push("/forum/topics");
};

const searchTopic = async () => {
  // The backend logic can be added at next versions of the application
  try {
    let response = await api.post("forum/topics/search", {
      name: topicName.value,
      description: topicDescription.value,
    });
    alert(response.data);
  } catch (error) {
    alert("Error: " + error);
  }
  isSearchDialogOpen.value = false;
};
</script>
