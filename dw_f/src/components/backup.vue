<template>
    <div>
      <ContentField>
        <div>
          <div>请输入年份</div>
          <el-input v-model="year" placeholder="Please input year" />
          <div>请输入月份</div>
          <el-input v-model="month" placeholder="Please input month" />
          <el-button type="primary" @click="getdata1()">查询</el-button>
        </div>
        <div id="chart1" style="width: 600px;height:400px;"></div>
  
        <el-select v-model="value1" class="m-2" placeholder="Select" size="large">
          <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
        <el-select v-model="value2" class="m-2" placeholder="Select" size="large">
          <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
        <el-button type="primary" @click="getdata2()">查询</el-button>
        <div id="chart2" style="width: 1200px;height:600px;"></div>
  
        <div class="demo-date-picker">
          <div class="container">
            <div class="block">
              <span class="demonstration">Start</span>
              <el-date-picker v-model="value3" type="date" placeholder="Pick a date" />
            </div>
            <div class="block">
              <span class="demonstration">End</span>
              <el-date-picker v-model="value4" type="date" placeholder="Pick a date" />
            </div>
          </div>
        </div>
        <el-button type="primary" @click="getdata3()">查询</el-button>
        <div id="chart3" style="width: 600px;height:400px;"></div>
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
          year: "",
          month: "",
          quarter: "",
          value1: "",
          value2: "",
          value3: "", //startdate
          value4: "", //enddate
          options: [
            {
              value: '导演',
              label: '导演',
            },
            {
              value: '演员',
              label: '演员',
            },
          ],
        }
      },
      mounted() {
        this.echartsInit();
      },
      methods: {
        getdata1() {
          axios
            .get('/movie/ym', {
              params: {
                'year': this.year,
              }
            })
            .then((res) => {
              //获取到的数据渲染echart
              console.log(res);
              console.log(res.data);
              var myChart = echarts.init(document.getElementById('chart1'));//var仅限function内
              myChart.setOption({
                series: [
                  {
                    data: [
                      { value: res.data }
                    ]
                  }
                ]
              })
            })
            .catch((error) => {
              console.log(error);
            });
        },
    
        getdata2() {
          var thisurl;
          if (this.value1 == '导演' && this.value2 == '导演') thisurl = "/director/findtwo";
          if ((this.value1 == "导演" && this.value2 == "演员") || (this.value1 == "演员" && this.value2 == "导演")) thisurl = "/actor/finddir";
          if (this.value1 == "演员" && this.value2 == "演员") thisurl = "/actor/findtwo";
          axios
            .get(thisurl, {
              // params: {
              //   'value1': this.value1,
              //   'value2':this.value2,
              // }
            })
            .then((res) => {
              console.log(this.value1);
              console.log(this.value2);
              console.log(res.data.data);
              var series_data = [];
              for (let i = 0; i < 10; i++) {
                series_data.push(parseInt(res.data.data[i].time));
              }
              console.log(series_data);
              var xAxis_name = [];
              for (let i = 0; i < 10; i++) {
                xAxis_name.push(res.data.data[i].name1 + " + " + res.data.data[i].name2);
              }
              var chart2 = echarts.init(document.getElementById('chart2'));//var仅限function内
              chart2.setOption({
                xAxis: {
                  data: xAxis_name
                },
                series: [
                  {
                    data: series_data,
                    itemStyle: {
                      normal: {
                        label: {
                          show: true, //开启显示
                          position: 'top', //在上方显示
                          textStyle: { //数值样式
                            color: 'black',
                            fontSize: 16
                          }
                        }
                      }
                    },
                  }
                ]
              })
            })
            .catch((error) => {
              console.log(error);
            });
        },
    
        getdata3() {
          axios
            .get('/movie/ym', {
              params: {
                'startdate': this.value3,
                'enddate': this.value4,
              }
            })
            .then((res) => {
              console.log(this.value3);
              console.log(this.value4);
              console.log(res);
              //获取到的数据渲染echart
              var chart3 = echarts.init(document.getElementById('chart2'));//var仅限function内
              chart3.setOption({
                series: [
                  {
                    data: res.data,
                  }
                ]
              })
            })
            .catch((error) => {
              console.log(error);
            });
        },
    
    
        echartsInit() {
          var myChart = echarts.init(document.getElementById('chart1'));
          var chart2 = echarts.init(document.getElementById('chart2'));
          // 指定图表的配置项和数据
          var option1 = {
            title: {
              text: '查询结果图示'
            },
            tooltip: {},
            legend: {
              data: ['数量']
            },
            xAxis: {
              data: ['该时段电影']
            },
            yAxis: {},
            series: [
              {
                name: '数量',
                type: 'bar',
                data: []
              }
            ]
          };
    
          var option2 = {
            title: {
              text: '查询结果图示'
            },
            tooltip: {},
            legend: {
              data: ['合作次数']
            },
            xAxis: {
              data: ['No.1', 'No.2', 'No.3', 'No.4', 'No.5', 'No.6', 'No.7', 'No.8', 'No.9', 'No.10']
            },
            yAxis: {},
            series: [
              {
                //name: '合作次数',
                type: 'bar',
                data: []
              }
            ]
          };
    
          myChart.setOption(option1);
          chart2.setOption(option2);
        }
      }
    
    }
  </script>
  
  <style scoped>
  .demo-date-picker {
    display: flex;
    width: 100%;
    padding: 0;
    flex-wrap: wrap;
  }
  
  .demo-date-picker .block {
    padding: 30px 0;
    text-align: center;
    border-right: solid 1px var(--el-border-color);
    flex: 1;
  }
  
  .demo-date-picker .block:last-child {
    border-right: none;
  }
  
  .demo-date-picker .container {
    flex: 1;
    border-right: solid 1px var(--el-border-color);
  }
  
  .demo-date-picker .container .block {
    border-right: none;
  }
  
  .demo-date-picker .container .block:last-child {
    border-top: solid 1px var(--el-border-color);
  }
  
  .demo-date-picker .container:last-child {
    border-right: none;
  }
  
  .demo-date-picker .demonstration {
    display: block;
    color: var(--el-text-color-secondary);
    font-size: 14px;
    margin-bottom: 20px;
  }
  </style>