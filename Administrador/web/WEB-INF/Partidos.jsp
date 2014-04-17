<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="../WEB-INF/jspf/_head.jspf" %>
        <script>
        function edit(fecha,grupoa,grupob,aceptar,place){
            var result=confirm('¿Esta Seguro que Desea Editar el Partido?');
            if(result==true){
                fecha.removeAttribute('disabled');
                place.removeAttribute('disabled');
                grupoa.removeAttribute('hidden');
                grupob.removeAttribute('hidden');
                $(aceptar).removeClass('hidden');
            }
            return result;
        }
        </script>
    </head>
    <body class="">
        <!-- possible classes: minified, fixed-ribbon, fixed-header, fixed-width-->

        <%@include file="../WEB-INF/jspf/_header.jspf" %>
        <%@include file="../WEB-INF/jspf/_menu.jspf" %>

        <!-- MAIN PANEL -->
        <div id="main" role="main">

            <!-- RIBBON -->
            <div id="ribbon">

                <!-- breadcrumb -->
                <ol class="breadcrumb">
                    <li>Partidos</li>
                </ol>
                <!-- end breadcrumb -->

            </div>
            <!-- END RIBBON -->

            <!-- MAIN CONTENT -->
            <div id="content">

                <!-- widget grid -->
                <section id="widget-grid" class="">

                    <!-- row -->

                    <div class="row">

                        <article class="col-sm-12 col-md-12 col-lg-12">

                            <!-- new widget -->
                            <div class="jarviswidget" id="wid-id-partidos" 
                                 data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-sortable="false" 
                                 data-widget-colorbutton="false" data-widget-deletebutton="false" 
                                 data-widget-fullscreenbutton="false" data-widget-collapsed="false">


                                <header>
                                    <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                                    <h2>Partidos Existentes</h2>
                                </header>
                                    
                                            <div class="col-sm-12 col-md-12 col-lg-8">
                                               <c:if test="${not empty requestScope.Status}">
                                                    <c:choose>
                                                        <c:when test="${requestScope.Status}">
                                                            <div class="alert alert-success">
                                                                <i class="fa fa-check"></i> Operación Exitosa
                                                            </div>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <div class="alert alert-danger">
                                                                <i class="fa fa-times"></i>Operación Fallida
                                                            </div>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:if>

                                                <table class="table table table-striped table-hover table-bordered">
                                                        <thead style="background:gray"><tr><th width="100px">EquipoA</th><th width="100px">EquipoB</th><th width="100px">Fecha Hora</th><th width="100px">Lugar</th><th width="50px">Ronda</th><th width="60px">Editar</th></tr></thead>
                                                        <c:forEach var="partido" items="${requestScope.Partidos}">
                                                            <tr>
                                                                <form action="Partidos" method="POST">
                                                                <td>
                                                                    <c:out value="${partido.idlocalteam.displayname}"/>
                                                                    <select name="Idlocalteam" id="${partido.idlocalteam.idequipo}${partido.idpartido}" hidden>
                                                                        <c:forEach var="equipo" items="${requestScope.Equipos}">
                                                                            <option value="${equipo.idequipo}"><c:out value="${equipo.displayname}"/></option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </td>
                                                                <td>
                                                                    <c:out value="${partido.idvisitteam.displayname}"/>
                                                                    <select name="Idvisitteam" id="${partido.idvisitteam.idequipo}${partido.idpartido}" hidden>
                                                                        <c:forEach var="equipo" items="${requestScope.Equipos}">
                                                                            <option value="${equipo.idequipo}"><c:out value="${equipo.displayname}"/></option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </td>
                                                                <td><input type="text" name="FechaHora" value="${partido.datetime}" id="${partido.idlocalteam.idequipo}${partido.idpartido}h" disabled/></td>
                                                                <td><input type="text" name="Place" value="${partido.place}" id="${partido.idlocalteam.idequipo}${partido.idpartido}p" disabled/></td>
                                                                <td><c:out value="${partido.ronda}"/></td>
                                                                <td style="width:120px;">
                                                                        <input type="hidden" name="Ronda" value="${partido.ronda}"/>
                                                                        <input type="hidden" name="id" value="${partido.idpartido}"/>
                                                                        <input type="hidden" value="Editar" name="opcion"/>
                                                                        <c:if test="${partido.details.status == 1 && partido.ronda>1}">
                                                                            <input type="button" class="btn btn-primary btn-sm" value="Editar" onclick="edit(document.getElementById('${partido.idlocalteam.idequipo}${partido.idpartido}h'),document.getElementById('${partido.idlocalteam.idequipo}${partido.idpartido}'),document.getElementById('${partido.idvisitteam.idequipo}${partido.idpartido}'),document.getElementById('${partido.idlocalteam.idequipo}${partido.idpartido}a'),document.getElementById('${partido.idlocalteam.idequipo}${partido.idpartido}p'));"/>
                                                                            <input type="submit" class="btn btn-primary btn-sm hidden" value="Guardar" id="${partido.idlocalteam.idequipo}${partido.idpartido}a"/>
                                                                        </c:if>   
                                                                </td>
                                                                </form>
                                                            </tr>
                                                        </c:forEach>
                                                    </table>

                                                </td></tr>
                                                </table>
                                            </div>

                                        </div>
                                        <!-- end content -->
                                    </div>

                                </div>
                                <!-- end widget div -->
                            </div>
                            <!-- end widget -->

                        </article>

                    </div>

                    <!-- end row -->

                </section>
                <!-- end widget grid -->

            </div>
            <!-- END MAIN CONTENT -->

        </div>
        <!-- END MAIN PANEL -->

        <!--================================================== -->
        <%@include file="../WEB-INF/jspf/_scripts.jspf" %>
    </body>
</html>