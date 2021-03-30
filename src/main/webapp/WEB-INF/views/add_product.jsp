<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>add product</title>
<style type="text/css">
    label {
        display: inline-block;
        width: 200px;
        margin: 5px;
        text-align: left;
    }
    input[type=text], input[type=password], select {
        width: 200px;  
    }
    input[type=radio] {
        display: inline-block;
        margin-left: 45px;
    }
    input[type=checkbox] {
        display: inline-block;
        margin-right: 190px;
    }  
     
    button {
        padding: 10px;
        margin: 10px;
    }
</style>
</head>
<body>
    <div align="center">
        <h2>ADD PRODUCT</h2>
        <form:form action="add-product" method="post" modelAttribute="prod">
            <form:label path="label">Full name:</form:label>
            <form:input path="label"/><br/>
             
            <form:label path="price">Price:</form:label>
            <form:input path="price"/><br/>
             
            <form:label path="quantity">Quantity:</form:label>
            <form:input path="quantity"/><br/>      
 
            <form:label path="description">Description:</form:label>
            <form:input path="description"/><br/>
             
            <form:label path="weight">Weight:</form:label>
            <form:input path="weight"/><br/>
             
            <form:label path="barCode">barCode:</form:label>
            <form:input path="barCode"/><br/>
                     
                 
            <form:button>addProduct</form:button>
        </form:form>
    </div>
</body>
</html>