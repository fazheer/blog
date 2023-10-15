import {defineStore} from 'pinia'
import type {RouterTag} from "@/type";
import VRouter from '@/router'
import {ref} from "vue";

const router =VRouter;
export const useRouterTag = defineStore('useRouterTag',
    ()=>{
    const tags  = ref< Array<RouterTag>>(new Array<RouterTag>())
    function pushTag  (tag:RouterTag){
        if(tag.name=="主控台") return;
        for (let i = 0; i < tags.value.length; i++) {
            if(tags.value[i].name=="更新文章") {
                tags.value[i].path=tag.path
            }
            if(tags.value[i].name==tag.name) {
                return;
            }
        }
        tags.value.push(tag);
    }
    function  deleteTag (tag:RouterTag){
        const index = tags.value.indexOf(tag);
        if(index==tags.value.length-1) {
            tags.value.pop();
            if(tags.value.length==0) {
                router.push("/").then(()=>{})
            }else {
                const path = tags.value[index-1].path
                router.push(`${path}`).then(()=> {
                })
            }
        }else {
            const array = new Array<RouterTag>();
            tags.value.forEach(tagItem=>{
                if(tag.name!=tagItem.name) array.push(tagItem)
            })
            tags.value=array
        }
    }
    return { tags,pushTag,deleteTag}
},{
    persist:true
    }
)