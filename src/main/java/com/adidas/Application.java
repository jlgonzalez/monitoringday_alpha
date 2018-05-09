package com.adidas;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import com.adidas.domain.Inventory;
import com.adidas.repository.InventoryRepository;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
// Add a Prometheus metrics enpoint to the route `/prometheus`. `/metrics` is already taken by Actuator.
@EnablePrometheusEndpoint
// Pull all metrics from Actuator and expose them as Prometheus metrics. Must have permission to do this.
@EnableSpringBootMetricsCollector
public class Application {

	@Autowired
	InventoryRepository inventoryRepository;
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @PostConstruct
	public void init() {

		Set<Inventory> set = new HashSet<>();

		set.add(new Inventory("C77124","SuperStar M01" ,"WH1" ,1000));
		set.add(new Inventory("C77125","SuperStar M02" , "WH1" ,2000));
		set.add(new Inventory("C77126","SuperStar M03"  ,"WH1" ,3000));
		set.add(new Inventory("C77127","SuperStar M04"  ,"WH1" ,500));

		set.add(new Inventory("C77124","SuperStar M01" , "WH2" ,100));
		set.add(new Inventory("C77125","SuperStar M02" , "WH2" ,200));
		set.add(new Inventory("C77126","SuperStar M03" , "WH2" ,300));
		set.add(new Inventory("C77127","SuperStar M04" , "WH2" ,0));

		set.add(new Inventory("C77124","SuperStar M01" , "WH3" ,5000));
		set.add(new Inventory("C77125","SuperStar M02" , "WH3" ,400));
		set.add(new Inventory("C77126","SuperStar M03" , "WH3" ,0));
		set.add(new Inventory("C77127","SuperStar M04" , "WH3" ,0));

		inventoryRepository.save(set);
	}
    
}
