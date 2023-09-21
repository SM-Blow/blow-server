package com.blow.server.api.common.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionMessage {

    EMPTY_METHOD_ARGUMENT("빈 요청값이 있습니다."),

    /** user **/
    NOT_FOUND_USER("해당하는 유저를 찾을 수 없습니다."),


    /** post **/
    NOT_FOUND_POST("해당하는 게시글을 찾을 수 없습니다."),
    NOT_POST_OWNER("게시물의 주인이 아닙니다."),

    /** auth **/
    EXIST_EMAIL("이미 존재하는 이메일입니다."),
    EXIST_USERNAME("이미 존재하는 유저 이름입니다."),
    INVALID_TOKEN("유효하지 않은 토큰입니다"),
    EMPTY_TOKEN("빈 토큰입니다"),
    INVALID_EMAIL("잘못된 이메일입니다."),
    INVALID_PASSWORD("잘못된 비밀번호입니다."),


    /** s3 **/
    INVALID_EXTENSION("유효하지 않은 확장자입니다."),

    /** notification **/
    EMPTY_NOTIFICATION_TOKEN("FCM 토큰이 없습니다");
    private final String message;
}
