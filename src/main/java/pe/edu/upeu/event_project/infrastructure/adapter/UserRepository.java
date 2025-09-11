package pe.edu.upeu.event_project.infrastructure.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.event_project.infrastructure.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByEmail(String email);
}
