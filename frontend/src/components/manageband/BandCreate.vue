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
        <v-text-field v-model="band.name" label="밴드명" outlined clearable>
        </v-text-field>

        <v-file-input
          type="file"
          label="밴드프로필 이미지(추후 추가예정)"
          ref="files"
          multiple
          disabled
        ></v-file-input>



        <v-textarea
          v-model="band.intro"
          label="밴드소개"
          outlined
          clearable
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
import axiosCommon from '../../axios/axios-common';
const MemberStore = "MemberStore"; //MemberStore 모듈 사용

export default {
  data: () => {
    return {
        img : null,
      band: {
        name: "",
        img: "",
        intro: "",
      },
      images: {
        type: 'profile',
        file: [],
      },
      ////
      files: [], //업로드용 파일
      filesPreview: [],
      uploadImageIndex: 0, // 이미지 업로드를 위한 변수
    };
  },
  computed: {
    ...mapGetters(MemberStore, { 
      //MemberStore 모듈 내 getters 사용
      memberid : "getMemberId", //memberid 변수에 getMemberId 리턴값 저장
    }),
  },
  methods: {
  
    show(){
      console.log(this.img);
    },
    create(){
        //공백이 존재하면 경고
        if(this.band.name == ""  || this.band.intro == ""){
            alert("공백이 존재합니다!");
            return;
        }

        axiosCommon
        .post("/band/"+this.memberid,{
            img : this.band.img,
            intro : this.band.intro,
            name : this.band.name
        })
        .then((response)=>{
            if(response.data.status){//성공 시 알려주고 push
                alert("밴드 개설이 완료되었습니다!");
                this.$router.push("/band/list/"+this.memberid);
            }else{
                alert("이미 존재하는 밴드명입니다!")
                console.log(response.data)
            }
        })
        .catch((exp)=>(alert(exp+"밴드를 생성할 수 없습니다.")));
    },
    bandlist(){//밴드리스트 페이지로 이동
        this.$router.push("/band/list/"+this.memberid);
    }
  },
};
</script>

<style>
</style>