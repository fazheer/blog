<script setup lang="ts">
import type {BaseArticleCard} from "@/type";
import { NDivider} from 'naive-ui'
import VRouter from '@/router'
const props= defineProps<{
  item:BaseArticleCard
  isLast:boolean
}>()
</script>

<template>
  <div class="newest-item" @click="VRouter.push({
    path:`/post/${props.item.id}`
  })">
    <div class="post-cover-preview">
      <img :src="item.coverUrl" />
    </div>
    <div class="post-info">
      <span class="title">{{item.title}}</span>
      <span class="date">{{new Date(item.createAt).toLocaleDateString()}}</span>
    </div>
  </div>
  <n-divider v-show="!isLast"/>
</template>

<style scoped>
.newest-item {
  width: 100%;
  height: 20%;
  display: flex;
  flex-direction: row;
  cursor: pointer;
}

.post-cover-preview {
  width: 40%;
  height: 40%;
  overflow: hidden;
}

 img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.newest-item:hover img{
  transition: filter 375ms ease-in 0.2s, transform 0.6s;
  object-fit: cover;
  transform: scale(1.1);
}
.post-info {
  padding-left: 10px;
  display: flex;
  flex-direction: column;
}

@media (max-width: 900px) {
  .post-cover-preview {
    width: 10%;
    height: 10%;
  }
}
</style>