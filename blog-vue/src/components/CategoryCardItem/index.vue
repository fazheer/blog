<template>
  <router-link  :to="`/categories/${item.name}/${item.id}`" class="categories">
                <span>
                    {{item.name}}
                </span>
    <span class="count">
                    {{item.count}}
                </span>
  </router-link>
</template>
<script lang="ts" setup>
import type {CategoryCard} from "@/type";

defineProps<{
  item:CategoryCard
}>()
</script>

<style scoped>
.categories{
  width: auto;
  height: auto;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  padding-bottom: 10px;
}
.categories:hover{
  background-color: rgb(151, 172, 175 );
  font-size: 1rem;
  padding: 5px;
}
</style>