<template>
  <div class="q-pa-md">
    <q-form @send="onSend" class="q-gutter-md">
      <q-input
        name="name"
        autocomplete="name"
        v-model="name"
        color="primary"
        label="Full name"
        filled
        clearable
      />
      <div>
        <q-btn label="Send" type="send" color="primary" />
      </div>
    </q-form>
  </div>
</template>

<script>
import { ref } from "vue";

export default {
  setup() {
    const sendResult = ref([]);

    return {
      name: ref(),
      sendResult,

      onSend(evt) {
        const formData = new FormData(evt.target);
        const data = [];

        for (const [name, value] of formData.entries()) {
          data.push({
            name,
            value,
          });
        }

        sendResult.value = data;
      },
    };
  },
};
</script>
