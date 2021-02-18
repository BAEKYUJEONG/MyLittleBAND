<template>
  <v-footer color="#231E1A" padless>
    <v-row justify="center" no-gutters>
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
      <v-col class="lighten-2 py-4 text-center white--text" cols="12">
        {{ new Date().getFullYear() }} — <strong>Copyright Team A408 All rights reserved.</strong>
      </v-col>
    </v-row>
  </v-footer>
</template>

<script>
export default {
  data: () => {
    return {
      Team: [
        {
          name: '김영현',
          url: 'https://github.com/KimYH-KU',
        },
        {
          name: '박성우',
          url: 'https://github.com/uaio88',
        },
        {
          name: '백유정',
          url: 'https://github.com/BAEKYUJEONG',
        },
        {
          name: '신인섭',
          url: 'https://github.com/InSub-Shin',
        },
        {
          name: '엄재웅',
          url: 'https://github.com/eomjaewoong',
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
