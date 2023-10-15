<template>
  <div class="banner"  :style="{'--before-height':beforeBannerHeight,backgroundImage:`url(${props.backgroundUrl})`}" >
    <slot>
      <div class="page-info">
        <HomeLogo text="jiaoker"/>
        <div class="typing-container">
          <TypingWord text="啊对对对！啊对对对"/>
        </div>
      </div>
    </slot>
  </div>
</template>

<script lang="ts" setup>
import {ref, onMounted} from 'vue';
import HomeLogo from '@/components/cover/HomeLogo';
import TypingWord from '@/components/cover/TypingWord'
const props = defineProps({
      backgroundUrl: {
        type:String,
        default:'/preview.png'
      },
      t:{
        type:Number,
        default:0
      }
    }
);
const beforeBannerHeight =ref('100%')
onMounted(() => {
  if(props.t==1) {
    beforeBannerHeight.value='400px';
  }else {
    beforeBannerHeight.value='100vh'
  }
})

</script>

<style scoped>

.banner {
  width: 100%;
  height: var(--before-height);
  background-size: cover;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.banner::before {
  content: '';
  position: absolute;
  width: 100%;
  height: var(--before-height);
  background-color: rgba(0, 0, 0, 0.5);
}

.page-info {
  position: absolute;
  color: #fff;
  text-align: center;
}

.typing-container {
  display: flex;
  flex-direction: row;
  flex-wrap: nowrap;
}
</style>