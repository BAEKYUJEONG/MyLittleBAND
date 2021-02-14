<template>
<v-main>
    <v-container>
      <v-layout column>
        <!-- title -->
        <v-flex text-center class="ma-10">
          <h1>{{ getBandInfo.name }}</h1>
        </v-flex>

        <!-- icon - notice create --> 
        <v-flex text-right >
          <v-btn
            icon
            color="primary"
            large
            router-link :to="{name: 'boardbandcreate'}"
          >
            <v-icon size="50">mdi-plus</v-icon>
          </v-btn>
        </v-flex>

        <!-- table -->
        <v-flex class="ma-7" xs-10 sm-10 col-18>
          <v-responsive :aspect-ratio="16/9">
          <v-layout column>
            <v-flex text-center>
              <v-simple-table>
                <template v-slot:default>
                  <thead>
                    <tr>
                      <th class="text-center">
                        제목
                      </th>
                      <th class="text-center">
                        작성자
                      </th>
                      <th class="text-center">
                        날짜
                      </th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="board in getBoards" :key="board.no" @click="onBoard(board.no)">
                      <td>{{ board.title }}</td>
                      <td>{{ board.writer }}</td>
                      <td>{{ board.date }}</td>
                    </tr>
                  </tbody>
                </template>
              </v-simple-table>
            </v-flex>
            <!-- pagination -->
            <v-flex>
              <v-pagination
                v-model="page"
                :length="4"
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
import { mapGetters,mapActions } from "vuex";
const BandStore = "BandStore";
const BandBoardStore = "BandBoardStore";

export default {
  data(){
    return{
      page : 1,
    }
  },
  computed: {
    ...mapGetters(BandStore, ["getBandInfo"]),
    ...mapGetters(BandBoardStore,  ["getBoards"]),
  },
  methods:{
    ...mapActions(BandStore,["reqBandInfo"]),
    ...mapActions(BandBoardStore,["reqBoards","reqBoard"]),

    onBoard(no){
      this.reqBoard(no)
      .then((response) => {
        if(!response) this.$router.push("/band/board/detail/"+no);
        else                alert(response.msg);
      })
    },
  },
  created(){
    this.reqBandInfo(this.$route.params.bandno);
    this.reqBoards({ bandId : this.$route.params.bandno, page : this.page});
  }
};
</script>

<style>
</style>