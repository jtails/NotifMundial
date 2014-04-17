<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="../WEB-INF/jspf/_head.jspf" %>
        <script>
        function AgregarGEquipo(elementE,elementM){
            var equipo=elementE;
            var result=prompt('Esta Seguro que Desea Anotar un Gol para el Equipo '+equipo+'?\n Ingrese el Minuto del Gol');
            if(result!='' && result!=null){
                elementM.value=result;
                result=confirm('Anotara un Gol en el Minuto '+ result +' para el Equipo '+equipo);
            }
            return result;
        }
        
        function AnularGEquipo(elementE,elementM){
            var equipo=elementE;
            var result=prompt('Esta Seguro que Desea Anular un Gol al Equipo '+equipo+'?\n Ingrese el Minuto para Anular el Gol');
            if(result!='' && result!=null){
                elementM.value=result;
                result=confirm('Anulara un Gol en el Minuto '+ result +' para el Equipo '+equipo);
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

                        <article class="col-sm-12 col-md-12 col-lg-8">

                            <!-- new widget -->
                            <div class="jarviswidget" id="wid-id-partidos-existentes" data-widget-colorbutton="false" data-widget-editbutton="false"
                                data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="false"
                                data-widget-sortable="false">

                                <header>
                                    <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                                    <h2>Partidos Existentes</h2>
                                </header>

                                <!-- widget div-->
                                <div>
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
                                    
                                    <div class="widget-body padding">
                                        <!-- content goes here -->
                                        <div class="row">
                                            <div class="col-sm-12 col-md-12 col-lg-12">
                                                <table class="table table-bordered table-striped table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th width="150px">Equipo A</th>
                                                            <th width="150px">Equipo B</th>
                                                            <th width="300px" class="hidden-xs">Fecha Hora</th>
                                                            <th width="50px" class="hidden-xs">Ronda</th>
                                                            <th width="100px">Resultado</th>
                                                        </tr>
                                                    </thead>
                                                    <c:forEach var="partido" items="${requestScope.Partidos}">
                                                        <tr>
                                                            <td><c:out value="${partido.idlocalteam.displayname}"/></td>
                                                            <td><c:out value="${partido.idvisitteam.displayname}"/></td>
                                                            <td class="hidden-xs"><c:out value="${partido.datetime}"/></td>
                                                            <td class="hidden-xs"><c:out value="${partido.ronda}"/></td>
                                                            <td>
                                                                <form action="IniciarPartido" method="POST">
                                                                    <input type="hidden" name="Idlocalteam" value="${partido.idlocalteam.idequipo}"/>
                                                                    <input type="hidden" name="Idvisitteam" value="${partido.idvisitteam.idequipo}"/>
                                                                    <input type="hidden" name="FechaHora" value="${partido.datetime}"/>
                                                                    <input type="hidden" name="Ronda" value="${partido.ronda}"/>
                                                                    <input type="hidden" name="Id" value="${partido.idpartido}"/>
                                                                    <input type="hidden" value="Iniciar" name="opcion"/>
                                                                    <c:choose>
                                                                        <c:when test="${partido.details.status == 1}">
                                                                            <input type="button" value="Iniciar Partido" class="btn btn-primary" onclick="var result=confirm('¿Esta Seguro que Desea Iniciar el Partido, esta Opción solo se puede Seleccionar Una Vez?');if(result==true){form.submit();}else{form.reset();}"/>
                                                                        </c:when>
                                                                        <c:when test="${partido.details.status == 3}">
                                                                            <center>
                                                                                <h1>
                                                                                    <c:out value="${partido.details.goleslocalteam}"/> - <c:out value="${partido.details.golesvisitteam}"/>
                                                                                </h1>
                                                                            </center>
                                                                        </c:when>
                                                                    </c:choose>
                                                                </form>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
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
                        
                        <article class="col-sm-12 col-md-12 col-lg-4">

                            <!-- new widget -->
                            <div class="jarviswidget" id="wid-id-partidos-juego" data-widget-colorbutton="false" data-widget-editbutton="false"
                                data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="false"
                                data-widget-sortable="false">

                                <header>
                                    <span class="widget-icon"> <i class="fa fa-play-circle"></i> </span>
                                    <h2>En Juego</h2>
                                </header>

                                <!-- widget div-->
                                <div>
                                    <div class="widget-body padding">
                                        <!-- content goes here -->
                                        <div class="row">
                                            <div class="col-sm-12 col-md-12 col-lg-12">
                                                <c:forEach var="partido" items="${requestScope.Partidos}">
                                                    <c:if test="${partido.details.status == 2}">
                                                        <div class="well">
                                                            <table style="width: 250px;">
                                                                <tr>
                                                                    <td align="center" style="padding: 5px;">
                                                                        <h2><c:out value="${partido.idlocalteam.displayname}"/></h2>
                                                                        <table>
                                                                            <tr>
                                                                                <td style="font-size:60px; text-align: center;" colspan="2">
                                                                                   <c:out value="${partido.details.goleslocalteam}"/>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    <form action="IniciarPartido" method="POST">
                                                                                        <input type="hidden" name="Idlocalteam" value="${partido.idlocalteam.idequipo}" id="${partido.idpartido}equipoa"/>
                                                                                        <input type="hidden" name="Idvisitteam" value="${partido.idvisitteam.idequipo}"/>
                                                                                        <input type="hidden" name="FechaHora" value="${partido.datetime}"/>
                                                                                        <input type="hidden" name="Ronda" value="${partido.ronda}"/>
                                                                                        <input type="hidden" name="Minuto" value="" id="${partido.idpartido}a"/>
                                                                                        <input type="hidden" name="Id" value="${partido.idpartido}"/>
                                                                                        <input type="hidden" value="AgregarGEquipoA" name="opcion"/>
                                                                                        <button type="button" class="btn btn-primary btn-lg" onclick="var result=AgregarGEquipo(document.getElementById('${partido.idpartido}equipoa').value,document.getElementById('${partido.idpartido}a'));if(result==true){form.submit();}else{form.reset();}">
                                                                                            <i class="fa fa-plus"></i>
                                                                                        </button>
                                                                                        <!--<a href="#" class="btn btn-primary"> <i class="fa fa-plus"></i></a>-->
                                                                                    </form>
                                                                                </td>
                                                                                <td>
                                                                                    <form action="IniciarPartido" method="POST">
                                                                                        <input type="hidden" name="Idlocalteam" value="${partido.idlocalteam.idequipo}" id="${partido.idpartido}equipoaA"/>
                                                                                        <input type="hidden" name="Idvisitteam" value="${partido.idvisitteam.idequipo}"/>
                                                                                        <input type="hidden" name="FechaHora" value="${partido.datetime}"/>
                                                                                        <input type="hidden" name="Ronda" value="${partido.ronda}"/>
                                                                                        <input type="hidden" name="Minuto" value="" id="${partido.idpartido}b"/>
                                                                                        <input type="hidden" name="Id" value="${partido.idpartido}"/>
                                                                                        <input type="hidden" value="EliminarGEquipoA" name="opcion"/>
                                                                                        <button type="button" class="btn btn-lg btn-danger" onclick="var result=AnularGEquipo(document.getElementById('${partido.idpartido}equipoaA').value,document.getElementById('${partido.idpartido}b'));if(result==true){form.submit();}else{form.reset();}">
                                                                                            <i class="fa fa-minus"></i>
                                                                                        </button>
                                                                                    </form>
                                                                                </td>
                                                                            </tr>
                                                                        </table>
                                                                    </td>
                                                                    <td align="center" width="20px"></td>
                                                                    <td align="center">
                                                                        <h2><c:out value="${partido.idvisitteam.displayname}"/></h2>
                                                                        <table>
                                                                            <tr>
                                                                                <td style="font-size:60px" align="center" colspan="2">
                                                                                    <c:out value="${partido.details.golesvisitteam}"/>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>
                                                                                    <form action="IniciarPartido" method="POST">
                                                                                        <input type="hidden" name="Idlocalteam" value="${partido.idlocalteam.idequipo}"/>
                                                                                        <input type="hidden" name="Idvisitteam" value="${partido.idvisitteam.idequipo}" id="${partido.idpartido}equipob"/>
                                                                                        <input type="hidden" name="FechaHora" value="${partido.datetime}"/>
                                                                                        <input type="hidden" name="Ronda" value="${partido.ronda}"/>
                                                                                        <input type="hidden" name="Minuto" value="" id="${partido.idpartido}c"/>
                                                                                        <input type="hidden" name="Id" value="${partido.idpartido}"/>
                                                                                        <input type="hidden" value="AgregarGEquipoB" name="opcion"/>
                                                                                        <button type="button" class="btn btn-primary btn-lg" onclick="var result=AgregarGEquipo(document.getElementById('${partido.idpartido}equipob').value,document.getElementById('${partido.idpartido}c'));if(result==true){form.submit();}else{form.reset();}">
                                                                                            <i class="fa fa-plus"></i>
                                                                                        </button>
                                                                                    </form>
                                                                                </td>
                                                                                <td>
                                                                                    <form action="IniciarPartido" method="POST">
                                                                                        <input type="hidden" name="Idlocalteam" value="${partido.idlocalteam.idequipo}"/>
                                                                                        <input type="hidden" name="Idvisitteam" value="${partido.idvisitteam.idequipo}" id="${partido.idpartido}equipobA"/>
                                                                                        <input type="hidden" name="FechaHora" value="${partido.datetime}"/>
                                                                                        <input type="hidden" name="Ronda" value="${partido.ronda}"/>
                                                                                        <input type="hidden" name="Minuto" value="" id="${partido.idpartido}d"/>
                                                                                        <input type="hidden" name="Id" value="${partido.idpartido}"/>
                                                                                        <input type="hidden" value="EliminarGEquipoB" name="opcion"/>
                                                                                        <button type="button" class="btn btn-lg btn-danger" onclick="var result=AnularGEquipo(document.getElementById('${partido.idpartido}equipobA').value,document.getElementById('${partido.idpartido}d'));if(result==true){form.submit();}else{form.reset();}">
                                                                                            <i class="fa fa-minus"></i>
                                                                                        </button>
                                                                                    </form>
                                                                                </td>
                                                                            </tr>
                                                                        </table>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td width="100%" colspan="3" align="center">
                                                                        <BR/>
                                                                        <form action="IniciarPartido" method="POST">
                                                                            <input type="hidden" name="Idlocalteam" value="${partido.idlocalteam.idequipo}"/>
                                                                            <input type="hidden" name="Idvisitteam" value="${partido.idvisitteam.idequipo}"/>
                                                                            <input type="hidden" name="FechaHora" value="${partido.datetime}"/>
                                                                            <input type="hidden" name="Ronda" value="${partido.ronda}"/>
                                                                            <input type="hidden" name="Minuto" value=""/>
                                                                            <input type="hidden" name="Id" value="${partido.idpartido}"/>
                                                                            <input type="hidden" name="opcion" value="TerminarP"/>
                                                                            <input type="button" class="btn btn-success btn-lg btn-block" value="Terminar Partido" onclick="var result=confirm('¿Esta Seguro que Desea Terminar el Partido?');if(result==true){form.submit();}else{form.reset();}"/>
                                                                        </form>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </div>
                                                        <div class="padding-bottom-20"></div>
                                                    </c:if>
                                                </c:forEach>
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