@import exercise.dto.LoginPage
@param LoginPage page


@template.layout.page(content = @`

    @if(page.getError() != null)
        <h2>
            ${page.getError()}
        </h2>
    @endif

<form action="/sessions" method="post">
    <input type="text" name="name" value="${page.getName()}">
    <input type="text" name="password" >
    <input type="submit" value="Submit">
</form>
`)