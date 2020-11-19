#!/bin/bash
# Does it have execute permissions? Try a chmod +x scriptname and then ./scriptname
clear

# Stop server Docker container, if running
echo -e "\nStoping ThinkIT Server Docker container..."
docker stop thinkit.server

# Run ThinkIT Server container
echo -e "\nStarting ThinkIT Server Docker container..."
docker run \
  --name=thinkit.server \
  --rm \
  -d \
  -p 80:80 \
  --env-file ./config/prod.env \
  thinkit.server

# --env-file ./config/prod.env
# Injects this file into our Docker container
# It imports the environment varibles contained in prod.env

# The -d flag is for detaching the docker app
# and put it in the background.
# -d --> non blocking