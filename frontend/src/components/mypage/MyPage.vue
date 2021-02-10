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
            <v-slide-item v-for="band in bandlist" :key="band.bandId">
              <v-btn
                @click.native="toBand(band.bandId)"
                class="ma-auto"
                style="width: 180px"
                >{{ band.name }}
                <v-icon v-if="band.isChief == 1" color="#FFD600">mdi-crown</v-icon>
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
import axios from "../../axios/axios-common"
import { mapActions } from "vuex"; //vuex사용
const MemberStore = "MemberStore"; //MemberStore 모듈 사용

export default {
  created () {
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
        .then((response) => 
        { if(response.data.data == "success")
          this.member = response.data.object;
          
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
          if (response.data.status) {//성공하면 alert 후 메인페이지로
            alert("탈퇴성공");
            this.Logout();
            this.$router.push({name:'main'});
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
      this.$refs.imageInput.click();//imageInput이름을 가진 refs를 찾아서 click
    },
    async onChangeImages(e) {
      //변화가 감지되었을 경우
      const file = e.target.files[0]; //해당 event가 호출된 파일
      this.member.imgdata = file;//이미지데이터에 file 기입
      //console.log(e.target.files[0]);
      await this.encodeImage(file);//base64url로 인코딩
      //DB에 저장
      await this.imgmodify();//이미지 DB에 수정
    },
    encodeImage(input) {//이미지 인코딩
      if (input) {
        const reader = new FileReader(); //reader를 이용해서 읽어줘야함
        reader.onload = (e) => {
          this.image = e.target.result; //결과값을 url에 저장
          //console.log(this.base64Img);
        };
        reader.readAsDataURL(input);
      }
    },
    imgmodify() {
      let formData = new FormData(); //정보 전달을 위해 formdata 생성
      formData.append("file", this.member.imgdata);//이미지 정보전달
      //formData.append("baseurl", this.member.imgurl);//이미지 base64url이 전송될 부분
      //console.log(formData.get("image"));

      axios
        .post("/upload" , formData, { headers: { 'Content-Type': 'multipart/form-data' }})
        .then(() => {
            alert("수정성공!");
            this.$router.push("/member/" + this.$route.params.memberno);
            this.member.imgurl = this.image;
        })
        .catch((exp) => alert(exp + "수정에 실패하였습니다."));
    },
    toBand(bandid) {
      this.$router.push("/band/detail/" + bandid);
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
        imgdata: null
      },
      currentOffset: 0, //현재 밴드 위치
      windowSize: 3, //한번에 나오는 밴드 개수
      paginationFactor: 210, //좌우로 움직이는 정도
      bandlist: [
        {
          bandId: "01",
          name: "잘한다밴드",
          isChief: 0,
        },
        {
          bandId: "02",
          name: "더잘한다밴드",
          isChief: 1,
        },
        {
          bandId: "03",
          name: "못한다밴드",
          isChief: 0,
        },
        {
          bandId: "04",
          name: "조금못한다밴드",
          isChief: 1,
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