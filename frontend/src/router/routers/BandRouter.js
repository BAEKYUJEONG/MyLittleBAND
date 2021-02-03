// Components import
import BandList from '../../components/bandmain/BandList';
import BandInfo from '../../components/manageband/BandInfo';
import BandDetail from '../../components/manageband/BandDetail';
// ExampleRouter 정의 부분
const bandRoute = [
    {   // 자신의 밴드 목록
        path: '/band/:memberno',
        name: 'bandlist',
        component: BandList,
        props: true,
    },
    {   // 밴드 소개 페이지
        path: '/band/introduce/:bandno',
        name: 'bandinfo',
        component: BandInfo,
        props: true,
    },
    {   // 밴드멤버용 상세 페이지
        path: '/band/:bandno',
        name: 'banddetail',
        component: BandDetail,
        props: true,
    },
    /*
        하나의 라우터는 이런 형식으로 정의합니다
        {
            path: address 뒤에 붙을 path -> http://localhost:8080/{path}
            주의! : path는 절대경로로 작성을 부탁드립니다
            name: router의 name
            component: routing될 component
            props: 라우팅 될 곳에 props를 전달하면 true, 그렇지 않으면 false
        }
        ex)
        {
            path: '/board/general',
            name: 'general',
            component: General,
            props: true,
        },
        밑으로 이어 붙이면 됩니다
    */
];

export default bandRoute;