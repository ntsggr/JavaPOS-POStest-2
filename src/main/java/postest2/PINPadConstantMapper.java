package postest2;

public class PINPadConstantMapper implements IMapWrapper {

	// ///////////////////////////////////////////////////////////////////
	// "AvailablePromptsList" and "Prompt" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getAvailablePromptsList)
	public static final ConstantConverter PPAD_MSG_ENTERPIN = new ConstantConverter(1, "PPAD_MSG_ENTERPIN");
	@BelongingProperty(PropertyNames.getAvailablePromptsList)
	public static final ConstantConverter PPAD_MSG_PLEASEWAIT = new ConstantConverter(2,
			"PPAD_MSG_PLEASEWAIT");
	@BelongingProperty(PropertyNames.getAvailablePromptsList)
	public static final ConstantConverter PPAD_MSG_ENTERVALIDPIN = new ConstantConverter(3,
			"PPAD_MSG_ENTERVALIDPIN");
	@BelongingProperty(PropertyNames.getAvailablePromptsList)
	public static final ConstantConverter PPAD_MSG_RETRIESEXCEEDED = new ConstantConverter(4,
			"PPAD_MSG_RETRIESEXCEEDED");
	@BelongingProperty(PropertyNames.getAvailablePromptsList)
	public static final ConstantConverter PPAD_MSG_APPROVED = new ConstantConverter(5, "PPAD_MSG_APPROVED");
	@BelongingProperty(PropertyNames.getAvailablePromptsList)
	public static final ConstantConverter PPAD_MSG_DECLINED = new ConstantConverter(6, "PPAD_MSG_DECLINED");
	@BelongingProperty(PropertyNames.getAvailablePromptsList)
	public static final ConstantConverter PPAD_MSG_CANCELED = new ConstantConverter(7, "PPAD_MSG_CANCELED");
	@BelongingProperty(PropertyNames.getAvailablePromptsList)
	public static final ConstantConverter PPAD_MSG_AMOUNTOK = new ConstantConverter(8, "PPAD_MSG_AMOUNTOK");
	@BelongingProperty(PropertyNames.getAvailablePromptsList)
	public static final ConstantConverter PPAD_MSG_NOTREADY = new ConstantConverter(9, "PPAD_MSG_NOTREADY");
	@BelongingProperty(PropertyNames.getAvailablePromptsList)
	public static final ConstantConverter PPAD_MSG_IDLE = new ConstantConverter(10, "PPAD_MSG_IDLE");
	@BelongingProperty(PropertyNames.getAvailablePromptsList)
	public static final ConstantConverter PPAD_MSG_SLIDE_CARD = new ConstantConverter(11,
			"PPAD_MSG_SLIDE_CARD");
	@BelongingProperty(PropertyNames.getAvailablePromptsList)
	public static final ConstantConverter PPAD_MSG_INSERTCARD = new ConstantConverter(12,
			"PPAD_MSG_INSERTCARD");
	@BelongingProperty(PropertyNames.getAvailablePromptsList)
	public static final ConstantConverter PPAD_MSG_SELECTCARDTYPE = new ConstantConverter(13,
			"PPAD_MSG_SELECTCARDTYPE");

	// ///////////////////////////////////////////////////////////////////
	// "TransactionType" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getTransactionType)
	public static final ConstantConverter PPAD_TRANS_DEBIT = new ConstantConverter(1, "PPAD_TRANS_DEBIT");
	@BelongingProperty(PropertyNames.getTransactionType)
	public static final ConstantConverter PPAD_TRANS_CREDIT = new ConstantConverter(2, "PPAD_TRANS_CREDIT");
	@BelongingProperty(PropertyNames.getTransactionType)
	public static final ConstantConverter PPAD_TRANS_INQ = new ConstantConverter(3, "PPAD_TRANS_INQ");
	@BelongingProperty(PropertyNames.getTransactionType)
	public static final ConstantConverter PPAD_TRANS_RECONCILE = new ConstantConverter(4,
			"PPAD_TRANS_RECONCILE");
	@BelongingProperty(PropertyNames.getTransactionType)
	public static final ConstantConverter PPAD_TRANS_ADMIN = new ConstantConverter(5, "PPAD_TRANS_ADMIN");

	// ///////////////////////////////////////////////////////////////////
	// "CapDisplay" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapDisplay)
	public static final ConstantConverter PPAD_DISP_UNRESTRICTED = new ConstantConverter(1,
			"PPAD_DISP_UNRESTRICTED");
	@BelongingProperty(PropertyNames.getCapDisplay)
	public static final ConstantConverter PPAD_DISP_PINRESTRICTED = new ConstantConverter(2,
			"PPAD_DISP_PINRESTRICTED");
	@BelongingProperty(PropertyNames.getCapDisplay)
	public static final ConstantConverter PPAD_DISP_RESTRICTED_LIST = new ConstantConverter(3,
			"PPAD_DISP_RESTRICTED_LIST");
	@BelongingProperty(PropertyNames.getCapDisplay)
	public static final ConstantConverter PPAD_DISP_RESTRICTED_ORDER = new ConstantConverter(4,
			"PPAD_DISP_RESTRICTED_ORDER");
	@BelongingProperty(PropertyNames.getCapDisplay)
	public static final ConstantConverter PPAD_DISP_NONE = new ConstantConverter(5, "PPAD_DISP_NONE");

	// ///////////////////////////////////////////////////////////////////
	// "CapLanguage" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapLanguage)
	public static final ConstantConverter PPAD_LANG_NONE = new ConstantConverter(1, "PPAD_LANG_NONE");
	@BelongingProperty(PropertyNames.getCapLanguage)
	public static final ConstantConverter PPAD_LANG_ONE = new ConstantConverter(2, "PPAD_LANG_ONE");
	@BelongingProperty(PropertyNames.getCapLanguage)
	public static final ConstantConverter PPAD_LANG_PINRESTRICTED = new ConstantConverter(3, "PPAD_LANG_PINRESTRICTED");
	@BelongingProperty(PropertyNames.getCapLanguage)
	public static final ConstantConverter PPAD_LANG_UNRESTRICTED = new ConstantConverter(4, "PPAD_LANG_UNRESTRICTED");

	public static int getConstantNumberFromString(String constant) {
		
		if (constant.equals(PINPadConstantMapper.PPAD_LANG_NONE.getConstant())) {
			return PINPadConstantMapper.PPAD_LANG_NONE.getContantNumber();
		}

		if (constant.equals(PINPadConstantMapper.PPAD_LANG_ONE.getConstant())) {
			return PINPadConstantMapper.PPAD_LANG_ONE.getContantNumber();
		}

		if (constant.equals(PINPadConstantMapper.PPAD_LANG_PINRESTRICTED.getConstant())) {
			return PINPadConstantMapper.PPAD_LANG_PINRESTRICTED.getContantNumber();
		}

		if (constant.equals(PINPadConstantMapper.PPAD_LANG_UNRESTRICTED.getConstant())) {
			return PINPadConstantMapper.PPAD_LANG_UNRESTRICTED.getContantNumber();
		}
		
		if (constant.equals(PINPadConstantMapper.PPAD_DISP_UNRESTRICTED.getConstant())) {
			return PINPadConstantMapper.PPAD_DISP_UNRESTRICTED.getContantNumber();
		}

		if (constant.equals(PINPadConstantMapper.PPAD_DISP_PINRESTRICTED.getConstant())) {
			return PINPadConstantMapper.PPAD_DISP_PINRESTRICTED.getContantNumber();
		}

		if (constant.equals(PINPadConstantMapper.PPAD_DISP_RESTRICTED_LIST.getConstant())) {
			return PINPadConstantMapper.PPAD_DISP_RESTRICTED_LIST.getContantNumber();
		}

		if (constant.equals(PINPadConstantMapper.PPAD_DISP_RESTRICTED_ORDER.getConstant())) {
			return PINPadConstantMapper.PPAD_DISP_RESTRICTED_ORDER.getContantNumber();
		}

		if (constant.equals(PINPadConstantMapper.PPAD_DISP_NONE.getConstant())) {
			return PINPadConstantMapper.PPAD_DISP_NONE.getContantNumber();
		}
		
		if (constant.equals(PINPadConstantMapper.PPAD_MSG_ENTERPIN.getConstant())) {
			return PINPadConstantMapper.PPAD_MSG_ENTERPIN.getContantNumber();
		}

		if (constant.equals(PINPadConstantMapper.PPAD_MSG_PLEASEWAIT.getConstant())) {
			return PINPadConstantMapper.PPAD_MSG_PLEASEWAIT.getContantNumber();
		}

		if (constant.equals(PINPadConstantMapper.PPAD_MSG_ENTERVALIDPIN.getConstant())) {
			return PINPadConstantMapper.PPAD_MSG_ENTERVALIDPIN.getContantNumber();
		}

		if (constant.equals(PINPadConstantMapper.PPAD_MSG_RETRIESEXCEEDED.getConstant())) {
			return PINPadConstantMapper.PPAD_MSG_RETRIESEXCEEDED.getContantNumber();
		}

		if (constant.equals(PINPadConstantMapper.PPAD_MSG_APPROVED.getConstant())) {
			return PINPadConstantMapper.PPAD_MSG_APPROVED.getContantNumber();
		}
		
		if (constant.equals(PINPadConstantMapper.PPAD_MSG_DECLINED.getConstant())) {
			return PINPadConstantMapper.PPAD_MSG_DECLINED.getContantNumber();
		}

		if (constant.equals(PINPadConstantMapper.PPAD_MSG_CANCELED.getConstant())) {
			return PINPadConstantMapper.PPAD_MSG_CANCELED.getContantNumber();
		}

		if (constant.equals(PINPadConstantMapper.PPAD_MSG_AMOUNTOK.getConstant())) {
			return PINPadConstantMapper.PPAD_MSG_AMOUNTOK.getContantNumber();
		}

		if (constant.equals(PINPadConstantMapper.PPAD_MSG_NOTREADY.getConstant())) {
			return PINPadConstantMapper.PPAD_MSG_NOTREADY.getContantNumber();
		}

		if (constant.equals(PINPadConstantMapper.PPAD_MSG_IDLE.getConstant())) {
			return PINPadConstantMapper.PPAD_MSG_IDLE.getContantNumber();
		}
		
		if (constant.equals(PINPadConstantMapper.PPAD_MSG_SLIDE_CARD.getConstant())) {
			return PINPadConstantMapper.PPAD_MSG_SLIDE_CARD.getContantNumber();
		}
		
		if (constant.equals(PINPadConstantMapper.PPAD_MSG_INSERTCARD.getConstant())) {
			return PINPadConstantMapper.PPAD_MSG_INSERTCARD.getContantNumber();
		}
		
		if (constant.equals(PINPadConstantMapper.PPAD_MSG_SELECTCARDTYPE.getConstant())) {
			return PINPadConstantMapper.PPAD_MSG_SELECTCARDTYPE.getContantNumber();
		}

		if (constant.equals(PINPadConstantMapper.PPAD_TRANS_DEBIT.getConstant())) {
			return PINPadConstantMapper.PPAD_TRANS_DEBIT.getContantNumber();
		}

		if (constant.equals(PINPadConstantMapper.PPAD_TRANS_CREDIT.getConstant())) {
			return PINPadConstantMapper.PPAD_TRANS_CREDIT.getContantNumber();
		}

		if (constant.equals(PINPadConstantMapper.PPAD_TRANS_INQ.getConstant())) {
			return PINPadConstantMapper.PPAD_TRANS_INQ.getContantNumber();
		}

		if (constant.equals(PINPadConstantMapper.PPAD_TRANS_RECONCILE.getConstant())) {
			return PINPadConstantMapper.PPAD_TRANS_RECONCILE.getContantNumber();
		}

		if (constant.equals(PINPadConstantMapper.PPAD_TRANS_ADMIN.getConstant())) {
			return PINPadConstantMapper.PPAD_TRANS_ADMIN.getContantNumber();
		}

		return Integer.parseInt(constant);
	}

	@Override
	public IMapWrapper getTheClass() {
		return this;
	}
	
}
