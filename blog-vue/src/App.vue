<script setup lang="ts">
import { RouterView } from 'vue-router'
import Menu from './components/menu/Menu.vue';
import Footer from '@/components/footer'
</script>

<template>
  <main>
    <Menu/>
    <RouterView />
  </main>
  <footer>
    <Footer/>
  </footer>
</template>
<style scoped>


main {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
footer{
  width: 100%;
  height: 20vh;
  display: flex;
  background-image: url("/preview.png");
  background-size: cover;
  justify-content: center;
  align-items: center;
}
footer::before{
  content: '';
  position: absolute;
  width: 100%;
  height: 20vh;
  background-color: rgba(0, 0, 0, 0.5);
}
</style>