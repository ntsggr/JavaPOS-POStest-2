package postest2;

public class CATConstantMapper implements IMapWrapper {

	// ///////////////////////////////////////////////////////////////////
	// Payment Condition Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getPaymentCondition)
	public static final ConstantConverter CAT_PAYMENT_LUMP = new ConstantConverter(10, "CAT_PAYMENT_LUMP");
	@BelongingProperty(PropertyNames.getPaymentCondition)
	public static final ConstantConverter CAT_PAYMENT_BONUS_1 = new ConstantConverter(21,
			"CAT_PAYMENT_BONUS_1");
	@BelongingProperty(PropertyNames.getPaymentCondition)
	public static final ConstantConverter CAT_PAYMENT_BONUS_2 = new ConstantConverter(22,
			"CAT_PAYMENT_BONUS_2");
	@BelongingProperty(PropertyNames.getPaymentCondition)
	public static final ConstantConverter CAT_PAYMENT_BONUS_3 = new ConstantConverter(23,
			"CAT_PAYMENT_BONUS_3");
	@BelongingProperty(PropertyNames.getPaymentCondition)
	public static final ConstantConverter CAT_PAYMENT_BONUS_4 = new ConstantConverter(24,
			"CAT_PAYMENT_BONUS_4");
	@BelongingProperty(PropertyNames.getPaymentCondition)
	public static final ConstantConverter CAT_PAYMENT_BONUS_5 = new ConstantConverter(25,
			"CAT_PAYMENT_BONUS_5");
	@BelongingProperty(PropertyNames.getPaymentCondition)
	public static final ConstantConverter CAT_PAYMENT_INSTALLMENT_1 = new ConstantConverter(61,
			"CAT_PAYMENT_INSTALLMENT_1");
	@BelongingProperty(PropertyNames.getPaymentCondition)
	public static final ConstantConverter CAT_PAYMENT_INSTALLMENT_2 = new ConstantConverter(62,
			"CAT_PAYMENT_INSTALLMENT_2");
	@BelongingProperty(PropertyNames.getPaymentCondition)
	public static final ConstantConverter CAT_PAYMENT_INSTALLMENT_3 = new ConstantConverter(63,
			"CAT_PAYMENT_INSTALLMENT_3");
	@BelongingProperty(PropertyNames.getPaymentCondition)
	public static final ConstantConverter CAT_PAYMENT_BONUS_COMBINATION_1 = new ConstantConverter(31,
			"CAT_PAYMENT_BONUS_COMBINATION_1");
	@BelongingProperty(PropertyNames.getPaymentCondition)
	public static final ConstantConverter CAT_PAYMENT_BONUS_COMBINATION_2 = new ConstantConverter(32,
			"CAT_PAYMENT_BONUS_COMBINATION_2");
	@BelongingProperty(PropertyNames.getPaymentCondition)
	public static final ConstantConverter CAT_PAYMENT_BONUS_COMBINATION_3 = new ConstantConverter(33,
			"CAT_PAYMENT_BONUS_COMBINATION_3");
	@BelongingProperty(PropertyNames.getPaymentCondition)
	public static final ConstantConverter CAT_PAYMENT_BONUS_COMBINATION_4 = new ConstantConverter(34,
			"CAT_PAYMENT_BONUS_COMBINATION_4");
	@BelongingProperty(PropertyNames.getPaymentCondition)
	public static final ConstantConverter CAT_PAYMENT_REVOLVING = new ConstantConverter(80,
			"CAT_PAYMENT_REVOLVING");
	@BelongingProperty(PropertyNames.getPaymentCondition)
	public static final ConstantConverter CAT_PAYMENT_DEBIT = new ConstantConverter(110, "CAT_PAYMENT_DEBIT");
	@BelongingProperty(PropertyNames.getPaymentCondition)
	public static final ConstantConverter CAT_PAYMENT_ELECTRONIC_MONEY = new ConstantConverter(111,
			"CAT_PAYMENT_ELECTRONIC_MONEY");

	// ///////////////////////////////////////////////////////////////////
	// "LogStatus" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getLogStatus)
	public static final ConstantConverter CAT_LOGSTATUS_OK = new ConstantConverter(1, "CAT_LOGSTATUS_OK");
	@BelongingProperty(PropertyNames.getLogStatus)
	public static final ConstantConverter CAT_LOGSTATUS_NEARFULL = new ConstantConverter(2,
			"CAT_LOGSTATUS_NEARFULL");
	@BelongingProperty(PropertyNames.getLogStatus)
	public static final ConstantConverter CAT_LOGSTATUS_FULL = new ConstantConverter(3, "CAT_LOGSTATUS_FULL");

	// ///////////////////////////////////////////////////////////////////
	// "PaymentMedia' Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getPaymentMedia)
	public static final ConstantConverter CAT_MEDIA_UNSPECIFIED = new ConstantConverter(0,
			"CAT_MEDIA_UNSPECIFIED");
	@BelongingProperty(PropertyNames.getPaymentMedia)
	public static final ConstantConverter CAT_MEDIA_NONDEFINE = new ConstantConverter(0,
			"CAT_MEDIA_NONDEFINE");
	@BelongingProperty(PropertyNames.getPaymentMedia)
	public static final ConstantConverter CAT_MEDIA_CREDIT = new ConstantConverter(1, "CAT_MEDIA_CREDIT");
	@BelongingProperty(PropertyNames.getPaymentMedia)
	public static final ConstantConverter CAT_MEDIA_DEBIT = new ConstantConverter(2, "CAT_MEDIA_DEBIT");
	@BelongingProperty(PropertyNames.getPaymentMedia)
	public static final ConstantConverter CAT_MEDIA_ELECTRONIC_MONEY = new ConstantConverter(3,
			"CAT_MEDIA_ELECTRONIC_MONEY");

	// ///////////////////////////////////////////////////////////////////
	// "Daily Log" Property & Argument Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapDailyLog)
	public static final ConstantConverter CAT_DL_NONE = new ConstantConverter(0, "CAT_DL_NONE");
	@BelongingProperty(PropertyNames.getCapDailyLog)
	public static final ConstantConverter CAT_DL_REPORTING = new ConstantConverter(1, "CAT_DL_REPORTING");
	@BelongingProperty(PropertyNames.getCapDailyLog)
	public static final ConstantConverter CAT_DL_SETTLEMENT = new ConstantConverter(2, "CAT_DL_SETTLEMENT");
	@BelongingProperty(PropertyNames.getCapDailyLog)
	public static final ConstantConverter CAT_DL_REPORTING_SETTLEMENT = new ConstantConverter(3,
			"CAT_DL_REPORTING_SETTLEMENT");

	// ///////////////////////////////////////////////////////////////////
	// Transaction Type Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getTransactionType)
	public static final ConstantConverter CAT_TRANSACTION_SALES = new ConstantConverter(10,
			"CAT_TRANSACTION_SALES");
	@BelongingProperty(PropertyNames.getTransactionType)
	public static final ConstantConverter CAT_TRANSACTION_VOID = new ConstantConverter(20,
			"CAT_TRANSACTION_VOID");
	@BelongingProperty(PropertyNames.getTransactionType)
	public static final ConstantConverter CAT_TRANSACTION_REFUND = new ConstantConverter(21,
			"CAT_TRANSACTION_REFUND");
	@BelongingProperty(PropertyNames.getTransactionType)
	public static final ConstantConverter CAT_TRANSACTION_VOIDPRESALES = new ConstantConverter(29,
			"CAT_TRANSACTION_VOIDPRESALES");
	@BelongingProperty(PropertyNames.getTransactionType)
	public static final ConstantConverter CAT_TRANSACTION_COMPLETION = new ConstantConverter(30,
			"CAT_TRANSACTION_COMPLETION");
	@BelongingProperty(PropertyNames.getTransactionType)
	public static final ConstantConverter CAT_TRANSACTION_PRESALES = new ConstantConverter(40,
			"CAT_TRANSACTION_PRESALES");
	@BelongingProperty(PropertyNames.getTransactionType)
	public static final ConstantConverter CAT_TRANSACTION_CHECKCARD = new ConstantConverter(41,
			"CAT_TRANSACTION_CHECKCARD");
	@BelongingProperty(PropertyNames.getTransactionType)
	public static final ConstantConverter CAT_TRANSACTION_CASHDEPOSIT = new ConstantConverter(50,
			"CAT_TRANSACTION_CASHDEPOSIT");

	public static int getConstantNumberFromString(String constant) {

		if (constant.equals(CATConstantMapper.CAT_TRANSACTION_SALES.getConstant())) {
			return CATConstantMapper.CAT_TRANSACTION_SALES.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_TRANSACTION_VOID.getConstant())) {
			return CATConstantMapper.CAT_TRANSACTION_VOID.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_TRANSACTION_REFUND.getConstant())) {
			return CATConstantMapper.CAT_TRANSACTION_REFUND.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_TRANSACTION_VOIDPRESALES.getConstant())) {
			return CATConstantMapper.CAT_TRANSACTION_VOIDPRESALES.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_TRANSACTION_COMPLETION.getConstant())) {
			return CATConstantMapper.CAT_TRANSACTION_COMPLETION.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_TRANSACTION_PRESALES.getConstant())) {
			return CATConstantMapper.CAT_TRANSACTION_PRESALES.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_TRANSACTION_CHECKCARD.getConstant())) {
			return CATConstantMapper.CAT_TRANSACTION_CHECKCARD.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_TRANSACTION_CASHDEPOSIT.getConstant())) {
			return CATConstantMapper.CAT_TRANSACTION_CASHDEPOSIT.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_PAYMENT_LUMP.getConstant())) {
			return CATConstantMapper.CAT_PAYMENT_LUMP.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_PAYMENT_BONUS_1.getConstant())) {
			return CATConstantMapper.CAT_PAYMENT_BONUS_1.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_PAYMENT_BONUS_2.getConstant())) {
			return CATConstantMapper.CAT_PAYMENT_BONUS_2.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_PAYMENT_BONUS_3.getConstant())) {
			return CATConstantMapper.CAT_PAYMENT_BONUS_3.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_PAYMENT_BONUS_4.getConstant())) {
			return CATConstantMapper.CAT_PAYMENT_BONUS_4.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_PAYMENT_BONUS_5.getConstant())) {
			return CATConstantMapper.CAT_PAYMENT_BONUS_5.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_PAYMENT_INSTALLMENT_1.getConstant())) {
			return CATConstantMapper.CAT_PAYMENT_INSTALLMENT_1.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_PAYMENT_INSTALLMENT_2.getConstant())) {
			return CATConstantMapper.CAT_PAYMENT_INSTALLMENT_2.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_PAYMENT_INSTALLMENT_3.getConstant())) {
			return CATConstantMapper.CAT_PAYMENT_INSTALLMENT_3.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_PAYMENT_BONUS_COMBINATION_1.getConstant())) {
			return CATConstantMapper.CAT_PAYMENT_BONUS_COMBINATION_1.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_PAYMENT_BONUS_COMBINATION_2.getConstant())) {
			return CATConstantMapper.CAT_PAYMENT_BONUS_COMBINATION_2.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_PAYMENT_BONUS_COMBINATION_3.getConstant())) {
			return CATConstantMapper.CAT_PAYMENT_BONUS_COMBINATION_3.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_PAYMENT_BONUS_COMBINATION_4.getConstant())) {
			return CATConstantMapper.CAT_PAYMENT_BONUS_COMBINATION_4.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_PAYMENT_REVOLVING.getConstant())) {
			return CATConstantMapper.CAT_PAYMENT_REVOLVING.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_PAYMENT_DEBIT.getConstant())) {
			return CATConstantMapper.CAT_PAYMENT_DEBIT.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_PAYMENT_ELECTRONIC_MONEY.getConstant())) {
			return CATConstantMapper.CAT_PAYMENT_ELECTRONIC_MONEY.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_LOGSTATUS_OK.getConstant())) {
			return CATConstantMapper.CAT_LOGSTATUS_OK.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_LOGSTATUS_NEARFULL.getConstant())) {
			return CATConstantMapper.CAT_LOGSTATUS_NEARFULL.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_LOGSTATUS_FULL.getConstant())) {
			return CATConstantMapper.CAT_LOGSTATUS_FULL.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_MEDIA_UNSPECIFIED.getConstant())) {
			return CATConstantMapper.CAT_MEDIA_UNSPECIFIED.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_MEDIA_NONDEFINE.getConstant())) {
			return CATConstantMapper.CAT_MEDIA_NONDEFINE.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_MEDIA_CREDIT.getConstant())) {
			return CATConstantMapper.CAT_MEDIA_CREDIT.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_MEDIA_DEBIT.getConstant())) {
			return CATConstantMapper.CAT_MEDIA_DEBIT.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_MEDIA_ELECTRONIC_MONEY.getConstant())) {
			return CATConstantMapper.CAT_MEDIA_ELECTRONIC_MONEY.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_DL_NONE.getConstant())) {
			return CATConstantMapper.CAT_DL_NONE.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_DL_REPORTING.getConstant())) {
			return CATConstantMapper.CAT_DL_REPORTING.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_DL_SETTLEMENT.getConstant())) {
			return CATConstantMapper.CAT_DL_SETTLEMENT.getContantNumber();
		}

		if (constant.equals(CATConstantMapper.CAT_DL_REPORTING_SETTLEMENT.getConstant())) {
			return CATConstantMapper.CAT_DL_REPORTING_SETTLEMENT.getContantNumber();
		}

		return Integer.parseInt(constant);
	}

	@Override
	public IMapWrapper getTheClass() {
		return this;
	}

}
