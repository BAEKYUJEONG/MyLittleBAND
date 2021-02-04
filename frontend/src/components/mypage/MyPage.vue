<template>
  <v-main>
    <v-row class="ma-auto" justify="center">
      <!-- 유저 프로필 이미지 -->
      <v-col cols="4">
        <v-spacer></v-spacer>
        <v-hover v-slot="{ hover }" class="ma-auto">
          <v-card
            max-height="300"
            max-width="300"
            id="rounded-card"
            :elevation="hover ? 8 : 1"
            :class="{ 'on-hover': !hover }"
          >
            <v-container>
              <v-layout column align-center justify-center>
                <v-img
                  class="ma-auto mp-4"
                  max-height="100%"
                  max-width="100%"
                  style="border-radius: 50%"
                  contain
                  :src="member.imgurl"
                >
                  <input
                    ref="imageInput"
                    type="file"
                    hidden
                    @change="onChangeImages"
                  />

                  <v-card-text class="justify-center">
                    <v-btn v-if="hover" @click="onClickImageUpload"
                      >Edit
                    </v-btn>
                  </v-card-text>
                </v-img>
              </v-layout>
            </v-container>
          </v-card>
        </v-hover>
      </v-col>

      <!-- 유저 정보 -->
      <v-col cols="4">
        <v-text-field
          v-model="member.email"
          label="E-mail"
          outlined
          readonly
        ></v-text-field>

        <v-text-field
          v-model="member.name"
          label="이름"
          outlined
          readonly
        ></v-text-field>

        <v-text-field
          v-model="member.phone"
          label="휴대폰"
          outlined
          readonly
        ></v-text-field>

        <v-textarea
          v-model="member.profile"
          label="소개"
          outlined
          readonly
        ></v-textarea>
      </v-col>
      <v-col cols="2">
        <v-spacer></v-spacer>
      </v-col>
    </v-row>
    <hr />
    <!-- 소속밴드 -->

    <v-row class="ma-auto" justify="center">
      <v-col cols="6" class="card-carousel-wrapper">
        <v-banner class="my-10">
        <strong>소속밴드</strong>
      </v-banner>

        <v-sheet class="mx-auto" elevation="8">
          <v-slide-group class="pa-4" show-arrows>
            <v-slide-item v-for="band in bandlist" :key="band.id">
              <v-btn
                @click.native="toBand(band.id)"
                class="ma-auto"
                style="width: 180px"
                >{{ band.name }}
                <v-icon v-if="band.isChief" color="#FFD600">mdi-crown</v-icon>
              </v-btn>
            </v-slide-item>
          </v-slide-group>
        </v-sheet>
      </v-col>
    </v-row>

    <!-- 회원정보 수정 버튼 -->
    <v-row class="ma-auto">
      <v-col cols="12" class="ma-auto">
        <v-btn color="primary" class="mx-6" @click="modify()"
          >회원정보수정</v-btn
        >
          <!-- 회원탈퇴 모달창 -->
        <v-dialog v-model="dialog" persistent max-width="290">
          <template v-slot:activator="{ on, attrs }">
            <v-btn color="error" v-bind="attrs" v-on="on" class="white--text mx-10">
              회원탈퇴
            </v-btn>
          </template>
          <v-card style="opacity: 1.0;">
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
import axios from "axios";
export default {
  created: () => {
    //회원정보를 받아옴
    //백엔드 연동 후 아래 주석 해제
    //this.getmember();
    //밴드리스트 받아옴
    //백엔드 연동 후 아래 주석 해제
    //this.getbandlist();
  },
  methods: {
    onClick() {},
    getmember() {
      axios
        .get("/member/" + this.$route.params.memberno)
        .then((response) => (this.member = response.data))
        .catch((exp) => alert(exp + "조회에 실패하였습니다."));
    },
    getbandlist() {
      //소속된 밴드리스트 조회
      axios
        .get("/band-list/" + this.$route.params.memberno)
        .then((response) => (this.bandlist = response.data))
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
          if (response.data == true) {//성공하면 alert 후 메인페이지로
            alert("삭제성공");
            this.$router.push("/main");
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
      this.$refs.imageInput.click();
    },
    onChangeImages(e) {
      //변화가 감지되었을 경우
      const file = e.target.files[0];
      console.log(e.target.files[0]);
      this.member.imgurl = URL.createObjectURL(file); //이미지 url을 새로운 url로 생성하여 변경
      alert("프로필사진이 변경되었습니다!");
      //DB에 저장
      //백엔드 연동 후 아래 주석 해제
      //this.imgmodify();
    },
    imgmodify() {
      axios
        .put("/member/" + this.$route.params.memberno, { member: this.member })
        .then((response) => {
          if (response.data == "success") {
            //성공하면 알림 후 새로고침
            alert("수정성공!");
            this.$router.push("/member/" + this.$route.params.memberno);
          } else {
            //실패하면 알림 후 수정페이지 다시 불러옴
            alert("수정실패!");
            this.getmember();
          }
        })
        .catch((exp) => alert(exp + "수정에 실패하였습니다."));
    },
    toBand(bandid) {
      this.$router.push("/band/" + bandid);
    },
  },
  computed: {
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
      member: {
        email: "mylittle@band.com",
        name: "홍길동",
        phone: "010-1234-5678",
        profile:
          "제 소개로 말할 것 같으면 \n99년 LA에 있었을 당시였습니다. \n당시 저는 메이저리그에서",
        imgurl: require("@/assets/image/pepe.jpg"),
      },
      currentOffset: 0, //현재 밴드 위치
      windowSize: 3, //한번에 나오는 밴드 개수
      paginationFactor: 210, //좌우로 움직이는 정도
      bandlist: [
        {
          id: "01",
          name: "잘한다밴드",
          isChief: false,
        },
        {
          id: "02",
          name: "더잘한다밴드",
          isChief: true,
        },
        {
          id: "03",
          name: "못한다밴드",
          isChief: false,
        },
        {
          id: "04",
          name: "조금못한다밴드",
          isChief: true,
        },
      ],
    };
  },
};
</script>

<style scoped>
.v-card {
  transition: opacity 0.4s ease-in-out;
}

.v-card:not(.on-hover) {
  opacity: 0.8;
}

#rounded-card {
  border-radius: 50%;
  min-height: 300px;
  min-width: 300px;
}
</style>