from flask import Flask,request
from flask_cors import CORS
from pyspark.sql import SparkSession
import time
 
app=Flask(__name__)
CORS(app)
 
# XX年XX月有多少电影
@app.route('/spark/byTime/count/yearMonth', methods=["GET"])
def spark_count_by_year_and_month_movie():
    year = request.args.get("year")
    month = request.args.get("month")
    consume_time = 0
    start_time = time.time()
    res = spark.sql("""select count(*)
            from dw.movie
            where movie.year = {}
            and movie.month = {}""".format(year,month))
    consume_time = time.time() - start_time
    resDict = res.toPandas().to_dict()
    return {"res":resDict, "time":consume_time}


# XX年XX季度有多少电影
@app.route('/spark/byTime/count/yearSeason', methods=["GET"])
def spark_count_by_year_and_season_movie():
    year = request.args.get("year")
    season = request.args.get("season")
    consume_time = 0
    start_time = time.time()
    res = spark.sql("""select count(*)
            from dw.movie
            where movie.year = {}
            and movie.season = {}""".format(year,season))
    consume_time = time.time() - start_time
    resDict = res.toPandas().to_dict()
    return {"res":resDict, "time":consume_time}


# XX年XX月XX日-YY年YY月YY日新增多少电影
@app.route('/spark/byTime/count/yearMonthDay', methods=["GET"])
def spark_count_by_start_and_end_movie():
    start = request.args.get("start")
    end = request.args.get("end")
    consume_time = 0
    start_time = time.time()
    res = spark.sql("""select count(*)
            from dw.movie
            where movie.release_time > '{}'
            and movie.release_time <= '{}'""".format(start,end))
    consume_time = time.time() - start_time
    resDict = res.toPandas().to_dict()
    return {"res":resDict, "time":consume_time}


################################################################################


# XX电影共有多少版本
@app.route('/spark/byMovieName/count/format', methods=["GET"])
def spark_count_by_movie_name_format():
    movieName = request.args.get("movieName")
    consume_time = 0
    start_time = time.time()
    res = spark.sql("""select format_num
            from dw.movie
            where movie.title = '{}'""".format(movieName))
    consume_time = time.time() - start_time
    resDict = res.toPandas().to_dict()
    return {"res":resDict, "time":consume_time}


################################################################################


# XX导演共有多少电影
@app.route('/spark/byDirector/count/movie', methods=["GET"])
def spark_count_by_director_name_movie():
    directorName = request.args.get("directorName")
    consume_time = 0
    start_time = time.time()
    res = spark.sql("""select count(*)
            from dw.movie
            where movie.directors rlike '{}'""".format(directorName))
    consume_time = time.time() - start_time
    resDict = res.toPandas().to_dict()
    return {"res":resDict, "time":consume_time}


# XX导演导演过哪些电影
@app.route('/spark/byDirector/list/movie', methods=["GET"])
def spark_list_by_director_name_movie():
    directorName = request.args.get("directorName")
    consume_time = 0
    start_time = time.time()
    res = spark.sql("""select title
            from dw.movie
            where movie.directors rlike '{}'""".format(directorName))
    consume_time = time.time() - start_time
    resDict = res.toPandas().to_dict()
    return {"res":resDict, "time":consume_time}


################################################################################
    
 
# XX演员主演或非主演多少电影
@app.route('/spark/byActor/count/movie', methods=["GET"])
def spark_count_by_actor_movie():
    actorName = request.args.get("actorName")
    isStarring = request.args.get("isStarring")
    if isStarring == True: 
        consume_time = 0
        start_time = time.time()
        res = spark.sql("""select count(*)
                from dw.movie
                where movie.starring_actors rlike '{}'""".format(actorName))
        consume_time = time.time() - start_time
        resDict = res.toPandas().to_dict()
        return {"res":resDict, "time":consume_time}
    else:
        consume_time = 0
        start_time = time.time()
        res = spark.sql("""select count(*)
                from dw.movie
                where movie.actors rlike '{}'
                and movie.starring_actors not rlike '{}'""".format(actorName,actorName))
        consume_time = time.time() - start_time
        resDict = res.toPandas().to_dict()
        return {"res":resDict, "time":consume_time}


# XX演员主演或非主演过哪些电影
@app.route('/spark/byActor/list/movie', methods=["GET"])
def spark_list_by_actor_movie():
    actorName = request.args.get("actorName")
    isStarring = request.args.get("isStarring")
    if isStarring == True: 
        consume_time = 0
        start_time = time.time()
        res = spark.sql("""select title
                from dw.movie
                where movie.starring_actors rlike '{}'""".format(actorName))
        consume_time = time.time() - start_time
        resDict = res.toPandas().to_dict()
        return {"res":resDict, "time":consume_time}
    else:
        consume_time = 0
        start_time = time.time()
        res = spark.sql("""select title
                from dw.movie
                where movie.actors rlike '{}'
                and movie.starring_actors not rlike '{}'""".format(actorName,actorName))
        consume_time = time.time() - start_time
        resDict = res.toPandas().to_dict()
        return {"res":resDict, "time":consume_time}


################################################################################


# 合作次数超过XX的演员和演员有哪些
@app.route('/spark/byColla/count/acac', methods=["GET"])
def spark_list_by_colla_acac():
    collaTime = request.args.get("collaTime")
    consume_time = 0
    start_time = time.time()
    res = spark.sql("""select actor_name_1, actor_name_2
            from dw.actor_actor
            where collaborate_count > {}""".format(collaTime))
    consume_time = time.time() - start_time
    resDict = res.toPandas().to_dict()
    return {"res":resDict, "time":consume_time}


# 合作次数超过XX的导演和导演有哪些
@app.route('/spark/byColla/count/colla/dcdc', methods=["GET"])
def spark_list_by_colla_colla_dcdc():
    collaTime = request.args.get("collaTime")
    consume_time = 0
    start_time = time.time()
    res = spark.sql("""select director_name_1, director_name_2
            from dw.director_director
            where collaborate_count > {}""".format(collaTime))
    consume_time = time.time() - start_time
    resDict = res.toPandas().to_dict()
    return {"res":resDict, "time":consume_time}


# 合作次数超过XX的导演和演员有哪些
@app.route('/spark/byColla/count/colla/dcac', methods=["GET"])
def spark_list_by_colla_colla_dcac():
    collaTime = request.args.get("collaTime")
    consume_time = 0
    start_time = time.time()
    res = spark.sql("""select director_name, actor_name
            from dw.actor_director
            where collaborate_count > {}""".format(collaTime))
    consume_time = time.time() - start_time
    resDict = res.toPandas().to_dict()
    return {"res":resDict, "time":consume_time}


################################################################################


# 用户评分XX分以上的电影有哪些
@app.route('/spark/byComment/score', methods=["GET"])
def spark_list_by_comment_score():
    score = request.args.get("score")
    consume_time = 0
    start_time = time.time()
    res = spark.sql("""select movie.title
            from dw.movie
            where score > {}
            limit 100""".format(score))
    consume_time = time.time() - start_time
    resDict = res.toPandas().to_dict()
    return {"res":resDict, "time":consume_time}


# 用户评价中正面评价占比XX%以上的电影有哪些
@app.route('/spark/byComment/rate', methods=["GET"])
def spark_list_by_comment_rate():
    rate = request.args.get("rate")
    consume_time = 0
    start_time = time.time()
    res = spark.sql("""select movie.title
            from dw.movie
            where score > {}
            limit 100""".format(rate))
    consume_time = time.time() - start_time
    resDict = res.toPandas().to_dict()
    return {"res":resDict, "time":consume_time}


################################################################################


# 如果要拍一部XXX类型的电影，最受关注（评论最多）的演员组合（2人，3人等）是什么
@app.route('/spark/combine/list/type', methods=["GET"])
def spark_combine_list_type():
    type = request.args.get("type")
    consume_time = 0
    start_time = time.time()
    res = spark.sql("""select actors,comment_num
            from dw.movie
            where type = '{}'
            order by comment_num desc
            limit 1;""".format(type))
    consume_time = time.time() - start_time
    resDict = res.toPandas().to_dict()
    return {"actors":resDict["actors"][0],"time":consume_time}


# XX导演拍的XX类型的差评率大于XX的电影有哪些
@app.route('/spark/combine/list/rate', methods=["GET"])
def spark_combine_list_rate():
    directorName = request.args.get("directorName")
    type = request.args.get("type")
    rate = request.args.get("rate")
    consume_time = 0
    start_time = time.time()
    res = spark.sql("""select distinct movie.title
            from dw.movie
            where directors rlike '{}'
            and type = '{}'
            and score < {}""".format(directorName,type,rate))
    consume_time = time.time() - start_time
    resDict = res.toPandas().to_dict()
    return {"res":resDict, "time":consume_time}


# XX年-XX年间XX演员主演的评分大于XX的电影有哪些
@app.route('/spark/combine/list/score', methods=["GET"])
def spark_combine_list_score():
    start = request.args.get("start")
    end = request.args.get("end")
    name = request.args.get("name")
    score = request.args.get("score")
    consume_time = 0
    start_time = time.time()
    res = spark.sql("""select distinct movie.title
            from dw.movie
            where year >= {}
            and year <= {}
            and starring_actors rlike '{}'
            and score > {}""".format(start,end,name,score))
    consume_time = time.time() - start_time
    resDict = res.toPandas().to_dict()
    return {"res":resDict, "time":consume_time}
 



if __name__ == "__main__":
    spark = SparkSession\
    .builder\
    .config("hive.metastore.uris","thrift://localhost:9083")\
    .config("spark.driver.memory",'2G')\
    .config("spark.executor.memory",'2G')\
    .enableHiveSupport()\
    .appName("test")\
    .master("local[*]")\
    .getOrCreate()
    app.run("0.0.0.0", "8100")