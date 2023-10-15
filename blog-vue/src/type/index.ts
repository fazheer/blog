export type HomeArticleCard = {
    id: bigint
    title: String
    coverUrl: String
    content: String
    createAt: Date
    categoryName: String
    commentNum: number
    isTop: Boolean
}


export type BaseArticleCard = {
    id: bigint
    title: String
    coverUrl: String
    createAt:String|null
}
export type BaseArticleYear = {
    id: bigint
    title: String
    coverUrl: String
    createAt:String
    year:String
}
export type Article = {
    id: bigint
    title: String
    coverUrl: String
    content: String
    createAt: Date|undefined
    views: bigint
    prevArticle: BaseArticleCard|null
    nextArticle: BaseArticleCard|null
}
export type Category = {
    id: bigint,
    name: String

}
export type Tag = {
    id: bigint,
    name: String
}
export type CategoryCard = {
    id: bigint,
    name: String,
    count:bigint
}
export type ArchiveCard = {
    title: String,
    count:bigint
}
export type Comment = {
    id: bigint
    content: String
    name: String
    email: String
    url: String
    createAt: Date
    love: bigint
    device: String
    address: String
    ops: String
    pageId:bigint
}
export type Reply = {
    id: bigint
    content: String
    name: String
    email: String
    url: String
    createAt: String
    love: bigint
    device: String
    address: String
    ops: String
    commentId: bigint
    toId:bigint|null
}

export type SiteInfo = {
    avatarUrl:String
    name:String
    personalProfile:String
    articleCount:bigint
    tagCount:bigint
    categoryCount:bigint
    projectUrl:String
    email:String
    notice:String
    siteView:bigint
    buildTime:String|undefined
    lastTime:String|undefined
}
export type CommentReply = {
    comment: Comment
    replies: Array<Reply>
}
export type CommentVO = {
    pageId: bigint
    name: String|null|undefined
    email: String
    url: String
    content: String
}
export type ReplyVO = {
    name: String|null|undefined
    email: String
    url: String
    content: String
    commentId:bigint
    toId?: bigint|null
}
