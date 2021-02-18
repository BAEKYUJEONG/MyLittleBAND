<template>
  <v-container class="my-20">
    <v-card
      height="758px"
      elevation="5"
      class="pa-7"
      color="rgba(255, 255, 255, 0.5)"
    >
      <v-row>
          <v-col cols="4">
            <v-banner single-line  class="mb-5" style="font-size : 20px">
              <v-icon  size="45">mdi-video-wireless</v-icon>
              <strong class="ml-10">실시간 스트리밍</strong>
            </v-banner>
          </v-col>
        </v-row>

      <v-row>
        <v-col class="my-10" align="center">
          <video id="remoteVideo" autoplay playsinline></video>
        </v-col>
      </v-row>
      <v-row align="end" justify="center">
        <v-col cols="auto">
          <v-btn class="mx-3" id="watchButton" style="font-size : 22px;" @click="onClickWatch">시청하기</v-btn
          >
          <v-btn class="mx-3" id="moveButton" style="font-size : 22px;" @click="onClickMove">방송하러가기</v-btn>
        </v-col>
      </v-row>
    </v-card>
  </v-container>
</template>

<script>
import * as client from "@/js/client.js";

export default {
  methods: {
    onClickWatch() {
      const remoteVideo = document.getElementById("remoteVideo");
      remoteVideo.srcObject = client.watch();
    },
    onClickMove() {
      this.$router.push("/bandbroadcast");
    },
  },
};
</script>
