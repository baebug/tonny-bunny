import temp from "./mutations/temp";
import account from "./mutations/account";
import board from "./mutations/board";
import tonny from "./mutations/tonny";
import jtonny from "./mutations/jtonny";
import bunny from "./mutations/bunny";
import common from "./mutations/common";

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
