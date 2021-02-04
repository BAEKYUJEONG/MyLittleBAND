<template>
  <v-main>
    <v-row class="mt-15">
      <v-spacer></v-spacer>
      <v-col>
        <!-- 회원 가입 fields -->
        <v-form ref="form" v-model="valid" lazy-validation>
          <v-text-field
            v-model="email"
            :counter="50"
            :rules="[
              (v) => !!v || '이메일을 입력하세요',
              (v) =>
                /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i.test(
                  v
                ) || '이메일 형식이 아닙니다',
            ]"
            label="이메일"
            required
          ></v-text-field>

          <v-text-field
            v-model="pw"
            :rules="[
              (v) => !!v || '비밀번호를 입력해 주세요',
              (v) =>
                (v && v.length > 7 && v.length <= 20) ||
                '비밀번호는 8자리 이상 20자리 이하로 입력해야 합니다',
            ]"
            type="password"
            label="비밀번호"
            required
          ></v-text-field>

          <v-text-field
            v-model="pwvaild"
            :rules="[
              (v) => !!v || '비밀번호를 입력해 주세요',
              (v) => v === pw || '입력한 비밀번호와 다릅니다',
            ]"
            type="password"
            label="비밀번호 확인"
            required
          ></v-text-field>

          <v-text-field
            v-model="name"
            :counter="10"
            :rules="[(v) => !!v || '이름을 입력해 주세요']"
            label="이름"
            required
          ></v-text-field>

          <v-text-field
            v-model="phone"
            :counter="13"
            :rules="[
              (v) => !!v || '번호를 입력하세요',
              (v) => /^\d{3}-\d{3,4}-\d{4}$/.test(v) || '번호 형식이 아닙니다',
            ]"
            placeholder="xxx-xxxx-xxxx"
            label="전화번호"
            required
          ></v-text-field>

          <v-btn
            :disabled="!valid"
            color="success"
            class="mr-4"
            @click="onSignup"
          >
            가입하기
          </v-btn>

          <v-btn color="error" class="mr-4" @click="reset">
            모두 지우기
          </v-btn>

          <v-btn color="warning" router-link to="/">
            메인으로
          </v-btn>
        </v-form>
      </v-col>
      <v-spacer></v-spacer>
    </v-row>
  </v-main>
</template>

<script>
import { mapActions } from "vuex";

const MemberStore = "MemberStore";

export default {
  data() {
    return {
      valid: true,
      email: "",
      pw: "",
      pwvaild: "",
      phone: "",
      name: "",
    };
  },
  methods: {
    ...mapActions(MemberStore, ["reqSignup", "reqSignupValidation"]),
    async onSignup() {
      // form 검증
      if(this.$refs.form.validate()===false)
        return;
      
      let result = await this.reqSignup({
        email: this.email,
        pw: this.pw,
        phone: this.phone,
        name: this.name,
      });

      if (result === "success") {
        // TODO: 이메일 인증 요청
        result = this.reqSignupValidation(this.email);

        if(result)  this.$router.push({ name: "validate" });
        else {
          alert("회원가입 오류?? 으아니");

          this.pw = "";
          this.pwvaild = "";
        }
      }
    },
  },
};
</script>

<style></style>
