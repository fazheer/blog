<template>
    <Banner background-url="/banner.jpg" :t="1">
        <PageTitle :page-title="pageTitle" />
    </Banner>
    <div class="page-container">
        <div class="content-box">
            <slot>
                <md-editor v-model="props.pageContent" previewTheme="vuepress" showCodeRowNumber code-theme="github"
                    preview-only />
                <n-divider dashed />
            </slot>
        </div>
        <Aside />
    </div>
</template>
<script setup lang="ts">
import Aside from '@/components/aside';
import { ref, onMounted } from 'vue';
import { NIcon, NCard, NDivider } from 'naive-ui';
import MdEditor from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';
import Banner from "@/components/banner";
import PageTitle from './pageTitle';

 
const props = defineProps({
    pageContent: {
        type: String,
        default: '> 这里没有内容'
    },
    pageTitle: {
        type: String,
        default: '一个页面'
    }
})


</script>
<style scoped>
.page-container {
    max-width: 100%;
    width: 1200px;
    padding: 40px 15px;
    display: flex;
    justify-content: center;
}

.content-box {
    width: 74%;
    height: fit-content;
    padding: 50px 40px;
    border-radius: 8px;
    box-shadow: 0 3px 8px 6px rgba(7, 17, 27, 0.05);

}

@media (min-width: 2000px) {
    .page-container {
        max-width: 100%;
        width: 1500px;
        display: flex;
        justify-content: center;
    }
}

@media (max-width: 900px) {
    .main-container {
        width: 100%;
        padding: 40px 15px;
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
    }

    .content-box {
        width: 100%;
        display: flex;
        position: relative;
        justify-content: center;
        flex-direction: column;
        padding: 40px 15px;
        border-radius: 8px;
        box-shadow: 0 3px 8px 6px rgba(7, 17, 27, 0.05);
    }

    .catalog-container {
        display: none;
    }
}

@media (max-width: 768px) {
    .main-container {
        width: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
        padding: 20px 5px;
    }


}
</style>