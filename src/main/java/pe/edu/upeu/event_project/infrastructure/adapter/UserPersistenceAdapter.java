package pe.edu.upeu.event_project.infrastructure.adapter;

import org.springframework.stereotype.Component;
import pe.edu.upeu.event_project.domain.model.User;
import pe.edu.upeu.event_project.domain.port.on.UserRepositoryPort;
import pe.edu.upeu.event_project.infrastructure.adapter.mapper.UserMapper;
import pe.edu.upeu.event_project.infrastructure.entity.UserEntity;

import java.util.Optional;

@Component
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserPersistenceAdapter(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity savedEntity = userRepository.save(userEntity);
        return userMapper.toDomainModel(savedEntity);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id).map(userMapper::toDomainModel);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email).map(userMapper::toDomainModel);

    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
