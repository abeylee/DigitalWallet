import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
 * Create TransactionException, DigitalWallet, and DigitalWalletTransaction classes here.
 */
class TransactionException extends RuntimeException {
    String errorMessage;
    String errorCode;

    public TransactionException(String errorMessage, String errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.errorMessage;
    }
}

class DigitalWallet {

    String walletId;
    String userName;
    String userAccessCode;
    int walletBalance;

    public DigitalWallet(String walletId, String userName) {
        this.walletId = walletId;
        this.userName = userName;
    } 

    public DigitalWallet(String walletId, String userName, String userAccessCode) {
        this.walletId = walletId;
        this.userName = userName;
        this.userAccessCode = userAccessCode;
    }
    
    public String getWalletId() {
        return walletId;
    }

    public String getUsername() {
        return userName;
    }

    public String getUserAccessCode() {
        return userAccessCode;
    }

    public int getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(int walletBalance){
        this.walletBalance = this.walletBalance + walletBalance;
    }
}


class DigitalWalletTransaction {
    //TransactionException tExcep = new TransactionException();
    public void addMoney(DigitalWallet digitalWallet, int amount){
        checkUserAccess(digitalWallet);
        checkAmount(amount);
        digitalWallet.setWalletBalance(amount);
    }

    public void payMoney(DigitalWallet digitalWallet, int amount){
        checkUserAccess(digitalWallet);
        checkAmount(amount);
        checkBalance(amount, digitalWallet.getWalletBalance());
        digitalWallet.setWalletBalance(-amount);
    }

    private void checkUserAccess(DigitalWallet digitalWallet) throws TransactionException {
        if (digitalWallet.getUserAccessCode() == null || digitalWallet.getUserAccessCode().isEmpty()) {
            throw new TransactionException("User not authorized", "USER_NOT_AUTHORIZED");
        }
    }

    private void checkAmount(int amount) throws TransactionException {
        if (amount <= 0) {
            throw new TransactionException("Amount should be greater than zero", "INVALID_AMOUNT");
        }
    }

    private void checkBalance(int payment, int balance) throws TransactionException {
        if (payment > balance) {
            throw new TransactionException("Insufficient balance", "INSUFFICIENT_BALANCE");
        }
    }
}



public class Solution {
