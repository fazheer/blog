<script setup lang="ts">
import {ref, watch} from "vue";
import {NAvatar, NButton, NCheckbox, NPopconfirm, useMessage} from 'naive-ui'
import 'md-editor-v3/lib/style.css';
import type {Comment} from "@/type";
import {useDeletedIdsStore} from "@/stores/DeletedIdsStore";
import api from "@/api";

const props = defineProps<{
  comment:Comment
  allChecked: boolean
}>()
const message = useMessage()
const deletedIdsStore = useDeletedIdsStore()
const emit = defineEmits(['delete'])
const checked = ref(false)
const DelComment = ()=>{
  api.delComment(props.comment.id).then(({data})=>{
    if(data.code==10000) {
      message.success("删除成功！")
      emit("delete")
    }else {
      message.error("操作失败！")
    }
  })
}
watch(()=>props.allChecked,()=>checked.value=props.allChecked)
watch(()=>checked.value,()=>{
  if(checked.value) {
    if(deletedIdsStore.ids.has(props.comment.id)) {
      return
    }else {
      deletedIdsStore.ids.add(props.comment.id)
    }
  }else {
    deletedIdsStore.ids.delete(props.comment.id)
  }
})

</script>

<template>
    <tr>
      <td>
        <n-checkbox v-model:checked="checked" @on-update:checked="checked=!checked"/>
      </td>
      <td style="width: 50px;height: 10px;">
        <NAvatar src="https://s2.loli.net/2022/04/07/1HolLJn4tfzvqEU.jpg" round size="large"/>
      </td>
      <td>{{ comment.name}}</td>
      <td>{{ comment.email }}</td>
      <td><a :href="`http://${comment.url}`" target="_blank">{{ comment.url }}</a> </td>
      <td>{{ comment.content }}</td>
      <td>{{ comment.love }}</td>
      <td>{{ comment.createAt}}</td>
      <td>
          <n-popconfirm
              negative-text="取消"
              positive-text="是的"
              @positive-click="DelComment"
          >
            <template #trigger>
          <n-button type="error" >删除</n-button>
            </template>
            确认删除？
          </n-popconfirm>
      </td>
    </tr>
</template>

<style scoped>

a {
  color: #474a59;
  text-decoration: none;
}

a:hover {
  color: #18a058;
  text-decoration: none;
}

a:link {
  color: #474a59;
  text-decoration: none;
}

a:active {
  color: #474a59;
  text-decoration: none;
}

a:visited {
  color: #474a59;
  text-decoration: none;
}
</style>