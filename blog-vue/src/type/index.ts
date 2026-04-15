export type HomeArticleCard = {
    id: bigint
    title: string
    coverUrl: string
    content: string
    createAt: Date
    categoryName: string
    commentNum: number
    isTop: boolean
}


export type BaseArticleCard = {
    id: bigint
    title: string
    coverUrl: string
    createAt:string|null
}
export type BaseArticleYear = {
    id: bigint
    title: string
    coverUrl: string
    createAt:string
    year:string
}
export type Article = {
    id: bigint
    title: string
    coverUrl: string
    content: string
    createAt: Date|undefined
    views: bigint
    prevArticle: BaseArticleCard|null
    nextArticle: BaseArticleCard|null
}
export type Category = {
    id: bigint,
    name: string

}
export type Tag = {
    id: bigint,
    name: string
}
export type CategoryCard = {
    id: bigint,
    name: string,
    count:bigint
}
export type ArchiveCard = {
    title: string,
    count:bigint
}
export type Comment = {
    id: bigint
    content: string
    name: string
    email: string
    url: string
    createAt: Date
    love: bigint
    device: string
    address: string
    ops: string
    pageId:bigint
}
export type Reply = {
    id: bigint
    content: string
    name: string
    email: string
    url: string
    createAt: string
    love: bigint
    device: string
    address: string
    ops: string
    commentId: bigint
    toId:bigint|null
}

export type SiteInfo = {
    avatarUrl:string
    name:string
    personalProfile:string
    articleCount:bigint
    tagCount:bigint
    categoryCount:bigint
    projectUrl:string
    email:string
    notice:string
    siteView:bigint
    buildTime:string|undefined
    lastTime:string|undefined
}
export type CommentReply = {
    comment: Comment
    replies: Array<Reply>
}
export type CommentVO = {
    pageId: bigint
    name: string|null|undefined
    email: string
    url: string
    content: string
}
export type ReplyVO = {
    name: string|null|undefined
    email: string
    url: string
    content: string
    commentId:bigint
    toId?: bigint|null
}
