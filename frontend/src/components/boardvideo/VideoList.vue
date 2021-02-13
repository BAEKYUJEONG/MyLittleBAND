<template>
  <v-main>
    <v-container grid-list-xs>
      <v-row justify="space-around">
        <!-- 필터 영역 -->
        <v-col cols="6" align="center">
          <v-autocomplete
            v-model="select"
            :disabled="isUpdating"
            :items="people"
            filled
            chips
            color="blue lighten-2"
            label="필터"
            item-text="name"
            item-value="name"
          >
            <template v-slot:append-outer>
              <v-slide-x-reverse-transition mode="out-in">
                <v-icon
                  :key="`icon-${isEditing}`"
                  :color="isEditing ? 'success' : 'info'"
                  @click="onEditing"
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
      </v-row>

      <!-- 검색 영역 -->

      <v-row justify="space-around">
        <v-col cols="6" align="center">
          <v-text-field
            v-model="msg"
            label="검색어"
            placeholder="원하는 검색어를 입력하세요."
            append-outer-icon="mdi-magnify"
            @click:append-outer="onSearch"
          ></v-text-field>
        </v-col>
      </v-row>

      <!-- 비디오 리스트 영역 -->
      <v-layout justify-center column wrap>
        <v-flex xs12 sm9>
          <!-- 카드 뷰 시작 -->
          <v-layout row wrap>
            <!-- 백엔드 연동 시 : v-for="card in getVideos" -->
            <v-flex
              v-for="card in cards"
              :key="card.title"
              v-bind="{ [`xs${card.flex}`]: true }"
            >
              <v-card class="mx-10 my-10">
                <v-img
                  :src="card.src"
                  height="200px"
                  @click="onVideo(card.no)"
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
                      card.show ? 'keyboard_arrow_down' : 'keyboard_arrow_up'
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
        <v-row align="center" justify="space-around">
          <v-col cols="auto" class="mb-4">
            <v-btn block outlined color="blue" @click="onAdd">
              글쓰기
            </v-btn>
          </v-col>
        </v-row>
      </v-layout>
    </v-container>
  </v-main>
</template>

<script>
import axios from '@/axios/axios-common.js';

import { mapGetters, mapActions } from 'vuex';

const VideoStore = 'VideoStore';
const MemberStore = 'MemberStore'; //로그인 체크 용.

export default {
  data() {
    return {
      bandlist: [],
      select: [],
      msg: '',
      isEditing: false,
      isUpdating: false,
      name: '밴드 속성',
      people: [
        { header: '컬러' },
        { name: '빨강', group: '색깔' },
        { name: '보라', group: '색깔' },
        { name: '노랑', group: '색깔' },
        { divider: true },
        { header: '장르' },
        { name: '락', group: '장르' },
        { name: '팝', group: '장르' },
        { name: '재즈', group: '장르' },
      ],

      cards: [
        {
          title: '201215 싸피밴드',
          src: require('@/assets/image/1.jpg'),
          flex: 4,
          show: false,
          content: '싸피밴드의 공연입니다',
        },
        {
          title: '191113 써니밴드',
          src: require('@/assets/image/2.jpg'),
          flex: 4,
          show: false,
          content: '써니밴드 공연영상',
        },
        {
          title: '싸피홀 200303',
          src: require('@/assets/image/3.jpg'),
          flex: 4,
          show: false,
          content: '싸피홀에서의 공연',
        },
        {
          title: '200903 전공밴드',
          src: require('@/assets/image/4.jpg'),
          flex: 4,
          show: false,
          content: '전공밴드 공연영상입니다.',
        },
        {
          title: '19회 싸피데이 공연',
          src: require('@/assets/image/5.jpg'),
          flex: 4,
          show: false,
          content: '싸피데이 공연영상입니다.',
        },
        {
          title: '27회 싸피 정기공연',
          src: require('@/assets/image/6.jpg'),
          flex: 4,
          show: false,
          content: '싸피...',
        },
      ],
    };
  },
  computed: {
    ...mapGetters(VideoStore, ['getVideos']),
    ...mapGetters(MemberStore, {
      islogin: 'getIsLogined', //islogin 변수에 getIsLogined 리턴값 저장
      memberid: 'getMemberId',
    }),
  },
  methods: {
    ...mapActions(VideoStore, ['reqVideos', 'reqVideo']),

    onEditing(){
      this.isEditing = !this.isEditing;

      // 여기서 axios를 이용한 검색 처리.(필터는 select에 있음)

    },

    onSearch() {
      // 사용자가 원하는 검색어를 눌렀을 때, 검색이 되도록 처리.

      // 여기서 axios를 이용한 검색 처리.

      // 이후
      let videos = [];
      this.reqVideos(videos).then((response) => {
        if (!response) console.log(response);
        //여기서 가져온 데이터로 새로고침 하는 구문이 필요함.
        else alert(response.msg);
      });
      this.msg = '';
    },
    onVideo(videonum) {
      // 비디오를 클릭했을 때, 비디오가 선택되도록 처리.
      if (this.islogin) {
        // 로그인이 되었다면,
        this.reqVideo(videonum).then((response) => {
          // 선택한 비디오로 req 요청 보냄.
          if (!response) this.$router.push('/video/' + videonum);
          else alert(response.msg);
        });
      } else alert('로그인 해주세요!');
    },
    remove(item) {
      const index = this.friends.indexOf(item.name);
      if (index >= 0) this.friends.splice(index, 1);
    },
    onAdd() {
      if (this.islogin) {
        //로그인이 된 경우에만 글쓰기로 보냄.
        this.getbandlist();
        console.log(this.bandlist.length);
        if (this.bandlist.length == 0) {
          //가입한 밴드 리스트가 1개 이상일 경우에만,
          this.$router.push({ name: 'videocreate' });
        } else alert('밴드 가입 후 글쓰기 가능합니다!');
      } else alert('로그인 해주세요!');
    },
    getbandlist() {
      axios
        .get('/band-list/' + this.memberid)
        .then((response) => (this.bandlist = response.data.object))
        .catch((exp) => alert(exp + '조회에 실패하였습니다.'));
    },
  },
};
</script>

<style scoped></style>
