<script setup lang="ts">
import CIcon from '@/components/icons/CIcon.vue'
import {NIcon,NSpace} from 'naive-ui'
import {onMounted, ref} from "vue";
import api from "@/api";
const icpGov = ref<{
  icp:String,
  gov:String
}>({gov: "", icp: ""})
const GetIcpGov = ()=>{
  api.getIcpAndGov().then(({data:{data}})=>{
    icpGov.value=data
  })
}
onMounted(()=>{
  GetIcpGov()
})
</script>

<template>
  <div class="footer-container">

    <div class="item">
      <n-icon >
        <CIcon/>
      </n-icon>
      <n-space size="large">
        <span>2020-2023</span>
        <span>By jiaoker</span>
      </n-space>

    </div>
    <div class="item">
     Theme imitate By Butterfly
    </div>
    <div class="item">
    {{icpGov.icp}}
    </div>
    <div class="item">
      <img src="https://obs.jiaoker.cn/avatar/202204292211311.png" alt=""/>
     {{icpGov.gov}}
    </div>
  </div>

</template>

<style scoped>
.footer-container{
  height: 100%;
  display: flex;
  color: #ffffff;
  flex-direction: column;
  flex-wrap: wrap;
  position: relative;
}
.item{
  padding: 5px;
  display: inline-flex;
  align-items: center;
}
span{
  color: #ffffff;
}
</style>