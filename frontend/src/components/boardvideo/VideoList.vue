<template>
<v-main>
  <v-layout justify-center>
    <v-flex xs12 sm6>
      <!-- 카드 뷰 시작 -->
      
        <v-container fluid grid-list-md>
          <v-layout row wrap>
            <v-flex
              v-for="card in cards"
              :key="card.title"
              v-bind="{ [`xs${card.flex}`]: true }"
            >
              <v-card class="mx-10 my-10"> 
                <v-img
                  :src="card.src"
                  height="200px"
                  @click="onClick"
                  style="cursor: pointer"
                >
                </v-img>

                <v-card-actions>
                  <v-list>
                    <v-list-item class="grow">
                      <v-list-item-avatar color="grey darken-3">
                        <v-img
                          src="https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light"
                        ></v-img>
                      </v-list-item-avatar>
                      <v-list-item-content>
                        <v-list-item-title
                          v-text="card.title"
                        ></v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>
                  </v-list>
                  <v-spacer></v-spacer>
                  <v-btn icon @click="card.show = !card.show">
                    <v-icon>{{
                      card.show ? "keyboard_arrow_down" : "keyboard_arrow_up"
                    }}</v-icon>
                  </v-btn>
                </v-card-actions>

                <v-slide-y-transition>
                  <v-card-text v-show="card.show" v-text="card.content">
                  </v-card-text>
                </v-slide-y-transition>

              </v-card>
            </v-flex>
          </v-layout>
        </v-container>
     
    </v-flex>
  </v-layout>
</v-main>
</template>

<script>
export default {
  data() {
    return {
      cards: [
        {
          title: "201215 싸피밴드",
          src: require("@/assets/image/1.jpg"),
          flex: 6,
          show: false,
          content: "싸피밴드의 공연입니다",
        },
        {
          title: "191113 써니밴드",
          src: require("@/assets/image/2.jpg"),
          flex: 6,
          show: false,
          content: "써니밴드 공연영상",
        },
        {
          title: "싸피홀 200303",
          src: require("@/assets/image/3.jpg"),
          flex: 6,
          show: false,
          content: "싸피홀에서의 공연",
        },
        {
          title: "200903 전공밴드",
          src: require("@/assets/image/4.jpg"),
          flex: 6,
          show: false,
          content: "전공밴드 공연영상입니다.",
        },
      ],
    };
  },
  methods: {
    onClick() {
      this.$router.push({ name: "videodetail" });
    },
  },
};
</script>

<style scoped>
</style>