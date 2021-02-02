<template>
  <v-main>
    <v-container>
      <!-- <Streaming /> -->
      <!-- <v-divider></v-divider> -->
      <Streaming2 />
      <v-divider></v-divider>
      <Timeline />
      <v-divider></v-divider>
      <Ranking />
    </v-container>
  </v-main>
</template>

<script>
// import Streaming from "./Straming";
import Streaming2 from "./Straming2";
import Timeline from "./TimeLine";
import Ranking from "./Ranking";

export default {
  components: {
    // Streaming,
    Streaming2,
    Timeline,
    Ranking,
  },
};
</script>

<style></style>
