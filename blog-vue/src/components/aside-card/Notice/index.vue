<script setup lang="ts">
import { NotificationsOutline } from '@vicons/ionicons5'
import { NCard,NIcon} from "naive-ui";
defineProps<{
  noticle:String
}>()
</script>

<template>
    <n-card hoverable embedded>
        <template #header>
            <div class="card-title">
                <n-icon size="18" color="red">
                    <NotificationsOutline />
                </n-icon>
                <span>公告</span>
            </div>
        </template>

        <div class="notice-msg">{{ noticle }}</div>
    </n-card>
</template>
<style scoped>
.notice-msg {
    margin-top: 5px;
}
</style>