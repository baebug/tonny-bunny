import store from "@/store/store";
import http from "@/common/axios";

export function setInterceptors(instance) {
    instance.interceptors.request.use(
        (config) => {
            // Do something before request is sent
            config.headers.ACCESS_TOKEN = store.state.account.ACCESS_TOKEN;
            console.log(
                "common/interceptors.js 발동! 헤더에 토큰을 저장한다!",
                store.state.account.ACCESS_TOKEN
            );
            return config;
        },
        (error) => {
            // // 오류 발생 시 오류 내용 출력 후 요청 거절
            return Promise.reject(error);
        }
    );

    // Add a response interceptor
    instance.interceptors.response.use(
        (response) => {
            return response;
        },
        function (error) {
            console.log("!! common/interceptors.js 에서 에러가 발생했다");
            // res에서 error가 발생했을 경우 catch로 넘어가기 전에 처리하는 부분
            let errResponseStatus = null;
            const originalRequest = error.config;

            try {
                errResponseStatus = error.response.status;
            } catch (e) {
                e;
            }

            // access token이 만료되어 발생하는 에러인 경우
            if (
                (error.message === "Network Error" || errResponseStatus === 401) &&
                !originalRequest.retry
            ) {
                originalRequest.retry = true;
                // const preRefreshToken = localStorage.getItem("refresh_token");
                const preRefreshToken = store.state.account.refresh_TOKEN;
                if (preRefreshToken) {
                    const httpLoadedToken = http;
                    httpLoadedToken.headers.REFRESH_TOKEN = `${preRefreshToken}`;
                    // refresh token을 이용하여 access token 재발행 받기
                    return httpLoadedToken
                        .post(`/refresh/${store.state.account.userInfo.seq}`)
                        .then((res) => {
                            const { access_token, refresh_token } = res.data;
                            // 새로 받은 token들의 정보 저장

                            localStorage.setItem("access_token", access_token);
                            localStorage.setItem("refresh_token", refresh_token);

                            originalRequest.headers.authorization = `${access_token}`;
                            return http(originalRequest);
                        })
                        .catch(() => {
                            // access token을 받아오지 못하는 오류 발생시 logout 처리
                            localStorage.removeItem("access_token");
                            localStorage.removeItem("refresh_token");
                            localStorage.removeItem("profile");
                            window.location.href = "/";

                            return false;
                        });
                }
                // 오류 발생 시 오류 내용 출력 후 요청 거절
                return Promise.reject(error);
            }
            return Promise.reject(error);
        }
    );
    return instance;
}

export default setInterceptors;
