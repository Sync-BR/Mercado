<%-- 
    Document   : Post
    Created on : 13 de jun. de 2024, 07:07:04
    Author     : Eduar
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/reset.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/Painel/post.css">
        <title>Painel administrador</title>
    </head>

    <body>
        <header>
            <img src="${pageContext.request.contextPath}/src/img/Logo.png" alt="Logo" width="140px" height="80px">
            <nav>
                <ul>
                    <li><a href="#principal">INICIO</a></li>
                    <li><a href="${pageContext.request.contextPath}/Administrator/Post.jsp">Efetuar post</a></li>
                    <li><a href="${pageContext.request.contextPath}/Administrator/ManagePost.jsp">Gerenciar Slider</a></li>
                </ul>
            </nav>
        </header>
        <main>

            <form action="${pageContext.request.contextPath}/Post" method="post" enctype="multipart/form-data">
                <input type="hidden" name="action" value="newpost">

                <h2>Adicionar Postagem</h2>
                <br><br>
                <label for="titulo">Titulo:</label><br>
                <input type="text" name="titulo" id="titulo" required>
                <br>
                <label for="descricao">Descrição</label><br> 
                <textarea name="descricao" id="descricao" required></textarea>

                <br>
                <label for="files">Arquivo</label><br>
                <input type="file" name="files" id="files" accept="image/png, image/jpeg,image/jpg">
                <br>
                <button type="submit" value="Enviar">Enviar</button>
                <br>


            </form>
        </main>
    </body>

</html>
