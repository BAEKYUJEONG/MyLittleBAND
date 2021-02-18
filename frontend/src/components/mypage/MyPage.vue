<template>
  <v-main>
    <v-container class="mb-10">
      <v-card class="pa-10" color="rgba(255, 255, 255, 0.5)">
    <v-row class="ma-auto" justify="center">
      <!-- 유저 프로필 이미지 -->
      <v-col cols="4">
        <v-spacer></v-spacer>
        <v-tooltip left>
          <template v-slot:activator="{ on, attrs }">
            <v-hover v-slot="{ hover }" class="ma-auto">
              <v-card
                v-bind="attrs"
                v-on="on"
                height="300"
                width="300"
                id="rounded-card" 
                :elevation="hover ? 8 : 1"
                :class="{ 'on-hover': !hover }"
              >
              <!-- 프로필 이미지가 없을 때 -->
                <v-img
                  v-if="member.img == null || member.img == ''"
                  class="ma-auto mp-4 align-center"
                  height="300"
                  width="300"
                  style="border-radius: 50%"
                  src="https://i4a408.p.ssafy.io/profile/LogoMini.png"
                >
                  <input
                    ref="imageInput"
                    accept="image/*"
                    type="file"
                    hidden
                    @change="onChangeImages"
                  />
                  <v-card-text>
                    <v-btn v-if="hover" @click="onClickImageUpload"
                      >Edit
                    </v-btn>
                  </v-card-text>
                </v-img>

                <!-- 프로필 이미지가 있을 때 -->
                <v-img
                  v-else
                  class="ma-auto mp-4 align-center "
                  height="300"
                  width="300"
                  style="border-radius: 50%"
                  :src="member.img"
                >
                  <input
                    ref="imageInput"
                    accept="image/*"
                    type="file"
                    hidden
                    @change="onChangeImages"
                  />
                  <v-card-text class="justify-center" >
                    <v-btn v-if="hover" @click="onClickImageUpload"
                      >Edit
                    </v-btn>
                  </v-card-text>
                </v-img>
              </v-card>
            </v-hover>
          </template>
          <span>
            <div>300 X 300 px</div>
            <div>사이즈로 넣어주세요</div>
          </span>
        </v-tooltip>
      </v-col>

      <!-- 유저 정보 -->
      <v-col cols="4">
        <v-text-field
          v-model="member.email"
          label="E-mail"
          prepend-inner-icon="mdi-email"
          solo
          readonly
        ></v-text-field>

        <v-text-field
          v-model="member.name"
          label="이름"
            prepend-inner-icon="mdi-account-circle"
              solo
          readonly
        ></v-text-field>

        <v-text-field
          v-model="member.phone"
          label="휴대폰"
           prepend-inner-icon="mdi-phone"
              solo
          readonly
        ></v-text-field>

        <v-textarea
          v-model="member.profile"
          label="소개"
           prepend-inner-icon="mdi-message-reply-text"
              solo
          readonly
        ></v-textarea>
      </v-col>
      <v-col cols="2">
        <v-spacer></v-spacer>
      </v-col>
    </v-row>
      </v-card>
    </v-container>
    <v-divider/>


    <v-container class="mb-10">
      <v-card class="pa-10" color="rgba(255, 255, 255, 0.5)">
    <!-- 소속밴드 -->
    <v-row justify="center">
      <v-col cols="6" class="card-carousel-wrapper">
        <v-banner class="mb-10">
          <strong>소속밴드</strong>
        </v-banner>

        <v-sheet class="mx-auto" elevation="8">
          <v-slide-group class="pa-4" show-arrows>
            <v-slide-item v-for="band in bandlist" :key="band.bandId">
              <v-btn
                @click.native="toBand(band.bandId)"
                class="ma-auto"
                style="width: 180px"
                >{{ band.name }}
                <v-icon v-if="band.isChief == 1" color="#FFD600"
                  >mdi-crown</v-icon
                >
              </v-btn>
            </v-slide-item>
          </v-slide-group>
        </v-sheet>
      </v-col>
    </v-row>
      </v-card>
    </v-container>

    <!-- 회원정보 수정 버튼 -->
    <v-row class="ma-auto mb-10" justify="center">
      <v-col cols="auto" class="ma-auto">
        <v-btn color="primary" class="mx-6 " @click="modify()"
          >회원정보수정</v-btn
        >
        <!-- 회원탈퇴 모달창 -->
        <v-dialog v-model="dialog" persistent max-width="290">
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              color="error"
              v-bind="attrs"
              v-on="on"
              class="white--text mx-10"
            >
              회원탈퇴
            </v-btn>
          </template>
          <v-card style="opacity: 1">
            <v-card-title class="headline"> 회원탈퇴 </v-card-title>
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
import axios from "../../axios/axios-common";
import { mapActions, mapGetters } from "vuex"; //vuex사용
const MemberStore = "MemberStore"; //MemberStore 모듈 사용
export default {
  created() {
    //회원정보를 받아옴
    //백엔드 연동 후 아래 주석 해제
    this.getmember();
    //밴드리스트 받아옴
    //백엔드 연동 후 아래 주석 해제
    this.getbandlist();
  },

  methods: {
    ...mapActions(MemberStore, [
      //MemberStore 모듈 내 actions 사용
      "Logout",
    ]),
    getmember() {
      axios
        .get("/member/" + this.$route.params.memberno)
        .then((response) => {
          if (response.data.data == "success")
            this.member = response.data.object;
          console.log(this.member.img);
        })
        .catch((exp) => alert(exp + "조회에 실패하였습니다."));
    },
    getbandlist() {
      //소속된 밴드리스트 조회
      axios
        .get("/band-list/" + this.$route.params.memberno)
        .then((response) => (this.bandlist = response.data.object))
        .catch((exp) => alert(exp + "조회에 실패하였습니다."));
    },

    modify() {
      //회원정보 수정으로 이동
      this.$router.push("/member/modify/" + this.$route.params.memberno);
    },
    remove() {
      //회원탈퇴
      axios
        .delete("/member/" + this.$route.params.memberno)
        .then((response) => {
          if (response.data.status) {
            //성공하면 alert 후 메인페이지로
            alert("탈퇴성공");
            this.Logout();
            this.$router.push({ name: "main" });
          }
        })
        .catch((exp) => alert(exp + "삭제에 실패하였습니다."));
      this.dialog = false;
    },
    moveCarousel(direction) {
      //밴드리스트를 좌우로 움직여줌
      // Find a more elegant way to express the :style. consider using props to make it truly generic
      if (direction === 1 && !this.atEndOfList) {
        //1이면 왼쪽으로 이동
        this.currentOffset -= this.paginationFactor;
      } else if (direction === -1 && !this.atHeadOfList) {
        //-1이면 오른쪽으로 이동
        this.currentOffset += this.paginationFactor;
      }
    },
    onClickImageUpload() {
      //이미지 업로드 버튼 클릭시
      this.$refs.imageInput.click(); //imageInput이름을 가진 refs를 찾아서 click
    },
    async onChangeImages(e) {
      //변화가 감지되었을 경우
      const file = e.target.files[0]; //해당 event가 호출된 파일
      this.member.imgdata = file; //이미지데이터에 file 기입
      this.image = window.URL.createObjectURL(file);
      await this.imgmodify(); //이미지 DB에 수정
    },
    imgmodify() {
      let formData = new FormData(); //정보 전달을 위해 formdata 생성
      formData.append("file", this.member.imgdata); //이미지 정보전달

      axios
        .post("/upload/member/" + this.$route.params.memberno, formData, {
          headers: { "Content-Type": "multipart/form-data" },
        })
        .then(() => {
          alert("수정성공!");
          this.getmember();
          this.member.img = this.image;
        })
        .catch((exp) => alert(exp + "수정에 실패하였습니다."));
    },
    toBand(bandid) {
      this.$router.push("/band/detail/" + bandid);
    },
  },
  computed: {
    ...mapGetters(MemberStore, [{ member: "getMemberInfo" }]),
    atEndOfList() {
      //밴드리스트의 끝을 반환하는 메소드
      return (
        this.currentOffset <=
        this.paginationFactor * -1 * (this.bandlist.length - this.windowSize)
      );
    },
    atHeadOfList() {
      //밴드리스트의 처음을 반환하는 메소드
      return this.currentOffset === 0;
    },
  },
  data() {
    return {
      image: null,
      dialog: false,
      member: {},
      currentOffset: 0, //현재 밴드 위치
      windowSize: 3, //한번에 나오는 밴드 개수
      paginationFactor: 210, //좌우로 움직이는 정도
      bandlist: [],
    };
  },
};
</script>

<style scoped>
#rounded-card {
  transition: opacity 0.4s ease-in-out;
  border-radius: 50%;
  min-height: 300px;
  min-width: 300px;
}

#rounded-card:not(.on-hover){
  opacity: 0.8;
}
</style>