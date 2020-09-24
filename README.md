
# Medical Secretary App - COMP90082 2020 S2 Team Kookaburra

Team Kookoburra's Contribution has been an database administration tool, so priveliged administrators for Medical Secretary, can alter the application on a very fine-grained level. 

It's purpose is essentially to allow easier, more user-friendly alteration of the application, without the indirect genie_script. 

From the root, our contribution can be seen in both tool-api and tool-ui: 

1. Tool-Api: A java-driven Spring Boot RESTful API, which receives requests on http://localhost:8081 at the moment, for development purposes. 

2. Tool-UI: A REACT application that renders our database, and allows the user to easily interact with it via the Tool-API. It current lives on http://localhost:8081. 

## Backend

### Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. Production deployment is a consideration for Sprint 2. 

### Prerequisites

Preferably linux system, the following configuration has been tested, though other configs will most likely work with minimal change.  

```
git version 2.21.0
Ubuntu 18.04
Docker version 19.03.8, build afacb8b
docker-compose version 1.25.5, build 8a1c60f6
```

It is possible to run this on a mac or windows version of docker.  

The following will assume a linux server.  

### Install

Installing prerequisites:  

```
Follow your platform specific install for docker compose and docker
```

Getting the repo:  

```
git clone {remote url}
```

## Spinning up Database

Navigate to backend directory:  

```
cd {projectname}/tool-api/
```

Spin up the Medsec development database. 

```
docker-compose up -d
```

Now we need to seed the database server, this needs to be done manually, as we want the database to remain persistent from now on.  

First we need to find what the hash of the running container for the database is. On each system, the container id will be different. If you restart the container, the id will change.  

```
docker ps
```

Take note of the first few numbers of the container id.  

Next we need to actually seed the database.  

```
cd db/seed/
docker cp db_with_data.sql {containerid}:/db_with_data.sql
```

The file is inside the running container, we now execute a bash inside the container.  

```
docker exec -it {containerid} bash
```

Seed the db (while in container bash)  

```
mariadb -u root -p < db_with_data.sql
```

We just need to type in the password, which by default is root. (These settings are specified in docker-compose.yml)  

Can also check database we create  

```
mariadb -u root -p
```

Enter the default password: root (These settings are specified in docker-compose.yml)  

Show databases:  

```
SHOW DATABASES;
```

And you can check the data in the database:  

```
USE medsec;
SHOW TABLES;
SELECT * FROM User;
```

Exit MariaDB:  

```
exit
```

Exit docker bash:  

```
exit
```
### Spinning up tool API

Return to root of tool/api

```
cd ../..
```

Spin up the api : 

```
./mvnw spring-boot:run
```

### Spinning up tool UI

Navigate to the tool-ui folder

```
cd ../tool-ui
```

Install the npm dependencies 

```
npm i
```

Spin up the ui

```
npm run start
```

## Authors

Christian Gioia - christiang101@gmail.com  


