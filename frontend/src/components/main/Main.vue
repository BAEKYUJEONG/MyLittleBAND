<template>
  <v-main>
    <v-container>
      <Streaming />
      <v-divider></v-divider>
      <Timeline />
      <v-divider></v-divider>
      <Ranking />
    </v-container>
  </v-main>
</template>

<script>
import Streaming from "./Straming";
import Timeline from "./TimeLine";
import Ranking from "./Ranking";

export default {
  components: {
    Streaming,
    Timeline,
    Ranking,
  },
};
</script>

<style></style>
