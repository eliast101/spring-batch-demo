package com.elias.batch;

import com.elias.batch.entity.Country;
import com.elias.batch.repository.CountryRepository;
import jakarta.annotation.PostConstruct;
import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBatchDemoApplication {

	@Autowired
	private CountryRepository countryRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchDemoApplication.class, args);
	}

	/*@PostConstruct
	public void initData() {
		Country country1 = new Country();
		Country country2 = new Country();
		Country country3 = new Country();
		Country country4 = new Country();
		Country country5 = new Country();
		Country country6 = new Country();
		Country country7 = new Country();
		Country country8 = new Country();
		country1.setName("Ethiopia");
		country2.setName("Eritrea");
		country3.setName("United States");
		country4.setName("Canada");
		country5.setName("Ukraine");
		country6.setName("Russia");
		country7.setName("Romania");
		country8.setName("Poland");

		countryRepository.save(country1);
		countryRepository.save(country2);
		countryRepository.save(country3);
		countryRepository.save(country4);
		countryRepository.save(country5);
		countryRepository.save(country6);
		countryRepository.save(country7);
		countryRepository.save(country8);

	}*/

}
