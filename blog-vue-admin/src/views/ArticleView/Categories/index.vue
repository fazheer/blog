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
import {onMounted, ref, watch} from "vue";
import CategoryTr from "@/components/CategoryTr";
import {useDeletedIdsStore} from "@/stores/DeletedIdsStore";
import type {CategoryCount} from "@/type";
import {SearchOutline} from "@vicons/ionicons5";
import api from "@/api";

const deletedIdsStore = useDeletedIdsStore()

const page = ref(1)
const categoryCount = ref<bigint>(0n)
const categories = ref<Array<CategoryCount>>()
const allChecked = ref(false)
const message = useMessage()
const inputValue = ref("")
const addModalShow = ref(false)
const newCategoryName = ref()
const searchValue = ref("")
const GetCategories = () => {
  api.getCategoryCard(BigInt(page.value)).then(({data: {data}}) => {
    categories.value = data
  })
}
const SearchCategory = ()=>{
  console.log(searchValue.value)
  if(searchValue.value=="") {
    message.error("请输入分类名！")
    return
  }
  api.searchCategory(searchValue.value,BigInt(page.value)).then(({data:{data}})=>{
    categories.value=data
    GetSearchCategoryCount()
  })
}
const GetSearchCategoryCount = ()=>{
  api.getSearchCategoryCount(searchValue.value).then(({data:{data}})=>{
    categoryCount.value=data
  })
}
const DelCategories = () => {
  api.delCategories(Array.from(deletedIdsStore.ids)).then(({data}) => {
    if (data.code == 10000) {
      message.success("操作成功")
      GetCategories()
    } else if (data.code == 30001) {
      message.error("权限不足")
    } else {
      message.error("操作失败")
    }
    deletedIdsStore.ids.clear()
  })
}
const GetCategoriesCount = () => {
  api.getCategoryCardCount().then(({data: {data}}) => {
    categoryCount.value = data
  })
}
const SaveCategory =  ()=>{
  if(newCategoryName.value=="") {
    message.error("请输入分类名!")
    return;
  }else {
    api.saveCategory(newCategoryName.value).then(({data})=>{
      if (data.code == 10000) {
        message.success("操作成功")
        GetCategories()
        addModalShow.value=false
      } else if (data.code == 30001) {
        message.error("权限不足")
      } else {
        message.error("操作失败")
      }
    })
  }
}
onMounted(() => {
  GetCategories()
  GetCategoriesCount()
})
watch(() => page.value, () => GetCategories())
</script>

<template>
  <div class="option-container">
    <n-space justify="space-between">
      <n-space>
        <n-button type="error" @click="addModalShow=true">新增</n-button>
        <n-modal v-model:show="addModalShow">
          <n-card
              title="添加分类"
              size="small"
              style="height: 200px;width: 400px"
          >
            <n-space align="center">
              分类名
              <n-input placeholder="请输入新分类名" v-model:value="newCategoryName"/>
            </n-space>
            <template #footer>
              <n-space justify="end" align="center">
                <n-button @click="addModalShow=false">取消</n-button>
                <n-button type="info" @click="SaveCategory">添加</n-button>
              </n-space>
            </template>
          </n-card>
        </n-modal>
        <n-popconfirm
            negative-text="取消"
            positive-text="是的"
            @positive-click="DelCategories"
        >
          <template #trigger>
            <n-button type="error" :disabled="deletedIdsStore.btnDisabled()">批量删除</n-button>
          </template>
          确认删除？
        </n-popconfirm>
      </n-space>
      <div class="input-container">
        <n-space>
          <n-input placeholder="输入分类名查找" style="width: 200px" v-model:value="searchValue">
            <template #prefix>
              <n-icon size="tiny">
                <SearchOutline/>
              </n-icon>
            </template>
          </n-input>
          <n-button  type="info" @click="SearchCategory">搜索</n-button>
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
      <th>分类名称</th>
      <th>文章数量</th>
      <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <CategoryTr v-for="category in categories" :key="category.id.toString()" :category="category"
                :all-checked="allChecked" @delete="GetCategories()"/>
    </tbody>

  </n-table>
  <div class="pagination-container">
    <n-pagination v-model:page="page" :page-count="Math.ceil(Number(categoryCount)/10)" size="large">
      <template #prefix>
        共{{categoryCount}}条数据
      </template>
    </n-pagination>
  </div>
</template>

<style scoped>
.option-container {
  width: 100%;
  padding: 0 0 10px 10px;
}

.pagination-container {
  display: flex;
  width: 100%;
  height: 50px;
  justify-content: center;
  align-items: center;
}

.input-container {
  display: flex;
  padding-right: 20px;
}
</style>