<template>
    <div>
      <div>请输入年份</div>
      <el-input v-model="year" placeholder="Please input year" />
      <div>请输入月份</div>
      <el-input v-model="month" placeholder="Please input month" />
      <el-button type="primary" @click="getdata1()">查询</el-button>
    </div>
    <div id="main" style="width: 600px;height:400px;"></div>

    <div>
      <div>请输入年份</div>
      <el-input v-model="year" placeholder="Please input year" />
      <div>请输入季度</div>
      <el-input v-model="quarter" placeholder="Please input quarter" />
      <el-button type="primary" @click="getdata2()">查询</el-button>
    </div>
    <div id="main" style="width: 600px;height:400px;"></div>

</template>

<script>
import axios from "axios"
import * as echarts from 'echarts'

export default {
  data(){
    return{
      year:"",
      month:"",
      quarter:"",
    }
  },
  mounted(){
    this.echartsInit();
  },
  methods:{
    getdata1(){
      axios
        .get('/movie/ym',{
          params:{
            'year':this.year,
          }
        })
        .then((res)=>{
          console.log(res);
        })
        .catch((error)=>{
          console.log(error);
        });
    },

    echartsInit(){
      var myChart = echarts.init(document.getElementById('main'));

      // 指定图表的配置项和数据
      var option = {
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
        series:[],
        /*
        series: [
          {
            name: '数量',
            type: 'bar',
            data: [5]
          }
        ]
        */
      };

      myChart.setOption(option);
    }
  }

}
</script>

<style scoped>

</style>
