

<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="Beans.ProductsBean"%>
<%@page import="Dao.ProductsDao"%>
<%@page import="java.util.List"%>
<%@page import="Beans.PostBean"%>
<%@page import="Dao.Postdao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    NumberFormat dinheiro = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
    NumberFormat dinheiroSTR = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
%>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="./src/css/reset.css">
        <link rel="stylesheet" href="./src/css/style.css">
        <title>Mercado</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@200..700&display=swap" rel="stylesheet">
    </head>
    <body>
        <header>
            <img src="./src/img/Logo.png" alt="Logo" width="140px" height="80px">
            <nav>
                <ul>
                    <li><a href="#principal">INICIO</a></li>
                    <li><a href="#produtos">PRODUTOS</a></li>
                </ul>
            </nav>
        </header>
        <br>
        <main>
            <section id="principal">


                <%
                    /**
                     * <div class="carousel-caption d-none d-md-block">
                     * <h5>...</h5>
                     * <p>
                     * ...</p>
                     * </div>
                     *
                     *
                     */
                    Postdao Returnpost = new Postdao();
                    try {
                        out.print("<div id=\"carouselExampleSlidesOnly\" class=\"carousel slide\" data-ride=\"carousel\">");
                        out.print("<div class=\"carousel-inner\">");
                        out.print("<div class=\"carousel-item active\">");
                        out.print("<img class=\"d-block w-100\" src=\"./src/img/Posts/Principal.png\" alt=\"Primaria\" height=\"600px\">");

                        out.print("</div>");
                        List<PostBean> posts = Returnpost.Returnpost();
                        for (PostBean post : posts) {
                            out.print("<div class=\"carousel-item\">");
                            out.print("<img class=\"d-block w-100\" src=\"./src/img/Posts/" + post.getImg() + "\" alt=\"Second slide\" height=\"600px\">");
                            out.print("<div class=\"carousel-caption d-none d-md-block\">");
                            out.print("<h5>" + post.getTitle() + "</h5>");
                            out.print("<p>" + post.getDescription() + "</p>");
                            out.print("</div>");
                            out.print("</div>");
                        }
                        out.print("</div> </div>");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                %>


            </section>

            <section class="returnProducts">

                <h1>Produtos</h1>
                <%                    
                    ProductsDao returnProducts = new ProductsDao();
                    List<ProductsBean> products = returnProducts.returnProducts();
                    for (ProductsBean productsInfor : products) {
                       out.print(" <div class=\"card\" style=\"width: 18rem;\">");
                       out.print("<img src=\"./src/img/Posts/Produtos/" + productsInfor.getProductImages() +"\" class=\"card-img-top\" alt=\""+productsInfor.getProductName()+"\">");
                       out.print("<div class=\"card-body\">");
                       out.print("<h5 class=\"card-title\">"+productsInfor.getProductName()+"</h5>");
                       out.print("<p class=\"card-text\">"+productsInfor.getProductDescription()+"</p>");
                       out.print("<a href=\"#\" class=\"btn btn-primary\">Comprar</a>");
                       out.print("</div></div>");

                    }

                %>
            </section>
        </main>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>

