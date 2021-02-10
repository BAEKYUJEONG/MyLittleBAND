<template>
  <v-container>
    <v-layout col-8 offset-2 fill-height>
      <v-flex class="ma-10">
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
            <v-btn fab text>
              <v-icon>
                mdi-plus
              </v-icon>
            </v-btn>
            <v-btn fab text small color="grey darken-2" @click="prev">
              <v-icon small>
                mdi-chevron-left
              </v-icon>
            </v-btn>
            <v-btn fab text small color="grey darken-2" @click="next">
              <v-icon small>
                mdi-chevron-right
              </v-icon>
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
            <v-card color="grey lighten-4" max-width="500px" flat>
              <v-toolbar :color="getSelectedEvent.color" dark>
                <v-toolbar-title
                  v-html="getSelectedEvent.name"
                ></v-toolbar-title>
                <v-spacer></v-spacer>
                <v-btn icon>
                  <v-icon>mdi-pencil</v-icon>
                </v-btn>
                <v-btn icon>
                  <v-icon>mdi-minus</v-icon>
                </v-btn>
              </v-toolbar>

              <v-layout row class="my-3 mx-1">
                <v-spacer />
                <v-flex col style="color: black;"><v-text>시작</v-text></v-flex>
                <v-flex col>
                  <v-chip small>
                    {{ getSelectedEvent.start }}
                  </v-chip>
                </v-flex>
                <v-flex col style="color: black;"><v-text>종료</v-text></v-flex>
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
                    <v-btn text color="secondary" @click="selectedOpen = false" class="mr-1 ml-auto">
                      확인
                    </v-btn>
                  </v-card-actions>
                </v-flex>
              </v-layout>
            </v-card>
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
    selectedEvent: {},
    selectedElement: null,
    selectedOpen: false,
  }),
  computed: {
    ...mapGetters(CalenderStore, ["getEvents", "getSelectedEvent"]),
  },
  mounted() {
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
      this.focus = "";
    },
    prev() {
      this.$refs.calendar.prev();
    },
    next() {
      this.$refs.calendar.next();
    },
    showEvent({ nativeEvent, event }) {
      const open = () => {
        this.reqEvent({ bandno: this.$route.params.bandno, no: event.no })
          .then((response) => {
            if (response === undefined) {
              this.selectedElement = nativeEvent.target;
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
  },
};
</script>

<style></style>
