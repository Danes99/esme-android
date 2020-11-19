#!/bin/bash
# Does it have execute permissions? Try a chmod +x scriptname and then ./scriptname
clear

# Stop server Docker container, if running
echo -e "\nStoping ThinkIT Server Docker container..."
docker stop thinkit.server