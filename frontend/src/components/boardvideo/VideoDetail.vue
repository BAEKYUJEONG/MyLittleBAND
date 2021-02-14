<template>
  <!-- 동작에 따른 이벤트 처리가 가능한듯..? -->
  <v-main>
    <v-container>
      <v-row
        ><v-spacer /><v-col align="center">
          <video-player
            class="video-player-box"
            ref="videoPlayer"
            :options="playerOptions"
            :playsinline="true"
            customEventName="customstatechangedeventname"
            @play="onPlayerPlay($event)"
            @pause="onPlayerPause($event)"
            @statechanged="playerStateChanged($event)"
            @ready="playerReadied"
          >
          </video-player> </v-col
        ><v-spacer
      /></v-row>
      <v-row justify="center">
        <v-col cols="auto">
          <v-avatar>
            <img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John" />
          </v-avatar>
        </v-col>
        <strong class="black--text mt-6">{{ video.boardSubject }}</strong>
        <v-spacer></v-spacer>
        <v-col cols="auto">
          <v-icon
            size="50"
            @click="setLike()"
            :color="member.like ? 'red' : 'grey'"
            >mdi-heart</v-icon
          >
        </v-col>
        <strong class="black--text mt-6">좋아요 수</strong>
      </v-row>
      <v-row
        ><v-col><p class="black--text text-left">설명</p></v-col></v-row
      >
      <v-divider inset></v-divider>
      <h3 class="display-2 mt-4">댓글</h3>

      <!-- 댓글 작성 -->
      <v-row class="mt-2"
        ><v-col cols="auto"
          ><v-avatar>
            <img
              src="https://cdn.vuetifyjs.com/images/john.jpg"
              alt="John"
            /> </v-avatar
        ></v-col>
        <v-col>
          <v-text-field
            :counter="50"
            label="내용"
            name="usercomment"
            required
            v-model="usercomment"
            maxlength="50"
          ></v-text-field>
        </v-col>
        <v-col cols="auto">
          <v-btn block outlined color="blue" @click="onWrite"> 등록 </v-btn>
        </v-col>
      </v-row>

      <!-- 작성된 댓글 목록-->
      <v-row class="mt-2">
        <v-list shaped>
          <v-list-item v-for="(comment, i) in getComments" :key="i">
            <v-list-item-icon>
              <v-list-item-avatar>
                <v-img :src="comment.avatar"></v-img>
              </v-list-item-avatar>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title v-text="comment.text"></v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-row>
    </v-container>
  </v-main>
</template>

<script>
import 'video.js/dist/video-js.css';
//import axios from '@/axios/axios-common.js';

import { videoPlayer } from 'vue-video-player';
import { mapActions, mapGetters } from 'vuex';

const VideoStore = 'VideoStore';

export default {
  components: {
    videoPlayer,
  },
  created() {
    this.playerOptions.sources[0].src =
      'https://i4a408.p.ssafy.io/video/asd.mp4';
    this.reqComments(4);
    //console.log(this.getVideo);
    //this.reqVideo(this.getVideo.videonum);
    //this.playerOptions.sources[0].src = this.video.src; 백엔드에서 가져올 경우 이렇게 수정
  },
  data() {
    return {
      usercomment: '',
      member: {
        like: false, //true랑 fasle 다르게 함.
      },
      playerOptions: {
        // 처음에 음소거 설정 필수.
        muted: true,
        language: 'en',
        // 배속
        playbackRates: [0.7, 1.0, 1.5, 2.0],

        // 영상의 타입과 영상 주소. src를 바꿔주면 됨.
        sources: [
          {
            type: 'video/mp4',
            src: '',
          },
        ],
        // 썸네일
        poster: '../assets/logo.png',
      },
    };
  },
  mounted() {
    console.log('this is current player instance object', this.player);
  },
  computed: {
    player() {
      return this.$refs.videoPlayer.player;
    },
    ...mapGetters(VideoStore, { video: 'getVideo', comments: 'getComments' }),
  },
  methods: {
    // listen event
    ...mapActions(VideoStore, ['reqComments']),
    onPlayerPlay(player) {
      console.log('player play!', player);
    },
    onPlayerPause(player) {
      console.log('player pause!', player);
    },
    // ...player event

    // or listen state event
    playerStateChanged(playerCurrentState) {
      console.log('player current update state', playerCurrentState);
    },

    // player is ready
    playerReadied(player) {
      console.log('the player is readied', player);
      // you can use it to do something...
      // player.[methods]
    },
    getLike() {
      // 좋아요 여부 확인.
      // 백엔드에서 해당 부분 만들어줘야함.
      // axios
      //   .post('/likecheck', {
      //     memberId: this.memberid,
      //     videoId: this.video.no,
      //   })
      //   .then((response) => {
      //     if (response.data.object != null) this.member.like = true;
      //   })
      //   .catch((exp) => alert(exp + '멤버정보 조회에 실패하였습니다.'));
    },
    setLike() {
      //좋아요 상태변화 (true <=> false 바꿔줌)
      // 백엔드에서 해당 부분 만들어줘야함.
      // axios
      //   .put('/like', {
      //     memberId: this.memberid,
      //     bandId: this.$route.params.bandno,
      //   })
      //   .then((response) => {
      //     if (response.data.status) {
      //       this.member.like = !this.member.like;
      //     }
      //   })
      //   .catch((exp) => alert(exp + '팔로우 상태 변화에 실패하였습니다.'));
    },
    onWrite() {
      this.usercomment = '';
      // axios
      //   .post('/comment/'+this.video.videonum, {
      //     comment: this.usercomment,
      //     memberid: '', //userid도 얻어와야 할 듯.
      //   })
      //   .then((response) => {
      //     if (response.data.status) consoe.log("성공!");
      //   })
      //   .catch((exp) => alert(exp + '멤버정보 조회에 실패하였습니다.'));
    },
  },
};
</script>
