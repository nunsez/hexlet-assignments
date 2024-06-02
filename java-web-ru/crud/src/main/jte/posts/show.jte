@import exercise.dto.posts.PostPage
@param PostPage page

@template.layout.page(
content = @`
    <div>
        <div>${page.post().getName()}</div>
        <div>${page.post().getBody()}</div>
    </div>
`
)
