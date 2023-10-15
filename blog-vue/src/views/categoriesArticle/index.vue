<template>
    <Page :page-title="categoryName">
      <TimeLineArticle :title="categoryName" :id="id" :name="categoryName" type="分类"/>
    </Page>
</template>

<script  lang="ts" setup>
import TimeLineArticle from '@/components/TimeLineArticle'
import Page from '@/components/page'
import {useRoute} from "vue-router";
import {ref} from "vue";
const route = useRoute()
let categoryName :String
let id:bigint
const page =ref(1)
categoryName= route.params.categoryName.toString() ;
id =BigInt(route.params.id)
</script>
<style scoped >
</style>