@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page(
page = page,
content = @`
    @for(var post : page.posts())
        <div class="mb-3">
            <h1>${post.getName()}</h1>
        </div>

        <div class="mb-3">
            ${post.getBody()}
        </div>
    @endfor
`,
)
