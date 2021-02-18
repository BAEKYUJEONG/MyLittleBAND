<template>
  <v-row
    ><v-spacer /><v-col class="my-10" align="center">
      <video id="localVideo" autoplay playsinline></video>
      <div>
        <v-btn class="mx-3" id="testButton" @click="onClickTest"
          >broadcast</v-btn
        >
      </div> </v-col
    ><v-spacer />
  </v-row>
</template>

<script>
import * as caller from '@/js/newbroadcast.js';

export default {
  mounted() {
    caller.flag = true;
  },
  methods: {
    onClickTest() {
      caller.broadcast2().then(function (result) {
        const localVideo = document.getElementById('localVideo');
        localVideo.srcObject = result;
      });
    },
  },
};
</script>
