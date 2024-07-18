package Dao;

import Beans.ProductsBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Conexao;

/**
 * Classe responsável pelas operações com o banco de dados relacionadas aos
 * produtos. Fornece métodos para recuperar e adicionar produtos no banco de
 * dados.
 *
 * @autor SYNC
 */
public class ProductsDao {

    /**
     * @param id Recebe o valor do tipo int para buscar na base de dados.
     * @return Retornar uma array do productsBean com os dados encontrado.
     * @throws java.lang.Exception Faz o tratamento dos possiveis erros
     * @searchID Faz uma busca no banco de dados pelo id.
     * @autor SYNC
     */
    public List<ProductsBean> searchID(int id) throws Exception {
        List<ProductsBean> addproducts = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM mercado.products WHERE idProducts = ?";
        try {
            connection = Conexao.getconnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                String productName = rs.getString("productName");
                String productDescription = rs.getString("productDescription");
                double productValue = rs.getDouble("productValue");
                int productStock = rs.getInt("productStock");
                String productSupplier = rs.getString("productSupplier");
                String productBarcode = rs.getString("productBarcode");
                String productImages = rs.getString("productImages");
                addproducts.add(new ProductsBean(productName, productDescription, productValue, productStock, productSupplier, productBarcode, productImages));
            }
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            Conexao.closeConnection(connection, ps, rs);

        }

        return addproducts;
    }

    /**
     * @param update Instancia a classe ProductsBean
     * @param Type True para atualizar imagem
     * @return
     * @throws java.lang.Exception Faz o tratamento dos possiveis erros
     * @updateProducts faz atualização dos dados no banco de dados.
     * @autor SYNC
     */
    public boolean updateProducts(ProductsBean update, boolean Type) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        String sql;
        boolean statusUpdateProducts = false;

        try {
            if (Type) {
                sql = "Update mercado.products set productName = ?,productDescription = ?,productValue = ?,productStock = ?,productSupplier = ?,                    productBarcode = ?,productImages = ? WHERE  idProducts = ?";

            } else {
                sql = "Update mercado.products set productName = ?,productDescription = ?,productValue = ?,productStock = ?,productSupplier = ?,                    productBarcode = ? WHERE  idProducts = ?";

            }
            connection = Conexao.getconnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, update.getProductName());
            ps.setString(2, update.getProductDescription());
            ps.setDouble(3, update.getProductValue());
            ps.setInt(4, update.getProductStock());
            ps.setString(5, update.getProductSupplier());
            ps.setString(6, update.getProductBarcode());
            if (Type) {
                ps.setString(7, update.getProductImages());
                ps.setInt(8, update.getProductId());
            } else {
                ps.setInt(7, update.getProductId());
            }
            int sucesso = ps.executeUpdate();
            if (sucesso > 0) {
                statusUpdateProducts = true;
            }
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            Conexao.closeConnection(connection, ps);
        }
        return statusUpdateProducts;
    }

    /**
     * @return Retornar uma arrayList com todos os dados
     * @throws java.lang.Exception Faz o tratamento dos possiveis erros
     * @returnProducts Retornar todos produtos.
     * @autor SYNC
     */
    public List<ProductsBean> returnProducts() throws Exception {
        List<ProductsBean> addproducts = new ArrayList();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM mercado.products";
        try {
            connection = Conexao.getconnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("idProducts");
                String productName = rs.getString("productName");
                String productDescription = rs.getString("productDescription");
                double productValue = rs.getDouble("productValue");
                int productStock = rs.getInt("productStock");
                String productSupplier = rs.getString("productSupplier");
                String productBarcode = rs.getString("productBarcode");
                String productImages = rs.getString("productImages");
                addproducts.add(new ProductsBean(productId, productName, productDescription, productValue, productStock, productSupplier, productBarcode, productImages));
            }
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            Conexao.closeConnection(connection, ps, rs);
        }
        return addproducts;

    }

    /**
     * @param rmProducts instancia as variaveis da classe ProductsBean
     * @return Retornar o estado da variaveil removed. Se o produto for removido
     * retornar true ate que prove ao contrario.
     * @throws java.lang.Exception Faz o tratamento dos possiveis erros.
     * @autor SYNC
     */
    public boolean removeProducts(ProductsBean rmProducts) throws Exception {
        boolean removed = false;
        Connection connection = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM mercado.products WHERE idProducts = ?";
        try {
            connection = Conexao.getconnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, rmProducts.getProductId());
            int status = ps.executeUpdate();
            if (status > 0) {
                removed = true;
            }
        } catch (SQLException e) {
            e.getMessage();

        } finally {
            Conexao.closeConnection(connection, ps);
        }

        return removed;
    }

    /**
     * @param add instancia a classe ProductsBean
     * @return retorna o estado da variavel registred, se o registro for bem
     * sucedido retornar true.
     * @throws java.lang.Exception Efetuar os possiveis tratamentos de erros
     * @autor SYNC
     */
    public boolean addProducts(ProductsBean add) throws Exception {
        boolean registred = false;
        Connection connection = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO mercado.products ( productName, productDescription, productValue, productStock, productBarcode,productSupplier, productImages) VALUES (?, ?, ?, ?, ?, ?,?)";
        try {
            connection = Conexao.getconnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, add.getProductName());
            ps.setString(2, add.getProductDescription());
            ps.setDouble(3, add.getProductValue());
            ps.setInt(4, add.getProductStock());
            ps.setString(5, add.getProductBarcode());
            ps.setString(6, add.getProductSupplier());
            ps.setString(7, add.getProductImages());
            int sucesso = ps.executeUpdate();
            if (sucesso > 0) {
                registred = true;
            }

        } catch (SQLException e) {
            e.getMessage();
        } finally {
            Conexao.closeConnection(connection, ps);
        }
        return registred;
    }

    public static void main(String[] args) throws Exception {
        ProductsBean ps = new ProductsBean();
        ProductsDao update = new ProductsDao();
        ps.setProductId(2);
        ps.setProductName("Notebook Dell Inspiron");
        ps.setProductDescription("Notebook Dell Inspiron com processador Intel i7, 16GB RAM, 512GB SSD");
        ps.setProductValue(4999.99);
        ps.setProductStock(25);
        ps.setProductSupplier("Dell Brasil");
        ps.setProductBarcode("1234567890123");
        ps.setProductImages("dell_inspiron.jpg");
        // update.updateProducts(ps);
        /**
         * Locale localeBR = new Locale("pt", "BR"); NumberFormat dinheiro =
         * NumberFormat.getCurrencyInstance(localeBR); ProductsDao register =
         * new ProductsDao(); List<ProductsBean> retornar =
         * register.returnProducts(); for (ProductsBean produtos : retornar) {
         * System.out.println("Nome: " + produtos.getProductName());
         * System.out.println("Descrição: " + produtos.getProductDescription());
         * System.out.println("Quantidade: " + produtos.getProductStock());
         * System.out.println("Valor: " +
         * dinheiro.format(produtos.getProductValue()));
         * System.out.println("Fornecedor: " + produtos.getProductSupplier());
         * System.out.println("Código de barras: " +
         * produtos.getProductBarcode()); System.out.println("Imagem: " +
         * produtos.getProductImages()); }
         */
    }
}
