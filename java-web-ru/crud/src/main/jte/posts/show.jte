@import exercise.model.Post
@import exercise.dto.posts.PostPage
@param PostPage page



@template.layout.page(content = @`
    <div>
        <h2>${page.getPost().getName()}</h2>
    </div>
    <div>
        <p>
            ${page.getPost().getBody()}
        </p>
    </div>
`)
