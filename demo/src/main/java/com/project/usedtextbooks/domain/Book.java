package com.project.usedtextbooks.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;
    private String authors;
    private String title;
    private String edition;
    private Double price;
    private boolean inStock; // Renamed from 'inventory' and changed type
    private int purchaseCount = 0; // 初始化为0

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        if (isbn != null) {
            this.isbn = isbn.replaceAll("[^0-9]", ""); // Ensure only numbers remain
        } else {
            this.isbn = null;
        }
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        if (price != null) {
            BigDecimal bd = new BigDecimal(price);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            this.price = bd.doubleValue();
        } else {
            this.price = null;
        }
    }

    public boolean isInStock() { // Renamed from 'getInventory'
        return inStock;
    }

    public void setInStock(boolean inStock) { // Renamed from 'setInventory'
        this.inStock = inStock;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", authors='" + authors + '\'' +
                ", title='" + title + '\'' +
                ", edition='" + edition + '\'' +
                ", price=" + price +
                ", inStock=" + inStock + // Updated this line
                '}';
    }
}