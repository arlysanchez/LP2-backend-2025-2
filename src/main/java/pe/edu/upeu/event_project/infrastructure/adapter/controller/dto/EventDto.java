package pe.edu.upeu.event_project.infrastructure.adapter.controller.dto;

import java.time.LocalDateTime;

public record EventDto() {
    public record EventRequest(String name, String description,
                               LocalDateTime eventDate,
                               String location, double budget) {}


    public record EventResponse(
            Long id, String name, String description,
            LocalDateTime eventDate, String location,
            double budget, UserDto.UserResponse organizer) {}


}
