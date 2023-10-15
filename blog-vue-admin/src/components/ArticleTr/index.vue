<script setup lang="ts">
import {ref, watch} from "vue";
import {NButton, NCheckbox, NImage, NPopconfirm, NSpace, NSwitch, NTag, useMessage} from 'naive-ui'
import 'md-editor-v3/lib/style.css';
import type {ArticleDTO} from "@/type";
import {useDeletedIdsStore} from "@/stores/DeletedIdsStore";
import api from "@/api";
import VRouter from '@/router'

const props = defineProps<{
  article: ArticleDTO
  allChecked: boolean
}>()
const emit = defineEmits(['delete'])
const deletedIdsStore = useDeletedIdsStore()

const checked = ref(false)

watch(() => props.allChecked, () => checked.value = props.allChecked)
watch(() => checked.value, () => {
  if (checked.value) {
    if (deletedIdsStore.ids.has(props.article.id)) {
      return
    } else {
      deletedIdsStore.ids.add(props.article.id)
    }
  } else {
    deletedIdsStore.ids.delete(props.article.id)
  }
})
const isTop = ref<boolean>(props.article.isTop)
const loading = ref<boolean>(false)
const message = useMessage()
const handleSwitch = () => {
  api.updateTop(isTop.value, props.article.id).then(({data}) => {
    loading.value = true
    if(data.code==10000) {
      message.success("操作成功")
    }else {
      message.error("操作失败")
    }
      loading.value=false
  })
}
const handleYes = ()=>{
  api.delArticle(props.article.id).then(({data})=>{
    if(data.code==10000) {
      message.success("删除成功！")
      emit("delete")
    }else if(data.code==30001) {
      message.error("权限不足")
    }
  })
}
</script>

<template>
  <tr>
    <td>
      <n-checkbox v-model:checked="checked" @on-update:checked="checked=!checked"/>
    </td>
    <td style="width: 50px;height: 10px;">
      <n-image :src="article.coverUrl" style="zoom: 0.1"/>
    </td>
    <td>{{ article.title }}</td>
    <td>{{ article.categoryName }}</td>
    <td>
      <n-space>
        <n-tag type="error" v-for="tag in article.tags">{{ tag }}</n-tag>
      </n-space>
    </td>
    <td>{{ article.views }}</td>
    <td>{{ article.createAt }}</td>
    <td>
      <n-switch v-model:value="isTop" @update:value="handleSwitch" :loading="loading"/>
    </td>
    <td>
      <n-space>
        <n-button type="info"
                  @click="VRouter.push({
                  path:`/update/${article.id}`
                  })"
        >编辑</n-button>
        <n-popconfirm
            negative-text="取消"
            positive-text="是的"
            @negative-click=""
            @positive-click="handleYes"
        >
        <template #trigger>
        <n-button type="error">删除</n-button>
        </template>
          确认删除？
        </n-popconfirm>
      </n-space>
    </td>
  </tr>


</template>

<style scoped>

</style>