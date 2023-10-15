<script setup lang="ts">
import { AlbumsOutline } from '@vicons/ionicons5'
import { NCard,NIcon} from "naive-ui";
import api from '@/api'
import {onMounted, ref} from "vue";
import type {CategoryCard} from "@/type";
import CategoryCardItem from "@/components/CategoryCardItem";
const categoryCard = ref<Array<CategoryCard>>(new Array<CategoryCard>())
const GetCategoryCard = ()=>{
  api.getCategoryCard().then(({data:{data}})=>{
    categoryCard.value=data
  })
}
onMounted(()=>{})
GetCategoryCard()
</script>
<template>
    <n-card hoverable embedded>
        <template #header>
            <div class="card-title">
                <n-icon size="18">
                    <AlbumsOutline />
                </n-icon>
                <span>分类</span>
            </div>
        </template>
        <div class="categories-container">
            <CategoryCardItem v-for="item in categoryCard" :key="item" :item="item"/>
        </div>
    </n-card>
</template>
<style scoped>

</style>