import {ref} from 'vue'
import {defineStore} from 'pinia'

export const useTokenStore = defineStore('tokenStore', () => {
        const avatarToken = ref<{
            avatarUrl:String,
            token:String
        }>({
            avatarUrl: "", token: ""
        })
        return { avatarToken}
    },
    {persist: true}
)
