package at.metsch.blockchain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BlockchainApplication {
	private static final Logger logger = LoggerFactory.getLogger(BlockchainApplication.class);

	public static void main(String[] args) {


		SpringApplication.run(BlockchainApplication.class, args);
		logger.info("This is the main call");

	}
}
