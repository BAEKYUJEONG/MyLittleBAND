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
            <img :src="video.img" alt="John" />
          </v-avatar>
        </v-col>
        <strong class="black--text mt-6">{{ video.boardSubject }}</strong>
        <v-spacer></v-spacer>
        <!-- 좋아요 버튼 -->
        <v-col cols="4" class="ma-auto">
          <v-tooltip bottom nudge-bottom="20">
            <template v-slot:activator="{ on, attrs }">
              <v-btn icon :color="color" v-bind="attrs" v-on="on">
                <v-icon
                  v-if="member.follow"
                  size="80"
                  @click="unLike()"
                  color="red"
                  >mdi-heart</v-icon
                >
                <v-icon v-else size="80" @click="setLike()" color="gray"
                  >mdi-heart</v-icon
                >
              </v-btn>
            </template>
            <span v-if="!member.follow">좋아요 버튼</span>
            <span v-else>좋아요 취소</span>
          </v-tooltip>
        </v-col>
        <strong class="black--text mt-6">{{ video.boardLike }}</strong>
      </v-row>
      <v-row
        ><v-col
          ><p class="black--text text-left">{{ video.boardContent }}</p></v-col
        ></v-row
      >
      <v-divider inset></v-divider>
      <h3 class="display-2 mt-4">댓글</h3>

      <!-- 댓글 작성 -->
      <v-row class="mt-2"
        ><v-col cols="auto"
          ><v-avatar>
            <img :src="memberinfo.img" alt="video.boardId" /> </v-avatar
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
          <v-list-item v-for="(comment, i) in comments" :key="i">
            <v-list-item-icon>
              <v-list-item-avatar>
                <v-img :src="comment.img"></v-img>
              </v-list-item-avatar>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title v-text="comment.content"></v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-row>
    </v-container>
  </v-main>
</template>

<script>
import 'video.js/dist/video-js.css';
import axios from '@/axios/axios-common.js';

import { videoPlayer } from 'vue-video-player';
import { mapActions, mapGetters } from 'vuex';

const VideoStore = 'VideoStore';
const MemberStore = 'MemberStore';

export default {
  components: {
    videoPlayer,
  },
  created() {
    this.playerOptions.sources[0].src = this.video.boardVideoUrl;
    //console.log(this.video.boardId);
    this.reqComments(this.video.boardId);
    this.getLike();
  },
  data() {
    return {
      usercomment: '',
      color: '',
      member: {
        follow: false, //true이면 icon이 빨간색, false면 회색
        followId: '',
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
    ...mapGetters(MemberStore, {
      memberid: 'getMemberId',
      memberinfo: 'getMemberInfo',
    }),
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
      //밴드 팔로우 여부 불러옴
      axios
        .get(
          '/likecheck?boardId=' +
            this.video.boardId +
            '&memberId=' +
            this.memberid
        )
        .then((response) => {
          if (response.data.status) {
            this.member.follow = true;
            this.member.followId = response.data.object.boardId;
          }
        })
        .catch((exp) => {
          console.log(exp + '언팔로우중');
          this.member.follow = false;
        });
    },
    setLike() {
      //팔로우상태변화 (false => true 바꿔줌)
      //팔로우시켜줌
      console.log(this.video.boardId);
      console.log(this.memberid);
      axios
        .post('/like', {
          boardId: this.video.boardId,
          memberId: this.memberid,
        })
        .then((response) => {
          if (response.data.status) {
            this.member.follow = true;
            this.member.followId = response.data.object.likeId;
          }
        })
        .catch((exp) => alert(exp + '팔로우 상태 변화에 실패하였습니다.'));
    },
    unLike() {
      //팔로우상태변화 (true => false 바꿔줌)
      //언팔로우시켜줌
      axios
        .delete('/like/likelist/' + this.member.followId)
        .then((response) => {
          if (response.data.status) {
            this.member.follow = false;
            this.member.followId = '';
          }
        })
        .catch((exp) => alert(exp + '팔로우 상태 변화에 실패하였습니다.'));
      console.log('팔로우상태 : 언팔로우');
    },
    onWrite() {
      //댓글 등록.
      axios
        .post('/comment', {
          commentId: null,
          boardId: this.video.boardId,
          memberId: this.memberid,
          content: this.usercomment,
        })
        .then((response) => {
          if (response.data.status) console.log('성공!');
        })
        .catch((exp) => alert(exp + '댓글 등록 실패.'));
      this.usercomment = '';
    },
  },
};
</script>
