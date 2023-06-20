<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>&Closet Search</title>
    </head>
<body>
<!-- 메뉴바 -->
	<jsp:include page = "../menu.jsp"/>
	<hr>
	<div align="center">
	  <table class="n23">
	    <tr bgcolor="#CCCCCC">
	      <td>&nbsp;</td>
	      <td><b>Product ID</b></td>
	      <td><b>Name</b></td>
	    </tr>
	    <c:forEach var="product" items="${productList.pageList}">
	      <tr bgcolor="#FFFF88">
	        <td><a
	          href='<c:url value="/shop/viewProduct.do"><c:param name="productId" value="${product.productId}"/></c:url>'>
	            <c:out value="${product.description}" escapeXml="false" />
	        </a></td>
	        <td><b><a
	            href='<c:url value="/shop/viewProduct.do"><c:param name="productId" value="${product.productId}"/></c:url>'>
	              <font color="BLACK"><c:out value="${product.productId}" /></font>
	          </a></b></td>
	        <td><c:out value="${product.name}" /></td>
	      </tr>
	    </c:forEach>
	    <tr>
	      <td><c:if test="${!productList.firstPage}">
	          <a href="?page=previous"><font color="white"><B>&lt;&lt;
	                Prev</B></font></a>
	        </c:if> <c:if test="${!productList.lastPage}">
	          <a href="?page=next"><font color="white"><B>Next
	                &gt;&gt;</B></font></a>
	        </c:if></td>
	    </tr>
	  </table>
	</div>
</body>
</html>