@import exercise.dto.users.UserPage
@param UserPage userPage

<table>
    <tbody>
        <tr>
            <td>${userPage.user().getId()}</td>
            <td>${userPage.user().getFirstName()}</td>
            <td>${userPage.user().getLastName()}</td>
            <td>${userPage.user().getEmail()}</td>
        </tr>
    </tbody>
</table>
