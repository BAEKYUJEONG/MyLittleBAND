<template>
  <v-main>
    <v-row class="px-10" justify="center">
      <v-col cols="6">
        <v-banner class="my-10">
          <strong>밴드개설</strong>
        </v-banner>
      </v-col>
    </v-row>
    <v-row class="px-10" justify="center">
      <v-col cols="6">
        <v-text-field
          v-model="band.name"
          :counter="20"
          label="밴드명"
          outlined
          clearable
          :rules="[(v) => v.length <= 20 || '밴드명이 너무 깁니다']"
          required
        >
        </v-text-field>

        <v-file-input
          type="file"
          id="file"
          label="밴드프로필 이미지 (권장사이즈 300px X 300px)"
          v-model="image"
          multiple
        ></v-file-input>

        <v-img v-if="band.img != ''" width="300" height="300"></v-img>
      </v-col>
    </v-row>
    <v-row class="px-10" justify="center">
      <v-col cols="3">
        <v-select
          :items="genre"
          label="밴드장르"
          v-model="band.genre"
          solo
        ></v-select>
      </v-col>
      <v-col cols="3">
        <v-select
          :items="color"
          v-model="band.color"
          label="밴드색깔"
          solo
        ></v-select>
      </v-col>
    </v-row>
    <v-row class="px-10" justify="center">
      <v-col cols="6">
        <v-textarea
          v-model="band.intro"
          label="밴드소개"
          outlined
          clearable
          required
          :counter="200"
          :rules="[(v) => v.length <= 200 || '밴드소개가 너무 깁니다']"
        ></v-textarea>
      </v-col>
    </v-row>

    <v-row class="ma-auto">
      <v-col cols="12" class="ma-auto">
        <v-btn color="primary" class="mx-6" @click="create()">밴드개설</v-btn>

        <v-btn color="error" class="mx-6" @click="bandlist()">돌아가기</v-btn>
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
      image: null,
      band: {
        name: "",
        img: "",
        intro: "",
        color: "",
        genre: "",
      },
      color:['하양','빨강','주황','노랑','초록','파랑','남색','보라','검정'],
      genre:['팝','락','재즈']
    };
  },
  computed: {
    ...mapGetters(MemberStore, {
      //MemberStore 모듈 내 getters 사용
      memberid: "getMemberId", //memberid 변수에 getMemberId 리턴값 저장
    }),
  },
  methods: {
    show() {
      console.log(this.img);
    },
    async create() {
      //공백이 존재하면 경고
      if (this.band.name == "" || this.band.intro == "") {
        alert("공백이 존재합니다!");
        return;
      }
      let frm = new FormData(); //정보 전달을 위해 formdata 생성
      await frm.append("file", this.image); //이미지 정보전달

      await axiosCommon
      .post("/upload/band/"+this.$route.params.bandno,frm,{
        headers: { "Content-Type": "multipart/form-data" },
      })
      .then(() => {
          console.log("이미지 변경 완료");
          //밴드 정보 수정
          axiosCommon
        .post("/band/" + this.memberid, {
          name: this.band.name,
          intro: this.band.intro,
          genre : this.band.genre,
          color : this.band.color
        })
        .then((response) => {
          if (response.data.status) {
            //성공 시 알려주고 push
            alert("밴드 개설이 완료되었습니다!");
            this.$router.push("/band/list/" + this.memberid);
          } else {
            alert("이미 존재하는 밴드명입니다!");
            console.log(response.data);
          }
        })
        .catch((exp) => alert(exp + "밴드를 생성할 수 없습니다."));
        })
        .catch((e) => console.log(e));

      
    },
    bandlist() {
      //밴드리스트 페이지로 이동
      this.$router.push("/band/list/" + this.memberid);
    },
  },
};
</script>

<style>
</style>