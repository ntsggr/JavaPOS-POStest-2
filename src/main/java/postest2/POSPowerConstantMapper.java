package postest2;

public class POSPowerConstantMapper {

	// ///////////////////////////////////////////////////////////////////
	// "restartPOS", "standbyPOS", "suspendPOS" Methods:
	// "reason" Parameter Constants
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter PWR_REASON_REQUEST = new ConstantConverter(1, "PWR_REASON_REQUEST");
	public static final ConstantConverter PWR_REASON_ALLOW = new ConstantConverter(2, "PWR_REASON_ALLOW");
	public static final ConstantConverter PWR_REASON_DENY = new ConstantConverter(3, "PWR_REASON_DENY");

	public static int getConstantNumberFromString(String constant) {

		if (constant.equals(POSPowerConstantMapper.PWR_REASON_REQUEST.getConstant())) {
			return POSPowerConstantMapper.PWR_REASON_REQUEST.getContantNumber();
		}

		if (constant.equals(POSPowerConstantMapper.PWR_REASON_ALLOW.getConstant())) {
			return POSPowerConstantMapper.PWR_REASON_ALLOW.getContantNumber();
		}

		if (constant.equals(POSPowerConstantMapper.PWR_REASON_DENY.getConstant())) {
			return POSPowerConstantMapper.PWR_REASON_DENY.getContantNumber();
		}

		return Integer.parseInt(constant);
	}

}
