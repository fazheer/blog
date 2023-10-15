<script setup lang="ts">
import {NSpace, NTag} from 'naive-ui'
import {onBeforeRouteUpdate} from "vue-router";
import VRouter from '@/router'
import type {RouterTag} from "@/type";
import {useRouterTag} from "@/stores/RouterTagStore";

const routerTag = useRouterTag()

onBeforeRouteUpdate((to) => {
  if (to.name!.toString() == '主控台') {
    return;
  }
  const tag: RouterTag = {
    name: to.name!.toString(),
    path: to.fullPath
  }
  routerTag.pushTag(tag)
})
const deleteTag = (tag: RouterTag) => {
  routerTag.deleteTag(tag)
}
const router = VRouter
const pushRouter = (path: String) => {
  router.push(`${path}`)
}
</script>

<template>
  <div class="tag-container">
    <n-space>
      <n-tag type="info" @click="()=>{router.push('/')}" style="cursor: pointer">主控台</n-tag>
      <n-tag type="info" v-for="tag in routerTag.tags" @click="pushRouter(tag.path)" closable @close="deleteTag(tag)"
             style="cursor: pointer">{{ tag.name }}
      </n-tag>
    </n-space>
  </div>
</template>

<style scoped>
.tag-container {
  padding: 10px;
}
</style>