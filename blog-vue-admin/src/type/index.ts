export type RouterTag = {
    name:String
    path:String
}
export type BreadcrumbItem = {
    name:String
}
export type User={
    username:String
    password:String
}
export type ArticleDTO = {
    id: bigint
    coverUrl: String
    title: String
    content:String|null|undefined
    categoryName: String
    tags:Array<String>|undefined
    views:bigint
    createAt: Date|undefined
    isTop: boolean
}
export type ArticleVO  ={
    id:bigint|null
    title:String
    coverUrl:String|null
    content:String|null
    createAt:String
    categoryName:String|null
    tags:Array<String>|null
    status:number
    isTop:Boolean
}
export type CategoryCount = {
    id:bigint
    name:String
    count:bigint
}
export type Tag = {
    id:bigint
    name:String
    count:bigint
}
export type Comment = {
    id:bigint
    avatarUrl:String
    name:String
    email:String
    url:String
    content:String
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
    name:String
}
export type Setting = {
     avatarUrl:String
     adminAvatar:String
     jiaokerAvatar:String
     projectUrl:String
     email:String
     siteName:String
     typingWord:String
     name:String
     personalProfile:String
     buildTime:String
     notice:String
     icp:String
     gov:String
}