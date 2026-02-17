package ru.tictactoe.datasource.repository.impl;

import ru.tictactoe.datasource.mapper.GameMapper;
import ru.tictactoe.datasource.model.GameEntity;
import ru.tictactoe.datasource.repository.GameRepository;
import ru.tictactoe.domain.model.CurrentGame;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryGameRepository implements GameRepository {
    private final ConcurrentHashMap<UUID, GameEntity> storage = new ConcurrentHashMap<>();
    private final GameMapper mapper = new GameMapper();

    @Override
    public void saveGame(CurrentGame game) {
        GameEntity entity = mapper.toEntity(game);
        storage.put(entity.getGameUUID(), entity);;
    }

    @Override
    public CurrentGame getGame(UUID gameUUID) {
        GameEntity entity = storage.get(gameUUID);
        return (entity != null) ? mapper.toDomain(entity) : null;
    }

    @Override
    public void removeGame(UUID gameUUId) {
        storage.remove(gameUUId);
    }
}
