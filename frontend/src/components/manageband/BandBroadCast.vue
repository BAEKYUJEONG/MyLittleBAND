<template>
  <v-row
    ><v-spacer /><v-col class="my-10" align="center">
      <video id="localVideo" autoplay playsinline></video>
      <div>
        <v-btn class="mx-3" id="testButton" @click="onClickTest"
          >broadcast</v-btn
        >
        <v-btn class="mx-3" id="viewButton" @click="onClickOffer">offer</v-btn>
      </div> </v-col
    ><v-spacer />
  </v-row>
</template>

<script>
import * as caller from '@/js/broadcast.js';

export default {
  methods: {
    onClickOffer() {
      caller.createOffer();
    },
    onClickTest() {
      caller.broadcast2().then(function(result) {
        const localVideo = document.getElementById('localVideo');
        localVideo.srcObject = result;
      });
    },
  },
};
</script>
