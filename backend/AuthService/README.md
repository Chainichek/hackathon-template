# AuthService

Может выдавать токены и регистрировать новых пользователей

## Особенности
Сваггер открывается по эндпоинту /api/swagger-ui/index.html

User релизован [так](src/main/java/com/munsun/auth_service/models).
В JWT вкладывается [такая нагрузка](src/main/java/com/munsun/auth_service/services/impl/providers/impl/DefaultJwtProvider.java)

## Запуск
Перейти в корень и собрать проект:
```
mvn clean package
```
или без прогона тестов
```
mvn clean package -DskipTests=true
```
Предварительно запустив docker-daemon:
```
docker compose up