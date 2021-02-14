<template>
  <v-main align="center">
    <v-row justify="center" aligh="center" class="ma-10">
      <v-col cols="4">
        <v-alert outlined text border="left" type="info">
          <div class="title">가입하신 이메일로 인증메일이 발송되었습니다.</div>
          <div>이메일 인증 후 로그인이 가능합니다.</div>
          <div class="mt-10"><v-btn rounded router-link to="/" color="warning">홈으로</v-btn></div>
        </v-alert>
      </v-col>
    </v-row>
  </v-main>
</template>

<script>
export default {
};
</script>

<style>
</style>