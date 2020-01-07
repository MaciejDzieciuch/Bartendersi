<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <title>Bartendersi</title>
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <meta name="description" content="Bartenders Giuds">
  <meta name="author" content="Bartendersi">
  <meta name="keywords" content="Bartenders, drinks, giuds">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="/css/bootstrap.css">
  <link rel="stylesheet" href="/css/style.css">

  <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.10/css/select2.min.css"
        rel="stylesheet"/>

  <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.10/css/select2.min.css"
        rel="stylesheet"/>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <link rel="stylesheet"
        href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

  <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
          integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
          crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
          integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
          crossorigin="anonymous"></script>

  <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
</head>
<body class="main_con">

<#if userType??>
    <#include "${userType}View.ftlh">
<#else>
    <#include "guestView.ftlh">
</#if>


<#include "content${function}.ftlh">


</body>
<#include "footer.ftlh">
</html>
