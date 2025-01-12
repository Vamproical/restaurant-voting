Voting system for deciding where to have lunch
===============================

- Stack: [JDK 17](http://jdk.java.net/17/), Spring Boot 3.0, Lombok, H2, Caffeine Cache, SpringDoc OpenApi 2.1,
  Mapstruct
- Run: `mvn spring-boot:run` in root directory.

-----------------------------------------------------
[REST API documentation](http://localhost:8080/)  
Credentials:

```
User:  user@yandex.ru / password
Admin: admin@gmail.com / admin
Guest: guest@gmail.com / guest
```

## Technical requirement

-------------------------------------------------------------
***Build a voting system for deciding where to have lunch.***

2 types of users: admin and regular users

+ Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price).
+ Menu changes each day (admins do the updates).
+ Users can vote for a restaurant they want to have lunch at today.
+ Only one vote counted per user.
+ If user votes again the same day:
    + If it is before 11:00 we assume that he changed his mind;
    + If it is after 11:00 then it is too late, vote can't be change.
+ Each restaurant provides a new menu each day.