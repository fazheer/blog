<script setup lang="ts">
import { NCard, NAvatar, NButton, NIcon, NSpace } from "naive-ui";

import { LogoGithub, Mail } from "@vicons/ionicons5";
import { RouterLink } from "vue-router";
import { ref } from "vue";
const url = ref("https://s2.loli.net/2022/04/07/1HolLJn4tfzvqEU.jpg")
const props = defineProps<{
  avatarUrl:String
  name:String
  personProfile:String
  articleCount:bigint
  tagCount:bigint
  categoryCount:bigint
  projectUrl:String
  email:String
}>()
</script>
<template>
    <n-card embedded hoverable>
        <div class="pandect-container">
            <n-avatar :src="avatarUrl" round :size="120" class="avatar" />
            <h1>{{ name }}</h1>
          <span>{{personProfile}}</span>
            <n-space justify="space-between" style="width:100%">
                <router-link to="/archive">
                    <span class="article">文章</span>
                    <p>{{ articleCount }}</p>
                </router-link>
                <router-link to="/tag">
                    <div class="article">标签</div>
                    <p>{{tagCount}}</p>
                </router-link>
                <router-link to="/categories">
                    <div class="article">分类</div>
                    <p>{{categoryCount}}</p>
                </router-link>
            </n-space>
            <n-button 
                class="github-btn" 
                type="primary" 
                tag="a" 
                target="_blank" 
                :href="projectUrl"
                color="#97acaf"
            >
                <template #icon>
                    <logo-github/>
                </template>
                项目地址
            </n-button>
            <div class="social-box">
              <n-button
                  text
                  tag="a"
                  target="_blank"
                  :href='`mailto:${email}`'
              >
                <template #icon>
                  <n-icon :size="20">
                    <Mail />
                  </n-icon>
                </template>
              </n-button>

            </div>
        </div>
    </n-card>
</template>
<style scoped>
.n-avatar {
    transition: transform ease-out 1s;
}

.n-avatar:hover {

    transform: rotateZ(360deg);
}

.pandect-container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    text-align: center;
}

.info-container {
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: space-around;
    margin: 5px 0 5px 0;
}

.github-btn {
    width: 100%;
    margin: 5px 0 5px 0;
}

.social-box {
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    justify-content: space-around;
}
.card-info {
    display: flex;
    width: 100%;
}</style>