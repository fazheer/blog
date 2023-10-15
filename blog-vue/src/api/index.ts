import axios, {AxiosHeaders} from 'axios'
import jsonBig from 'json-bigint/index'
import type {CommentVO, ReplyVO} from "@/type";

const axiosService = axios.create({
    baseURL:"/api",
    transformResponse: [function (data) {
        try {
            return jsonBig.parse(data)
        } catch {
            return data
        }
    }]
    , transformRequest: [function (data) {
        try {
            return jsonBig.stringify(data)
        } catch {
            return data;
        }
    }]
});
axiosService.defaults.headers.common["Content-Type"] = "application/json"
axiosService.defaults.headers.post["Content-Type"] = "application/json"
axiosService.defaults.headers.put["Content-Type"] = "application/json"
export default {
    getArticleCount: () => {
        return axiosService.get(`/article`)
    },
    getCountInfo:()=>{
      return axiosService.get("/article/home")
    },
    getArticleCountByTagId: (tagId:bigint) => {
        return axiosService.get(`/article/tag/${tagId}`)
    },
    getArticleCountByCategoryId:( categoryId:bigint) => {
        return axiosService.get(`/article/categories/${categoryId}`)
    },
    getHomeArticles: (pageNum: number) => {
        return axiosService.get(`/article/home/${pageNum}`)
    },
    getArticleById: (articleId: bigint) => {
        return axiosService.get(`/article/${articleId}`)
    },
    getCategories: () => {
        return axiosService.get("/categories")
    },
    getTopSixArticle: () => {
        return axiosService.get("/article/top")
    },
    getArticleYear: (pageNum: bigint) => {
        return axiosService.get(`/article/archive/${pageNum}`)
    },
    getArchiveArticleTitle: (title:String,pageNum: bigint) => {
        return axiosService.get(`/article/archive/title/${title}/${pageNum}`)
    },
    getCategoryArticleYear: (categoryId: bigint, pageNum: bigint) => {
        return axiosService.get(`/article/categories/${categoryId}/pageNum/${pageNum}`)
    },
    getTagArticleYear: (categoryId: bigint, pageNum: bigint) => {
        return axiosService.get(`/article/tag/${categoryId}/pageNum/${pageNum}`)
    },
    getCategoryCard: () => {
        return axiosService.get("/categories/card")
    },
    getArchiveCard: () => {
        return axiosService.get("/categories/archive")
    },
    getTags: () => {
        return axiosService.get("/tag")
    }
    ,
    getComment: (pageId: bigint) => {
        return axiosService.get(`/comment/${pageId}`)
    },
    getReplies: (commentId: bigint) => {
        return axiosService.get(`/comment/reply/${commentId}`)
    },
    saveComment: (comment: CommentVO) => {
        return axiosService.post('/comment/save/comment', comment)
    },
    saveReply: (reply: ReplyVO) => {
        return axiosService.post('/comment/save/reply', reply)
    },
    getReplyUrl: (id: bigint) => {
        return axiosService.get(`/comment/url/${id}`)
    },
    getSieInfo:()=>{
        return axiosService.get("/info")
    },
    getArchiveArticleTitleCount(title: string) {
      return axiosService.get(`/article/archive/title/${title}`)
    },
    updateCommentLove:(commentId:bigint)=>{
        return axiosService.get(`/comment/comment/love/${commentId}`)
    },
    updateReplyLove:(replyId:bigint)=>{
        return axiosService.get(`/comment/reply/love/${replyId}`)
    },
    getIcpAndGov:()=>{
        return axiosService.get("/info/icp")
    }


}