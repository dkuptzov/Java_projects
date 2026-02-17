### 📁 Общая архитектура (слои)
- ─ domain/          # Бизнес-логика (не зависит от Spring)
- ─ datasource/      # Хранение данных (репозитории, entity, мапперы)
- ─ web/            # REST API (контроллеры, DTO, мапперы)
- ─ di/             # Dependency Injection (Spring конфигурация)
- ─ MainApplication.java

### 📦 Пакет domain — Бизнес-логика

Назначение: Содержит чистую бизнес-логику игры, не зависящую от фреймворков и баз данных.

- domain/
- ├── model/              # Модели предметной области
- │   ├── Field.java          # Enum: EMPTY(0), PLAYER_X(1), PLAYER_O(2)
- │   ├── TicTacToe.java     # Игровое поле 3x3 (int[][]), валидация, копирование
- │   ├── CurrentGame.java   # Текущая игра: UUID, board, currentPlayer, humanPlayer, aiPlayer
- │   └── MoveResult.java    # Результат хода AI: score, row, col
- │
- └── service/           # Интерфейсы и реализация сервисов
- ├── TicTacToeService.java     # Главный интерфейс игры (getAIMove, validateGame, isGameFinished)
- ├── MiniMaxService.java       # Интерфейс алгоритма Minimax
- ├── GameResultService.java    # Интерфейс проверки окончания игры
- ├── ValidationService.java    # Интерфейс валидации хода
- │
- ├── MiniMax.java             # Реализация Minimax (AI)
- ├── GameResult.java          # Реализация проверки победы/ничьей
- ├── ValidationService.java   # Реализация валидации хода (сравнение досок)
- └── TicTacToeImpl.java      # Реализация главного сервиса (связывает AI, валидацию, GameResult)

### 📁 Пакет datasource — Хранение данных

Назначение: Работа с хранилищем данных (in-memory коллекции).

- datasource/
- ├── model/              # Entity для хранения
- │   └── GameEntity.java     # Сущность игры: UUID, board[][], currentPlayer, humanPlayer, aiPlayer
- │
- ├── mapper/             # Преобразование domain <-> datasource
- │   └── GameMapper.java     # toEntity(CurrentGame) -> GameEntity
- │                          # toDomain(GameEntity) -> CurrentGame
- │
- └── repository/         # Репозитории (доступ к данным)
- ├── GameRepository.java     # Интерфейс: saveGame, getGame, containsGame, removeGame
- │
- └── impl/                   # Реализации
- └── InMemoryGameRepository.java  # ConcurrentHashMap<UUID, GameEntity>

### 🌐 Пакет web — REST API

Назначение: Взаимодействие с клиентами (HTTP-запросы/ответы).

- web/
- ├── model/              # DTO (Data Transfer Objects)
- │   ├── GameStateRequest.java   # Запрос: board[][]
- │   ├── GameStateResponse.java  # Ответ: gameId, board[][], yourPlayer, currentPlayer, gameStatus
- │   ├── GameStatus.java         # Enum: IN_PROGRESS, PLAYER_X_WON, PLAYER_O_WON, DRAW
- │   └── ErrorResponse.java      # Ошибка: message
- │
- ├── mapper/             # Преобразование domain <-> web
- │   └── GameMapper.java     # toDomain(request, gameId, currentPlayer, humanPlayer, aiPlayer) -> CurrentGame
- │                          # toResponse(game, gameStatus) -> GameStateResponse
- │
- └── controller/         # REST контроллеры
- └── TicTacToeController.java
- ├── POST /game              # Создание новой игры
- └── POST /game/{gameId}     # Ход игрока + ответ AI

### 🔧 Пакет di — Dependency Injection

Назначение: Конфигурация Spring-бинов (ручное связывание).

- di/
- ├── AppConfig.java          # @Configuration: создание всех бинов
- │                           # - MiniMaxService
- │                           # - GameResultService
- │                           # - ValidationService
- │                           # - TicTacToeService
- │                           # - GameRepository
- │                           # - TicTacToeController
- │
- └── MainApplication.java    # @SpringBootApplication, main()

### 🚀 Точка входа
- MainApplication.java
- ├── @SpringBootApplication
- ├── main() -> SpringApplication.run(...)
- └── Расположение: корневой пакет (или di/)

### 📊 Схема взаимодействия слоев
- [Клиент]
- ↓ (HTTP JSON)
- [web.controller] → принимает GameStateRequest
- ↓
- [web.mapper] → преобразует Request → CurrentGame
- ↓
- [domain.service] → бизнес-логика (валидация, AI, проверка победы)
- ↓
- [datasource.repository] → сохранить/загрузить GameEntity
- ↓
- [datasource.mapper] → преобразует GameEntity ↔ CurrentGame
- ↓
- [web.mapper] → преобразует CurrentGame → GameStateResponse
- ↓
- [Клиент] ← JSON ответ

### 📝 Порядок работы приложения

- Создание игры → POST /game → сервер создает CurrentGame (UUID, пустая доска, random роли)
- Клиент получает gameId, yourPlayer, currentPlayer
- Если ход AI → сервер сразу делает первый ход AI
- Ход человека → POST /game/{id} с новой доской
- Валидация → проверка, что изменена 1 клетка и ходил правильный игрок
- Ход AI → MiniMax.nextStep() выбирает лучший ход
- Проверка победы → GameResult.theEnd()
- Сохранение → InMemoryGameRepository.saveGame()
- Ответ → GameStateResponse с обновленной доской

### Создание игры
curl -X POST "http://localhost:8080/game" \
-H "Content-Type: application/json"


### Играем
curl -X POST "http://localhost:8080/game/769a839e-c44c-4944-9db2-20ada04b0e0f" \
-H "Content-Type: application/json" \
-d '{
"board": [
[1,0,1],
[2,0,0],
[0,2,2]
]
}'