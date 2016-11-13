<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Calvins DVDs</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/font.css" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div class="container">
            <h1>Calvin's DVD Library</h1>
            <hr/>
        </div>
        <div class="container">
            <h1>New DVD Form</h1>
            <!-- #1 - Link to displayContactListNoAjax -->
            <a href="displayDVDLibraryNoAjax">DVD Library (No Ajax)</a><br/>
            <hr/>
            <form class="form-horizontal"
                  role="form"
                  action="addNewDVDNoAjax"
                  method="POST">
                <div class="form-group">
                    <label for="add-director"
                           class="col-md-4 control-label">Director:</label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               id="add-director"
                               name="director"
                               placeholder="Director"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-title" class="col-md-4 control-label">Title:</label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               id="add-title"
                               name="title"
                               placeholder="Title"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="add-Rating"
                           class="col-md-4 control-label">Rating:</label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               id="add-Rating"
                               name="rating"
                               placeholder="Rating"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-year" class="col-md-4 control-label">Year:</label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               id="add-year"
                               name="year"
                               placeholder="Year"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-note" class="col-md-4 control-label">Note:</label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               id="add-note"
                               name="note"
                               placeholder="Note"/>
                    </div>
                </div>
                
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <button type="submit"
                                id="add-button"
                                class="btn btn-default">Add New DVD</button>
                    </div>
                </div>
            </form>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
