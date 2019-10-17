# Medical heal app - team bilby

This repoistory contains backend components (database, api) inherited from the previous team, our team has made significant modifications and bugfixes to the backend and made a frontend codebase from scratch. The individual components have also been packaged up using docker containers. The benefits of this approach is the following: changes made to the docker compose yml file will be version controlled and the nuances of a server setup can be ignored because each setup will use the same file. Overall, it is far less timeconsuming to spin up a server than going through the original method of installing each component.  

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

### Installing
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

Spin up server- this will install and run each container, it may take some time to download images. Please ensure that the ports specified in docker-compose.yml are not being used up by other processes on your server.

```
docker-compose -up d
```

Now we need to seed the database server, this needs to be done manually, as we want the database to remain persistant from now on.
First we need to find what the hash of the running container for the database is. On each system, the container id will be different, if you restart the container id will change. 
```
docker ps
```
Take note of the first few numbers of the id

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

In order to deploy to the app, configure your router settings such that the server exposes the api port on the internet.
Edit the ~/Development/medical-heal/frontend/lib/util/serverDetails.dart file to reflect your public ip address.

Then rebuild the frontend using the following guide.

## Building & deploying frontend

## Building & deploying api

## Versioning

## Authors

## License

## Acknowledgments
