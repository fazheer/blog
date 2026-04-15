<template>
  <div class="archive-list-container">
    <n-timeline size="large">
      <n-timeline-item type="error">
        <template #header>
          <div class="title">{{ listTitle }} - {{ articleCount }}</div>
        </template>
        <template #icon>
          <n-icon :size="24">
            <Circle12Regular />
          </n-icon>
        </template>
      </n-timeline-item>
      <TimeLineItem v-for="data in handledData" :article="data" :key="data.id.toString()" />
    </n-timeline>
  </div>
</template>

<style scoped>
.title {
  font-size: 2rem;
  height: 40px;
  position: relative;
  top: -10px;
}

.n-timeline {
  margin-top: 10px;
}
</style>

<script setup lang="ts">
import { computed } from 'vue';
import Circle12Regular from '@vicons/fluent/Circle12Regular'
import { NTimeline, NTimelineItem, NIcon } from 'naive-ui';
import TimeLineItem from '@/components/timeLineItem'
import type { BaseArticleYear, BaseArticleCard } from "@/type";

const props = defineProps<{
  articles: Array<BaseArticleYear>
  listTitle: string
  articleCount: number
}>()

const handledData = computed<Array<BaseArticleCard>>(() => {
  const result: Array<BaseArticleCard> = []
  if (!props.articles || props.articles.length === 0) return result
  let first = props.articles[0].year
  result.push({
    id: 0n,
    coverUrl: "",
    createAt: null,
    title: first
  })
  props.articles.forEach(item => {
    if (item.year == first) {
      result.push({
        coverUrl: item.coverUrl,
        createAt: item.createAt,
        id: item.id,
        title: item.title
      })
    } else {
      first = item.year
      result.push({
        id: 0n,
        coverUrl: "",
        createAt: null,
        title: first
      })
      result.push({
        coverUrl: item.coverUrl,
        createAt: item.createAt,
        id: item.id,
        title: item.title
      })
    }
  })
  return result
})
</script>
