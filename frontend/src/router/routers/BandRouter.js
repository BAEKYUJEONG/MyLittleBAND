// Components import
import BandMain from '../../components/bandmain/BandMain';
import BandInfo from '../../components/manageband/BandInfo';
import BandDetail from '../../components/manageband/BandDetail';
import BandCreate from '../../components/manageband/BandCreate';
import BandModify from '../../components/manageband/BandModify';
import ReserveStage from '../../components/manageband/ReserveStage';
import FindFollowBand from '../../components/follow/FindFollowBand'
// ExampleRouter 정의 부분
const bandRoute = [
    {   // 자신의 밴드 목록
        path: '/band/list/:memberno',
        name: 'bandmain',
        component: BandMain,
        props: true,
    },
    {   // 밴드 소개 페이지
        path: '/band/introduce/:bandno',
        name: 'bandinfo',
        component: BandInfo,
        props: true,
    },
    {   // 밴드멤버용 상세 페이지
        path: '/band/detail/:bandno',
        name: 'banddetail',
        component: BandDetail,
        props: true,
    },
    {   // 밴드개설 페이지
        path: '/bandcreate',
        name: 'bandcreate',
        component: BandCreate,
        props: true,
    },
    {   // 밴드정보수정 페이지
        path: '/band/modify/:bandno',
        name: 'bandmodify',
        component: BandModify,
        props: true,
    },
    {   // 밴드정보수정 페이지
        path: '/band/reserve/:bandno',
        name: 'reservestage',
        component: ReserveStage,
        props: true,
    },
    {   // 밴드정보수정 페이지
        path: '/findfollowband/:memberno',
        name: 'findfollowband',
        component: FindFollowBand,
        props: true,
    },
   
];

export default bandRoute;