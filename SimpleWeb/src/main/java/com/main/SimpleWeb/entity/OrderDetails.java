package com.main.SimpleWeb.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
public class OrderDetails{

	@Id
	@GeneratedValue
	@JsonProperty(required=false)
	private int id;
	
	@JsonProperty("supplierId")
    private String supplierId;
	
	@JsonProperty("supplierName")
    private String supplierName;
	
	@JsonProperty("supplierUrl")
	private String supplierUrl;

    // Default constructor is needed to de-serialize JSON
    public OrderDetails() {
    }

    public OrderDetails(String supplierId, String supplierName,  String supplierUrl) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierUrl = supplierUrl;
    }

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierUrl() {
		return supplierUrl;
	}

	public void setSupplierUrl(String supplierUrl) {
		this.supplierUrl = supplierUrl;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CrawlSupplierData [supplierId=" + supplierId + ", supplierName=" + supplierName + ", supplierUrl=" + supplierUrl + "]";
	}

}

