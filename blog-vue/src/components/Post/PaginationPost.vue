<template>
  <div class="pagination-post-container">
    <div class="prev-post" v-show="prevArticle!=null">
      <router-link :to="`/post/${prevArticle?.id}`">
        <img :src="`${prevArticle?.coverUrl}`" alt=""/>
        <div class="text-content">
          <div class="label">上一篇</div>
          <div class="prev-info">{{prevArticle?.title}}</div>
        </div>
      </router-link>
    </div>
    <div class="next-post" v-show="nextArticle!=null">
      <router-link :to="`/post/${nextArticle?.id}`">
        <img :src="`${nextArticle?.coverUrl}`" alt=""/>
        <div class="text-content">
          <div class="label">下一篇</div>
          <div class="next-info">{{nextArticle?.title}}</div>
        </div>
      </router-link>
    </div>
  </div>
</template>

<script lang="ts" setup>
import type {BaseArticleCard} from "@/type";

 defineProps<{
  prevArticle:BaseArticleCard|null,
  nextArticle:BaseArticleCard|null
}>()
</script>
<style scoped>
.pagination-post-container {
  width: 100%;
  margin-top: 50px;
  height: 150px;
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
  background-color: #000;
  overflow: hidden;

}

a {
  position: relative;
  width: 100%;
  height: 100%;
  display: block;
  overflow: hidden;
}

.prev-post {
  height: 100%;
  flex: 1;
  text-align: left;
}

.next-post {
  flex: 1;
  height: 100%;
  text-align: right;
}

.pagination-post-container img {
  width: 100%;
  height: 100%;
  opacity: 0.4;
  object-fit: cover;
}
.pagination-post-container :hover img {
  opacity: 0.8;
  transition: all 0.6s, filter 375ms ease-in 0.2s;
  transform: scale(1.1);
}

.label {
  color: #ffffff;
  text-transform: uppercase;
  font-size: 1rem;
  line-height: 2;
}
.text-content{
  position: absolute;
  top: 50%;
  padding: 20px 40px;
  width: 100%;
  -ms-transform: translate(0, -50%);
  transform: translate(0, -50%);
}
@media (max-width: 768px) {
  .pagination-post-container {
    width: 100%;
    margin-top: 50px;
    height: 300px;
    display: flex;
    flex-direction: column;
    flex-wrap: wrap;
    background-color: #000;
    overflow: hidden;

  }
  .prev-post {
    width: 100%;
    height: 50%;
    text-align: left;
  }

  .next-post {
    width: 100%;
    height: 50%;
    text-align: right;
  }

}
</style>