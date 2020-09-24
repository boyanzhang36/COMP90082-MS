
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



### Deployment

In order to deploy the app, configure your router settings such that the server exposes the api port on the internet. To find the port, view the docker-compose yml port mapping.  

Edit the ```~/{projectname}/frontend/lib/util/serverDetails.dart``` file to reflect your public ip address.  

## API

While the api will be automatically deployed via docker-compose, the following details are about how to rebuild the api code, should changes be required.  

The code is built using Maven and the documentation for building can be found in the api folder ```README.md``` file.  

Essentially ```mvn clean install``` will build a ```.war``` file in the target directory. To deploy, move this file into ```~/{projectname}/backend/tomcat/webapps``` then rebuild the docker containers with:  

```
docker-compose stop
docker-compose --build
docker-compose up -d
```

One important note is that the Java version Maven uses must match with the Java version Tomcat uses, the following configuration works without issues:  

```
Apache Maven 3.6.3
Java version: 1.8
```

For more detailed instructions, please see the documentations from previous two teams and repeat those steps (while making sure to use the updated version of Java and Maven), and obviously no need to set up Tomcat because docker will handle that.  

## Frontend

The frontend project is located in the ```~/{projectname}/frontend``` directory, please refer to your platform specific guide on how to set up flutter on your system and import a project.  

The following config was used successfully to build flutter code:  

```
Flutter 1.17.1 • channel stable • https://github.com/flutter/flutter.git
Engine • revision 6bc433c6b6
Tools • Dart 2.8.2
```

There are 2 more configurations you will want to change. Firstly, the app points to an ip address and port which is located in a filed called ```~/{projectname}/frontend/lib/util/serverDetails.dart``` - change the relevant fields to your server.  

Secondly, the fcm token setup should be changed over to your account. Set up a firebase account, then follow the steps required to activate firebase for your app. This is a very involved process which included generating key files from firebase and changing configurations for both the android and ios subdirectories of the project. Consult the official documentation.  

## Authors

Wenkai Huang - wenkai.huang@hotmail.com  
Callum Dowling - callum.dowling@gmail.com

## Acknowledgments

Previous MH-Bilby team who built docker and part of frontend.  

Previous medical-secretary team who built the genie script and api.
