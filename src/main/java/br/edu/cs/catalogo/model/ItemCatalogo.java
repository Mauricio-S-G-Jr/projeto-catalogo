package br.edu.cs.catalogo.model;

public class ItemCatalogo {
    private Long id;
    private String title;
    private String authorDirector;
    private int publicationYear;
    private String genre;
    private String synopsis;

    public ItemCatalogo() {
    }

    public ItemCatalogo(Long id, String title, String authorDirector, int publicationYear, String genre, String synopsis) {
        this.id = id;
        this.title = title;
        this.authorDirector = authorDirector;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.synopsis = synopsis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorDirector() {
        return authorDirector;
    }

    public void setAuthorDirector(String authorDirector) {
        this.authorDirector = authorDirector;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
}
