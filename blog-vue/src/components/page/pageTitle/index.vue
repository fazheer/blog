<template>
    <span class="page-title">
        {{ pageTitle }}
    </span>
</template>
<script setup lang="ts">
const props = defineProps({
    pageTitle: {
        type: String,
        default: '一个页面'
    }
})
</script>
<style scoped>
.page-title {
    position: absolute;
    font-size: 4rem;
    text-shadow: 2px 2px 4px rgb(0 0 0 / 15%);
    font-weight: bold;
    cursor: pointer;
    color: #fff;
}
</style>