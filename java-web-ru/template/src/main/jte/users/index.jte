@import exercise.dto.users.UsersPage
@param UsersPage usersPage

<table>
    <tbody>
    @for(var user : usersPage.users())
        <tr>
            <td>${user.getId()}</td>
            <td>${user.getFirstName()}</td>
            <td>${user.getLastName()}</td>
            <td>${user.getEmail()}</td>
        </tr>
    @endfor
    </tbody>
</table>
