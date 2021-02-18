<template>
  <v-main>
    <v-container>
      <!-- <Streaming />
      <v-divider></v-divider> -->
      <v-row>
        <v-col cols="8">
      <StreamingCustomTest/>
      </v-col>
      <v-col cols="4">
      <Timeline />
</v-col >
      </v-row>
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
