<template>
    <n-timeline-item class="item-container"
                     :type="computedType"
                     :time="props.article.createAt!"
                     :title="$props.article.title!"
                     @mouseover="mouseover()"
                     @mouseout="mouseout()"
                     @click="handleClick"
    >
      <div class="img-container" v-show="props.article.coverUrl!=''">
        <!--suppress HtmlRequiredAltAttribute -->
      <img :src="$props.article.coverUrl" style="zoom: 0.1;" />
      </div>
    </n-timeline-item>
</template>
<style scoped>
.item-container {
  cursor: pointer;
}

.img-container{
  width: 20%;

  overflow: hidden;
}

img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.item-container:hover img{
  transition: filter 375ms ease-in 0.2s, transform 0.6s;
  object-fit: cover;
  transform: scale(1.1);
}


</style>
<script setup lang="ts">
import {computed, ref} from 'vue';
import VRouter from '@/router'
import 'md-editor-v3/lib/style.css';
import {  NTimelineItem, } from 'naive-ui';
import type {BaseArticleCard} from "@/type";
const props = defineProps<{
    article:BaseArticleCard,
}>()
const itemType = ref<'default' | 'success' | 'info' | 'warning' | 'error'>("info")
const computedType = computed(()=>{
  if(props.article.coverUrl=="") itemType.value="error"
  return itemType.value
})
const mouseover = ()=>{
    itemType.value="error"
}
const mouseout = ()=>{
    itemType.value="info"
}
const handleClick = ()=>{
  if(props.article.coverUrl!="") {
    VRouter.push({
      path:`/post/${props.article.id}`
    })
  }
}

</script>