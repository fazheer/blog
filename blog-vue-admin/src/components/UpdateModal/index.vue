<script setup lang="ts">
import type {UploadFileInfo} from "naive-ui";
import {
  NButton,
  NCard,
  NInput,
  NModal,
  NPopover,
  NPopselect,
  NSpace,
  NSwitch,
  NTag,
  NText,
  NUpload,
  NUploadDragger,
  useMessage
} from 'naive-ui'
import jsonBig from 'json-bigint/index'
import {useTokenStore} from "@/stores/useTokenStore";
import {useModelShowStore} from "@/stores/useModalShowStore";
import {onMounted, ref, watch} from "vue";
import api from "@/api";
import type {ArticleVO} from "@/type";

const props = defineProps<{
 article:ArticleVO
}>()
watch(()=>props.article,()=>{
  categoryName.value=props.article.categoryName
  tags.value = new Set<String>(props.article.tags)
})
const newCategoryName = ref("")
const categoryName = ref(props.article.categoryName)
const modalShow = useModelShowStore()
const tags = ref<Set<String>>(new Set<String>(props.article.tags))
const categoryOption = ref<Array<{
  label: String,
  value: String
}>>(new Array<{ label: String; value: String }>())
const tagOption = ref<Array<{
  label: String,
  value: String
}>>(new Array<{ label: String; value: String }>())
const handleCloseTag = (tag: String) => {
  tags.value.delete(tag)
}
const newTagName = ref("")
const handleAddTag = () => {
  if (newTagName.value == "") {
    message.warning("请输入新分类名!")
    return
  }
  tags.value.add(newTagName.value)
  newTagName.value = ""
}
const handleAddCategory = () => {
  if (newCategoryName.value == "") {
    message.warning("请输入新分类名!")
    return
  }
  categoryName.value = newCategoryName.value
}
const UpdateArticle = () => {
  if(categoryName.value=="") {
    message.error("分类名不能为空！")
    return
  }
  if(tags.value.size==0) {
    message.error("分类名不能为空！")
    return
  }
  props.article.categoryName=categoryName.value
  props.article.tags=Array.from(tags.value)
  api.updateArticle(props.article).then(({data: data}) => {
    if (data.code == 10000) {
      message.success("修改成功！")
    } else if (data.code == 30001) {
      message.error("没有权限!")
    } else {
      message.error("操作失败")
    }
    modalShow.showUpdate=false
  })
}

const GetTags = () => {
  api.getTags().then(({data: {data}}) => {
    const tags = data as Array<{ name: String }>
    tags.forEach(tag => {
      tagOption.value.push({
        label: tag.name,
        value: tag.name
      })
    })
  })
}
const GetCategories = () => {
  api.getCategories().then(({data: {data}}) => {
    const categories = data as Array<{ name: String }>
    categories.forEach(tag => {
      categoryOption.value.push({
        label: tag.name,
        value: tag.name
      })
    })
  })
}

const message = useMessage()
const handleUploadFinish = (options: {
  file: UploadFileInfo
  event?: ProgressEvent
}) => {
  if (options.file.status == 'finished') {
    const response = (options.event?.target as XMLHttpRequest).response
    const data = jsonBig.parse(
        response)
    if (data.code == 10000) {
      message.success("添加成功！")
    } else if (data.code == 30001) {
      message.error("没有权限!")
    } else {
      message.error("操作失败")
    }
    props.article.coverUrl =data.data
    options.file.url =data.data
  }
  return options.file
}
const RemoveImg = (options: { file: UploadFileInfo, fileList: Array<UploadFileInfo> })=>{
  api.removeCover(options.file.name).then(({data:data})=>{
    if (data.code == 10000) {
      message.success("添加成功！")
    } else if (data.code == 30001) {
      message.error("没有权限!")
    } else {
      message.error("操作失败")
    }
  })
  props.article.coverUrl=""
  options.fileList = new Array<UploadFileInfo>()
  return true;
}

onMounted(() => {
  GetTags()
  GetCategories()
})
</script>

<template>
  <n-modal :show="modalShow.showUpdate">
    <n-card
        style="width: 600px"
        title="发布文章"
        :bordered="false"
    >
      <n-space vertical size="large">
        <div class="item">
          <n-space>
            <span>文章分类</span>
            <n-popselect v-model:value="categoryName" v-model:options="categoryOption" scrollable>
              <n-button type="success" size="small" tertiary>{{ categoryName || '选择分类' }}</n-button>
              <template #action>
                <div class="add-container">
                  <n-input placeholder="输入新分类名" v-model:value="newCategoryName"/>
                  <n-button type="success" size="medium" tertiary @click="handleAddCategory">+</n-button>
                </div>
              </template>
            </n-popselect>
          </n-space>
        </div>
        <div class="item">
          <n-space>
            <span>文章标签</span>
            <n-popover
                :show-arrow="false"
                placement="bottom"
                trigger="click"
                :disabled="tags.size==3"
            >
              <template #trigger>
                <n-space size="small">
                  <n-tag v-for="tag in tags" size="medium" type="info" closable @close="handleCloseTag(tag)">{{
                      tag
                    }}
                  </n-tag>
                  <n-button type="info" size="small" tertiary>添加标签</n-button>
                </n-space>
              </template>
              <div class="tag-container">
                <div class="add-container">
                  <n-input placeholder="输入新标签名" v-model:value="newTagName"/>
                  <n-button type="info" size="medium" tertiary style="margin-left: 10px" @click="handleAddTag">+
                  </n-button>
                </div>
                <n-space size="small" style="padding-top: 10px">
                  <n-tag v-for="tag in tagOption" type="info" @click="tags.add(tag.value)">{{
                      tag.label
                    }}
                  </n-tag>
                </n-space>
              </div>
            </n-popover>
          </n-space>
        </div>
        <div class="item">
          <n-space>
            <span>上传封面</span>
            <div class="upload-container">
              <n-upload
                  directory-dnd
                  action="https://www.jiaoker.cn/api/file/cover"
                  :max="1"
                  list-type="image"
                  :multiple="false"
                  :headers="{
                'token':`${useTokenStore().avatarToken.token}`,
                }"
                  @finish="handleUploadFinish"
                  @remove="RemoveImg"
              >
                <n-upload-dragger style="width: 305px;height: 200px;">
                  <n-text style="font-size: 16px" v-show="article.coverUrl==''">
                    点击或者拖动文件到该区域来上传
                  </n-text>
                  <div class="img-container">
                    <img :src="article.coverUrl" v-show="article.coverUrl!=''"/>
                  </div>
                </n-upload-dragger>
              </n-upload>
            </div>
          </n-space>
        </div>
        <div class="item" style="padding-top: 50px">
          <n-space>
            <span>置顶</span>
            <n-switch v-model:value="article.isTop"/>
          </n-space>
        </div>
      </n-space>
      <template #footer>
        <div class="footer">
          <n-space>
            <n-button type="tertiary" @click="modalShow.showUpdate=false">取消</n-button>
            <n-button type="error" @click="UpdateArticle">发布</n-button>
          </n-space>
        </div>
      </template>
    </n-card>
  </n-modal>
</template>

<style scoped>
.item {
  font-size: 16px;
  display: flex;
  flex-direction: row;
  align-items: center;
}

.footer {
  width: 100%;
  display: flex;
  justify-content: right;
}

.add-container {
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
}

.tag-container {
  width: 400px;
  height: 300px;
}

.upload-container {
  width: 300px;
  height: 200px;
}
.img-container {
  width: 300px;
  height: 200px;
  overflow: hidden;
  position: relative;
  top: -16%;
  left: -9%;
}
.img-container img {
  border-radius: 2px;
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>