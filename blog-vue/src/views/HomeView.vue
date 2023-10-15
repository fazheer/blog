<script setup lang="ts">
import PostOverView from '../components/Post/PostOverView.vue';
import { NSpace, NPagination } from "naive-ui";
import {ref, onMounted, watch} from 'vue';
import Aside from '@/components/aside/index';
import Banner from "@/components/banner";
import api from '@/api'
import type {HomeArticleCard}from '@/type'
import {useRouter} from "vue-router";
const page = ref(1)
const size = ref(0)
const articles = ref<Array<HomeArticleCard>>(new Array<HomeArticleCard>());
const router = useRouter()
const GetHomeArticles = ()=>{
  api.getHomeArticles(page.value).then(({data:{data}})=>{
    articles.value=data
  })
}
const GetArticleCount = ()=>{
  api.getArticleCount().then(({data:{data}})=>{
    size.value=data
  })
}
onMounted(()=>{
  GetArticleCount()
  GetHomeArticles()
})
watch(()=>page.value,()=>{
  GetHomeArticles()
})
const getArticleIndex = (article:HomeArticleCard)=>{
  return articles.value?.indexOf(article)
}
</script>
<template>
  <Banner/>
    <div class="home-page">
        <div class="main-container">
            <div class="overview-list">
                <n-space vertical :size="20">
                    <PostOverView
                        v-for="article in articles"
                        :key="article.title"
                        :is-left="getArticleIndex(article)%2==1"
                        :article="article"
                        />
                    <div class="bottom-pagination"><n-pagination v-model:page="page" :page-count="Math.ceil(size/10)"  /></div>
                </n-space>
            </div>
            <Aside />
        </div>
    </div>
</template>
<style scoped>

.bottom-pagination {
    display: flex;
    justify-content: center;
    align-items: center;
}

.home-page {
    max-width: 100%;
    width: 1200px;
    justify-content: center;
    align-items: center;
}

.main-container {
    width: 100%;
    display: flex;
    justify-content: center;
    padding: 50px 40px;
    box-sizing: border-box;
}

.overview-list {
    width: 74%;
}

@media (min-width: 2000px) {
    .home-page {
    max-width: 100%;
    width: 1500px;
    display: flex;
    justify-content: center;
}
}

@media (max-width: 900px) {
    .main-container {
        width: 100%;
        display: flex;
        justify-content: center;
        flex-direction: column;
        padding: 40px 15px;
        box-sizing: border-box;

    }

    .overview-list {
        width: 100%;

    }
}

@media (max-width:768px) {
    .main-container {
        width: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        padding: 20px 5px;
    }

    .overview-list {
        width: 100%;
    }
}
</style>