@import exercise.dto.users.UserPage
@param UserPage user

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
            <li> ${user.getUser().getId()}</li>
            <li> ${user.getUser().getFirstName()}</li>
            <li> ${user.getUser().getLastName()}</li>
            <li> ${user.getUser().getEmail()}</li>
        </ul>
    </main>
</div>
</body>
</html>