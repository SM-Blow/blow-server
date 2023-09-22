package com.blow.server.api.controller;

import com.blow.server.api.common.ApiResponse;
import com.blow.server.api.common.message.ResponseMessage;
import com.blow.server.api.dto.event.request.CreateEventRequestDTO;
import com.blow.server.api.entity.BlowUserDetails;
import com.blow.server.api.service.event.EventService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/event")
public class EventController {

    private final EventService eventService;

    @PostMapping("")
    public ResponseEntity<ApiResponse> createEvent(
                                                   @RequestBody CreateEventRequestDTO request) {
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



}
