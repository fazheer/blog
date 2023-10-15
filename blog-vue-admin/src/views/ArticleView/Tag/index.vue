<script setup lang="ts">
import {
  NButton,
  NCard,
  NCheckbox,
  NIcon,
  NInput,
  NModal,
  NPagination,
  NPopconfirm,
  NSpace,
  NTable,
  useMessage
} from 'naive-ui'
import TagTr from "@/components/TagTr";
import {onMounted, ref, watch} from "vue";
import type {Tag} from "@/type";
import {SearchOutline} from "@vicons/ionicons5";
import {useDeletedIdsStore} from "@/stores/DeletedIdsStore";
import api from "@/api";

const deletedIdsStore = useDeletedIdsStore()
const page = ref(1)
const  allChecked = ref(false)
const tagCount = ref<bigint>(0n)
const message = useMessage()
const searchValue = ref("")
const GetTags = ()=>{
  api.getTagsCard(BigInt(page.value)).then(({data:{data}})=>{
    tags.value=data
  })
  GetTagsCount()
}
const newTagName = ref("")
const addModalShow = ref(false)
const SaveTag =  ()=>{
  if(newTagName.value=="") {
    message.error("请输入分类名!")
    return;
  }else {
    api.saveTag(newTagName.value).then(({data})=>{
      if (data.code == 10000) {
        message.success("操作成功")
        GetTags()
        addModalShow.value=false
      } else if (data.code == 30001) {
        message.error("权限不足")
      } else {
        message.error("操作失败")
      }
    })
  }
}
const SearchTag = ()=>{
  if(searchValue.value=="") {
    message.error("请输入标签名！")
    return
  }
  api.searchTag(searchValue.value,BigInt(page.value)).then(({data:{data}})=>{
    tags.value=data
    GetSearchTagCount()
  })
}
const GetSearchTagCount = ()=>{
  api.getSearchTagCount(searchValue.value).then(({data:{data}})=>{
    tagCount.value=data
  })
}
const DelTags = ()=>{
  api.delTag(Array.from(deletedIdsStore.ids)).then(({data})=>{
    if (data.code == 10000) {
      message.success("操作成功")
      GetTags()
    } else if(data.code==30001){
      message.error("权限不足")
    }
    else{
      message.error("操作失败")
    }
    deletedIdsStore.ids.clear()
  })
}
const GetTagsCount = ()=>{
  api.getTagsCount().then(({data:{data}})=>{
    tagCount.value=data
  })
}
watch(()=>page.value,()=>{
  GetTags()
})
const tags  = ref<Array<Tag>>()
onMounted(()=>{
  GetTags()
})
</script>

<template>
  <div class="option-container">
    <n-space justify="space-between">
      <n-space>
        <n-button type="error" @click="addModalShow=true">新增</n-button>
        <n-modal v-model:show="addModalShow">
          <n-card
              title="添加标签"
              size="small"
              style="height: 200px;width: 400px"
          >
            <n-space align="center">
              标签名
              <n-input placeholder="请输入新标签名" v-model:value="newTagName"/>
            </n-space>
            <template #footer>
              <n-space justify="end" align="center">
                <n-button @click="addModalShow=false">取消</n-button>
                <n-button type="info" @click="SaveTag">添加</n-button>
              </n-space>
            </template>
          </n-card>
        </n-modal>
        <n-popconfirm
            negative-text="取消"
            positive-text="是的"
            @positive-click="DelTags"
        >
          <template #trigger>
            <n-button type="error" :disabled="deletedIdsStore.btnDisabled()">批量删除</n-button>
          </template>
          确认删除？
        </n-popconfirm>
      </n-space>
      <div class="input-container" >
        <n-space>
          <n-input placeholder="输入分类名查找" style="width: 200px" v-model:value="searchValue">
            <template #prefix>
              <n-icon size="tiny">
                <SearchOutline/>
              </n-icon>
            </template>
          </n-input>
          <n-button type="info" @click="SearchTag">搜索</n-button>
        </n-space>
      </div>
    </n-space>

  </div>
  <n-table :single-line="true" single-column :bordered="false" size="large" >
    <thead>
    <tr>
      <th><n-checkbox v-model:checked="allChecked"/> </th>
      <th>标签名称</th>
      <th>文章数量</th>
      <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <TagTr v-for="tag in tags" :tag="tag" :all-checked="allChecked" @deleted="GetTags"/>
    </tbody>
  </n-table>
  <div class="pagination-container">
    <n-pagination v-model:page="page" :page-count="Math.ceil(Number(tagCount)/10)" size="large">
      <template #prefix>
        共{{tagCount}}条
      </template>
    </n-pagination>
  </div>
</template>

<style scoped>
.option-container{
  width: 100%;
  padding: 0 0 10px 10px;
}
.input-container{
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