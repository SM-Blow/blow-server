package com.blow.server.api.common.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseMessage {
    /** auth **/
    SUCCESS_SIGNUP_USER("회원가입에 성공했습니다"),
    SUCCESS_SIGNIN_USER("로그인에 성공했습니다."),

    /** presignedUrl **/
    SUCCESS_GET_PRESIGNED_URLS("단일 이미지 업로드 URL 가져오기 성공"),

    /** user **/
    SUCCESS_GET_MYPAGE("마이페이지 조회를 성공했습니다."),
    /** post **/
    SUCCESS_CREATE_WORK("게시글 생성을 완료 했습니다."),
    SUCCESS_DELETE_WORK("게시글 삭제를 완료 했습니다."),
    SUCCESS_UPDATE_POST("게시글 수정을 완료 했습니다."),
    SUCCESS_GET_POSTS("게시글 조회를 완료 했습니다."),
    SUCCESS_GET_POSTS_BY_CATEGORY("카테고리 별 게시글 조회를 완료 했습니다."),
    SUCCESS_GET_POST_DETAIL("게시글 상세 조회를 완료 했습니다."),
    SUCCESS_UPDATE_STATUS("게시글 거래 상태 변경을 완료 했습니다."),
    SUCCESS_SEARCH_POST("게시글 검색을 완료 했습니다."),
    SUCCESS_SCRAP_POST("게시글 스크랩을 완료 했습니다."),
    SUCCESS_GET_POST_SCRAP_LIST("게시글 스크랩 목록 조회를 완료 했습니다."),
    /** notification **/
    SUCCESS_SEND_NOTIFICATION("성공적으로 푸시알림을 보냈습니다"),

    /** event **/
    SUCCESS_GET_EVENTS("우리학교 행사 목록 조회를 성공 했습니다."),
    SUCCESS_CREATE_EVENT("행사 생성을 성공 했습니다."),
    SUCCESS_APPLY_EVENT("학교 행사 신청을 성공 했습니다."),
    GET_MY_EVENT("내가 신청한 행사 목록 조회를 성공 했습니다."),

    /** coupon **/
    SUCCESS_REGIST_COUPON("쿠폰 등록을 성공 했습니다."),
    SUCCESS_GET_COUPONS("쿠폰 목록 조회를 성공 했습니다."),
    SUCCESS_USE_COUPON("쿠폰 사용을 성공 했습니다.");

    private final String message;
}