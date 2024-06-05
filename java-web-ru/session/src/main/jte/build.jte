@import exercise.dto.LoginPage
@import exercise.util.NamedRoutes
@param LoginPage page

@template.layout.page(
content = @`
    @if(page.getError() != null)
        <p>${page.getError()}</p>
    @endif

    <form action="${NamedRoutes.loginPath()}" method="post">
        <label>
            Имя
            <input name="name" type="text" value="${page.getName()}"/>
        </label>
        <label>
            Пароль
            <input name="password" type="password" value=""/>
        </label>
        <label>
            Войти
            <input type="submit"/>
        </label>
    </form>
`
)
