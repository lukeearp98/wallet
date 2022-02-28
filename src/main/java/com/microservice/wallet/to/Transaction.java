/********************************************************************************************
 * Author: 			Luke Earp
 * Class: 			TransactionDAO.java
 * Description: 	Data object for each transaction.
 * Creation Date: 	28/02/2022
 * Current Version: 1.0 - Created.
 * Version History: 
 *********************************************************************************************/
package com.microservice.wallet.to;

public class Transaction {
	int transactionId;
	String transactionType;
	int customerId;
	double transAmount;
	/*********************************************************************************************
	 * Name: 		TransactionDAO
	 * Description: Default instance of constructor.
	 * Author: 		Luke Earp
	 * Created: 	28/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 ********************************************************************************************/
	public Transaction() {
	}
	/*********************************************************************************************
	 * Name: 		TransactionDAO
	 * Description: Default instance of constructor with parameters.
	 * Parameters:	The details of the transaction.
	 * Author: 		Luke Earp
	 * Created: 	28/02/2022
	 * Version: 	1.0 - Created.
	 * History: 
	 ********************************************************************************************/
	public Transaction(int transactionId, String transactionType, int customerId, double transAmount) {
		super();
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.transAmount = transAmount;
		this.customerId = customerId;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public double getTransAmount() {
		return transAmount;
	}
	public void setTransAmount(double transAmount) {
		this.transAmount = transAmount;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", transactionType=" + transactionType
				+ ", customerId=" + customerId + ", transAmount=" + transAmount + "]";
	}
	
	
	
}
