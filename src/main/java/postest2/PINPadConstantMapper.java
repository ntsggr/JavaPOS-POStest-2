package postest2;

public class PINPadConstantMapper {

	// ///////////////////////////////////////////////////////////////////
	// "TransactionType" Property Constants
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter PPAD_TRANS_DEBIT = new ConstantConverter(1, "PPAD_TRANS_DEBIT");
	public static final ConstantConverter PPAD_TRANS_CREDIT = new ConstantConverter(2, "PPAD_TRANS_CREDIT");
	public static final ConstantConverter PPAD_TRANS_INQ = new ConstantConverter(3, "PPAD_TRANS_INQ");
	public static final ConstantConverter PPAD_TRANS_RECONCILE = new ConstantConverter(4,
			"PPAD_TRANS_RECONCILE");
	public static final ConstantConverter PPAD_TRANS_ADMIN = new ConstantConverter(5, "PPAD_TRANS_ADMIN");

	
	public static int getConstantNumberFromString(String constant) {

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

}
