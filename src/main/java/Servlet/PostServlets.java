package Servlet;

import Beans.PostBean;
import Dao.Postdao;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author SYNC
 */
@MultipartConfig
public class PostServlets extends HttpServlet {

    private static String UploadDirs = "E:/Projetos/Mercado/src/main/webapp/src/img/Posts/";

    protected void uploadIMG(HttpServletRequest request, Part filePart) throws ServletException, IOException {

        String fileName = filePart.getSubmittedFileName();
        InputStream fileContent = filePart.getInputStream();
        if (filePart.getSize() > 0) {
            try {
                Files.copy(fileContent, new File(UploadDirs + fileName).toPath());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            deleteImg(fileName);;

        }

    }

    protected void deleteImg(String name) throws ServletException, IOException {
        Path file = Paths.get(UploadDirs + name);
        System.out.println(file);
        try {
            Files.deleteIfExists(file);
        } catch (IOException e) {
            e.getMessage();
        }

    }

    protected void editPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionDelete = request.getParameter("inpultText");
        PostBean updatePost = new PostBean();
        Postdao update = new Postdao();
        updatePost.setTitle(request.getParameter("titulo"));
        updatePost.setDescription(request.getParameter("description"));
        Part img = request.getPart("fileIMG");
        String id = request.getParameter("Iduser");
        updatePost.setId(Integer.parseInt(id));
        updatePost.setImg(img.getSubmittedFileName());
        boolean status;
        switch (actionDelete) {
            case "deletar":
                deletePost(response, updatePost);
                break;
            case "editar":
                try {
                    String nameImg = update.searchID(updatePost.getId());
                    if (nameImg != null) {
                        try {
                            deleteImg(nameImg);
                        } catch (IOException e) {
                            e.getMessage();
                        }
                    }

                } catch (Exception e) {
                    e.getMessage();
                }

                try {
                    if (img.getSize() > 0) {
                        status = update.updatePost(updatePost, true);
                        if (status) {
                            uploadIMG(request, img);
                        }

                    } else {
                        update.updatePost(updatePost, false);
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
                break;

        }

        RequestDispatcher redirect = request.getRequestDispatcher("Administrator/ManagePost.jsp");
        redirect.forward(request, response);
    }

    protected void deletePost(HttpServletResponse response, PostBean posts) throws ServletException, IOException {
        Postdao operation = new Postdao();
        String nameImg = null;
        boolean Deleted = false;

        try {
            nameImg = operation.searchID(posts.getId());
            if (nameImg != null) {
                Deleted = operation.deletePost(posts);
                if (Deleted) {
                    try {
                        deleteImg(nameImg);
                    } catch (IOException e) {
                        e.getMessage();
                    }
                }
            }

        } catch (Exception e) {
            e.getMessage();
        }

        // response.sendRedirect("Administrator/ManagePost.jsp");
    }

    protected void Select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Titulo = request.getParameter("title");
        String Description = request.getParameter("title");
        String id = request.getParameter("Iduser");
        request.setAttribute("postID", id);
        request.setAttribute("postTitle", Titulo);
        request.setAttribute("postDescription", Description);

        RequestDispatcher dispatcher = request.getRequestDispatcher("Administrator/modal/modal.jsp");
        dispatcher.forward(request, response);

    }

    protected void Newpost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean Uploadsuccess = false;
        // Obtém a parte (part) do arquivo enviado
        Part filePart = request.getPart("files");

        PostBean addPost = new PostBean();
        Postdao cadastrar = new Postdao();
        addPost.setTitle(request.getParameter("titulo"));
        addPost.setDescription(request.getParameter("descricao"));
        addPost.setImg(filePart.getSubmittedFileName());
        try {
            boolean status = cadastrar.Addpost(addPost);
            if (status) {
                uploadIMG(request, filePart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("Administrator/Post.jsp");

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing.");
            return;
        }
        switch (action) {
            case "select":
                Select(request, response);

                break;
            case "newpost":
                Newpost(request, response);
                break;
            case "edit":
                editPost(request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
