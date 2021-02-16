import axios from '../../axios/axios-common';

const VideoStore = {
  namespaced: true,
  state: {
    videos: [],
    video: {},
    comments: [],
  },
  getters: {
    getVideos(state) {
      return state.videos;
    },
    getVideo(state) {
      return state.video;
    },
    getComments(state) {
      return state.comments;
    },
  },
  mutations: {
    setVideos(state, payload) {
      state.videos = payload;
    },
    setVideo(state, payload) {
      state.video = payload;
    },
    setComments(state, payload) {
      state.comments = payload;
    },
  },
  actions: {
    // 비디오 리스트 받아오기
    reqVideos(context) {
      return axios
        .get('/videoboard/list')
        .then((response) => {
          if (response.data.status) {
            context.commit('setVideos', response.data.object);
            console.log('받아오기 성공!');
            return { result: true, msg: '비디오 목록 받아오기 성공' };
          } else {
            return {
              result: false,
              msg: '비디오 목록을 불러오는데 실패했습니다',
            };
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },

    // 비디오 받아오기
    reqVideo(context, videonum) {
      return axios
        .get('/videoboard/list/' + videonum)
        .then((response) => {
          if (response.data.status) {
            context.commit('setVideo', response.data.object);
          } else {
            return { result: false, msg: '비디오를 불러오는데 실패했습니다' };
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },

    //댓글 리스트 받아오기.
    reqComments(context, videonum) {
      return axios
        .get('/comment/' + videonum)
        .then((response) => {
          if (response.data.succes == 'success') {
            console.log(response);
            context.commit('setComments', response.data.object);
            return { result: true, msg: '댓글 목록 받아오기 성공' };
          } else {
            return {
              result: false,
              msg: '댓글 목록을 불러오는데 실패했습니다',
            };
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};

export default VideoStore;
