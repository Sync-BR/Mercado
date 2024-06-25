package Dao;

import Beans.PostBean;
import Production.Config;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.Conexao;

/**
 * Classe responsável pelas operações com o banco de dados relacionadas aos
 * posts. Fornece métodos para recuperar e adicionar posts no banco de dados.
 *
 * @autor SYNC
 */
public class Postdao implements PostdaoInterface {

    Config config = new Config();

    public String searchID(int id) throws Exception {
        Connection connection = null;
        String img = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM mercado.posts WHERE id = ?";
        try {
            connection = Conexao.getconnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                img = rs.getString("img");
                System.out.println(img);
            } else{
                System.out.println("nao encontrado.");
            }

        } catch (SQLException e) {
            e.getMessage();
        } finally {
            Conexao.closeConnection(connection, ps);
        }

        return img;
    }

    @Override
    public boolean deletePost(PostBean post) throws Exception {
        boolean status = false;
        Connection connection = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM mercado.posts WHERE id = ?";
        try {
            connection = Conexao.getconnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, post.getId());
            int deletado = ps.executeUpdate();
            if (deletado > 0) {
                status = true;
            }

        } catch (SQLException e) {
            e.getMessage();
        } finally {
            Conexao.closeConnection(connection, ps);
        }

        return status;
    }

    //Editar dados
    @Override
    public boolean updatePost(PostBean post, boolean Type) throws Exception {
        boolean status = false;
        Connection connection = null;
        PreparedStatement ps = null;
        String sql;
        if (Type) {
            sql = "UPDATE mercado.posts SET Titulo =? , Descricao =? , img =?  WHERE id = ?";
        } else {
            sql = "UPDATE mercado.posts SET Titulo =? , Descricao =?   WHERE id = ?";
        }
        try {
            connection = Conexao.getconnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getDescription());
            if (Type) {
                ps.setString(3, post.getImg());
                ps.setInt(4, post.getId());
            } else {
                ps.setInt(3, post.getId());
            }
            int update = ps.executeUpdate();
            if (update > 0) {
                status = true;
            }
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            Conexao.closeConnection(connection, ps);
        }
        return status;
    }

    //Buscar por Titulo
    @Override
    public List<PostBean> SearchTitulo(PostBean title) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        List<PostBean> Returnpost = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT * FROM mercado.posts where Titulo = ? ";

        try {
            connection = Conexao.getconnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, title.getTitle());
            rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String Title = rs.getString("Titulo");
                String Description = rs.getString("Descricao");
                String Img = rs.getString("img");
                Returnpost.add(new PostBean(id, Title, Description, Img));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(connection, ps, rs);
        }
        return Returnpost;

    }

    /**
     * Retorna uma lista de todos as postagem do banco de dados.
     *
     * @return Lista de objetos {@link PostBean} contendo todos os posts do
     * banco de dados.
     * @throws Exception Se ocorrer algum erro ao acessar o banco de dados.
     */
    @Override
    public List<PostBean> Returnpost() throws Exception {
        List<PostBean> post = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "Select * From mercado.posts";
        try {
            connection = Conexao.getconnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("Titulo");
                String descricao = rs.getString("Descricao");
                String img = rs.getString("img");
                post.add(new PostBean(id, titulo, descricao, img)); // Adicionar os dados no array.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(connection, ps, rs);
        }
        return post;
    }

    /**
     * Adiciona novas postagem no banco de dados.
     *
     * @param add Instância da classe {@link PostBean} contendo os dados das
     * novas postagem.
     * @return true se a postagem foi adicionada com sucesso, caso contrário
     * false.
     * @throws Exception Se ocorrer algum erro ao acessar o banco de dados.
     */
    @Override
    public boolean Addpost(PostBean add) throws Exception {
        System.out.println("Cheguei aqui");
        boolean status = false;
        Connection connection = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO mercado.posts (Titulo, Descricao, img) VALUES (?,?,?)";
        try {
            connection = Conexao.getconnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, add.getTitle());
            ps.setString(2, add.getDescription());
            ps.setString(3, add.getImg());

            int sucesso = ps.executeUpdate();
            if (sucesso > 0) {
                status = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.closeConnection(connection, ps);
        }
        return status;
    }

    public static void main(String[] args) throws Exception {
        PostBean add = new PostBean();
        Postdao Return = new Postdao();
        add.setTitle("Descubra o Futuro com Nosso Software Inovador!");
        Return.SearchTitulo(add);

    }

}
