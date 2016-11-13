<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>DVDs</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/font.css" rel="stylesheet">
        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>

        <div class="container">
            <h1>Calvin's DVD Library</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/home">Home</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/search">Search</a>
                    </li>
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/displayDVDLibraryNoAjax">
                            DVD Library (No Ajax)
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="container">
            <h1>DVD Library</h1>
            <!-- #1 - Link to addContactForm -->
            <h3>Add a DVD</h3>
            <a href="displayNewDVDFormNoAjax"><img src="https://cdn4.iconfinder.com/data/icons/devine_icons/Black/PNG/Folder%20and%20Places/CD-DVD%20Drive.png" style="height: 50px; width: 50px"></a><br/>
            <hr/>
            <!-- #2 - Iterate over contactList: forEach contact in contactList, do something -->
            <div class="row col-lg-12">
                <div class="col-md-6">
                <c:forEach var="dvd" items="${dvdLibrary}">
                    <!-- #3 - Build custom delete URL for each contact. Use the id of the contact -->
                    <!-- to specify the contact to delete or update -->
                    <s:url value="deleteDVDNoAjax"
                           var="deleteDVD_url">
                        <s:param name="DVDId" value="${dvd.dvdId}" />
                    </s:url>
                    <!-- Build custom edit URL for each contact -->
                    <s:url value="displayEditDVDFormNoAjax"
                           var="editDVD_url">
                        <s:param name="DVDId" value="${dvd.dvdId}" />
                    </s:url>

                    Title: ${dvd.title}<text> </br>
                    Director: ${dvd.director} </br>
                    Year: ${dvd.year} </br>

                    <sf:hidden path="DVDId"/>
                    Rating: ${dvd.rating} </br>
                    Notes: <br/><ul><c:forEach var="note" items="${dvd.notes}">
                            <c:if test="${note != null}"> 
                                <li>${note}</li>
                                </c:if>
                            </c:forEach>
                    </ul>
                    <a href="${deleteDVD_url}">Delete</a>
                    <text>   |   </text>
                    <a href="${editDVD_url}">Edit</a><br/>
                    <hr>
                </c:forEach>
                    
            </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
