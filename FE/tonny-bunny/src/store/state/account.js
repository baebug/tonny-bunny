import { saveAuthToCookie, getAuthFromCookie } from "@/common/utils";

export default {
    isLogin: false,
    isLoginModalOpen: false,
    token: getAuthFromCookie() || "",
    token2: saveAuthToCookie() || "",

    loginInfo: {
        id: "",
        pw: "",
    },

    // 로그인 관련 정보
    isHelper: false,
    possibleLanguage: null,
    ACCESS_TOKEN: null,
    REFRESH_TOKEN: null,
    userInfo: {},
    helperInfo: {},
};
