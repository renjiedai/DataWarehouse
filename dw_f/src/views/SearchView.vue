<template>
  <div>
    <ContentField>
      <el-card class="box-card">
        <template #header>
          <div class="card-header" style="background-color: #17C3B2;">
            <span style="font-size: 25px;">查询条件</span>
          </div>
        </template>
        <!-- <div style="margin-bottom:15px">
          <slot style="margin-bottom:5px">请选择使用的数据库：</slot>
          <el-select clearable v-model="db" class="m-2" placeholder="Select" size="large">
            <el-option v-for="item in dbs" :key="item.value" :label="item.label" :value="item.value" />
          </el-select>
        </div> -->
        <div>
          <slot>请确定查询标准：</slot>
          <el-select style="margin-right:10px" v-model="entity" clearable class="m-2" placeholder="Select" size="large">
            <el-option v-for="item in entities" :key="item.value" :label="item.label" :value="item.value" />
          </el-select>
          <el-select v-if="entity === '导演'" v-model="standard" clearable class="m-2" placeholder="Select" size="large">
            <el-option v-for="item in dirstd" :key="item.value" :label="item.label" :value="item.value" />
          </el-select>
          <el-select v-if="entity === '电影'" v-model="standard" clearable class="m-2" placeholder="Select" size="large">
            <el-option v-for="item in moviestd" :key="item.value" :label="item.label" :value="item.value" />
          </el-select>
          <el-select v-else-if="entity === '演员'" v-model="standard" clearable class="m-2" placeholder="Select"
            size="large">
            <el-option v-for="item in actstd" :key="item.value" :label="item.label" :value="item.value" />
          </el-select>
          <el-select v-else-if="entity === '评分'" v-model="standard" clearable class="m-2" placeholder="Select"
            size="large">
            <el-option v-for="item in scorestd" :key="item.value" :label="item.label" :value="item.value" />
          </el-select>

          <div v-if="entity === '电影'" style="margin-top: 20px;">
            <slot> 请输入电影名： </slot>
            <el-input class="m-2" v-model="movie_name" style="width:150px;margin-left: 10px"
              placeholder="Please input name" />
          </div>

          <div v-if="entity === '导演' && standard === '执导'" style="margin-top: 20px;">
            <slot> 请输入导演名： </slot>
            <el-input class="m-2" v-model="dirname" style="width:150px;margin-left: 10px"
              placeholder="Please input name" />
          </div>

          <div v-if="entity === '演员'" style="margin-top: 20px;">
            <slot> 请输入演员名： </slot>
            <el-input class="m-2" v-model="actorname" style="width:150px;margin-left: 10px"
              placeholder="Please input name" />
          </div>

          <div v-if="entity === '日期【年季】'" style="margin-top: 20px;">
            <el-input-number v-model="year" :min="1600" :max="2022" @change="handleChange" />
            <slot> 年 </slot>
            <slot> 第 </slot>
            <el-input-number v-model="season" :min="1" :max="4" @change="handleChange" />
            <slot> 季度 </slot>
          </div>


          <div v-if="entity === '日期【年月】'" class="demo-date-picker" style="margin-top: 20px">
            <div class="container">
              <div class="block">
                <!-- <span class="demonstration">Month</span> -->
                <el-date-picker v-model="ym" type="month" placeholder="Pick a month" />
              </div>
            </div>
          </div>

          <div v-if="entity === '起止年月日'" class="demo-date-picker" style="margin-top: 20px">
            <div class="block">
              <span class="demonstration">起始年月日</span>
              <el-date-picker v-model="ymd1" type="date" placeholder="Pick a day" :size="size" />
            </div>
            <br />
            <div class="block">
              <span class="demonstration">结束年月日</span>
              <el-date-picker v-model="ymd2" type="date" placeholder="Pick a day" :size="size" />
            </div>
          </div>

          <el-input v-if="entity === '评分'" class="m-2" v-model="value" style="width:150px;margin-left: 10px"
            placeholder="Please input value" />

        </div>

        <br />
        <button @click="search()"> 查询 </button>

      </el-card>

      <br />

      <el-card class="box-card">
        <template #header>
          <div class="card-header" style="background-color: #17C3B2;">
            <span style="font-size: 25px;">查询结果</span>
          </div>
        </template>
        <p style="text-align:left; margin-bottom: 20px;">查询结果：{{ result }}</p>
        <p style="text-align:left; margin-bottom: 20px;">查询用时</p>
        <p style="text-align:left; margin-bottom: 20px;">mysql : {{ t_mysql }}</p>
        <p style="text-align:left; margin-bottom: 20px;">hive : {{ t_hive }}</p>
        <p style="text-align:left; margin-bottom: 50px;">neo4j : {{ t_neo4j }}</p>
        <div id="chart1" style="width: 600px;height:400px;"></div>
        <div v-if="entity === '评分'">
          <p style="text-align:left; margin-bottom: 20px;">查询结果详情</p>
          <el-table :data="movies" height="250" style="width: 100%">
            <el-table-column prop="movie" label="Movie" />
          </el-table>
        </div>
      </el-card>
    </ContentField>
  </div>
</template>
  
<script>
import axios from "axios"
import * as echarts from 'echarts'
import ContentField from '@/components/ContentField.vue';

export default {
  components: { ContentField },
  data() {
    return {
      db: "",
      entity: "",
      standard: "",
      value: "",
      t_mysql: "",
      t_hive: "",
      t_neo4j: "",
      year: "",
      season: "",
      dirname: "",
      actorname: "",
      movie_name: "",
      ym: "",
      ymd1: "",
      ymd2: "",
      result: "",
      movies: [],//部分会显示的表格内容
      dbs: [
        {
          value: 'neo4j',
          label: 'neo4j',
        },
        {
          value: 'mysql',
          label: 'mysql',
        },
        {
          value: 'hive',
          label: 'hive',
        }
      ],
      entities: [
        {
          value: '电影',
          label: '电影',
        },
        {
          value: '导演',
          label: '导演',
        },
        {
          value: '演员',
          label: '演员',
        },
        {
          value: '评分',
          label: '评分',
        },
        {
          value: '日期【年月】',
          label: '日期【年月】',
        },
        {
          value: '日期【年季】',
          label: '日期【年季】',
        },
        {
          value: '起止年月日',
          label: '起止年月日',
        },
      ],
      dirstd: [
        {
          value: '执导',
          label: '执导',
        },
      ],
      moviestd: [
        {
          value: '版本数',
          label: '版本数',
        },
      ],
      actstd: [
        {
          value: '主演',
          label: '主演',
        },
        {
          value: '非主演',
          label: '非主演',
        },
        {
          value: '参演',
          label: '参演',
        },
      ],
      scorestd: [
        {
          value: '分数大于等于',
          label: '分数大于等于',
        },
        {
          value: '好评率大于等于',
          label: '好评率大于等于',
        }
      ],
    }
  },
  mounted() {
    this.echartsInit();
  },
  methods: {
    map_helper(ymd) {
      var ret = [];
      ret.push(parseInt(ymd.substr(11, 4)));
      switch (ymd.substr(4, 3)) {
        case 'Jau':
          ret.push(1);
          break;
        case 'Feb':
          ret.push(2);
          break;
        case 'Mar':
          ret.push(3);
          break;
        case 'Apr':
          ret.push(4);
          break;
        case 'May':
          ret.push(5);
          break;
        case 'Jun':
          ret.push(6);
          break;
        case 'Jul':
          ret.push(7);
          break;
        case 'Aug':
          ret.push(8);
          break;
        case 'Sep':
          ret.push(9);
          break;
        case 'Oct':
          ret.push(10);
          break;
        case 'Nov':
          ret.push(11);
          break;
        case 'Dec':
          ret.push(12);
          break;
      }
      ret.push(parseInt(ymd.substr(8, 2))); //day
      return ret;
    },

    search() {
      if (this.entity == "导演" && this.standard == "执导") {
        //mysql
        axios
          .get('http://47.100.205.153:3445/mysql/byDirector/count/movie',
            {
              params: {
                directorName: this.dirname
              }
            })
          .then((res) => {
            this.result = res.data.num;
            this.t_mysql = res.data.time;
            //neo4j
            axios
              .get('http://47.100.205.153:3445/director/dirmovie',
                {
                  params: {
                    directorName: this.dirname
                  }
                })
              .then((res) => {
                console.log(res);
                console.log(res.data);
                this.t_neo4j = parseInt(res.data);
                console.log(this.t_neo4j);

                //hive还没写
                axios
                  .get('http://47.101.153.105:8100/spark/byDirector/count/movie',
                    {
                      params: {
                        directorName: this.dirname
                      }
                    })
                  .then((res) => {
                    console.log(res);

                    this.t_hive = parseInt(res.data.time * 1000);


                    this.drawchart();
                  });
              });
          });
      }
      else if (this.entity == "电影" && this.standard == "版本数") {
        //mysql
        axios
          .get('http://47.100.205.153:3445/mysql/byMovieName/count/format',
            {
              params: {
                movieName: this.movie_name
              }
            })
          .then((res) => {
            console.log(res);
            this.t_mysql = res.data.time;
            this.result = res.data.num;
            //neo4j
            axios
              .get('http://47.100.205.153:3445/movie/byname',
                {
                  params: {
                    name: this.movie_name
                  }
                })
              .then((res) => {
                console.log(res);
                this.t_neo4j = res.data;

                //hive 【hasn't finished】

                axios
                  .get('http://47.101.153.105:8100/spark/byMovieName/count/format',
                    {
                      params: {
                        name: this.movie_name
                      }
                    })
                  .then((res) => {
                    console.log(res);

                    this.t_hive = parseInt(res.data.time * 1000);


                    this.drawchart();
                  });
              });

          });

      }
      else if (this.entity == "演员") {
        if (this.standard == "主演") {
          //mysql
          axios
            .get('http://47.100.205.153:3445/mysql/byActor/count/movie',
              {
                params: {
                  actorName: this.actorname,
                  isStarring: true, //not sure about the meaning of the value
                }
              })
            .then((res) => {
              console.log(res);
              this.t_mysql = res.data.time;
              this.result = res.data.num;

              //neo4j
              axios
                .get('http://47.100.205.153:3445/actor/starmovie',
                  {
                    params: {
                      actorName: this.actorname,
                    }
                  })
                .then((res) => {
                  console.log(res);
                  this.t_neo4j = res.data;

                  //hive
                  axios
                    .get('http://47.101.153.105:8100/spark/byActor/count/movie',
                      {
                        params: {
                          actorName: this.actorname,
                          isStarring: true, //not sure about the meaning of the value
                        }
                      })
                    .then((res) => {
                      console.log(res);

                      this.t_hive = parseInt(res.data.time * 1000);


                      this.drawchart();
                    });
                })
            })

        }
        else if (this.standard == "非主演") {
          //mysql
          axios
            .get('http://47.100.205.153:3445/mysql/byActor/count/movie',
              {
                params: {
                  actorName: this.actorname,
                  isStarring: false,
                }
              })
            .then((res) => {
              console.log(res);
              this.t_mysql = res.data.time;
              this.result = res.data.num;

              //neo4j
              axios
                .get('http://47.100.205.153:3445/actor/actmovie',
                  {
                    params: {
                      actorName: this.actorname,
                    }
                  })
                .then((res) => {
                  console.log(res);
                  this.t_neo4j = res.data;

                  //hive
                  axios
                    .get('http://47.101.153.105:8100/spark/byActor/count/movie',
                      {
                        params: {
                          actorName: this.actorname,
                          isStarring: false, //not sure about the meaning of the value
                        }
                      })
                    .then((res) => {
                      console.log(res);

                      this.t_hive = parseInt(res.data.time * 1000);


                      this.drawchart();
                    });
                })
            })

        }
        else if (this.standard == "参演") {
          //mysql
          var time1, time2, time3, time4;
          var result1, result2;
          axios
            .get('http://47.100.205.153:3445/mysql/byActor/count/movie',
              {
                params: {
                  actorName: this.actorname,
                  isStarring: false,
                }
              })
            .then((res) => {
              console.log(res);
              time1 = res.data.time;
              result1 = res.data.num;
              axios
                .get('http://47.100.205.153:3445/mysql/byActor/count/movie',
                  {
                    params: {
                      actorName: this.actorname,
                      isStarring: true,
                    }
                  })
                .then((res) => {
                  console.log(res);
                  time2 = res.data.time;
                  result2 = res.data.num;
                  this.t_mysql = parseInt(time1) + parseInt(time2);
                  this.result = result1 + result2;

                  //neo4j
                  axios
                    .get('http://47.100.205.153:3445/actor/inmovie',
                      {
                        params: {
                          actorName: this.actorname,
                        }
                      })
                    .then((res) => {
                      console.log(res);
                      this.t_neo4j = res.data;

                      //hive
                      axios
                        .get('http://47.101.153.105:8100/spark/byActor/count/movie',
                          {
                            params: {
                              actorName: this.actorname,
                              isStarring: false, //not sure about the meaning of the value
                            }
                          })
                        .then((res) => {
                          console.log(res);

                          time3 = parseInt(res.data.time * 1000);


                          axios
                            .get('http://47.101.153.105:8100/spark/byActor/count/movie',
                              {
                                params: {
                                  actorName: this.actorname,
                                  isStarring: true, //not sure about the meaning of the value
                                }
                              })
                            .then((res) => {
                              console.log(res);
                              time4 = parseInt(res.data.time * 1000);
                              this.t_hive = time3 + time4


                              this.drawchart();
                            });
                        });
                    })
                })
            })
        }
      }
      else if (this.entity == "评分") {
        if (this.standard == "分数大于等于") {
          //mysql
          axios
            .get('http://47.100.205.153:3445/mysql/byComment/score',
              {
                params: {
                  score: this.value
                }
              })
            .then((res) => {
              console.log(res);
              this.t_mysql = res.data.time;
              this.result = res.data.num;

              var values = res.data.data;
              var fi = [];
              for (var i = 0; i < values.length; i++) {
                var m = { movie: values[i] };
                fi.push(m);
              }
              this.movies = fi;
              console.log(this.movies);
              //neo4j
              axios
                .get('http://47.100.205.153:3445/movie/score',
                  {
                    params: {
                      value: this.value
                    }
                  })
                .then((res) => {
                  console.log(res);
                  this.t_neo4j = res.data;

                  //hive
                  axios
                    .get('http://47.101.153.105:8100/spark/byComment/score',
                      {
                        params: {
                          score: this.value
                        }
                      })
                    .then((res) => {
                      console.log(res);
                      this.t_hive = parseInt(res.data.time * 1000);



                      this.drawchart();
                    });
                });
            });
        }
        else if (this.standard == "好评率大于等于") {
          //mysql
          axios
            .get('http://47.100.205.153:3445/mysql/byComment/rate',
              {
                params: {
                  rate: parseInt(this.value)
                }
              })
            .then((res) => {
              console.log(res);
              this.t_mysql = res.data.time;
              var values = res.data.data;
              var fi = [];
              for (var i = 0; i < values.length; i++) {
                var m = { movie: values[i] };
                fi.push(m);
              }
              this.movies = fi;

              //neo4j
              axios
                .get('http://47.100.205.153:3445/movie/posrate',
                  {
                    params: {
                      value: this.value
                    }
                  })
                .then((res) => {
                  console.log(res);
                  this.t_neo4j = res.data;

                  //hive
                  axios
                    .get('http://47.101.153.105:8100/spark/byComment/rate',
                      {
                        params: {
                          rate: parseInt(this.value)
                        }
                      })
                    .then((res) => {
                      console.log(res);
                      this.t_hive = parseInt(res.data.time * 1000);



                      this.drawchart();
                    });
                });
            });

        }

      }
      else if (this.entity == "日期【年月】") {
        var y = this.ym.getFullYear();
        var m = this.ym.getMonth() + 1;
        //mysql
        axios
          .get('http://47.100.205.153:3445/mysql/byTime/count/yearMonth',
            {
              params: {
                year: y,
                month: m
              }
            })
          .then((res) => {
            console.log(res);
            this.t_mysql = res.data.time;
            this.result = res.data.num;

            //neo4j
            axios
              .get('http://47.100.205.153:3445/movie/ym',
                {
                  params: {
                    year: y,
                    month: m
                  }
                })
              .then((res) => {
                console.log(res);
                this.t_neo4j = res.data;

                //hive

                //hive
                axios
                  .get('http://47.101.153.105:8100/spark/byTime/count/yearMonth',
                    {
                      params: {
                        year: y,
                        month: m
                      }
                    })
                  .then((res) => {
                    console.log(res);
                    this.t_hive = parseInt(res.data.time * 1000);



                    this.drawchart();
                  });
              });


          });

      }
      else if (this.entity == "日期【年季】") {
        //mysql
        axios
          .get('http://47.100.205.153:3445/mysql/byTime/count/yearSeason',
            {
              params: {
                year: this.year,
                season: this.season,
              }
            })
          .then((res) => {
            console.log(res);
            this.t_mysql = res.data.time;
            this.result = res.data.num;

            //neo4j
            axios
              .get('http://47.100.205.153:3445/movie/ys',
                {
                  params: {
                    year: this.year,
                    season: this.season,
                  }
                })
              .then((res) => {
                console.log(res);
                this.t_neo4j = res.data;

                //hive

                axios
                  .get('http://47.101.153.105:8100/spark/byTime/count/yearSeason',
                    {
                      params: {
                        year: this.year,
                        season: this.season,
                      }
                    })
                  .then((res) => {
                    console.log(res);
                    this.t_hive = parseInt(res.data.time * 1000);



                    this.drawchart();
                  });
              });

          });

      }
      else if (this.entity == "起止年月日") {
        console.log(this.ymd1);
        var y1 = this.ymd1.getFullYear();
        var y2 = this.ymd2.getFullYear();
        var m1 = this.ymd1.getMonth() + 1;
        var m2 = this.ymd2.getMonth() + 1;
        var d1 = this.ymd1.getDate();
        var d2 = this.ymd2.getDate();

        var s1 = y1 + "-" + m1 + "-" + d1;
        var s2 = y2 + "-" + m2 + "-" + d2;

        //mysql
        axios
          .get('http://47.100.205.153:3445/mysql/byTime/count/yearMonthDay',
            {
              params: {
                start: s1,
                end: s2
              }
            })
          .then((res) => {
            console.log(res);
            this.t_mysql = res.data.time;
            this.result = res.data.num;

            //neo4j
            axios
              .get('http://47.100.205.153:3445/movie/ymd',
                {
                  params: {
                    startYear: y1,
                    endYear: y2,
                    startMonth: m1,
                    endMonth: m2,
                    startDay: d1,
                    endDay: d2
                  }
                })
              .then((res) => {
                console.log(res);
                this.t_neo4j = res.data;

                //hive

                axios
                  .get('http://47.101.153.105:8100/spark/byTime/count/yearMonthDay',
                    {
                      params: {
                        start: s1,
                        end: s2
                      }
                    })
                  .then((res) => {
                    console.log(res);
                    this.t_hive = parseInt(res.data.time * 1000);



                    this.drawchart();
                  });
              });

          });

      }

    },

    drawchart() {
      //获取到的数据渲染echart
      var myChart = echarts.init(document.getElementById('chart1'));//var仅限function内
      var times = [this.t_mysql, this.t_hive, this.t_neo4j]; //不知道支不支持
      myChart.setOption({
        series: [
          {
            data: times
          }
        ]
      })
    },


    echartsInit() {
      var myChart = echarts.init(document.getElementById('chart1'));
      // 指定图表的配置项和数据
      var option1 = {
        title: {
          text: '三种数据库的查询时间对比图示'
        },
        tooltip: {},
        legend: {
          data: ['时间']
        },
        xAxis: {
          data: ['mysql', 'hive', 'neo4j']
        },
        yAxis: {},
        series: [
          {
            name: '时间',
            type: 'bar',
            data: []
          }
        ]
      };
      myChart.setOption(option1);
    }
  }
}
</script>
  
<style scoped>
button {
  padding: 0.8em 1.8em;
  border: 2px solid #17C3B2;
  position: relative;
  overflow: hidden;
  background-color: transparent;
  text-align: center;
  text-transform: uppercase;
  font-size: 16px;
  transition: .3s;
  z-index: 1;
  font-family: inherit;
  color: #17C3B2;
}

button::before {
  content: '';
  width: 0;
  height: 300%;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%) rotate(45deg);
  background: #17C3B2;
  transition: .5s ease;
  display: block;
  z-index: -1;
}

button:hover::before {
  width: 105%;
}

button:hover {
  color: #111;
}
</style>