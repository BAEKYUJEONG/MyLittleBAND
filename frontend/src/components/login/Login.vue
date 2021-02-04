<template >
  <v-dialog
    v-model="dialog"
    persistent
    max-width="600px"
    style="background-color: #feeeb0"
  >
    <template v-slot:activator="{ on, attrs }">
      <v-btn text v-bind="attrs" v-on="on" class="white--text mx-10"> 로그인 </v-btn>
    </template>
    <v-card>
      <v-card-title>
        <span class="headline">로그인</span>
        <v-spacer />
        <v-btn icon @click="dialog = false">
          <v-icon>mdi-close</v-icon>
        </v-btn>
      </v-card-title>
      <v-card-text>
        <v-container>
          <v-row>
            <v-col cols="12">
              <v-text-field
                label="Email*"
                placeholder="mylittle@band.com"
                v-model="member.email"
                required
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-text-field
                label="Password*"
                type="password"
                v-model="member.pw"
                required
                @keypress.enter="onSubmit"
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-btn color="blue" @click="onSubmit">로그인</v-btn>
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="blue darken-1" text @click="signup()"> 회원가입 </v-btn>
        <v-btn color="blue darken-1" text @click="findidpw()">
          아이디/비밀번호 찾기
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
//import { Login } from "../../store/modules/MemberStore";
import { mapActions, mapGetters } from 'vuex' //vuex사용
const MemberStore = 'MemberStore' //MemberStore 모듈 사용

export default {
  data: () => {
    return {
      dialog: false, //false면 모달창 종료, true:켜기
      member: {
        email: "",
        pw: "",
      },
    };
  },
  computed:{
    ...mapGetters(MemberStore,{//MemberStore 모듈 내 getters 사용
      memberid : 'getMemberId',//memberid 변수에 getMemberId 리턴값 저장
      manager : 'getManager'    //manager 변수에 getManager 리턴값 저장
    })
  },
  methods: {
    ...mapActions(MemberStore,[//MemberStore 모듈 내 actions 사용
        'Login'
      ]),

    onSubmit: function() {
      //공백이면
      if (this.member.email == "" || this.member.pw == "") {
        alert("공백이 존재합니다!");
        return;
      }
      this.Login(this.member); //Login action 사용 (member를 payload로 보냄)
      if(localStorage.getItem("access-token") != ""){
        console.log("회원번호: " + this.memberid);
            this.$router.push("/");
            this.dialog = false;
      }else{
        alert("아이디 혹은 비밀번호가 틀렸습니다!");
      }
    },
    signup() {
      //모달창 끄기
      this.dialog = false;
      //회원가입창으로 이동
      this.$router.push("/signup");
    },
    findidpw() {
      //모달창 끄기
      this.dialog = false;
      //아이디 비밀번호 찾기로 이동
      this.$router.push("/findidpw");
    },
  },
};
</script>

<style>
</style>