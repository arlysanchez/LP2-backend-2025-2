package pe.edu.upeu.event_project.infrastructure.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "events")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDateTime eventDate;

    @Column(length = 200)
    private String location;

    private double budget;

    @Column(length = 255)
    private String imageUrl;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id", nullable = false)
    private UserEntity organizer;

    @OneToMany(mappedBy = "event",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskEntity> task;

    @OneToMany(mappedBy = "event",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvitationEntity> invitations;


    public EventEntity() {
    }

    public EventEntity(Long id, String name, String description, LocalDateTime eventDate, String location, double budget, String imageUrl, UserEntity organizer, List<TaskEntity> task, List<InvitationEntity> invitations) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.eventDate = eventDate;
        this.location = location;
        this.budget = budget;
        this.imageUrl = imageUrl;
        this.organizer = organizer;
        this.task = task;
        this.invitations = invitations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public UserEntity getOrganizer() {
        return organizer;
    }

    public void setOrganizer(UserEntity organizer) {
        this.organizer = organizer;
    }

    public List<TaskEntity> getTask() {
        return task;
    }

    public void setTask(List<TaskEntity> task) {
        this.task = task;
    }

    public List<InvitationEntity> getInvitations() {
        return invitations;
    }

    public void setInvitations(List<InvitationEntity> invitations) {
        this.invitations = invitations;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
