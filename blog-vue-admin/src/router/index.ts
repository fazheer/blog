import {createRouter, createWebHistory} from 'vue-router'
import {useProviderStore} from "@/stores/provider";
import {useTokenStore} from "@/stores/useTokenStore";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [

        {
            path: '/Login',
            name: 'Login',
            component: () => import("@/views/Login")
        },
        {
            path: '/',
            component: () => import("@/views/Home"),
            children: [
                {
                    path: "",
                    name: "主控台",
                    component: () => import("@/views/Console")
                },
                {
                    path: "article",
                    name: "文章列表",
                    component: () => import("@/views/ArticleView/List")
                },
                {
                    path: "publish",
                    name: "发布文章",
                    component: () => import("@/views/ArticleView/Publish")
                },
                {
                    path: "category",
                    name: "分类管理",
                    component: () => import("@/views/ArticleView/Categories")
                },
                {
                    path: "update/:id",
                    name: "更新文章",
                    component: () => import("@/views/ArticleView/Update")
                },
                {
                    path: "tag",
                    name: "标签管理",
                    component: () => import("@/views/ArticleView/Tag")
                },
                {
                    path: "comment",
                    name: "评论管理",
                    component: () => import("@/views/Comment")
                },
                {
                    path: "exception",
                    name: "异常日志",
                    component: () => import("@/views/LoggingView/ExceptionLog")
                },
                {
                    path: "option",
                    name: "操作日志",
                    component: () => import("@/views/LoggingView/OptionLog")
                },
                {
                    path: "setting",
                    name: "网站设置",
                    component: () => import("@/views/SiteSetting")
                },
                {
                    path:'/:catchAll(.*)',
                    component:()=>import('@/views/404/index')
                },
                {
                    path:'404',
                    name:'404',
                    component:()=>import('@/views/404/index')
                },
                {
                    path:'505',
                    name:'505',
                    component:()=>import('@/views/505/index')
                }
            ]
        }
    ],
    scrollBehavior() {
        return {
            top: 0,
            behavior: 'smooth'
        }
    }
})
router.beforeEach((to,from,next) => {
  if(to.path!='/login' && (useTokenStore().avatarToken.token=='expired' &&useTokenStore().avatarToken==undefined ||useTokenStore().avatarToken.token=="")) {
      next('/login')
  }else {
      next()
  }
    useProviderStore().loadingBar?.start()
})
router.afterEach(() => {
    useProviderStore().loadingBar?.finish()
})

export default router
