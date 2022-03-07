package main;

import bKash_frontend.Bkash;
import bkash_backend.BkashAccount;

public class BkashSystem {

	private final static int MAX_ACCOUNT = 5;

	public static void main(String[] args) {
		boolean accountChangeRequested = false;
		BkashAccount[] accounts = new BkashAccount[MAX_ACCOUNT];
		Bkash bKash = new Bkash();
		do {
			int accountIndex = bKash.authenticate(accounts, MAX_ACCOUNT);
			if (accountIndex >= 0 && accountIndex <= MAX_ACCOUNT) {
				accountChangeRequested = bKash.showMenu(accounts[accountIndex], accounts, MAX_ACCOUNT);
			} else {
				accountChangeRequested = true;
			}
		} while (accountChangeRequested);
	}

}


