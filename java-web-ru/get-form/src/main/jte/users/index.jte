@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
    content = @`
        <form action="/users" method="get">
            <input type="search" name="term" value="${page.firstName()}" />
            <input type="submit" value="Search" />
        </form>
        @if(!page.users().isEmpty())
            <table>
                <tbody>
                @for(var user : page.users())
                    <tr>
                        <td>${user.getFirstName()}</td>
                        <td>${user.getLastName()}</td>
                        <td>${user.getEmail()}</td>
                    </tr>
                @endfor
                </tbody>
            </table>
        @endif
`
)
