import {ref} from 'vue'
import {defineStore} from 'pinia'

export const useBreadcrumbStore = defineStore('breadcrumb', () => {
        const breadcrumbItem = ref<Array<String>>(new Array<String>())

        return {breadcrumbItem}
    },
    {
        persist: true
    }
)
