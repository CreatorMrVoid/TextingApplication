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
          icon="chat"
          label="New Topic"
        />
      </q-fab>
    </q-page-sticky>

    <q-dialog v-model="isDialogOpen">
      <q-card style="min-width: 350px">
        <q-card-section>
          <div class="text-h6">topic</div>
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
            v-model="topicTitle"
            label="Title: "
            outlined
            class="fixed-size-input"
            :rules="[(val) => !!val || '* Required']"
            autofocus
          />
        </q-card-section>

        <q-card-actions align="left" class="text-primary">
          <!-- <q-btn flat label="Cancel" v-close-popup /> -->
          <q-btn flat label="Cancel" v-close-popup @click="closeDialog" />
          <q-btn flat label="Create Topic" v-close-popup @click="createTopic" />
        </q-card-actions>
      </q-card>
    </q-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { api } from "boot/axios";
import { useRouter } from "vue-router";

const topicName = ref("");
const topicTitle = ref("");
const fab1 = ref(true);
const isDialogOpen = ref(false);
const router = useRouter();

const openDialog = () => {
  isDialogOpen.value = true;
};

const closeDialog = () => {
  isDialogOpen.value = false;
};

async function createTopic() {
  try {
    let response = await api.post("forum/topics");
    alert(response.data);
  } catch (error) {
    alert("Error: " + error);
  }
  isDialogOpen.value = false;
}
</script>
