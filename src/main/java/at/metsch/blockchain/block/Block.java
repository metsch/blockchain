package at.metsch.blockchain.block;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.codec.binary.Hex;

public class Block implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(Block.class);

    private final byte[] parentHash;
    private final byte[] hash;
    private final Long timestamp;
    private final ArrayList<Transaction> transactions;
    private int index;
    private int proofOfWork;


    public Block(int index, Long timestamp, int proofOfWork, byte[] parentHash, ArrayList<Transaction> transactions) {
        this.index = index;
        this.timestamp = timestamp;
        this.proofOfWork = proofOfWork;
        this.parentHash = parentHash;
        this.transactions = transactions;
        hash = computeHash();
    }

    private byte[] computeHash() {
        StringBuilder concatenation = new StringBuilder();

        concatenation.append(index);
        concatenation.append(timestamp);
        concatenation.append(proofOfWork);
        concatenation.append(Hex.encodeHexString(parentHash));

        for (Transaction tx : transactions) {
            concatenation.append(tx.toString());
        }
        
        byte[] blockHash = DigestUtils.sha256(concatenation.toString().getBytes(StandardCharsets.UTF_8));

        logger.info("Block: {} concatenation: {}", index, concatenation.toString());
        logger.info("Block: {} with hash: {}", index, Hex.encodeHexString(blockHash));

        return blockHash;
    }

    public byte[] getParentHash() {
        return this.parentHash;
    }

    public byte[] getHash() {
        return this.hash;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public ArrayList<Transaction> getTransactions() {
        return this.transactions;
    }

    public int getIndex() {
        return this.index;
    }

    public int getProofOfWork() {
        return this.proofOfWork;
    }
}