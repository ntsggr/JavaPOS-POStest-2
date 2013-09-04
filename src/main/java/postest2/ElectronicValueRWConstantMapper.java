package postest2;

public class ElectronicValueRWConstantMapper implements IMapWrapper {

	// ///////////////////////////////////////////////////////////////////
	// "CapCardSensor" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapCardSensor)
	public static final ConstantConverter EVRW_CCS_ENTRY = new ConstantConverter(0x00000001, "EVRW_CCS_ENTRY");
	@BelongingProperty(PropertyNames.getCapCardSensor)
	public static final ConstantConverter EVRW_CCS_DETECT = new ConstantConverter(0x00000002,
			"EVRW_CCS_DETECT");
	@BelongingProperty(PropertyNames.getCapCardSensor)
	public static final ConstantConverter EVRW_CCS_CAPTURE = new ConstantConverter(0x00000004,
			"EVRW_CCS_CAPTURE");

	// ///////////////////////////////////////////////////////////////////
	// "CapDetectionControl" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapDetectionControl)
	public static final ConstantConverter EVRW_CDC_RWCONTROL = new ConstantConverter(0x00000001,
			"EVRW_CDC_RWCONTROL");
	@BelongingProperty(PropertyNames.getCapDetectionControl)
	public static final ConstantConverter EVRW_CDC_APPLICATIONCONTROL = new ConstantConverter(0x00000002,
			"EVRW_CDC_APPLICATIONCONTROL");

	// ///////////////////////////////////////////////////////////////////
	// "DetectionStatus" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getDetectionStatus)
	public static final ConstantConverter EVRW_DS_NOCARD = new ConstantConverter(1, "EVRW_DS_NOCARD");
	@BelongingProperty(PropertyNames.getDetectionStatus)
	public static final ConstantConverter EVRW_DS_DETECTED = new ConstantConverter(2, "EVRW_DS_DETECTED");
	@BelongingProperty(PropertyNames.getDetectionStatus)
	public static final ConstantConverter EVRW_DS_ENTERED = new ConstantConverter(3, "EVRW_DS_ENTERED");
	@BelongingProperty(PropertyNames.getDetectionStatus)
	public static final ConstantConverter EVRW_DS_CAPTURED = new ConstantConverter(4, "EVRW_DS_CAPTURED");

	// ///////////////////////////////////////////////////////////////////
	// "LogStatus" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getLogStatus)
	public static final ConstantConverter EVRW_LS_OK = new ConstantConverter(1, "EVRW_LS_OK");
	@BelongingProperty(PropertyNames.getLogStatus)
	public static final ConstantConverter EVRW_LS_NEARFULL = new ConstantConverter(2, "EVRW_LS_NEARFULL");
	@BelongingProperty(PropertyNames.getLogStatus)
	public static final ConstantConverter EVRW_LS_FULL = new ConstantConverter(3, "EVRW_LS_FULL");

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
	public static final ConstantConverter EVRW_TA_TRANSACTION = new ConstantConverter(11,
			"EVRW_TA_TRANSACTION");
	public static final ConstantConverter EVRW_TA_NORMAL = new ConstantConverter(12, "EVRW_TA_NORMAL");

	public static int getConstantNumberFromString(String constant) {
		
		if (constant.equals(ElectronicValueRWConstantMapper.EVRW_LS_OK.getConstant())) {
			return ElectronicValueRWConstantMapper.EVRW_LS_OK.getContantNumber();
		}

		if (constant.equals(ElectronicValueRWConstantMapper.EVRW_LS_NEARFULL.getConstant())) {
			return ElectronicValueRWConstantMapper.EVRW_LS_NEARFULL.getContantNumber();
		}

		if (constant.equals(ElectronicValueRWConstantMapper.EVRW_LS_FULL.getConstant())) {
			return ElectronicValueRWConstantMapper.EVRW_LS_FULL.getContantNumber();
		}
		
		if (constant.equals(ElectronicValueRWConstantMapper.EVRW_DS_NOCARD.getConstant())) {
			return ElectronicValueRWConstantMapper.EVRW_DS_NOCARD.getContantNumber();
		}

		if (constant.equals(ElectronicValueRWConstantMapper.EVRW_DS_DETECTED.getConstant())) {
			return ElectronicValueRWConstantMapper.EVRW_DS_DETECTED.getContantNumber();
		}

		if (constant.equals(ElectronicValueRWConstantMapper.EVRW_DS_ENTERED.getConstant())) {
			return ElectronicValueRWConstantMapper.EVRW_DS_ENTERED.getContantNumber();
		}

		if (constant.equals(ElectronicValueRWConstantMapper.EVRW_DS_CAPTURED.getConstant())) {
			return ElectronicValueRWConstantMapper.EVRW_DS_CAPTURED.getContantNumber();
		}

		if (constant.equals(ElectronicValueRWConstantMapper.EVRW_CDC_RWCONTROL.getConstant())) {
			return ElectronicValueRWConstantMapper.EVRW_CDC_RWCONTROL.getContantNumber();
		}

		if (constant.equals(ElectronicValueRWConstantMapper.EVRW_CDC_APPLICATIONCONTROL.getConstant())) {
			return ElectronicValueRWConstantMapper.EVRW_CDC_APPLICATIONCONTROL.getContantNumber();
		}

		if (constant.equals(ElectronicValueRWConstantMapper.EVRW_CCS_ENTRY.getConstant())) {
			return ElectronicValueRWConstantMapper.EVRW_CCS_ENTRY.getContantNumber();
		}

		if (constant.equals(ElectronicValueRWConstantMapper.EVRW_CCS_DETECT.getConstant())) {
			return ElectronicValueRWConstantMapper.EVRW_CCS_DETECT.getContantNumber();
		}

		if (constant.equals(ElectronicValueRWConstantMapper.EVRW_CCS_CAPTURE.getConstant())) {
			return ElectronicValueRWConstantMapper.EVRW_CCS_CAPTURE.getContantNumber();
		}

		if (constant.equals(ElectronicValueRWConstantMapper.EVRW_AL_REPORTING.getConstant())) {
			return ElectronicValueRWConstantMapper.EVRW_AL_REPORTING.getContantNumber();
		}

		if (constant.equals(ElectronicValueRWConstantMapper.EVRW_AL_SETTLEMENT.getConstant())) {
			return ElectronicValueRWConstantMapper.EVRW_AL_SETTLEMENT.getContantNumber();
		}

		if (constant.equals(ElectronicValueRWConstantMapper.EVRW_BD_ANY.getConstant())) {
			return ElectronicValueRWConstantMapper.EVRW_BD_ANY.getContantNumber();
		}

		if (constant.equals(ElectronicValueRWConstantMapper.EVRW_BD_SPECIFIC.getConstant())) {
			return ElectronicValueRWConstantMapper.EVRW_BD_SPECIFIC.getContantNumber();
		}

		if (constant.equals(ElectronicValueRWConstantMapper.EVRW_TA_NORMAL.getConstant())) {
			return ElectronicValueRWConstantMapper.EVRW_TA_NORMAL.getContantNumber();
		}

		if (constant.equals(ElectronicValueRWConstantMapper.EVRW_TA_TRANSACTION.getConstant())) {
			return ElectronicValueRWConstantMapper.EVRW_TA_TRANSACTION.getContantNumber();
		}

		return Integer.parseInt(constant);
	}

	@Override
	public IMapWrapper getTheClass() {
		return this;
	}

}
