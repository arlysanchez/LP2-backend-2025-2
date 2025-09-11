package pe.edu.upeu.event_project.domain.port.in;

import pe.edu.upeu.event_project.domain.model.User;

import java.util.Optional;

public interface UserUseCase {
User registerUser(User user);
Optional<User> getUserById(Long id);
Optional<User> getByEmail(String email);
void deleteById(Long id);

}
