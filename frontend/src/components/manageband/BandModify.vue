<template>
  <v-main>
    <v-row class="px-10" justify="center">
      <v-col cols="6">
        <v-banner class="my-10">
          <strong>밴드정보수정</strong>
        </v-banner>
      </v-col>
    </v-row>
    <v-row class="px-10" justify="center">
      <v-col cols="6">
        <v-text-field v-model="band.name" label="밴드명" outlined clearable>
        </v-text-field>

        <v-file-input
          accept="image/*"
          label="밴드프로필 이미지(추후 추가예정)"
          @change="onChangeImage"
        ></v-file-input>

        <v-textarea
          v-model="band.intro"
          label="밴드소개"
          outlined
          height="200px"
        ></v-textarea>
      </v-col>
    </v-row>
    <!-- 밴드 멤버 -->
    <v-row>
      <v-col cols="2"> </v-col>
      <v-col cols="2" class="px-30">
        <v-banner>
          <strong>멤버구성</strong>
        </v-banner>
      </v-col>
    </v-row>
    <v-row
      v-for="member in members"
      :key="member.no"
      justify="center"
      class="px-10"
    >
      <v-col cols="2">
        <v-text-field outlined v-model="member.name" readonly> </v-text-field>
      </v-col>
      <v-col cols="4">
        <v-select
          outlined
          :items="sessions"
          v-model="member.session"
          :disabled="member.no == memberid || isChief ? false : true"
        ></v-select>
      </v-col>
      <v-col cols="1">
        <!-- 밴드탈퇴 모달창 -->
        <v-dialog
          v-if="member.no == memberid || isChief"
          v-model="dialog.Member"
          persistent
          max-width="290"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              color="error"
              v-bind="attrs"
              v-on="on"
              class="white--text mx-4"
            >
              밴드탈퇴
            </v-btn>
          </template>
          <v-card style="opacity: 1">
            <v-card-title class="headline"> 밴드탈퇴 </v-card-title>
            <v-card-text>정말 탈퇴하시겠습니까?</v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="green darken-1" text @click="removeMember()">
                예
              </v-btn>
              <v-btn color="green darken-1" text @click="dialog.Member = false">
                아니오
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-col>

      <v-col cols="1">
        <!-- 밴드장위임 모달창 -->
        <v-dialog
          v-if="isChief && member.no != memberid"
          v-model="dialog.Chief"
          persistent
          max-width="290"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              color="primary"
              v-bind="attrs"
              v-on="on"
              class="white--text mx-4"
            >
              밴드장위임
            </v-btn>
          </template>
          <v-card style="opacity: 1">
            <v-card-title class="headline"> 밴드장위임 </v-card-title>
            <v-card-text>정말 위임하시겠습니까?</v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="green darken-1" text @click="changechief()">
                예
              </v-btn>
              <v-btn color="green darken-1" text @click="dialog.Chief = false">
                아니오
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-col>
    </v-row>

    <!-- 버튼들 -->
    <v-row class="ma-auto">
      <v-col cols="12" class="ma-auto">
        <v-btn color="primary" class="mx-6" @click="modify()">수정하기</v-btn>

        <v-btn class="mx-6" @click="banddetail()">돌아가기</v-btn>

        <!-- 밴드해체 모달창 -->
        <v-dialog
          v-if="isChief"
          v-model="dialog.Crew"
          persistent
          max-width="290"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              color="error"
              v-bind="attrs"
              v-on="on"
              class="white--text mx-4"
            >
              밴드해체
            </v-btn>
          </template>
          <v-card style="opacity: 1">
            <v-card-title class="headline"> 밴드해체 </v-card-title>
            <v-card-text>정말 해체하시겠습니까?</v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="green darken-1" text @click="removeCrew()"> 예 </v-btn>
              <v-btn color="green darken-1" text @click="dialog.Crew = false">
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
import axios from "../../axios/axios-common";
import { mapGetters } from "vuex"; //vuex사용
const MemberStore = "MemberStore"; //MemberStore 모듈 사용

export default {
  data: () => {
    return {
      dialog: {
        //모달창 관리
        Member: false, //멤버탈퇴
        Chief: false, //밴드장위임
        Crew: false, //밴드해체
      },
      isChief: false,
      band: {
        name: "",
        intro: "",
        img: "",
      },
      sessions: ["보컬", "키보드", "드럼", "일렉기타", "베이스"],
      members: [
        //소속멤버정보
        {
          name: "이보드",
          session: "키보드",
          no: "12",
        },
        {
          name: "최베스",
          session: "베이스",
          no: "1",
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
    };
  },
  computed: {
    ...mapGetters(MemberStore, {
      //MemberStore 모듈 내 getters 사용
      memberid: "getMemberId", //memberid 변수에 getMemberId 리턴값 저장
    }),
  },
  created() {
    //밴드정보가져오기
    this.getBandinfo();
    //this.getMemberinfo();//밴드소속 멤버정보 가져오기
    //this.getMember(); //로그인 멤버 정보 가져오기
  },
  methods: {
    onChangeImage() {
      //밴드 이미지 수정
    },
    getBandinfo() {
      //밴드 정보 불러옴
      axios
        .get("/band/" + this.$route.params.bandno)
        .then((response) => {
          this.band = response.data.object;
          //console.log(response.data.object.name)
        })
        .catch((exp) => alert(exp + "밴드정보 조회에 실패하였습니다."));
    },
    getMemberinfo() {
      //밴드 소속 멤버 정보를 불러옴
      axios
        .get("/band/member/" + this.$route.params.bandno)
        .then((response) => {
          this.members = response.data.object
          //console.log(response.data.object)
          })
        .catch((exp) => alert(exp + "소속 멤버 조회에 실패하였습니다."));
    },
    getMember() {
      //밴드장 여부 확인을 위해 불러옴
      axios
        .get("/member/" + this.memberid)
        .then((response) => (this.isChief = response.data.object.isChief))
        .catch((exp) => alert(exp + "멤버정보 조회에 실패하였습니다."));
    },
    modify() {
      //밴드 정보 수정
    },
    banddetail() {
      //밴드상세 페이지로 이동
      this.$router.push("/band/detail/" + this.$route.params.bandno);
    },

    removeMember() {
      //밴드 멤버 탈퇴
    },
    changechief() {
      //밴드장 위임
    },
    removeCrew() {
      //밴드해체
      axios
        .delete("/band/" + this.$route.params.bandno)
        .then((response) => {
          if (response.data.data == "success") {
            alert("삭제성공!");
            this.$router.push("/");
          }
          //console.log(response.data.object.name)
        })
        .catch((exp) => alert(exp + "밴드삭제에 실패하였습니다."));
    },
  },
};
</script>

<style>
</style>