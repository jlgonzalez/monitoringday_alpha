package com.adidas;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import com.adidas.domain.Article;
import com.adidas.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;

@SpringBootApplication
// Add a Prometheus metrics enpoint to the route `/prometheus`. `/metrics` is already taken by Actuator.
@EnablePrometheusEndpoint
// Pull all metrics from Actuator and expose them as Prometheus metrics. Must have permission to do this.
@EnableSpringBootMetricsCollector
public class Application {

	@Autowired
	ArticleRepository articleRepository;
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @PostConstruct
	public void init() {

		Set<Article> set = new HashSet<>();
		set.add(new Article("Stan Smith", "001.jpg" ,"120"));
		set.add(new Article("NPM", "002.jpg" ,"200"));
		set.add(new Article("Gazelle", "003.jpg" ,"90"));
		articleRepository.save(set);
	}    
    
}
