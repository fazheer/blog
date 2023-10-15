<script setup lang="ts">
import {BarChartOutline} from '@vicons/ionicons5'
import {NCard, NIcon, NSpace} from 'naive-ui';
import moment from 'moment'
const props = defineProps<{
  articleCount:bigint
  buildTime:Date
  siteView:bigint
  lastTime:Date
}>()
const  getDay = (date:Date):number =>{
  return moment(moment()).diff(moment(date),'day')

}
</script>
<template>
  <n-card hoverable embedded>
    <template #header>
      <div class="card-title">
        <n-icon size="18">
          <BarChartOutline/>
        </n-icon>
        <span>网站资讯</span>
      </div>
    </template>
    <n-space vertical>

    <div class="item">
      <n-space justify="space-between">
        <span>文章数目:</span>
        <span>{{articleCount}}</span>
      </n-space>
    </div>
    <div class="item">
      <n-space justify="space-between">
        <span>已运行时间:</span>
        <span>{{getDay(buildTime)}}天</span>
      </n-space>
    </div>
    <div class="item">
      <n-space justify="space-between">
        <span>网站总访问量:</span>
        <span>{{siteView}}</span>
      </n-space>
    </div>
    <div class="item">
      <n-space justify="space-between">
        <span>最后更新时间:</span>
        <span>{{getDay(lastTime) }}天前</span>
      </n-space>
    </div>
    </n-space>
  </n-card>
</template>
<style scoped>

</style>