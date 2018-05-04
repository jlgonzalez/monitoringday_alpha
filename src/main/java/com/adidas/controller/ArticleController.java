package com.adidas.controller;

import com.adidas.domain.Article;
import com.adidas.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import io.prometheus.client.Histogram;
import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;

@RestController
public class ArticleController {

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
	ArticleRepository articleRepository;
	
	@GetMapping("/articles")
	public Iterable<Article> getArticles() {

		//Counter: added 1 when we access to the /hello
		requests.labels("getArticles").inc();
		//Histogram: time request
		Histogram.Timer requestTimer = requestLatency.labels("getArticles").startTimer();

		try {
			return articleRepository.findAll();
		} finally {
			// Stop the histogram timer.
			requestTimer.observeDuration();
		}
	}
	
	@GetMapping("/articles/{id}")
	public Article getArticle(@PathVariable Long id){
		//Counter: added 1 when we access to the /hello
		requests.labels("getArticle").inc();
		//Histogram: time request
		Histogram.Timer requestTimer = requestLatency.labels("getArticle").startTimer();

		try {
			return articleRepository.findOne(id);
		} finally {
			// Stop the histogram timer.
			requestTimer.observeDuration();
		}
	}
	
}
