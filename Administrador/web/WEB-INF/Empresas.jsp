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
                    <li>Empresas</li>
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
                                    <h2>Administración de Empresas</h2>
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
                                                
                                        <form action="Empresas" method="POST" enctype="multipart/form-data" class="smart-form">
                                            <fieldset>
                                                <section>
                                                        <label class="label">Nombre</label>
                                                        <label class="input">
                                                                <!--<input type="text" class="input-lg">-->
                                                                <input type="hidden" name="opcion" value="Agregar"/>
                                                                <input type="text" name="nombre" value=""/>
                                                        </label>
                                                </section>
                                                <section>
                                                        <label class="label">No.Licencias</label>
                                                        <label class="input">
                                                                <input type="text" name="licencias" value=""/>
                                                        </label>
                                                </section>
                                                
                                                <section>
                                                    <label class="label">Imagen Estatica</label>
                                                            <div class="input input-file">
                                                                <span class="button"><input type="file" id="file" name="imagen1" onchange="this.parentNode.nextSibling.value = this.value">Examinar</span><input type="text" placeholder="Seleccione un archivo" readonly="">
                                                            </div>
                                                </section>
                                                <section>
                                                    <label class="label">Imagen Estatica</label>
                                                            <div class="input input-file">
                                                                <span class="button"><input type="file" id="file" name="imagen2" onchange="this.parentNode.nextSibling.value = this.value">Examinar</span><input type="text" placeholder="Seleccione un archivo" readonly="">
                                                            </div>
                                                </section>
                                                <section>
                                                    <label class="label">Imagen Estatica</label>
                                                            <div class="input input-file">
                                                                <span class="button"><input type="file" id="file" name="imagen3" onchange="this.parentNode.nextSibling.value = this.value">Examinar</span><input type="text" placeholder="Seleccione un archivo" readonly="">
                                                            </div>
                                                </section>
                                                <section>
                                                    <label class="label">Imagen Dinamica</label>
                                                            <div class="input input-file">
                                                                <span class="button"><input type="file" id="file" name="imagen4" onchange="this.parentNode.nextSibling.value = this.value">Examinar</span><input type="text" placeholder="Seleccione un archivo" readonly="">
                                                            </div>
                                                </section>
                                                <section>
                                                    <label class="label">Imagen Dinamica</label>
                                                            <div class="input input-file">
                                                                <span class="button"><input type="file" id="file" name="imagen5" onchange="this.parentNode.nextSibling.value = this.value">Examinar</span><input type="text" placeholder="Seleccione un archivo" readonly="">
                                                            </div>
                                                </section>
                                                <section>
                                                    <label class="label">Imagen Dinamica</label>
                                                            <div class="input input-file">
                                                                <span class="button"><input type="file" id="file" name="imagen6" onchange="this.parentNode.nextSibling.value = this.value">Examinar</span><input type="text" placeholder="Seleccione un archivo" readonly="">
                                                            </div>
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
                                    <h2>Empresas Existentes</h2>
                                </header>

                                <!-- widget div-->
                                <div>
                                    <div class="widget-body padding">
                                        <table class="table table table-striped table-hover table-bordered">
                                            <thead style="background:gray"><tr><th>Nombre</th><th>Codigo</th><th>Lic Actuales</th><th>Lic Disponibles</th><th>Eliminar</th></th></thead>
                                                        <c:forEach var="empresa" items="${requestScope.Empresas}" varStatus="i">
                                                <tr>
                                                    <td><c:out value="${empresa.nombre}"/></td>
                                                    <td width="200px"><c:out value="${empresa.codigo}"/></td>
                                                    <td width="200px"><c:out value="${empresa.licencias}"/></td>
                                                    <td width="200px"><c:out value="${empresa.licencias-empresa.nolicencias}"/></td>
                                                    <td style="width: 130px;">
                                                        <form action="Empresas" method="POST" enctype="multipart/form-data">
                                                            <input type="hidden" name="id" value="${empresa.id_empresa}"/>
                                                            <input type="hidden" name="opcion" value="Eliminar"/>
                                                            <input type="button" value="Eliminar" class="btn btn-danger btn-block" onclick="var result = confirm('¿Esta Seguro que Desea Eliminar la Empresa?\n No se podran registrar mas Dispositivos con esta Empresa');if (result == true) {form.submit();} else {form.reset();}"/>
                                                        </form>
                                                    </td>
                                                </tr>
                                            </c:forEach>
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