<script setup lang="ts">
import type {UploadFileInfo} from 'naive-ui'
import {NButton, NCard, NDatePicker, NInput, NSpace, NUpload, useMessage} from 'naive-ui'
import {onMounted, ref} from "vue";
import api from "@/api";
import {useTokenStore} from "@/stores/useTokenStore";
import type {Setting} from "@/type";
import jsonBig from "json-bigint";

const message = useMessage()
const setting = ref<Setting>(
    {
      adminAvatar: "",
      avatarUrl: "",
      buildTime: "",
      email: "",
      gov: "",
      icp: "",
      jiaokerAvatar: "",
      name: "",
      notice: "",
      personalProfile: "",
      projectUrl: "",
      siteName: "",
      typingWord: ""
    }
)
const GetSetting = () => {
  api.getSetting().then(({data: {data}}) => {
    setting.value = data
    console.log(setting.value)
  })
}
const UpdateSetting = () => {
  api.updateSetting(setting.value).then(({data}) => {
    if (data.code == 10000) {
      message.success("添加成功！")
      GetSetting()
    } else if (data.code == 30001) {
      message.error("没有权限!")
    } else {
      message.error("操作失败")
    }
  })
}
const handleUploadSiteAvatarFinish = (options: {
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
    setting.value.avatarUrl=data.data
    options.file.url =data.data
  }
  return options.file
}
const handleUploadAdminAvatarFinish = (options: {
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
    setting.value.adminAvatar=data.data
    options.file.url =data.data
  }
  return options.file
}
const handleUploadJiaokerAvatarFinish = (options: {
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
    setting.value.jiaokerAvatar=data.data
    options.file.url =data.data
  }
  return options.file
}
onMounted(() => {
  GetSetting()
})
</script>

<template>
  <div class="setting-container">

    <n-card embedded>
      <n-space vertical>
        <div class="item">
          <div class="text-container">网站头像</div>
          <n-upload
              v-if="setting.avatarUrl!=''"
              action="https://www.jiaoker.cn/api/file/avatar"
              :default-file-list="[{
                id: 'fuck',
                name: 'siteAvatar',
                status: 'finished',
                url: `${setting.avatarUrl}`}
              ]"
              :headers="{
                'token':`${useTokenStore().avatarToken.token}`,
                }"
              @finish="handleUploadSiteAvatarFinish"
              list-type="image-card"
              :max="1"
          >
          </n-upload>
        </div>
        <div class="item">
          <div class="text-container">用户头像</div>
          <n-upload
              v-if="setting.avatarUrl!=''"
              action="https://www.jiaoker.cn/api/file/avatar"
              :default-file-list="[{
                id: 'fuck',
                name: 'siteAvatar',
                status: 'finished',
                url: `${setting.adminAvatar}`}
              ]"
              :headers="{
                'token':`${useTokenStore().avatarToken.token}`,
                }"
              @finish="handleUploadAdminAvatarFinish"
              list-type="image-card"
              :max="1"
          >
          </n-upload>
        </div>
        <div class="item">
          <div class="text-container">游客头像</div>
          <n-upload
              v-if="setting.avatarUrl!=''"
              action="https://www.jiaoker.cn/api/file/avatar"
              :default-file-list="[{
                id: 'fuck',
                name: 'siteAvatar',
                status: 'finished',
                url: `${setting.jiaokerAvatar}`}
              ]"
              :headers="{
                'token':`${useTokenStore().avatarToken.token}`,
                }"
              @finish="handleUploadJiaokerAvatarFinish"
              list-type="image-card"
              :max="1"
          >
          </n-upload>
        </div>
        <div class="item">
          <div class="text-container">项目地址</div>
          <n-input v-model:value="setting.projectUrl"/>
        </div>
        <div class="item">
          <div class="text-container">邮箱</div>
          <n-input v-model:value="setting.email"/>
        </div>
        <div class="item">
          <div class="text-container">网站名称</div>
          <n-input v-model:value="setting.siteName"/>
        </div>
        <div class="item">
          <div class="text-container">打字机内容</div>
          <n-input v-model:value="setting.typingWord"/>
        </div>
        <div class="item">
          <div class="text-container">网站作者</div>
          <n-input v-model:value="setting.name"/>
        </div>
        <div class="item">
          <div class="text-container">作者简介</div>
          <n-input v-model:value="setting.personalProfile"/>
        </div>
        <div class="item">
          <div class="text-container">创建日期</div>
          <n-date-picker
              v-if="setting.buildTime!=''"
              style="margin-left: -15px"
              v-model:formatted-value="setting.buildTime"
              value-format="yyyy-MM-dd HH:mm:ss"
              type="datetime"
              clearable
          />
        </div>
        <div class="item">
          <div class="text-container">网站公告</div>
          <n-input type="textarea" v-model:value="setting.notice"/>
        </div>
        <div class="item">
          <div class="text-container">ICP备案号</div>
          <n-input v-model:value="setting.icp"/>
        </div>
        <div class="item">
          <div class="text-container">公安备案号</div>
          <n-input v-model:value="setting.gov"/>
        </div>
        <n-button type="info" @click="UpdateSetting">
          修改
        </n-button>
      </n-space>
    </n-card>
  </div>
</template>

<style scoped>
.setting-container {
  width: 100%;
  height: 100%;
  padding: 5px 10px 10px 10px;
}

.item {
  display: flex;
  width: 100%;
  align-items: center;
  flex-wrap: nowrap;
  padding-bottom: 30px;
}

.text-container {
  display: block;
  width: 12%;
  font-size: 14px;
}

.n-input {
  width: 35%;
  margin-left: -15px;
}
</style>