package ru.tictactoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tictactoe.repository.entity.Pvp;
import ru.tictactoe.web.model.GameStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PVPRepository extends JpaRepository<Pvp, UUID> {
    List<Pvp> findByStatus(GameStatus status);
    List<Pvp> findByUser1Id(UUID userId);
    List<Pvp> findByUser2Id(UUID userId);
    Optional<Pvp> findByIdAndUser1Id(UUID gameId, UUID userId);
    Optional<Pvp> findByIdAndUser2Id(UUID gameId, UUID userId);
}
