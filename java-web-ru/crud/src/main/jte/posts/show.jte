@import exercise.dto.posts.PostPage
@param PostPage page

@template.layout.page(
content = @`
    <main>
        <div class="mx-auto p-4 py-md-5">
            <h1>${page.post().getName()}</h1>
        </div>

        <div class="mx-auto p-4 py-md-5">
            ${page.post().getBody()}
        </div>
    </main>
`
)
