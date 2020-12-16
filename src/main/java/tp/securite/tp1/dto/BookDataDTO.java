package tp.securite.tp1.dto;

import io.swagger.annotations.ApiModelProperty;

public class BookDataDTO {

    @ApiModelProperty(position = 0)
    private String auteur;
    @ApiModelProperty(position = 1)
    private String titre;
    @ApiModelProperty(position = 2)
    private int nbpages;

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
}
