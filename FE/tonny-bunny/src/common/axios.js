import axios from "axios";

// axios 비로그인 객체 생성
function createInstance() {
    const instance = axios.create({
        // 백엔드 localhost:8080
        baseURL: "http://localhost:8080/api",

        headers: {
            // "Content-Type": "application/json",
        },

        withCredentials: true,
    });
    return instance;
}
export const http = createInstance();

export default http;
