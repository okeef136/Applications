<%-- 
    Document   : home
    Created on : Oct 24, 2016, 9:38:16 AM
    Author     : mgaffney
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Addresses</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
              rel="stylesheet">
        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div class="container">
            <h1>Addresses</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/home">Home</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/search">Search</a>
                    </li>

                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayAddressBookNoAjax">
                            Address Book (No Ajax)
                        </a>
                    </li>
                </ul>
            </div>

            <!--
 #1: Start Changes
 Add a row to our container - this will hold the summary table and the new
 contact form.
            -->
            <div class="row">
                <!--
                #2: Add a col to hold the summary table - have it take up half the row
                -->
                <div class="col-md-6">
                    <h2>Addresses</h2>
                    <%@include file="addressSummaryTableFragment.jsp" %>
                </div> <!-- End col div -->
                <!--
                #4: Add col to hold the new contact form - have it take up the other
                half of the row
                -->
                <div class="col-md-6">
                    <h2>Add New Address</h2>
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="add-first-name" class="col-md-4 control-label">
                                First Name:
                            </label>
                            <div class="col-md-8">
                                <input type="text"
                                       class="form-control"
                                       id="add-first-name"
                                       placeholder="First Name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-last-name" class="col-md-4 control-label">
                                Last Name:
                            </label>
                            <div class="col-md-8">
                                <input type="text"
                                       class="form-control"
                                       id="add-last-name"
                                       placeholder="Last Name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-street-name" class="col-md-4 control-label">
                                Street:
                            </label>
                            <div class="col-md-8">
                                <input type="text"
                                       class="form-control"
                                       id="add-street-name"
                                       placeholder="Street"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="add-city" class="col-md-4 control-label">City:</label>
                            <div class="col-md-8">
                                <input type="text"
                                       class="form-control"
                                       id="add-city"
                                       placeholder="City"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-state" class="col-md-4 control-label">State:</label>
                            <div class="col-md-8">
                                <input type="text"
                                       class="form-control"
                                       id="add-state"
                                       placeholder="State"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-zipcode" class="col-md-4 control-label">
                                Zip:
                            </label>
                            <div class="col-md-8">
                                <input type="text"
                                       class="form-control"
                                       id="add-zipcode"
                                       placeholder="Zip"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <button type="submit"
                                        id="add-button"
                                        class="btn btn-default">
                                    Create Address
                                </button>
                            </div>
                        </div>
                    </form>
                </div> <!-- End col div -->
            </div>
        </div>
        <%@include file="detailsEditModalFragment.jsp"%>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/addressBook.js"></script>
    </body>
</html>
