#!/bin/bash
echo "=== Starting Java Application ==="
echo "Current time: $(date)"
echo "Java version:"
java -version
echo ""

# Запускаем Java приложение
java -jar /app/app.jar "$@"
