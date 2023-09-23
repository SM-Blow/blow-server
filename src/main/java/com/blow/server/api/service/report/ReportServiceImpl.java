package com.blow.server.api.service.report;

import com.blow.server.api.common.exception.ReportException;
import com.blow.server.api.common.message.ExceptionMessage;
import com.blow.server.api.dto.report.request.ReportCreateRequestDTO;
import com.blow.server.api.entity.Report;
import com.blow.server.api.entity.ReportStatus;

import com.blow.server.api.repository.ReportRepository;
import com.blow.server.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
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

        val targetUser = userRepository.findById(request.targetUserId())
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessage.NOT_FOUND_USER.getMessage()));

        if (reportUser.getId() == targetUser.getId()){
            throw new ReportException(ExceptionMessage.NOT_REPORT_ME.getMessage(), HttpStatus.BAD_REQUEST);
        }

        val report = reportRepository.findByReportUser_IdAndTargetUser_Id(reportUser.getId(), targetUser.getId());

        if (report.isPresent()){
            isAlreadyReport(report.get());
        }

        val newReport = Report.builder()
                        .reportUser(reportUser)
                        .targetUser(targetUser)
                        .content(request.content())
                        .status(true)
                        .build();

        targetUser.setReportStatus(ReportStatus.PROCEEDING);

        reportRepository.save(newReport);
    }
    public void isAlreadyReport(Report report){
        if (report.isStatus()) {
            throw new ReportException(ExceptionMessage.ALREADY_APPLY_REPORT_USER.getMessage(), HttpStatus.ALREADY_REPORTED);
        }
    }

}
