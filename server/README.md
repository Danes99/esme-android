# Start MongoDB

```bash
/Users/stauner/mongodb/bin/mongod --dbpath=/Users/stauner/mongodb-data
```

# Install the Heroku CLI
Download and install the Heroku CLI.

If you haven't already, log in to your Heroku account and follow the prompts to create a new SSH public key.

```bash
heroku login
````

# Create a new Git repository
Initialize a git repository in a new or existing directory

```bash
cd my-project/
git init
heroku git:remote -a node-thinkit
````

# Deploy your application
Commit your code to the repository and deploy it to Heroku using Git.

```bash
git add .
git commit -am "make it better"
git push heroku master
```

# Managing config vars
The heroku config commands of the Heroku CLI makes it easy to manage your app’s config vars.

## View current config var values
```bash
heroku config
GITHUB_USERNAME: joesmith
OTHER_VAR:    production
```

## View current config var values
```bash
heroku config:get GITHUB_USERNAME
joesmith
````

## Set a config var
```bash
heroku config:set GITHUB_USERNAME=joesmith
Adding config vars and restarting myapp... done, v12
GITHUB_USERNAME: joesmith
```

## Remove a config var
```bash
heroku config:unset GITHUB_USERNAME
Unsetting GITHUB_USERNAME and restarting myapp... done, v13
```