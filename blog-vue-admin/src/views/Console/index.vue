<script setup lang="ts">
import {NCard, NNumberAnimation, NStatistic, NTag} from 'naive-ui'
import ViewCountEcharts from "@/components/ViewCountEcharts";
import ViewCountRankEcharts from "@/components/ViewCountRankEcharts";
import ArticleCategoryEcharts from "@/components/ArticleCategoryEcharts";
import TagCloud from "@/components/TagCloud";
import api from "@/api";
import {onMounted, ref} from "vue";
import type {CommentCount, WeekView} from "@/type";

const weekView = ref<Array<WeekView>>(new Array<WeekView>())
const GetWeekView =  () => {
  api.getWeekView().then(({data: {data}}) => weekView.value = data)
}
const siteView = ref<number>()
const GetAllView = ()=>{
  api.getAllView().then(({data:{data}})=>{
    siteView.value=Number(data)
  })
}
const commentCount = ref<CommentCount>({
  month: 0n, total: 0n
})
const GetCommentCount = ()=>{
  api.getCommentCount().then(({data:{data}})=>{
    commentCount.value=data
  })
}
const GetArticleCount = ()=>{
  api.getArticleCount().then(({data:{data}})=>{
    articleCount.value=data
  })
}
const articleCount = ref<bigint>(0n)

onMounted(() => {
  GetAllView()
  GetWeekView()
  GetCommentCount()
  GetArticleCount()
})
</script>

<template>
  <div class="card-container">
    <div class="card-item">
      <n-card
          embedded
          title="访问量"
          size="small"
          :segmented="{
            content: true,
            footer: true
          }">
        <template #header-extra>
          <n-tag type="success">日</n-tag>
        </template>
        <n-statistic tabular-nums>
          <n-number-animation ref="numberAnimationInstRef" :from="0" v-if="weekView.length>0" :to="Number(weekView[weekView.length-1].view)"/>
        </n-statistic>
        <template #footer>
          <div class="card-footer">
            <span>总访问量:</span>
            <n-number-animation ref="numberAnimationInstRef" :from="0" :to="siteView!"/>
          </div>
        </template>
      </n-card>
    </div>
    <div class="card-item">
      <n-card
          embedded
          title="评论数"
          size="small"
          :segmented="{
            content: true,
            footer: true
          }">
        <template #header-extra>
          <n-tag type="warning">月</n-tag>
        </template>
        <n-statistic tabular-nums>
          <n-number-animation ref="numberAnimationInstRef" :from="0" :to="Number(commentCount.month)"/>
        </n-statistic>
        <template #footer>
          <div class="card-footer">
            <span>总评论数:</span>
            <n-number-animation ref="numberAnimationInstRef" :from="0" :to="Number(commentCount.total)"/>
          </div>
        </template>
      </n-card>
    </div>
    <div class="card-item">
      <n-card
          embedded
          title="文章数量"
          size="small"
          :segmented="{
            content: true,
            footer: true
          }">
        <template #header-extra>
          <n-tag type="info">总</n-tag>
        </template>
        <n-statistic tabular-nums>
          <n-number-animation ref="numberAnimationInstRef" :from="0" :to="Number(articleCount)"/>
        </n-statistic>
        <template #footer>
          <div class="card-footer">
            <span>&nbsp;</span>
          </div>
        </template>
      </n-card>
    </div>
  </div>
  <div class="echarts-container">
    <div class="first-line" style="width: 100%;">
      <ViewCountEcharts :week-view-data="weekView" v-if="weekView.length>0"/>
      <TagCloud/>
    </div>
    <div class="line" style="width: 100%;">
      <ArticleCategoryEcharts style="width: 30%"/>
      <ViewCountRankEcharts style="width: 70%"/>

    </div>
    <div class="line" style="width: 100%;">
    </div>
  </div>
</template>

<style scoped>
.card-container {
  width: 100%;
  display: flex;
}

.card-item {
  flex: 1;
  padding-left: 10px;
}

.card-footer {
  display: flex;
  flex-wrap: nowrap;
  justify-content: space-between;
  align-content: center;
  font-size: 14px;
}


.echarts-container {
  width: 100%;
  padding: 0 0 0 10px;
}

.first-line {
  width: 100%;
  display: flex;
  flex-direction: row;
}

.line {
  width: 100%;
  display: flex;
  flex-direction: row;
  padding-bottom: 10px;
}

</style>