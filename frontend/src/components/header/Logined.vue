<template>
  <span v-if="mode == 'wide'">
    <v-btn
      v-for="link in links"
      :key="link.text"
      text
      @click.native="click(link.text)"
      class="white--text"
    >
      {{ link.text }}
    </v-btn>
  </span>
  <v-list v-else>
    <v-list-item v-for="link in links" :key="link.text">
      <v-btn text @click.native="click(link.text)">
        {{ link.text }}
      </v-btn>
    </v-list-item>
  </v-list>
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
  props: ["mode"],
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
        this.$router.push({ name: "main" });
      } else if (value == "마이페이지") {
        //마이페이지로 이동
        this.$router.push("/member/" + this.memberid);
      } else if (value == "밴드") {
        //밴드페이지로 이동
        this.$router.push("/band/list/" + this.memberid);
      }
    },
  },
};
</script>

<style></style>
