package postest2;

public class ElectronicValueRWConstantMapper {

	// ///////////////////////////////////////////////////////////////////
	// "accessLog" Method: "Type" Parameter Constants
	// ///////////////////////////////////////////////////////////////////

	public static final ConstantConverter EVRW_AL_REPORTING = new ConstantConverter(1, "EVRW_AL_REPORTING");
	public static final ConstantConverter EVRW_AL_SETTLEMENT = new ConstantConverter(2, "EVRW_AL_SETTLEMENT");

	// ///////////////////////////////////////////////////////////////////
	// "beginDetection" Method: "Type" Parameter Constants
	// ///////////////////////////////////////////////////////////////////

	public static final ConstantConverter EVRW_BD_ANY = new ConstantConverter(1, "EVRW_BD_ANY");
	public static final ConstantConverter EVRW_BD_SPECIFIC = new ConstantConverter(2, "EVRW_BD_SPECIFIC");

	// ///////////////////////////////////////////////////////////////////
	// "transactionAccess" Method: "Control" Parameter Constants
	// ///////////////////////////////////////////////////////////////////

	public static final ConstantConverter EVRW_TA_TRANSACTION = new ConstantConverter(11, "EVRW_TA_TRANSACTION");
	public static final ConstantConverter EVRW_TA_NORMAL = new ConstantConverter(12, "EVRW_TA_NORMAL");

	
	public static int getConstantNumberFromString(String constant){

		if(constant.equals(ElectronicValueRWConstantMapper.EVRW_AL_REPORTING.getConstant())) {
			return ElectronicValueRWConstantMapper.EVRW_AL_REPORTING.getContantNumber();
		}

		if(constant.equals(ElectronicValueRWConstantMapper.EVRW_AL_SETTLEMENT.getConstant())) {
			return ElectronicValueRWConstantMapper.EVRW_AL_SETTLEMENT.getContantNumber();
		}

		if(constant.equals(ElectronicValueRWConstantMapper.EVRW_BD_ANY.getConstant())) {
			return ElectronicValueRWConstantMapper.EVRW_BD_ANY.getContantNumber();
		}

		if(constant.equals(ElectronicValueRWConstantMapper.EVRW_BD_SPECIFIC.getConstant())) {
			return ElectronicValueRWConstantMapper.EVRW_BD_SPECIFIC.getContantNumber();
		}

		if(constant.equals(ElectronicValueRWConstantMapper.EVRW_TA_NORMAL.getConstant())) {
			return ElectronicValueRWConstantMapper.EVRW_TA_NORMAL.getContantNumber();
		}

		if(constant.equals(ElectronicValueRWConstantMapper.EVRW_TA_TRANSACTION.getConstant())) {
			return ElectronicValueRWConstantMapper.EVRW_TA_TRANSACTION.getContantNumber();
		}
		
		return Integer.parseInt(constant);
	}
	
	
	
	
	
}
