package postest2;

public class POSPowerConstantMapper implements IMapWrapper {

	// ///////////////////////////////////////////////////////////////////
	// "CapUPSChargeState" Capability and "UPSChargeState" Property
	// Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapUPSChargeState)
	public static final ConstantConverter PWR_UPS_FULL = new ConstantConverter(0x00000001, "PWR_UPS_FULL");
	@BelongingProperty(PropertyNames.getCapUPSChargeState)
	public static final ConstantConverter PWR_UPS_WARNING = new ConstantConverter(0x00000002,
			"PWR_UPS_WARNING");
	@BelongingProperty(PropertyNames.getCapUPSChargeState)
	public static final ConstantConverter PWR_UPS_LOW = new ConstantConverter(0x00000004, "PWR_UPS_LOW");
	@BelongingProperty(PropertyNames.getCapUPSChargeState)
	public static final ConstantConverter PWR_UPS_CRITICAL = new ConstantConverter(0x00000008,
			"PWR_UPS_CRITICAL");

	// ///////////////////////////////////////////////////////////////////
	// "restartPOS", "standbyPOS", "suspendPOS" Methods:
	// "reason" Parameter Constants
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter PWR_REASON_REQUEST = new ConstantConverter(1, "PWR_REASON_REQUEST");
	public static final ConstantConverter PWR_REASON_ALLOW = new ConstantConverter(2, "PWR_REASON_ALLOW");
	public static final ConstantConverter PWR_REASON_DENY = new ConstantConverter(3, "PWR_REASON_DENY");

	// ///////////////////////////////////////////////////////////////////
	// "PowerSource" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getPowerSource)
	public static final ConstantConverter PWR_SOURCE_NA = new ConstantConverter(1, "PWR_SOURCE_NA");
	@BelongingProperty(PropertyNames.getPowerSource)
	public static final ConstantConverter PWR_SOURCE_AC = new ConstantConverter(2, "PWR_SOURCE_AC");
	@BelongingProperty(PropertyNames.getPowerSource)
	public static final ConstantConverter PWR_SOURCE_BATTERY = new ConstantConverter(3, "PWR_SOURCE_BATTERY");
	@BelongingProperty(PropertyNames.getPowerSource)
	public static final ConstantConverter PWR_SOURCE_BACKUP = new ConstantConverter(4, "PWR_SOURCE_BACKUP");

	public static int getConstantNumberFromString(String constant) {
		
		if (constant.equals(POSPowerConstantMapper.PWR_SOURCE_NA.getConstant())) {
			return POSPowerConstantMapper.PWR_SOURCE_NA.getContantNumber();
		}

		if (constant.equals(POSPowerConstantMapper.PWR_SOURCE_AC.getConstant())) {
			return POSPowerConstantMapper.PWR_SOURCE_AC.getContantNumber();
		}

		if (constant.equals(POSPowerConstantMapper.PWR_SOURCE_BATTERY.getConstant())) {
			return POSPowerConstantMapper.PWR_SOURCE_BATTERY.getContantNumber();
		}

		if (constant.equals(POSPowerConstantMapper.PWR_SOURCE_BACKUP.getConstant())) {
			return POSPowerConstantMapper.PWR_SOURCE_BACKUP.getContantNumber();
		}

		if (constant.equals(POSPowerConstantMapper.PWR_UPS_FULL.getConstant())) {
			return POSPowerConstantMapper.PWR_UPS_FULL.getContantNumber();
		}

		if (constant.equals(POSPowerConstantMapper.PWR_UPS_WARNING.getConstant())) {
			return POSPowerConstantMapper.PWR_UPS_WARNING.getContantNumber();
		}

		if (constant.equals(POSPowerConstantMapper.PWR_UPS_LOW.getConstant())) {
			return POSPowerConstantMapper.PWR_UPS_LOW.getContantNumber();
		}

		if (constant.equals(POSPowerConstantMapper.PWR_UPS_CRITICAL.getConstant())) {
			return POSPowerConstantMapper.PWR_UPS_CRITICAL.getContantNumber();
		}

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

	@Override
	public IMapWrapper getTheClass() {
		return this;
	}

}
