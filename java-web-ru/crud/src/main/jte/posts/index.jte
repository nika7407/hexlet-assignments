@import exercise.dto.posts.PostsPage
@import exercise.model.Post
@param PostsPage pages


<%--Выведите список добавленных постов. --%>
<%--Каждый пост — это имя, представленное ссылкой, которая ведет на отображение.--%>

    @template.layout.page(content = @`
     <ul>
         @for(Post page: pages.getList())
             <li>
                 <a href="/posts/${page.getId()}">${page.getName()}</a>
             </li>
         @endfor
     </ul>
        <div></div>
        <div>
            <a href="/posts?page=${(pages.getPage() + 1) }">вперед</a>
            <a href="/posts?page=${(pages.getPage() - 1) }">назад</a>
        </div>
    `)