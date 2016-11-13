<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Calvins DVD Search</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
              rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/font.css" rel="stylesheet">
        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
        <style>

            #panel, #dvdDisp {
                display: none;
            }

            #panel {
                border: 1px solid lightgrey;
                border-radius:3px;
                box-shadow: 3px 7px 3px #e3e7ed;
                background-color:white;
            }

            #jumboImg{
                height:16%;
                width:16%;
                padding-right: 1%;
            }

            h1 {
                text-align:center;
                padding-top: 20px;
                padding-right:40px;
            }

            nav {
                padding-left:6%;   
            }

            h2 {
                text-align:center; 
                padding-bottom: 3%;
            }

            table {
                border: 1px solid lightgrey;
                border-radius:3px;
                background-color:white;
            }
            body {
                background-color: #eff6ff;
            }
        </style>
    </head>
    <body>
        <div class="container">

            <nav class="navbar navbar-default navbar-fixed-top">
                <h1>DVD Library</h1>
                <br>
                <ul style="font-size:14pt" class="nav nav-tabs">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/home">Home</a></li>
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/search">Search</a></li>
                    
                </ul>
            </nav>
            <div style="padding-top:180px" class="row">
                <!--
                #2: Add a col to hold the summary table - have it take up half the row
                -->
                <div id="dvdDisp" class="col-md-6">
                    <h2 style="padding-right:30px">DVDs</h2>
                    <%@include file="dvdSummaryTableFragment.jsp" %>
                </div> <!-- End col div -->
                <!--
                #4: Add col to hold the new contact form - have it take up the other
                half of the row
                -->
                <div style="padding-top:65px">
                    <div id="panel" class="col-md-6">
                        <h2>Search DVDs</h2>
                        <form style="margin-right:100px" class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="search-title" class="col-md-4 control-label">
                                    Title:
                                </label>
                                <div class="col-md-8">
                                    <input type="text"
                                           class="form-control"
                                           id="search-title"
                                           placeholder="Title"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="search-director" class="col-md-4 control-label">
                                    Director:
                                </label>
                                <div class="col-md-8">
                                    <input type="text"
                                           class="form-control"
                                           id="search-director"
                                           placeholder="Director"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="search-rating" class="col-md-4 control-label">
                                    Rating:
                                </label>
                                <div class="col-md-8">
                                    <input type="text"
                                           class="form-control"
                                           id="search-rating"
                                           placeholder="Rating"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="search-year" class="col-md-4 control-label">Year:</label>
                                <div class="col-md-8">
                                    <input type="text"
                                           class="form-control"
                                           id="search-year"
                                           placeholder="Year"/>
                                </div>
                            </div>
                            <div id = "notes-table">
                            </div>

                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <button type="submit"
                                            id="search-button"
                                            class="btn btn-default">
                                        Search
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div> <!-- End col div -->
                </div>
            </div>
        </div>
        <%@include file="detailsEditModalFragment.jsp"%>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script>
            $(document).ready(function () {
                $("#panel").fadeIn("slow");
                $("#dvdDisp").fadeIn("slow");
            });
        </script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/dvdLibrary.js"></script>
    </body>
</html>
