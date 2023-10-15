<script setup lang="ts">
import { TimerOutline } from '@vicons/ionicons5'
import { NCard, NIcon } from "naive-ui";
import api from '@/api'
import {onMounted, ref} from "vue";
import NewestItem from "@/components/NewestItem";
import type {BaseArticleCard} from "@/type";
const topArticles = ref<Array<BaseArticleCard>>(new Array<BaseArticleCard>())

const GetTopArticle = ()=>{
  api.getTopSixArticle().then(({data:{data}})=>{
    topArticles.value=data;
  })
}
const isLast = (item:BaseArticleCard):boolean =>{
  if(topArticles.value.indexOf(item)!=5) return false
  else return true
}
onMounted(()=>{
  GetTopArticle()
})
</script>

<template>
    <n-card hoverable embedded>
        <template #header>
            <div class="card-title">
                <n-icon size="18">
                    <TimerOutline />
                </n-icon>
                <span>最新文章</span>
            </div>
        </template>
    <NewestItem v-for="item in topArticles" :item="item" :key="item.title" :is-last="isLast(item)"/>
    </n-card>
</template>
<style scoped>

</style>