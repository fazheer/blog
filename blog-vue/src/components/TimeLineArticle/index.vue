<script setup lang="ts">
import {ref, onMounted, watch} from 'vue';
import Circle12Regular from '@vicons/fluent/Circle12Regular'
import 'md-editor-v3/lib/style.css';
import { NTimeline, NTimelineItem,NIcon,NPagination } from 'naive-ui';
import TimeLineItem from '@/components/timeLineItem'
import api from "@/api";
import type {BaseArticleCard, BaseArticleYear} from "@/type";
const page = ref(1)
const pageCount =ref(1)
const articleCount = ref(30)
const props =defineProps<{
  id:bigint
  title:String,
  name:String
  type:String
}>()
const handledData = ref<Array<BaseArticleCard>>(new Array<BaseArticleCard>())
const articleYears = ref<Array<BaseArticleYear>>(new Array<BaseArticleYear>())
const GetCategoryArticleYear = (pageNum:bigint)=>{
  api.getCategoryArticleYear(props.id!,pageNum).then(({data:{data}})=>{
    articleYears.value=data
    handelData()
  })
}
function getArticleCount(){
  if(props.type=="分类") {
    api.getArticleCountByCategoryId(props.id).then(({data:{data}})=>{
      articleCount.value=data
      pageCount.value=Math.ceil(articleCount.value/10)
    })
  }else if(props.type=="标签") {
    api.getArticleCountByTagId(props.id).then(({data:{data}})=>{
      articleCount.value=data
      pageCount.value=Math.ceil(articleCount.value/10)
    })
  }
}
const GetTagArticleYear = (pageNum:bigint)=>{
  api.getTagArticleYear(props.id!,pageNum).then(({data:{data}})=>{
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
onMounted(()=>{
  getArticleCount()
  if(props.type=="分类") {
    GetCategoryArticleYear(BigInt(page.value))
  }else if(props.type=="标签") {
    GetTagArticleYear(BigInt(page.value))
  }
})
watch(()=>page.value,()=>GetCategoryArticleYear(BigInt(page.value)))
</script>
<template>
    <div class="archive-container">
      <n-timeline size="large" >
        <n-timeline-item  type="error">
          <template #header>
            <div class="title">{{props.type}}-{{title}}</div>
          </template>
          <template #icon>
            <n-icon :size="24">
              <Circle12Regular/>
            </n-icon>
          </template>
        </n-timeline-item>
        <TimeLineItem v-for="data in handledData" :article="data" :key="data.id.toString()"/>
      </n-timeline>
    </div>
    <div class="pagination-container">
      <n-pagination v-model:page="page" :page-count="pageCount"/>
    </div>
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
