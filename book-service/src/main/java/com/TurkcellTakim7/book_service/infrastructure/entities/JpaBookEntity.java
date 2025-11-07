package com.TurkcellTakim7.book_service.infrastructure.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class JpaBookEntity {

  @Id
  @Column(name = "id", nullable = false, updatable = false)
  private UUID id;

  @Column(name = "title", nullable = false, length = 255)
  private String title;

  @Column(name = "category_id", nullable = false)
  private UUID categoryId;

  @Column(name = "available_copies", nullable = false)
  private int availableCopies;

  @Column(name = "copies_count", nullable = false)
  private int copiesCount;

  @Column(name = "isbn", nullable = false, unique = true, length = 20)
  private String isbn;

  @Column(name = "publisher_id", nullable = false)
  private UUID publisherId;

  @Column(name = "publish_year", nullable = false)
  private int publishYear;

  public JpaBookEntity() {
  }

  public JpaBookEntity(UUID id, String title, UUID categoryId, int availableCopies, int copiesCount,
      String isbn, UUID publisherId, int publishYear) {
    this.id = id;
    this.title = title;
    this.categoryId = categoryId;
    this.availableCopies = availableCopies;
    this.copiesCount = copiesCount;
    this.isbn = isbn;
    this.publisherId = publisherId;
    this.publishYear = publishYear;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public UUID getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(UUID categoryId) {
    this.categoryId = categoryId;
  }

  public int getAvailableCopies() {
    return availableCopies;
  }

  public void setAvailableCopies(int availableCopies) {
    this.availableCopies = availableCopies;
  }

  public int getCopiesCount() {
    return copiesCount;
  }

  public void setCopiesCount(int copiesCount) {
    this.copiesCount = copiesCount;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public UUID getPublisherId() {
    return publisherId;
  }

  public void setPublisherId(UUID publisherId) {
    this.publisherId = publisherId;
  }

  public int getPublishYear() {
    return publishYear;
  }

  public void setPublishYear(int publishYear) {
    this.publishYear = publishYear;
  }
}
