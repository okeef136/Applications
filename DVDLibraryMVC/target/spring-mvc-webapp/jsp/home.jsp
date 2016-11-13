<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Calvin DVD Library</title>
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
            form {

            }
            #jumboImg{
                height:16%;
                width:16%;
                padding-right: 1%;
            }

            h1 {
                text-align:center;
                padding-top:20px;
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
    <body data-spy="scroll" data-target="#myScrollspy" data-offset="20">
        <div class="container">

            <nav class="navbar navbar-default navbar-fixed-top">
                <h1>DVD Library</h1>
                <br>
                <ul style="font-size:14pt" class="nav nav-tabs">
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/home">Home</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/search">Search</a>
                    </li>

                </ul>
            </nav>

            <div id="jumbo" style="padding-top:180px; background-color:#f9f9f9; border: 1px solid lightgrey" class="jumbotron">
                <div class="row">
                    <a href="http://www.imdb.com/title/tt0816692/"><img id="jumboImg" src="${pageContext.request.contextPath}/img/interstellar-ice-walk.jpg" alt="interstellar"></a>
                    <a href="http://www.imdb.com/title/tt2872718/"><img id="jumboImg" src="${pageContext.request.contextPath}/img/nightcrawler.jpg" alt="nightcrawler"></a>
                    <a href="http://www.imdb.com/title/tt0401792/"><img id="jumboImg" src="${pageContext.request.contextPath}/img/sin-city.jpg" alt="sin city"></a>
                    <a href="http://www.imdb.com/title/tt0241527/"><img id="jumboImg" src="${pageContext.request.contextPath}/img/harry-potter-sourcerer-s-stone.jpg" alt="Harry Potter 1"></a>
                    <a href="http://www.imdb.com/title/tt1201607/"><img id="jumboImg" src="${pageContext.request.contextPath}/img/harry-potter-and-the-deathly-hallows.jpg" alt="Harry Potter 7"></a>
                    <a href="http://www.imdb.com/title/tt1631867/"><img id="jumboImg" src="${pageContext.request.contextPath}/img/edge-of-tomorrow.jpg" alt="edge of tomorrow"></a>
                </div>

            </div>
            <div class="row">
                <!--
                #2: Add a col to hold the summary table - have it take up half the row
                -->
                <div id="dvdDisp" class="col-md-6">
                    <h2 style="padding-right:50px">DVDs</h2>
                    <table id="dvdTable" class="table table-hover">
                        <tr>
                            <th style="font-size:14pt" width="30%">Title</th>
                            <th style="font-size:14pt" width="20%">Director</th>
                            <th style="font-size:14pt" width="20%">Rating</th>
                            <th style="font-size:14pt" width="20%">Year</th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>
                        <!--
                        #3: This holds the list of contacts - we will add rows dynamically
                        using jQuery
                        -->
                        <tbody id="content-rows"></tbody>
                    </table>
                </div> <!-- End col div -->
                <!--
                #4: Add col to hold the new contact form - have it take up the other
                half of the row
                -->
                <div style="padding-top:40px">
                    <div id="panel" class="col-md-6">
                        <h2>Add New DVD</h2>
                        <form style="margin-right:100px" class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="add-title" class="col-md-4 control-label">
                                    Title:
                                </label>
                                <div class="col-md-8">
                                    <input type="text"
                                           class="form-control"
                                           id="add-title"
                                           placeholder="Title"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-director" class="col-md-4 control-label">
                                    Director:
                                </label>
                                <div class="col-md-8">
                                    <input type="text"
                                           class="form-control"
                                           id="add-director"
                                           placeholder="Director"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-rating" class="col-md-4 control-label">
                                    Rating:
                                </label>
                                <div class="col-md-8">
                                    <input type="text"
                                           class="form-control"
                                           id="add-rating"
                                           placeholder="Rating"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="add-year" class="col-md-4 control-label">Year:</label>
                                <div class="col-md-8">
                                    <input type="text"
                                           class="form-control"
                                           id="add-year"
                                           placeholder="Year"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-note" class="col-md-4 control-label">Note:</label>
                                <div class="col-md-8">
                                    <input type="text"
                                           class="form-control"
                                           id="add-note"
                                           placeholder="Note"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <button type="submit"
                                            id="add-button"
                                            class="btn btn-default">
                                        Create DVD
                                    </button>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <div id="validationErrors" style="color: red"/>
                                </div>
                            </div>
                    </div>
                    </form>
                </div> <!-- End col div -->
            </div>
        </div>
    </div>

    <div class="modal fade" id="details-modal" tabindex="-1" role="dialog"
         aria-labelledby="detailsModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span>
                        <span class="sr-only">Close</span>
                    </button>
                    <h4 class="modal-title" id="detailsModalLabel">DVD Details</h4>
                </div>
                <div class="modal-body">

                    <table class="table table-bordered">
                        <tr>
                            <th>Title:</th>
                            <td id="dvd-title"></td>
                        </tr>
                        <tr>
                            <th>Director:</th>
                            <td id="dvd-director"></td>
                        </tr>
                        <tr>
                            <th>Rating:</th>
                            <td id="dvd-rating"></td>
                        </tr>
                        <tr>
                            <th>Year:</th>
                            <td id="dvd-year"></td>
                        </tr>
                        <th>Notes:</th>
                        <td id="dvd-notes"></td>

                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        Close
                    </button>
                </div>
            </div>
        </div>
    </div>
    <br>
    <!-- Edit Modal -->
    <div class="modal fade" id="edit-modal" tabindex="-1" role="dialog"
         aria-labelledby="detailsModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span>
                        <span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="details-modal-label">Edit DVD</h4>
                </div>
                <div class="modal-body">
                    <h3 id="dvd-title"></h3>
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="edit-title" class="col-md-4 control-label">
                                Title:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="edit-title"
                                       placeholder="Title">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="edit-director" class="col-md-4 control-label">
                                Director:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="edit-director"
                                       placeholder="Director">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="edit-rating" class="col-md-4 control-label">
                                Rating:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="edit-rating"
                                       placeholder="Rating">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="edit-year" class="col-md-4 control-label">
                                Year:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="edit-year"
                                       placeholder="Year">
                            </div>
                        </div>
                        <div id = "notes-table">

                        </div>
                        <div class="form-group">
                            <label for="new-note" class="col-md-4 control-label">
                                New Note:
                            </label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="new-note"
                                       placeholder="Note">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <button type="submit" id="add-note" class="btn btn-default" data-dismiss="modal">
                                    Add Note
                                </button>
                                <button type="submit" id="edit-button" class="btn btn-default"
                                        data-dismiss="modal">
                                    Edit DVD
                                </button>

                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">
                                    Cancel
                                </button>
                                <input type="hidden" id="edit-dvd-id">
                                <div id="validationErrors" style="color: red"/>
                            </div>
                        </div>
                </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
<script>
    $(document).ready(function () {
        $("#panel").fadeIn("slow");
        $("#dvdDisp").fadeIn("slow");
        $("#jumbo").fadeIn("slow");
    });
</script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/dvdLibrary.js"></script>

</body>
</html>
