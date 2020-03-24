# docker-spark

## Overview
This is a learner project to understand how to ingest a semi-structured data and query with Spark.

1. Run the init-spark script to deploy a docker container of Hadoop cluster server and Spark
2. Build the Java based Spark application to a Jar file
3. Docker cp the Jar file and CSV dataset to Spark container
4. Run Jar to process CSV dataset
5. Read results

## Tech stack
- Docker
- Spark
- Java
- Gradle

## data
This folder consists of a CSV dataset that describes the total attendance group by medical institutions and year.

## spark
This folder consists of a Spark application that will process the CSV dataset to return the total attendance group by medical institutions.

## init-spark shell script
This is a script that will git clone the Spark docker GitHub project, deploy a docker container of Spark.

## Prerequsites

### Download and install Docker. Follow the below guides.
https://docs.docker.com/install


## How to run

### Start your docker daemon
This is really depend on your OS. For my case, it is just starting the Docker app.

### Deploy Spark container
This will deploy the docker container holding Spark.
```bash
./init-spark.sh
```

### Build the Spark application
Use your favorite IDE and build the jar in the spark folder.
```bash
# go to the output jar folder
zip -d spark.jar META-INF/*.RSA META-INF/*.DSA META-INF/*.SF
```

### Copy the Jar and dataset into the Hadoop + Spark container
```bash
# Go to data folder
docker cp hospital-and-outpatient-attendances.csv \
<spark_server_container_id>:hospital-and-outpatient-attendances.csv

# Go to spark folder
docker cp spark.jar <spark_server_container_id>:spark.jar
```

### Process the dataset and enjoy the output results
```bash
# Get into the Spark container
docker exec -it <spark_server_container_id> bash

# Process the dataset
java -cp spark.jar SparkApplication hospital-and-outpatient-attendances.csv
```

## Housekeeping
Here are some housekeeping tips if you are on a low memory resource machine like me.

```bash
# This is to have a clean state of your docker environment
docker stop $(docker ps -a -q) && \
docker system prune -a
```

## TODO
1. Create and integrate a REST API
3. Extract the output result to the REST API