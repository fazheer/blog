export type RouterTag = {
    name:string
    path:string
}
export type BreadcrumbItem = {
    name:string
}
export type User={
    username:string
    password:string
}
export type ArticleDTO = {
    id: bigint
    coverUrl: string
    title: string
    content:string|null|undefined
    categoryName: string
    tags:Array<string>|undefined
    views:bigint
    createAt: Date|undefined
    isTop: boolean
}
export type ArticleVO  ={
    id:bigint|null
    title:string
    coverUrl:string|null
    content:string|null
    createAt:string
    categoryName:string|null
    tags:Array<string>|null
    status:number
    isTop:boolean
}
export type CategoryCount = {
    id:bigint
    name:string
    count:bigint
}
export type Tag = {
    id:bigint
    name:string
    count:bigint
}
export type Comment = {
    id:bigint
    avatarUrl:string
    name:string
    email:string
    url:string
    content:string
    love:bigint
    createAt:Date
}
export type WeekView = {
    view:bigint,
    time:Date
}
export type ArticleViewRank = {
    views:bigint,
    title:Date
}
export type CommentCount = {
    month:bigint,
    total:bigint
}
export type SelectOption = {
    id:bigint
    name:string
}
export type Setting = {
     avatarUrl:string
     adminAvatar:string
     jiaokerAvatar:string
     projectUrl:string
     email:string
     siteName:string
     typingWord:string
     name:string
     personalProfile:string
     buildTime:string
     notice:string
     icp:string
     gov:string
}