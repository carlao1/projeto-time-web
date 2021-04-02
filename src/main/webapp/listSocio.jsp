<%-- 
    Document   : listSocio
    Created on : 2 de abr de 2021, 22:11:49
    Author     : Luiz
--%>

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
        <title>socio</title>
    </head>
<body>
    <table border=1>
        <thead>
            <tr>
                <th>Nome</th>
                <th>Idade</th>
                <th>Sexo</th>
                <th>socio</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${socios}" var="socio">
                <tr>
                    <td><c:out value="${socio.nome}" /></td>
                    <td><c:out value="${socio.idade}" /></td>
                    <td><c:out value="${socio.sexo}" /></td>
                    <td><c:out value="${socio.cpf}" /></td>
                    <td><a href="">Update</a></td>
                    <td><a href="/>">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="UserController?action=insert">Add socio</a></p>
</body>
</html>
