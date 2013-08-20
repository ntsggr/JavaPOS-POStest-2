package postest2;

public class CATConstantMapper {

	// ///////////////////////////////////////////////////////////////////
	// "PaymentMedia' Property Constants
	// ///////////////////////////////////////////////////////////////////

	public static final ConstantConverter CAT_MEDIA_UNSPECIFIED = new ConstantConverter(0, "CAT_MEDIA_UNSPECIFIED");
	public static final ConstantConverter CAT_MEDIA_NONDEFINE = new ConstantConverter(0, "CAT_MEDIA_NONDEFINE");
	public static final ConstantConverter CAT_MEDIA_CREDIT = new ConstantConverter(1, "CAT_MEDIA_CREDIT");
	public static final ConstantConverter CAT_MEDIA_DEBIT = new ConstantConverter(2, "CAT_MEDIA_DEBIT");
	public static final ConstantConverter CAT_MEDIA_ELECTRONIC_MONEY = new ConstantConverter(3,
			"CAT_MEDIA_ELECTRONIC_MONEY");

	// ///////////////////////////////////////////////////////////////////
	// "Daily Log" Property & Argument Constants
	// ///////////////////////////////////////////////////////////////////

	public static final ConstantConverter CAT_DL_REPORTING = new ConstantConverter(1, "CAT_DL_REPORTING"); // Only
	// Reporting
	public static final ConstantConverter CAT_DL_SETTLEMENT = new ConstantConverter(2, "CAT_DL_SETTLEMENT"); // Only
	// Settlement
	
	public static int getConstantNumberFromString(String constant){

		if(constant.equals(CATConstantMapper.CAT_MEDIA_UNSPECIFIED.getConstant())) {
			return CATConstantMapper.CAT_MEDIA_UNSPECIFIED.getContantNumber();
		}
		
		if(constant.equals(CATConstantMapper.CAT_MEDIA_NONDEFINE.getConstant())) {
			return CATConstantMapper.CAT_MEDIA_NONDEFINE.getContantNumber();
		}

		if(constant.equals(CATConstantMapper.CAT_MEDIA_CREDIT.getConstant())) {
			return CATConstantMapper.CAT_MEDIA_CREDIT.getContantNumber();
		}

		if(constant.equals(CATConstantMapper.CAT_MEDIA_DEBIT.getConstant())) {
			return CATConstantMapper.CAT_MEDIA_DEBIT.getContantNumber();
		}

		if(constant.equals(CATConstantMapper.CAT_MEDIA_ELECTRONIC_MONEY.getConstant())) {
			return CATConstantMapper.CAT_MEDIA_ELECTRONIC_MONEY.getContantNumber();
		}

		if(constant.equals(CATConstantMapper.CAT_DL_REPORTING.getConstant())) {
			return CATConstantMapper.CAT_DL_REPORTING.getContantNumber();
		}

		if(constant.equals(CATConstantMapper.CAT_DL_SETTLEMENT.getConstant())) {
			return CATConstantMapper.CAT_DL_SETTLEMENT.getContantNumber();
		}
		
		
		return Integer.parseInt(constant);
	}
	

}
