<script setup lang="ts">
import { NSpace } from "naive-ui";
import PandectBox from '@/components/aside-card/Pandect'
import CategoriesBox from '@/components/aside-card/Categories'
import NoticeBox from '@/components/aside-card/Notice'
import NewestBox from '@/components/aside-card/Newest'
import DateBox from '@/components/aside-card/Date'
import TagBox from '@/components/aside-card/Tag'
import InfoBox from '@/components/aside-card/Info'
import {onMounted, ref} from "vue";
import type {SiteInfo} from "@/type";
import api from "@/api";
const siteInfo = ref<SiteInfo>({
  articleCount: 0n,
  avatarUrl: "",
  buildTime: undefined,
  categoryCount: 0n,
  email: "",
  lastTime: undefined,
  name: "",
  notice: "",
  personalProfile: "",
  projectUrl: "",
  siteView: 0n,
  tagCount: 0n

})
const GetSiteInfo = ()=>{
  api.getSieInfo().then(({data:{data}})=>{
    siteInfo.value=data
  })
}
onMounted(()=>{
  GetSiteInfo()
})
</script>
<template>
    <div class="aside-container" id="aside-container">
        <n-space vertical :size="20">
            <PandectBox
                :avatar-url="siteInfo.avatarUrl"
                :name="siteInfo.name"
                :person-profile="siteInfo.personalProfile"
                :article-count="siteInfo.articleCount"
                :tag-count="siteInfo.tagCount"
                :category-count="siteInfo.categoryCount"
                :project-url="siteInfo.projectUrl"
                :email="siteInfo.email"
            />
            <NoticeBox :noticle="siteInfo.notice"/>
            <NewestBox />
            <CategoriesBox />
            <DateBox />
            <TagBox />
            <InfoBox
                :article-count="siteInfo.articleCount"
                :build-time="new Date(Date.parse(siteInfo.buildTime!))"
                :last-time="new Date(Date.parse(siteInfo.lastTime!))"
                :site-view="siteInfo.siteView"
            />
        </n-space>
    </div>
</template>
<style scoped>
.aside-container {
    width: 26%;
    padding-left: 15px;
}
@media (max-width: 900px) {
    .aside-container{
        width: 100%;
    }
}
</style>