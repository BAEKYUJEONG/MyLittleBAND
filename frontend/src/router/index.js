import Vue from 'vue';
import VueRouter from 'vue-router';

import Main from '../components/main/Main';
import NotFound from '../components/error/NotFound';
import memberRoute from './routers/MemberRouter';
import videoRoute from './routers/VideoRouter';
import noticeRoute from './routers/NoticeRouter';
import bandRoute from './routers/BandRouter';
import bandboardRoute from './routers/BandBoardRouter';
import timetableRoute from './routers/timetableRouter';

Vue.use(VueRouter);

const routes = [
    {
        path: '/',
        name: 'main',
        component: Main
    },
    {
        path: '*',
        component: NotFound
    }
    // concat을 통한 module화 한 routes 추가
].concat(memberRoute)
    .concat(videoRoute)
    .concat(noticeRoute)
    .concat(bandRoute)
    .concat(bandboardRoute)
    .concat(timetableRoute);

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes,
    scrollBehavior () {
        return { x: 0, y: 0 }
      }
});

export default router;