package com.project.usedtextbooks.dto;

public class BookInventoryDTO {

    private String isbn;
    private String authors;
    private String title;
    private String edition;
    private int totalInventory;

    // Constructors
    public BookInventoryDTO() {
    }

    public BookInventoryDTO(String isbn, String authors, String title, String edition, int totalInventory) {
        this.isbn = isbn;
        this.authors = authors;
        this.title = title;
        this.edition = edition;
        this.totalInventory = totalInventory;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public int getTotalInventory() {
        return totalInventory;
    }

    public void setTotalInventory(int totalInventory) {
        this.totalInventory = totalInventory;
    }

    // Getters and setters...

    // ... other methods (if necessary) ...
}
