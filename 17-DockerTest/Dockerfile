# Многоэтапная сборка (основана на примере из Adoptium[citation:10])
FROM eclipse-temurin:25-jdk AS jre-builder

# Создаём кастомную среду выполнения. Добавьте ВСЕ нужные модули.
RUN $JAVA_HOME/bin/jlink \
    --add-modules java.base,java.logging \
    --strip-debug \
    --no-man-pages \
    --no-header-files \
    --compress=2 \
    --output /javaruntime

# Финальный образ использует Debian slim (glibc) вместо Alpine (musl)
FROM debian:bookworm-slim

ENV JAVA_HOME=/opt/java/openjdk
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# Копируем среду выполнения из первого этапа
COPY --from=jre-builder /javaruntime $JAVA_HOME

# Копируем и запускаем приложение
WORKDIR /app
COPY build/libs/myapp.jar app.jar
CMD ["java", "-jar", "app.jar"]