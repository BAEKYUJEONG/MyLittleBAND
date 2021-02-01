<template>
  <v-form>
    <v-container>
      <h1 style="margin: 10px auto">밴드게시판 상세보기</h1>
      <v-layout>
        <v-flex><h2 style="margin: 20px auto">제목</h2></v-flex>
        <v-flex>
          <v-text-field
            readonly
            solo
            style="margin: 20px auto"
            v-model="board.title"
          >
          </v-text-field>
        </v-flex>
      </v-layout>
      <v-layout>
        <v-flex><h2 style="margin: 20px auto">내용</h2></v-flex>
        <v-flex>
          <v-textarea
            readonly
            solo
            height="200"
            style="margin: 20px auto"
            v-model="board.content"
          ></v-textarea>
        </v-flex>
      </v-layout>

      <v-layout>
        <v-flex>
          <!-- 수정 삭제 부분은 작성자만 볼 수 있게 해야함 -->
          <v-btn color="blue" style="margin: 10px" @click="modify()"
            >수정하기</v-btn
          >
          <v-btn color="blue" style="margin: 10px" @click="remove()"
            >삭제하기</v-btn
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
  created() {
    console.log("밴드번호 : " + this.$route.params.bandnum); //현재 게시물의 밴드 번호 (id)
    console.log("게시물번호 : " + this.$route.params.boardnum); //현재 게시물의 게시물 번호 (id)

    //현재 게시물 번호의 내용 표출
    //백엔드와 연동 후 아래 주석 삭제
    //this.show();
  },
  methods: {
    list() {
      //밴드게시판 목록으로 돌아감
      this.$router.push("/boardbandlist/" + this.$route.params.bandnum);
    },
    modify() {
      //밴드게시물 수정 목록으로 이동
      this.$router.push(
        "/boardbandmodify/" +
          this.$route.params.bandnum +
          "/" +
          this.$route.params.boardnum
      );
    },
    remove() {
      axios
        .delete("/board/band", {
          bandid: this.$route.params.bandnum,
        }) //백엔드와 맞출 필요 있음
        .then(() => {
          // 성공하면 alert 후 리스트페이지로
          alert("삭제성공!");
          this.$router.push("/boardbandlist/" + this.$route.params.bandnum);
        })
        .catch((exp) => alert("삭제에 실패하였습니다." + exp));
    },
    show() {
      //현재 번호의 게시글 정보를 불러옴
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
        writer: "작성자",
        view: "조회수",
      },
    };
  },
};
</script>

<style>
</style>