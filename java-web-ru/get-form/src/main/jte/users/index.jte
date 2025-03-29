@import exercise.dto.users.UsersPage
@param UsersPage term
@template.layout.page(content = @`
    <form action="/users" method="get">
        <input type="search" name="term" value="${term.getTerm()}" />
        <input type="submit" value="Search" />
    </form>

    <ul>
    @for (var user : term.getUsers())
        <li>
            ${user.getFirstName()} ${user.getLastName()}
        </li>
    @endfor
    </ul>
`)