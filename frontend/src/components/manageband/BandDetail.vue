<!-- 밴드소속 멤버들(크루원)에게 보여 줄 페이지 -->
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

      <!-- 밴드 소개  -->
      <v-col cols="4" class="ma-auto">
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
      <!-- 밴드 멤버 -->
      <v-col cols="4" class="ma-auto">
        <v-card>
          <v-card-title left
            >멤버구성
            <v-spacer></v-spacer>
            <v-btn @click="memberInvite()" x-small> 멤버초대 </v-btn>
          </v-card-title>
          <v-text-field
            v-for="member in members"
            :key="member.no"
            v-model="member.name"
            :label="member.session"
            readonly
            class="ma-auto pa-2"
          >
          </v-text-field>
        </v-card>
      </v-col>
    </v-row>
    <v-divider></v-divider>

    <!-- 달력 -->
    <v-row class="ma-auto px-10">
      
      <v-col cols="12">
        <!-- 제목(배너) -->
        <v-banner class="my-10">
          <strong>밴드일정</strong>
        </v-banner>
        <v-row>
          <v-col cols="6">
            <v-menu
              ref="dateOpen"
              v-model="dateOpen"
              :close-on-content-click="false"
              :return-value.sync="start"
              offset-y
            >
              <template v-slot:activator="{ on }">
                <v-text-field
                  v-model="start"
                  label="Start Date"
                  prepend-icon="mdi-calendar"
                  dense
                  readonly
                  outlined
                  hide-details
                  v-on="on"
                ></v-text-field>
              </template>

              <v-date-picker dark v-model="start" no-title>
                <v-spacer />
                <v-btn text dark color="primary" @click="dateOpen = false"
                  >Cancel</v-btn
                >
                <v-btn
                  text
                  dark
                  color="primary"
                  @click="$refs.dateOpen.save(start)"
                  >OK</v-btn
                >
              </v-date-picker>
            </v-menu>
          </v-col>
          <v-col cols="6">
            <v-select
              v-model="type"
              :items="typeOptions"
              label="Type"
              class="my-auto"
              hide-details
              outlined
              dense
            ></v-select>
          </v-col>
        </v-row>
        <div class="text-center mb-3">
          <h2 class="mx-5 d-inline-block">{{start | moment('YYYY MMMM')}}</h2>
        </div>   
        <v-sheet height="500">
          
          <v-calendar ref="calendar" 
          :type="type"></v-calendar>
        </v-sheet>
      </v-col>
    </v-row>

    <v-divider></v-divider>

    <!-- 공연영상 -->
    <v-row class="ma-auto px-10">
      <v-col>
        <!-- 제목(배너) -->
        <v-banner class="pa-10">
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

    <v-divider></v-divider>
    <!-- 활성화버튼 -->
    <v-row class="ma-auto pa-10">
      <!-- 밴드장일때 -->
      <v-col cols="12" v-if="member.isChief">
        <v-btn
          v-for="c in chief"
          :key="c.text"
          @click.native="onClick(c.text)"
          color="primary"
          class="mx-6"
        >
          {{ c.text }}
        </v-btn>
      </v-col>

      <!-- 밴드원일때 -->
      <v-col cols="12" v-else>
        <v-btn color="primary" class="mx-6" @click="upload()">영상업로드</v-btn>

        <!-- 밴드탈퇴 모달창 -->
        <v-dialog v-model="dialog" persistent max-width="290">
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              color="error"
              v-bind="attrs"
              v-on="on"
              class="white--text mx-10"
            >
              밴드탈퇴
            </v-btn>
          </template>
          <v-card style="opacity: 1">
            <v-card-title class="headline"> 밴드탈퇴 </v-card-title>
            <v-card-text>정말 탈퇴하시겠습니까?</v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="green darken-1" text @click="remove()"> 예 </v-btn>
              <v-btn color="green darken-1" text @click="dialog = false">
                아니오
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
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
      dialog: false,
      member: {
        //로그인 한 아이디의 정보
        isChief: false,
      },
      chief: [
        //밴드장일 때 나오는 버튼들
        {
          text: "밴드정보수정",
        },
        {
          text: "영상업로드",
        },
        {
          text: "공연신청",
        },
      ],
      //캘린더 관련 데이터
      dateOpen: false,
      start: "",
      type: "month",
      typeOptions: [
        { text: "Day", value: "day" },
        { text: "Week", value: "week" },
        { text: "Month", value: "month" },
      ],

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
          no: "1",
        },
        {
          name: "최베스",
          session: "베이스",
          no: "2",
        },
        {
          name: "김일렉",
          session: "일렉기타",
          no: "3",
        },
        {
          name: "최드럼",
          session: "드럼",
          no: "4",
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
      this.getMember(); //로그인 멤버 정보 가져오기
      */
     this.start = this.$moment().format('YYYY-MM-DD');
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
    getMember() {
      //밴드장 여부 확인을 위해 불러옴
      axios
        .get("/member/" + this.memberid)
        .then((response) => (this.member = response.data))
        .catch((exp) => alert(exp + "멤버정보 조회에 실패하였습니다."));
    },
    memberInvite() {
      //밴드 멤버 초대
      //영현이의 도움이 필요
      console.log("멤버초대");
    },
    videoDetail(value) {
      //비디오게시판 비디오 상세로 라우터링크
      this.$router.push("/video/detail/" + value);
    },
    onClick(value) {
      console.log(value);
      if (value == "밴드정보수정") {
        //this.$router.push("/band/modify/" + this.$route.params.bandno);
      } else if (value == "영상업로드") {
        this.upload();
      } else if (value == "공연신청") {
        //this.$router.push("/reserve/stage/" + this.$route.params.bandno);
      }
    },
    remove() {
      //밴드탈퇴
      axios
        .delete("/band/member/" + this.$route.params.bandno, {
          memberid: this.member.id,
        })
        .then((response) => {
          if (response.data == "success") {
            alert("탈퇴에 성공하였습니다.");
            this.dialog = false;
            this.$router.push({ name: "main" });
          }
        })
        .catch((exp) => alert(exp + "탈퇴에 실패하였습니다."));
    },
    upload() {
      //영상업로드
      console.log("영상업로드페이지로 이동");
      //this.$router.push("/video/create/" + this.$route.params.bandno);
    },
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