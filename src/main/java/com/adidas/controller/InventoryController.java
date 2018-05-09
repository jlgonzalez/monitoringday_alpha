package com.adidas.controller;

import com.adidas.domain.Inventory;
import com.adidas.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;
import io.prometheus.client.Histogram;
import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;

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
	static final Counter requestsCounter = Counter.build()
			.name("requests_total")
			.help("Total requests.")
			.labelNames("method")
			.register();

	private static final Log log = LogFactory.getLog(InventoryController.class);

	@Autowired
	InventoryRepository inventoryRepository;


	@Autowired
	RestTemplate restTemplate;
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@GetMapping("/inventory")
	public Iterable<Inventory> getInventory() {
		//Counter: added 1 when we access to the /path
		requestsCounter.labels("getInventory").inc();
 		//Histogram: time request
		Histogram.Timer requestTimer = requestLatency.labels("getInventory").startTimer();
		try {
			return inventoryRepository.findAll();
		} finally {
			//Histogram:  Stop the histogram timer.
			requestTimer.observeDuration();
		}
	}
	
	@GetMapping("/inventory/{modelId}")
	public List<Inventory> getModelInventory(@PathVariable String modelId){
		//Counter: added 1 when we access to the /path
		requestsCounter.labels("getModelInventory").inc();
 		//Histogram: time request
		Histogram.Timer requestTimer = requestLatency.labels("getModelInventory").startTimer();
		try {
			return inventoryRepository.findByModelId(modelId);
		} finally {
			//Histogram:  Stop the histogram timer.
			requestTimer.observeDuration();
		}
	}

	@GetMapping("/inventory/{modelId}/warehouse/{warehouseId}")
	public  List<Inventory> getModelInventoryByWarehouse( @PathVariable String modelId, @PathVariable String warehouseId){
		//Counter: added 1 when we access to the /path
		requestsCounter.labels("getModelInventoryByWarehouse").inc();
 		//Histogram: time request
		Histogram.Timer requestTimer = requestLatency.labels("getModelInventoryByWarehouse").startTimer();
		try {
			return inventoryRepository.findByModelIdAndWarehouseId(modelId,warehouseId);
		} finally {
			//Histogram: Stop the histogram timer.
			requestTimer.observeDuration();
		}
	}


}
