# ft-test-log

## Introduction
This api allows to ft-test-factory to log using rest api automated test result in a mysql database.

## Build 

### Build command
This is a build command when you develop
```
sh rebuild.sh
```

If you need to build db and insert test data at the end of the build use the following command. But you need to un comment the line accordingly
```
sh rebuild.sh [IPADDRESS-SQL-SERVER]
```

### Build clean command
This command will remove all existing images and build 
```
sh remove-all-docker.sh
```

In this build we provide for devlopment purpose a docker for phpmyadmin and a mysql db. Do not use them for production deployment.

## Service insertTestLog
* When it is POST method you need to pass all the paramters and pass the string "NULL" to force the db null. 
* In GET method you need minimally to pass "projectId" and the db null are automatically handled.

You should use GET first. If you have too large url then you should use POST.

## Security
* The system is base on ip whilisting. Update the table `ip_white_list` accordingly. Note if the ip `0.0.0.0` is present in that table. It will allow call from anywhere.
* The db creation is based on a tocken. When the databse is created the ENV VARIABLE `TOCKEN` is update to avoid an accidental db reset. $

## Production deployment
Before building ensure the ENV parameter are what you need in the /ROOT-PROJECT/api/Dockerfile

The system will not overwrite your db if already exists. There 2 levels of security to ensure it. But if your db is not created you can use the `/ROOT-PROJECT/api/src/setupdb.php`.

First go under /ROOT-PROJECT/api
```
docker build -t fanaticaltest/ft-test-log-php .
```

If you want to tag with a specific version like X.Y.Z
```
docker build -t fanaticaltest/ft-test-log-php:X.Y.Z .
```

