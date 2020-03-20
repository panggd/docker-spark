# docker-spark

## Overview
This is a learner project to understand how to ingest a semi-structured data and query with Spark.

1. Run the init-spark script to deploy a docker container of Hadoop cluster server and Spark
2. Build the Java based Spark application to a Jar file
3. Docker cp the Spark Jar file and CSV dataset to Hadoop + Spark container
4. Run Spark Jar to process CSV dataset
5. Read results

## Tech stack
- Docker
- Hadoop
- Spark
- Java
- Gradle

## data
This folder consists of a CSV dataset that describes the total attendance group by medical institutions and year.

## spark
This folder consists of a Spark application that will process the CSV dataset to return the total attendance group by medical institutions.

## init-spark shell script
This is a script that will git clone the Spark docker GitHub project, deploy a docker container of a Hadoop cluster server and Spark.

## Prerequsites

### Download and install Docker. Follow the below guides.
https://docs.docker.com/install


## How to run

### Start your docker daemon
This is really depend on your OS. For my case, it is just starting the Docker app.

### Deploy Hadoop cluster server and Spark
This will deploy the docker container holding a Hadoop cluster server and Spark.
```bash
./init-spark.sh
```

### Build the Spark application
Use your favorite IDE and build the jar in the spark folder.

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
# Get into the Hadoop cluster server
docker exec -it <spark_server_container_id> bash

# This is a series of command to remove the input, output folders
# Create a new input folder in HDFS
# Copy the dataset to the HDFS input folder
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