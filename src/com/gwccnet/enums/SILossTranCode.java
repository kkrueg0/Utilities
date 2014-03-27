package com.gwccnet.enums;

public enum SILossTranCode
{
	HANDLING_FEE_PAYMENT("805","Claims Handling Fee"),
	REIMBURSEMENT_EXPENSE("804","Allocated Loss Adjustment Expense"),
	REIMBURSEMENT("500","Paid Loss"),
	HANDLING_FEE_ADJUSTMENT("806","Claims Handling Fee Adjustment"),
	UNKNOWN_TRAN_CODE("UNK","Unknown Transaction");
	private String transactionCode;
	private String description;
	private SILossTranCode(String transactionCode, String description)
	{
		this.transactionCode = transactionCode;
		this.description = description;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getTransactionCode()
	{
		return transactionCode;
	}
	public void setTransactionCode(String transactionCode)
	{
		this.transactionCode = transactionCode;
	}
	public static SILossTranCode findLossTranCode(String tranCode)
	{
		SILossTranCode tran = UNKNOWN_TRAN_CODE;
		if (tranCode.equals("805"))
		{
			tran =  HANDLING_FEE_PAYMENT;
		}
		else if (tranCode.equals("804"))
		{
			tran = REIMBURSEMENT_EXPENSE;
		}
		else if (tranCode.equals("500"))
		{
			tran = REIMBURSEMENT;
		}
		else if (tranCode.equals("806"))
		{
			tran = HANDLING_FEE_ADJUSTMENT;
		}
		return tran;
	}
	
}
