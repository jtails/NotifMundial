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
                    <li>Principal</li>
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
                            <div class="jarviswidget" id="wid-id-menu" 
                                 data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-sortable="false" 
                                 data-widget-colorbutton="false" data-widget-deletebutton="false" 
                                 data-widget-fullscreenbutton="false" data-widget-collapsed="false">

                                <!-- widget options:
                                usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">

                                data-widget-colorbutton="false"
                                data-widget-editbutton="false"
                                data-widget-togglebutton="false"
                                data-widget-deletebutton="false"
                                data-widget-fullscreenbutton="false"
                                data-widget-custombutton="false"
                                data-widget-collapsed="true"
                                data-widget-sortable="false"

                                -->

                                <header>
                                    <span class="widget-icon"> <i class="fa fa-home"></i> </span>
                                    <h2>Principal</h2>
                                </header>

                                <!-- widget div-->
                                <div>
                                    <div class="widget-body padding">
                                        <!-- content goes here -->
                                        <div class="row">
                                            <div class="col-sm-12 col-md-12 col-lg-6">
                                                <form action="Menu" method="POST" class="padding-bottom-20">
                                                    <input type="hidden" value="Estadisticas" name="opcion"/>
                                                    <input type="submit" value="Tabla de Posiciones" class="btn btn-lg btn-primary btn-block"/>
                                                </form>
                                            </div>
                                            <div class="col-sm-12 col-md-12 col-lg-6">
                                                <form action="Menu" method="POST" class="padding-bottom-20">
                                                    <input type="hidden" value="Empresas" name="opcion"/>
                                                    <input type="submit" value="Empresas" class="btn btn-lg btn-primary btn-block"/>
                                                </form>
                                            </div>
                                        </div>
                                        
                                        <div class="row">
                                            <div class="col-sm-12 col-md-12 col-lg-6">
                                                <form action="Menu" method="POST" class="padding-bottom-20">
                                                    <input type="hidden" value="Partidos" name="opcion"/>
                                                    <input type="submit" value="Editar Partidos" class="btn btn-lg btn-primary btn-block"/>
                                                </form>
                                            </div>
                                            <div class="col-sm-12 col-md-12 col-lg-6">
                                                <form action="Menu" method="POST" class="padding-bottom-20">
                                                    <input type="hidden" value="IPartido" name="opcion"/>
                                                    <input type="submit" value="Partidos" class="btn btn-lg btn-primary btn-block"/>
                                                </form>
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