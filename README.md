# IV1201-recruitment-app

## Provide a database, preferably psql
- $ docker pull postgres
- $ docker run --name IV1201psql -p 5432:5432 -e POSTGRES_PASSWORD=root123 --mount src="$(pwd)",target=/host_files,type=bind -d postgres
- put the sql script in the specified mount directory
  - psql -U postgres
  - CREATE DATABASE recruitment;
  - \c recruitment;
  - \i /host_files/existing-database.sql;

## use adminer for database management
- docker run --name adminer --link IV1201psql:db -p 8050:8080 -d adminer

## add database from sql file
- add heroku postgres as addon
- heroku pg:psql --app YOUR_APP_NAME_HERE < existing-database.sql

# production
- heroku has postgres & spring support

# dependencies
- Spring Boot
- Spring Devtools
- Thymeleaf
- Validation (javax.validation)
- JPA
- Spring Security
- Lombok
