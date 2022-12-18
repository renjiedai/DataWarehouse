<template>
  <div>
    <ContentField>
      <el-card>
        <div style="margin-bottom:25px;font-size:20px;">
          <span>Q:在过程中，我们找出了多少非电影的数据？</span>
          <span>A:45895</span>
        </div>
        <div style="margin-bottom:25px;font-size:20px;">
          <span>Q:在ETL和数据预处理中,我们找到多少哈利波特系列的电影? </span>
          <span>A:55</span>
        </div>
        <div style="margin-bottom:25px;font-size:20px;">
          <span>Q:这些电影来自多少网页？</span>
          <span>A:98</span>
        </div>
        <div style="margin-bottom:25px;font-size:20px;">
          <span>Q:哈利波特第一部我们合并了多少个不同的网页？   </span>
          <span>A:10</span>
        </div>
      </el-card>

      <el-card class="box-card">
        <template #header>
          <div class="card-header">
            <span>数据流向</span>

          </div>
        </template>
        <div id="chart1" style="width: 1000px;height:400px;margin-left: 200px;"></div>
      </el-card>

      <el-card class="box-card">
        <template #header>
          <div class="card-header">
            <span>数据溯源</span>

          </div>
        </template>
        <div id="chart2" style="width: 1000px;height:400px;margin-left: 200px;"></div>
      </el-card>
    </ContentField>
  </div>
</template>
  
<script>
import * as echarts from 'echarts'
import ContentField from '@/components/ContentField.vue';

export default {
  components: { ContentField },
  mounted() {
    this.echartsInit1();
    this.echartsInit2();
  },
  methods: {
    echartsInit1() {
      var myChart = echarts.init(document.getElementById('chart1'));
      // 指定图表的配置项和数据
      var option1 = {
        tooltip: {
          trigger: 'item',
          triggerOn: 'mousemove'
        },
        series: {
          type: 'sankey',
          layout: 'none',
          emphasis: {
            focus: 'adjacency'
          },
          data: [
            {
              name: '评论数据'
            },
            {
              name: 'Amazon网页'
            },
            {
              name: '原始电影数据'
            },
            {
              name: '非电影页面'
            },
            {
              name: '404页面'
            },
            {
              name: '合并后的电影数据'
            },
            {
              name: '重复项（电影名、导演名、演员名）'
            }
          ],
          links: [
            {
              source: '评论数据',
              target: 'Amazon网页',
              value: 253059
            },
            {
              source: 'Amazon网页',
              target: '原始电影数据',
              value: 203972
            },
            {
              source: 'Amazon网页',
              target: '非电影页面',
              value: 45895
            },
            {
              source: 'Amazon网页',
              target: '404页面',
              value: 3912
            },
            {
              source: '原始电影数据',
              target: '合并后的电影数据',
              value: 146831
            },
            {
              source: '原始电影数据',
              target: '重复项（电影名、导演名、演员名）',
              value: 57141
            }
          ],

        }
      };
      myChart.setOption(option1);
    },
    echartsInit2() {
      var myChart = echarts.init(document.getElementById('chart2'));
      // 指定图表的配置项和数据
      var option1 = {
        tooltip: {
          trigger: 'item',
          triggerOn: 'mousemove'
        },
        series: {
          type: 'sankey',
          layout: 'none',
          emphasis: {
            focus: 'adjacency'
          },
          data: [
            {
              name: 'Amazon网页'
            },
            {
              name: 'Douban网页'
            },
            {
              name: '上映时间'
            },
          ],
          links: [
            {
              source: 'Amazon网页',
              target: '上映时间',
              value: 39342
            },
            {
              source: 'Douban网页',
              target: '上映时间',
              value: 14256
            },
          ]
        }
      };
      myChart.setOption(option1);
    }
  }

}
</script>
  
<style scoped>

</style>