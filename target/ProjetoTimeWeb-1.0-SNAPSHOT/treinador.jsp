
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<title>Add new treinador</title>
</head>
<body>
    <script>
        $(function() {
            $('input[name=dob]').datepicker();
        });
    </script>

    <form method="POST" action='Treinadores' name="frmAddTreinador">
        
        Nome Completo : <input type="text" name="nome" value="" /> <br />
        Sexo : <input type="text" name="sexo" value="M"/> <br /> 
        Idade : <input type="number" name="idade" /> <br /> 
        CPF : <input type="text" name="cpf" value=""/> <br /> 
        <input type="submit" value="Submit" />
    </form>
</body>
</html>
