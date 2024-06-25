/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Beans.PostBean;
import java.util.List;

/**
 *
 * @author Eduar
 */
public interface PostdaoInterface {

    /**
     * Adiciona novas postagem no banco de dados.
     *
     * @param add Instância da classe {@link PostBean} contendo os dados das
     * novas postagem.
     * @return true se a postagem foi adicionada com sucesso, caso contrário
     * false.
     * @throws Exception Se ocorrer algum erro ao acessar o banco de dados.
     */
    boolean Addpost(PostBean add) throws Exception;

    /**
     * Retorna uma lista de todos as postagem do banco de dados.
     *
     * @return Lista de objetos {@link PostBean} contendo todos os posts do
     * banco de dados.
     * @throws Exception Se ocorrer algum erro ao acessar o banco de dados.
     */
    List<PostBean> Returnpost() throws Exception;

    //Buscar por Titulo
    List<PostBean> SearchTitulo(PostBean title) throws Exception;

    boolean deletePost(PostBean post) throws Exception;

    //Editar dados
    boolean updatePost(PostBean post, boolean Type) throws Exception;
    
}
