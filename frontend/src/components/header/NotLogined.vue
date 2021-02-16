<template>
  <v-toolbar-items v-if="mode == 'wide'">
    <Login clr="white--text"/>
    <v-btn text @click="signup()" class="white--text mx-3">
      회원가입
    </v-btn>
  </v-toolbar-items>
  <v-list v-else>
    <v-list-item><Login clr="black--text" /></v-list-item>
    <v-list-item>
      <v-btn text @click="signup()"
        >회원가입</v-btn
      ></v-list-item
    >
  </v-list>
</template>

<script>
import Login from "../login/Login.vue";

export default {
  data: () => ({}),
  props: ["mode"],
  components: {
    Login, //로그인 자체가 버튼이자 컴포넌트가 된다
  },
  methods: {
    signup() {
      //회원가입 페이지로 이동
      this.$router.push("/signup");
    },
  },
};
</script>

<style></style>
