@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(content = @`
    @for (var user : page.getUsers())
        <li>
            <a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a>
        </li>
    @endfor
`)