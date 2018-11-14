package at.metsch.blockchain.main;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Base64;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import at.metsch.blockchain.model.Block;
import at.metsch.blockchain.model.Transaction;

@Component
public class GenesisRunner implements ApplicationRunner{
    private static final Logger logger = LoggerFactory.getLogger(GenesisRunner.class);

    Block genesis;
    
    @Override
    public void run(ApplicationArguments args) {
        logger.info("GenesisRunner Started");
        genesis=genesisBlock();
    }

    private Block genesisBlock() {
        ArrayList<Transaction> txs = new ArrayList<Transaction>();

        String parentConcatenation ="0";
        byte[] parentHash = DigestUtils.sha256(parentConcatenation.getBytes(StandardCharsets.UTF_8));

        Block genesis = new Block(0, 22222L, 100, parentHash, txs);
        return genesis;
    }
}