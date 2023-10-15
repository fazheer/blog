<script setup lang="ts">
import {ref, watch} from "vue";
import {NButton, NCard, NCheckbox, NInput, NModal, NPopconfirm, NSpace, useMessage} from 'naive-ui'
import 'md-editor-v3/lib/style.css';
import type {Tag} from "@/type";
import {useDeletedIdsStore} from "@/stores/DeletedIdsStore";
import api from "@/api";

const props = defineProps<{
  tag: Tag
  allChecked: boolean
}>()
const deletedIdsStore = useDeletedIdsStore()
const emit = defineEmits(['delete'])
const checked = ref(false)
const message = useMessage()
watch(() => props.allChecked, () => checked.value = props.allChecked)
watch(() => checked.value, () => {
  if (checked.value) {
    if (deletedIdsStore.ids.has(props.tag.id)) {
      return
    } else {
      deletedIdsStore.ids.add(props.tag.id)
    }
  } else {
    deletedIdsStore.ids.delete(props.tag.id)
  }
})
const tagName = ref(props.tag.name)
const newTagName = ref("")
const updateModalShow = ref(false)
const updateTag = ()=>{
  if(newTagName.value=="") {
    message.error("请输入新标签名！")
    return
  }
  api.updateTag(props.tag.id,newTagName.value).then(({data})=>{
    if (data.code == 10000) {
      message.success("操作成功")
      tagName.value=newTagName.value
    } else if (data.code == 30001) {
      message.error("权限不足")
    } else {
      message.error("操作失败")
    }
    updateModalShow.value=false
    newTagName.value=""
  })
}
const DelTag =()=>{
  api.delTag([props.tag.id]).then(({data})=>{
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
    <td><n-checkbox v-model:checked="checked" @on-update:checked="checked=!checked"/> </td>
    <td>{{tagName}}</td>
    <td>{{ tag.count }}</td>
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
              <n-input placeholder="请输入新标签名" v-model:value="newTagName"/>
            </n-space>
            <template #footer>
              <n-space justify="end" align="center">
                <n-button @click="updateModalShow=false">取消</n-button>
                <n-button type="info" @click="updateTag">修改</n-button>
              </n-space>
            </template>
          </n-card>
        </n-modal>
        <n-popconfirm
            negative-text="取消"
            positive-text="确定"
            @positive-click="DelTag"
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