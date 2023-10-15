<script setup lang="ts">
import type {MenuInst} from "naive-ui";
import {NLayoutSider, NMenu} from 'naive-ui'
import {ref, watch} from "vue"
import Logo from '@/components/icons/Logo'
import {useRoute} from "vue-router";
import {useMenuOption} from "@/stores/MenuOptionProvider";

const menuOptions = useMenuOption().menuOptions
defineProps<{
  collapsed: Boolean
}>()
const menuInstRef = ref<MenuInst | null>(null)
const route =useRoute()
const selectedKeyRef = ref(`${route.path}`)
watch(()=>route.path,()=>{
  selectedKeyRef.value=route.path
  menuInstRef.value?.showOption(`${route.path}`)
})
</script>
<template>
  <n-layout-sider
      class="side-container"
      bordered
      collapse-mode="width"
      :collapsed-width="64"
      :width="200"
      :inverted="true"
      :collapsed="collapsed"
      show-trigger
      @collapse="collapsed = true"
      @expand="collapsed = false">
    <div class="logo-container">
      <Logo/>
      <span v-show="!collapsed">JiaokerAdmin</span>
    </div>
    <n-menu
        ref="menuInstRef"
        v-model:value="selectedKeyRef"
        :collapsed="collapsed"
        :collapsed-width="64"
        :collapsed-icon-size="24"
        :options="menuOptions"
        key="key"
        :inverted="true"
        label-field="label"
        :default-expand-all="true"
        children-field="children"
    />
  </n-layout-sider>
</template>

<style scoped>
.side-container {
  height: 100vh;
}

.logo-container {
  display: flex;
  font-size: 1.4rem;
  flex-wrap: nowrap;
  padding: 10px 5px;
  justify-content: center;
  align-content: center;
}
</style>