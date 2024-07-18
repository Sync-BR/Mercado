package Dao;

import Beans.PostBean;
import Production.Config;
import Register.Logs;
import java.io.IOException;
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
public class Postdao implements PostInterface {

    //Instanciando classes Logs e Config
    Logs addlogs = new Logs();
    Config config = new Config();

    /**
     *
     * @param id Receber um valor do tipo int para pesquisar a postagem por id
     * @return Retornar o nome da imagem
     * @throws java.lang.Exception Efetuar os possives tratamentos dee erros.
     * @autor SYNC
     * @searchID Buscar o nome da imagem pelo id
     */
    @Override
    public String searchID(int id) throws Exception {
        boolean Found = false;
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
                Found = true;
            }
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            Conexao.closeConnection(connection, ps);
            //Registrar Logs
            try {
                if (Found) {
                    addlogs.registerLogs("Imagem encontrado " + img);
                } else {
                    addlogs.registerLogs("Não foi possivel encontrar a imagem do id " + id);
                }
            } catch (IOException e) {
                addlogs.registerLogs(e.getMessage());
            }
        }
        
        return img;
    }

    /**
     *
     * @param post Instancia a classe PostBeans com seus dados.
     * @return Se deletado retornar true ate que prove ao contrario.
     * @throws java.lang.Exception Faz o tratatamento dos possiveis erros.
     * @autor SYNC
     * @deletePost deletar uma postagem pelo id
     */
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
            try {
                if (status) {
                    addlogs.registerLogs("ID da postagem " + post.getId() + " Apagado com sucesso!");
                } else {
                    addlogs.registerLogs("ID da postagem " + post.getId() + " Falhou ao deletar do banco de dados");
                }
            } catch (IOException e) {
                addlogs.registerLogs(e.getMessage());
                
            }
        }
        
        return status;
    }

    /**
     *
     * @param post Instancias a classe Postbeans e seus dados.
     * @param Type Se true atualizar a imagem, se false não atualizar a imagem.
     * @return Se verdadeiro a operação foi bem sucedida ate que prove ao
     * contrario.
     * @throws java.lang.Exception Tratar os possiveis erros.
     * @autor SYNC
     * @updatePost Efetuar a atualização do banco de dados de acordo com a
     * nescessidade.
     */
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
            
            try {
                if (status) {
                    addlogs.registerLogs("Postagem " + post.getTitle() + " Foi atualizado com sucesso!");
                } else {
                    addlogs.registerLogs("Postagem " + post.getTitle() + " Falhou ao atualizar a postagem.");
                    
                }
            } catch (IOException e) {
                addlogs.registerLogs(e.getMessage());
            }
        }
        return status;
    }

    /**
     *
     * @param title Instancia a classe Postbean
     * @return Retornar um conjunto de dados em arraylist
     * @throws java.lang.Exception Efetuar os possiveis tratamentos de erros.
     * @autor SYNC
     * @SearchTitulo Buscar por um conjunto de dados pelo Titlo.
     */
    @Override
    public List<PostBean> SearchTitulo(PostBean title) throws Exception {
        boolean status = false;
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
                status = true;
            }
            
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            Conexao.closeConnection(connection, ps, rs);
            try {
                if (status) {
                    addlogs.registerLogs("Os dados da postagem " + Returnpost.get(1) + " Foi encontrado!");
                } else {
                    addlogs.registerLogs("Os dados da postagem " + Returnpost.get(1) + " Não foi encontrado!");
                }
            } catch (IOException e) {
                addlogs.registerLogs(e.getMessage());
            }
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
        boolean status = false;
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
                status = true;
            }
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            Conexao.closeConnection(connection, ps, rs);
            try {
                if(status){
                    addlogs.registerLogs("Foi encontrado um conjunto de postagem");
                } else {
                    addlogs.registerLogs("Não foi encontrado nenhuma postagem.");
                }
            } catch (IOException e) {
                addlogs.registerLogs(e.getMessage());
            }
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
        int sucesso = 0;
        String sql = "INSERT INTO mercado.posts (Titulo, Descricao, img) VALUES (?,?,?)";
        System.out.println("Titulo: " +add.getTitle());
        System.out.println("Descrição: "+add.getDescription() );
        try {
            connection = Conexao.getconnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, add.getTitle());
            ps.setString(2, add.getDescription());
            ps.setString(3, add.getImg());
            
             sucesso = ps.executeUpdate();
            if (sucesso > 0) {
                status = true;
            }
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            Conexao.closeConnection(connection, ps);
            try{
                if(sucesso > 0){
                    addlogs.registerLogs("Postagem " +add.getTitle()+ " foi adicionado com sucesso!");
                } else {
                    addlogs.registerLogs("Postagem " +add.getTitle()+ " Falhou ao adicionar nova postagem"
                            + "!");
                }
            } catch(IOException e){
                addlogs.registerLogs(e.getMessage());
            }
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
