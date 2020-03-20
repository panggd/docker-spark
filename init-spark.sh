#!/bin/sh
git clone https://github.com/big-data-europe/docker-spark.git
cd docker-spark
docker-compose up -d # -d flag to start as background task