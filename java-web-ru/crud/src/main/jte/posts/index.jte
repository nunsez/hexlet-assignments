@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(
content = @`
    @for(var post : page.posts())
        <div>
            <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>
            <div>${post.getBody()}</div>
        </div>
    @endfor
    <a href="${NamedRoutes.postsPath() + "?page=" + (page.pageNumber() - 1)}"></a>
    <a href="${NamedRoutes.postsPath() + "?page=" + (page.pageNumber() + 1)}"></a>
`
)
