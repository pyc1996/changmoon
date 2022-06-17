<template>
<div class="content">
    <div class="md-layout">
        <div class="md-layout-item">
            <md-card>
                <md-card-header data-background-color="green">
                    <h4 class="title">비교하기</h4>
                </md-card-header>
                <md-card-content>
                    <div id="app" align="center" style="margin: 10px;">

                        <div style="margin: 20px">
                            <b-form-group v-slot="{ ariaDescribedby }">
                                <b-form-checkbox-group id="checkbox-group-2" size="lg" v-model="selected" :aria-describedby="ariaDescribedby" name="flavour-2">
                                    <b-form-checkbox value="loc">지역</b-form-checkbox>
                                    <b-form-checkbox value="part">업종</b-form-checkbox>
                                    <b-form-checkbox value="age-gender-pa">나이성별(인구별)</b-form-checkbox>
                                    <b-form-checkbox value="age-gender-deal">나이성별(거래별)</b-form-checkbox>
                                    <b-button variant="outline-primary">검색</b-button>
                                    <div>Selected: <strong>{{ selected }}</strong></div>
                                </b-form-checkbox-group>

                            </b-form-group>
                        </div>
                    </div>
                </md-card-content>

                <md-card>
                    <md-card-content>
                        <div id="typography">
                            <div class="row">
                                <div id="box1">
                                    <div class="select-box">
                                    <b-form-select v-model="selected" style="font-size:16px">
                                    <option v-for="data1 in dropboxData" v-bind:key="data1.text" style="font-size:16px">
                                        {{ data1.value }}
                                    </option>
                                    </b-form-select>
                                    </div>
                                    <div class="box-inner">
                                        <div class="center-box-word">
                                            <canvas id="myChart2" width="400" height="400">
                                            </canvas>
                                        </div>
                                    </div>
                                </div>
                                <div id="center-box">
                                    <div class="center-box-word">
                                        <h2 class="center-box-word2">VS</h2>
                                    </div>
                                </div>
                                <div id="box2">
                                    <div class="select-box">
                                    <b-form-select v-model="selected2" style="font-size:16px">
                                    <option v-for="data1 in dropboxData" v-bind:key="data1.text" style="font-size:16px">
                                        {{ data1.value }}
                                    </option>
                                    </b-form-select>
                                    </div>
                                    <div class="box-inner">
                                        <div class="center-box-word">
                                            <canvas id="myChart" width="400" height="400">
                                            </canvas>
                                           
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </md-card-content>
                </md-card>
            </md-card>

        </div>

    </div>
</div>
</template>

<script>

import { Chart, registerables } from 'chart.js';
Chart.register(...registerables);

export default {
    created(){
        this.selected = "select1",
        this.selected2 = "select2",
        this.dropboxData = [
            { text : "text1", value : "hello1"},
            { text : "text2", value : "hello222"},
        ];
    },

    props: {
        
    },

    data(){
        return{
            selected : "select1",
            selected2 : "select2",
            dropboxData :[],
            myChart : null,
            myChart2 : null,
        }
    },

    mounted(){
        this.func1();
        this.func2();
    },

    methods : {
        func1(){
            const ctx = document.getElementById('myChart');
            this.myChart = new Chart(ctx, {
            type: 'doughnut',
            data: {
                labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
                datasets: [{
                    label: '# of Votes',
                    data: [12, 19, 3, 5, 2, 3],
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(153, 102, 255, 0.2)',
                        'rgba(255, 159, 64, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
        },
        func2(){
            const ctx = document.getElementById('myChart2');
            this.myChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
                datasets: [{
                    label: '# of Votes',
                    data: [12, 19, 3, 5, 2, 3],
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.2)',
                        'rgba(54, 162, 235, 0.2)',
                        'rgba(255, 206, 86, 0.2)',
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(153, 102, 255, 0.2)',
                        'rgba(255, 159, 64, 0.2)'
                    ],
                    borderColor: [
                        'rgba(255, 99, 132, 1)',
                        'rgba(54, 162, 235, 1)',
                        'rgba(255, 206, 86, 1)',
                        'rgba(75, 192, 192, 1)',
                        'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
        }
    }
};
</script>

<style scoped>
#box1 {
    margin-left: 100px;
    float: left;
    background-color: rgba(163, 197, 207, 0.76);
    width: 600px;
    height: 680px;
    margin-bottom: 30px;
    border-radius: 10px;
}

#box2 {
    margin-right: 100px;
    float: right;
    background-color: rgba(163, 197, 207, 0.76);
    width: 600px;
    height: 680px;
    margin-bottom: 30px;
    border-radius: 10px;
}

#center-box {
    width: 140px;
    height: 650px;
    float: left;
}

.center-box-word {
 
}

.center-box-word2{
    margin-top: 40px; 
    text-align: center;
    padding-top: 170px; 
}

.box-inner {
    margin: 10px;
    height: 600px;
    background-color: rgba(231, 241, 241, 0.849);
}

.select-box {
    margin: 10px;
}
</style>
