<template>
  <v-row
    ><v-spacer /><v-col class="my-10" align="center">
      <video id="localVideo" autoplay playsinline></video>
      <video id="remoteVideo" autoplay playsinline></video>
      <div>
        <v-btn class="mx-3" id="testButton" @click="onClickTest"
          >broadcast</v-btn
        >
        <v-btn class="mx-3" id="viewButton" @click="onClickOffer">offer</v-btn>
        <v-btn class="mx-3" id="watchButton" @click="onClickWatch">watch</v-btn>
      </div> </v-col
    ><v-spacer />
  </v-row>
</template>

<script>
import * as client from "@/js/client.js";

export default {
  data() {
    return {
      localStream: null,
    };
  },
  mounted() {},
  methods: {
    onClickOffer() {
      client.createOffer();
    },
    onClickTest() {
      client.broadcast2().then(function (result) {
        const localVideo = document.getElementById("localVideo");
        localVideo.srcObject = result;
      });
    },
    onClickWatch() {
      const remoteVideo = document.getElementById("remoteVideo");
      remoteVideo.srcObject = client.watch();
    },
  },
};
</script>