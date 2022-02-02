# IV1201-recruitment-app

## provide a database, preferably psql
- $ docker pull postgres
- $ docker run --name IV1201psql -p 5432:5432 -e POSTGRES_PASSWORD=root123 --mount src="$(pwd)",target=/host_files,type=bind -d postgres
- put the sql script in the specified mount directory
  - psql -U postgres
  - CREATE DATABASE recruitment;
  - \c recruitment;
  - \i /host_files/imdb_dump.sql;
## use adminer for database management, perhaps adminer
- docker run --name adminer --link IV1201psql:db -p 8050:8080 -d adminer
  

# development
- 

# production
- heroku has postgres & spring support
