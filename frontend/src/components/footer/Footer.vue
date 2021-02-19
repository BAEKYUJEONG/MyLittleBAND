<template>
  <v-footer color="#231E1A" padless>
    <v-row justify="center" no-gutters>
      <v-col cols="auto">
        <v-btn
          v-for="member in Team"
          :key="member.name"
          color="white"
          text
          rounded
          class="my-10 member"
          :href="member.url"
        >
          {{ member.name }}
        </v-btn>
        <v-btn color="blue" text rounded @click="dialog = true">이용약관</v-btn>
      </v-col>
    </v-row>

    <v-row justify-lg="center" no-gutters>
      <v-col class="lighten-2 py-4 text-center white--text" cols="12">
        <strong
          >본 사이트의 콘텐츠는 저작권법의 보호를 받는 바 무단 전재, 복사, 배포
          등을 금합니다.</strong
        >
      </v-col>
    </v-row>
    <v-row justify="center" no-gutters>
      <v-col class="lighten-2 py-4 text-center white--text" cols="12">
        {{ new Date().getFullYear() }} —
        <strong>Copyright Team A408 All rights reserved.</strong>
      </v-col>
    </v-row>

    <!-- 이용약관 모달창 -->
    <v-dialog v-model="dialog" persistent max-width="600">
      <v-expansion-panels tile class="mb-10">
        <v-expansion-panel>
          <v-expansion-panel-header> 나작밴 이용약관 </v-expansion-panel-header>
          <v-expansion-panel-content>
            제1조 (목적)<br /><br />
            본 약관은 나작밴 주식회사(이하 '회사')가 나작밴 사이트(이하
            ‘사이트’)를 통하여 인터넷상에서 제공하는 서비스(이하 ‘서비스’)를
            이용하는 고객 (이하 '고객') 간의 권리와 의무 및 책임 등 기타
            제반사항을 규정함을 목적으로 합니다.<br /><br />

            제2조 (용어의 정의)<br /><br />
            이 약관에서 사용하는 용어의 정의는 다음과 같습니다.<br />
            가. '회원'이라 함은 이 약관에 동의하고 회원가입을 통하여
            이용자ID(고유번호)와 비밀번호를 발급받은 자로서, 회사가 제공하는
            서비스를 이용할 수 있는 이용자를 말합니다.<br />
            나. '이용자ID'라 함은 회원의 식별 및 서비스 이용을 위하여 회원의
            신청에 따라 회사가 회원 별로 부여하는 고유한 문자와 숫자의 조합을
            말합니다.<br />
            다. '비밀번호'라 함은 이용자ID로 식별되는 회원의 본인 여부를
            검증하기 위하여 회원이 설정하여 회사에 등록한 고유의 문자와 숫자의
            조합을 말합니다.<br />
            라. ‘로그인’이라 함은 이용자ID와 비밀번호를 통하여 서비스 신청 및
            사용 중 서비스의 세부정보를 확인할 수 있는 행위를 말합니다.<br />
            마. '탈퇴'라 함은 회원이 서비스 이용을 해지하는 것을 말합니다.<br />
            바. ‘휴면ID’ 라 함은 회사에서 정한 일정 기간 동안 사이트 접속 및 미
            로그인 시 고객의 정보 악용방지를 위하여 보호되는 계정입니다.<br />
            이 약관에서 사용하는 용어 중 제1항에서 정하지 아니한 것은 관계 법령
            및 서비스 별 안내에서 정하는 바에 따르며, 그 외에는 일반 관례에
            따릅니다.<br /><br />

            제3조 (이용약관의 효력 및 개정)<br />
            1. 이 약관은 사이트를 통해 온라인으로 공시하고 회원의 동의로 효력을
            발생합니다. 본 약관의 공시는 사이트에 게시하는 방법으로 합니다.<br />
            2. 회사는 합리적인 사유가 발생할 경우 관련 법령에 위배되지 않는 범위
            내에서 약관을 개정할 수 있습니다. 개정된 약관은 사이트를 통해
            공지하거나 기타의 방법으로 회원에게 공지함으로써 그 효력이 발생하며,
            이용자의 권리 또는 의무에 관한 변경은 최소한 7일 전에 공지합니다.<br />
            3. 변경된 약관에 대한 정보를 알지 못해 발생하는 회원의 피해는
            회사에서 책임지지 않습니다.<br />
            4. 회원은 변경된 약관에 동의하지 않을 경우 서비스 이용을 중단하고
            회원 탈퇴를 요청할 수 있습니다. 단, 이의가 있음에도 불구하고 제
            2항에서 정한 바에 따른 회사의 고지가 있은 후 30일 이내에 이용계약을
            하지 않은 회원은 변경된 약관에 동의한 것으로 간주합니다.<br />
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
      <v-btn class="mt-5" color="error " large @click="dialog = false">
        닫기
      </v-btn>
    </v-dialog>
  </v-footer>
</template>

<script>
export default {
  data: () => {
    return {
      dialog: false,
      Team: [
        {
          name: "김영현",
          url: "https://github.com/KimYH-KU",
        },
        {
          name: "박성우",
          url: "https://github.com/uaio88",
        },
        {
          name: "백유정",
          url: "https://github.com/BAEKYUJEONG",
        },
        {
          name: "신인섭",
          url: "https://github.com/InSub-Shin",
        },
        {
          name: "엄재웅",
          url: "https://github.com/eomjaewoong",
        },
      ],
    };
  },
};
</script>

<style>
.member {
  color: white;
  text-align: center;
}
</style>
