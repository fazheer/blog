
<script setup lang="ts">
import {ref, onMounted, reactive, watch} from 'vue';
import {ListCircle, CalendarClear} from '@vicons/ionicons5'
import {NIcon, NCard, NDivider,NMessageProvider} from 'naive-ui';
import MdEditor from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';
import type {TocItem} from 'md-editor-v3/lib/MdEditor/extensions/MdCatalog';
import PaginationPost from "@/components/Post/PaginationPost.vue";
import Comment from "@/components/comment";
import Banner from "@/components/banner";
import api from "@/api";
import type {Article} from "@/type";
import {useRouter, useRoute} from "vue-router";
const route = useRoute()
const router = useRouter()
const articleId =BigInt( route.params.id) as bigint;
const GetArticle = (articleId: bigint)=> {
  api.getArticleById(articleId).then(({data:{data}})=>{
    article.value=data
    articleContent.value=article.value.content
  });
}
const articleContent = ref<String>()
const article = ref<Article>({
  content: "",
  coverUrl: "",
  createAt: undefined,
  id: 0n,
  nextArticle:  {
    id: 0n,
    title: "",
    coverUrl: "",
    createAt:null
  },
  prevArticle: {
    id: 0n,
    title: "",
    coverUrl: "",
    createAt:null
  },
  title: "",
  views: 0n

})
onMounted(() => {
GetArticle(articleId)
})
watch(()=>route.params.id,()=>{
  router.go(0)
})

const MdCatalog = MdEditor.MdCatalog;
const state = ref({
  text: articleContent,
  id: "post-content",
  theme: 'light'
})
const scrollElement = document.documentElement;
const onClick = (e: MouseEvent, t: TocItem) => {
  history.replaceState({}, '', `${location.pathname}#${t.text}`);
  let menuBar = document.getElementById('menu-bar');
  if (menuBar) menuBar.style.position = 'absolute'
}
</script>
<template>
  <Banner :background-url="article.coverUrl" :t="1">
    <div class="post-title">
      <div class="title">{{ article.title}}</div>
      <div class="time">
        <div class="icon">
          <n-icon color="white">
            <CalendarClear/>
          </n-icon>
        </div>
        <span>发表于 {{ new Date().toLocaleDateString() }}</span>
      </div>
    </div>
  </Banner>
  <div class="post-page">
    <div class="post-container">
      <div class="post-box">
        <md-editor v-model="state.text" :editor-id="state.id" previewTheme="vuepress" showCodeRowNumber
                   code-theme="github"
                   preview-only/>
        <pagination-post :prev-article="article.prevArticle" :next-article="article.nextArticle"/>
        <n-divider dashed/>
        <n-message-provider>
          <Comment :page-id="articleId"/>
        </n-message-provider>

      </div>

      <div class="catalog-container">
        <n-card>
          <template #header>
            <div style="display: inline-flex; align-items: center;">
              <n-icon :size="24">
                <ListCircle/>
              </n-icon>
              <span>目录</span>
            </div>

          </template>
          <md-catalog :editor-id="state.id" :scroll-element="scrollElement" :scroll-element-offset-top="10"
                      @on-click="onClick"/>
        </n-card>

      </div>

    </div>

  </div>
</template>
<style scoped>
.post-title {
  display: flex;
  color: white;
  flex-direction: column;
  align-items: center;
  position: absolute;
  top: 20%;
}

.title {
  font-size: 3rem;

}

.icon {
  padding-right: 3px;
}

.time {
  font-size: 0.9rem;
  display: inline-flex;
  align-items: center;
}

.comment-container {
  font-size: 1.5rem;
  display: inline-flex;
  align-items: center;
  margin: 40px 0 40px 0;
}

.comment-title {
  font-size: 1.2rem;
}

.post-page {
  max-width: 100%;
  width: 1200px;
  display: flex;
  justify-content: center;
}

.post-container {
  width: 100%;
  padding: 50px 40px;
  display: flex;
  justify-content: center;
  flex-direction: row;
}

.post-box {
  width: 74%;
  display: flex;
  position: relative;
  flex-direction: column;
  justify-content: center;
  padding: 50px 40px;
  border-radius: 8px;
  box-shadow: 0 3px 8px 6px rgba(7, 17, 27, 0.05);
}

.catalog-container {
  position: sticky;
  top: 0;
  height: min-content;
  padding-left: 15px;
}


@media (min-width: 2000px) {
  .post-page {
    max-width: 100%;
    width: 1500px;
    display: flex;
    justify-content: center;
  }
}

@media (max-width: 900px) {
  .post-container {
    width: 100%;
    padding: 40px 15px;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
  }

  .post-box {
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
  .post-container {
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    padding: 20px 5px;
  }


}
</style>