package io.pivotal.httpconfig;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@ComponentScan(basePackages={"io.pivotal.httpconfig.config"})
@SpringBootApplication
public class HttpConfigDemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(HttpConfigDemoApplication.class, args);

		if (context.containsBean("pooledRestTemplate")) System.out.println("context contains our pooled RestTemplate bean");
		else System.out.println("context does NOT contain our pooled RestTemplate bean");

		if (context.containsBean("unpooledRestTemplate")) System.out.println("context contains our unpooled RestTemplate bean");
		else System.out.println("context does NOT contain our unpooled RestTemplate bean");
	}

	@Autowired
	RestTemplate pooledRestTemplate;

	@Autowired
	RestTemplate unpooledRestTemplate;

	public void run(String... args) {
		List<String> sites = new ArrayList<>();
		sites.add("https://google.com");
		sites.add("https://youtube.com");
		sites.add("https://facebook.com");
		sites.add("https://en.wikipedia.org");
		sites.add("https://amazon.com");
		sites.add("https://yahoo.com");
		sites.add("https://twitter.com");
		sites.add("https://blogspot.com");
		sites.add("https://netflix.com");
		sites.add("https://linkedin.com");
		sites.add("https://twitch.tv");
		sites.add("https://ebay.com");
		sites.add("https://office.com");
		sites.add("https://microsoft.com");
		sites.add("https://bing.com");
		sites.add("https://espn.com");
		sites.add("https://paypal.com");
		sites.add("https://imgur.com");
		sites.add("https://walmart.com");
		sites.add("https://craigslist.org");
		sites.add("https://chase.com");
		sites.add("https://pinterest.com");
		sites.add("https://cnn.com");
		sites.add("https://imdb.com");
		sites.add("https://tumblr.com");
		sites.add("https://nytimes.com");
		sites.add("https://wikia.com");
		sites.add("https://etsy.com");
		sites.add("https://salesforce.com");
		sites.add("https://github.com");
		sites.add("https://hulu.com");
		sites.add("https://spotify.com");

		sites.forEach((site) -> {
			System.out.println("site: " + site);
		});

		final boolean usePooledRT = (args[0].equalsIgnoreCase("pooled")) ? true : false;
		System.out.println("args[0]: " + args[0] + " / usePooledRT: " + usePooledRT);

		for (int i = 0; i < 5; i++) {
			List<Long> times = sites.stream()
				.map((site) -> {
					long start = System.currentTimeMillis();
					System.out.print(".");
					if (usePooledRT) {
						pooledRestTemplate.getForEntity(site, String.class);
					}
					else {
						unpooledRestTemplate.getForEntity(site, String.class);
					}
					return (System.currentTimeMillis() - start);
				})
				.collect(Collectors.toList());

			System.out.println("");
			System.out.println("----- run #" + i + ": ");
			System.out.println("raw time data (in millis): " + times);
			System.out.println("total time in millis: " + times.stream().map((time) -> time).reduce(0L, (a,b) -> a+b));
//			try { Thread.sleep(1000); } catch (InterruptedException ie) { Thread.dumpStack(); }
		}
	}


}

