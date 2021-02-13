<template>
  <v-main align="center">
    <v-row justify="center" aligh="center" class="ma-10">
      <v-col cols="4">
        <v-alert outlined text border="left" type="success">
          <div class="title">회원가입이 완료되었습니다.</div>
          <div>이메일 인증 후 로그인이 가능합니다.</div>
          <div class="mt-10"><v-btn rounded @click="home()" color="warning">홈으로</v-btn></div>
        </v-alert>
      </v-col>
    </v-row>
  </v-main>
</template>

<script>
export default {
  methods:{
    home(){
      this.$router.push("/");
    }
  }
};
</script>

<style>
</style>