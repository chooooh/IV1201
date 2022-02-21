# IV1201-recruitment-app

## provide a database, preferably psql
- $ docker pull postgres
- $ docker run --name IV1201psql -p 5432:5432 -e POSTGRES_PASSWORD=root123 --mount src="$(pwd)",target=/host_files,type=bind -d postgres
- put the sql script in the specified mount directory
  - psql -U postgres
  - CREATE DATABASE recruitment;
  - \c recruitment;
  - \i /host_files/existing-database.sql;
## use adminer for database management, perhaps adminer
- docker run --name adminer --link IV1201psql:db -p 8050:8080 -d adminer
  

# development
- run using maven with provided spring command
  - ./mvnw spring-boot:run
- or start the application using the main spring boot application, i.e. RecruitmentAppApplication.java
- this project uses Spring Devtools. Therefore, a restart is not required for most changes.


# deployment
- heroku has postgres & spring support
