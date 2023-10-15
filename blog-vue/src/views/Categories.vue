<template>
    <Page page-title="分类">
        <div class="categories-container">
            <ul class="categories-item-list">
                <li v-for="category in categories" class="categories-item">
                  <router-link :to="`/categories/${category.name}/${category.id}`" >
                    {{ category.name }}
                  </router-link>
                </li>
            </ul>
        </div>
    </Page>
</template>
<script setup lang="ts">
import api from "@/api";
import type {Category} from "@/type";
import {ref, onMounted} from 'vue';
import Page from '@/components/page';
onMounted(()=>{
  api.getCategories().then(({data:{data}})=>{
    categories.value= data as Array<Category>
  })
})

const categories = ref<Array<Category>>()
</script>
<style scoped>


.categories-container ul {
    list-style: none;
    display: block;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    padding-inline-start: 40px;
}

.categories-container ul li {
    position: relative;
    margin: 6px 0;
    padding: .12em .4em .12em 1.4em;
    cursor: pointer;
}

.categories-container ul li:before {
    position: absolute;
    top: .67em;
    left: 0;
    width: .43em;
    height: .43em;
    border: .215em solid #49b1f5;
    border-radius: .43em;
    background: 0 0;
    content: '';
    cursor: pointer;
    -webkit-transition: all .3s ease-out;
    -moz-transition: all .3s ease-out;
    -o-transition: all .3s ease-out;
    -ms-transition: all .3s ease-out;
    transition: all .3s ease-out;
}

.categories-container ul li:hover:before {
    position: absolute;
    top: .67em;
    left: 0;
    width: .43em;
    height: .43em;
    border: .215em solid #e49424;
    border-radius: .43em;
    background: 0 0;
    content: '';
    cursor: pointer;
    -webkit-transition: all .3s ease-out;
    -moz-transition: all .3s ease-out;
    -o-transition: all .3s ease-out;
    -ms-transition: all .3s ease-out;
    transition: all .3s ease-out;
}
.categories-item:hover{
    color:#49b1f5;
}
</style>