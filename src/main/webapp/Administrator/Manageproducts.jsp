
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="util.Conexao"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Dao.ProductsDao"%>
<%@page import="Beans.ProductsBean"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    boolean Estadoconexao = false;
    Connection conn = null;
    try {
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/mercado?useUnicode=true&characterEncoding=UTF-8", "root", "");

    } catch (SQLException e) {
        response.sendRedirect(request.getContextPath() + "/teste.html");
    } finally {
        Conexao.closeConnection(conn);
    }

%>

<%    ProductsDao operacion = new ProductsDao();
    NumberFormat dinheiro = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
    NumberFormat dinheiroSTR = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type;charset=UTF-8" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/src/css/reset.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/src/css/style.css">
        <title>Gerenciar Produtos</title>
    </head>
    <body>



        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
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
        <style>

            body{
                overflow-y: hidden;
            }

            section {
                width: 100%;
                height: auto;
            }
            form {
                display: block;
                margin-top: 0em;
                unicode-bidi: isolate;
            }
            .btn{

            }
        </style>
        <main>

            <!-- Sessão de lista de produtos -->
            <section>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Nome do produto</th>
                            <th scope="col">Valor</th>
                            <th scope="col">Código de barras</th>
                            <th scope="col">Operações</th>
                        </tr>
                    </thead>
                    <tbody>                 
                        <!-- 
                            @input name deve conter um nomero
                            para isso deve exister uma variavel count
                            para ser adicionado  depois do nome do produto.
                        -->

                    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

                    <script>
                        $(document).ready(function () {
                            $('.edit-btn').on('click', function () {
                                var productId = $(this).data('id');
                                var productName = $(this).data('name');
                                var productDescription = $(this).data('description');
                                var productStock = $(this).data('stock');
                                var productValue = $(this).data('value');
                                var productBarcode = $(this).data('barcode');
                                var productSupplier = $(this).data('supplier');

                                $('#exampleModal input[name="Productid"]').val(productId);
                                $('#exampleModal input[name="Productname"]').val(productName);
                                $('#exampleModal textarea[name="Productdescription"]').val(productDescription);
                                $('#exampleModal input[name="ProductStock"]').val(productStock);
                                $('#exampleModal input[name="Productvalue"]').val(productValue);
                                $('#exampleModal span[id="productValueText"]').text('R$ ' + productValue);
                                $('#exampleModal input[name="Productbarcode"]').val(productBarcode);
                                $('#exampleModal input[name="Productsupplier"]').val(productSupplier);

                                // Você pode adicionar mais campos aqui conforme necessário
                            });
                        });
                    </script>
                    <script>
                        $(document).ready(function () {
                            $('.delete-btn').on('click', function () {
                                var productId = $(this).data('id');

                                $('#staticBackdrop input[name="Idproducts"]').val(productId);

                            });
                        });
                    </script>



                    <%                        List<ProductsBean> products = operacion.returnProducts();
                        try {
                            for (ProductsBean ReturnProducts : products) {

                                String valorconvertidoSTRs = String.valueOf(ReturnProducts.getProductValue());
                                double valorconvertidoDBE = Double.parseDouble(valorconvertidoSTRs);

                                String valorconvertidoDBESTR = dinheiro.format(valorconvertidoDBE);
                                System.out.println("valor convertido em string  " + valorconvertidoDBESTR);

                                int productIds = ReturnProducts.getProductId();
                                out.print("<tr>");
                                out.print("<th scope=\"row\">" + ReturnProducts.getProductId() + "</th>");
                                out.print("<td>" + ReturnProducts.getProductName() + "</td>");
                                out.print("<td>" + dinheiroSTR.format(ReturnProducts.getProductValue()) + "</td>");
                                out.print("<td>" + ReturnProducts.getProductBarcode() + "</td>");
                                out.print("<td>");
                                out.print("<button class=\"btn btn-primary edit-btn\" data-toggle=\"modal\" data-target=\"#exampleModal\" data-id=\"" + ReturnProducts.getProductId() + "\" data-name=\"" + ReturnProducts.getProductName() + "\" data-description=\"" + ReturnProducts.getProductDescription() + "\" data-value=\"" + valorconvertidoDBESTR + "\" data-stock=\"" + ReturnProducts.getProductStock() + "\" data-barcode=\"" + ReturnProducts.getProductBarcode() + "\" data-supplier = \"" + ReturnProducts.getProductSupplier() + " \">Editar</button>&nbsp;");

                                out.print("<button  class=\"btn btn-danger delete-btn\" data-toggle=\"modal\" data-target=\"#staticBackdrop\"   data-id=\"" + ReturnProducts.getProductId() + " \">Excluir</button>");
                                out.print("</td>");

                                out.print("</tr>");
                            }

                        } catch (Exception e) {
                            e.getMessage();
                        }


                    %>

                    </tbody>
                </table>
            </section>
            <section >

                <!-- Modal de edição -->
                <section>
                    <form action="${pageContext.request.contextPath}/ProductServlets" method="post" enctype="multipart/form-data">
                        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Editar produto</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <form>
                                            <input type="hidden" name="action" value="editProduct">
                                            <div class="form-group">
                                                <input type="hidden" class="form-control" id="formGroupExampleInput" name="Productid">
                                            </div>
                                            <div class="form-group">
                                                <label for="formGroupExampleInput">Nome do produto</label>
                                                <input type="text" class="form-control" id="formGroupExampleInput" name="Productname">
                                            </div>
                                            <div class="form-group">
                                                <label for="formGroupExampleInput">Descrição do produto</label>
                                                <textarea class="form-control" aria-label="With textarea" name="Productdescription"></textarea>
                                            </div>
                                            <div class="form-group">
                                                <label for="formGroupExampleInput">Valor do produto</label>
                                                <div class="form-group form-group-edited">
                                                    <div class="input-group-prepend">
                                                        <span class="input-group-text" id="productValueText" >R$ 0.00</span>
                                                    </div>
                                                    <input type="text" class="form-control" name="Productvalue" id="productValueInput"  pattern="[0-9]+([,\.][0-9]+)?" title="Use vírgula como separador decimal">
                                                    <script>
                                                        document.getElementById('productValueInput').addEventListener('input', function () {
                                                            let rawValue = this.value.trim(); // Valor sem espaços extras

                                                            // Remover qualquer caractere não numérico, exceto vírgula e ponto
                                                            rawValue = rawValue.replace(/[^\d,.]/g, '');

                                                            // Tratar casos de vírgula e ponto juntos
                                                            rawValue = rawValue.replace(/[,.](?=.*[,.])/g, '');

                                                            // Substituir vírgula por ponto se houver
                                                            rawValue = rawValue.replace(',', '.');

                                                            // Converter para float e formatar com duas casas decimais
                                                            let value = parseFloat(rawValue).toFixed(2).replace('.', ',');

                                                            // Adicionar separador de milhar
                                                            value = value.replace(/\B(?=(\d{3})+(?!\d))/g, '.');

                                                            // Adicionar o símbolo de moeda e formatação desejada
                                                            value = 'R$' + value;

                                                            // Atualizar o valor no elemento de texto
                                                            document.getElementById('productValueText').innerText = value;
                                                        });
                                                    </script>
                                                </div>
                                            </div>


                                            <div class="form-group">
                                                <label for="formGroupExampleInput">Estoque do produto</label>
                                                <input type="number" class="form-control" name="ProductStock">
                                            </div>
                                            <div class="form-group">
                                                <label for="formGroupExampleInput">Fornecedor do produto</label>
                                                <input type="text" class="form-control" id="formGroupExampleInput" name="Productsupplier">
                                            </div>
                                            <div class="form-group">
                                                <label for="formGroupExampleInput">Código de Barras</label>
                                                <input type="text" class="form-control" id="formGroupExampleInput" name="Productbarcode">
                                            </div>
                                            <div class="form-group">
                                                <label for="formGroupExampleInput"> Imagem do produto</label>
                                                <input type="file" class="custom-file-input" id="inputGroupFile01" aria-describedby="inputGroupFileAddon01" accept="image/png, image/jpeg" name="Productimages">
                                            </div>
                                            <div class="modal-footer">
                                                <button type="submit" class="btn btn-primary">Salvar alterações</button>
                                                <button type="submit" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>


                    <!-- Modal de exclusão-->
                    <form action="${pageContext.request.contextPath}/ProductServlets" method="post">

                        <div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1"
                             aria-labelledby="staticBackdropLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="staticBackdropLabel">Tem certeza de que deseja excluir </h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <input type="hidden" name="action" value="deleteProducts">
                                        Tem certeza de que deseja excluir este
                                        produto?
                                        <input type="hidden" name="Idproducts" >
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                        <!-- 
                                            Form para excluir
                                            @button value deve ser definido o id do usuario
                                        -->
                                        <button type="submit" class="btn btn-danger" value="">Excluir</button>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>

                </section>
        </main>
    </body>
</html>
