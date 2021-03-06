package tp.securite.tp1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min = 4, max = 255, message = "Minimum auteurname length: 4 characters")
    private String auteur;
    @Size(min = 4, max = 255, message = "Minimum titrename length: 4 characters")
    private String titre;
    private int nbpages;

    @ManyToMany(mappedBy = "books",fetch = FetchType.LAZY)
    @JsonIgnoreProperties("users")
    private List<User> users;

    public Book() {
        this.users = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getNbpages() {
        return nbpages;
    }

    public void setNbpages(int nbpages) {
        this.nbpages = nbpages;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id.toString() +
                ", auteur='" + auteur + '\'' +
                ", titre='" + titre + '\'' +
                ", nbpages=" + nbpages +
                ", users=" + users +
                '}';
    }
}
