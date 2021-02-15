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
          class="mb-10"
          @change="onChangeImage()"
          v-model="band.imgdata"
          accept="image/*"
          type="file"
          id="file"
          label="밴드 이미지 변경"
        />
        <v-btn class="mb-10" v-if="dialog.imgbtn" @click="submit()"
          >프로필 이미지 변경하기</v-btn
        >

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
      :key="member.memberId"
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
          v-model="member.codeSession"
          :disabled="member.memberId != memberId && isChief == '0'"
        ></v-select>
      </v-col>
      <v-col cols="1">
        <v-btn
          v-if="member.memberId != memberId && isChief == '1'"
          color="error"
          class="white--text mx-4"
          @click="OpenRemoveMember(member.crewId)"
        >
          밴드강퇴
        </v-btn>
        <v-btn
          v-else-if="member.memberId == memberId && isChief == '0'"
          color="error"
          class="white--text mx-4"
          @click="OpenRemoveMember(member.crewId)"
        >
          밴드탈퇴
        </v-btn>
      </v-col>

      <v-col cols="1">
        <v-btn
          v-if="isChief == '1' && member.memberId != memberId"
          color="primary"
          class="white--text mx-4"
          @click="OpenChangeChief(member.crewId)"
        >
          밴드장위임
        </v-btn>
      </v-col>
    </v-row>

    <!-- 밴드강퇴 모달창 -->
    <v-dialog v-model="dialog.Member" persistent max-width="290">
      <v-card style="opacity: 1">
        <v-card-title class="headline"> 밴드강퇴 </v-card-title>
        <v-card-text>정말 강퇴하시겠습니까?</v-card-text>
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

    <!-- 밴드장위임 모달창 -->
    <v-dialog v-model="dialog.Chief" persistent max-width="290">
      <v-card style="opacity: 1">
        <v-card-title class="headline"> 밴드장위임 </v-card-title>
        <v-card-text>정말 위임하시겠습니까?</v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green darken-1" text @click="changechief()"> 예 </v-btn>
          <v-btn color="green darken-1" text @click="dialog.Chief = false">
            아니오
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>

    <!-- 버튼들 -->
    <v-row class="ma-auto">
      <v-col cols="12" class="ma-auto">
        <v-btn color="primary" class="mx-6" @click="modify()">수정하기</v-btn>

        <v-btn class="mx-6" @click="banddetail()">돌아가기</v-btn>
        <!-- 밴드해체 모달창 -->
        <v-dialog
          v-if="isChief == '1'"
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
              <v-btn color="green darken-1" text @click="removeCrew()">
                예
              </v-btn>
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
import { mapGetters } from "vuex"; //vuex사용
import axiosCommon from "../../axios/axios-common";
const MemberStore = "MemberStore"; //MemberStore 모듈 사용

export default {
  data: () => {
    return {
      tempId: "", //강퇴 or 밴드장 위임 할 크루원Id

      dialog: {
        //모달창 관리
        Member: false, //멤버탈퇴
        Chief: false, //밴드장위임
        Crew: false, //밴드해체
        imgbtn: false, //이미지 변경시 버튼
      },
      isChief: "0", //로그인사용자의 밴드장여부
      crewId: "", //로그인사용자의 crewId
      band: {
        imgdata: null, //null로 설정해줘야함
        imgurl: "",
      },
      sessions: ["보컬", "키보드", "드럼", "일렉기타", "베이스"],
      members: [],
    };
  },
  computed: {
    ...mapGetters(MemberStore, {
      //MemberStore 모듈 내 getters 사용
      memberId: "getMemberId", //memberId 변수에 getMemberId 리턴값 저장
    }),
  },
  created() {
    //밴드정보가져오기
    this.getBandinfo();
    this.getMemberinfo(); //밴드소속 멤버정보 가져오기
  },
  methods: {
    onChangeImage() {
      //밴드 이미지 수정
      this.dialog.imgbtn = true; //이미지 저장버튼 생성
    },

    async submit() {
      let formData = new FormData(); //정보 전달을 위해 formdata 생성
      await formData.append("file", this.band.imgdata); //이미지 정보전달

      axiosCommon
        .post("/upload/band/" + this.$route.params.bandno, formData, {
          headers: { "Content-Type": "multipart/form-data" },
        })
        .then((rec) => {
          console.log("suc");
          console.log(rec);
          alert("프로필 이미지가 변경되었습니다!");
        })
        .catch((e) => console.log(e));

      const st2 = "https://i4a408.p.ssafy.io/band/" + this.band.imgdata.name; //주소를 넣어줌.
      let formData2 = new FormData();
      formData2.append("img", st2);
      axiosCommon.put("/band/img/" + this.$route.params.bandno, formData2);
    },

    getBandinfo() {
      //밴드 정보 불러옴
      axiosCommon
        .get("/band/" + this.$route.params.bandno)
        .then((response) => {
          this.band = response.data.object;
          //console.log(response.data.object.name)
        })
        .catch((exp) => alert(exp + "밴드정보 조회에 실패하였습니다."));
    },
    getMemberinfo() {
      //밴드 소속 멤버 정보를 불러옴
      axiosCommon
        .get("/band/member/" + this.$route.params.bandno)
        .then((response) => {
          this.members = response.data.object;
          this.getMember(); //로그인 멤버 정보 가져오기
        })
        .catch((exp) => alert(exp + "소속 멤버 조회에 실패하였습니다."));
    },
    getMember() {
      //밴드장 여부 확인을 위해 불러옴
      for (let i = 0; i < this.members.length; i++) {
        if (this.members[i].isChief == "1")
          if (this.members[i].memberId == this.memberId) {
            this.isChief = "1";
            this.crewId = this.members[i].crewId;
            break;
          }
      }
    },
    modify() {
      if (this.band.name == null || this.band.intro == "") {
        alert("빈칸이 존재합니다!");
        return;
      }
      for (let i = 0; i < this.members.length; i++) {
        let codes = new FormData();
        codes.append("session", this.members[i].codeSession);
        axiosCommon
          .put("/band/member/" + this.members[i].crewId, codes)
          .then((res) => console.log(res))
          .catch((exp) => alert(exp + "멤버 세션 수정에 실패하였습니다."));
      }

      let frm = new FormData();
      frm.append("name", this.band.name);
      frm.append("profile", this.band.intro);
      //밴드 정보 수정
      axiosCommon
        .put("/band/" + this.$route.params.bandno, frm)
        .then((response) => {
          if (response.data.status) {
            alert("수정성공!");
            this.$router.push("/band/detail/" + this.$route.params.bandno);
          }
        })
        .catch((exp) => alert(exp + "밴드 정보 수정에 실패하였습니다."));
    },
    banddetail() {
      //밴드상세 페이지로 이동
      this.$router.push("/band/detail/" + this.$route.params.bandno);
    },

    OpenRemoveMember(val) {
      this.dialog.Member = true;
      this.tempId = val;
    },
    removeMember() {
      //밴드 멤버 탈퇴
      axiosCommon
        .delete("/band/member/" + this.tempId)
        .then((response) => {
          if (response.data.status) {
            alert("강퇴성공!");
            this.getMemberinfo(); //멤버정보 재호출
          }
        })
        .catch((exp) => alert(exp + "밴드삭제에 실패하였습니다."));
      this.dialog.Member = false;
    },
    OpenChangeChief(val) {
      this.dialog.Chief = true;
      this.tempId = val;
    },
    changechief() {
      //밴드장 위임
      let frm = new FormData();
      frm.append("mandatecrewId", this.tempId);
      axiosCommon
        .put("/band/member/chief/" + this.crewId, frm)
        .then((response) => {
          if (response.data.status) {
            alert("밴드장이 변경되었습니다!");
            this.$router.push("/band/detail/" + this.$route.params.bandno);
          }
        })
        .catch((exp) => alert(exp + "밴드장 변경에 실패하였습니다."));
      this.dialog.Chief = false;
    },
    removeCrew() {
      //밴드해체
      axiosCommon
        .delete("/band/" + this.$route.params.bandno)
        .then((response) => {
          if (response.data.data == "success") {
            alert("해체성공!");
            this.$router.push("/band/list/" + this.memberId);
          }
          //console.log(response.data.object.name)
        })
        .catch((exp) => alert(exp + "밴드해체에 실패하였습니다."));
    },
  },
};
</script>

<style>
</style>