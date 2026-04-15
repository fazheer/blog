import axios from 'axios'
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

axiosService.interceptors.response.use(
    (response) => response,
    (error) => {
        console.error('API Error:', error);
        return Promise.reject(error);
    }
);

export const getArticleCount = () => {
    return axiosService.get(`/article`)
}
export const getCountInfo = () => {
    return axiosService.get("/article/home")
}
export const getArticleCountByTagId = (tagId: bigint) => {
    return axiosService.get(`/article/tag/${tagId}`)
}
export const getArticleCountByCategoryId = (categoryId: bigint) => {
    return axiosService.get(`/article/categories/${categoryId}`)
}
export const getHomeArticles = (pageNum: number) => {
    return axiosService.get(`/article/home/${pageNum}`)
}
export const getArticleById = (articleId: bigint) => {
    return axiosService.get(`/article/${articleId}`)
}
export const getCategories = () => {
    return axiosService.get("/categories")
}
export const getTopSixArticle = () => {
    return axiosService.get("/article/top")
}
export const getArticleYear = (pageNum: bigint) => {
    return axiosService.get(`/article/archive/${pageNum}`)
}
export const getArchiveArticleTitle = (title: string, pageNum: bigint) => {
    return axiosService.get(`/article/archive/title/${title}/${pageNum}`)
}
export const getCategoryArticleYear = (categoryId: bigint, pageNum: bigint) => {
    return axiosService.get(`/article/categories/${categoryId}/pageNum/${pageNum}`)
}
export const getTagArticleYear = (categoryId: bigint, pageNum: bigint) => {
    return axiosService.get(`/article/tag/${categoryId}/pageNum/${pageNum}`)
}
export const getCategoryCard = () => {
    return axiosService.get("/categories/card")
}
export const getArchiveCard = () => {
    return axiosService.get("/categories/archive")
}
export const getTags = () => {
    return axiosService.get("/tag")
}
export const getComment = (pageId: bigint) => {
    return axiosService.get(`/comment/${pageId}`)
}
export const getReplies = (commentId: bigint) => {
    return axiosService.get(`/comment/reply/${commentId}`)
}
export const saveComment = (comment: CommentVO) => {
    return axiosService.post('/comment/save/comment', comment)
}
export const saveReply = (reply: ReplyVO) => {
    return axiosService.post('/comment/save/reply', reply)
}
export const getReplyUrl = (id: bigint) => {
    return axiosService.get(`/comment/url/${id}`)
}
export const getSiteInfo = () => {
    return axiosService.get("/info")
}
export const getArchiveArticleTitleCount = (title: string) => {
    return axiosService.get(`/article/archive/title/${title}`)
}
export const updateCommentLove = (commentId: bigint) => {
    return axiosService.get(`/comment/comment/love/${commentId}`)
}
export const updateReplyLove = (replyId: bigint) => {
    return axiosService.get(`/comment/reply/love/${replyId}`)
}
export const getIcpAndGov = () => {
    return axiosService.get("/info/icp")
}
