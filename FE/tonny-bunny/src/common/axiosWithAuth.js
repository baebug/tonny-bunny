import axios from "axios";
import { setInterceptors } from "@/common/interceptors";

// axios 객체 생성 auth가 필요한 경우
function createInstanceWithAuth() {
    const instance = axios.create({
        // 백엔드 localhost:8080
        baseURL: "http://localhost:8080/api",

        headers: {
            "Content-Type": "application/json",
            // "Access-Control-Expose-Headers": "Authorization",
            "Allow-Control-Allow-Origin": "*",
        },

        withCredentials: true,
    });
    return setInterceptors(instance);
}
export const httpWithAuth = createInstanceWithAuth();

export default httpWithAuth;
