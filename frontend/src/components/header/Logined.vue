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
        text: "로그아웃",
        path: ""
      }, 
      {
        text: "마이페이지",
        path: "/member/2"
      },
      {
        text: "밴드",
        path: "/band/3"
      }
    ],
  }),
};
</script>

<style></style>