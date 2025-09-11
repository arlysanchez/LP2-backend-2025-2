package pe.edu.upeu.event_project.infrastructure.adapter.mapper;

import org.mapstruct.Mapper;
import pe.edu.upeu.event_project.domain.model.User;
import pe.edu.upeu.event_project.infrastructure.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toDomainModel(UserEntity entity);
    UserEntity toEntity(User user);
}
