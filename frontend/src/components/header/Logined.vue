<template>
  <v-div>
    <v-btn v-for="link in links" :key="link" text>
      {{ link }}
    </v-btn>
  </v-div>
</template>

<script>
export default {
  data: () => ({
    links: ["로그아웃", "마이페이지", "밴드"],
  }),
};
</script>

<style></style>