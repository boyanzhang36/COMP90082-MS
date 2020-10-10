#!/bin/bash

echo "Hello"

# Load variablese
# Image Name
# Account ID 
# Region
# Cluster 
# Service 

echo Image name is: $apiImageName
echo AWS Account ID is: $accountId
echo AWS Region is: $awsRegion
echo Cluster name is: $cluster
echo Service name is: $service

repo=${accountId}.dkr.ecr.${awsRegion}.amazonaws.com/apt:latest
echo ECR Repo is: $repo

# # Build the executable .jar 
mvn package

# # # Build the image 
docker build -t $apiImageName . 
# # # Tag the image to the ECR Repo
docker tag ${apiImageName}:latest $repo

# # # # Push the image to the ECR Repo
docker push $repo

# # # # Trigger the ECS service to update 

aws ecs update-service --cluster $cluster --service $service --force-new-deployment

echo "Goodbye"