import {ref} from 'vue'
import {defineStore} from 'pinia'

export const useModelShowStore = defineStore('publishModalStore', () => {
    const showPublish = ref(false)
    const showUpdate = ref(false)
    return {showPublish,showUpdate}
})
