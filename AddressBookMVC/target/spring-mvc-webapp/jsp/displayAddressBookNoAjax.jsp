<%-- 
    Document   : displayAddressListNoAjax.jsp
    Created on : Oct 25, 2016, 1:01:54 PM
    Author     : Devin Hall
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Company Addresses</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div class="container">
            <h1>Company Addresses</h1>
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
                        <a href="${pageContext.request.contextPath}/displayAddressBookNoAjax">
                            Address Book (No Ajax)
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="container">
            <h1>Company Addresses</h1>
            <!-- #1 - Link to addContactForm -->
            <a href="displayNewAddressFormNoAjax">Add a Contact</a><br/>
            <hr/>
            <!-- #2 - Iterate over contactList: forEach contact in contactList, do something -->
            <c:forEach var="address" items="${addressBook}">
                <!-- #3 - Build custom delete URL for each contact. Use the id of the contact -->
                <!-- to specify the contact to delete or update -->
                <s:url value="deleteAddressNoAjax"
                       var="deleteAddress_url">
                    <s:param name="addressId" value="${address.addressId}" />
                </s:url>
                <!-- Build custom edit URL for each contact -->
                <s:url value="displayEditAddressFormNoAjax"
                       var="editAddress_url">
                    <s:param name="addressId" value="${address.addressId}" />
                </s:url>
                <!-- #4 - A pointless demonstration of the if tag -->
                <c:if test="${address.lastName == 'Doe'}">
                    CEO<br/>
                </c:if>
                Name: ${address.firstName} ${address.lastName} |
                <a href="${deleteAddress_url}">Delete</a> |
                <a href="${editAddress_url}">Edit</a><br/>
                Address: ${address.streetName}<br/>
                 ${address.city}, ${address.state}<br/>
                 ${address.zipcode}<br/>
                <hr>
            </c:forEach>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
