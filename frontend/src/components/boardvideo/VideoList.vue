<template>
  <v-main>
    <v-container grid-list-xs>
      <v-layout column align-center>
        <v-flex>
          <!-- 필터 영역 -->
          <v-col >
            <v-autocomplete
              v-model="friends"
              :disabled="isUpdating"
              :items="people"
              filled
              chips
              color="blue lighten-2"
              label="필터"
              item-text="name"
              item-value="name"
              multiple
            >
              <template v-slot:append-outer>
                <v-slide-x-reverse-transition mode="out-in">
                  <v-icon
                    :key="`icon-${isEditing}`"
                    :color="isEditing ? 'success' : 'info'"
                    @click="isEditing = !isEditing"
                    v-text="
                      isEditing ? 'mdi-check-outline' : 'mdi-filter-variant'
                    "
                  ></v-icon>
                </v-slide-x-reverse-transition>
              </template>
              <template v-slot:selection="data">
                <v-chip
                  v-bind="data.attrs"
                  :input-value="data.selected"
                  close
                  @click="data.select"
                  @click:close="remove(data.item)"
                >
                  {{ data.item.name }}
                </v-chip>
              </template>
              <template v-slot:item="data">
                <template v-if="typeof data.item !== 'object'">
                  <v-list-item-content v-text="data.item"></v-list-item-content>
                </template>
                <template v-else>
                  <v-list-item-content>
                    <v-list-item-title
                      v-html="data.item.name"
                    ></v-list-item-title>
                    <v-list-item-subtitle
                      v-html="data.item.group"
                    ></v-list-item-subtitle>
                  </v-list-item-content>
                </template>
              </template>
            </v-autocomplete>
          </v-col>
        </v-flex>
        <v-flex>
          <v-row>
            <v-col>
              <v-text-field
                v-model="msg"
                label="검색어"
                placeholder="원하는 검색어를 입력하세요."
                append-outer-icon = "mdi-magnify"
                @click:append-outer="onSearch"
              ></v-text-field>
            </v-col>
          </v-row>
        </v-flex>
      </v-layout>
    </v-container>

    <v-container grid-list-sm>
      <v-layout justify-center column wrap>
        <v-flex xs12 sm9>
          <!-- 카드 뷰 시작 -->
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
        </v-flex>
      </v-layout>
    </v-container>
  </v-main>
</template>

<script>
export default {
  data() {
    return {
      friends:[],
      msg:'',
      isEditing: false,
      isUpdating: false,
      name: "밴드 속성",
      people: [
        { header: "컬러" },
        { name: "빨강", group: "색깔" },
        { name: "보라", group: "색깔" },
        { name: "노랑", group: "색깔" },
        { divider: true },
        { header: "장르" },
        { name: "락", group: "장르" },
        { name: "팝", group: "장르" },
        { name: "재즈", group: "장르" },
      ],

      cards: [
        {
          title: "201215 싸피밴드",
          src: require("@/assets/image/1.jpg"),
          flex: 4,
          show: false,
          content: "싸피밴드의 공연입니다",
        },
        {
          title: "191113 써니밴드",
          src: require("@/assets/image/2.jpg"),
          flex: 4,
          show: false,
          content: "써니밴드 공연영상",
        },
        {
          title: "싸피홀 200303",
          src: require("@/assets/image/3.jpg"),
          flex: 4,
          show: false,
          content: "싸피홀에서의 공연",
        },
        {
          title: "200903 전공밴드",
          src: require("@/assets/image/4.jpg"),
          flex: 4,
          show: false,
          content: "전공밴드 공연영상입니다.",
        },
        {
          title: "19회 싸피데이 공연",
          src: require("@/assets/image/5.jpg"),
          flex: 4,
          show: false,
          content: "싸피데이 공연영상입니다.",
        },
        {
          title: "27회 싸피 정기공연",
          src: require("@/assets/image/6.jpg"),
          flex: 4,
          show: false,
          content: "싸피...",
        },
      ],
    };
  },
  methods: {
    onSearch(){
      // 사용자가 원하는 검색어를 눌렀을 때, 검색이 되도록 처리.
      //
      this.msg = ''
    },
    onClick() {
      // 비디오를 클릭했을 때, 비디오가 선택되도록 처리.
      this.$router.push({ name: "videodetail" });
    },
    remove(item) {
      const index = this.friends.indexOf(item.name);
      if (index >= 0) this.friends.splice(index, 1);
    },
  },
};
</script>

<style scoped>
</style>