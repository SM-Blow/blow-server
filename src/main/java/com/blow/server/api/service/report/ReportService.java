package com.blow.server.api.service.report;

import com.blow.server.api.dto.report.request.ReportCreateRequestDTO;

public interface ReportService {

    void createReport(Long reportUserId, ReportCreateRequestDTO request);
}
