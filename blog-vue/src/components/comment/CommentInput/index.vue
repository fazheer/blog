<template>
  <div class="input-container">
    <div v-if="mode === 'reply' && replyToName" class="reply-hint">
      <n-tag size="small" round>@{{ replyToName }}</n-tag>
    </div>
    <div class="user-infos">
      <div class="info-box">
        <span class="info-text">昵称</span>
        <input v-model="nickName" type="text" />
      </div>
      <div class="info-box">
        <span class="info-text">邮箱</span>
        <input v-model="email" type="text" />
      </div>
      <div class="info-box">
        <span class="info-text">网址</span>
        <input v-model="siteUrl" type="text" />
      </div>
    </div>
    <md-editor
      v-model="commentInput"
      previewTheme="vuepress"
      showCodeRowNumber
      code-theme="github"
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
import { ref } from "vue";
import MdEditor from 'md-editor-v3';
import { NButton, NTag, useMessage } from "naive-ui";
import type { CommentVO, ReplyVO } from "@/type";
import * as api from "@/api";

const nickName = ref<string | null>();
const email = ref('');
const siteUrl = ref('');
const commentInput = ref("");
const message = useMessage();
const emit = defineEmits(['commit']);

const props = defineProps<{
  mode: 'comment' | 'reply'
  pageId?: bigint
  commentId?: bigint
  toId?: bigint | null
  replyToName?: string
}>()

const handleCommit = () => {
  if (commentInput.value == "") {
    message.error("评论内容不能为空!")
    return
  }

  if (props.mode === 'comment') {
    const commentVO: CommentVO = {
      pageId: props.pageId || 0n,
      name: nickName.value,
      email: email.value,
      url: siteUrl.value,
      content: commentInput.value,
    }
    api.saveComment(commentVO).then((resp) => {
      if (resp.status == 200) {
        message.success("发布成功")
        emit('commit')
      } else {
        message.error("发布失败")
      }
    })
  } else {
    const replyVO: ReplyVO = {
      name: nickName.value,
      email: email.value,
      url: siteUrl.value,
      content: commentInput.value,
      commentId: props.commentId || 0n,
      toId: props.toId ?? null,
    }
    api.saveReply(replyVO).then((resp) => {
      if (resp.status == 200) {
        message.success("发布成功")
        emit('commit')
      } else {
        message.error("发布失败")
      }
    })
  }
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

.reply-hint {
  padding: 8px 8px 0 8px;
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
