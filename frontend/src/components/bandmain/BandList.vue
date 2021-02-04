<!-- 밴드 목록 보여 줄 페이지 -->
<template>
  <v-main>
      <v-row justify="center" class="ma-auto">
        <v-col cols = "4">
          <v-btn color="primary" @click="myband()" class="ma-10">내 소속 밴드로 이동</v-btn>

          <v-btn  color="error" @click="otherband()"  class="ma-10">다른 밴드 구경하기</v-btn>
        </v-col>
      </v-row>
  </v-main>
</template>

<script>
export default {
  methods:{
    myband(){
      this.$router.push("/band/detail/12");
    },
    otherband(){
      this.$router.push("/band/introduce/12");
    }
  }
}
</script>

<style>

</style>