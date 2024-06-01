@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page

@template.layout.page(
    content = @`
    !{var errors = page.errors();}

    @if(errors != null)
        <ul>
            @for(var validator : errors.values())
                @for(var error : validator)
                    <li>${error.getMessage()}</li>
                @endfor
            @endfor
        </ul>
    @endif

    <form action="/articles" method="post">
        <div>
            <label>
                Название
                <input type="text" required name="title" value="${page.title()}" />
            </label>
        </div>
        <div>
            <label>
                Содержимое
                <textarea name="content">${page.content()}</textarea>
            </label>
        </div>

        <input type="submit" value="Создать" />
    </form>
`
)
