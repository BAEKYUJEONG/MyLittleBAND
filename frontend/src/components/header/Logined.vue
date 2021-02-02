<template>
  <v-div>
    <v-btn
      v-for="link in links"
      :key="link"
      text
      @click.native="click(link.text)"
      class="white--text mx-4"
    >
      {{ link.text }}
    </v-btn>
  </v-div>
</template>

<script>
import { mapActions, mapGetters } from "vuex"; //vuex사용
const MemberStore = "MemberStore"; //MemberStore 모듈 사용

export default {
  data: () => ({
    links: [
      {
        text: "로그아웃",
      },
      {
        text: "마이페이지",
      },
      {
        text: "밴드",
      },
    ],
  }),
  computed: {
    ...mapGetters(MemberStore, {
      //MemberStore 모듈 내 getters 사용
      memberid: "getMemberId", //memberid 변수에 getMemberId 리턴값 저장
    }),
  },
  methods: {
    ...mapActions(MemberStore, [
      //MemberStore 모듈 내 actions 사용
      "Logout",
    ]),
    click(value) {
      //클릭이벤트로 페이지 이동
      if (value == "로그아웃") {
        this.Logout(); //로그아웃
        this.$router.push("/");
      } else if (value == "마이페이지") {
        //마이페이지로 이동
        this.$router.push("/mypage/" + this.memberid);
      } else if (value == "밴드") {
        //밴드페이지로 이동
        this.$router.push("/band/" + this.memberid);
      }
    },
  },
};
</script>

<style></style>