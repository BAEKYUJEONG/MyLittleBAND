<template>
  <v-container>
    Timeline Component
    <v-chip router-link :to="links[0].path">{{ links[0].text }}</v-chip>
  </v-container>
</template>

<script>
/*
$(function() {
  var inputs = $(".input");
  var paras = $(".description-flex-container").find("p");
  inputs.click(function() {
    var t = $(this),
      ind = t.index(),
      matchedPara = paras.eq(ind);

    t.add(matchedPara).addClass("active");
    inputs
      .not(t)
      .add(paras.not(matchedPara))
      .removeClass("active");
  });
});
*/

export default {
  data() {
    return {
      links: [
        {
          text: "전체보기",
          path: "/timetable",
        },
      ],
    };
  },
  methods: {},
};
</script>

<style></style>
