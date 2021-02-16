<template>
  <v-main>
    <v-container grid-list-xs>
      <v-row justify="space-around">
        <!-- 필터 영역 -->
        <v-col cols="6" align="center">
          <v-autocomplete
            v-model="selectOption"
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
            <!-- 버튼 -->
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
              <!-- close 옵션을 없애고 하나씩 선택하는 것으로 변 경-->
              <v-chip
                v-bind="data.attrs"
                :input-value="data.selected"
                @click="data.select"
              >
                {{ data.item.name }}
              </v-chip>
            </template>
            <template :disabled="isButton" v-slot:item="data">
              <template v-if="typeof data.item !== 'object'">
                <v-list-item-content v-text="data.item"></v-list-item-content>
              </template>
              <template v-else>
                <v-list-item-content @click="onItemClick(data.item.idx)">
                  <v-list-item-title
                    v-html="data.item.name"
                  ></v-list-item-title>
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
            <v-flex
              v-for="(card, idx) in getVideos"
              :key="idx"
              v-bind="{ [`xs4`]: true }"
            >
              <v-card class="mx-10 my-10">
                <v-img
                  :src="card.boardThumbnail"
                  height="200px"
                  @click="onVideo(card.boardId)"
                  style="cursor: pointer"
                >
                </v-img>

                <v-card-actions>
                  <v-list>
                    <v-list-item class="grow">
                      <v-list-item-avatar color="grey darken-3">
                        <v-img :src="card.img"></v-img>
                      </v-list-item-avatar>
                      <v-list-item-content>
                        <v-list-item-title
                          v-text="card.boardSubject"
                        ></v-list-item-title>
                      </v-list-item-content>
                    </v-list-item>
                  </v-list>
                  <v-spacer></v-spacer>
                  <v-btn icon @click="card.shows = !card.shows">
                    <v-icon>{{
                      card.shows ? 'keyboard_arrow_down' : 'keyboard_arrow_up'
                    }}</v-icon>
                  </v-btn>
                </v-card-actions>

                <v-slide-y-transition>
                  <v-card-text v-show="card.shows" v-text="card.boardContent">
                  </v-card-text>
                </v-slide-y-transition>
              </v-card>
            </v-flex>
          </v-layout>
        </v-flex>
        <v-row align="center" justify="space-around">
          <v-col cols="auto" class="mb-4">
            <v-btn block outlined color="blue" @click="onAdd"> 글쓰기 </v-btn>
          </v-col>
        </v-row>
      </v-layout>
    </v-container>
  </v-main>
</template>

<script>
import axios from '@/axios/axios-common.js';

import { mapGetters, mapActions, mapMutations } from 'vuex';

const VideoStore = 'VideoStore'; //비디오 store
const MemberStore = 'MemberStore'; //로그인 체크 용.

export default {
  created() {
    this.reqVideos();
  },
  mounted() {
    this.getbandlist();
  },
  data() {
    return {
      isButton: false,
      filter1: 1,
      filter2: 0,
      bandlist: [],
      selectOption: [],
      msg: '',
      isEditing: false,
      isUpdating: false,
      name: '밴드 속성',
      people: [
        { header: '컬러' },
        { name: '빨강', group: '색깔', flag: true, idx: 1 },
        { name: '주황', group: '색깔', flag: false, idx: 2 },
        { name: '노랑', group: '색깔', flag: false, idx: 3 },
        { name: '초록', group: '색깔', flag: false, idx: 4 },
        { name: '파랑', group: '색깔', flag: false, idx: 5 },
        { name: '검정', group: '색깔', flag: false, idx: 6 },
        { name: '하양', group: '색깔', flag: false, idx: 7 },
        { divider: true },
        { header: '장르' },
        { name: '락', group: '장르', flag: false, idx: 10 },
        { name: '팝', group: '장르', flag: false, idx: 11 },
        { name: '재즈', group: '장르', flag: false, idx: 12 },
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
    ...mapMutations(VideoStore, ['setVideos', 'setVideo']),
    onEditing() {
      // 여긴 선택한 필터로 검색하는 곳.

      // 필터에서 하나씩만 선택했다면,

      if (this.filter1 == 1 && this.filter2 == 1) {
        let st1, st2;

        // 순서 맞춰 주는 곳.
        this.isEditing = !this.isEditing;
        let tmpFlag = false;
        for (let i = 1; i < 8; i++) {
          if (this.selectOption[0] == this.people[i].name) {
            st1 = this.selectOption[0];
            st2 = this.selectOption[1];
            tmpFlag = true;
            break;
          }
        }
        if (!tmpFlag) {
          st1 = this.selectOption[1];
          st2 = this.selectOption[0];
        }

        //formData를 통해 전송.
        let frm = new FormData();
        frm.append('color', st1);
        frm.append('genre', st2);

        axios
          .post('/filter', frm)
          .then((response) => {
            this.setVideos(response.data.object);
          })
          .catch((exp) => alert(exp + '조회에 실패하였습니다.'));
        // 여기서 axios를 이용한 검색 처리.(필터는 select에 있음)
      } else alert('속성을 각각 하나씩 선택해 주세요!');
    },

    onSearch() {
      // 사용자가 원하는 검색어를 눌렀을 때, 검색이 되도록 처리.
      axios
        .get('/filter/' + this.msg)
        .then((response) => {
          this.setVideos(response.data.object);
        })
        .catch((exp) => alert(exp + '조회에 실패하였습니다.'));

      //this.$router.go(0); //자동 새로고침. 쓰지는 않음.
      this.msg = '';
    },
    onVideo(videonum) {
      // 비디오를 클릭했을 때, 비디오가 선택되도록 처리.
      console.log(videonum);
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
      //chip뒤에 달리는 close 옵션인데, 현재는 사용하지 않음.
      console.log(this.selectOption);
      const index = this.selectOption.indexOf(item.name);
      if (index >= 0) this.selectOption.splice(index, 1);
    },
    onAdd() {
      if (this.islogin) {
        //로그인이 된 경우에만 글쓰기로 보냄.
        console.log(this.bandlist.length);
        if (this.bandlist.length > 0) {
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
    onItemClick(idx) {
      //console.log(this.filter1);

      // 클릭시 속성에 맞게 boolean 값을 변경 하고. 카운팅 해줌.
      if (this.people[idx].group == '색깔') {
        if (!this.people[idx].flag) {
          this.filter1++;
        } else this.filter1--;
        this.people[idx].flag = !this.people[idx].flag;
      } else {
        if (!this.people[idx].flag) {
          this.filter2++;
        } else this.filter2--;
        this.people[idx].flag = !this.people[idx].flag;
      }
    },
  },
};
</script>

<style scoped></style>
