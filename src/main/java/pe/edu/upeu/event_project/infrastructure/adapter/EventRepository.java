package pe.edu.upeu.event_project.infrastructure.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upeu.event_project.infrastructure.entity.EventEntity;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
    /* Busca todos los eventos organizados por un usuario específico.
    List<EventEntity> findByOrganizerId(Long organizerId);

    @Query("SELECT e FROM EventEntity e LEFT JOIN FETCH e.tasks WHERE e.id = :id")
    Optional<EventEntity> findByIdWithTasks(@Param("id") Long id);

    @Query("SELECT e FROM EventEntity e LEFT JOIN FETCH e.invitations WHERE e.id = :id")
    Optional<EventEntity> findByIdWithInvitations(@Param("id") Long id);
*/

}
