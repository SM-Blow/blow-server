package com.blow.server.api.controller;

import com.blow.server.api.common.ApiResponse;
import com.blow.server.api.common.message.ResponseMessage;
import com.blow.server.api.dto.event.request.CreateEventRequestDTO;
import com.blow.server.api.entity.BlowUserDetails;
import com.blow.server.api.service.event.EventService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/event")
public class EventController {

    private final EventService eventService;

    @PostMapping("")
    public ResponseEntity<ApiResponse> createEvent(@Valid @RequestBody CreateEventRequestDTO request) {
        eventService.createEvent(request);
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_CREATE_EVENT.getMessage(), null));
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse> getEvents() {
        val response =  eventService.getEvents();
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_GET_EVENTS.getMessage(), response));
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<ApiResponse> getEventDetail(@PathVariable Long eventId) {
        val response =  eventService.getEventDetail(eventId);
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_GET_EVENTS.getMessage(), response));
    }

    @PostMapping("/{eventId}/apply")
    public ResponseEntity<ApiResponse> applyEvent(@AuthenticationPrincipal BlowUserDetails userDetails,
                                                  @PathVariable Long eventId) {
        val userId = userDetails.getId();
        eventService.applyEvent(userId, eventId);
        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.SUCCESS_APPLY_EVENT.getMessage(), null));
    }

    @GetMapping("/my")
    public ResponseEntity<ApiResponse> getMyApplyEventList(@AuthenticationPrincipal BlowUserDetails userDetails) {
        val userId = userDetails.getId();
        val response = eventService.getMyApplyEventList(userId);

        return ResponseEntity.ok(ApiResponse.success(ResponseMessage.GET_MY_EVENT.getMessage(), response));
    }


}
