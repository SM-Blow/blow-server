package com.blow.server.api.service.report;

import com.blow.server.api.common.message.ExceptionMessage;
import com.blow.server.api.dto.report.request.ReportCreateRequestDTO;
import com.blow.server.api.entity.Report;
import com.blow.server.api.repository.ReportRepository;
import com.blow.server.api.repository.UserRepository;
import com.google.api.gax.rpc.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final UserRepository userRepository;
    private final ReportRepository reportRepository;

    @Transactional
    @Override
    public void createReport(Long reportUserId, ReportCreateRequestDTO request) {
        val reportUser = userRepository.findById(reportUserId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessage.NOT_FOUND_USER.getMessage()));

        val targetUser = userRepository.findById(request.reportUserId())
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessage.NOT_FOUND_USER.getMessage()));

        val report = Report.builder()
                .reportUser(reportUser)
                .targetUser(targetUser)
                .content(request.content())
                .status(true)
                .build();

        reportRepository.save(report);
    }
}
