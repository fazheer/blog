<template>
  <div class="item">

  <n-card embedded title="文章访问量总排行">
    <div id="m" style="height: 500px;width: 100%"></div>
  </n-card>
  </div>

</template>

<script setup lang="ts">
import {NCard} from 'naive-ui'
import * as echarts from 'echarts/core';
import type {GridComponentOption} from 'echarts/components'
import {GridComponent, LegendComponent, TitleComponent, TooltipComponent} from 'echarts/components';
import type {BarSeriesOption} from 'echarts/charts'
import {BarChart} from 'echarts/charts';
import {UniversalTransition} from 'echarts/features';
import {CanvasRenderer} from 'echarts/renderers';
import {onMounted, ref} from "vue";
import type {ArticleViewRank} from "@/type";
import api from "@/api";

const xData = []
const yData = []
const articleViewRank =ref<Array<ArticleViewRank>>(new Array<ArticleViewRank>())
const GeTArticleViewRank = ()=>{
  api.getArticleViewRank().then(({data:{data}})=>{
    articleViewRank.value=data
    for (let i = articleViewRank.value.length - 1; i >= 0; i--) {
      xData.push(articleViewRank.value[i].title)
      yData.push(articleViewRank.value[i].views)
    }
    const myChart = echarts.init(document.getElementById('m')!)
    option && myChart.setOption(option);
  })
}
echarts.use([GridComponent, BarChart,CanvasRenderer, UniversalTransition, TitleComponent, LegendComponent, TooltipComponent]);

type EChartsOption = echarts.ComposeOption<
    GridComponentOption | BarSeriesOption
>;
const option: EChartsOption = {
  tooltip: {
    trigger: 'axis'
  },
  legend: {
    data: ['访问量'],
  },
  xAxis: {
    data: xData

  },
  yAxis: {
  },
  series: [
    {
      name: "访问量",
      type: 'bar',
      data: yData,
    }
  ]
};
onMounted(() => {
  GeTArticleViewRank()
  
})


</script>

<style scoped>
.item {
  width: 66%;
  padding: 0 10px 0 10px;
}
</style>
