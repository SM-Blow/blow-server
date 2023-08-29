package com.blow.server.api.common.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionMessage {
    /** user **/
    NOT_FOUND_USER("해당하는 유저를 찾을 수 없습니다."),

    /** post **/
    NOT_FOUND_POST("해당하는 게시글을 찾을 수 없습니다."),
    NOT_POST_OWNER("게시물의 주인이 아닙니다."),

    /** s3 **/
    INVALID_EXTENSION("유효하지 않은 확장자입니다.");
    private final String message;
}
