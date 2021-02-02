<template>
  <!-- 동작에 따른 이벤트 처리가 가능한듯..? -->
  <v-row
    ><v-spacer /><v-col class="my-10" align="center">
      <!-- <video-player
        class="video-player-box"
        ref="videoPlayer"
        :options="playerOptions"
        :playsinline="true"
        customEventName="customstatechangedeventname"
        @play="onPlayerPlay($event)"
        @pause="onPlayerPause($event)"
        @ended="onPlayerEnded($event)"
        @waiting="onPlayerWaiting($event)"
        @playing="onPlayerPlaying($event)"
        @loadeddata="onPlayerLoadeddata($event)"
        @timeupdate="onPlayerTimeupdate($event)"
        @canplay="onPlayerCanplay($event)"
        @canplaythrough="onPlayerCanplaythrough($event)"
        @statechanged="playerStateChanged($event)"
        @ready="playerReadied"
      >
      </video-player> -->

     </v-col
    ><v-spacer />
  </v-row>
</template>

<script>
//import "video.js/dist/video-js.css";

//import { videoPlayer } from "vue-video-player";

export default {
  components: {
    // videoPlayer,
  },
  created() {},
  data() {
    return {
      playerOptions: {
        // videojs options

        // 처음에 음소거 설정
        muted: true,
        language: "en",
        // 배속
        playbackRates: [0.7, 1.0, 1.5, 2.0],

        // 영상의 타입과 영상 주소. src를 바꿔주면 됨.
        sources: [
          {
            type: "video/mp4",
            src:
              "https://cdn.theguardian.tv/webM/2015/07/20/150716YesMen_synd_768k_vp8.webm",
          },
        ],
        // 썸네일
        poster: "../assets/logo.png",
      },
      localStream: null,
    };
  },
  mounted() {
    let gotLocalMediaStream;
    const mediaStreamConstraints = {
      video: true,
    };

    const localVideo = document.querySelector("video");
    console.log(localVideo);

    // Handles success by adding the MediaStream to the video element.
    gotLocalMediaStream = (mediaStream) => {
      this.localStream = mediaStream;
      localVideo.srcObject = mediaStream;
    };

    // Handles error by logging a message to the console with the error message.
    function handleLocalMediaStreamError(error) {
      console.log("navigator.getUserMedia error: ", error);
    }

    //((a,b,c) => console.log(a+b+c))(1,3,5)
    // Initializes media stream.
    navigator.mediaDevices
      .getUserMedia(mediaStreamConstraints)
      .then(gotLocalMediaStream)
      .catch(handleLocalMediaStreamError);
  },
  computed: {
    player() {
      return this.$refs.videoPlayer.player;
    },
  },
  methods: {
    // listen event
    onPlayerPlay(player) {
      console.log("player play!", player);
    },
    onPlayerPause(player) {
      console.log("player pause!", player);
    },
    // ...player event

    // or listen state event
    playerStateChanged(playerCurrentState) {
      console.log("player current update state", playerCurrentState);
    },

    // player is ready
    playerReadied(player) {
      console.log("the player is readied", player);
      // you can use it to do something...
      // player.[methods]
    },
  },
};
</script>
