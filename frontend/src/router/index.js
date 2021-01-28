import Vue from 'vue';
import VueRouter from 'vue-router';
import Videocreate from '../components/boardvideo/VideoCreate.vue';
import Videolist from '../components/boardvideo/VideoList.vue'
import Videodetail from '../components/boardvideo/VideoDetail.vue'

import Main from '../components/main/Main';
import memberRoute from './routers/MemberRouter';
import videoRoute from './routers/VideoRouter';
import noticeRoute from './routers/NoticeRouter';
import bandRoute from './routers/BandRouter';
import timetableRoute from './routers/timetableRouter';

Vue.use(VueRouter);

const routes = [
    {
        path : '/',
        name : 'main',
        component : Main
    },
    // concat을 통한 module화 한 routes 추가
].concat(memberRoute)
.concat(videoRoute)
.concat(noticeRoute)
.concat(bandRoute)
.concat(timetableRoute);

const router = new VueRouter({
    mode: 'history',
    base : process.env.BASE_URL,
    routes,
});

export default router;