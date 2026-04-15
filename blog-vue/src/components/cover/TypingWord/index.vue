<script setup lang="ts">
import Typed from 'typed.js'
import { ref, onMounted, onUnmounted } from 'vue';
const props = defineProps(['text'])
const typing = ref();
let typed: Typed | null = null;
onMounted(() => {
    typed = new Typed(typing.value, {
        strings: [props.text],
        typeSpeed: 100,
        backSpeed: 60,
        loop: true
    });
})
onUnmounted(() => {
    if (typed) {
        typed.destroy()
    }
})
</script>
<template>
    <div class="text-content" ref="typing">
    </div>
</template>
<style>
.text-content {
    font-size: 1.5rem;
    color: #fff;
    line-height: 1;
}
</style>
