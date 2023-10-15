<template>
  <div class="item">

  <n-card embedded title="文章分类统计">
    <div id="pie" style="height: 500px;width: 100%"></div>
  </n-card>
  </div>

</template>

<script setup lang="ts">
import {NCard} from 'naive-ui'
import * as echarts from 'echarts/core';
import type {TitleComponentOption, TooltipComponentOption, VisualMapComponentOption} from 'echarts/components';
import {TooltipComponent, VisualMapComponent,} from 'echarts/components';
import type {PieSeriesOption} from 'echarts/charts'
import {PieChart,} from 'echarts/charts';
import {LabelLayout} from 'echarts/features';
import {CanvasRenderer} from 'echarts/renderers';
import {onMounted, ref} from "vue";
import api from "@/api";

const categoriesData = ref<Array<{
  value:number
  name:string,
}>>(new Array<{name: string; value: number}>())
const GetData = ()=>{
  api.getCategoriesCard().then(({data:{data}})=>{
  const d= data as Array<{name:string,count:number}>
    d.forEach(item=>{
      categoriesData.value.push({
        name:item.name,
        value:item.count
      })
    })
    const myChart = echarts.init(document.getElementById('pie')!,'light')
    option && myChart.setOption(option);
  })

}
echarts.use([
  TooltipComponent,
  VisualMapComponent,
  PieChart,
  CanvasRenderer,
  LabelLayout
]);

type EChartsOption = echarts.ComposeOption<
    | TitleComponentOption
    | TooltipComponentOption
    | VisualMapComponentOption
    | PieSeriesOption
>;

const option: EChartsOption= {

  tooltip: {
    trigger: 'item'
  },
  legend: {
    top: '0%',
    left: 'center'
  },
  series: [
    {
      type: 'pie',
      radius: '100%',
      center: ['50%', '55%'],
      data:categoriesData.value!.sort(function (a, b) {
        return a.value - b.value;
      }),
      roseType: 'radius',
      label: {
        color: 'rgba(0, 0, 0, 0.3)'
      },
      labelLine: {
        lineStyle: {
          color: 'rgba(0, 0, 0, 0.3)'
        },
        smooth: 0.2,
        length: 10,
        length2: 20
      },

      animationType: 'scale',
      animationEasing: 'elasticOut',
      animationDelay: function (idx) {
        return Math.random() * 200;
      }
    }
  ]
};


onMounted(() => {
  GetData()

})


</script>

<style scoped>
.item {
  width: 60%;
}
</style>
