package com.adidas.controller;

import com.adidas.domain.Inventory;
import com.adidas.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;
import io.prometheus.client.Histogram;
import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;

import java.util.List;


@RestController
public class InventoryController {

	//Histogram
	static final Histogram requestLatency = Histogram.build()
			.name("http_request_duration_seconds")
			.help("HTTP request duration (seconds).")
			.labelNames("method")
			.register();    // Register must be called to add it to the output

	//Counter
	static final Counter requests = Counter.build()
			.name("requests_total")
			.help("Total requests.")
			.labelNames("method")
			.register();

	@Autowired
	InventoryRepository inventoryRepository;
	
	@GetMapping("/inventory")
	public Iterable<Inventory> getInventory() {
		//Counter: added 1 when we access to the /path
		requests.labels("getInventory").inc();
		//Histogram: time request
		Histogram.Timer requestTimer = requestLatency.labels("getInventory").startTimer();

		try {
			return inventoryRepository.findAll();
		} finally {
			// Stop the histogram timer.
			requestTimer.observeDuration();
		}
	}
	
	@GetMapping("/inventory/{modelId}")
	public List<Inventory> getModelInventory(@PathVariable String modelId){
		//Counter: added 1 when we access to the /path
		requests.labels("getModelInventory").inc();
		//Histogram: time request
		Histogram.Timer requestTimer = requestLatency.labels("getModelInventory").startTimer();
		try {
			return inventoryRepository.findByModelId(modelId);
		} finally {
			// Stop the histogram timer.
			requestTimer.observeDuration();
		}
	}


	@GetMapping("/inventory/{modelId}/warehouse/{warehouseId}")
	public  List<Inventory> getModelInventoryByWarehouse(@PathVariable String modelId, @PathVariable String warehouseId){
		//Counter: added 1 when we access to the /path
		requests.labels("getModelInventoryByWarehouse").inc();
		//Histogram: time request
		Histogram.Timer requestTimer = requestLatency.labels("getModelInventoryByWarehouse").startTimer();

		try {
			return inventoryRepository.findByModelIdAndWarehouseId(modelId,warehouseId);
		} finally {
			// Stop the histogram timer.
			requestTimer.observeDuration();
		}
	}


}
