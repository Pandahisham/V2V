package model;

import java.util.Date;
import java.util.List;

public class CollectionBackingForm {
  private Collection collection;
  private List<String> centers;
  private List<String> sites;
  private String dateCollectedFrom;
  private String dateCollectedTo;

  public CollectionBackingForm() {
    collection = new Collection();
  }

  public CollectionBackingForm(Collection collection) {
    this.collection = collection;
  }

  public void copy(Collection collection) {
    collection.copy(collection);
  }

  public boolean equals(Object obj) {
    return collection.equals(obj);
  }

  public Long getCollectionId() {
    return collection.getCollectionId();
  }

  public Long getCenterId() {
    return collection.getCenterId();
  }

  public Long getSiteId() {
    return collection.getSiteId();
  }

  public Date getDateCollected() {
    return collection.getDateCollected();
  }

  public String getSampleNumber() {
    Long sampleNumber = collection.getSampleNumber();
    return (sampleNumber == null) ? null : sampleNumber.toString();
  }

  public String getShippingNumber() {
    Long shippingNumber = collection.getShippingNumber();
    return (shippingNumber == null) ? null : shippingNumber.toString();
  }

  public String getDonorNumber() {
    return collection.getDonorNumber();
  }

  public String getComments() {
    return collection.getComments();
  }

  public String getCollectionNumber() {
    return collection.getCollectionNumber();
  }

  public String getDonorType() {
    return collection.getDonorType();
  }

  public Boolean getIsDeleted() {
    return collection.getIsDeleted();
  }

  public String getAbo() {
    return collection.getAbo();
  }

  public String getRhd() {
    return collection.getRhd();
  }

  public int hashCode() {
    return collection.hashCode();
  }

  public void setCollectionNumber(String collectionNumber) {
    collection.setCollectionNumber(collectionNumber);
  }

  public void setCenterId(Long centerId) {
    collection.setCenterId(centerId);
  }

  public void setSiteId(Long siteId) {
    collection.setSiteId(siteId);
  }

  public void setDateCollected(Date dateCollected) {
    collection.setDateCollected(dateCollected);
  }

  public void setSampleNumber(String sampleNumber) {
    if (sampleNumber == null)
      collection.setSampleNumber((long)0);
    else
      collection.setSampleNumber(Long.parseLong(sampleNumber));
  }

  public void setShippingNumber(String shippingNumber) {
    if (shippingNumber == null)
      collection.setShippingNumber((long)0);
    else
      collection.setShippingNumber(Long.parseLong(shippingNumber));
  }

  public void setDonorNumber(String donorNumber) {
    collection.setDonorNumber(donorNumber);
  }

  public void setComments(String comments) {
    collection.setComments(comments);
  }

  public void setIsDeleted(Boolean isDeleted) {
    collection.setIsDeleted(isDeleted);
  }

  public void setAbo(String abo) {
    collection.setAbo(abo);
  }

  public void setRhd(String rhd) {
    collection.setRhd(rhd);
  }

  public String toString() {
    return collection.toString();
  }

  public List<String> getCenters() {
    return centers;
  }

  public void setCenters(List<String> centers) {
    this.centers = centers;
  }

  public List<String> getSites() {
    return sites;
  }

  public void setSites(List<String> sites) {
    this.sites = sites;
  }

  public void setDonorType(String donorType) {
    collection.setDonorType(donorType);
  }

  public String getDateCollectedFrom() {
    return dateCollectedFrom;
  }

  public void setDateCollectedFrom(String dateCollectedFrom) {
    this.dateCollectedFrom = dateCollectedFrom;
  }

  public String getDateCollectedTo() {
    return dateCollectedTo;
  }

  public void setDateCollectedTo(String dateCollectedTo) {
    this.dateCollectedTo = dateCollectedTo;
  }

  public Collection getCollection() {
    return this.collection;
  }
}
