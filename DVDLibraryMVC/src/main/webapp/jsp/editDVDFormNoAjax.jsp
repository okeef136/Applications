<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> <!-- #1 - Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Calvins DVD Library</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/font.css" rel="stylesheet">
        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head> <body>
        <div class="container"> <h1>Calvin's DVD Library</h1> <hr/>
        </div>
        <div class="container">
            <h1>Edit DVD Form</h1>
            <a href="displayDVDLibraryNoAjax">DVD Library (No Ajax)</a><br/> <hr/>
            <sf:form class="form-horizontal" role="form" modelAttribute="dvd"
                     action="editDVDNoAjax" method="POST"> 
                <div class="form-group">
                    <label for="edit-director" class="col-md-4 control-label">Director:</label> 
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="edit-director" path="director" placeholder="Director"/>
                        <sf:errors path="director" cssclass="error"></sf:errors>
                        </div> 
                    </div>
                    <div class="form-group">
                        <label for="edit-title" class="col-md-4 control-label">Title:</label> 
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="edit-title" path="title" placeholder="Title"/>
                        <sf:errors path="title" cssclass="error"></sf:errors>
                        </div> 
                    </div>
                    <div class="form-group">
                        <label for="edit-rating" class="col-md-4 control-label">Rating:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="edit-rating"
                                  path="rating" placeholder="Rating"/>
                        <sf:errors path="rating" cssclass="error"></sf:errors>
                        </div> 
                    </div>
                    <div class="form-group">
                        <label for="edit-year" class="col-md-4 control-label">Year:</label> 
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="edit-year" path="year" placeholder="Year"/>
                        <sf:hidden path="DVDId"/>
                        <sf:errors path="year" cssclass="error"></sf:errors>
                        </div> 
                    </div>

                <c:forEach var="note" items="${dvd.notes}" varStatus="loop">       
                    <div class="form-group">
                        <label for="edit-note" class="col-md-4 control-label">Edit Note: </label> 
                        <div class="col-md-8">
                            <sf:input type="text" class="form-control" id="edit-note" path="notes[${loop.index}]" placeholder=""/>
                        </div>
                    </div>
                </c:forEach>
                        
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <button type="submit" id="add-button" class="btn btn-default">Update DVD</button>
                    </div> 
                </div>
            </sf:form> 
                
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script> 
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body> 