# privatenanny_api

Privatenanny_api is a SpringBoot api

## Installation

install PostgreSQL version 12.7 --> https://www.enterprisedb.com/downloads/postgres-postgresql-downloads

create privatenanny database in postgres : 

```bash

CREATE DATABASE privatenanny
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'French_France.1252'
    LC_CTYPE = 'French_France.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
```

clone project in your preferred editor

for dev edit [application.yml](https://github.com/Dam1Dam/privatenanny_api/blob/master/src/main/resources/application.yml), change action : prod to  action : dev
edit  [application-dev.yml](https://github.com/Dam1Dam/privatenanny_api/blob/master/src/main/resources/application-dev.yml), change username and password

CRUD documentation : 

[api  documentation](http://localhost/swagger-ui.html#/) (local)

[api  documentation](https://privatenanny.herokuapp.com/swagger-ui.html#/) (base dev test)


build, run and enjoy !

## Usage
