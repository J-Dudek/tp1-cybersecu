package tp.securite.tp1.dto;

import io.swagger.annotations.ApiModelProperty;

public class BookDataDTO {

    @ApiModelProperty()
    private String auteur;
    @ApiModelProperty(position = 1)
    private String titre;
    @ApiModelProperty(position = 2)
    private int nbpages;
    @ApiModelProperty(position = 4)
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
