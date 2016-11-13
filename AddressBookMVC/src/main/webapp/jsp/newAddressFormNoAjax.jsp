<%-- 
    Document   : newAddressFormNoAjax
    Created on : Oct 25, 2016, 1:23:12 PM
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
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div class="container">
            <h1>Company Addresses</h1>
            <hr/>
        </div>
        <div class="container">
            <h1>New Address Form</h1>
            <!-- #1 - Link to displayContactListNoAjax -->
            <a href="displayAddressBookNoAjax">Address Book (No Ajax)</a><br/>
            <hr/>
            <form class="form-horizontal"
                  role="form"
                  action="addNewAddressNoAjax"
                  method="POST">
                <div class="form-group">
                    <label for="add-first-name"
                           class="col-md-4 control-label">First Name:</label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               id="add-first-name"
                               name="firstName"
                               placeholder="First Name"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-last-name" class="col-md-4 control-label">Last Name:</label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               id="add-last-name"
                               name="lastName"
                               placeholder="Last Name"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="add-streetName"
                           class="col-md-4 control-label">Street:</label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               id="add-streetName"
                               name="streetName"
                               placeholder="Street"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-city" class="col-md-4 control-label">City:</label>
                    <div class="col-md-8">
                        <input type="city"
                               class="form-control"
                               id="add-city"
                               name="city"
                               placeholder="City"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-state" class="col-md-4 control-label">State:</label>
                    <div class="col-md-8">
                        <input type="state"
                               class="form-control"
                               id="add-state"
                               name="state"
                               placeholder="State"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-zipcode" class="col-md-4 control-label">Zip:</label>
                    <div class="col-md-8">
                        <input type="zipcode"
                               class="form-control"
                               id="add-zipcode"
                               name="zipcode"
                               placeholder="Zip"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <button type="submit"
                                id="add-button"
                                class="btn btn-default">Add New Address</button>
                    </div>
                </div>
            </form>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
