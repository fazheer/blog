<template>
  <div class="item">

  <n-card embedded title="网站一周访问量">
    <div id="main" style="height: 350px;width: 100%"></div>
  </n-card>
  </div>
</template>

<script setup lang="ts">
import {NCard} from 'naive-ui'
import * as echarts from 'echarts/core';
import type {GridComponentOption} from 'echarts/components'
import {GridComponent, LegendComponent, TooltipComponent} from 'echarts/components';
import type {LineSeriesOption} from 'echarts/charts'
import {LineChart} from 'echarts/charts';
import {UniversalTransition} from 'echarts/features';
import {CanvasRenderer} from 'echarts/renderers';
import {onMounted} from "vue";
import type {WeekView} from "@/type";
import moment from 'moment'

const props= defineProps<{
  weekViewData:Array<WeekView>
}>()
const xData = []
const yData = []
props.weekViewData.forEach(item=>{

})
for (let i = props.weekViewData.length-1; i >=0; i--) {
  xData.push(moment(props.weekViewData[i].time).format("YYYY-MM-DD"))
  yData.push(props.weekViewData[i].view)
}
echarts.use([GridComponent, LineChart, CanvasRenderer, UniversalTransition,LegendComponent,TooltipComponent]);

type EChartsOption = echarts.ComposeOption<
    GridComponentOption | LineSeriesOption
    >;
const option: EChartsOption = {

  tooltip:{
    trigger:'axis'
  },
  legend: {
    data:['访问量'],
  },
  xAxis: {
    type: 'category',
    data: xData
  },
  yAxis: {
    type: 'value'
  },
  series: [
    {
      name:"访问量",
      data: yData,
      type: 'line'
    }
  ]
};
onMounted(()=>{
  const myChart = echarts.init(document.getElementById('main')!)
  option && myChart.setOption(option);
})


</script>

<style scoped>
.item {
  width: 72%;
  padding: 10px 0 0 0;
}
</style>
