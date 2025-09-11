package pe.edu.upeu.event_project.infrastructure.adapter.controller.dto;

import pe.edu.upeu.event_project.infrastructure.entity.Role;

public record UserDto() {


    //DTO para la peticion del registro
    public record UserRequest(String name, String email,
                              String password, Role role){}

    //DTO de respuesta
    public record UserResponse(Long id,String name, String email,
                               Role role ){}


}
