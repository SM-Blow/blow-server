package com.blow.server.api.controller;

import com.blow.server.api.common.ApiResponse;
import com.blow.server.api.common.message.ResponseMessage;
import com.blow.server.api.dto.report.request.ReportCreateRequestDTO;
import com.blow.server.api.entity.BlowUserDetails;
import com.blow.server.api.service.report.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/report")
public class ReportController {
    private final ReportService reportService;

    @PostMapping
    public ResponseEntity<ApiResponse> createReport(@AuthenticationPrincipal BlowUserDetails userDetail,
                                                    @Valid @RequestBody ReportCreateRequestDTO request) {
        val reportUserId = userDetail.getId();
        reportService.createReport(reportUserId, request);
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_CREATE_REPORT.getMessage(), null));
    }
}
