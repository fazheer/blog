<script setup lang="ts">
import {NButton, NIcon} from 'naive-ui';

const props = defineProps({
  name: String,
  routerUrl:{
    default:'/',
   type: String
  }
})
</script>
<template>
  <div class="page-menu-item">
    <router-link :to="routerUrl">
      <slot>
      </slot>
    </router-link>
  </div>
</template>
<style scoped>
.page-menu-item{
  position: relative;
  display: inline-flex;
  align-items: center;
  padding: 0 0 0 15px;
}
.page-menu-item:last-child{
  position: relative;
  display: inline-flex;
  align-items: center;
  padding: 0 15px 0 15px;
}

</style>