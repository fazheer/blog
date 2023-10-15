<script setup lang="ts">
import { ref, onMounted,watch } from 'vue';
import {useRoute} from "vue-router";
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
const articles = ref<Array<BaseArticleCard>>(new Array<BaseArticleCard>())
const route = useRoute()
const title = route.params.title!.toString();
const GetArticlesAndCount = ()=>{
  api.getArchiveArticleTitle(title,BigInt(page.value)).then(({data:{data}})=>{
    articles.value=data
  })
  api.getArchiveArticleTitleCount(title).then(({data:{data}})=>{
    articleCount.value=data
    pageCount.value=Math.ceil(articleCount.value/10)
  })

}
onMounted(()=>{
  GetArticlesAndCount()
})

</script>
<template>
  <Page :page-title="title">
    <div class="archive-container">
      <n-timeline size="large" >
        <n-timeline-item  type="error">
          <template #header>
              <div class="title"> {{ title }} -{{ articleCount }}</div>
          </template>
          <template #icon>
            <n-icon :size="24">
              <Circle12Regular/>
            </n-icon>
          </template>
          </n-timeline-item>
       <TimeLineItem v-for="data in articles" :article="data"/>
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
