<template>
    <Page page-title="留言板">
      <md-editor model-value="# 留下你想说的话" previewTheme="vuepress" showCodeRowNumber code-theme="vuepress"
                 preview-only />
      <n-divider dashed />
      <n-message-provider>
        <Comment :page-id="1001n"/>
      </n-message-provider>
    </Page>
</template>
<script setup lang="ts">
import {NDivider, NMessageProvider} from 'naive-ui';
import { ref, onMounted } from 'vue';
import Page from '@/components/page';
import Comment from '@/components/comment';
import MdEditor from "md-editor-v3";
</script>
<style scoped></style>