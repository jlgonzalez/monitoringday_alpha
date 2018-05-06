package com.adidas.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Inventory {

    @Id
	@GeneratedValue
	Long inventoryId;
	String modelId;
    String modelName;
	String warehouseId;
	int stock;


    public Inventory() {
        super();
    }


    public Inventory(String modelId, String modelName, String warehouseId, int stock) {
        this.modelId = modelId;
        this.modelName = modelName;
        this.stock=stock;
        this.warehouseId = warehouseId;

    }

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }



}
