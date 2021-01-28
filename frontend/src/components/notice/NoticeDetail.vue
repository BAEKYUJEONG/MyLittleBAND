<template>
  <v-main>
    <v-form>
      <v-container>
        <h1 style="margin: 10px auto">공지사항 상세보기</h1>
        <v-layout>
          <v-flex><h2 style="margin: 20px auto">제목</h2></v-flex>
          <v-flex>
            <v-text-field
              readonly
              solo
              style="margin: 20px auto"
              v-model="notice.title"
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
              v-model="notice.content"
            ></v-textarea>
          </v-flex>
        </v-layout>

        <v-layout>
          <v-flex>
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
  </v-main>
</template>

<script>
import axios from "axios";

export default {
  created() {
    console.log(this.$route.params.seq); //현재 공지사항의 번호 (id)
    //현재 공지사항 번호의 내용 표출
    //this.show();
  },
  methods: {
    list() {
      //공지사항 목록으로 돌아감
      this.$router.push("/notice");
    },
    modify() {
      //공지사항 수정 목록으로 이동
      this.$router.push("/notice/modify/" + this.$route.params.seq);
    },
    remove() {
      axios
        .delete("/notice/" + this.$route.params.seq)
        .then(() => {
          // 성공하면 alert 후 리스트페이지로
          alert("삭제성공!");
          this.$router.push("/notice");
        })
        .catch((exp) => alert("삭제에 실패하였습니다." + exp));
    },
    show() {
      //현재 번호의 공지사항 정보를 불러옴
      axios
        .get("/notice/" + this.$route.params.seq)
        .then((response) => (this.notice = response.data)) // 성공하면 notice에 정보 입력
        .catch((exp) => alert("조회에 실패하였습니다." + exp));
    },
  },
  data() {
    return {
      notice: {
        //Backend와 연동 후 안의 내용 삭제가능
        title: "3번째 제목",
        content: "내용입니다.",
      },
    };
  },
};
</script>

<style></style>
