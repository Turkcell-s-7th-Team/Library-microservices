package com.TurkcellTakim7.publisher_service.infrastructure.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "publishers")
public class JpaPublisherEntity {

  @Id
  @Column(name = "id", nullable = false, updatable = false)
  private UUID id;

  @Column(name = "publisherName", nullable = false, length = 100)
  private String publisherName;

  @Column(name = "address", nullable = false, length = 150)
  private String address;

  public JpaPublisherEntity() {
  }

  public JpaPublisherEntity(UUID id, String publisherName, String address) {
    this.id = id;
    this.publisherName = publisherName;
    this.address = address;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPublisherName() {
    return publisherName;
  }

  public void setPublisherName(String publisherName) {
    this.publisherName = publisherName;
  }
}
