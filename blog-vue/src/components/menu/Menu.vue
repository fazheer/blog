<script setup lang="ts">
import { Search, Home, ListCircle, ChevronDown, PaperPlane, Planet, List, Train } from '@vicons/ionicons5';
import { NButton } from 'naive-ui';
import { onMounted, ref } from 'vue';
import Logo from './items/Logo.vue';
import PageMenuItem from './items/PageMenuItem.vue';
const menuBar = ref<HTMLElement>();
  const buttonColor = ref('#eee')
onMounted(() => {

  window.addEventListener('wheel', (e: WheelEvent) => {
    const dom = menuBar.value as HTMLElement;
    if (e.deltaY < 0) {
      if (document.documentElement.scrollTop == 0) {
        buttonColor.value = "#eee"
        dom.style.boxShadow = 'none'
        dom.style.backgroundColor = 'transparent'
        dom.style.opacity = '1'
      } else {
        buttonColor.value = "#000"
        dom.style.position = 'fixed'
        dom.style.backgroundColor = '#fff'
        dom.style.opacity = '0.8'
        dom.style.boxShadow = '0 5px 6px -5px rgb(133 133 133 / 60%)'
        dom.style.zIndex = '1'
      }
    } else if (e.deltaY > 0) {
      dom.style.position = 'absolute'
      buttonColor.value = "#000"

    }
  })
}
)
</script>
<template>
  <div class="site-menu" ref='menuBar' id="menu-bar">
    <Logo text="Jiaoker" :fontColor="buttonColor" />
    <div class="menu-items">
      <div class="search-item">
        <PageMenuItem>
          <n-button :color="buttonColor" text>
            <template #icon>
              <Search />
            </template>
            <span>搜索</span>
          </n-button>
        </PageMenuItem>
      </div>
      <div class="page-menu-items">
        <PageMenuItem>
          <n-button :color="buttonColor" text>
            <template #icon>
              <Home />
            </template>
            <span>首页</span>
          </n-button>
        </PageMenuItem>
        <PageMenuItem routerUrl="/categories">
          <n-button :color="buttonColor" text>
            <template #icon>
              <Home />
            </template>
            <span>分类</span>
          </n-button>
        </PageMenuItem>
        <PageMenuItem routerUrl="/tag">
          <n-button :color="buttonColor" text>
            <template #icon>
              <Home />
            </template>
            <span>标签</span>
          </n-button>
        </PageMenuItem>
        <PageMenuItem routerUrl="/archive">
          <n-button :color="buttonColor" text>
            <template #icon>
              <Train />
            </template>
            <span>归档</span>
          </n-button>
        </PageMenuItem>
        <PageMenuItem routerUrl="/messageboard">
          <n-button :color="buttonColor" text>
            <template #icon>
              <PaperPlane />
            </template>
            <span>留言板</span>
          </n-button>
        </PageMenuItem>
      </div>

    </div>

  </div>

</template>
<style scoped>
.n-button::after {
  height: 3px;
  width: 0px;
  bottom: 0;
  left: 0;
  z-index: -1;
  content: '';
  position: absolute;
  transition: all .3s ease-in-out;
  background-color: #97acaf;
}

.n-button:hover::after {
  width: 100%;
  z-index: 1;
}

.site-menu {
  width: 100%;
  height: 50px;
  position: absolute;
  top: 0;
  z-index: 1;
  display: flex;
  flex-direction: row;
  cursor: pointer;
  align-items: center;
  text-shadow: 1px 1px 2px rgb(0 0 0 / 30%);
  transition: 0.2s ease-in;
}

.menu-items {
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: right;
}


.page-menu-items {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

@media (max-width: 768px) {
  .page-menu-items {
    display: none;
  }
}
</style>