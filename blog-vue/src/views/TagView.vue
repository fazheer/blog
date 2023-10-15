<template>
  <Page page-title="标签">
    <div class="tags-container">
      <router-link v-for="tag in tags" :to="`/tag/${tag.name}/${tag.id}`">
        <n-gradient-text class="tag-text" :type="randomColor()">
          {{ tag.name }}
        </n-gradient-text>
      </router-link>

    </div>

  </Page>
</template>
<script setup lang="ts">
import Page from '@/components/page';
import {NGradientText} from 'naive-ui';
import {onMounted, ref} from 'vue';
import type {Tag} from '@/type'
import api from '@/api'
import VRouter from '@/router'

type itemType = "error" | "primary" | "info" | "success" | "warning" | "danger"
const tags = ref<Array<Tag>>(new Array<Tag>())
const GetTags = () => {
  api.getTags().then(({data: {data}}) => {
    tags.value = data
  })
}
onMounted(() => {
  GetTags()
})
const typeData: String[] = ["error", "primary", "info", "success", "warning", "danger"]

function randomColor(): itemType {
  const index = Math.floor(Math.random() * 5 + 1);

  return typeData[index] as itemType
}
</script>
<style scoped>
.tags-container {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  text-align: center;
  justify-content: center;
  align-items: center;
  padding: 40px 15px;
}

.tag-text {
  font-size: 1.8rem;
  cursor: pointer;
  display: block;
  padding: 20px;
}

.tag-text:hover {
  font-size: 1.82rem;
  color: #49b1f5;
}
</style>