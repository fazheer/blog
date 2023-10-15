import {createRouter, createWebHistory} from 'vue-router'

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
            component: () => import('@/views/tagArticle')
        },
        {
            path: '/categories',
            name: 'categories',
            component: () => import('@/views/Categories.vue')
        },
        {
            path: '/categories/:categoryName/:id',
            name: 'categoriesArticle',

            component: () => import('@/views/categoriesArticle')
        }
    ],
    scrollBehavior(to, from, savedPosition) {
        if (savedPosition) {
            return savedPosition
        } else {
            return {top: 0}
        }
    }
}
)

export default router
