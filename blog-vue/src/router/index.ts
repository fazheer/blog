import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: () => import('../views/HomeView.vue')
        },
        {
            path: '/post/:id',
            name: 'post',
            component: () => import('@/views/PostView.vue')
        },
        {
            path: '/archive',
            name: 'archive',
            component: () => import('@/views/ArchiveView.vue')
        },
        {
            path: '/archive/:title',
            name: 'archiveTitle',
            component: () => import('@/views/ArchiveTitleView.vue')
        },
        {
            path: '/messageboard',
            name: 'messageboard',
            component: () => import('@/views/MessageBoard.vue')
        },
        {
            path: '/tag',
            name: 'tag',
            component: () => import('@/views/TagView.vue')
        },
        {
            path: '/tag/:tagName/:tagId',
            name: 'tagArticle',
            props: route => ({
                type: 'tag',
                id: BigInt(route.params.tagId as string),
                name: route.params.tagName as string
            }),
            component: () => import('@/views/ArticleListByType')
        },
        {
            path: '/categories',
            name: 'categories',
            component: () => import('@/views/Categories.vue')
        },
        {
            path: '/categories/:categoryName/:id',
            name: 'categoriesArticle',
            props: route => ({
                type: 'category',
                id: BigInt(route.params.id as string),
                name: route.params.categoryName as string
            }),
            component: () => import('@/views/ArticleListByType')
        }
    ],
    scrollBehavior(to, from, savedPosition) {
        if (savedPosition) {
            return savedPosition
        } else {
            return { top: 0 }
        }
    }
}
)

export default router
