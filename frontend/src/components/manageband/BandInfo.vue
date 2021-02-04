<!-- 팔로우 할 멤버들에게 보여 줄 페이지 -->
<template>
  <v-main>
    <v-row  class="px-10">
      <v-col cols="12">
        <v-banner class="my-10">
          <strong>밴드소개</strong>
        </v-banner>
      </v-col>
    </v-row>

    <v-row class="ma-auto pa-10" justify="center">
      <!-- 밴드 프로필 이미지-->
      <v-col cols="4" class="ma-auto">
        <v-spacer></v-spacer>
        <v-card
          max-height="300"
          max-width="300"
          id="rounded-card"
          class="ma-auto"
        >
          <v-layout column align-center justify-center>
            <v-img
              class="ma-auto mp-4"
              max-height="100%"
              max-width="100%"
              style="border-radius: 50%"
              contain
              :src="band.imgurl"
            >
            </v-img>
          </v-layout>
        </v-card>
      </v-col>

      <!-- 밴드 소개 -->
      <v-col cols="4">
        <v-text-field
          v-model="band.name"
          label="밴드명"
          outlined
          readonly
        ></v-text-field>

        <v-textarea
          v-model="band.profile"
          label="소개"
          outlined
          readonly
        ></v-textarea>
      </v-col>
      <!-- 좋아요 버튼 -->
      <v-col cols="4" class="ma-auto">
        <v-tooltip bottom nudge-bottom="20" >
          <template v-slot:activator="{ on, attrs }">
        <v-btn icon :color="color"
         v-bind="attrs"
            v-on="on">
          <v-icon
            size="100"
            @click="setFollow()"
            :color="member.follow ? 'red' : 'grey'"
            >mdi-heart</v-icon
          >
        </v-btn>
          </template>
        <span v-if="!member.follow">밴드 팔로우하기</span>
        <span v-else>밴드 팔로우취소</span>
        </v-tooltip>
      </v-col>
    </v-row>
    <v-divider></v-divider>
    <!-- 멤버구성 -->
    <v-row  class="px-10">
      <v-col cols="12">
        <v-banner class="my-10">
          <strong>멤버구성</strong>
        </v-banner>
      </v-col>
    </v-row>
    <v-row class="ma-auto pa-10">
      <v-card
        v-for="member in members"
        :key="member.id"
        max-height="200"
        max-width="200"
        id="rounded-card"
        class="ma-auto"
      >
        <v-layout column align-center justify-center>
          <v-img
            class="ma-auto mp-4"
            max-height="100%"
            max-width="100%"
            style="border-radius: 50%"
            contain
            :src="band.imgurl"
          >
          </v-img>
          <v-slide-y-transition>
            <v-card-text
              >{{ member.name }} /
              {{ member.session }}
            </v-card-text>
          </v-slide-y-transition>
        </v-layout>
      </v-card>
    </v-row>

    <v-divider></v-divider>

    <!-- 공연영상 -->
    <v-row class="ma-auto px-10">
      <v-col>
        <!-- 제목(배너) -->
        <v-banner class="my-10">
          <strong>공연영상</strong>
        </v-banner>

        <!-- 목록 -->
        <v-sheet class="mx-auto" elevation="8">
          <v-slide-group class="pa-4" show-arrows>
            <v-slide-item
              v-for="video in videolist"
              :key="video.videono"
              v-slot="{ active }"
            >
              <v-card
                :color="active ? 'grey lighten-1' : ''"
                class="d-flex ma-4 flex-end flex-column"
                height="300"
                width="500"
                @click="[active, videoDetail(video.videono)]"
              >
                <v-card-text class="text-end mt-auto">
                  <p>{{ video.title }}</p>
                </v-card-text>
              </v-card>
            </v-slide-item>
          </v-slide-group>
        </v-sheet>
      </v-col>
    </v-row>
  </v-main>
</template>

<script>
import { mapGetters } from "vuex"; //vuex사용
const MemberStore = "MemberStore"; //MemberStore 모듈 사용
import axios from "axios";

export default {
  data: () => {
    return {
      color : '',
      member: {
        follow: false,//true이면 icon이 빨간색, false면 회색
      },
      band: {
        //밴드정보
        name: "잘하는밴드",
        profile: "홍대에 터를 두고 활동하는 잘하는밴드입니다.",
        imgurl: require("@/assets/image/pepe.jpg"),
      },
      members: [
        //소속멤버정보
        {
          name: "이보드",
          session: "키보드",
        },
        {
          name: "최베스",
          session: "베이스",
        },
        {
          name: "김일렉",
          session: "일렉기타",
        },
        {
          name: "최드럼",
          session: "드럼",
        },
      ],
      videolist: [
        {
          title: "가을 홍대공연",
          videono: "123", //이동할 게시판의 게시글번호
        },
        {
          title: "200202 홍대버스킹",
          videono: "456",
        },
        {
          title: "191213 합정역 5번출구",
          videono: "78",
        },
        {
          title: "문래 재즈바 공연",
          videono: "91",
        },
      ],
    };
  },
  computed: {
    ...mapGetters(MemberStore, {
      //MemberStore 모듈 내 getters 사용
      memberid: "getMemberId", //memberid 변수에 getMemberId 리턴값 저장
    }),
  },
  created() {
    //백엔드와 연동 후 아래 주석 해제
    /*
      this.getBandinfo();//밴드정보가져오기
      this.getMemberinfo();//밴드소속 멤버정보 가져오기
      this.getVideolist();//밴드의 비디오리스트 가져오기
      this.getFollow(); //멤버의 팔로우 여부 가져오기
      */
  },
  methods: {
    getBandinfo() {
      //밴드 정보 불러옴
      axios
        .get("/band/" + this.$route.params.bandno)
        .then((response) => (this.band = response.data))
        .catch((exp) => alert(exp + "밴드정보 조회에 실패하였습니다."));
    },
    getMemberinfo() {
      //밴드 소속 멤버 정보를 불러옴
      axios
        .get("/band/member/" + this.$route.params.bandno)
        .then((response) => (this.members = response.data))
        .catch((exp) => alert(exp + "소속 멤버 조회에 실패하였습니다."));
    },
    getVideolist() {
      //밴드의 비디오 리스트를 조회합니다.
      axios
        .post("/videosearchbyfilter", { bandid: this.$route.params.bandno })
        .then((response) => (this.videolist = response.data))
        .catch((exp) => alert(exp + "비디오리스트 조회에 실패하였습니다."));
    },
    getFollow() {
      //밴드 팔로우 여부 불러옴
      axios
        .get("/member/" + this.memberid)
        .then((response) => (this.member = response.data))
        .catch((exp) => alert(exp + "멤버정보 조회에 실패하였습니다."));
    },
    setFollow() {
      if (this.member.follow) {
        //팔로우 상태일 때
        this.member.follow = false;
        //DB 팔로우 상태 변화
        //this.memberModify()
        console.log("팔로우상태 : 팔로우취소");
      } else {
        //팔로우 중이 아닐 때
        this.member.follow = true;
        //DB 팔로우 상태 변화
        //this.memberModify()
        console.log("팔로우상태 : 팔로우");
      }
    },
    memberModify(){//회원 팔로우 상태 변경
        axios
      .put('/member/'+this.memberid,{member : this.member})
      .then((response)=>{
        if(response.data == "success"){
          console.log("팔로우 상태변화 성공")
        }
      })
      .catch((exp)=>alert(exp+"수정에 실패하였습니다."));
    }
  },
};
</script>

<style>
#rounded-card {
  border-radius: 50%;
  min-height: 200px;
  min-width: 200px;
}
</style>