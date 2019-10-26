# Medical heal app - team bilby

This repoistory contains backend components (database, api) inherited from the previous team, our team has made significant modifications and bugfixes to the backend and made a frontend codebase from scratch. The individual components have also been packaged up using docker containers. The benefits of this approach is the following: changes made to the docker compose yml file will be version controlled and the nuances of a server setup can be ignored because each setup will use the same file. Overall, it is far less timeconsuming to spin up a server than going through the original method of installing each component.  

The original documentation is located in the docs directory. This includes the api docuemntation as well as the original build configuration for the api and database.

## Backend

# Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.  

### Prerequisites

Preferably linux system, the following configuration has been tested, though other configs will most likely work with minimal change
```
git version 2.20.1
Ubuntu 19.04
docker version 19.03.1, build 74b1e89
docker-compose version 1.21.0, build unknown
```
It is possible to run this on a mac or windows version of docker, though windows is not recommended - we have included a windows version of the docker-compose yml, if this is absolutely required.     
The following will assume a linux server.

### Install
Installing prerequisites
```
Follow your platform specific install for docker compose and docker
```
getting the repo

```
git clone {remote url}
```

Navigate to backend directory

```
cd medical-heal/backend/
```
Optionally change the clinic name in the /medical-heal/backend/.env file to name the clinics appropriately

Spin up server- this will install and run each container, it may take some time to download images. Please ensure that the ports specified in docker-compose.yml are not being used up by other processes on your server.


```
docker-compose -up d
```

Now we need to seed the database server, this needs to be done manually, as we want the database to remain persistant from now on.
First we need to find what the hash of the running container for the database is. On each system, the container id will be different, if you restart the container id will change. 
```
docker ps
```
Take note of the first few numbers of the container id

```

Next we need to actually seed the database
```
cd db/seed/
docker cp db_with_data.sql {containerid}:/db_with_data.sql
```

The file is inside the running container, we now execute a bash inside the container

```
docker exec -it {container id} bash 
```

Seed the db ( while in container bash)
```
mariadb -u root -p < db_with_data.sql
```

We just need to type in the password, which by default is root.

Confirm setup by typing in localhost/adminer.php into a browser, then login using: server:DB, username:root, password:root.
You should be able to see a database called medsec or similar. 

## Deployment

In order to deploy to the app, configure your router settings such that the server exposes the api port on the internet. To find the port, view the docker-compose yml port mapping.
Edit the ~/Development/medical-heal/frontend/lib/util/serverDetails.dart file to reflect your public ip address.


## API

While the api will be automatically deployed via docker-compose, the following details how to rebuild the api code, should changes be required.
The code is built using maven and the documatation for building can be found in the last team's submission docuemnts. 
Essentially mvn clean install will build a .war file in the target directory, to deploy move this file into backend/tomcat/webapps then rebuild the docker containers with 

```
docker-compose --build
```
One important note is that the java version maven uses must match with the java version tomcat uses, the following configuration works without issues:

```
Apache Maven 3.6.0
Java version: 11.0.4
```
For more detailed instructions, please see the documentation from the last team and repeat those steps ( while making sure to use the updated version of java and maven), and obviously no need to setup tomcat because docker will handle that.

## Frontend
The frontend project is located in the /frontend directory, please refer to your platform specific guide on how to setup flutter on your system and import a project. 

The following config was used successfully to build flutter code:

```
Flutter 1.7.8+hotfix.4 • channel stable • https://github.com/flutter/flutter.git
Engine • revision fee001c93f
Tools • Dart 2.4.0

```
There are 2 more configurations you will want to change. Firstly the app points to an ip address and port which is located in a filed called frontend/lib/util/serverDetails.dart - change the relevent fields to your server.

Secondly, the fcm token setup should be changed over to your account. Set up a firebase account, then follow the steps required to activate firebase for your app. This is a very involved process which included generating key files from firebase and changing configurations for both the andriod and ios subdirectories of the project. Consult offical docuentation. 

## Authors
Callum Dowling - callum.dowling@gmail.com

## Acknowledgments

Previous medical-secretary team who built the genie script and api. 
