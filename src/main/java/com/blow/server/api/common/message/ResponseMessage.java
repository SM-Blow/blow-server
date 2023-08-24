package com.blow.server.api.common.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseMessage {


    /** post **/
    SUCCESS_CREATE_WORK("게시글 생성을 완료 했습니다."),
    SUCCESS_DELETE_WORK("게시글 삭제를 완료 했습니다."),
    SUCCESS_UPDATE_POST("게시글 수정을 완료 했습니다."),
    SUCCESS_GET_POSTS("게시글 조회를 완료 했습니다."),
    SUCCESS_GET_POSTS_BY_CATEGORY("카테고리 별 게시글 조회를 완료 했습니다.");


    private final String message;
}