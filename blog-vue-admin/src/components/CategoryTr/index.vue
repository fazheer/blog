<script setup lang="ts">
import {ref, watch} from "vue";
import {NButton, NCard, NCheckbox, NInput, NModal, NPopconfirm, NSpace, useMessage} from 'naive-ui'
import 'md-editor-v3/lib/style.css';
import type {CategoryCount} from "@/type";
import {useDeletedIdsStore} from "@/stores/DeletedIdsStore";
import api from "@/api";

const props = defineProps<{
  category: CategoryCount
  allChecked: boolean
}>()
const deletedIdsStore = useDeletedIdsStore()
const updateModalShow =ref(false)
const checked = ref(false)
const newCategoryName = ref("")
const categoryName = ref(props.category.name)
watch(() => props.allChecked, () => checked.value = props.allChecked)
watch(() => checked.value, () => {
  if (checked.value) {
    if (deletedIdsStore.ids.has(props.category.id)) {
      return
    } else {
      deletedIdsStore.ids.add(props.category.id)
    }
  } else {
    deletedIdsStore.ids.delete(props.category.id)
  }
})
const updateCategory = ()=>{
  if(newCategoryName.value=="") {
    message.error("请输入新分类名！")
    return
  }
  api.updateCategory(props.category.id,newCategoryName.value).then(({data})=>{
    if (data.code == 10000) {
      message.success("操作成功")
      categoryName.value=newCategoryName.value
    } else if (data.code == 30001) {
      message.error("权限不足")
    } else {
      message.error("操作失败")
    }
    updateModalShow.value=false
    newCategoryName.value=""
  })
}
const emit = defineEmits(['delete']);
const message = useMessage()
const delCategory = () => {
  api.delCategories([props.category.id]).then(({data}) => {
    if (data.code == 10000) {
      message.success("删除成功")
      emit('delete')
    } else if (data.code == 30001) {
      message.error("权限不足")
    } else {
      message.error("操作失败")
    }
  })
}


</script>

<template>
  <tr>
    <td>
      <n-checkbox v-model:checked="checked" @on-update:checked="checked=!checked"/>
    </td>
    <td>{{ categoryName }}</td>
    <td>{{ category.count }}</td>
    <td>
      <n-space>
        <n-button type="info" @click="updateModalShow=true">编辑</n-button>
        <n-modal v-model:show="updateModalShow">
          <n-card
              title="修改分类"
              size="small"
              style="height: 200px;width: 400px"
          >
            <n-space align="center">
              分类名
              <n-input placeholder="请输入新分类名" v-model:value="newCategoryName"/>
            </n-space>
            <template #footer>
              <n-space justify="end" align="center">
                <n-button @click="updateModalShow=false">取消</n-button>
                <n-button type="info" @click="updateCategory">修改</n-button>
              </n-space>
            </template>
          </n-card>
        </n-modal>
        <n-popconfirm
            negative-text="取消"
            positive-text="确定"
            @positive-click="delCategory"
        >
          <template #trigger>
            <n-button type="error">删除</n-button>
          </template>
          确认删除吗
        </n-popconfirm>
      </n-space>
    </td>
  </tr>


</template>

<style scoped>

</style>