<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main"/>
	<title>SNIRH - Principal</title>
</head>
<body>
  <div id="menu">
    <ul class="menu">
      <sec:ifAllGranted roles="ROLE_ADMIN">
        <li><a href="#" class="parent"><span style="color:white;">Administrador</span></a>
          <div><ul>
            <li><g:link controller="usuario" action="list"><span>Usuarios</span></g:link></li>
          </ul></div>
        </li>
      </sec:ifAllGranted>
      <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_DATALOADER">
        <li><a href="#" class="parent"><span style="color:white;">Puntos de Observación</span></a>
        <div><ul>
          <li><g:link controller="pohm" action="list"><span>Puntos de Observación</span></g:link></li>
          <li><a href="#" class="parent"><span style="color:white;">Códigos</span></a>
            <div><ul>
              <li><g:link controller="dominio" action="list"><span>Dominio</span></g:link></li>
              <li><g:link controller="tipoEstacion" action="list"><span>Tipos de Estación</span></g:link></li>
              <li><g:link controller="estadoEstacion" action="list"><span>Estados de Estación</span></g:link></li>
              <li><g:link controller="institucion" action="list"><span>Instituciones</span></g:link></li>
              <li><g:link controller="operativo" action="list"><span>Operatividad</span></g:link></li>
            </ul></div>
          </li>
        </ul></div>
      </li>
        <li><a href="#" class="parent"><span>Series de Datos</span></a>
        <div><ul>
          <li><g:link controller="upldFile" action="list"><span>Carga de Datos</span></g:link></li>
          <li><a href="#" class="parent"><span style="color:white;">Códigos</span></a>
            <div><ul>
              <li><g:link controller="tipoRelleno" action="list"><span>Tipos de relleno</span></g:link></li>
              <li><g:link controller="frecuencia" action="list"><span>Frecuencias</span></g:link></li>
              <li><g:link controller="estadistica" action="list"><span>Estadísticas</span></g:link></li>
              <li><g:link controller="parametro" action="list"><span>Parámetros</span></g:link></li>
            </ul></div>
          </li>
        </ul></div>
      </li>
      </sec:ifAnyGranted>
      <li><g:link controller="geoserver" action="showInfo" id="Demarcaciones"><span>Datamart</span></g:link></li>
    </ul>
  </div>
</body>
</html>
