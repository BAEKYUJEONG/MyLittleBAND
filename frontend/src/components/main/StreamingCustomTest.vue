<template>
  <v-row
    ><v-spacer /><v-col class="my-10" align="center">
      <video id="remoteVideo" autoplay playsinline></video>
      <div>
        <v-btn class="mx-3" id="watchButton" @click="onClickWatch">watch</v-btn>
        <v-btn class="mx-3" id="moveButton" @click="onClickMove">move</v-btn>
      </div> </v-col
    ><v-spacer />
  </v-row>
</template>

<script>
import * as client from '@/js/client.js';

export default {
  methods: {
    onClickWatch() {
      const remoteVideo = document.getElementById('remoteVideo');
      remoteVideo.srcObject = client.watch();
    },
    onClickMove() {
      this.$router.push('/bandbroadcast');
    },
  },
};
</script>
