package ru.tictactoe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "ru/tictactoe/di",
        "ru/tictactoe/domain",
        "ru/tictactoe/web",
        "ru/tictactoe/datasource"
})

public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

/*
curl -X POST "http://localhost:8080/game" \
  -H "Content-Type: application/json"
 */

/*
curl -X POST "http://localhost:8080/game/cbf80cc7-1c96-4760-b98d-7e90dfb75bb2" \
  -H "Content-Type: application/json" \
  -d '{
    "board": [
      [1,1,2],
      [0,2,0],
      [1,2,2]
    ]
  }'
 */