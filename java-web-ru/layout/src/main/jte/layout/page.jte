@import gg.jte.Content
@param Content content

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hexlet Javalin Example</title>
</head>
<body>
    <header>
        <p>
            <a href="/">Root</a>
            <a href="/users">Users</a>
        </p>
    </header>
    ${content}
    <footer>footer</footer>
</body>
</html>
