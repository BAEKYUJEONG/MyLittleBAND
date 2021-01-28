<template>
  <v-form>
    <v-container>
      <h1 style="margin: 10px auto">밴드게시물 수정</h1>
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
            height="200"
            style="margin: 20px auto"
            v-model="board.content"
          ></v-textarea>
        </v-flex>
      </v-layout>

      <v-layout>
        <v-flex>
          <v-btn color="blue" style="margin: 10px" @click="modify()"
            >수정완료</v-btn
          >
          <v-btn color="blue" style="margin: 10px" @click="detail()"
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
  created() {
    //현재 공지사항 번호의 내용 표출
    //백엔드 연동 후 아래 주석 삭제
    //this.show();

    //현재 게시물의 밴드 번호 (id)
    console.log("밴드번호 : " + this.$route.params.bandnum); 
    //현재 게시물의 게시물 번호 (id)
    console.log("게시물번호 : " + this.$route.params.boardnum); 
    
  },
  methods: {
    detail() {
      //게시글 상세내역으로 이동
      this.$router.push("/boardbanddetail/" + this.$route.params.bandnum + "/" + this.$route.params.boardnum);
    },
    modify() {
      //공지사항 내용 수정

      //공백이 존재하면 경고
      if (this.board.title == "" || this.board.content == "") {
        alert("공백이 존재합니다.");
        return;
      }

      axios
        .put("/board/band", {
          bandid : this.$route.params.bandnum,
          title : this.board.title,
          content : this.board.content
        })
        .then(() => {
          alert("수정 성공");
          this.detail(); //Detail 페이지로 이동
        })
        .catch((exp) => alert("수정에 실패하였습니다." + exp));
    },
    show() {
      //현재 번호의 공지사항 정보를 불러옴
      axios
        .post("/board/band/" + this.$route.params.boardnum, {
          id: this.$route.params.bandnum,
        })
        .then((response) => (this.board = response.data)) // 성공하면 board에 정보 입력
        .catch((exp) => alert("조회에 실패하였습니다." + exp));
    },
  },
  data() {
    return {
      board: {
        //Backend와 연동 후 안의 내용 삭제가능
        title: "3번째 제목",
        content: "내용입니다.",
      },
    };
  },
};
</script>

<style>
</style>