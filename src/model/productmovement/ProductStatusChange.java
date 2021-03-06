package model.productmovement;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.product.Product;
import model.product.ProductStatus;
import model.request.Request;
import model.user.User;

import org.hibernate.envers.Audited;

import constraintvalidator.ProductExists;
import constraintvalidator.RequestExists;


@Entity
@Audited
public class ProductStatusChange {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false)
  private Long id;

  @ProductExists
  @ManyToOne
  private Product product;

  @Temporal(TemporalType.TIMESTAMP)
  private Date statusChangedOn;

  @Enumerated(EnumType.STRING)
  @Column(length=30)
  private ProductStatusChangeType statusChangeType;
  
  @Enumerated(EnumType.STRING)
  @Column(length=30)
  private ProductStatus newStatus;

  @RequestExists
  @ManyToOne
  private Request issuedTo;

  @ManyToOne
  private User changedBy;

  @Lob
  private String statusChangeReasonText;

  @ManyToOne
  private ProductStatusChangeReason statusChangeReason;

  public ProductStatusChange() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Date getStatusChangedOn() {
    return statusChangedOn;
  }

  public void setStatusChangedOn(Date statusChangedOn) {
    this.statusChangedOn = statusChangedOn;
  }

  public ProductStatus getNewStatus() {
    return newStatus;
  }

  public void setNewStatus(ProductStatus newStatus) {
    this.newStatus = newStatus;
  }

  public Request getIssuedTo() {
    return issuedTo;
  }

  public void setIssuedTo(Request issuedTo) {
    this.issuedTo = issuedTo;
  }

  public User getChangedBy() {
    return changedBy;
  }

  public void setChangedBy(User changedBy) {
    this.changedBy = changedBy;
  }

  public String getStatusChangeReasonText() {
    return statusChangeReasonText;
  }

  public void setStatusChangeReasonText(String statusChangeReasonText) {
    this.statusChangeReasonText = statusChangeReasonText;
  }

  public User getIssuedBy() {
    return this.changedBy;
  }

  public void setIssuedBy(User issuedBy) {
    this.changedBy = issuedBy;
  }

  public Date getIssuedOn() {
    return this.statusChangedOn;
  }

  public void setIssuedOn(Date issuedOn) {
    this.statusChangedOn = issuedOn;
  }

  public ProductStatusChangeReason getStatusChangeReason() {
    return statusChangeReason;
  }

  public void setStatusChangeReason(ProductStatusChangeReason discardReason) {
    this.statusChangeReason = discardReason;
  }

  public ProductStatusChangeType getStatusChangeType() {
    return statusChangeType;
  }

  public void setStatusChangeType(ProductStatusChangeType statusChangeType) {
    this.statusChangeType = statusChangeType;
  }
}
