import axios from "axios";
import { setInterceptors } from "@/common/interceptors";

const serverUrl =
    process.env.NODE_ENV == "production" ? process.env.VUE_APP_SERVER_URL : "http://localhost:8080";

function createInstanceWithAuth() {
    // axios 객체 생성 export
    const instance = axios.create({
        // 백엔드 localhost:8080
        baseURL: serverUrl + "/api",

        headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Credentials": true,
        },

        withCredentials: true,
    });
    return setInterceptors(instance);
}
export const http = createInstanceWithAuth();

export default http;
