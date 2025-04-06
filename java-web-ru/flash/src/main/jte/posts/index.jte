@import exercise.dto.posts.PostsPage
@import exercise.model.Post
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(content = @`
    @if(page.getFlash() != null)
        <p>${page.getFlash()}</p>
    @endif

    <ul>
    @for(Post post : page.getList())
        <li>
            <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>
        </li>
    @endfor
</ul>
`)
