package bkash_backend;

public class BkashAccount extends Account {

	private AccountInfo accountInfo;
	
	public BkashAccount(String accountName, AccountInfo accountInfo) {
		super(accountName);
		this.setAccountInfo(accountInfo);
	}

	public AccountInfo getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(AccountInfo accountInfo) {
		this.accountInfo = accountInfo;
	}

	public boolean equals(BkashAccount a) {
		if (a.getAccountInfo().equals(this.accountInfo) && a.getUserName().equals(this.getUserName())) {
			return true;
		} else {
			return false;
		}
	}
}
