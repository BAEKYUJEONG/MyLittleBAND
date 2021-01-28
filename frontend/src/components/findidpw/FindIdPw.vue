<template>
  <div>
    <v-container>
      <v-row>
        <v-col>
          <h1>아이디찾기</h1>
        </v-col>
        <v-col>
          <v-text-field
            label="이름"
            placeholder="홍길동"
            solo
            v-model="id.name"
          ></v-text-field>
          <v-text-field
            label="전화번호"
            placeholder="010-0000-0000"
            solo
            v-model="id.phone"
          ></v-text-field>
        </v-col>
        <v-col>
          <v-btn
            text-color="accent"
            color="#BBDEFB"
            elevation-2
            x-large
            @click="findID()"
            >아이디 찾기</v-btn
          >
        </v-col>
      </v-row>

      <!-- 아이디 확인 모달창 -->
      <div v-show="is_show_id_result">
        <!-- 현재 컬러css 적용안됨 -->
        <v-alert type="success" color="green lighten-5">
          가입하신 아이디는 {{ result }} 입니다.
          <v-btn
            style="margin-left: 10px"
            color="#039BE5"
            @click="handle_toggle_id_result()"
          >
            닫기</v-btn
          >
        </v-alert>
      </div>

      <!-- 아이디 에러 모달창 -->
      <div v-show="is_show_id_error">
        <v-alert type="error" color="red">
          입력하신 내용과 일치하는 회원이 없습니다.
          <v-btn
            style="margin-left: 10px"
            color="#039BE5"
            @click="handle_toggle_id_error()"
          >
            닫기</v-btn
          >
        </v-alert>
      </div>

      <v-row>
        <v-col>
          <h1>비밀번호찾기</h1>
        </v-col>
        <v-col>
          <v-text-field
            label="이메일"
            placeholder="mylittle@band.com"
            solo
            v-model="pw.email"
          ></v-text-field>
          <v-text-field
            label="이름"
            placeholder="홍길동"
            solo
            v-model="pw.name"
          ></v-text-field>
          <v-text-field
            label="전화번호"
            placeholder="010-0000-0000"
            solo
            v-model="pw.phone"
          ></v-text-field>
        </v-col>
        <v-col>
          <v-btn
            text-color="accent"
            color="#BBDEFB"
            elevation-2
            x-large
            @click="sendEmail()"
            >이메일 전송</v-btn
          >
        </v-col>
      </v-row>
      <!-- 비밀번호 확인 모달창 -->
      <div v-show="is_show_pw_result">
        <v-alert type="success" color="green lighten-5">
          이메일이 전송되었습니다.
          <v-btn
            style="margin-left: 10px"
            color="#039BE5"
            @click="handle_toggle_pw_result()"
          >
            닫기</v-btn
          >
        </v-alert>
      </div>

      <!-- 비밀번호 에러 모달창 -->
      <div v-show="is_show_pw_error">

        <v-alert type="error" color="red">
          입력하신 내용과 일치하는 회원이 없습니다.
          <v-btn
            style="margin-left: 10px"
            color="#039BE5"
            @click="handle_toggle_pw_error()"
          >
            닫기</v-btn
          >
        </v-alert>
      </div>
    </v-container>
  </div>
</template>

<script>
import axios from "../../axios/axios-common.js";

export default {
  data() {
    return {
      //아이디찾기
      id: {
        name: "",
        phone: "",
      },
      result: "",
      //모달창 false면 안보임
      is_show_id_result: false,
      is_show_id_error: false,
      is_show_pw_result: false,
      is_show_pw_error: false,
      //비밀번호 찾기
      pw: {
        email: "",
        name2: "",
        phone2: "",
      },
    };
  },
  methods: {
    handle_toggle_id_result: function () {
      //아이디 확인 모달창 키고끄기
      this.is_show_id_result = !this.is_show_id_result;
    },
    handle_toggle_id_error: function () {
      //아이디 에러 모달창 키고끄기
      this.is_show_id_error = !this.is_show_id_error;
    },
    handle_toggle_pw_result: function () {
      //비밀번호 확인 모달창 키고끄기
      this.is_show_pw_result = !this.is_show_pw_result;
    },
    handle_toggle_pw_error: function () {
      //비밀번호 에러 모달창 키고끄기
      this.is_show_pw_error = !this.is_show_pw_error;
    },
    findID() {
      //아이디찾기
      //열려있는 모달창들을 닫아줌
      this.is_show_id_result = false;
      this.is_show_id_error = false;
      this.is_show_pw_result = false;
      this.is_show_pw_error = false;

      //공백이 존재하면 경고
      if (this.id.name == "" || this.id.phone == "") {
        alert("공백이 존재합니다.");
        return;
      }

      //아이디 찾기 통신
      axios
        .post("/findid", this.id)
        .then((response) => {
          this.result = response.data;
          //결과값이 없으면 error 모달창 띄워줌
          if (this.result == "") {
            this.is_show_id_error = true;
            this.id.name = "";
            this.id.phone = "";
          } else {
            //결과값이 존재하면 id확인 모달창 띄워줌
            this.is_show_id_result = true;
            console.log("확인한 이메일은" + this.result + "입니다.");
          }
        })
        .catch((exp) => alert("해당하는 회원이 존재하지 않습니다." + exp));
    },
    sendEmail() {
      //이메일 발송
      //열려있는 모달창 닫아줌
      this.is_show_id_result = false;
      this.is_show_id_error = false;
      this.is_show_pw_result = false;
      this.is_show_pw_error = false;
      //공백이 존재하면 경고
      if (this.pw.name == "" || this.pw.phone == "") {
        alert("공백이 존재합니다.");
        return;
      }
    },
  },
};
</script>

<style>
</style>