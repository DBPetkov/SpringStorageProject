package com.storageproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "storage")
public class Storage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "storage_id")
	private Long id;
	
	@Column(name = "product_name")
	@NotEmpty(message = "*Please provide a product name!")
	@Length(max=50, message="*The product name length must not exceed 50 characters!")
	private String productName;
	
	@Column(name = "product_description")
	@Length(max=2000, message="The product description length must not exceed 2000 characters!")
	private String productDescription;
	@Column(name = "buy_price")
	
	@NotNull(message="*Please provide a buy price!")
	@Min(0)
	private double buyPrice;
	
	@Column(name = "sell_price")
	@NotNull(message="*Please provide a sell price!")
	@Min(0)
	private double sellPrice;
	
	@Column(name = "product_count")
	@NotNull(message="*Please provide a product count!")
	@Min(0)
	private int productCount;
	
	@Column(name = "product_category")
	@NotEmpty(message="*Please select a product category!")
	private String productCategory;
	
	@Column(name = "product_code")
	@NotNull(message="*Please provide a product code!")
	private int productCode;
	

	// getters and setters for Storage
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public int getProductCode() {
		return productCode;
	}

	public void setProductCode(int productCode) {
		this.productCode = productCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}