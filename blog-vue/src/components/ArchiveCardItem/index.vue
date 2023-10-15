<template>
  <router-link  :to="`/archive/${item.title}`" class="categories">
                <span>
                    {{item.title}}
                </span>
    <span class="count">
                    {{item.count}}
                </span>
  </router-link>
</template>

<script lang="ts" setup>
import type {ArchiveCard} from "@/type";

const props = defineProps<{
  item:ArchiveCard
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