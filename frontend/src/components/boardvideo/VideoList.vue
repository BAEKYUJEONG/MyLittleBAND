<template>
  <div>
    <v-layout justify-center>
      <v-flex xs12 sm6>
        <!-- 카드 뷰 시작 -->
        <v-card>
          <v-container fluid grid-list-md>
            <v-layout row wrap>
              <v-flex
                v-for="card in cards"
                :key="card.title"
                v-bind="{ [`xs${card.flex}`]: true }"
              >
                <v-card>
                  <v-img :src="card.src" height="200px" @click="onClick"> </v-img>

                  <v-card-actions>
                    <v-list two-line>
                      <v-list-tile class="grow">
                        <v-list-item-avatar color="grey darken-3">
                          <v-img      
                            src="https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light"
                          ></v-img>
                        </v-list-item-avatar>

                        <v-list-tile-content>
                          <v-list-tile-title
                            v-text="card.title"
                          ></v-list-tile-title>
                          <v-list-tile-sub-title
                            ><span class="text--primary">
                              subtitle</span
                            ></v-list-tile-sub-title
                          >
                        </v-list-tile-content>
                      </v-list-tile>
                    </v-list>
                  </v-card-actions>
                  <!-- 해당 부분은 detail 에서 사용할 예정
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn icon>
                    <v-icon>favorite</v-icon>
                  </v-btn>
                  <v-btn icon>
                    <v-icon>bookmark</v-icon>
                  </v-btn>
                  <v-btn icon>
                    <v-icon>share</v-icon>
                  </v-btn>
                </v-card-actions>
            -->
                </v-card>
              </v-flex>
            </v-layout>
          </v-container>
        </v-card>
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
export default {
  data() {
    return {
      cards: [
        {
          title: "Pre-fab homes",
          src: "https://cdn.vuetifyjs.com/images/cards/house.jpg",
          flex: 6,
        },
        {
          title: "Favorite road trips",
          src: "https://cdn.vuetifyjs.com/images/cards/road.jpg",
          flex: 6,
        },
        {
          title: "Best airlines",
          src: "https://cdn.vuetifyjs.com/images/cards/plane.jpg",
          flex: 6,
        },
      ],
    };
  },
  methods:{
      onClick(){
          this.$router.push({name : 'Videodetail'});
      }
  }
};
</script>

<style scoped>
</style>