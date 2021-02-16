<template>
  <v-main>
    <v-container>
      <v-layout column>
        <!-- title -->
        <v-flex text-center class="ma-10">
          <h1>공지사항</h1>
        </v-flex>

        <!-- icon - notice create -->
        <v-flex text-right v-if="manager == '1'">
          <v-btn
            icon
            color="primary"
            large
            router-link
            :to="{ name: 'noticecreate' }"
          >
            <v-icon size="50">mdi-plus</v-icon>
          </v-btn>
        </v-flex>

        <!-- table -->
        <v-flex class="ma-7" xs-10 sm-10 col-18>
          <v-responsive :aspect-ratio="16 / 9">
            <v-layout column>
              <v-flex text-center>
                <v-simple-table>
                  <template v-slot:default>
                    <thead>
                      <tr>
                        <th class="text-center">제목</th>
                        <th class="text-center">날짜</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr
                        v-for="notice in getNotices"
                        :key="notice.noticeId"
                        @click="onNotice(notice.noticeId)"
                      >
                        <td>{{ notice.noticeTitle }}</td>
                        <td>{{ notice.date }}</td>
                      </tr>
                    </tbody>
                  </template>
                </v-simple-table>
              </v-flex>
              <!-- pagination -->
              <v-flex>
                <v-pagination
                  v-model="page"
                  :length="length"
                  color="grey"
                  prev-icon="mdi-menu-left"
                  next-icon="mdi-menu-right"
                ></v-pagination>
              </v-flex>
            </v-layout>
          </v-responsive>
        </v-flex>
      </v-layout>
    </v-container>
  </v-main>
</template>

<script>
import { mapGetters, mapActions } from "vuex";

const NoticeStore = "NoticeStore";
const MemberStore = "MemberStore";
export default {
  data() {
    return {
      page: 1,
      length: 0,
    };
  },
  computed: {
    ...mapGetters(NoticeStore, ["getNotices", "getListnum"]),
    ...mapGetters(MemberStore, { manager: "getManager" }),
  },
  created() {
    this.reqNotices(this.page);
    this.reqListnum().then((res) => {
      if (res.result) this.setLength();
    });
  },
  watch: {
    page: {
      handler() {
        this.reqNotices(this.page);
      },
    },
  },
  methods: {
    ...mapActions(NoticeStore, ["reqNotices", "reqNotice", "reqListnum"]),
    // 공지사항 글 불러오기
    onNotice(no) {
      this.reqNotice(no).then((response) => {
        if (!response) this.$router.push("/notice/" + no);
        else alert(response.msg);
      });
    },
    //페이지 최대개수 설정하기
    setLength() {
      this.length = Math.ceil(this.getListnum / 5);
    },
  },
};
</script>

<style></style>
