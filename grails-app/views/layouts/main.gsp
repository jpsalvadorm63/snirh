<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title><g:layoutTitle default="SNIRH"/></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
  <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
  <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
  <style type="text/css">
    body { background: url("${resource(dir: 'images', file: 'box-bg.png')}") repeat; min-height:1080px;min-width:960px; }
    div.main_container {width:100%;margin-left:0;}
    div.main_top { clear: both; background: url("${resource(dir: 'images', file: 'main-top.png')}") no-repeat top left ; border-width:0;height:96px;}
    div#menu {top:2px;width:100%;background:transparent url("${resource(dir: 'menu/images', file: 'header_bg.gif')}") repeat-x 0 0;}
    div.crud {margin-left:32px;margin-top:32px;padding:16px 32px 32px 32px;background-color:#f5f5f5;-moz-border-radius: 8px;border-radius: 8px; border:1px solid silver;}
  </style>
  <jq:resources/>
  <script type="text/javascript" src="${resource(dir: 'menu', file: 'menu.js')}"></script>
  <link rel="stylesheet" href="${resource(dir: 'menu', file: 'menu.css')}">
  <jqui:resources/>
  <g:layoutHead/>

	<r:layoutResources />
  <link rel="stylesheet" href="${resource(dir: 'css', file: 'button.css')}" type="text/css">
  <style>

    div.main_top div.app_top {
      height : 88px;
      text-align : right;
      margin : 4px 16px 0 0;
      position : absolute;
      width : 99%;
      clear : both;
      background : transparent url(${resource(dir: 'images', file: 'empresas.png')}) no-repeat right 24px;
    }

    div.app_top span {
      font-weight : bolder;
    }

    div.app_top a {
      color : #444444;
      text-decoration:none;
      border-bottom:1px #ffff00 dotted;
      font-weight : bolder;
      margin:0 4px 0 4px;
    }

  </style>

</head>
<body>
<div class="main_container">
  <div class="main_top" >
    <div class="app_top" >

      Aplicacion: <span><g:meta name="app.name"/> <g:meta name="app.version"/></span> |

      <sec:ifLoggedIn>
        Usuario: <span><sec:username/></span> |
        <g:link controller="logout">Terminar Sesión</g:link>
      </sec:ifLoggedIn>

      <sec:access expression="!hasRole('ROLE_ADMIN')">
        | <g:link controller="login" action="changePass2">Cambiar de Clave</g:link>
      </sec:access>

      <sec:ifNotLoggedIn>
        <g:link controller='login' action='auth'>Ingresar al Sistema</g:link>
      </sec:ifNotLoggedIn>

    </div>
  </div>
  <g:layoutBody/>
  <g:javascript library="application"/>
	<r:layoutResources />
</div>
</body>
</html>
