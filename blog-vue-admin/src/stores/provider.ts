import {ref} from 'vue'
import {defineStore} from 'pinia'
import type {LoadingBarApi} from "naive-ui";

export const useProviderStore = defineStore('provider', () => {
   const loadingBar = ref<LoadingBarApi>()
    function setLoadingBar(b: LoadingBarApi) {
        loadingBar.value=b
    }
    return {loadingBar,setLoadingBar}
})
