import axios from "axios";

// axios 공통 설정
export default axios.create({
    baseURL: "http://localhost:8080/",
    headers: {
        "Content-type": "application/json;charset=utf-8",
        "Access-Control-Allow-Origin": "http:/.i4a408.p.ssafy.io"
    },
    //withCredentials: true,
});