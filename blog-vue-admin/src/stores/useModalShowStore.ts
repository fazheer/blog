import {ref} from 'vue'
import {defineStore} from 'pinia'

export const useModelShowStore = defineStore('publishModalStore', () => {
    const showArticleEdit = ref(false)
    const articleEditMode = ref<'publish' | 'update'>('publish')
    return {showArticleEdit, articleEditMode}
})
