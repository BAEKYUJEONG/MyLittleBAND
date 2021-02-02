<template>
  <v-footer color="#231E1A" padless>
    <v-row justify="center" no-gutters>
      <v-btn
        v-for="member in Team"
        :key="member.name"
        color="white"
        text
        rounded
        class="my-10 mx-10"
        :href="member.url"
      >
        {{ member.name }}
      </v-btn>
      <v-col class="lighten-2 py-4 text-center white--text" cols="12">
        {{ new Date().getFullYear() }} — <strong>Team A408</strong>
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
          name: "김영현",
          url: "https://github.com/KimYH-KU",
        },
        {
          name: "박성우",
          url: "",
        },
        {
          name: "백유정",
          url: "",
        },
        {
          name: "신인섭",
          url: "",
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
.row,
.col {
  color: white;
  text-align: center;
}
</style>
