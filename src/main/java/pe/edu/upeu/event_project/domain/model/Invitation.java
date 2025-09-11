package pe.edu.upeu.event_project.domain.model;

import pe.edu.upeu.event_project.infrastructure.entity.Role;

public class Invitation {
    private Long id;
    private String guestEmail;
    private String status; // "PENDING", "ACCEPTED", "REJECTED"
    private Role role;  // "COLLABORATOR", "GUEST"
    private Event event;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
