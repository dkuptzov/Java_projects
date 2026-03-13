package ru.tictactoe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

/*
curl -X POST "http://localhost:8080/game" \
  -H "Content-Type: application/json" \
  -d '{ "userId": "8da8cec4-7192-4011-917c-4e8612d92b34"
  }'

curl -X POST "http://localhost:8080/game/0dc2e461-2cf0-4ecd-bffe-cffabd20cea0" \
  -H "Content-Type: application/json" \
  -d '{
    "userId": "8da8cec4-7192-4011-917c-4e8612d92b34",
    "board": [
      [1,1,0],
      [2,0,0],
      [2,0,0]
    ]
  }'
 */

/*
+++ USER/auth_json +++

curl -X POST "http://localhost:8080/user/auth_json" \
  -H "Content-Type: application/json" \
  -d '{ "userName": "user2",
        "password": "summer19"
  }'

+++ USER/signup_json +++

curl -X POST "http://localhost:8080/user/signup_json" \
  -H "Content-Type: application/json" \
  -d '{ "userName": "user5",
        "password": "karamba"
  }'

+++ USER/auth_base64 +++

curl -X POST "http://localhost:8080/user/auth_base64" \
  -H "Authorization: Basic dXNlcjI6c3VtbWVyMTk="

+++ USER/signup_base64 +++

curl -X POST "http://localhost:8080/user/signup_base64" \
  -H "Signup: Basic dXNlcjEwMDpkZmprZ2hka3Vncmg="

==============================
user3 terminator
dXNlcjM6dGVybWluYXRvcg==

user2 summer19
dXNlcjI6c3VtbWVyMTk=

user100:dfjkghdkugrh
dXNlcjEwMDpkZmprZ2hka3Vncmg=
==============================

+++ USER/FIND +++

curl -X GET "http://localhost:8080/user/find/e8ca8646-123b-4f2d-8250-e142873290b3" \
  -H "Content-Type: application/json" \
  -H "Authorization: Basic dXNlcjM6dGVybWluYXRvcg=="

+++ PVP/FIND +++

curl -X GET "http://localhost:8080/pvp/find" \
  -H "Content-Type: application/json" \
  -H "Authorization: Basic dXNlcjI6c3VtbWVyMTk="

+++ PVP/NEW +++

curl -X POST "http://localhost:8080/pvp/new" \
  -H "Content-Type: application/json" \
  -H "Authorization: Basic dXNlcjM6dGVybWluYXRvcg=="

+++ PVP/JOIN +++

curl -X POST "http://localhost:8080/pvp/9ad50033-7215-4dfe-bfec-d42bfbcd613e/join" \
  -H "Content-Type: application/json" \
  -H "Authorization: Basic dXNlcjI6c3VtbWVyMTk="

+++ PVP/STATUS +++

curl -X GET "http://localhost:8080/pvp/538f8740-36ad-49af-8292-55901d1dc466/status" \
  -H "Content-Type: application/json" \
  -H "Authorization: Basic dXNlcjM6dGVybWluYXRvcg=="

+++ PVP/CURRENT_GAME +++

curl -X GET "http://localhost:8080/pvp/current_game" \
  -H "Content-Type: application/json" \
  -H "Authorization: Basic dXNlcjM6dGVybWluYXRvcg=="

+++ PVP +++

  curl -X POST "http://localhost:8080/pvp/9ad50033-7215-4dfe-bfec-d42bfbcd613e" \
  -H "Content-Type: application/json" \
  -H "Authorization: Basic dXNlcjI6c3VtbWVyMTk=" \
    -d '{
    "board": [
      [2,0,0],
      [0,0,0],
      [0,0,0]
    ]
  }'

  curl -X POST "http://localhost:8080/pvp/18eb51f1-bfc2-40ac-a726-fbdd4f9e2b7b" \
  -H "Content-Type: application/json" \
  -H "Authorization: Basic dXNlcjM6dGVybWluYXRvcg==" \
    -d '{
    "board": [
      [0,0,0],
      [0,0,0],
      [0,0,0]
    ]
  }'
 */

/*
curl -X POST "http://localhost:8080/game/960b6b94-fb10-4a74-a2a3-c24bc88c4ea4" \
  -H "Content-Type: application/json" \
  -d '{
    "board": [
      [0,0,0],
      [0,0,0],
      [0,0,0]
    ]
  }'

curl -X POST "http://localhost:8080/game/960b6b94-fb10-4a74-a2a3-c24bc88c4ea4" \
  -H "Content-Type: application/json" \
  -d '{
    "board": [
      [0,0,0],
      [0,0,0],
      [0,0,0]
    ]
  }'
 */