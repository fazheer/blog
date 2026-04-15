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
  type: 'published' | 'draft' | 'deleted'
}>()
const emit = defineEmits(['refresh'])
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

const handleDelete = () => {
  if (props.type === 'published') {
    api.delArticle(props.article.id).then(({data}) => {
      if(data.code==10000) {
        message.success("删除成功！")
        emit("refresh")
      }else if(data.code==30001) {
        message.error("权限不足")
      }
    })
  } else {
    api.delArticlesPhysics([props.article.id]).then(({data}) => {
      if(data.code==10000) {
        message.success("删除成功！")
        emit("refresh")
      } else {
        message.error("操作失败！")
      }
    })
  }
}

const handleRecovery = () => {
  api.recoverArticle(props.article.id).then(({data}) => {
    if(data.code==10000) {
      message.success("恢复成功！")
      emit("refresh")
    } else {
      message.error("操作失败！")
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
      <n-switch v-if="type === 'published'" v-model:value="isTop" @update:value="handleSwitch" :loading="loading"/>
    </td>
    <td>
      <n-space>
        <template v-if="type === 'published'">
          <n-button type="info"
                    @click="VRouter.push({
                    path:`/update/${article.id}`
                    })"
          >编辑</n-button>
          <n-popconfirm
              negative-text="取消"
              positive-text="是的"
              @positive-click="handleDelete"
          >
            <template #trigger>
              <n-button type="error">删除</n-button>
            </template>
            确认删除？
          </n-popconfirm>
        </template>
        <template v-else-if="type === 'draft'">
          <n-button type="info"
                    @click="VRouter.push({
                    path:`/update/${article.id}`
                    })"
          >编辑</n-button>
          <n-popconfirm
              negative-text="取消"
              positive-text="是的"
              @positive-click="handleDelete"
          >
            <template #trigger>
              <n-button type="error">删除</n-button>
            </template>
            确认删除？
          </n-popconfirm>
        </template>
        <template v-else-if="type === 'deleted'">
          <n-popconfirm
              negative-text="取消"
              positive-text="是的"
              @positive-click="handleRecovery"
          >
            <template #trigger>
              <n-button type="success">恢复</n-button>
            </template>
            确认恢复？
          </n-popconfirm>
          <n-popconfirm
              negative-text="取消"
              positive-text="是的"
              @positive-click="handleDelete"
          >
            <template #trigger>
              <n-button type="error">删除</n-button>
            </template>
            确认删除？
          </n-popconfirm>
        </template>
      </n-space>
    </td>
  </tr>
</template>

<style scoped>

</style>
