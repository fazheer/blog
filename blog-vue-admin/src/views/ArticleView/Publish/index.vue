<script setup lang="ts">
import {MdEditor} from "md-editor-v3";
import {ref} from "vue";
import {NButton, NInput, NSpace, useMessage} from 'naive-ui'
import 'md-editor-v3/lib/style.css';
import PublishModal from "@/components/PublishModal";
import {useModelShowStore} from "@/stores/useModalShowStore";
import api from "@/api";
import moment from "moment";
import axios from "axios";

const message = useMessage()
const modalShow = useModelShowStore()
const articleContent = ref("")
const showModalF = () => {
  if (articleContent.value == '') message.warning("文章内容不能为空")
  else if (title.value == '') message.warning("标题不能为空")
  else {
    modalShow.showPublish = true
  }
}
const title = ref("")
const SaveArticleAsDraft = () => {
  if (articleContent.value == '') {
    message.warning("文章内容不能为空")
    return
  } else if (title.value == '') {
    message.warning("标题不能为空")
    return;
  }
  api.publishArticle({
    categoryName: null,
    content: articleContent.value,
    coverUrl: null,
    createAt: moment().format('yyyy-MM-DD HH:mm:ss'),
    id: null,
    isTop: false,
    status: 1,
    tags: null,
    title: title.value
  }).then(({data:data}) => {
    if (data.code == 10000) {
      message.success("添加成功！")
    } else if (data.code == 30001) {
      message.error("没有权限!")
    } else {
      message.error("操作失败")
    }
  })
}

</script>

<template>
  <div class="top-container">
    <div class="input-container">
      <n-input placeholder="文章标题" v-model:value="title"/>
    </div>
    <div class="option-container">
      <n-space size="small">
        <n-button type="success" @click="showModalF()">发布文章</n-button>
        <PublishModal :content="articleContent" :title="title"/>
        <n-button type="error" @click="SaveArticleAsDraft">存为草稿</n-button>
      </n-space>
    </div>
  </div>
  <div class="editor-container">
    <md-editor v-model="articleContent" style="height: 90%" :no-upload-img="true"/>
  </div>

</template>

<style scoped>
.top-container {
  display: flex;
  width: 100%;
  padding: 0 10px 10px 10px;
}

.input-container {
  width: 84%;
  padding-right: 10px;
}

.option-container {
  width: 16%;
}

.editor-container {
  height: 90vh;
  padding-left: 10px;
}

.article-info {
  display: flex;
  flex-direction: column;
}

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
</style>