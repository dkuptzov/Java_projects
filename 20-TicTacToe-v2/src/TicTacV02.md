Задание 1. Добавление базы данных

Опиши подключение к базе данных PostgreSQL в application.properties.
Избавься от класса-хранилища.
Добавь специальные аннотации для классов, которые необходимо сохранять в базу данных.
Используй у репозиториев в качестве родителя CrudRepository.

## Опиши подключение к базе данных PostgreSQL в application.properties.

В папке src/resources создал файл application.properties с таким содержанием

### Основные настройки подключения
spring.datasource.url=jdbc:postgresql://localhost:5432/tictactoe
spring.datasource.username=spring
spring.datasource.password=123
spring.datasource.driver-class-name=org.postgresql.Driver

### Настройки Hibernate (авто-генерация таблиц)
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

В PGAdmin создал нового пользователя spring, создал новую базу tictactoe 
- TODO - надо еще таблицы будет создать

В gradle надо добавить -
implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
runtimeOnly ("org.postgresql:postgresql")

- URL: https://javarush.com/groups/posts/2579-dobavljaem-bd-k-restful-servisu-na-spring-boot-chastjh-1

## Избавься от класса-хранилища.

Создал пакет repository/entity для классов, работающих с таблицами.

## Добавь специальные аннотации для классов, которые необходимо сохранять в базу данных.

Создал Users, Games.
В games добавил геттеры и сеттеры, с users пока не знаю, надо или нет.
Пытаюсь разобраться как работает this.fieldGame = new ObjectMapper().writeValueAsString(matrix);
для сохранения поля в бд в виде строки.

Как работает @ManyToOne — простыми словами
Этот код создает связь между таблицами в базе данных на уровне JPA (Java Persistence API).

## Используй у репозиториев в качестве родителя CrudRepository.

- URL: https://javarush.com/quests/lectures/ru.javarush.java.spring.lecture.level05.lecture04

Добавил интерфейсы UsersRepository и GamesRepository для работы с таблицами.
Названия методов должны соответствовать конвенциям: findBy, countBy, deleteBy.

## Исправления

- Добавил маппер для CurrentGame и Games.

## Задание 2. Добавление авторизации
## Добавь пользователей, у которых будет UUID, логин, пароль.

- Добавил новый контроллер UserController. Новый сервис UserService. Для добавления пользователей.
- Добавил CreateUserRequest, CreateUserResponse - запрос и ответ.
- Добавил бины.
- Сейчас можно создать пользователя отправив такой запрос:

        curl -X POST "http://localhost:8080/user" \
        -H "Content-Type: application/json" \
        -d '{ "userName": "user2",
        "password": "summer19"
        }'

- Создать пользователей с одинаковыми именами нельзя.

## Реализуй поддержку пользователей на всех слоях.

- При создании новой игры. public ResponseEntity<GameStateResponse> startNewGame(@RequestBody StartGameRequest request)
- @RequestBody StartGameRequest request - для обработки userId в запросе, создал в web/model/StartGameRequest
- Получается просто для получения userId
- Запрос на создание новой игры:

        curl -X POST "http://localhost:8080/game" \
        -H "Content-Type: application/json" \
        -d '{ "userId": "8da8cec4-7192-4011-917c-4e8612d92b34"
        }'

- В TicTacToeController
- небольшой недочет, надо подумать так оставить или ...

      if (gameStatus == 0) {
          // Здесь третий параметр был previousGame.getCurrentPlayer() и передавался не тот игрок,
          // пока заменил на previousGame.getAiPlayer(), так как его ход
          // работает
          MoveResult aiMoveResult = ticTacToeService.getAIMove(newBoard, previousGame.getAiPlayer(), previousGame.getHumanPlayer(), previousGame.getAiPlayer());

- Добавил удаление игры после завершения.

## Создай модель SignUpRequest, у которой будет логин и пароль.

- Здесь надо было создать модель регистрации пользователя, это уже было сделано,
- просто пришлось переименовать CreateUserRequest в SignUpRequest

## Создай сервис авторизации, который использует UserService:
## метод регистрации, который принимает SignUpRequest и возвращает факт успешной регистрации;

- Готово.

## Создай сервис авторизации, который использует UserService:
## метод авторизации, который принимает в заголовке логин и пароль в виде base64(login:password) и возвращает UUID пользователя.

- В UserController добавил

      @PostMapping("/signup_base64")
      public ResponseEntity<UUID> createUser(@RequestHeader("Authorization") String authHeader)

- В UserService добавил метод для декодирования bse64 и получения UUID из базы

      public UUID authBase64(String authorizationHeader)

## Создай контроллер авторизации, у которого будут следующие endpoint'ы:
## для регистрации пользователя;
## для авторизации пользователя.

- UserController и UserService создание и авторизация пользователей, создание через
- json и base64. Авторизация только через base64

## Создай класс AuthFilter, сделай наследование от GenericFilterBean и реализуй метод doFilter:
## Провалидируй логин и пароль.
## Если валидация прошла успешно, то выполни запрос.
## Если валидация прошла с ошибкой, то добавь в ответ 401 код и не выполняй запрос.

- GenericFilterBean — это абстрактный класс в Spring, который упрощает создание фильтров (Filters) в веб-приложениях. Фильтры перехватывают HTTP-запросы до того, как они попадут в контроллер, и ответы после их обработки. 
- Если сделать: @Component abstract class AuthFilter extends GenericFilterBean, тогда все url будут защищены.
- Если добавить public FilterRegistrationBean<AuthFilter> authFilterRegistration(UserService userService),
- тогда можно будет указать нужные url: registrationBean.addUrlPatterns("/game/*", "/user/*");
- AuthFilter относиться к web, поэтому он должен быть в web/filter

## Создай Spring Configuration, где:
## Опиши Bean для получения SecurityFilterChain.
## Разреши доступ без авторизации к endpoint'ам регистрации и авторизации.
## Для всех остальных endpoint'ов должна требоваться авторизация.
## Воспользуйся AuthFilter в качестве фильтра.

- SecurityFilterChain — это компонент Spring Security, который определяет, какие фильтры и для каких URL применять для обеспечения безопасности.
- Как я понимаю это авто замена предыдущему пункту с AuthFilter, но 
- Воспользуйся AuthFilter в качестве фильтра...
- В build

## Endpoint
- 📌 Endpoint (эндпоинт) — это конкретный URL, по которому клиент может обращаться к вашему API для выполнения определенного действия.
- ✅ ПРОСТОЕ ОПРЕДЕЛЕНИЕ
- Endpoint = Адрес + HTTP метод + Назначение

## Задание 3. Добавление логики игры между двумя игроками
### Добавь состояния для текущей игры:
### ожидание игроков;
### ход игрока с UUID;
### ничья;
### победа игрока с UUID.
### Добавь информацию о значках, которыми будут ходить пользователи, в текущую игру.
### Улучши алгоритм определения окончания игры с использованием состояний.
### Добавь endpoint для создания новой игры с пользователем или компьютером.
### Добавь endpoint для получения доступных текущих игр.
### Добавь endpoint для присоединения пользователя к игре.
### Улучши endpoint обновления текущей игры с учетом игры с пользователем или компьютером.
### Добавь endpoint для получения текущей игры.
### Добавь endpoint для получения информации о пользователе по UUID.

- Получается тут лучше написать всю логику почти с нуля.


## Проблемы
- нет сообщений об ошибочном логине и пароле
- нет сообщений если base64 не правильный

## Обработка метода, если непонятная ошибка

    //    @PostMapping("/new_pvp")
    //    public ResponseEntity<PVPStateResponse> startNewPVPGame(@RequestBody PVPStateRequest request) {
    //        System.out.println("🔥 МЕТОД ВЫЗВАН! 🔥");
    //        System.out.println("userId1: " + request.getUserId1());
    //
    //        try {
    //            Pvp newGame = gameService.createNewGamePVP(request.getUserId1());
    //            System.out.println("newGame created: " + newGame.getId());
    //
    //            Pvp savedGame = gameService.savePVPGame(newGame);
    //            System.out.println("savedGame: " + savedGame.getId());
    //
    //            PVPStateResponse response = new PVPStateResponse(
    //                    savedGame.getId(),
    //                    savedGame.getMatrix(),
    //                    GameStatus.IN_PROGRESS,
    //                    savedGame.getCurrentPlayer()
    //            );
    //            System.out.println("Response created");
    //
    //            return ResponseEntity.ok(response);
    //
    //        } catch (Exception e) {
    //            System.out.println("❌ ОШИБКА: " + e.getMessage());
    //            e.printStackTrace();
    //            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    //        }
    //    }

## FilterRegistrationBean

    @Bean  // Аннотация Spring, которая говорит, что этот метод создает и возвращает бин (компонент), который будет управляться Spring-контейнером
    public FilterRegistrationBean<AuthFilter> authFilterRegistration(UserService userService) {  // Метод возвращает специальный объект для регистрации фильтров, Spring автоматически внедрит UserService в параметр метода
    
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();  // Создаем объект-регистратор, который будет содержать настройки для фильтра
    
        registrationBean.setFilter(new AuthFilter(userService));  // Создаем экземпляр нашего кастомного фильтра AuthFilter и передаем ему UserService для проверки логинов и паролей, затем устанавливаем этот фильтр в регистратор
    
        registrationBean.addUrlPatterns("/game/*", "/user/*");  // Указываем URL-паттерны, к которым будет применяться фильтр - все запросы, начинающиеся с /game/ и /user/
    
        registrationBean.setOrder(1);  // Устанавливаем порядок выполнения фильтра (чем меньше число, тем выше приоритет) - этот фильтр будет выполняться первым в цепочке фильтров
    
        return registrationBean;  // Возвращаем настроенный регистратор обратно в Spring, который зарегистрирует фильтр и будет применять его ко всем указанным URL
    }

## Кодирование и декодирование

        String originalString = request.getUserName() + ":" + request.getPassword();
        String encodedString = Base64.getEncoder().encodeToString(originalString.getBytes(StandardCharsets.UTF_8));
        System.out.println(encodedString);

        String originalString = "user2:summer19";
        String encodedString = Base64.getEncoder().encodeToString(originalString.getBytes(StandardCharsets.UTF_8));
        System.out.println(encodedString);

- filter исключает из списка пользователей с логинами равными userId

        pvpList.stream().
        filter(pvp -> !pvp.getUser1().getId().equals(userId) &&
        !pvp.getUser2().getId().equals(userId)).map(Pvp::getId).toList()