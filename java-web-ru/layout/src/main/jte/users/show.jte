@import exercise.dto.users.UserPage
@param UserPage page
@template.layout.page(content =@`
    <ul>
       <dov></dov>
        <li> ${page.getUser().getId()}</li>
        <li> ${page.getUser().getFirstName()}</li>
        <li> ${page.getUser().getLastName()}</li>
        <li> ${page.getUser().getEmail()}</li>
    </ul>
` )