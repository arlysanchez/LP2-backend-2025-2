package pe.edu.upeu.event_project.app.usecase;

import org.springframework.stereotype.Service;
import pe.edu.upeu.event_project.domain.exception.UserAlreadyExistsException;
import pe.edu.upeu.event_project.domain.model.User;
import pe.edu.upeu.event_project.domain.port.in.UserUseCase;
import pe.edu.upeu.event_project.domain.port.on.UserRepositoryPort;

import java.util.Optional;

@Service
public class UserUseCaseImpl implements UserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public UserUseCaseImpl(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User registerUser(User user) {
        if (userRepositoryPort.findByEmail(user.getEmail()).isPresent()){
           throw new UserAlreadyExistsException("Ya existe un usuario con el email"+user.getEmail());
        }
       return userRepositoryPort.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepositoryPort.findById(id);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepositoryPort.findByEmail(email);
    }

    @Override
    public void deleteById(Long id) {
        userRepositoryPort.deleteById(id);
    }
}
