package Beans;

import Dao.Postdao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author SYNC
 * @PostBean classe responsavel por todas operações de postagem
 */
@ManagedBean(name = "Addpost")
@ViewScoped
@RequestScoped
public class PostBean {


    private int id;
    private String Title;
    private String Description;
    private String Img;
    private UploadedFile  filePart;

    public PostBean() {
    }
    
    public PostBean(int id,String title, String descricao, String img){
        this.id = id;
        this.Title = title;
        this.Description = descricao;
        this.Img = img;
    }
    
    /**
     *
     * @author SYNC
     * @throws java.lang.Exception Tratamento de possiveis erros.
     * @Addpost Funcionalidade para adicionar uma nova postagem.
     * 
     */
    public void Addposts() throws Exception {
        //Chamar as classes
        PostBean addPost = new PostBean();
        Postdao CadPost = new Postdao();
        //Obter os dados do website
        addPost.setTitle(Title);
        addPost.setDescription(Description);
        addPost.setFilePart(filePart);
        System.out.println("Titulo: " +Title);
        System.out.println("Descrição: " +Description);
        System.out.println("Titulo: " +filePart);
    }




    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String Img) {
        this.Img = Img;
    }

    public UploadedFile  getFilePart() {
        return filePart;
    }

    public void setFilePart(UploadedFile  filePart) {
        this.filePart = filePart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
