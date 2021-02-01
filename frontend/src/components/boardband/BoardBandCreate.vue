<template>
  <v-form>
    <v-container>
      <h1 style="margin: 10px auto">밴드 게시글 작성</h1>
      <v-layout>
        <v-flex><h2 style="margin: 20px auto">제목</h2></v-flex>
        <v-flex>
          <v-text-field solo style="margin: 20px auto" v-model="board.title">
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
            v-model="board.content"
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
      //밴드게시판 목록으로 돌아감
      this.$router.push("/boardbandlist/"+this.$route.params.bandnum);
    },
    create() {
      //밴드게시판 게시글 작성

      //공백이 존재하면 경고
      if (this.board.title == "" || this.board.content == "") {
        alert("공백이 존재합니다.");
        return;
      }

      axios
        .post("/board/band", {
          bandid : this.$route.params.bandnum, //백엔드와 통일할 필요 있음
          title : this.board.title,
          content : this.board.content,
          memberid : "" //로그인 기능 구현 후 추가필요
        })
        .then(() => {
          //성공하면 alert 후 리스트 페이지로
          alert("글쓰기성공!");
          this.$router.push("/boardbandlist/"+this.$route.params.bandnum);
        })
        .catch((exp) => alert("생성에 실패하였습니다." + exp));
    },
  },
  data() {
    return {
      board: {
        title: "",
        content: "",
      },
    };
  },
};
</script>

<style>
</style>