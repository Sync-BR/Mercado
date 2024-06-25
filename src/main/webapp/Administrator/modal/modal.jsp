<%-- 
    Document   : modal
    Created on : 23 de jun. de 2024, 05:24:28
    Author     : Eduar
--%>

<%@page import="Beans.PostBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Obtém o valor 'postId' definido no Servlet
    String postTitle = (String) request.getAttribute("postTitle");
    String postDescription = (String) request.getAttribute("postDescription");
    String postID = (String) request.getAttribute("postID");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

        <title>editar</title>
    </head>
    <body>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
        <script>
            // Script para abrir o modal automaticamente ao carregar a página
            $(document).ready(function () {
                $('#exampleModal').modal('show');
            });

        </script>

    </button>
    <div class="modal fade modaledit" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Editar Postagem</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <%
                    PostBean addpost = new PostBean();
                %>
                <div class="modal-body">
                    <form class="form" action="${pageContext.request.contextPath}/Post" method="post" enctype="multipart/form-data">

                        <input type="hidden"  id="Iduser" value="<%=postID%>" name="Iduser" style="border: none;" >
                        <input type="hidden" name="action" value="edit"> </input>
                        <div class="form-group">
                            <label for="recipient-name" class="col-form-label">Titulo</label>

                            <input type="text" class="form-control" id="recipient-name" name="titulo" value="<%=postTitle%>">


                        </div>
                        <div class="form-group">
                            <label for="message-text" class="col-form-label">Descrição:</label>
                            <textarea class="form-control" id="message-text"  name="description"><%=postDescription%></textarea>
                        </div>
                        <div class="form-group">
                            <label for="message-text" class="col-form-label">Imagem:</label>
                            <input type="file" class="form-control-file" id="exampleFormControlFile1" name="fileIMG">
                        </div>
                        <button type="submit" class="btn btn-primary" name="inpultText" value="editar">Alterar</button>
                        <button type="button" class="btn btn-primary"  data-toggle="modal" data-target="#modalExemplo" >Apagar</button>


                        <!-- Modal para salvar -->
                        <div class="modal fade" id="modalExemplo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Confirma operação </h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        Tem certeza que desejar apagar esse post 
                                    </div>
                                    <div class="modal-footer">
                                        <button type="submit" class="btn btn-primary"   name="inpultText" value="deletar">Apagar</button>
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>



</body>
</html>
