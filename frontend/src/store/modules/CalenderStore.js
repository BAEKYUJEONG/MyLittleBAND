import axios from "../../axios/axios-common";

const CalenderStore = {
  namespaced: true,
  state: {
    events: [],
    selectedEvent: {},
  },
  getters: {
    getEvents(state) {
      let events = [];
      state.events.forEach((val) => {
        events.push({
          no: val.calendarId,
          name: val.title,
          start: new Date(val.start+"T00:00:00"),
          end: new Date(val.end+"T23:59:59"),
          color: "#" + Math.round(Math.random() * 0xffffff).toString(16),
          timed: false,
        });
      });
      return events;
    },
    getSelectedEvent(state) {
      return {
        no: state.selectedEvent.no,
        name: state.selectedEvent.name,
        content: state.selectedEvent.content,
        start: state.selectedEvent.start,
        end: state.selectedEvent.end,
        color: state.selectedEvent.color,
      };
    },
  },
  mutations: {
    setEvents(state, payload) {
      state.events = payload;
    },
    setSelectedEvent(state, payload) {
      state.selectedEvent = payload;
    },
  },
  actions: {
    // 일정 받아오기
    reqEvents(context, bandno) {
      return axios
        .get("/calendar/" + bandno)
        .then((response) => {
          context.commit("setEvents", response.data.object);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 세부 일정 받아오기
    reqEvent(context, info) {
      return axios
        .get("/calendar/" + info.bandno + "/" + info.no)
        .then((response) => {
          if (response.data.status){
            console.log(response.data)
            context.commit("setSelectedEvent", {
              no: info.no,
              name: response.data.object.title,
              content: response.data.object.content,
              start: response.data.object.start,
              end: response.data.object.end,
              color: info.color,
            });
          } else return { result: false, msg: "세부 일정을 받아오지 못했습니다" };
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 일정 등록하기
    reqCreateEvent(context, info) {
      return axios
        .post("/calendar/" + info.bandno, {
          title: info.title,
          content: info.content,
          start: info.start,
          end: info.end,
          member: info.member,
          band: info.band
        })
        .then((response) => {
          if (response.data.status)
            return { result: true, msg: "일정 등록이 완료되었습니다" };
          else return { result: false, msg: "일정 등록이 실패했습니다" };
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 일정 수정하기
    reqModifyEvent(context, info) {
      return axios
        .put("/calendar/" + info.bandno + "/" + info.no, {
          title: info.title,
          start: info.start,
          end: info.end,
          content: info.content,
          member: [],
          band: [],
        })
        .then((response) => {
          if (response.data.status)
            return { result: true, msg: "일정 수정이 완료되었습니다" };
          else return { result: false, msg: "일정 수정이 실패했습니다" };
        })
        .catch((error) => {
          console.log(error);
        });
    },
    // 일정 삭제하기
    reqDeleteEvent(context, info) {
      return axios
        .delete("/calendar/" + info.bandno + "/" + info.no)
        .then((response) => {
          if (response.data.status)
            return { result: true, msg: "일정 삭제가 완료되었습니다" };
          else return { result: false, msg: "일정 삭제가 실패했습니다" };
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};

export default CalenderStore;
