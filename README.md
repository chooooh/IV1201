# IV1201-recruitment-app

provide a database, preferably psql
- $ docker pull postgres
- $ docker run --name testpostgres -e POSTGRES_PASSWORD=example --mount src="$(pwd)",target=/host_files,type=bind -d postgres
- put the sql script in the specified mount directory
  - psql -U postgres
  - CREATE DATABASE DATABASENAME;
  - \c DATABASENAME;
  - \i /host_files/imdb_dump.sql;
use adminer for database management, perhaps adminer
- docker run --name testadminer --link testpostgres:db -p 8050:8080 -d adminer
  

# development
- 

# production
- heroku has postgres & spring support
