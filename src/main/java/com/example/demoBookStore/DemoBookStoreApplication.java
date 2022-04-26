package com.example.demoBookStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;


@SpringBootApplication
public class DemoBookStoreApplication implements CommandLineRunner{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {

		SpringApplication.run(DemoBookStoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String sql = "INSERT INTO allbooks (stname, email, phone, book, author, bookcond, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
//		String pattern = '%' + '%';
//		Object[] params = {9.99f, "Effective Java"};
		int result = jdbcTemplate.update(sql, "qwer", "qwer", "qwer", "qwer", "qwer", "qwer", 456);

		if (result > 0) {
			System.out.println("A new row has been inserted.");
		}
	}
}
