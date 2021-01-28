<template>
  <v-form>
    <v-container>
      <h1 style="margin: 10px auto">공지사항 작성</h1>
      <v-layout>
        <v-flex><h2 style="margin: 20px auto">제목</h2></v-flex>
        <v-flex>
          <v-text-field solo style="margin: 20px auto" v-model="notice.title">
          </v-text-field>
        </v-flex>
      </v-layout>
      <v-layout>
        <v-flex><h2 style="margin: 20px auto">내용</h2></v-flex>
        <v-flex>
          <v-textarea
            solo
            full-width
            height="200"
            style="margin: 20px auto"
            v-model="notice.content"
          ></v-textarea>
        </v-flex>
      </v-layout>

      <v-layout>
        <v-flex>
          <v-btn color="blue" style="margin: 10px" @click="create()"
            >작성완료</v-btn
          >
          <v-btn color="blue" style="margin: 10px" @click="list()"
            >돌아가기</v-btn
          >
        </v-flex>
      </v-layout>
    </v-container>
  </v-form>
</template>

<script>
import axios from "axios";

export default {
  methods: {
    list() {
      //공지사항 목록으로 돌아감
      this.$router.push("/noticelist");
    },
    create() {
      //공지사항 작성

      //공백이 존재하면 경고
      if (this.notice.title == "" || this.notice.content == "") {
        alert("공백이 존재합니다.");
        return;
      }

      axios
        .post("/notice",{
          notice :  this.notice
          })
        .then(() => {
          //성공하면 alert 후 리스트 페이지로
          alert("삭제성공!");
          this.$router.push("/noticelist");
        })
        .catch((exp) => alert("생성에 실패하였습니다." + exp));
    },
  },
  data() {
    return {
      notice: {
        title: "",
        content: "",
      },
    };
  },
};
</script>

<style>
</style>