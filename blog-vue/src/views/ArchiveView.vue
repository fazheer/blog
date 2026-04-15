<template>
  <Page page-title="归档">
    <ArchiveArticleList :articles="articleYears" list-title="文章总览" :article-count="articleCount" />
    <div class="pagination-container">
      <n-pagination v-model:page="page" :page-count="pageCount" />
    </div>
  </Page>
</template>

<style scoped>
.pagination-container {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>

<script setup lang="ts">
import { ref, onMounted, watch } from 'vue';
import Page from '@/components/page'
import { NPagination } from 'naive-ui';
import ArchiveArticleList from '@/components/ArchiveArticleList'
import * as api from "@/api";
import type { BaseArticleYear } from "@/type";

const page = ref(1)
const articleCount = ref(0)
const pageCount = ref(0)
const articleYears = ref<Array<BaseArticleYear>>(new Array<BaseArticleYear>())

const GetArticleYear = (pageNum: bigint) => {
  api.getArticleYear(pageNum).then(({ data: { data } }) => {
    articleYears.value = data
  })
}

const getArticleCount = () => {
  api.getArticleCount().then(({ data: { data } }) => {
    articleCount.value = data
    pageCount.value = Math.ceil(articleCount.value / 10)
  })
}

onMounted(() => {
  getArticleCount()
  GetArticleYear(BigInt(page.value))
})

watch(() => page.value, () => GetArticleYear(BigInt(page.value)))
</script>
