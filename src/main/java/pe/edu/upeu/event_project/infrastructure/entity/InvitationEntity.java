package pe.edu.upeu.event_project.infrastructure.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "invitations")
public class InvitationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String guestEmail;

    @Column(nullable = false, length = 50)
    private String status; // "PENDING", "ACCEPTED", "REJECTED"

    @Column(nullable = false, length = 50)
    private Role role;  // "COLLABORATOR", "GUEST"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private EventEntity event;

    public InvitationEntity() {
    }

    public InvitationEntity(Long id, String guestEmail, String status, Role role, EventEntity event) {
        this.id = id;
        this.guestEmail = guestEmail;
        this.status = status;
        this.role = role;
        this.event = event;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }
}
