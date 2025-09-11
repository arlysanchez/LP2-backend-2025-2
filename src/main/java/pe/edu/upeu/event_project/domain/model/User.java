package pe.edu.upeu.event_project.domain.model;

import pe.edu.upeu.event_project.infrastructure.entity.Role;

public class User {
    private Long id;
    private String full_name;
    private String email;
    private String password;
    private Role role;


    public Long getId() { return id;}
    public void setId(Long id) {this.id = id;}
    public String getFull_name() {return full_name;}
    public void setFull_name(String full_name) {this.full_name = full_name;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
