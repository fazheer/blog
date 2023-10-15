
<script lang="ts" setup>
import {onMounted, ref} from "vue";
import MdEditor from 'md-editor-v3';
import {NTag, NAvatar, NButton, NSpace, useMessage} from "naive-ui";
import {ChatbubbleEllipsesOutline, CloseCircle, HeartOutline} from '@vicons/ionicons5'
import ReplyInput from '../input/replyInput'
import type {Reply} from "@/type";
import api from "@/api";
import {useLoveIdStore} from "@/stores/useLoveIdStore";

const avatarUrl = ref("https://s2.loli.net/2022/04/07/1HolLJn4tfzvqEU.jpg");
const replyNameUrl = ref({
  name: String,
  url: String
})
const loveIdStore = useLoveIdStore();
const props = defineProps<{
  reply: Reply
}>()
const loveCount = ref<bigint>(props.reply.love);
const isShow = ref<Boolean>(false);
const message = useMessage()
const emit = defineEmits(['commit'])
onMounted(() => {
  if (props.reply.toId != null) {
    api.getReplyUrl(props.reply.toId).then(({data: {data}}) => {
      replyNameUrl.value = data
    })
  }
})
const handleLove = () => {
  if (loveIdStore.addId(props.reply.id)) {
    message.success("点赞成功")
    api.updateReplyLove(props.reply.id)
    loveCount.value++
  } else {
    message.error("不能重复点赞")
  }
}
const showInputBox = () => {
  isShow.value = !isShow.value;
}
</script>
<template>
  <div class="reply-container">
    <div class="avatar">
      <n-avatar :src="avatarUrl" round :size="60"/>
    </div>

    <div class="main-container">
      <div class="user-info">
        <n-space align="center">
        <span class="nickName">{{ reply.name }}</span>
        <span class="time" style="font-size: 14px">{{reply.createAt}}</span>
        </n-space>
      </div>

      <n-space class="info">
        <n-tag round size="tiny">{{ reply.address }}</n-tag>
        <n-tag round size="tiny">{{ reply.device }}</n-tag>
        <n-tag round size="tiny">{{ reply.ops }}</n-tag>
        <div class="option">
          <n-button text style="padding-right: 5px" @click="handleLove" color="#ff69b4">
            <template #icon>
              <HeartOutline/>
            </template>
            {{ loveCount }}
          </n-button>
          <n-button text @click="showInputBox">
            <template #icon>
              <ChatbubbleEllipsesOutline/>
            </template>
          </n-button>
        </div>
      </n-space>
      <div class="content">
                <span v-if="reply.toId!=null">回复
                  <n-button text>{{ replyNameUrl.name }}</n-button>:</span>
        <md-editor
            v-model="reply.content"
            previewTheme="vuepress"
            showCodeRowNumber code-theme="github"
            preview-only
        />
      </div>
      <div class="reply-input-box" v-show="isShow">
        <ReplyInput :comment-id="reply.commentId" :to-id="reply.id" @commit="emit('commit');isShow=false"/>
        <div class="cancel-btn">
          <n-button circle text @click="showInputBox">
            <template #icon>
              <CloseCircle/>
            </template>
          </n-button>
        </div>
      </div>
    </div>
  </div>
</template>



<style scoped>
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

.reply-container {
  width: 100%;
  height: auto;
  display: flex;
  flex-direction: row;
  line-height: 1.75;
  padding: 10px 0 0 0;
}

.avatar {
  padding-right: 15px;
}

.nickName {
  font-size: 1.1rem;
  font-weight: bold;
}

.option {
  position: absolute;
  right: 0;
}

.main-container {
  width: 100%;
  min-height: 100px;
  display: flex;
  flex-direction: column;
  justify-content: left;
  padding-bottom: 15px;
}

.content {
  padding-top: 10px;
  font-family: 'Courier New', Courier, monospace;
  line-height: 1;
}

.reply-input-box {
  width: 92%;
  position: relative;
}

.cancel-btn {
  position: absolute;
  top: -1%;
  right: -0.6%;
}</style>