<template>
  <n-loading-bar-provider>
    <n-message-provider>
    <RouterViewComponent/>
    </n-message-provider>
  </n-loading-bar-provider>
</template>
<script setup lang="ts">
import {RouterView} from 'vue-router'
import {NLoadingBarProvider, NMessageProvider, useLoadingBar} from "naive-ui";
import {useProviderStore} from "@/stores/provider";
import {defineComponent, h} from "vue";

const provider = useProviderStore()
const RouterViewComponent = defineComponent({
  render: () => h(RouterView),
  setup: () => {
    provider.setLoadingBar(useLoadingBar())
  }
})

</script>


