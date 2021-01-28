<template>
  <v-app id="inspire">
    <h1 style="margin: 10px auto">공지사항</h1>
    <v-data-table
      :headers="headers"
      :items="items"
      item-key="num"
      dark
      disable-sort
      style="text-align: center; margin: 20px"
      @click:row="detail"
    >
    </v-data-table>
    <v-layout>
      <v-flex>
        <v-btn color="blue" @click="write()">글쓰기</v-btn>
      </v-flex>
    </v-layout>
  </v-app>
</template>

<script>
export default {
  methods: {
    detail(row) {
      //디테일 페이지로 라우터 이동 기능
      this.$router.push("/noticedetail/" + row.num);
    },
    write() {
      //글쓰기 페이지로 라우터 이동 기능
      this.$router.push("/noticecreate");
    },
  },
  data() {
    return {
      headers: [
        //header에 들어갈 목록
        { text: "번호", value: "num", align: "center" },
        { text: "제목", value: "subject", align: "center" },
        { text: "작성일", value: "date", align: "center" },
      ],
      //데이터가 저장 될 목록
      items: [
        {
          value: false,
          num: "1",
          subject: "첫번째 제목입니다.",
          date: "2021.01.01",
        },
        {
          value: false,
          num: "2",
          subject: "두번째 제목입니다.",
          date: "2021.01.03",
        },
        {
          value: false,
          num: "3",
          subject: "세번째 제목입니다.",
          date: "2021.01.05",
        },
        {
          num: "4",
          subject: "네번째 제목입니다.",
          date: "2021.01.07",
        },
        {
          num: "5",
          subject: "다섯번째 제목입니다.",
          date: "2021.01.09",
        },
        {
          num: "6",
          subject: "여섯번째 제목입니다.",
          date: "2021.01.11",
        },
      ],
    };
  },
};
</script>

<style>
</style>