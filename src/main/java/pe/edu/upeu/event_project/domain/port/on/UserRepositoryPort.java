package pe.edu.upeu.event_project.domain.port.on;

import pe.edu.upeu.event_project.domain.model.User;

import java.util.Optional;

public interface UserRepositoryPort {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    void deleteById(Long id);
}
