package com.revolut.app;

public class Constants {
	
	public static final String CREATE_ACCOUNT_MESSAGE = "Account was created.";
	
    public enum TransactionStatus {
    	
        NEW("preview"), IN_PROGRESS("publish"), FINISHED("finished"), FAILED("failed");

        private String value;

        TransactionStatus(final String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
