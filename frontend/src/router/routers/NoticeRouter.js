// Components import
import NoticeList from '../../components/notice/NoticeList'; 
import NoticeItem from '../../components/notice/NoticeDetail';
import NoticeCreate from '../../components/notice/NoticeCreate';
import NoticeModify from '../../components/notice/NoticeModify';

// noticeRoute
const noticeRoute = [
    {   // 공지사항 게시판
        path: '/notice',
        name: 'noticelist',
        component: NoticeList,
        props: false,
    },
    {   // 공지사항 게시물
        path: '/notice/:noticeno',
        name: 'noticedetail',
        component: NoticeItem,
        props: true,
    },
    {   // 공지사항 게시물 작성
        path: '/notice/create',
        name: 'noticecreate',
        component: NoticeCreate,
        props: false,
    },
    {   // 공지사항 게시물 수정
        path: '/notice/modify/:noticeno',
        name: 'noticemodify',
        component: NoticeModify,
        props: true,
    }
];

export default noticeRoute;