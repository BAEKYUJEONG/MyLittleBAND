<template>
  <!-- 동작에 따른 이벤트 처리가 가능한듯..? -->
  <v-main>
    <v-container>
      <v-row justify="center" class="pt-4 mt-10"
        ><v-col cols="auto">
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
            responsive: true
          >
          </video-player> </v-col
        ></v-row>
      <v-row justify="center">
        <v-col cols="auto">
          <v-avatar>
            <img :src="getVideo.img" alt="John" />
          </v-avatar>
        </v-col>
        <strong class="black--text mt-6">{{ getVideo.boardSubject }}</strong>
        <v-spacer></v-spacer>
        <!-- 좋아요 버튼 -->
        <v-col cols="1" class="ma-auto">
          <v-tooltip bottom nudge-bottom="20">
            <template v-slot:activator="{ on, attrs }">
              <v-btn icon :color="color" v-bind="attrs" v-on="on">
                <v-icon
                  v-if="member.follow"
                  size="60"
                  @click="unLike()"
                  color="red"
                  >mdi-heart</v-icon
                >
                <v-icon v-else size="40" @click="setLike()" color="gray"
                  >mdi-heart</v-icon
                >
              </v-btn>
            </template>
            <span v-if="!member.follow">좋아요 버튼</span>
            <span v-else>좋아요 취소</span>
          </v-tooltip>
          <strong class="black--text mt-6 ml-3">{{ like }}</strong>
        </v-col>
      </v-row>
      <v-row class="mb-10"
        ><v-col
          ><p class="black--text text-left">
            {{ getVideo.boardContent }}
          </p></v-col
        ><v-spacer></v-spacer>
        <v-col><p class="black--text text-right">
            조회수 : {{ getVideo.boardView }}
          </p></v-col>
        </v-row
      >

      <v-card class="pa-10" color="rgba(200, 200, 200, 0.5)">
        <v-card-title class="headline">댓글</v-card-title>

        <!-- 댓글 작성 -->
        <v-row class="mt-4"
          ><v-col cols="auto">
            <v-avatar> <img :src="getMemberInfo.img" alt="John" /> </v-avatar
          ></v-col>
          <v-col>
            <v-text-field
              :counter="200"
              label="내용"
              name="usercomment"
              required
              v-model="usercomment"
              maxlength="50"
            ></v-text-field>
          </v-col>
          <v-col cols="auto">
            <v-btn block outlined dense color="secondary" @click="onWrite"> 등록 </v-btn>
          </v-col>
        </v-row>

        <!-- 작성된 댓글 목록-->
        <v-row class="mt-2">
          <v-list shaped color="rgba(255, 255, 255, 0)">
            <v-list-item v-for="(comment, i) in getComments" :key="i">
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
      </v-card>

      <!-- 뒤로가기 -->
      <v-layout>
        <v-flex text-right>
           <v-btn
            icon
            color="primary"
            large
            class="ma-10"
            @click="
              () => {
                this.$router.go(-1);
              }
            "
          >
            <v-icon size="80">mdi-undo-variant</v-icon>
           </v-btn>
        </v-flex>
      </v-layout>
    </v-container>
  </v-main>
</template>

<script>
import 'video.js/dist/video-js.css';
import axios from '@/axios/axios-common.js';

import { videoPlayer } from 'vue-video-player';
import { mapActions, mapGetters, mapMutations } from 'vuex';

const VideoStore = 'VideoStore';
const MemberStore = 'MemberStore';

export default {
  components: {
    videoPlayer,
  },
  created() {
    this.reqVideo(this.$route.params.no)
    .then((response)=> {
      if(!response){
        this.playerOptions.sources[0].src = this.getVideo.boardVideoUrl;
        this.reqComments(this.getVideo.boardId);
        this.getLike();
        this.reqMemberInfo(this.getMemberId);
        this.like = this.getVideo.boardLike;
        console.log(this.getVideo)
      }
    })
  },
  data() {
    return {
      like: 0,
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
        width:"1120px",
        height:"630px",
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
    ...mapGetters(VideoStore, ['getVideo', 'getComments']),
    ...mapGetters(MemberStore, ['getMemberId', 'getMemberInfo']),
  },
  methods: {
    // listen event
    ...mapActions(VideoStore, ['reqVideo', 'reqComments']),
    ...mapActions(MemberStore, ['reqMemberInfo']),
    ...mapMutations(VideoStore, ['setComments']),
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
            this.getVideo.boardId +
            '&memberId=' +
            this.getMemberId
        )
        .then((response) => {
          if (response.data.status) {
            this.like = this.getVideo.boardLike;
            this.member.follow = true;
            this.member.followId = response.data.object.likeId;
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
      console.log(this.getVideo.boardId);
      console.log(this.getMemberId);
      axios
        .post('/like', {
          boardId: this.getVideo.boardId,
          memberId: this.getMemberId,
        })
        .then((response) => {
          if (response.data.status) {
            this.like++;
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
            this.like--;
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
          boardId: this.getVideo.boardId,
          memberId: this.getMemberId,
          content: this.usercomment,
        })
        .then((response) => {
          if (response.data.succes == 'success') {
            console.log('성공!');
            this.reqComments(this.getVideo.boardId);
          }
        })
        .catch((exp) => alert(exp + '댓글 등록 실패.'));
      this.usercomment = '';
    },
  },
};
</script>
