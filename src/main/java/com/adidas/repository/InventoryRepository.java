package com.adidas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.adidas.domain.Inventory;

import java.util.List;

@RestResource(path="inventory", rel="inventory")
public interface InventoryRepository extends CrudRepository<Inventory,Long>{

    List<Inventory> findByModelId(String modelId);
    List<Inventory> findByModelIdAndWarehouseId(String warehouseId, String modelId);


}
