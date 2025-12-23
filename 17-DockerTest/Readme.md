### Cборка Docker с запуском java программы
## Минимальный проект

# Структура проекта
java-docker-project/
├── Dockerfile          # инструкции для сборки образа
├── docker-compose.yml  # опционально, для удобства
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── app/
│                   └── Main.java
├── scripts/
│   └── entrypoint.sh   # скрипт, который будет запускаться
├── build.gradle.kts    # или pom.xml для Maven
└── README.md

# Шаг 1: Создайте Dockerfile
# Шаг 2: Создайте скрипт запуска
mkdir scripts
touch scripts/entrypoint.sh
chmod +x scripts/entrypoint.sh
В итоговом проекте него нет

# Шаг 3: Настройте сборку JAR
Создаем build.gradle.kts

# Шаг 4: Собераем проект
chmod +x gradlew
./gradlew build

# Шаг 5: Соберите Docker-образ
docker build -t my-java-app .
my-java-app - название Docker-образа

# Команды Docker
- Просмотр собранных образов: docker images
- Запуск контейтера: docker run my-java-app
- Запуск контейтера с аргументами: docker run my-java-app arg1 arg2 arg3
- Запуск контейтера с пробросом портов (если приложение использует порт 8080): docker run -p 8080:8080 my-java-app
- Пересобрать Docker образ: docker build -t my-java-app .
- Удалить Docker образ: docker rmi my-java-app, docker rmi 01f35fdc2131
- Просмотр всех контейнеров: docker ps -a
- Удалить контейнер по ID: docker rm c1e7b2a8d320
- Остановить контейнер, если он запущен: docker stop c1e7b2a8d320
