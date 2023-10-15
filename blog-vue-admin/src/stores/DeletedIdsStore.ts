import {ref} from 'vue'
import {defineStore} from 'pinia'


export const useDeletedIdsStore = defineStore('deletedIds', () => {
        const ids = ref<Set<bigint>>(new Set<bigint>())
        function btnDisabled() :boolean{
            return ids.value.size == 0
        }
        return {ids,btnDisabled}
    },
)
