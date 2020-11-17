#!/bin/bash
# Does it have execute permissions? Try a chmod +x scriptname and then ./scriptname
clear

# Moving to server directory
cd `dirname "$0"`
cd ../server

# Does package-lock.json exist ?
FILE=./package-lock.json
if test -f "$FILE"; then
    echo "$FILE exists."
    # The npm ci command, helps provide faster, 
    #reliable, reproducible builds for production environments.
    npm ci
else
  echo "$FILE does not exist."
  npm i
fi

# Copy compiled React.js application (build) folder to the server
git add .
git commit -m "Deploy server to production: $currentDate"
git push heroku master