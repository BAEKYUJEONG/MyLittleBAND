<template>
  <v-col class="ma-auto">
    <Login/>
    <v-btn text @click="signup()" class="white--text ma-auto">
      회원가입
    </v-btn>
  </v-col>
</template>

<script>
import Login from '../login/Login.vue';

export default {
  data: () => ({
    
  }),
  components:{
    Login//로그인 자체가 버튼이자 컴포넌트가 된다
  },
  methods:{
    signup(){//회원가입 페이지로 이동
    this.$router.push("/signup");
    }
  }
};
</script>

<style></style>
