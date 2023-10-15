<script setup lang="ts">
import {
  NButton,
  NCheckbox,
  NIcon,
  NInput,
  NPagination,
  NPopconfirm,
  NSelect,
  NSpace,
  NTable,
  NTabPane,
  NTabs,
  useMessage
} from 'naive-ui'
import {SearchOutline} from '@vicons/ionicons5'
import {onMounted, ref, watch} from "vue";
import type {ArticleDTO, SelectOption} from "@/type";
import ArticleTr from "@/components/ArticleTr";
import DraftTr from "@/components/ArticleTr/DraftTr";
import DelTr from "@/components/ArticleTr/DelTr";
import {useDeletedIdsStore} from "@/stores/DeletedIdsStore";
import api from "@/api";

const message = useMessage()
const articleCount = ref<bigint>(0n)
const GetCount = () => {
  if (selectType.value == "已发布") {
    GetPublishCount()
  } else if (selectType.value == '草稿箱') {
    GetDraftCount()
  } else if (selectType.value == '回收站') {
    GetDelCount()
  }
}
const GetPublishCount = () => {
  api.getArticleCount().then(({data: {data}}) => {
    articleCount.value = data
  })
}
const GetPublishCountByCategory = () => {
  api.getArticleCountByCategoryId(BigInt(categoryValue.value)).then(({data: {data}}) => {
    articleCount.value = data
  })
}
const GetDraftArticleCountByCategory = () => {
  api.getDraftArticleCountByCategoryId(BigInt(categoryValue.value)).then(({data: {data}}) => {
    articleCount.value = data
  })
}
const GetDelArticleCountByCategory = () => {
  api.getDelArticleCountByCategoryId(BigInt(categoryValue.value)).then(({data: {data}}) => {
    articleCount.value = data
  })
}
const GetPublishArticleCountByTag = () => {
  api.getArticleCountByTagId(BigInt(tagValue.value)).then(({data: {data}}) => {
    articleCount.value = data
  })
}
const GetDraftArticleCountByTag = () => {
  api.getDraftArticleCountByTagId(BigInt(tagValue.value)).then(({data: {data}}) => {
    articleCount.value = data
  })
}
const GetDelArticleCountByTag = () => {
  api.getDelArticleCountByTagId(BigInt(tagValue.value)).then(({data: {data}}) => {
    articleCount.value = data
  })
}
const GetPublishArticleCountByTitle = () => {
  api.getArticleCountByTitle(titleValue.value).then(({data: {data}}) => {
    articleCount.value = data
  })
}
const GetDraftArticleCountByTitle = () => {
  api.getArticleCountByTitle(titleValue.value).then(({data: {data}}) => {
    articleCount.value = data
  })
}
const GetDelArticleCountByTitle = () => {
  api.getArticleCountByTitle(titleValue.value).then(({data: {data}}) => {
    articleCount.value = data
  })
}
const GetDraftCount = () => {
  api.getDraftArticleCount().then(({data: {data}}) => {
    articleCount.value = data
  })
}
const GetDelCount = () => {
  api.getDelArticleCount().then(({data: {data}}) => {
    articleCount.value = data
  })
}
const GetArticleList = () => {
  api.getArticleList(BigInt(page.value)).then(({data: {data}}) => {
    articles.value = data
  })
}
const GetArticleDraftList = () => {
  api.getArticleDraftList(BigInt(page.value)).then(({data: {data}}) => {
    articles.value = data
  })
}
const GetArticleDelList = () => {
  api.getArticleDelList(BigInt(page.value)).then(({data: {data}}) => {
    articles.value = data
  })
}
const GetArticleListByCategoryId = () => {
  api.getArticleListByCategoryId(BigInt(categoryValue.value), BigInt(page.value)).then(({data: {data}}) => {
    articles.value = data
  })
}

const GetArticleDraftListByCategoryId = () => {
  api.getArticleDraftListByCategoryId(BigInt(categoryValue.value), BigInt(page.value)).then(({data: {data}}) => {
    articles.value = data
  })
}
const GetArticleDelListByCategoryId = () => {
  api.getArticleDelListByCategoryId(BigInt(categoryValue.value), BigInt(page.value)).then(({data: {data}}) => {
    articles.value = data
  })
}
const GetArticleListByTagId = () => {
  api.getArticleListByTagId(BigInt(tagValue.value), BigInt(page.value)).then(({data: {data}}) => {
    articles.value = data
  })
}
const GetArticleDraftListByTagId = () => {
  api.getArticleDraftListByTagId(BigInt(tagValue.value), BigInt(page.value)).then(({data: {data}}) => {
    articles.value = data
  })
}
const GetArticleDelListByTagId = () => {
  api.getArticleDelListByTagId(BigInt(tagValue.value), BigInt(page.value)).then(({data: {data}}) => {
    articles.value = data
  })
}
const titleValue = ref("")
const GetArticleListByTitle = () => {
  api.getArticleListByTitle(titleValue.value, BigInt(page.value)).then(({data: {data}}) => {
    articles.value = data
  })
}
const GetArticleDraftListByTitle = () => {
  api.getArticleDraftListByTitle(titleValue.value, BigInt(page.value)).then(({data: {data}}) => {
    articles.value = data
  })
}
const GetArticleDelListByTitle = () => {
  api.getArticleDelListByTitle(titleValue.value, BigInt(page.value)).then(({data: {data}}) => {
    articles.value = data
  })
}

const page = ref(1)
const articles = ref<Array<ArticleDTO>>(new Array<ArticleDTO>())
const allChecked = ref(false)
const deletedIdsStore = useDeletedIdsStore()
const categoryOption = ref<Array<{
  label: String,
  value: String
}>>(new Array<{ label: String; value: String }>())
const tagOption = ref<Array<{
  label: String,
  value: String
}>>(new Array<{ label: String; value: String }>())
const categoryValue = ref<String | null>(null)
const tagValue = ref<String | null>(null)
const GetArticles = () => {
  if (selectType.value == "已发布") {
    GetArticleList()
  } else if (selectType.value == '草稿箱') {
    GetArticleDraftList()
  } else if (selectType.value == '回收站') {
    GetArticleDelList()
  }
  GetCount()
}
const GetArticlesByCategoryId = () => {
  tagValue.value = null
  if (selectType.value == "已发布") {
    GetArticleListByCategoryId()
    GetPublishCountByCategory()
  } else if (selectType.value == '草稿箱') {
    GetArticleDraftListByCategoryId()
    GetDraftArticleCountByCategory()
  } else if (selectType.value == '回收站') {
    GetArticleDelListByCategoryId()
    GetDelArticleCountByCategory()
  }

}
const GetArticlesByTagId = () => {
  categoryValue.value = null
  if (selectType.value == "已发布") {
    GetArticleListByTagId()
    GetPublishArticleCountByTag()
  } else if (selectType.value == '草稿箱') {
    GetArticleDraftListByTagId()
    GetDraftArticleCountByTag()
  } else if (selectType.value == '回收站') {
    GetArticleDelListByTagId()
    GetDelArticleCountByTag()
  }
}
const GetArticlesByTitle = () => {
  if (tagValue.value == "") return
  tagValue.value = null
  categoryValue.value = null
  if (selectType.value == "已发布") {
    GetArticleListByTitle()
    GetPublishArticleCountByTitle()
  } else if (selectType.value == '草稿箱') {
    GetArticleDraftListByTitle()
    GetDraftArticleCountByTitle()
  } else if (selectType.value == '回收站') {
    GetArticleDelListByTitle()
    GetDelArticleCountByTitle()
  }
}
const tabsChange = (value: String) => {
  page.value = 1
  allChecked.value = false
  selectType.value = value
  GetArticles()
}
const selectType = ref<String>("已发布")

const categories = ref<Array<SelectOption>>()
const GetCategories = () => {
  api.getCategoriesCard().then(({data: {data}}) => {
    categories.value = data
    categories.value!.forEach(category => {
      categoryOption.value?.push({
        label: category.name,
        value: category.id.toString()
      })
    })
  })
}
const tags = ref<Array<SelectOption>>()
const GetTags = () => {
  api.getTags().then(({data: {data}}) => {
    tags.value = data
    tags.value!.forEach(category => {
      tagOption.value?.push({
        label: category.name,
        value: category.id.toString()
      })
    })
  })
}
const DeleteArticles = () => {
  if (selectType.value == "已发布") {
    api.delArticles(Array.from(deletedIdsStore.ids)).then(({data}) => {
      if (data.code == 10000) {
        message.success("操作成功")
        GetArticles()
      } else if(data.code==30001){
        message.error("权限不足")
      }
        else{
        message.error("操作失败")
      }
    })
  } else {
    api.delArticlesPhysics(Array.from(deletedIdsStore.ids)).then(({data}) => {
      if (data.code == 10000) {
        message.success("操作成功")
        GetArticles()
      }  else if(data.code==30001){
        message.error("权限不足")
      } else {
        message.error("操作失败")
      }
    })
  }
  deletedIdsStore.ids.clear()
}
onMounted(() => {
  GetArticleList()
  GetCount()
  GetCategories()
  GetTags()
})
watch(() => page.value, () => {
      if (categoryValue.value) {
        GetArticlesByCategoryId()
      } else if (tagValue.value) {
        GetArticlesByTagId()
      } else if (titleValue.value != "") {
        GetArticlesByTitle()
      } else {
        GetArticles()
      }
    }
)
</script>

<template>

  <n-space class="option-container" justify="space-between">
    <n-popconfirm
        negative-text="取消"
        positive-text="是的"
        @positive-click="DeleteArticles"
    >
      <template #trigger>
        <n-button type="error" :disabled="deletedIdsStore.btnDisabled()">批量删除</n-button>
      </template>
      确认删除？
    </n-popconfirm>
    <div class="select-container">
      <n-space>
        <n-select placeholder="请选择文章分类" style="width: 200px" v-model:value="categoryValue"
                  :options="categoryOption!" @update:value="GetArticlesByCategoryId"/>
        <n-select placeholder="请选择文章标签" style="width: 200px" v-model:value="tagValue" :options="tagOption!"
                  @update:value="GetArticlesByTagId"/>
        <n-input placeholder="输入文章标题查找" style="width: 200px" v-model:value="titleValue">
          <template #prefix>
            <n-icon size="tiny">
              <SearchOutline/>
            </n-icon>
          </template>
        </n-input>
        <n-button type="info" @click="GetArticlesByTitle">搜索</n-button>
      </n-space>
    </div>
  </n-space>

  <n-tabs @update-value="tabsChange">
    <template #prefix>状态</template>
    <n-tab-pane name="已发布" tab="已发布">
      <n-table :single-line="true" :bordered="false" size="large">
        <thead>
        <tr>
          <th>
            <n-checkbox v-model:checked="allChecked"/>
          </th>
          <th>文章封面</th>
          <th>标题</th>
          <th>分类</th>
          <th>标签</th>
          <th>浏览量</th>
          <th>发表时间</th>
          <th>置顶</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <ArticleTr v-for="article in articles" :article="article" :key="Number(article.id)" :all-checked="allChecked"
                   @delete="GetArticles()"/>
        </tbody>
      </n-table>
    </n-tab-pane>
    <n-tab-pane name="草稿箱" tab="草稿箱">
      <n-table :single-line="true" single-column :bordered="false" size="large">
        <thead>
        <tr>
          <th>
            <n-checkbox v-model:checked="allChecked"/>
          </th>
          <th>文章封面</th>
          <th>标题</th>
          <th>分类</th>
          <th>标签</th>
          <th>浏览量</th>
          <th>发表时间</th>
          <th>置顶</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <DraftTr v-for="article in articles" :article="article" :all-checked="allChecked" @delete="GetArticles"/>
        </tbody>
      </n-table>
    </n-tab-pane>
    <n-tab-pane name="回收站" tab="回收站">
      <n-table :single-line="true" single-column :bordered="false" size="large">
        <thead>
        <tr>
          <th>
            <n-checkbox v-model:checked="allChecked"/>
          </th>
          <th>文章封面</th>
          <th>标题</th>
          <th>分类</th>
          <th>标签</th>
          <th>浏览量</th>
          <th>发表时间</th>
          <th>置顶</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <DelTr v-for="article in articles" :article="article" :all-checked="allChecked" @click="GetArticles"/>
        </tbody>

      </n-table>
    </n-tab-pane>
  </n-tabs>
  <div class="pagination-container">
    <n-pagination v-model:page="page" :page-count="Math.ceil(Number(articleCount)/10)">
      <template #prefix>
        共{{ articleCount }}条
      </template>
    </n-pagination>
  </div>
</template>

<style scoped>
.pagination-container {
  display: flex;
  width: 100%;
  height: 50px;
  justify-content: center;
  align-items: center;
}

.option-container {
  width: 100%;
  padding: 10px;
}

.select-container {
  padding-right: 20px;
}
</style>