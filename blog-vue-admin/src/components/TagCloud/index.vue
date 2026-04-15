<script setup lang="ts">
import TagCloud from "TagCloud";
import {NCard} from 'naive-ui'
import {onMounted, onUnmounted, ref} from "vue";
import api from "@/api";

const container = '.tagCloud';

const tags = ref()
const tagCloudInstance = ref<any>(null)

const options = {};
const GetTags = ()=>{
  api.getTags().then(({data:{data}})=>{
    tags.value=Array.from(data, ({name}) => name)
    tagCloudInstance.value = TagCloud(container,tags.value , options);
  })
}
onMounted(()=>{
 GetTags()

})

onUnmounted(() => {
  const el = document.querySelector(container)
  if (el) {
    el.innerHTML = ''
  }
})

</script>

<template>
  <div class="item">
    <n-card embedded title="标签统计" >
      <template #default>
          <div class="tagCloud"/>
      </template>
    </n-card>
  </div>
</template>

<style scoped>
.item {
  width:28%;
  padding: 10px;
}
.tagCloud{
  width: 100%;
  height:350px;
  box-sizing: border-box;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
