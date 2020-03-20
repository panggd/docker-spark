import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.DataTypes;

import static org.apache.spark.sql.functions.asc;

public class SparkApplication {
  public static void main(String[] args) {

    SparkSession spark = SparkSession.builder()
            .appName("Attendance by Category")
            .config("spark.master", "local")
            .config("spark.testing.memory", "2147480000")
            .getOrCreate();

    Dataset<Row> data = spark.read()
            .format("csv")
            .option("header", "false")
            .load(args[0])
            .toDF("year", "category", "attendance");

    data = data.withColumn("attendance",
            data.col("attendance")
                    .cast(DataTypes.IntegerType))
            .drop("year");

    data = data.groupBy("category")
            .sum("attendance")
            .orderBy(asc("category"));

    data.show();

    spark.stop();
  }
}
