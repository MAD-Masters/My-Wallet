package com.example.mywallet.Model;

public class Wallet {
    private int walletId;
    private String walletName;
    private String bank;

    public Wallet() {
    }

    public Wallet(int walletId, String walletName, String bank) {
        this.walletId = walletId;
        this.walletName = walletName;
        this.bank = bank;
    }

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
