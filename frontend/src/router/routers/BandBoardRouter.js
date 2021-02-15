// Components import
import BoardBandList from '../../components/boardband/BoardBandList.vue';
import BoardBandCreate from '../../components/boardband/BoardBandCreate.vue';
import BoardBandDetail from '../../components/boardband/BoardBandDetail.vue';
import BoardBandModify from '../../components/boardband/BoardBandModify.vue';


// BandBoardRoute
const BandBoardRoute = [
  {
    // 밴드 게시판
    path: '/band/board/:bandno',
    name: 'boardbandlist',
    component: BoardBandList,
    props: false,
  },
  {
    // 밴드 게시물
    path: '/band/board/detail/:boardno',
    name: 'bandboarddetail',
    component: BoardBandDetail,
    props: true,
  },
  {
    // 밴드 게시물 작성
    path: '/band/board/create/:bandno',
    name: 'boardbandcreate',
    component: BoardBandCreate,
    props: false,
  },
  {
    // 밴드 게시물 수정
    path: '/band/board/modify/:boardno',
    name: 'boardbandmodify',
    component: BoardBandModify,
    props: true,
  },

];

export default BandBoardRoute;
