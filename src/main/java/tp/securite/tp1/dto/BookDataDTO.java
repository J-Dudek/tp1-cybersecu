package tp.securite.tp1.dto;

import io.swagger.annotations.ApiModelProperty;

public class BookDataDTO {

    @ApiModelProperty()
    private String auteur;
    @ApiModelProperty(position = 1)
    private String titre;
    @ApiModelProperty(position = 2)
    private int nbpages;
    @ApiModelProperty(position = 3)
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
