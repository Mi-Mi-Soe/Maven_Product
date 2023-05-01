<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Product</title>
</head>
<body>

<h1>Products List</h1>  
<table border="2" width="70%" cellpadding="2">  
<tr><th>Id</th><th>Name</th><th>Price</th><th>Edit</th><th>Delete</th></tr>  
   <c:forEach var="product" items="${list}">   
   <tr>  
   <td>${product.id}</td>  
   <td>${product.name}</td>  
   <td>${product.price}</td>  
   <td><a href="editproduct/${product.id}">Edit</a></td>  
   <td><a href="deleteproduct/${product.id}">Delete</a></td>  
   </tr>  
   </c:forEach>  
   </table>  
   <br/>  
   <a href="product">Add New Product</a>  

</body>
</html>