@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
    content = @`
    @for(var user : page.getUsers())
        <div>
            <div>${user.getFirstName()}</div>
            <div>${user.getLastName()}</div>
            <div>${user.getEmail()}</div>
        </div>
    @endfor
`
)