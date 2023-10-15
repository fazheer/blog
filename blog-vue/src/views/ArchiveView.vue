
<template>
  <Page page-title="归档">
    <div class="archive-container">
      <n-timeline size="large" >
        <n-timeline-item  type="error">
          <template #header>
              <div class="title"> 文章总览 -{{ articleCount }}</div>
          </template>
          <template #icon>
            <n-icon :size="24">
              <Circle12Regular/>
            </n-icon>
          </template>
          </n-timeline-item>
       <TimeLineItem v-for="data in handledData" :article="data"/>
      </n-timeline>
    </div>
    <div class="pagination-container">
    <n-pagination v-model:page="page" :page-count="pageCount"/>
    </div>
  </Page>
</template>
<style scoped>
.pagination-container{
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.n-timeline{
  margin-top: 10px;
}
.title{
  font-size: 2rem;
  height: 40px;
  position: relative;
  top: -10px;
}
</style>
<script setup lang="ts">
import { ref, onMounted,watch } from 'vue';
import Circle12Regular from '@vicons/fluent/Circle12Regular'
import 'md-editor-v3/lib/style.css';
import Page from '@/components/page'
import { NTimeline, NTimelineItem,NIcon,NPagination } from 'naive-ui';
import TimeLineItem from '@/components/timeLineItem'
import api from "@/api";
import type {BaseArticleYear,BaseArticleCard} from "@/type";
const page = ref(1)
const articleCount = ref(0)
const pageCount= ref(0)
const handledData = ref<Array<BaseArticleCard>>(new Array<BaseArticleCard>())
const articleYears = ref<Array<BaseArticleYear>>(new Array<BaseArticleYear>())
const GetArticleYear = (pageNum:bigint)=>{
  api.getArticleYear(pageNum).then(({data:{data}})=>{
    articleYears.value=data
    handelData()
  })
}
const handelData = ()=>{
  handledData.value = []
  let first = articleYears.value[0].year;
  handledData.value.push({
    id:0n,
    coverUrl: "",
    createAt: null,
    title: first

  })
  articleYears.value.forEach(item=>{
    if(item.year==first) {
      handledData.value.push({
        coverUrl:item.coverUrl,
        createAt: item.createAt,
        id: item.id,
        title:item.title

      })
    }else {
      first=item.year;
      handledData.value.push({
        id:0n,
        coverUrl: "",
        createAt: null,
        title: first
      })
      handledData.value.push({
        coverUrl:item.coverUrl,
        createAt: item.createAt,
        id: item.id,
        title:item.title
      })
    }
  })
}
const getArticleCount = ()=>{
  api.getArticleCount().then(({data:{data}})=>{
    articleCount.value=data
    pageCount.value=Math.ceil(articleCount.value/10)
  })
}
onMounted(()=>{
  getArticleCount()
  GetArticleYear(BigInt(page.value))
})
watch(()=>page.value,()=>GetArticleYear(BigInt(page.value)))
</script>