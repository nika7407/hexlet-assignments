@import exercise.dto.posts.EditPostPage
@import exercise.util.NamedRoutes
@param EditPostPage page

@template.layout.page(content = @`
    @if(page.getErrors() != null)
        <div class="mb-3">
            <ul>
                @for(var validator : page.getErrors().values())
                    @for(var error : validator)
                        <li>${error.getMessage()}</li>
                    @endfor
                @endfor
            </ul>
        </div>
    @endif
    <form action="${NamedRoutes.postPath(page.getId())}" method="post">
    <div>
        <label>
            Название
            <input type="text" name="name" value="${page.getName()}">
        </label>
    </div>
    <div>
        <label>
            Содержимое
            <textarea name="body">${page.getBody()}</textarea>
        </label>

    </div>
    <input type="submit" value="Обновить">
</form>`)