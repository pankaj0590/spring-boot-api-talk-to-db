#!/usr/bin/env bash

# Below script will look for task defnition template in /var/lib/jenkins/template.json path
# The template.json file is provided in this repository

dockerRepo=`aws ecr describe-repositories --repository-name dev-spring-boot-api-talk-to-db --region us-east-1 | grep repositoryUri | cut -d "\"" -f 4`
dockerTag=`aws ecr list-images --repository-name dev-spring-boot-api-talk-to-db --region us-east-1 | grep imageTag | head -n 1 | cut -d "\"" -f 4`
sed -e "s;DOCKER_IMAGE_NAME;${repositoryURI};g" scripts/template.json > taskDefinition.json
aws ecs create-cluster --cluster-name EC2-cluster
aws ecs register-task-definition --family spring-boot-api-talk-to-db --cli-input-json file://taskDefinition.json --region us-east-1
aws ecs create-service --cluster EC2-cluster --service-name spring-boot-hello-world-service --task-definition spring-boot-hello-world --desired-count 1
revision=`aws ecs describe-task-definition --task-definition spring-boot-api-talk-to-db --region us-east-1 | grep "revision" | tr -s " " | cut -d " " -f 3`
aws ecs update-service --cluster EC2-cluster --service spring-boot-api-talk-to-db-service --task-definition spring-boot-api-talk-to-db:${revision} --desired-count 1
