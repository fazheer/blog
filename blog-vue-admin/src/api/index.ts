import axios from "axios";
import jsonBig from 'json-bigint/index'
import type {ArticleVO, Setting, User} from "@/type";
import {useTokenStore} from "@/stores/useTokenStore";
import {useMessage} from 'naive-ui'

const axiosService = axios.create({
    baseURL:"/api",
    transformResponse: [
        function (data) {
            try {
                const parseData = jsonBig.parse(data);

                if (parseData.code == 40001) {
                    const tokenStore = useTokenStore()
                    const message = useMessage()
                    message.error("token过期，请重新登录")
                    tokenStore.avatarToken.token = 'expired'
                }
                return parseData
            } catch {
                return data
            }
        }
    ],
    transformRequest: [function (data) {
        try {
            return jsonBig.stringify(data)
        } catch {
            return data;
        }
    }]
});
axiosService.interceptors.request.use(
    (config: any) => {
        const tokenStore = useTokenStore()
        config.headers.token = tokenStore.avatarToken.token
        return config
    }
)


axiosService.defaults.headers.common["Content-Type"] = "application/json"
axiosService.defaults.headers.post["Content-Type"] = "application/json"
axiosService.defaults.headers.put["Content-Type"] = "application/json"
axiosService.defaults.headers.get["Content-Type"] = "application/json"
export default {
    axiosService,
    login: (user: User) => {
        return axiosService.post("/login", user)
    },
    logout: () => {
        return axiosService.get("/customer/logout")
    },
    getArticleById: (id: bigint) => {
        return axiosService.get(`/article/admin/id/${id}`)
    },
    getArticleCount: () => {
        return axiosService.get("/article")
    },
    getArticleCountByCategoryId: (id: bigint) => {
        return axiosService.get(`/article/category/${id}`)
    },
    getDraftArticleCountByCategoryId: (id: bigint) => {
        return axiosService.get(`/article/admin/draft/category/${id}`)
    },
    getDelArticleCountByCategoryId: (id: bigint) => {
        return axiosService.get(`/article/admin/del/category/${id}`)
    },
    getArticleCountByTitle: (title: String) => {
        return axiosService.get(`/article/title/${title}`)
    },
    getDraftArticleCountByTitle: (title: String) => {
        return axiosService.get(`/article/admin/draft/title/${title}`)
    },
    getDelArticleCountByTitle: (title: String) => {
        return axiosService.get(`/article/admin/del/title/${title}`)
    },
    getArticleCountByTagId: (id: bigint) => {
        return axiosService.get(`/article/tag/${id}`)
    },
    getDraftArticleCountByTagId: (id: bigint) => {
        return axiosService.get(`/article/admin/draft/tag/${id}`)
    },
    getDelArticleCountByTagId: (id: bigint) => {
        return axiosService.get(`/article/admin/del/tag/${id}`)
    },
    getDraftArticleCount: () => {
        return axiosService.get("/article/admin/draft")
    },
    getDelArticleCount: () => {
        return axiosService.get("/article/admin/del")
    },
    getArticleList: (pageNum: bigint) => {
        return axiosService.get(`/article/admin/${pageNum}`)
    },
    getArticleDraftList: (pageNum: bigint) => {
        return axiosService.get(`/article/admin/draft/${pageNum}`)
    },
    getArticleDelList: (pageNum: bigint) => {
        return axiosService.get(`/article/admin/del/${pageNum}`)
    }, getArticleListByCategoryId: (categoryId: bigint, pageNum: bigint) => {
        return axiosService.get(`/article/admin/category/${categoryId}/${pageNum}`)
    },
    getArticleDraftListByCategoryId: (categoryId: bigint, pageNum: bigint) => {
        return axiosService.get(`/article/admin/draft/category/${categoryId}/${pageNum}`)
    },
    getArticleDelListByCategoryId: (categoryId: bigint, pageNum: bigint) => {
        return axiosService.get(`/article/admin/del/category/${categoryId}/${pageNum}`)
    },
    getArticleListByTagId: (tagId: bigint, pageNum: bigint) => {
        return axiosService.get(`/article/admin/tag/${tagId}/${pageNum}`)
    },
    getArticleDraftListByTagId: (tagId: bigint, pageNum: bigint) => {
        return axiosService.get(`/article/admin/draft/tag/category/${tagId}/${pageNum}`)
    },
    getArticleDelListByTagId: (tagId: bigint, pageNum: bigint) => {
        return axiosService.get(`/article/admin/del/tag/${tagId}/${pageNum}`)
    },
    getArticleListByTitle: (title: String, pageNum: bigint) => {
        return axiosService.get(`/article/admin/title/${title}/${pageNum}`)
    },
    getArticleDraftListByTitle: (title: String, pageNum: bigint) => {
        return axiosService.get(`/article/admin/draft/title/category/${title}/${pageNum}`)
    },
    getArticleDelListByTitle: (title: String, pageNum: bigint) => {
        return axiosService.get(`/article/admin/del/title/${title}/${pageNum}`)
    },
    delArticle: (id: bigint) => {
        return axiosService.delete(`/article/admin/del/${id}`)
    },
    delArticles: (ids: Array<bigint>) => {
        return axiosService.put('/article/admin/del', ids)
    },
    delArticlesPhysics: (ids: Array<bigint>) => {
        return axiosService.put('/article/admin/del/physics', ids)
    },
    getTags: () => {
        return axiosService.get(`/tag`)
    },
    getTagsCard: (pageNum: bigint) => {
        return axiosService.get(`/tag/card/${pageNum}`)
    },
    getTagsCount: () => {
        return axiosService.get('/tag/card/count')
    },
    updateTop(value: boolean, id: bigint) {
        return axiosService.get(`/article/isTop/${value}/id/${id}`)
    },
    delCategories: (ids: Array<bigint>) => {
        return axiosService.put('/categories/del', ids)
    },
    getCategoryCard: (pageNum: bigint) => {
        return axiosService.get(`/categories/card/admin/${pageNum}`)
    },
    getCategoryCardCount: () => {
        return axiosService.get("/categories/card/count")
    },
    getCategoriesCard: () => {
        return axiosService.get("/categories/card")
    },
    getCategories: () => {
        return axiosService.get("/categories")
    },
    getWeekView: () => {
        return axiosService.get("/info/week")
    },
    getArticleViewRank: () => {
        return axiosService.get("/article/rank")
    },
    getAllView: () => {
        return axiosService.get("/info/view")
    },
    getCommentCount: () => {
        return axiosService.get("/comment/count")
    },
    recoverArticle: (id: bigint) => {
        return axiosService.get(`/article/admin/del/recovery/${id}`)
    },
    getComments: (pageNum: bigint) => {
        return axiosService.get(`/comment/admin/${pageNum}`)
    },
    getCommentsCount: () => {
        return axiosService.get(`/comment/admin/count`)
    },
    delComment: (id: bigint) => {
        return axiosService.delete(`/comment/del/${id}`)
    },
    delComments: (ids: Array<bigint>) => {
        return axiosService.put('/comment/del', ids)
    },
    delTag: (ids: Array<bigint>) => {
        return axiosService.put('/tag/del', ids)
    },
    saveCategory: (value: String) => {
        return axiosService.post("/categories", {
            name: value
        })
    },
    saveTag: (value: String) => {
        return axiosService.post("/tag", {name: value})
    },
    searchCategory: (name: String, pageNum: bigint) => {
        return axiosService.get(`/categories/card/admin/name/${name}/page/${pageNum}`)
    },
    searchTag: (name: String, pageNum: bigint) => {
        return axiosService.get(`/tag/card/name/${name}/page/${pageNum}`)
    },
    getSearchCategoryCount: (name: String) => {
        return axiosService.get(`/categories/card/admin/name/${name}`)
    },
    getSearchTagCount: (name: String) => {
        return axiosService.get(`/tag/card/name/${name}`)
    },
    updateCategory: (id: bigint, name: String) => {
        return axiosService.put("/categories", {
            id: id,
            name: name
        })
    },
    updateTag: (id: bigint, name: String) => {
        return axiosService.put("/tag", {
            id: id,
            name: name
        })
    },
    publishArticle:(article:ArticleVO)=>{
        return axiosService.post('/article',article)
    },
    removeCover:(fileName:String)=>{
        return axiosService.delete(`/file/cover/${fileName}`)
    },
    updateArticle(article: ArticleVO) {
        return axiosService.put('article',article)
    },
    getSetting:()=>{
        return axiosService.get('/info/setting')
    },
    updateSetting:(setting:Setting)=>{
        return axiosService.put('/info/setting',setting)
    }


}