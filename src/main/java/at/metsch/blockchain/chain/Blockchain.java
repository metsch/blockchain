package at.metsch.blockchain.chain;

//https://medium.com/@kevalpatel2106/how-to-make-the-perfect-singleton-de6b951dfdb0

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import at.metsch.blockchain.block.Block;
import at.metsch.blockchain.block.Transaction;

public class Blockchain implements Serializable {

    private static volatile Blockchain INSTANCE;

    ArrayList<Block> chain;
    ArrayList<Transaction> openTransactions;

    // TODO: bessere Lösung finden
    int difficulty;
    String difficultyStr;

    private Blockchain() {

        if (INSTANCE != null)
            throw new RuntimeException("Use getInstance() to create an instance of Blockchain");

        chain = new ArrayList<Block>();
        mineGenesisBlock();

        difficulty = 4;
        difficultyStr = "0000";
    }

    public static Blockchain getInstance() {
        if (INSTANCE == null) {
            synchronized (Blockchain.class) {
                if (INSTANCE == null)
                    INSTANCE = new Blockchain();
            }
        }
        return INSTANCE;
    }

    public Blockchain(ArrayList<Block> chain) {
        this.chain = chain;
    }

    public synchronized boolean mineNewBlock(int proofOfWork, byte[] parentHash) {
        long timestamp = Instant.now().getEpochSecond();

        Block block = new Block(chain.size() + 1, timestamp, proofOfWork, parentHash, openTransactions);
        openTransactions.clear();

        chain.add(block);

        return true;
    }

    private boolean mineGenesisBlock() {

        String msg = "metsch";
        byte[] genesisHash = DigestUtils.sha256(msg.toString().getBytes(StandardCharsets.UTF_8));

        mineNewBlock(4995, genesisHash);
        return true;
    }


    public int proofOfWork(int previousProof) {
        int proof = 0;

        while (!isValidProof(previousProof, proof)) {
                proof += 1;
        }
        return proof;
    }

    public boolean isValidProof(Integer previousProof, Integer proof) {
        String concated = previousProof.toString() + proof.toString();

        String guessHash = DigestUtils.sha256Hex(concated);

        return StringUtils.substring(guessHash, 0, difficulty).equals(difficultyStr);
    }

    public Block getLastBlock() {
        return chain.get(chain.size() - 1);
    }
}