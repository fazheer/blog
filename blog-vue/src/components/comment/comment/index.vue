<template>
  <div class="content-container">
    <div class="avatar">
      <n-avatar :src="avatarUrl" round :size="60" />
    </div>
    <div class="main-container">
      <div class="user-info">
        <n-space align="center">
        <span class="nickName">{{comment.name}}</span>
        <span class="time">{{comment.createAt}}</span>
        </n-space>
      </div>
      <n-space class="device-info">
        <n-tag round size="tiny">{{comment.address}}</n-tag>
        <n-tag round size="tiny">{{comment.device}}</n-tag>
        <n-tag round size="tiny">{{comment.ops}}</n-tag>
        <div class="option">
          <n-button text style="padding-right: 5px" @click="handleLove" color="#ff69b4">
            <template #icon>
              <HeartOutline />
            </template>
            {{ loveCount }}
          </n-button>
          <n-button text @click="showInputBox">
            <template #icon>
              <ChatbubbleEllipsesOutline />
            </template>
          </n-button>
        </div>
      </n-space>
      <div class="content">
        <md-editor v-model="comment.content"  previewTheme="vuepress" showCodeRowNumber code-theme="github"
                   preview-only/>
      </div>
      <div class="reply-input-box" v-show="isShow">
        <ReplyInputBox :comment-id="comment.id" :to-id="null" @commit="handleCommitReply"/>
        <div class="cancel-btn">
          <n-button circle text @click="showInputBox" size="large">
            <template #icon>
              <CloseCircle />
            </template>
          </n-button>
        </div>
      </div>
      <div class="reply-container">
        <Reply v-for="reply in repliesRef" :reply="reply" :key="reply.id.toString()" @commit="handleCommitReply" />
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {ref} from "vue";
import MdEditor from 'md-editor-v3';
import {NTag, NAvatar, NButton, NSpace, useMessage} from "naive-ui";
import { ChatbubbleEllipsesOutline, CloseCircle, HeartOutline } from '@vicons/ionicons5'
import ReplyInputBox from "../input/replyInput";
import Reply  from '../reply'
import type {Comment,Reply as ReplyType} from "@/type";
import api from "@/api";
import {useLoveIdStore} from "@/stores/useLoveIdStore";
const loveIdStore = useLoveIdStore()
const avatarUrl = ref("https://s2.loli.net/2022/04/07/1HolLJn4tfzvqEU.jpg");
const isShow = ref<Boolean>(false);
const showInputBox = () => {
  isShow.value = !isShow.value;
}
const props = defineProps<{
  comment:Comment
  replies:Array<ReplyType>
}>()
const loveCount = ref<bigint>(props.comment.love)
const repliesRef = ref<Array<ReplyType>>(props.replies)
const handleCommitReply = ()=>{
  isShow.value=false
  api.getReplies(props.comment.id).then(({data:{data}})=>{
    repliesRef.value=data
  })
}
const message = useMessage()
const handleLove = ()=>{
  if (loveIdStore.addId(props.comment.id)) {
    message.success("点赞成功")
    api.updateCommentLove(props.comment.id)
    loveCount.value++
  } else {
    message.error("不能重复点赞")
  }
}
</script>
<style scoped>
.content-container {
  width: 100%;
  height: auto;
  display: flex;
  flex-direction: row;
  line-height: 1.75;
  position: relative;
  padding: 20px 15px;

}
.option{
    position: absolute;
    right: 2%;
}
.avatar {
  width: 10%;
  padding-right: 15px;
}

.nickName {
  font-size: 1.1rem;
  font-weight: bold;
}

.main-container {
  width: 90%;
  height: auto;
  display: flex;
  flex-direction: column;
  justify-content: left;
  padding-bottom: 15px;
  border-bottom: 1px dashed grey;
}

.content {
  width: 100%;
  max-height: 50vh;
  overflow: auto;
  padding: 10px;
  position: relative;
  font-family: 'Courier New', Courier, monospace;
  height: max-content;
}
.reply-input-box {
  position: relative;
}

.cancel-btn {
  position: absolute;
  top: -1%;
  right: -0.6%;
}

.reply-container {
  width: 100%;
  height: auto;
}</style>