<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRoute } from "vue-router";
import Page from '@/components/page'
import { NPagination } from 'naive-ui';
import ArchiveArticleList from '@/components/ArchiveArticleList'
import * as api from "@/api";
import type { BaseArticleYear } from "@/type";

const page = ref(1)
const articleCount = ref(0)
const pageCount = ref(0)
const articles = ref<Array<BaseArticleYear>>(new Array<BaseArticleYear>())
const route = useRoute()
const title = route.params.title!.toString()

const GetArticlesAndCount = () => {
  api.getArchiveArticleTitle(title, BigInt(page.value)).then(({ data: { data } }) => {
    articles.value = data
  })
  api.getArchiveArticleTitleCount(title).then(({ data: { data } }) => {
    articleCount.value = data
    pageCount.value = Math.ceil(articleCount.value / 10)
  })
}

onMounted(() => {
  GetArticlesAndCount()
})
</script>

<template>
  <Page :page-title="title">
    <ArchiveArticleList :articles="articles" :list-title="title" :article-count="articleCount" />
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
