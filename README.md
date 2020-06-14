
# Medical Secretary App - COMP90082 2020 S1 Team 04

This repoistory contains backend components (database, api) inherited from the previous team, our team has made significant modifications and bugfixes to the backend and made a frontend codebase from scratch. The individual components have also been packaged up using docker containers. The benefits of this approach is the following: changes made to the docker compose yml file will be version controlled and the nuances of a server setup can be ignored because each setup will use the same file. Overall, it is far less timeconsuming to spin up a server than going through the original method of installing each component.  

The original documentation is located in the docs directory. This includes the api docuemntation as well as the original build configuration for the api and database.

## Backend

### Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.  

### Prerequisites

Preferably linux system, the following configuration has been tested, though other configs will most likely work with minimal change.  

```
git version 2.21.0
Ubuntu 18.04
Docker version 19.03.8, build afacb8b
docker-compose version 1.25.5, build 8a1c60f6
```

It is possible to run this on a mac or windows version of docker, though windows is not recommended - we have included a windows version of the docker-compose yml, if this is absolutely required.  

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

Navigate to backend directory:  

```
cd {project name}/backend/
```

Optionally change the clinic name in the ```/{project name}/backend/.env``` file to name the clinics appropriately.  

Spin up server use the following command:  

```
docker-compose up -d
```

This will install and run each container, it may take some time to download images. Please ensure that the ports specified in docker-compose.yml are not being used up by other processes on your server.  

Now we need to seed the database server, this needs to be done manually, as we want the database to remain persistant from now on.  

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
docker exec -it {container id} bash
```

Seed the db (while in container bash)  

```
mariadb -u root -p < db_with_data.sql
```

We just need to type in the password, which by default is root.  

Can also check database we create  

```
mariadb -u root -p
```

Enter the default password: root  

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

Confirm setup by typing in ```localhost/adminer.php``` into a browser, then login using:  
server: DB  
username: root  
password: root  

You should be able to see a database named medsec.  

### Deployment

In order to deploy the app, configure your router settings such that the server exposes the api port on the internet. To find the port, view the docker-compose yml port mapping.  

Edit the ```~/{project name}/frontend/lib/util/serverDetails.dart``` file to reflect your public ip address.  

## API

While the api will be automatically deployed via docker-compose, the following details is about how to rebuild the api code, should changes be required.  

The code is built using maven and the documatation for building can be found in the last team's submission documents.  

Essentially ```mvn clean install``` will build a ```.war``` file in the target directory, to deploy move this file into backend/tomcat/webapps then rebuild the docker containers with  

```
docker-compose down
docker-compose --build
docker-compose up -d
```

One important note is that the java version maven uses must match with the java version tomcat uses, the following configuration works without issues:  

```
Apache Maven 3.6.3
Java version: 1.8
```

For more detailed instructions, please see the documentation from the previous two teams and repeat those steps (while making sure to use the updated version of java and maven), and obviously no need to setup tomcat because docker will handle that.  

## Frontend

The frontend project is located in the /frontend directory, please refer to your platform specific guide on how to setup flutter on your system and import a project.  

The following config was used successfully to build flutter code:  

```
Flutter 1.17.1 • channel stable • https://github.com/flutter/flutter.git
Engine • revision 6bc433c6b6
Tools • Dart 2.8.2
```

There are 2 more configurations you will want to change. Firstly the app points to an ip address and port which is located in a filed called frontend/lib/util/serverDetails.dart - change the relevent fields to your server.  

Secondly, the fcm token setup should be changed over to your account. Set up a firebase account, then follow the steps required to activate firebase for your app. This is a very involved process which included generating key files from firebase and changing configurations for both the andriod and ios subdirectories of the project. Consult offical docuentation.  

## Authors

Wenkai Huang - wenkai.huang@hotmail.com  
Callum Dowling - callum.dowling@gmail.com

## Acknowledgments

Previous medical-secretary team who built the genie script and api.