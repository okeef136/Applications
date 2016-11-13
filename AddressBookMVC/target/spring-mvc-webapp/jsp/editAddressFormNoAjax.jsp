<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> <!-- #1 - Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Company Addresses</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head> <body>
        <div class="container"> <h1>Company Addresses</h1> <hr/>
        </div>
        <div class="container">
            <h1>Edit Address Form</h1>
            <a href="displayAddressBookNoAjax">Address List (No Ajax)</a><br/> <hr/>
            <sf:form class="form-horizontal" role="form" modelAttribute="address"
                     action="editAddressNoAjax" method="POST"> <div class="form-group">
                    <label for="edit-first-name" class="col-md-4 control-label">First Name:</label> <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="edit-first-name" path="firstName" placeholder="First Name"/>
                        <sf:errors path="firstName" cssclass="error"></sf:errors>
                    </div> </div>
                <div class="form-group">
                    <label for="edit-last-name" class="col-md-4 control-label">Last Name:</label> <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="edit-last-name" path="lastName" placeholder="Last Name"/>
                    <sf:errors path="lastName" cssclass="error"></sf:errors>
                    </div> </div>
                <div class="form-group">
                    <label for="edit-streetName" class="col-md-4 control-label">Street:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="edit-streetName"
                                  path="streetName" placeholder="Street"/>
                    <sf:errors path="streetName" cssclass="error"></sf:errors>
                    </div> </div>
                <div class="form-group">
                    <label for="edit-city" class="col-md-4 control-label">City:</label> <div class="col-md-8">
                        <sf:input type="city" class="form-control" id="edit-city" path="city" placeholder="City"/>
                    <sf:errors path="city" cssclass="error"></sf:errors>
                    </div> </div>
                <div class="form-group">
                    <label for="edit-state" class="col-md-4 control-label">State:</label> <div class="col-md-8">
                        <sf:input type="tel" class="form-control" id="edit-state" path="state" placeholder="State"/>
                        <sf:errors path="state" cssclass="error"></sf:errors>
                        <sf:hidden path="addressId"/> </div>
                </div>
                <div class="form-group">
                    <label for="edit-zipcode" class="col-md-4 control-label">Zip:</label> <div class="col-md-8">
                        <sf:input type="zipcode" class="form-control" id="edit-zipcode" path="zipcode" placeholder="Zip"/>
                    <sf:errors path="zipcode" cssclass="error"></sf:errors>
                    </div> 
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <button type="submit" id="add-button" class="btn btn-default">Update Address</button>
                    </div> </div>
            </sf:form> </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script> 
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body> 