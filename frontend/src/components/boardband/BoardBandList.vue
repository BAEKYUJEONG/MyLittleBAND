<template>
  <v-main>
    <v-container>
      <v-layout column>
        <!-- title -->
        <v-flex text-center class="ma-10">
          <h1>{{ getBandInfo.name }} 게시판</h1>
        </v-flex>

        <!-- icon - notice create -->
        <v-flex text-right>
          <v-btn
            class="mx-10"
            icon
            color="primary"
            large
            router-link
            :to="{ name: 'boardbandcreate' }"
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
                        <th class="text-center">작성자</th>
                        <th class="text-center">날짜</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr
                        v-for="board in getBoards"
                        :key="board.bandboardId"
                        @click="onBoard(board.bandboardId)"
                      >
                        <td>{{ board.title }}</td>
                        <td>{{ board.name }}</td>
                        <td>{{ board.date }}</td>
                      </tr>
                    </tbody>
                  </template>
                </v-simple-table>
              </v-flex>
              <v-flex text-right>
                <v-btn
                  class="ma-10"
                  icon
                  color="primary"
                  large
                  @click="tobandDetail()"
                >
                  <v-icon size="40">mdi-undo-variant</v-icon>
                </v-btn>
              </v-flex>
            </v-layout>
          </v-responsive>
        </v-flex>
      </v-layout>
      <v-snackbar centered v-model="snackbar" timeout="2000" :color="color">
        {{ msg }}
      </v-snackbar>
    </v-container>
  </v-main>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
const BandStore = "BandStore";
const BandBoardStore = "BandBoardStore";

export default {
  data() {
    return {
      snackbar : false,
      msg : '',
      color : '',
    };
  },
  computed: {
    ...mapGetters(BandStore, ["getBandInfo"]),
    ...mapGetters(BandBoardStore, ["getBoards"]),
  },
  methods: {
    ...mapActions(BandStore, ["reqBandInfo"]),
    ...mapActions(BandBoardStore, ["reqBoards", "reqBoard", "reqCommentInit"]),

    onBoard(no) {
      this.reqCommentInit();

      this.reqBoard(no).then((response) => {
        if (!response) this.$router.push("/band/board/detail/" + no);
        else {
          this.msg = response.msg;
          this.color = "error";
          this.snackbar = true;
        }
      });
    },
    tobandDetail() {
      this.$router.push("/band/detail/" + this.$route.params.bandno);
    },
  },
  created() {
    this.reqBandInfo(this.$route.params.bandno);
    this.reqBoards(this.$route.params.bandno);
  },
};
</script>

<style>
</style>