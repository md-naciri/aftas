#!/bin/bash

# Wait for MySQL to be ready
until nc -z -v -w30 mysql 3306
do
  echo "Waiting for MySQL..."
  sleep 1
done

# Start Spring Boot application
java -jar /app/app.jar