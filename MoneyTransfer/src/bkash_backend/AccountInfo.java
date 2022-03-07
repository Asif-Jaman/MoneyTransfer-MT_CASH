package bkash_backend;

public class AccountInfo {
	private final int accountId;
	private String passsword;
	
	public AccountInfo(int id, String password) {
		this.accountId = id;
		this.passsword = password;
	}

	public int getAccountId() {
		return accountId;
	}
	
	public boolean checkPassword(String password) {
		boolean passwordMatched = false;
		if (this.passsword.equals(password)) {
			passwordMatched = true;
		}
		return passwordMatched;
	}
	
	public boolean equals(AccountInfo info) {
		if (info.checkPassword(this.passsword)) {
			return true;
		} else {
			return false;
		}
	}
}
