// Components import
import VideoList from '../../components/boardvideo/VideoList'; 
import VideoItem from '../../components/boardvideo/VideoDetail';
import VideoCreate from '../../components/boardvideo/VideoCreate';
import VideoModify from '../../components/boardvideo/VideoModify';

// vedioRoute
const videoRoute = [
    {   // 영상 게시판
        path: '/video',
        name: 'videolist',
        component: VideoList,
        props: false,
    },
    {   // 영상 게시물
        path: '/video/:videono',
        name: 'videodetail',
        component: VideoItem,
        props: true,
    },
    {   // 영상 게시물 작성
        path: '/video/create',
        name: 'videocreate',
        component: VideoCreate,
        props: false,
    },
    {   // 영상 게시물 수정
        path: '/video/modify/:videono',
        name: 'videomodify',
        component: VideoModify,
        props: true,
    }
];

export default videoRoute;