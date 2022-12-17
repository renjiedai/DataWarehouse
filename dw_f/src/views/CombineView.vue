<template>
    <div>
        <ContentField>
            <el-card class="box-card">
                <template #header>
                    <div class="card-header" style="background-color: #17C3B2;">
                        <span style="font-size: 25px;">查询条件</span>
                    </div>
                </template>
                <div style="margin-bottom:20px">
                    <slot>请确定查询标准：</slot>
                    <el-select v-model="type" class="m-2" placeholder="Select" size="large">
                        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>

                <div v-if="type === '组合1'" style="margin-top: 20px;">
                    <slot> 要拍一部 </slot>
                    <el-input class="m-2" v-model="type_name" style="width:150px; margin-right: 10px;"
                        placeholder="Please input type" />
                    <slot> 类型的电影，最受关注的演员组合是 </slot>
                </div>

                <div v-if="type === '组合2'" style="margin-top: 20px;">
                    <slot> 导演：</slot>
                    <el-input class="m-2" v-model="dir_name" style="width:150px; margin-right: 10px;"
                        placeholder="Please input name" />
                    <slot> 类型：</slot>
                    <el-input class="m-2" v-model="type_name" style="width:150px; margin-right: 10px;"
                        placeholder="Please input type" />
                    <slot> 差评率大于 </slot>
                    <el-input-number v-model="num" :precision="2" :step="0.1" :min="0" :max="1" />
                </div>

                <div v-if="type === '组合3'" style="margin-top: 20px;">
                    <slot>拍摄年份范围：</slot>
                    <el-input-number v-model="year1" :step="1" :min="1600" :max="2022" />
                    <slot> — </slot>
                    <el-input-number v-model="year2" :step="1" :min="1600" :max="2022" />
                    <slot> 主演演员：</slot>
                    <el-input class="m-2" v-model="star_name" style="width:150px; margin-right: 10px;"
                        placeholder="Please input name" />
                    <slot> 评分大于：</slot>
                    <el-input-number v-model="score" :precision="1" :step="0.1" :min="0" :max="5" />
                </div>


                <br />
                <button @click="search()"> 查询 </button>
            </el-card>

            <el-card class="box-card">
                <template #header>
                    <div class="card-header" style="background-color: #17C3B2;">
                        <span style="font-size: 25px;">查询结果</span>
                    </div>
                </template>
                <p style="text-align:left; margin-bottom: 20px;">查询用时</p>
                <p style="text-align:left; margin-bottom: 20px;">mysql : {{ t_mysql }}</p>
                <p style="text-align:left; margin-bottom: 20px;">hive : {{ t_hive }}</p>
                <p style="text-align:left; margin-bottom: 50px;">neo4j : {{ t_neo4j }}</p>
                <div id="chart1" style="width: 600px;height:400px;"></div>
                <div>
                    <p style="text-align:left; margin-bottom: 20px;">查询结果</p>
                    <el-table :data="pairs" height="250" style="width: 100%">
                        <el-table-column prop="name1" label="Name1" width="180" />
                        <el-table-column prop="name2" label="Name2" width="180" />
                        <el-table-column prop="collaborateCount" label="CollaborateCount" width="180" />
                    </el-table>
                </div>
            </el-card>
        </ContentField>
    </div>
</template>
  
<script>
import ContentField from '@/components/ContentField.vue';
import axios from "axios"
import * as echarts from 'echarts'

export default {
    components: { ContentField },
    mounted() {
        this.echartsInit();
    },
    data() {
        return {
            t_mysql: "",
            t_hive: "",
            t_neo4j: "",
            type: "",
            times: "",
            dir_name:"",
            type_name:"",
            num:"",
            year1:"",
            year2:"",
            star_name:"",
            score:"",
            pairs: [], //the couple name and numcount
            options: [
                {
                    value: '组合1',
                    label: '组合1',
                },
                {
                    value: '组合2',
                    label: '组合2',
                },
                {
                    value: '组合3',
                    label: '组合3',
                },
            ],
        }
    },
    methods: {
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

        search() {
            if (this.type == "组合1") {
                // //mysql
                // axios
                //     .get('mysql/byColla/count/colla/dcdc',
                //         {
                //             params: {
                //                 collaTime: this.times
                //             }
                //         })
                //     .then((res) => {
                //         console.log(res);
                //     });
                // //neo4j
                // axios
                //     .get('/director/findtwo',
                //         {
                //             params: {
                //                 time: this.times
                //             }
                //         })
                //     .then((res) => {
                //         console.log(res);
                //     });
                // //hive 【hasn't finished】
            }
            else if (this.type == "组合2") {
                //mysql
                axios
                    .get('mysql/combine/list/rate',
                        {
                            params: {
                                directorName: this.dir_name,
                                type:this.type_name,
                                rate:this.value,
                            }
                        })
                    .then((res) => {
                        console.log(res);
                    });
                //neo4j
                axios
                    .get('',
                        {
                            params: {
                                directorName: this.dir_name,
                                type:this.type_name,
                                rate:this.value,
                            }
                        })
                    .then((res) => {
                        console.log(res);
                    });
                //hive 【hasn't finished】
            }
            else if (this.type == "组合3") {
                //mysql
                axios
                    .get('mysql/combine/list/score',
                        {
                            params: {
                                start: this.year1,
                                end:this.year2,
                                name:this.star_name,
                                score:this.score
                            }
                        })
                    .then((res) => {
                        console.log(res);
                    });
                //neo4j
                axios
                    .get('/',
                        {
                            params: {
                                start: this.year1,
                                end:this.year2,
                                name:this.star_name,
                                score:this.score
                            }
                        })
                    .then((res) => {
                        console.log(res);
                    });
                //hive 【hasn't finished】
            }
            this.drawchart();
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
        },
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