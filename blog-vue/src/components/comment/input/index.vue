<template>
  <div class="input-container">
    <div class="user-infos">
      <div class="info-box">
        <span class="info-text">昵称</span>
        <input v-model="nickName" type="text"/>
      </div>
      <div class="info-box">
        <span class="info-text">邮箱</span>
        <input v-model="email" type="text" />
      </div>
      <div class="info-box">
        <span class="info-text">网址</span>
        <input v-model="siteUrl" type="text"/>
      </div>
    </div>
    <md-editor v-model="commentInput"
               previewTheme="vuepress"
               showCodeRowNumber code-theme="github"
               no-upload-img
    />
    <div class="option">
      <n-button color="#99dd71" @click="handleCommit()">
        提交
      </n-button>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {ref} from "vue";
import MdEditor from 'md-editor-v3';
import {NButton, useMessage} from "naive-ui";
import type {CommentVO} from "@/type";
import api from "@/api";

const commentVO = ref<CommentVO>({
  pageId: 0n,
  name: null,
  email: '',
  url: '',
  content: '',
})
const nickName = ref<String|null>();
const email = ref('');
const siteUrl = ref('');
const commentInput = ref("");
const message = useMessage()
const props = defineProps<{
  pageId: bigint
}>()
const status = ref(200)
const emit = defineEmits(['commit'])
const handleCommit = () => {
  if(commentInput.value=="") {
    message.error("评论内容不能为空!")
    return
  }
  commentVO.value.pageId = props.pageId
  commentVO.value.name = nickName.value
  commentVO.value.email = email.value
  commentVO.value.url = siteUrl.value
  commentVO.value.content = commentInput.value

  api.saveComment(commentVO.value).then((resp) => {
    if(resp.status==200) {
      message.success("发布成功")
      emit('commit')
    }
    else{
      status.value=400
      message.error("发布失败")
    }
  })
}
</script>

<style scoped>
.container {
  width: 100%;
}

.input-container {
  width: 100%;
  border-radius: 8px;
  border: 1px solid slategrey;
}

.option {
  width: 100%;
  padding: 5px 5px 5px 0;
  display: flex;
  justify-content: right;
}

.info-box {
  display: flex;
  flex: 1;
}

.user-infos input:focus {
  background-color: rgb(248, 248, 248);
}

.info-text {
  white-space: nowrap;
  display: inline-flex;
  padding: 5px 5px;
  align-items: center;
}

@media (min-width: 581px) {
  .info-box input {
    width: 80%;
    height: 100%;
    outline-style: none;
    border: none;
  }

  .user-infos {
    width: 100%;
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    border-bottom: 1px dashed grey;
  }


}

@media (max-width: 580px) {
  .user-infos {
    width: 100%;
    height: max-content;
    display: flex;
    flex-direction: column;
  }

  .user-infos input {
    width: 88%;
    outline-style: none;
    border: none;
  }

  .info-box {
    border-bottom: 1px dashed grey;
  }
}
</style>