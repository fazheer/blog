<script setup lang="ts">
import { TrainOutline } from '@vicons/ionicons5'
import { NCard,NIcon} from "naive-ui";
import api from '@/api'
import {onMounted, ref} from "vue";
import type {ArchiveCard} from "@/type";
import ArchiveCardItem from "@/components/ArchiveCardItem";
const archiveCard = ref<Array<ArchiveCard>>(new Array<ArchiveCard>())
const GetArchiveCard =()=>{
  api.getArchiveCard().then(({data:{data}})=>{
    archiveCard.value=data
  })
}
onMounted(()=>{
  GetArchiveCard()
})
</script>
<template>
    <n-card hoverable embedded>
        <template #header>
            <div class="card-title">
                <n-icon size="18">
                    <TrainOutline />
                </n-icon>
                <span>归档</span>
            </div>
        </template>
        <ArchiveCardItem v-for="item in archiveCard" :item="item" :key="item.title"/>
    </n-card>
</template>
<style scoped>
</style>