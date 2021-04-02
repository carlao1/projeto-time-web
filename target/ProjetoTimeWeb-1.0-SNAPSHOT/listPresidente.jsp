
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
        <title>Presidente</title>
    </head>
<body>
    <table border=1>
        <thead>
            <tr>
                <th>Nome</th>
                <th>Idade</th>
                <th>Sexo</th>
                <th>Presidente</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${presidentes}" var="presidente">
                <tr>
                    <td><c:out value="${presidente.nome}" /></td>
                    <td><c:out value="${presidente.idade}" /></td>
                    <td><c:out value="${presidente.sexo}" /></td>
                    <td><c:out value="${presidente.cpf}" /></td>
                    <td><a href="">Update</a></td>
                    <td><a href="/>">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="UserController?action=insert">Add Presidente</a></p>
</body>
</html>
