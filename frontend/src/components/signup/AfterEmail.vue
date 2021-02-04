<template>
  <v-main align="center">
    <div v-if="result">
      <p>회원 가입이 완료되었습니다!</p>
      <v-btn text router-link to="/">메인으로</v-btn>
    </div>
    <div v-else>
      <p>이메일 인증에 오류가 발생했습니다</p>
      <v-btn text @click.once="reqSignupValidation(email)">이메일 재인증</v-btn>
    </div>
  </v-main>
</template>

<script>
import { mapActions } from "vuex";

const MemberStore = "MemberStore";

export default {
  data() {
    return {
      result: false,
      email: ""
    };
  },
  created() {
    this.email = this.$route.query.email;
    this.result = this.reqClickEmailLink({email: this.email, authkey: this.$route.query.authkey});
  },
  methods: {
    ...mapActions(MemberStore, ["reqClickEmailLink", "reqSignupValidation"]),
  },
};
</script>

<style></style>
