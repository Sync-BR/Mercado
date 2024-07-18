<%-- 
    Document   : Products
    Created on : 27 de jun. de 2024, 12:50:51
    Author     : Eduar
--%>

<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/src/css/reset.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/src/css/style.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/src/css/Painel/post.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <img src="${pageContext.request.contextPath}/src/img/Logo.png" alt="Logo" width="140px" height="80px">
            <nav>
                <ul>
                    <li><a href="#principal">INICIO</a></li>
                    <li><a href="${pageContext.request.contextPath}/Administrator/Post.jsp">Efetuar post</a></li>
                    <li><a href="${pageContext.request.contextPath}/Administrator/ManagePost.jsp">Gerenciar Slider</a></li>
                    <li><a href="${pageContext.request.contextPath}/Administrator/Products.jsp">Adicionar postagem</a></li>
                </ul>
            </nav>
        </header>
        <main>
            <style>
                input{
                    width: 90%;
                }

            </style>
 
            <form action="${pageContext.request.contextPath}/ProductServlets" method="post"  enctype="multipart/form-data">
                <input type="hidden" name="action" value="newProducts" >
                <label>Imagem</label>
                <input type="file" name="img" >
                <label>Nome:</label>
                <input type="text" name="Name" ">
                <label>Descrição</label>
                <input type="text" name="Description" >
                <label>Quantidade</label>
                <input type="number" name="Amount" >
                <label>Valor</label>
                <input type="number" name="Value" >
                <label>Fornecedor</label>
                <input type="text" name="supplier" >
                <label>Código de barras</label>
                <input type="text" name="barcode" >
                <input type="submit" value="Enviar" name="Enviar" />

            </form>
        </main>
    </body>
</html>
