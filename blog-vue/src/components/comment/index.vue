<template>
   <div class="title-container">
      <CommentIcon style="padding-right: 10px;"/>
      <h2>{{commentNum}}条评论</h2>
    </div>
    <InputBox :page-id="pageId" @commit="handleCommit()"/>
  <div class="comment-container">
    <CommentContent v-for="commentReply in commentReplies" :key="commentReply.comment.id.toString()" :comment="commentReply.comment" :replies="commentReply.replies"/>
  </div>
</template>

<script lang="ts" setup>
import CommentIcon from "@/components/icons/CommentIcon.vue";
import {onMounted, ref} from "vue";
import CommentContent from "@/components/comment/comment";
import InputBox from "./input";
import type {CommentReply} from "@/type";
import api from "@/api";
const props = defineProps<{
  pageId:bigint
}>()
const commentReplies = ref<Array<CommentReply>>(new Array<CommentReply>())
const commentNum = ref(0)
const GetComment = (pageId:bigint)=>{
  api.getComment(pageId).then(({data:{data}})=>{
    commentReplies.value = data
    commentNum.value = commentReplies.value?.length!
  })
}
onMounted(()=>{
  GetComment(props.pageId!)
})
function handleCommit(){
  GetComment(props.pageId)
}
</script>
<style scoped>
.comment-container {
  width: 100%;
  height: auto;
}
.title-container{
  display: inline-flex;
  align-items: center;
  padding-bottom: 30px;

}
</style>