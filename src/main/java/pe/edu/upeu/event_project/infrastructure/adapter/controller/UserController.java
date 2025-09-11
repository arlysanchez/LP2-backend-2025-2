package pe.edu.upeu.event_project.infrastructure.adapter.controller;


import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.event_project.domain.model.User;
import pe.edu.upeu.event_project.domain.port.in.UserUseCase;
import pe.edu.upeu.event_project.infrastructure.adapter.controller.dto.UserDto;
import pe.edu.upeu.event_project.infrastructure.entity.Role;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserUseCase userUseCase;

    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @PostMapping("/register")
   public ResponseEntity<UserDto.UserResponse> registerUser(@RequestBody UserDto.UserRequest userRequest){
      //sent data
       User u = new User();
       u.setFull_name(userRequest.name());
       u.setEmail(userRequest.email());
       u.setPassword(userRequest.password());
       u.setRole(userRequest.role()!=null ? userRequest.role(): Role.ORGANIZER);

       User registerUser = userUseCase.registerUser(u);

       //response service
       UserDto.UserResponse response = new UserDto.UserResponse(
               registerUser.getId(),
               registerUser.getFull_name(),
               registerUser.getEmail(),
               registerUser.getRole()
       );

      return new ResponseEntity<>(response, HttpStatus.CREATED);


   }
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userUseCase.deleteById(id);
   return ResponseEntity.noContent().build();
   }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto.UserResponse> getUserById(@PathVariable Long id) {
        return userUseCase.getUserById(id)
                .map(user -> ResponseEntity.ok(mapToUserResponse(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Método de ayuda para el mapeo
    private UserDto.UserResponse mapToUserResponse(User user) {
        return new UserDto.UserResponse(
                user.getId(),
                user.getFull_name(),
                user.getEmail(),
                user.getRole()
        );
    }


}
