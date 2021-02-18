<template>
  <v-main>
      <v-btn
      dark
      @mouseover="snackbar = true"
      @mouseleave="snackbar = false"
    >
      Open Snackbar
      </v-btn>

    <v-snackbar
    centered
      v-model="snackbar"
    >
      {{ text }}

      <template v-slot:action="{ attrs }">
        <v-btn
          color="pink"
          text
          v-bind="attrs"
          @click="snackbar = false"
        >
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </v-main>
</template>

<script>
export default {
  data(){
    return{
      snackbar: false,
      text: `Hello, I'm a snackbar`,
    }
  }
}
</script>

<style>

</style>