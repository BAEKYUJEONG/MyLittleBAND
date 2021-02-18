<template>
  <v-main>
    <v-container class="mb-10">
      <v-card class="pa-10" color="rgba(255, 255, 255, 0.5)">
      <v-layout column>
        <v-flex>
          <!-- id find layout -->
          <v-layout row fill-height align-center class="ma-5">
            <v-flex col text-center>
              <v-sheet color="rgba(255, 255, 255, 0)"><strong>아이디 찾기</strong></v-sheet>
            </v-flex>
            <v-flex col>
              <v-form ref="id_form" v-model="idvalid" lazy-validation>
                <v-text-field
                  label="이름"
                  placeholder="이름"
                  :counter="10"
                  :rules="[
                    (v) => !!v || '이름을 입력해 주세요',
                    (v) =>
                      (v && v.length > 1 && v.length <= 10) ||
                      '이름은 2자리 이상 10자리 이하로 입력해야 합니다',
                  ]"
                  solo
                  v-model="id.name"
                  required
                ></v-text-field>
                <v-text-field
                  label="전화번호"
                  placeholder="xxx-xxxx-xxxx"
                  :counter="13"
                  :rules="[
                    (v) => !!v || '번호를 입력하세요',
                    (v) =>
                      /^\d{3}-\d{3,4}-\d{4}$/.test(v) || '번호 형식이 아닙니다',
                  ]"
                  solo
                  v-model="id.phone"
                  required
                ></v-text-field>
              </v-form>
            </v-flex>
            <v-flex col align-self-center>
              <!-- ID Modal -->
              <v-dialog v-model="idModal" width="500">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn
                    primary
                    depressed
                    large
                    color="primary"
                    :disabled="!idvalid"
                    :attr="attrs"
                    v-on="on"
                    @click="onFindID()"
                    >아이디 찾기</v-btn
                  >
                </template>
                <!-- Modal content -->
                <v-card>
                  <v-alert
                    border="top"
                    colored-border
                    :type="result ? 'success' : 'warning'"
                  >
                    아이디 찾기
                  </v-alert>
                  <v-card-text>
                    {{ msg }}
                  </v-card-text>

                  <v-divider></v-divider>

                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      :color="result ? 'success' : 'warning'"
                      text
                      @click="idModal = false"
                    >
                      확인
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-flex>
          </v-layout>
        </v-flex>
        <v-flex
          ><v-layout fill-height align-center><v-divider /></v-layout
        ></v-flex>
        <v-flex>
          <!-- password find layout -->
          <v-layout row fill-height align-center class="ma-1">
            <v-flex col text-center>
              <v-sheet color="rgba(255, 255, 255, 0)"><strong>비밀번호 찾기</strong></v-sheet>
            </v-flex>
            <v-flex col>
              <v-form ref="pw_form" v-model="pwvalid" lazy-validation>
                <v-text-field
                  label="이메일"
                  placeholder="이메일"
                  :counter="50"
                  :rules="[
                    (v) => !!v || '이메일을 입력하세요',
                    (v) => /.+@.+\..+/.test(v) || '이메일 형식이 아닙니다',
                    (v) => v.length <= 50 || '이메일이 너무 깁니다',
                  ]"
                  solo
                  v-model="pw.email"
                  required
                ></v-text-field>
                <v-text-field
                  label="이름"
                  placeholder="이름"
                  :counter="10"
                  :rules="[
                    (v) => !!v || '이름을 입력해 주세요',
                    (v) =>
                      (v && v.length > 1 && v.length <= 10) ||
                      '이름은 2자리 이상 10자리 이하로 입력해야 합니다',
                  ]"
                  solo
                  v-model="pw.name"
                  required
                ></v-text-field>
                <v-text-field
                  label="전화번호"
                  placeholder="전화번호"
                  :rules="[
                    (v) => !!v || '번호를 입력하세요',
                    (v) =>
                      /^\d{3}-\d{3,4}-\d{4}$/.test(v) || '번호 형식이 아닙니다',
                  ]"
                  solo
                  v-model="pw.phone"
                  required
                ></v-text-field>
              </v-form>
            </v-flex>
            <v-flex col>
              <!-- PW Modal -->
              <v-dialog v-model="pwModal" width="500">
                <template v-slot:activator="{ on, attrs }">
                  <v-btn primary
                    depressed
                    large
                    color="primary"
                    :disabled="!pwvalid"
                    :attr="attrs"
                    v-on="on"
                    @click="onFindPW()"
                    >비밀번호 찾기</v-btn
                  >
                </template>
                <!-- Modal content -->
                <v-card>
                  <v-alert
                    border="top"
                    colored-border
                    :type="result ? 'success' : 'warning'"
                  >
                    비밀번호 찾기
                  </v-alert>
                  <v-card-text>
                    {{ msg }}
                  </v-card-text>

                  <v-divider></v-divider>

                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      :color="result ? 'success' : 'warning'"
                      text
                      @click="pwModal = false"
                    >
                      확인
                    </v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-flex>
          </v-layout>
        </v-flex>
      </v-layout>
      </v-card>
    </v-container>
  </v-main>
</template>

<script>
import { mapActions } from "vuex";

const MemberStore = "MemberStore";

export default {
  data() {
    return {
      //아이디찾기
      id: {
        name: "",
        phone: "",
      },
      //비밀번호 찾기
      pw: {
        email: "",
        name: "",
        phone: "",
      },
      result: "",
      msg: "",
      // 유효값 검증에 의한 toggle
      idvalid: true,
      pwvalid: true,
      // Modal 활성화
      idModal: false,
      pwModal: false,
    };
  },
  methods: {
    ...mapActions(MemberStore, ["reqFindId", "reqFindPw"]),
    // ID찾기
    onFindID() {
      // 입력값 조건 충족이 안될 때
      if (this.$refs.id_form.validate() === false) return;

      this.reqFindId(this.id)
        .then((response) => {
          this.result = response.result;
          this.msg = response.msg;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // PW찾기
    onFindPW() {
      // 입력값 조건 충족이 안될 때
      if (this.$refs.pw_form.validate() === false) return;

      this.reqFindPw(this.pw)
        .then((response) => {
          this.result = response.result;
          this.msg = response.msg;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style></style>
