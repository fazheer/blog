<template>
    <Page :page-title="tagName">
      <TimeLineArticle :title="tagName" :id="id" :name="tagName" type="标签"/>
    </Page>
</template>

<script  lang="ts" setup>
import TimeLineArticle from '@/components/TimeLineArticle'
import Page from '@/components/page'
import {useRoute} from "vue-router";
import {ref} from "vue";
const route = useRoute()
let tagName :String
let id:bigint
const page =ref(1)
tagName= route.params.tagName.toString() ;
id =BigInt(route.params.tagId)
</script>
<style scoped >
</style>