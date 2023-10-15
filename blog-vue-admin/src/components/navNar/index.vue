<script setup lang="ts">
import {NAvatar, NBreadcrumb, NBreadcrumbItem, NIcon, NLayoutHeader, NSpace, useMessage} from 'naive-ui'
import {ExitOutline as ExitIcon, ReloadOutline as ReloadIcon} from '@vicons/ionicons5'
import {useRoute} from 'vue-router'
import {useMenuOption} from "@/stores/MenuOptionProvider";
import {useBreadcrumbStore} from "@/stores/BreadcrumbStore";
import VRouter from '@/router'
import RouterTag from "@/components/RouterTag";
import {ref, watch} from "vue";
import CancelFullScreenIcon from "@/components/icons/CancelFullScreenIcon";
import FullScreenIcon from "@/components/icons/FullScreenIcon";
import type {BreadcrumbItem} from "@/type";
import api from "@/api";
import {useTokenStore} from "@/stores/useTokenStore";

const breadcrumbItems = useBreadcrumbStore()
const isFullScreen = ref(false)
const items = ref<Array<BreadcrumbItem>>([{
  name: "主控台",
}])
const router = VRouter
const route = useRoute()
const message =useMessage()
const reloadPage = () => {
  router.go(0)
}
const holePage = () => {
  isFullScreen.value = !isFullScreen.value
  if (isFullScreen.value) {
    document.documentElement.requestFullscreen()
  } else {
    document.exitFullscreen()
  }
}
const MenuOptions = useMenuOption().menuOptions
const setBreadcrumb = (name: String, menuKey: String) => {
  for (let i = 0; i < MenuOptions.length; i++) {
    const hasChildren = ("children" in MenuOptions[i])
    if (!hasChildren) {
      if (menuKey == MenuOptions[i].key) {
        breadcrumbItems.breadcrumbItem = [name]
        return;
      }
    } else {
      MenuOptions[i].children?.forEach(child => {
        if (child.key == menuKey) {
          breadcrumbItems.breadcrumbItem = [MenuOptions[i].label as String,name]
        }
      })
    }
  }
}
const Logout = ()=>{
  api.logout().then(({data})=>{
    if(data.code==10000) {
      message.success("登出成功")
      useTokenStore().avatarToken.token=" "
      VRouter.push('/login')
    }
  })
}
watch(() => route.path, () => {
  setBreadcrumb(route.name!.toString(), route.path)
})
</script>

<template>
  <n-layout-header class="nav-container">
    <div class="left-container">
      <n-icon :size="20" @click="reloadPage()" style="cursor: pointer">
        <ReloadIcon/>
      </n-icon>
      <n-breadcrumb>
        <n-breadcrumb-item v-for="item in breadcrumbItems.breadcrumbItem" :key="item">
          {{ item }}
        </n-breadcrumb-item>
      </n-breadcrumb>
    </div>
    <div class="right-container">
      <n-space align="center" size="large">
      <n-icon :size="32" @click="holePage()" style="cursor: pointer">
        <FullScreenIcon v-show="!isFullScreen"/>
        <CancelFullScreenIcon v-show="isFullScreen"/>
      </n-icon>
        <n-avatar round size="large" :src="useTokenStore().avatarToken.avatarUrl" v-show="useTokenStore().avatarToken.avatarUrl!=''"/>
      <n-icon :size="32" @click="Logout" style="cursor: pointer">
        <ExitIcon/>
      </n-icon>
      </n-space>
    </div>
  </n-layout-header>
  <RouterTag/>
</template>

<style scoped>
.nav-container {
  background-color: rgb(255, 255, 255);
  display: flex;
  width: 100%;
  padding-left: 10px;
  height: 60px;
  -webkit-box-shadow: 0 1px 4px #00152914;;
  -moz-box-shadow: 0 1px 4px #00152914;;
  box-shadow: 0 1px 4px #00152914;;
}

.left-container {
  width: 60%;
  height: 100%;
  display: flex;
  flex-wrap: nowrap;
  justify-content: flex-start;
  align-items: center;
}

.right-container {
  width: 40%;
  display: flex;
  align-items: center;
  padding-right: 10px;
  justify-content: flex-end;
}
</style>