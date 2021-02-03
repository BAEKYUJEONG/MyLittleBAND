<template>
  <v-main>
    <v-container>
      <!-- <Streaming />
      <v-divider></v-divider> -->
      <StramingCustom/>
      <v-divider></v-divider>
      <Timeline />
      <v-divider></v-divider>
      <Ranking />
    </v-container>
  </v-main>
</template>

<script>
//import Streaming from "./Straming";
import Timeline from "./TimeLine";
import Ranking from "./Ranking";

import StramingCustom from "./StramingCustom";

export default {
  components: {
    //Streaming,
    Timeline,
    Ranking,
    StramingCustom,
  },
};
</script>

<style></style>
