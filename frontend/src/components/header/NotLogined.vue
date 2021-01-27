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
    links: ["로그인", "회원가입"],
  }),
};
</script>

<style></style>
