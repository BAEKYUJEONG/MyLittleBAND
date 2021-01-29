<template>
  <v-main>
    <v-row class="mt-15">
      <v-spacer></v-spacer>
      <v-col>
      <v-form ref="form" v-model="valid" lazy-validation>
        <v-text-field
          :counter="50"
          label="이메일"
          required
        ></v-text-field>

        <v-text-field
          type="password"
          label="비밀번호"
          required
        ></v-text-field>

        <v-text-field
          type="password"
          label="비밀번호 확인"
          required
        ></v-text-field>

        <v-text-field
          :counter="10"
          label="이름"
          required
        ></v-text-field>

        <v-btn
          :disabled="!valid"
          color="success"
          class="mr-4"
          @click="validate"
        >
          가입하기
        </v-btn>

        <v-btn color="error" class="mr-4" @click="reset">
          모두 지우기
        </v-btn>

        <v-btn color="warning" router-link to="/" >
          메인으로
        </v-btn>
      </v-form>
      </v-col>
      <v-spacer></v-spacer>
    </v-row>
  </v-main>
</template>

<script>
export default {};
</script>

<style></style>
