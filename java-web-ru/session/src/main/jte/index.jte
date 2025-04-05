@import  exercise.dto.MainPage
@import exercise.util.NamedRoutes
@import io.javalin.http.Context;
@param  MainPage page
@template.layout.page(content = @`
    @if (page.getCtx().cookie("currentUser") != null && page.getCtx().cookie("currentUser").equals(page.getName()))
       <h3>${page.getCtx().cookie("currentUser")}</h3>
        <form action="${NamedRoutes.logoutPath()}" method="post">
            <button type="submit">Выйти</button>
        </form>
    @else
        <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
    @endif
`)