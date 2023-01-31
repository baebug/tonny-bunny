import temp from "./getters/temp";
import account from "./getters/account";
import board from "./getters/board";
import tonny from "./getters/tonny";
import jtonny from "./getters/jtonny";
import bunny from "./getters/bunny";
import common from "./getters/common";

export default {
    // 각 파일 만들어서 모듈화 사용
    ...temp,
    ...account,
    ...board,
    ...tonny,
    ...jtonny,
    ...bunny,
    ...common,
};
