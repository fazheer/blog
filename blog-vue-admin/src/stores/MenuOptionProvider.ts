import {defineStore} from 'pinia'
import {RouterLink} from "vue-router";
import type {Component} from "vue";
import {h} from "vue";
import {type MenuOption, NIcon} from "naive-ui";
import {
    ChatboxEllipsesOutline as ChatIcon,
    DocumentTextOutline as DocumentIcon,
    List as ListIcon,
    NewspaperOutline as ArticleIcon,
    PersonOutline as PersonIcon,
    PricetagOutline as TagIcon,
    SettingsOutline as SettingsIcon
} from "@vicons/ionicons5";
import WriteIcon from '@/components/icons/WriteIcon'
import CategoryIcon from '@/components/icons/CategoryIcon'
import ExceptionIcon from '@/components/icons/ExceptionIcon'
import OptionIcon from '@/components/icons/OptionIcon'
import ErrorIcon from '@/components/icons/ErrorIcon'
import F0FIcon from '@/components/icons/404Icon'
import V0VIcon from '@/components/icons/500Icon'

function renderIcon(icon: Component) {
    return () => h(NIcon, null, {default: () => h(icon)})
}

export const useMenuOption = defineStore('useMenuOption', () => {
    const menuOptions: MenuOption[] = [
        {
            label: () =>
                h(
                    RouterLink,
                    {
                        to: {
                            path: "/"
                        }
                    },
                    {default: () => '主控台'}
                ),
            key: '/',
            icon: renderIcon(OptionIcon),
        },
        {
            label: '文章管理',
            key: "article",
            icon: renderIcon(ArticleIcon),
            children: [
                {
                    label: () =>
                        h(
                            RouterLink,
                            {
                                to: {
                                    path: "/article"
                                }
                            },
                            {default: () => '文章列表'}
                        ),
                    icon:renderIcon(ListIcon),
                    key: '/article',
                },
                {
                    label: () =>
                        h(
                            RouterLink,
                            {

                                to: {
                                    path: '/publish'
                                }
                            },
                            {default: () => '发布文章'}
                        ),
                    icon:renderIcon(WriteIcon),
                    key: '/publish',
                },
                {
                    label: () =>
                        h(
                            RouterLink,
                            {
                                to: {
                                    path: '/category'
                                }
                            },
                            {default: () => '分类管理'}
                        ),
                    icon:renderIcon(CategoryIcon),
                    key: '/category',
                },
                {
                    label: () =>
                        h(
                            RouterLink,
                            {
                                to: {
                                    path: '/tag'
                                }
                            },
                            {default: () => '标签管理'}
                        ),
                    icon:renderIcon(TagIcon),
                    key: '/tag',
                }
            ]
        },
        {
            label: () =>
                h(
                    RouterLink,
                    {
                        to: {
                            path: '/comment'
                        }
                    },
                    {default: () => '评论管理'}
                ),

            key: '/comment',
            icon: renderIcon(ChatIcon)
        },
        {
            label: '日志管理',
            key: '/log',
            icon: renderIcon(DocumentIcon),
            children: [
                {
                    label: () =>
                        h(
                            RouterLink,
                            {
                                to: {
                                    path: "/exception"
                                }
                            },
                            {default: () => '异常日志'}
                        ),
                    icon: renderIcon(ExceptionIcon),
                    key: '/exception',
                },
                {
                    label: () =>
                        h(
                            RouterLink,
                            {
                                to: {
                                    path: '/option'
                                }
                            },
                            {default: () => '操作日志'}
                        ),
                    icon: renderIcon(OptionIcon),
                    key: '/option',
                }

            ]

        },
        {
            label: '异常页面',
            icon: renderIcon(ErrorIcon),
            key: "/error",
            children: [
                {
                    label: () =>
                        h(
                            RouterLink,
                            {
                                to: {
                                    path: "/404"
                                }
                            },
                            {default: () => '404'}
                        ),
                    icon: renderIcon(F0FIcon),
                    key: '/404',
                },
                {
                    label: () =>
                        h(
                            RouterLink,
                            {
                                to: {
                                    path: '/505'
                                }
                            },
                            {default: () => '505'}
                        ),
                    icon: renderIcon(V0VIcon),
                    key: '/505',
                }]
        },
        {
            label: () =>
                h(
                    RouterLink,
                    {
                        to: {
                            path: '/setting'
                        }
                    },
                    {default: () => '网站设置'}
                ),
            key: '/setting',
            icon: renderIcon(SettingsIcon),

        },
    ]
    return {menuOptions}
})