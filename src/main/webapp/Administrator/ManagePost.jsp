
<%@page import="java.util.List"%>
<%@page import="Beans.PostBean"%>
<%@page import="Dao.Postdao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/src/css/reset.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/src/css/style.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/src/css/Painel/post.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">


        <title>Painel administrador</title>
    </head>

    <body>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
        <header>
            <img src="${pageContext.request.contextPath}/src/img/Logo.png" alt="Logo" width="140px" height="80px">
            <nav>
                <ul>
                    <li><a href="#principal">INICIO</a></li>
                    <li><a href="#produtos">Gerenciar Slider</a></li>
                </ul>
            </nav>
        </header>
        <main>

            <Style>
                main{
                    display: flex;
                    flex-direction: row;
                    flex-wrap: wrap;
                }
                form{
                    width: auto;
                    height: auto;
                    display: flex;
                    flex-direction: row;
                    gap: 15px;
                }
                .card-img-top{
                    width: 286px;
                    height: 180px;
                    border: 2px double black;
                    border-radius: 60px;
                }
                .btn{
                    height: auto;
                }



                .modal-content  {
                    position: relative;
                    display: flex;
                    flex-direction: column;
                    width: 100%;
                    color: var(--bs-modal-color);
                    pointer-events: auto;
                    background-color: var(--bs-modal-bg);
                    background-clip: padding-box;
                    border: var(--bs-modal-border-width) solid var(--bs-modal-border-color);
                    border-radius: var(--bs-modal-border-radius);
                    outline: 0;

                }


                .form-group{
                    width: 100%;
                }



            </Style>


            <style>

                .modal-footer {
                    flex-wrap: nowrap;
                }
                .modal-header{
                    justify-content: space-between;
                }
                .modal-header button{
                    width: auto;
                    margin-left: 1vh
                }
            </style>

            <%
                Postdao Returnpost = new Postdao();
                try {
                    List<PostBean> posts = Returnpost.Returnpost();
                    for (PostBean post : posts) {
                        out.print(" <form action=\"" + request.getContextPath() + "/Post\" method=\"post\" >");
                        out.print("<input type=\"hidden\" name=\"action\" value=\"select\">");
                        out.print("<div class=\"card\" style=\"width: 18rem;\">");
                        out.print("<img class=\"card-img-top\" src=\"" + request.getContextPath() + "/src/img/Posts/" + post.getImg() + "\" alt=\"" + post.getTitle() + "\">");
                        out.print("<div class=\"card-body\">");
                        out.print(("<p>ID: " + post.getId()+ " </p>"));
                        out.print(("<p>Nome: " + post.getTitle() + " </p>"));
                        out.print("<input type=\"hidden\"  id=\"title\" value=\"" + post.getTitle() + "\" name=\"title\" style=\"border: none;\" >");
                        out.print(("<p>Descrição " + post.getTitle() + " </p>"));
                        out.print("<input type=\"hidden\"  id=\"description\" value=\"" + post.getDescription() + "\" name=\"description\" style=\"border: none;\" >");
                        out.print("<input type=\"hidden\"  id=\"Iduser\" value=\"" + post.getId() + "\" name=\"Iduser\" style=\"border: none;\" >");

                        out.print("<button type=\"submit\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#exampleModal\" data-whatever=\"@mdo\">Editar</button>");
                        out.print("</div></div>");
                        out.print("</form>");

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            %>







        </main>

    </body>

</html>
