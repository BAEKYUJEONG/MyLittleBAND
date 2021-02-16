<template>
  <v-main>
    <v-container>
      <!-- <Streaming />
      <v-divider></v-divider> -->
      <StreamingCustomTest/>
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
import StreamingCustomTest from "./StreamingCustomTest";

export default {
  components: {
    //Streaming,
    Timeline,
    Ranking,
    StreamingCustomTest,
  },
};
</script>
