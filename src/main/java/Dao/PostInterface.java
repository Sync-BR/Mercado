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
public interface PostInterface {

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

    /**
     *
     * @param title Instancia a classe Postbean
     * @return Retornar um conjunto de dados em arraylist
     * @throws java.lang.Exception Efetuar os possiveis tratamentos de erros.
     * @autor SYNC
     * @SearchTitulo Buscar por um conjunto de dados pelo Titlo.
     */
    List<PostBean> SearchTitulo(PostBean title) throws Exception;

    /**
     *
     * @param post Instancia a classe PostBeans com seus dados.
     * @return Se deletado retornar true ate que prove ao contrario.
     * @throws java.lang.Exception Faz o tratatamento dos possiveis erros.
     * @autor SYNC
     * @deletePost deletar uma postagem pelo id
     */
    boolean deletePost(PostBean post) throws Exception;

    /**
     *
     * @param id Receber um valor do tipo int para pesquisar a postagem por id
     * @return Retornar o nome da imagem
     * @throws java.lang.Exception Efetuar os possives tratamentos dee erros.
     * @autor SYNC
     * @searchID Buscar o nome da imagem pelo id
     */
    String searchID(int id) throws Exception;

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
    boolean updatePost(PostBean post, boolean Type) throws Exception;
    
}
