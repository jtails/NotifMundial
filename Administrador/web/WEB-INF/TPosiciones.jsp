<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="../WEB-INF/jspf/_head.jspf" %>
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
                    <li>Tabla de Posiciones</li>
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
                            <div class="jarviswidget" id="wid-id-4" 
                                 data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-sortable="false" 
                                 data-widget-colorbutton="false" data-widget-deletebutton="false" 
                                 data-widget-fullscreenbutton="false" data-widget-collapsed="false">

                                <header>
                                    <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                                    <h2>Tabla de Posiciones</h2>
                                </header>

                                <!-- widget div-->
                                <div>
                                    <div class="widget-body padding">
                                        <!-- content goes here -->
                                        <table class="table table table-striped table-hover table-bordered">
                                            <thead style="background:gray"><tr><th width="200px">Nombre</th><th width="100px">PJ</th><th width="100px">PG</th><th width="100px">PE</th><th width="100px">PP</th><th width="100px">GF</th><th width="100px">GC</th><th width="100px">PTS</th><th width="100px">DIF</th></tr></thead>
                                            <c:forEach var="estadisticas" items="${requestScope.Estadisticas}" varStatus="i">
                                                <tr>
                                                    <td><c:out value="${estadisticas.name}"/></td>
                                                    <td><c:out value="${estadisticas.jugados}"/></td>
                                                    <td><c:out value="${estadisticas.ganados}"/></td>
                                                    <td><c:out value="${estadisticas.empatados}"/></td>
                                                    <td><c:out value="${estadisticas.perdidos}"/></td>
                                                    <td><c:out value="${estadisticas.gfavor}"/></td>
                                                    <td><c:out value="${estadisticas.gcontra}"/></td>
                                                    <td><c:out value="${estadisticas.puntos}"/></td>
                                                    <td><c:out value="${estadisticas.difgoles}"/></td>
                                                </tr>
                                            </c:forEach>
    </table>
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