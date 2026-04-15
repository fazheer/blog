<template>
  <div :class="isReply ? 'reply-item-container' : 'content-container'">
    <div class="avatar">
      <n-avatar :src="avatarUrl" round :size="60" />
    </div>
    <div class="main-container">
      <div class="user-info">
        <n-space align="center">
          <span class="nickName">{{ data.name }}</span>
          <span class="time" :style="isReply ? 'font-size: 14px' : ''">{{ data.createAt }}</span>
        </n-space>
      </div>
      <n-space class="device-info">
        <n-tag round size="tiny">{{ data.address }}</n-tag>
        <n-tag round size="tiny">{{ data.device }}</n-tag>
        <n-tag round size="tiny">{{ data.ops }}</n-tag>
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
        <span v-if="isReply && (data as Reply).toId != null">
          回复 <n-button text>{{ displayReplyTo }}</n-button>:
        </span>
        <md-editor
          v-model="contentRef"
          previewTheme="vuepress"
          showCodeRowNumber
          code-theme="github"
          preview-only
        />
      </div>
      <div class="reply-input-box" v-show="isShow">
        <CommentInput
          mode="reply"
          :comment-id="isReply ? (data as Reply).commentId : data.id"
          :to-id="isReply ? data.id : null"
          :reply-to-name="replyToName"
          @commit="handleCommitReply"
        />
        <div class="cancel-btn">
          <n-button circle text @click="showInputBox" size="large">
            <template #icon>
              <CloseCircle />
            </template>
          </n-button>
        </div>
      </div>
      <div v-if="!isReply" class="nested-reply-container">
        <CommentItem
          v-for="reply in repliesRef"
          :key="reply.id.toString()"
          :is-reply="true"
          :data="reply"
          @commit="handleCommitReply"
        />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  name: 'CommentItem'
}
</script>

<script lang="ts" setup>
import { ref, onMounted, computed } from "vue";
import MdEditor from 'md-editor-v3';
import { NTag, NAvatar, NButton, NSpace, useMessage } from "naive-ui";
import { ChatbubbleEllipsesOutline, CloseCircle, HeartOutline } from '@vicons/ionicons5'
import CommentInput from '../CommentInput'
import type { Comment, Reply } from "@/type";
import * as api from "@/api";
import { useLoveIdStore } from "@/stores/useLoveIdStore";
import { DEFAULT_AVATAR } from "@/config/avatar";

const avatarUrl = ref(DEFAULT_AVATAR);
const loveIdStore = useLoveIdStore();
const message = useMessage();

const props = defineProps<{
  data: Comment | Reply
  isReply: boolean
  replies?: Array<Reply>
  replyTo?: string
}>()

const contentRef = ref(props.data.content)
const loveCount = ref<bigint>(props.data.love)
const isShow = ref<Boolean>(false)
const repliesRef = ref<Array<Reply>>(props.replies || [])
const replyNameUrl = ref<{ name: string; url: string } | null>(null)

const replyToName = computed(() => {
  return props.replyTo || replyNameUrl.value?.name || ''
})

const displayReplyTo = computed(() => {
  return props.replyTo || replyNameUrl.value?.name || ''
})

onMounted(() => {
  if (props.isReply && (props.data as Reply).toId != null && !props.replyTo) {
    api.getReplyUrl((props.data as Reply).toId!).then(({ data: { data } }) => {
      replyNameUrl.value = data
    })
  }
})

const showInputBox = () => {
  isShow.value = !isShow.value;
}

const emit = defineEmits(['commit'])

const handleCommitReply = () => {
  isShow.value = false
  if (!props.isReply) {
    api.getReplies(props.data.id).then(({ data: { data } }) => {
      repliesRef.value = data
    })
  } else {
    emit('commit')
  }
}

const handleLove = () => {
  if (loveIdStore.addId(props.data.id)) {
    message.success("点赞成功")
    if (props.isReply) {
      api.updateReplyLove(props.data.id)
    } else {
      api.updateCommentLove(props.data.id)
    }
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

.reply-item-container {
  width: 100%;
  height: auto;
  display: flex;
  flex-direction: row;
  line-height: 1.75;
  padding: 10px 0 0 0;
}

.option {
  position: absolute;
  right: 2%;
}

.reply-item-container .option {
  right: 0;
}

.avatar {
  width: 10%;
  padding-right: 15px;
}

.reply-item-container .avatar {
  width: auto;
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

.reply-item-container .main-container {
  width: 100%;
  min-height: 100px;
  border-bottom: none;
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

.reply-item-container .content {
  max-height: none;
  padding-top: 10px;
  line-height: 1;
}

.reply-input-box {
  position: relative;
}

.cancel-btn {
  position: absolute;
  top: -1%;
  right: -0.6%;
}

.nested-reply-container {
  width: 100%;
  height: auto;
}

a {
  color: #3fa7db;
  text-decoration: none;
}

a:link {
  color: #3fa7db;
  text-decoration: none;
}

a:visited {
  color: #3fa7db;
  text-decoration: none;
}

a:hover {
  text-decoration: none;
  color: #3fa7db;
}

a:active {
  color: #3fa7db;
  text-decoration: none;
}
</style>
