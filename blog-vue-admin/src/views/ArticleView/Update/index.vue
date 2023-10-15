<script setup lang="ts">
import {MdEditor} from "md-editor-v3";
import {onMounted, ref} from "vue";
import {NButton, NInput, NSpace, useMessage} from 'naive-ui'
import 'md-editor-v3/lib/style.css';
import {useRoute} from "vue-router";
import api from "@/api";
import type {ArticleVO} from "@/type";
import UpdateModal from "@/components/UpdateModal/index";
import {useModelShowStore} from "@/stores/useModalShowStore";

const message = useMessage()
const modalShow = useModelShowStore()
const route = useRoute()
const articleId = BigInt(route.params.id);
const article = ref<ArticleVO>({
  categoryName: "",
  content: "",
  coverUrl: "",
  createAt: "",
  id: 0n,
  isTop: false,
  tags: null,
  title: "",
  status: 0
})
const showModalF = () => {
  if (article.value.content=='') message.warning("文章内容不能为空")
  else {
    modalShow.showUpdate = true
  }
}
const GetArticle = ()=>{
  api.getArticleById(articleId).then(({data:{data}})=>{
    article.value=data
  })
}
onMounted(()=>{
  GetArticle()
})
const onUploadImg = async (files:Array<File>, callback:(urls: Array<string>) => void) => {
  console.log(files)
  const res = await Promise.all(
      files.map((file) => {
        return new Promise((rev, rej) => {
          const form = new FormData();
          form.append('file', file);
          api.axiosService
              .post('/file/img', form, {
                headers: {
                  'Content-Type': 'multipart/form-data;boundary=----WebKitFormBoundarygjbbBuWhf3FFrRJ9'
                }
              })
              .then((res) => rev(res))
              .catch((error) => rej(error));
        });
      })
  );

  callback(res.map((item) => item.data.data));
};
</script>

<template>
  <div class="top-container">

  <div class="input-container">
    <n-input   v-model:value="article.title"/>
  </div>
    <UpdateModal :article="article" />
  <div class="button-container">
    <n-space style="padding-right: 10px">
      <n-button type="success" @click="showModalF()">发布文章</n-button>
    </n-space>
  </div>
  </div>
  <div class="editor-container">
    <md-editor v-model="article.content" style="height: 90%" :no-upload-img="true"/>
  </div>


</template>

<style scoped>
.top-container{
  display: flex;
  width: 100%;
  flex-wrap: nowrap;
  padding: 0 10px 10px 10px;
}
.input-container{
  width: 100%;
  padding-right: 10px;
}
.button-container{
  display: flex;
  width: 16%;
  padding: 0 10px 0 0;
  justify-content: right;
}
.editor-container{
  height: 90vh;
  padding: 0 10px 10px 10px;
}
</style>