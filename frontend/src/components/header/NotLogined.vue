<template>
  <span v-if="mode == 'wide'">
    <Login clr="white--text"/>
    <v-btn text @click="signup()" style="font-size : 20px;" class="white--text mx-3">
      회원가입
    </v-btn>
  </span>
  <v-list v-else>
    <v-list-item><Login clr="black--text" /></v-list-item>
    <v-list-item>
      <v-btn text style="font-size : 20px;" @click="signup()"
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

<style scoped>


</style>
