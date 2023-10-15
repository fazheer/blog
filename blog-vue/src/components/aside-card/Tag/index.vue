<script setup lang="ts">
import { PricetagOutline } from '@vicons/ionicons5'
import {NCard, NIcon ,NSpace,NGradientText} from "naive-ui";
import api from '@/api'
import {ref,onMounted} from "vue";
interface Tag {
    id:bigint,
    name:String
}
type itemType = "error" | "primary" | "info" | "success" | "warning" | "danger"
const tags = ref<Array<Tag>>(new Array<Tag>())
const typeData: String[] = ["error", "primary", "info", "success", "warning", "danger"]

function randomColor(): itemType {

  const index = Math.floor(Math.random() * 5 + 1);
  return typeData[index] as itemType
}
const GetTags = () =>{
  api.getTags().then(({data:{data}})=>{
    tags.value=data;
  })
}
onMounted(()=>{
  GetTags()
})
</script>
<template>
    <n-card hoverable embedded>
        <template #header>
            <div class="card-title">
                <n-icon size="18">
                    <PricetagOutline />
                </n-icon>
                <span>标签</span>
            </div>
        </template>
      <n-space>
        <router-link v-for="tag in tags" :to="`/tag/${tag.name}/${tag.id}`">
          <n-gradient-text class="tag-text" :type="randomColor()" style="font-size: 16px">
            {{ tag.name }}
          </n-gradient-text>
        </router-link>
      </n-space>
    </n-card>
</template>
<style scoped>
</style>