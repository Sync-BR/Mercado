package Servlet;

import Beans.ProductsBean;
import Dao.Postdao;
import Dao.ProductsDao;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ProductServlets extends HttpServlet {

    Locale localeBR = new Locale("pt", "BR");
    NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
    private static String UploadDirs = "E:/Projetos/Mercado/src/main/webapp/src/img/Posts/Produtos/";

    /**
     *
     * @author SYNC
     * @param deleted Instancia a classe ProductsBean e seus dados
     * @throws java.lang.Exception Tratar os possiveis erros.
     * @Deleteproducts Servlet para deletar produtos pelo id.
     */
    protected void Deleteproducts(ProductsBean deleted) throws Exception {
        ProductsDao deleteProducts = new ProductsDao();
        boolean status = deleteProducts.removeProducts(deleted);
        if (status) {
            System.out.println("Status do delete Sucesso");
        } else {
            System.out.println("Status do delete Falhou");
        }

    }

    protected void uploadIMG(Part filePart) throws ServletException, IOException, Exception {
        String fileName = filePart.getSubmittedFileName();
        InputStream fileContent = filePart.getInputStream();
        if (filePart.getSize() > 0) {
            try {
                Files.copy(fileContent, new File(UploadDirs + fileName).toPath());
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }

    protected void deleteProducts(ProductsBean products) throws ServletException, IOException {
        Postdao operation = new Postdao();
        String nameImg = null;
        boolean Deleted = false;
        /*
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
         */
    }

    protected void editProducts(ProductsBean add, Part img) throws Exception {
        ProductsDao updateProducts = new ProductsDao();
        //Caso a variavel img possuar dados
        if (img.getSize() > 0) {
            boolean status = updateProducts.updateProducts(add, true);
            if (status) {
                uploadIMG(img);
            }
        } else {
            boolean status = updateProducts.updateProducts(add, false);
            if (status) {
                System.out.println("sucesso");
            } else {
                System.out.println("Falhou");
            }

        }

        

    }

    protected void addProducts(ProductsBean add, Part file) throws ServletException, IOException, Exception {
        ProductsDao registerProducts = new ProductsDao();
        

        boolean registred = registerProducts.addProducts(add);
        if (registred) {
            uploadIMG(file);
        } else {
            System.out.println("Falhou");
        }
        

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        ProductsBean addProducts = new ProductsBean();
        String action = request.getParameter("action");

        switch (action) {
            case "newProducts":
                addProducts.setProductName(request.getParameter("Name"));
                addProducts.setProductDescription(request.getParameter("Description"));
                String estoque = request.getParameter("Amount");
                int estoqueInt = Integer.parseInt(estoque);
                addProducts.setProductStock(estoqueInt);
                String valor = request.getParameter("Value");
                double valorDoub = Double.parseDouble(valor);
                addProducts.setProductValue(valorDoub);
                addProducts.setProductSupplier(request.getParameter("supplier"));
                addProducts.setProductBarcode(request.getParameter("barcode"));
                String produtoImg = request.getPart("img").getSubmittedFileName();
                addProducts.setProductImages(produtoImg);
                Part productImages = request.getPart("img");
                addProducts(addProducts, productImages);
                response.sendRedirect("Administrator/Products.jsp");
                break;
            case "editProduct":
                Part editProductImages = request.getPart("Productimages");
                String idSTR = request.getParameter("Productid");
                String stockSTR = request.getParameter("ProductStock");
                String valorSTR = request.getParameter("Productvalue");
                String[] separate = valorSTR.split(",");

                int id = 0;
                int stock = 0;
                double valorDBE = 0;
                try {
                    id = Integer.parseInt(idSTR);
                    stock = Integer.parseInt(stockSTR);
                    valorDBE = Double.parseDouble(separate[0] + "." + separate[1]);
                } catch (NumberFormatException e) {
                    e.getMessage();
                }
                //Atribuir os valores para a classe  ProductsBean
                addProducts.setProductId(id);
                addProducts.setProductName(request.getParameter("Productname"));
                addProducts.setProductDescription(request.getParameter("Productdescription"));
                addProducts.setProductStock(stock);
                addProducts.setProductValue(valorDBE);
                addProducts.setProductBarcode(request.getParameter("Productbarcode"));
                addProducts.setProductSupplier(request.getParameter("Productsupplier"));
                addProducts.setProductImages(editProductImages.getSubmittedFileName());
                editProducts(addProducts, editProductImages);
                response.sendRedirect("Administrator/Manageproducts.jsp");
                break;
            case "deleteProducts":
                String deleteID = request.getParameter("Idproducts");
                int idConvertido = 0;
                try {
                    deleteID = deleteID.trim();
                    idConvertido = Integer.parseInt(deleteID);
                } catch (NumberFormatException e) {
                    System.out.println("Erro ao converter o ID: " + e.getMessage());
                }

                System.out.println("ID STR" + deleteID);

                addProducts.setProductId(idConvertido);
                System.out.println("ID SELECIONADO: " + addProducts.getProductId());
                Deleteproducts(addProducts);
                response.sendRedirect("Administrator/Manageproducts.jsp");
                break;
            default:
                throw new AssertionError();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ProductServlets.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ProductServlets.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
