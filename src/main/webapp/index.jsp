<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Calculator</title>
</head>
<body>
<h1 style="text-align: center">Calculator</h1>
<form method="post" action="calc">
    <label for="first">First number</label>
    <input type="text" name="first" id="first"/>
    <br/>
    <label for="second">Second number</label>
    <input type="text" name="second" id="second"/>
    <br/>
    <div>
        <label for="add">addition</label>
        <input type="radio" name="op" value="add" id="add" checked="checked"/>
        <br/>

        <label for="sub">subtraction</label>
        <input type="radio" name="op" value="sub" id="sub"/>
        <br/>

        <label for="mul">multiplication</label>
        <input type="radio" name="op" value="mul" id="mul"/>
        <br/>

        <label for="div">division</label>
        <input type="radio" name="op" value="div" id="div"/>
        <br/>

        <input type="hidden" name="my_csrf_token" value="${my_csrf_token}" />
    </div>
    <input type="submit"/>
</form>
</body>
</html>
