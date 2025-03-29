@import exercise.dto.users.UsersPage
@param UsersPage term
@template.layout.page(content = @`
    <form action="/users" method="get">
        <input type="search" name="term" value="${term.getTerm()}" />
        <input type="submit" value="Search" />
    </form>


    @for (var user : term.getUsers())
       <ul> <li>
            ${user.getFirstName()} ${user.getLastName()}
               id = ${user.getId()}
               email = ${user.getEmail()}
        </li> </ul>
    @endfor
`)