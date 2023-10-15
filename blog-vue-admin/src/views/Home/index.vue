<template>
  <n-loading-bar-provider>
    <n-layout has-sider>
      <MenuComponent :collapsed="false"/>
      <n-layout style="height: 100vh">
        <NavBar/>
        <n-layout-content >
          <MainRouterView/>
        </n-layout-content>
      </n-layout>
    </n-layout>
  </n-loading-bar-provider>
</template>
<script setup lang="ts">
import {RouterView} from 'vue-router'
import {NLayout, NLayoutContent, NLoadingBarProvider, useLoadingBar} from "naive-ui";
import {useProviderStore} from "@/stores/provider";
import {defineComponent, h} from "vue";
import MenuComponent from '@/components/menu'
import NavBar from '@/components/navNar'

const provider = useProviderStore()
const MainRouterView = defineComponent({
  render: () => h(RouterView),
  setup: () => {
    provider.setLoadingBar(useLoadingBar())
  }
})

</script>

