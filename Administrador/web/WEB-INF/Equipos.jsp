<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>Notificaciones Mundial - Equipos</title>
  </head>
  <body>
  
  
  <table>
  <tr><td width="40%" valign="top">
  <h3>Agregar Equipos</h3>
  
  <form action="Equipos" method="POST">
    <table>
        <tr>
            <td>Nombre :</td>
            <td>
                <input type="hidden" name="opcion" value="Agregar"/>
                <input type="text" name="nombre" value="${requestScope.Equipo.nombre}"/>
            </td>
        </tr>
        <tr>
            <td>Nombre Corto:</td>
            <td>
                <input type="text" name="nombrecorto" value="${requestScope.Equipo.nombrecorto}"/>
            </td>
        </tr>
        <tr><td>Grupo : </td>
            <td>
                <select name="grupo">
                    <option value="A">Grupo A</option>
                    <option value="B">Grupo B</option>
                    <option value="C">Grupo C</option>
                    <option value="D">Grupo D</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>Posicion :</td>
            <td><input type="text" name="posicion" value="${requestScope.Equipo.posicion}"/></td>
        </tr>
        <tr>
            <td></td>
            <td align="right">
                <input type="reset" value="Cancelar"/>
                <input type="submit" value="Agregar" name="Aceptar"/>
            </td>
        </tr>
    </table>
  </form>
    
    
    <c:if test="${not empty requestScope.Status}">
        <c:choose>
            <c:when test="${requestScope.Status}">
                Operación Exitosa
            </c:when>
            <c:otherwise>
                Operación Fallida
            </c:otherwise>
        </c:choose>
    </c:if>
    </td>
    
    
    <td width="60%" align="left" valign="top">
      <h3>Equipos Existentes</h3>

    <c:forEach var="equipospGrupo" items="${requestScope.EquipospGrupo}" varStatus="i">
        <c:choose>
            <c:when test="${not empty equipospGrupo}">
                <h3>Grupo <c:out value="${requestScope.Grupos[i.count-1]}"/></h3>
                <table border="1">
                    <thead style="background:gray"><tr><td>Nombre</td><td>Nombre Corto</td><td>Posicion</td><td>Puntos</td><td></td></tr></thead>
                    <c:forEach var="equipo" items="${equipospGrupo}">
                        <tr>
                            <td width="200px"><c:out value="${equipo.nombre}"/></td>
                            <td width="200px"><c:out value="${equipo.nombrecorto}"/></td>
                            <td width="100px"><c:out value="${equipo.posicion}"/></td>
                            <td width="100px"><c:out value="${equipo.puntos}"/></td>
                            <td width="100px">
                                <form action="Equipos" method="POST">
                                    <input type="hidden" name="nombre" value="${equipo.nombre}"/>
                                    <input type="hidden" name="opcion" value="Eliminar"/>
                                    <input type="button" value="Eliminar" onclick="var result=confirm('¿Esta Seguro que Desea Eliminar el Equipo?');if(result==true){form.submit();}else{form.reset();}"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <h3>No Existen Equipos en el Grupo <c:out value="${requestScope.Grupos[i.count-1]}"/></h3>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    
    </td></tr>
    </table>

  </body>
</html>