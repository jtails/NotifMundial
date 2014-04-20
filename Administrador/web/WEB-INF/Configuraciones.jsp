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
                    <li>Configuraciones</li>
                </ol>
                <!-- end breadcrumb -->

            </div>
            <!-- END RIBBON -->

            <!-- MAIN CONTENT -->
            <div id="content">

                <!-- widget grid -->
                <section id="widget-grid">

                    <!-- row -->
                    <div class="row">

                        <article class="col-sm-12 col-md-12 col-lg-4">

                            <!-- new widget -->
                            <div class="jarviswidget" id="wid-id-empresas-1" data-widget-colorbutton="false" data-widget-editbutton="false"
                                data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="false"
                                data-widget-sortable="false">
                                <header>
                                    <span class="widget-icon"> <i class="fa fa-building"></i> </span>
                                    <h2>Configuraciones</h2>
                                </header>

                                <!-- widget div-->
                                <div>
                                    <div class="widget-body no-padding">
                                        <!-- content goes here -->
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
                                                
                                        <form action="Configuraciones" method="POST" enctype="multipart/form-data" class="smart-form">
                                            <fieldset>
                                                <section>
                                                        <label class="label">Certificado iOS</label>
                                                        <label class="input">
                                                                <!--<input type="text" class="input-lg">-->
                                                                <input type="hidden" name="opcion" value="Agregar"/>
                                                                <div class="input input-file">
                                                                    <span class="button"><input type="file" id="file" name="certificado" onchange="this.parentNode.nextSibling.value = this.value">Examinar</span><input type="text" placeholder="Seleccione un archivo" readonly="">
                                                                </div>
                                                        </label>
                                                </section>
                                            </fieldset>
                                            <footer>
                                                <input type="submit" value="Agregar" name="Aceptar" class="btn btn-primary"/>
                                                <input type="reset" value="Cancelar" class="btn btn-default"/>
                                            </footer>
                                        </form>
                                        <!-- end content -->
                                    </div>

                                </div>
                                <!-- end widget div -->
                            </div>
                            <!-- end widget -->
                        </article>
                        
                        <article class="col-sm-12 col-md-12 col-lg-8">

                            <!-- new widget -->
                            <div class="jarviswidget" id="wid-id-empresas-2" data-widget-colorbutton="false" data-widget-editbutton="false"
                                data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="false"
                                data-widget-sortable="false">

                                <header>
                                    <span class="widget-icon"> <i class="fa fa-building"></i> </span>
                                    <h2>Configuraciones</h2>
                                </header>

                                <!-- widget div-->
                                <div>
                                    <div class="widget-body padding">
                                        <table class="table table table-striped table-hover table-bordered">
                                            <thead style="background:gray"><tr><th>Certificado iOS</th></tr></thead>
                                                <tr>
                                                    <td>
                                                        <c:if test="${not empty Configuraciones.certificadoios}">
                                                            <div class="alert alert-success">
                                                                <i class="fa fa-check"></i> Certificado OK
                                                            </div>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                        </table>
                                    </div>
                                    <!-- end content -->
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