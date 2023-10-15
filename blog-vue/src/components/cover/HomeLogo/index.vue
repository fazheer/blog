<script setup lang="ts">
defineProps(['text'])
</script>
<template>
<span class="logo-content">
    {{ text }}
</span>
</template>
<style scoped>
.logo-content{
    color: #fff;
    font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    font-size: 2.85em;
    text-align: center;
    text-shadow: 2px 2px 4px rgb(0 0 0 / 15%);
    line-height: 1.5;
    font-weight: bold;
    line-height: 2;
}
</style>