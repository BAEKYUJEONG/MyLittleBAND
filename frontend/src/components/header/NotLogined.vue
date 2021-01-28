<template>
  <v-div>
    <v-btn v-for="link in links" :key="link" text router-link :to="link.path">
      {{ link.text }}
    </v-btn>
  </v-div>
</template>

<script>
export default {
  data: () => ({
    links: [
      {
        text: "로그인",
        path: ""
      }, 
      {
        text: "회원가입",
        path: "/signup"
      }
    ],
  }),
};
</script>

<style></style>
