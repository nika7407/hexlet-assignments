@import exercise.dto.users.UsersPage
@param UsersPage users

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>IDK</title>
</head>
<body>
<div class="col-lg-8 mx-auto p-4 py-md-5">
    <main>
<ul>
    @for (var user : users.getUsers())
      <li>
        <a href="/users/${user.getId()}">${user.getFirstName()}</a>
      </li>
    @endfor
</ul>
    </main>
</div>
</body>
</html>