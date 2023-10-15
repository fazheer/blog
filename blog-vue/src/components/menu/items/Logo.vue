<script setup lang="ts">
import { NButton } from 'naive-ui';
defineProps({
  text: String,
  fontColor: String
})
</script>
<template>
  <div class="logo" :color="fontColor">
    <router-link to="/">
      <NButton :color="fontColor" text>
        <span>{{ text }}</span>
      </NButton>
    </router-link>
  </div>
</template>
<style scoped>
.logo {
  padding-left: 20px;
  min-width: 10%;
}
span {
  font-size: 1.4rem;
  text-shadow: 2px 2px 4px rgb(0 0 0 / 15%);
  font-weight: bold;
  cursor: pointer;
}

@media (max-width: 768px) {
  .logo {
    padding-left: 20px;
    min-width: 10%;
    font-size: 1rem;
  }
}
</style>