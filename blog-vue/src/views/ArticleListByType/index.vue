<template>
  <Page :page-title="name">
    <TimeLineArticle :title="name" :id="id" :name="name" :type="typeLabel" />
  </Page>
</template>

<script lang="ts" setup>
import TimeLineArticle from '@/components/TimeLineArticle'
import Page from '@/components/page'

const props = defineProps<{
  type: 'category' | 'tag'
  id: bigint
  name: string
}>()

const typeLabel = props.type === 'category' ? '分类' : '标签'
</script>

<style scoped>
</style>
