import {defineStore} from 'pinia'
import {ref} from "vue";
export const useLoveIdStore = defineStore('loveIds', () => {
        const loveId =ref(new Array<String>())
        function addId (id:bigint):boolean {
            const idString = id.toString()
            let flag:boolean
            if(loveId.value.indexOf(idString)!=-1) {
                flag=false
            }else {
                loveId.value.push(idString)
                flag=true
            }
            return  flag
        }

        return {loveId,addId};
    },
    {
        persist:{
            enabled:true,
            strategies:[
                {
                    storage:localStorage,
                }
            ]
        }
    }
)