package cn.edu.tongji.ycy.hivebackend.Service;


package com.spark.sparksqlhive.demo

import org.apache.spark.sql.SparkSession


object SparkSql_hive {

        case class Record(key: Int, value: String)
  def sparkSql_hive_demo(): Unit = {
        val warehouseLocation = "/warehouse"
        val spark = SparkSession
        .builder()
        .master("local[*]")
        .config("hive.metastore.uris","thrift://master:9083")
        .config("fs.defaultFS","hdfs://master:9000")
        .appName("Spark Hive Example")
        .config("spark.sql.warehouse.dir", warehouseLocation)
        .enableHiveSupport()
        .getOrCreate()
        spark.sparkContext.setLogLevel("INFO")

        //基本操作
        //在db_hive库中创建一个新表tableName
        spark.sql("CREATE TABLE IF NOT EXISTS db_hive.tableName (key INT, value STRING)")
        //向tableName中加载数据
        spark.sql("LOAD DATA LOCAL INPATH 'test.txt' INTO TABLE db_hive.tableName")
        //查询tableName表数据
        spark.sql("select * from db_hive.tableName").show(100)
        //总条数
        spark.sql("select count(*) from db_hive.tableName").show()

        }

        }
