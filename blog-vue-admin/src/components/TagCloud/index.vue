<script setup lang="ts">
import TagCloud from "TagCloud";
import {NCard} from 'naive-ui'
import {onMounted, ref} from "vue";
import api from "@/api";

const container = '.tagCloud';

const tags = ref()

const options = {
  containerClass:"display: flex;\n" +
      "  justify-content: center;\n" +
      "  align-items: center;"
};
const GetTags = ()=>{
  api.getTags().then(({data:{data}})=>{
    tags.value=Array.from(data, ({name}) => name)
    TagCloud(container,tags.value , options);
  })
}
onMounted(()=>{
 GetTags()

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
  padding-top: 50px;
  padding-left: 50px;
}
</style>