<script setup lang="ts">
import {NButton, NCheckbox, NIcon, NInput, NPagination, NPopconfirm, NSpace, NTable, useMessage} from 'naive-ui'
import {onMounted, ref, watch} from "vue";
import CommentTr from "@/components/CommentTr";
import {useDeletedIdsStore} from "@/stores/DeletedIdsStore";
import type {Comment} from '@/type'
import {SearchOutline} from "@vicons/ionicons5";
import api from "@/api";

const message = useMessage()
const deletedIdsStore = useDeletedIdsStore()
const allChecked = ref(false)
const page = ref(1)
const comments = ref<Array<Comment>>(new Array<Comment>())
const commentCount = ref<bigint>(0n)
const GetComments = () => {
  api.getComments(BigInt(page.value)).then(({data: {data}}) => {
    comments.value = data
  })
  GetCount()
}
const GetCount = () => {
  api.getCommentsCount().then(({data: {data}}) => {
    commentCount.value = data
  })
}
const DelComments = () => {
  api.delComments(Array.from(deletedIdsStore.ids)).then(({data}) => {
    if (data.code == 10000) {
      message.success("删除成功！")
      GetComments()
    } else {
      message.error("操作失败！")
    }
  })
  GetCount()
}
onMounted(() => {
  GetComments()
})
watch(() => page.value, () => {
  GetComments()
})
</script>

<template>
  <div class="option-container">
    <n-space justify="space-between">
      <n-space>
        <n-popconfirm
            negative-text="取消"
            positive-text="是的"
            @positive-click="DelComments"
        >
          <template #trigger>
            <n-button type="error" :disabled="deletedIdsStore.btnDisabled()">批量删除</n-button>
          </template>
          确认删除？
        </n-popconfirm>
      </n-space>
      <div class="input-container">
        <n-space>
          <n-input placeholder="输入内容查找" style="width: 200px">
            <template #prefix>
              <n-icon size="tiny">
                <SearchOutline/>
              </n-icon>
            </template>
          </n-input>
          <n-button type="info">搜索</n-button>
        </n-space>
      </div>
    </n-space>

  </div>
  <n-table :single-line="true" single-column :bordered="false" size="large">
    <thead>
    <tr>
      <th>
        <n-checkbox v-model:checked="allChecked"/>
      </th>
      <th>头像</th>
      <th>昵称</th>
      <th>邮箱</th>
      <th>网址</th>
      <th>内容</th>
      <th>点赞量</th>
      <th>发表时间</th>
      <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <CommentTr v-for="comment in comments" :comment="comment" :all-checked="allChecked" @delete="GetComments"/>
    </tbody>

  </n-table>
  <div class="pagination-container">
    <n-pagination v-model:page="page" :page-count="Math.ceil(Number(commentCount)/10)" size="large"/>
  </div>
</template>

<style scoped>
.option-container {
  width: 100%;
  padding: 0 0 10px 10px;
}

.input-container {
  display: flex;
  padding-right: 20px;
}

.pagination-container {
  display: flex;
  width: 100%;
  height: 50px;
  justify-content: center;
  align-items: center;
}
</style>