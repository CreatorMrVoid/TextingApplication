<template>
  <q-item clickable v-ripple>
    <q-item-section avatar>
      <q-avatar color="primary" text-color="white"> Q </q-avatar>
    </q-item-section>

    <q-item-section>
      <q-item-label>Contact #{{}}</q-item-label>
      <q-item-label caption lines="1">some@email.com</q-item-label>
    </q-item-section>

    <q-item-section side>
      <q-icon name="chat_bubble" color="green" />
    </q-item-section>
  </q-item>
</template>

<style lang="sass" scoped>
.example-item
  height: 56px
  // props here
</style>
