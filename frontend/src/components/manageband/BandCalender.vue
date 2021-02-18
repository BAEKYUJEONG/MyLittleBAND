<template>
  <v-container>
    <v-layout col-8 offset-2 fill-height>
      <v-flex >
        <v-sheet height="64">
          <!-- toolbar -->
          <v-toolbar flat>
            <v-toolbar-title v-if="$refs.calendar">
              {{ $refs.calendar.title }}
            </v-toolbar-title>
            <v-spacer></v-spacer>
            <v-btn text class="mr-4" color="grey darken-2" @click="setToday">
              Today
            </v-btn>
            <!-- calender todo create -->
            <v-dialog v-model="createModal" width="500px">
              <template v-slot:activator="{ on, attrs }">
                <v-btn fab text :attr="attrs" v-on="on"
                  ><v-icon> mdi-plus </v-icon></v-btn
                >
              </template>
              <!-- create Modal content -->
              <v-card color="grey lighten-4" max-width="500px" flat>
                <v-toolbar :color="getSelectedEvent.color" dark>
                  <v-text-field
                    v-model="event.title"
                    label="제목"
                    class="pt-5"
                  ></v-text-field>
                  <v-spacer></v-spacer>
                  <v-btn icon @click="onCreate()">
                    <v-icon>mdi-check</v-icon>
                  </v-btn>
                  <v-btn
                    icon
                    @click="
                      () => {
                        this.createModal = false;
                      }
                    "
                  >
                    <v-icon>mdi-undo-variant</v-icon>
                  </v-btn>
                </v-toolbar>

                <v-layout row class="my-3 mx-1">
                  <v-spacer />
                  <v-flex col style="color: black"
                    ><v-card-text>시작</v-card-text></v-flex
                  >
                  <v-flex col>
                    <v-text-field
                      v-model="event.start"
                      label="시작날짜"
                    ></v-text-field>
                  </v-flex>
                  <v-flex col style="color: black"
                    ><v-card-text>종료</v-card-text></v-flex
                  >
                  <v-flex col>
                    <v-text-field
                      v-model="event.end"
                      label="종료날짜"
                    ></v-text-field>
                  </v-flex>
                </v-layout>

                <v-divider class="mx-4" />
                <v-layout
                  ><v-flex class="mx-4">
                    <v-textarea v-model="event.content"></v-textarea>
                  </v-flex>
                </v-layout>
                <v-divider class="mx-4" />
                <v-layout column class="my-3">
                  <v-flex><v-card-subtitle>참여 멤버</v-card-subtitle></v-flex>
                  <v-card-text>참가 멤버 없음</v-card-text>
                </v-layout>
                <v-divider class="mx-4" />
                <v-layout column class="my-3">
                  <v-flex><v-card-subtitle>참여 밴드</v-card-subtitle></v-flex>
                  <v-card-text>참가 밴드 없음</v-card-text>
                </v-layout>
              </v-card>
            </v-dialog>

            <v-btn fab text small color="grey darken-2" @click="prev">
              <v-icon small> mdi-chevron-left </v-icon>
            </v-btn>
            <v-btn fab text small color="grey darken-2" @click="next">
              <v-icon small> mdi-chevron-right </v-icon>
            </v-btn>
          </v-toolbar>
        </v-sheet>
        <v-sheet height="600">
          <v-calendar
            text-overflow
            ref="calendar"
            v-model="focus"
            color="primary"
            :events="getEvents"
            :event-color="getEventColor"
            type="month"
            @click:event="showEvent"
          ></v-calendar>

          <!-- 선택시 나오는 상세일정 -->
          <v-menu
            v-model="selectedOpen"
            :close-on-content-click="false"
            :activator="selectedElement"
            offset-x
          >
            <template v-if="!isModify">
              <v-card
                color="grey lighten-4"
                min-width="600px"
                max-width="700px"
                flat
              >
                <v-toolbar :color="getSelectedEvent.color" dark>
                  <v-toolbar-title
                    v-html="getSelectedEvent.name"
                  ></v-toolbar-title>
                  <v-spacer></v-spacer>
                  <v-btn
                    icon
                    @click="
                      () => {
                        isModify = true;
                        this.event = this.getSelectedEvent;
                        this.event.title = this.getSelectedEvent.name;
                      }
                    "
                  >
                    <v-icon>mdi-pencil</v-icon>
                  </v-btn>
                  <v-btn icon @click="onDelete()">
                    <v-icon>mdi-minus</v-icon>
                  </v-btn>
                </v-toolbar>

                <v-layout row class="my-3 mx-1">
                  <v-spacer />
                  <v-flex col style="color: black"
                    ><v-card-text>시작</v-card-text></v-flex
                  >
                  <v-flex col>
                    <v-chip small>
                      {{ getSelectedEvent.start }}
                    </v-chip>
                  </v-flex>
                  <v-flex col style="color: black"
                    ><v-card-text>종료</v-card-text></v-flex
                  >
                  <v-flex col>
                    <v-chip small>
                      {{ getSelectedEvent.end }}
                    </v-chip>
                  </v-flex>
                </v-layout>

                <v-divider class="mx-4" />
                <v-layout
                  ><v-flex>
                    <v-card-text>{{ getSelectedEvent.content }}</v-card-text>
                  </v-flex>
                </v-layout>
                <v-divider class="mx-4" />
                <v-layout column class="my-3">
                  <v-flex><v-card-subtitle>참여 멤버</v-card-subtitle></v-flex>
                  <v-card-text>참가 멤버 없음</v-card-text>
                </v-layout>
                <v-divider class="mx-4" />
                <v-layout column class="my-3">
                  <v-flex><v-card-subtitle>참여 밴드</v-card-subtitle></v-flex>
                  <v-card-text>참가 밴드 없음</v-card-text>
                </v-layout>
                <v-layout>
                  <v-flex>
                    <v-card-actions>
                      <v-btn
                        text
                        color="secondary"
                        @click="selectedOpen = false"
                        class="mr-1 ml-auto"
                      >
                        확인
                      </v-btn>
                    </v-card-actions>
                  </v-flex>
                </v-layout>
              </v-card>
            </template>
            <!-- 수정 -->
            <template v-else>
              <v-card color="grey lighten-4" max-width="500px" flat>
                <v-toolbar :color="getSelectedEvent.color" dark>
                  <v-text-field
                    v-model="event.title"
                    label="제목"
                    class="pt-5"
                  ></v-text-field>
                  <v-spacer></v-spacer>
                  <v-btn icon @click="onModify()">
                    <v-icon>mdi-check</v-icon>
                  </v-btn>
                  <v-btn icon @click="isModify = false">
                    <v-icon>mdi-undo-variant</v-icon>
                  </v-btn>
                </v-toolbar>

                <v-layout row class="my-3 mx-1">
                  <v-spacer />
                  <v-flex col style="color: black"
                    ><v-card-text>시작</v-card-text></v-flex
                  >
                  <v-flex col>
                    <v-text-field
                      v-model="event.start"
                      label="시작날짜"
                    ></v-text-field>
                  </v-flex>
                  <v-flex col style="color: black"
                    ><v-card-text>종료</v-card-text></v-flex
                  >
                  <v-flex col>
                    <v-text-field
                      v-model="event.end"
                      label="종료날짜"
                    ></v-text-field>
                  </v-flex>
                </v-layout>

                <v-divider class="mx-4" />
                <v-layout
                  ><v-flex class="mx-4">
                    <v-textarea v-model="event.content"></v-textarea>
                  </v-flex>
                </v-layout>
                <v-divider class="mx-4" />
                <v-layout column class="my-3">
                  <v-flex><v-card-subtitle>참여 멤버</v-card-subtitle></v-flex>
                  <v-card-text>참가 멤버 없음</v-card-text>
                </v-layout>
                <v-divider class="mx-4" />
                <v-layout column class="my-3">
                  <v-flex><v-card-subtitle>참여 밴드</v-card-subtitle></v-flex>
                  <v-card-text>참가 밴드 없음</v-card-text>
                </v-layout>
              </v-card>
            </template>
          </v-menu>
        </v-sheet>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapGetters, mapActions } from "vuex";

const CalenderStore = "CalenderStore";

export default {
  data: () => ({
    focus: "",
    createModal: false,
    isModify: false,
    event: {
      title: "",
      content: "",
      start: "",
      end: "",
      member: [],
      band: [],
    },
    selectedElement: null,
    selectedOpen: false,
  }),
  computed: {
    ...mapGetters(CalenderStore, ["getEvents", "getSelectedEvent"]),
  },
  mounted() {
    this.setToday();
    this.reqEvents(this.$route.params.bandno);
    this.$refs.calendar.checkChange();
  },
  methods: {
    ...mapActions(CalenderStore, [
      "reqEvents",
      "reqEvent",
      "reqCreateEvent",
      "reqModifyEvent",
      "reqDeleteEvent",
    ]),
    getEventColor(event) {
      return event.color;
    },
    setToday() {
      this.focus = new Date();
    },
    prev() {
      this.$refs.calendar.prev();
    },
    next() {
      this.$refs.calendar.next();
    },
    showEvent({ nativeEvent, event }) {
      const open = () => {
        this.reqEvent({
          bandno: this.$route.params.bandno,
          no: event.no,
          color: event.color,
        })
          .then((response) => {
            if (response === undefined) {
              this.selectedElement = nativeEvent.target;
              console.log(this.getSelectedEvent);
              setTimeout(() => {
                this.selectedOpen = true;
              }, 10);
            }
          })
          .catch((error) => {
            console.error(error);
          });
      };

      if (this.selectedOpen) {
        this.selectedOpen = false;
        setTimeout(open, 10);
      } else {
        open();
      }

      nativeEvent.stopPropagation();
    },
    // 상세일정 작성
    onCreate() {
      this.reqCreateEvent({
        bandno: this.$route.params.bandno,
        title: this.event.title,
        content: this.event.content,
        start: this.event.start,
        end: this.event.end,
        member: this.event.member,
        band: this.event.band,
      })
        .then((response) => {
          if (response.result) {
            alert(response.msg);
            this.createModal = false;
            this.$router.go(0);
          } else {
            alert(response.msg);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 상세일정 수정
    onModify() {
      this.reqModifyEvent({
        bandno: this.$route.params.bandno,
        no: this.event.no,
        title: this.event.title,
        content: this.event.content,
        start: this.event.start,
        end: this.event.end,
        member: this.event.member,
        band: this.event.band,
      })
      .then((response) => {
          if (response.result) {
            alert(response.msg);
            this.isModify = false;
            this.$router.go(0);
          } else {
            alert(response.msg);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 상세일정 삭제
    onDelete() {
      let c = confirm("정말 삭제하시겠습니까?");
      if (!c) return;
      console.log(this.getSelectedEvent);
      this.reqDeleteEvent({
        bandno: this.$route.params.bandno,
        no: this.getSelectedEvent.no,
      })
        .then((response) => {
          if (response && response.result) {
            alert(response.msg);
            this.$router.go(0);
          } else {
            alert(response.msg);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style>
.v-calendar .v-event {
  margin-left: 0.25em;
}  
</style>
