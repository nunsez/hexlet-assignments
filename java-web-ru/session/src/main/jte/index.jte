@import exercise.dto.MainPage
@import exercise.util.NamedRoutes
@param MainPage page

@template.layout.page(
content = @`
    @if(page.getName() == null)
        <a href="${NamedRoutes.loginPath()}">Войти</a>
    @else
        <span>${String.valueOf(page.getName())}</span>
        <form action="${NamedRoutes.logoutPath()}" method="post">
            <label>
                Выйти
                <input type="submit">
            </label>
        </form>
    @endif
`
)
