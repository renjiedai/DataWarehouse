<template>
    <div>
        <ContentField>
            <el-card class="box-card">
                <template #header>
                    <div class="card-header" style="background-color: #17C3B2;">
                        <span style="font-size: 25px;">查询条件</span>
                    </div>
                </template>
                <div>
                    <div style="margin-bottom:20px">请确定查询标准：</div>
                    <slot>合作次数超过</slot>
                    <el-input-number v-model="times" :min="0" :max="100" />
                    <slot>次的</slot>
                    <el-select v-model="type1" class="m-2" placeholder="Select" size="large">
                        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                    <slot>和</slot>
                    <el-select v-model="type2" class="m-2" placeholder="Select" size="large">
                        <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
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
                <p style="text-align:left; margin-bottom: 20px;">查询结果：{{ result }}</p>
                <p style="text-align:left; margin-bottom: 20px;">查询用时</p>
                <p style="text-align:left; margin-bottom: 20px;">mysql : {{ t_mysql }}</p>
                <p style="text-align:left; margin-bottom: 20px;">hive : {{ t_hive }}</p>
                <p style="text-align:left; margin-bottom: 50px;">neo4j : {{ t_neo4j }}</p>
                <div id="chart1" style="width: 600px;height:400px;"></div>
                <div v-if="type1 === '导演' && type2 === '导演'">
                    <p style="text-align:left; margin-bottom: 20px;">查询结果</p>
                    <el-table :data="pairs" height="250" style="width: 100%">
                        <el-table-column prop="directorName1" label="Director1" />
                        <el-table-column prop="directorName2" label="Director2" />
                        <el-table-column prop="collaborateCount" label="CollaborateCount" />
                    </el-table>
                </div>
                <div v-else-if="type1 === '演员' && type2 === '演员'">
                    <p style="text-align:left; margin-bottom: 20px;">查询结果</p>
                    <el-table :data="pairs" height="250" style="width: 100%">
                        <el-table-column prop="actorName1" label="Actor1" />
                        <el-table-column prop="actorName2" label="Actor2" />
                        <el-table-column prop="collaborateCount" label="CollaborateCount" />
                    </el-table>
                </div>
                <div v-else>
                    <p style="text-align:left; margin-bottom: 20px;">查询结果</p>
                    <el-table :data="pairs" height="250" style="width: 100%">
                        <el-table-column prop="directorName" label="Director" />
                        <el-table-column prop="actorName" label="Actor" />
                        <el-table-column prop="collaborateCount" label="CollaborateCount" />
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
            type1: "",
            type2: "",
            times: "",
            result: "",
            pairs: [], //the couple name and numcount
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
            if (this.type1 == "导演" && this.type2 == "导演") {
                //mysql
                axios
                    .get('http://47.100.205.153:3445/mysql/byColla/count/colla/dcdc',
                        {
                            params: {
                                collaTime: this.times
                            }
                        })
                    .then((res) => {
                        console.log(res);
                        this.t_mysql = res.data.time;
                        this.result = res.data.num;
                        this.pairs = res.data.data;


                        //neo4j
                        axios
                            .get('http://47.100.205.153:3445/director/findtwo',
                                {
                                    params: {
                                        time: this.times
                                    }
                                })
                            .then((res) => {
                                console.log(res);
                                this.t_neo4j = res.data;


                                //hive 【hasn't finished】
                                axios
                                    .get('http://47.101.153.105:8100/spark/byColla/count/colla/dcdc',
                                        {
                                            params: {
                                                collaTime: this.times
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
            else if ((this.type1 == "导演" && this.type2 == "演员") || (this.type1 == '演员' && this.type2 == '导演')) {
                //mysql
                axios
                    .get('http://47.100.205.153:3445/mysql/byColla/count/colla/dcac',
                        {
                            params: {
                                collaTime: this.times
                            }
                        })
                    .then((res) => {
                        console.log(res);
                        this.t_mysql = res.data.time;
                        this.result = res.data.num;
                        this.pairs = res.data.data;
                        //neo4j
                        axios
                            .get('http://47.100.205.153:3445/actor/finddir',
                                {
                                    params: {
                                        time: this.times
                                    }
                                })
                            .then((res) => {
                                console.log(res);
                                this.t_neo4j = res.data;

                                //hive 【hasn't finished】
                                axios
                                    .get('http://47.101.153.105:8100/spark/byColla/count/colla/dcac',
                                        {
                                            params: {
                                                collaTime: this.times
                                            }
                                        })
                                    .then((res) => {
                                        console.log(res);

                                        this.t_hive = parseInt(res.data.time * 1000);


                                        this.drawchart();
                                    });
                            });
                        //hive 【hasn't finished】
                    });

            }
            else if (this.type1 == '演员' && this.type2 == '演员') {
                //mysql
                axios
                    .get('http://47.100.205.153:3445/mysql/byColla/count/acac',
                        {
                            params: {
                                collaTime: this.times
                            }
                        })
                    .then((res) => {
                        console.log(res);
                        this.t_mysql = res.data.time;
                        this.result = res.data.num;
                        this.pairs = res.data.data;

                        //neo4j
                        axios
                            .get('http://47.100.205.153:3445/actor/findtwo',
                                {
                                    params: {
                                        time: this.times
                                    }
                                })
                            .then((res) => {
                                console.log(res);
                                this.t_neo4j = res.data;
                                //hive 【hasn't finished】

                                axios
                                    .get('http://47.101.153.105:8100/spark/byColla/count/acac',
                                        {
                                            params: {
                                                collaTime: this.times
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