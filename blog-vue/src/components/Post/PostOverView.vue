<script setup lang="ts">
import { Calendar, ChatbubblesSharp, Albums } from '@vicons/ionicons5'
import { NIcon, NDivider } from "naive-ui";
 import TopIcon from '@/components/icons/TopIcon.vue'
import type {HomeArticleCard}from '@/type'
defineProps<{
  article:HomeArticleCard,
  isLeft:boolean
}>()
</script>
<template>
   <div class="overview-box" :class="[isLeft ? 'left' : 'right']">

      <div class="post-cover ">
         <router-link :to="`/post/${article.id}`">
            <img :src="article.coverUrl" alt="sb" :class="[isLeft ? 'img-left' : 'img-right']" />
         </router-link>
      </div>

      <div class="post-info">
         <router-link :to="`/post/${article.id}`" class="post-title">{{article.title}}</router-link>
         <div class="meta-wraps">
            <TopIcon v-show="article.isTop"/>
            <n-divider v-show="article.isTop" vertical />
            <div class="item">
               <n-icon :size="14">
                  <Calendar />
               </n-icon>
               <span>发布于 {{new Date(article.createAt).toLocaleDateString()}}</span>
            </div>
            <n-divider vertical />
            <div class="item">
               <n-icon :size="14">
                  <Albums />
               </n-icon>
               <span>{{ article.categoryName}}</span>
            </div>
            <n-divider vertical />
            <div class="item">
               <n-icon :size="14">
                  <ChatbubblesSharp />
               </n-icon>
               <span>评论</span>
               {{article.commentNum}}
            </div>
         </div>
         <div class="post-content">
           {{
             article.content
           }}
         </div>
      </div>
   </div>
</template>
<style scoped>
.overview-box {
   height: 18em;
   width: 100%;
   display: flex;
   border-radius: 8px;
   box-shadow: 0 3px 8px 6px rgba(7, 17, 27, 0.05);
}
.item{
  color: grey;
}
.left {
   flex-direction: row;
}

.right {
   flex-direction: row-reverse;
}

.post-cover {
   width: 44%;
   height: 100%;
   overflow: hidden;
}
img {
   width: 100%;
   height: 100%;
   object-fit: cover;
}
.overview-box:hover img{
    transition: filter 375ms ease-in 0.2s, transform 0.6s;
    object-fit: cover;
    transform: scale(1.1);
}
.img-left {
   border-radius: 8px 0 0 8px;
}

.img-right {
   border-radius: 0 8px 8px 0;
}

.post-info {
   width: 56%;
   display: flex;
   flex-direction: column;
   justify-content: center;
   padding: 40px 15px;
}

.post-title {
   font-size: 1.5rem;
}

.post-content {
   display: -webkit-box;
   overflow: hidden;
   -webkit-line-clamp: 2;
   -webkit-box-orient: vertical;
}

.meta-wraps {
   display: flex;
   flex-direction: row;
   flex-wrap: nowrap;
   align-items: center;
}

.item {
   display: inline-flex;
   align-items: center;

}

@media (max-width:768px) {

   .overview-box {
      width: 100%;
      height: auto;
      display: flex;
      flex-direction: column;
      overflow: hidden;
   }

   .post-cover {
      overflow: hidden;
      width: 100%;
      height: 230px;
   }

   .post-info {
      width: 100%;
      display: flex;
      flex-direction: column;
      justify-content: center;
   }
}
</style>