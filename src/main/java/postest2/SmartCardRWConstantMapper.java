package postest2;

public class SmartCardRWConstantMapper implements IMapWrapper {

	// ###################################################################
	// #### Smart Card Constants
	// ###################################################################

	// ///////////////////////////////////////////////////////////////////
	// "CapInterfaceMode" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapInterfaceMode)
	public static final ConstantConverter SC_CMODE_TRANS = new ConstantConverter(1, "SC_CMODE_TRANS");
	@BelongingProperty(PropertyNames.getCapInterfaceMode)
	public static final ConstantConverter SC_CMODE_BLOCK = new ConstantConverter(2, "SC_CMODE_BLOCK");
	@BelongingProperty(PropertyNames.getCapInterfaceMode)
	public static final ConstantConverter SC_CMODE_APDU = new ConstantConverter(4, "SC_CMODE_APDU");
	@BelongingProperty(PropertyNames.getCapInterfaceMode)
	public static final ConstantConverter SC_CMODE_XML = new ConstantConverter(8, "SC_CMODE_XML");

	// ///////////////////////////////////////////////////////////////////
	// "InterfaceMode" Property Constants
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter SC_MODE_TRANS = new ConstantConverter(1, "SC_MODE_TRANS");
	public static final ConstantConverter SC_MODE_BLOCK = new ConstantConverter(2, "SC_MODE_BLOCK");
	public static final ConstantConverter SC_MODE_APDU = new ConstantConverter(4, "SC_MODE_APDU");
	public static final ConstantConverter SC_MODE_XML = new ConstantConverter(8, "SC_MODE_XML");
	
	// ///////////////////////////////////////////////////////////////////
	// "readData" Method, "action" Parameter Constants
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter SC_READ_DATA = new ConstantConverter(11, "SC_READ_DATA");
	public static final ConstantConverter SC_READ_PROGRAM = new ConstantConverter(12, "SC_READ_PROGRAM");
	public static final ConstantConverter SC_EXECUTE_AND_READ_DATA = new ConstantConverter(13,
			"SC_EXECUTE_AND_READ_DATA");
	public static final ConstantConverter SC_XML_READ_BLOCK_DATA = new ConstantConverter(14,
			"SC_XML_READ_BLOCK_DATA");

	// ///////////////////////////////////////////////////////////////////
	// "writeData" Method, "action" Parameter Constants
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter SC_STORE_DATA = new ConstantConverter(21, "SC_STORE_DATA");
	public static final ConstantConverter SC_STORE_PROGRAM = new ConstantConverter(22, "SC_STORE_PROGRAM");
	public static final ConstantConverter SC_EXECUTE_DATA = new ConstantConverter(23, "SC_EXECUTE_DATA");
	public static final ConstantConverter SC_XML_BLOCK_DATA = new ConstantConverter(24, "SC_XML_BLOCK_DATA");
	public static final ConstantConverter SC_SECURITY_FUSE = new ConstantConverter(25, "SC_SECURITY_FUSE");
	public static final ConstantConverter SC_RESET = new ConstantConverter(26, "SC_RESET");

	// ///////////////////////////////////////////////////////////////////
	// "IsoEmvMode" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getIsoEmvMode)
	public static final ConstantConverter SC_MODE_ISO = new ConstantConverter(1, "SC_MODE_ISO");
	@BelongingProperty(PropertyNames.getIsoEmvMode)
	public static final ConstantConverter SC_MODE_EMV = new ConstantConverter(2, "SC_MODE_EMV");

	// ///////////////////////////////////////////////////////////////////
	// "CapIsoEmvMode" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapIsoEmvMode)
	public static final ConstantConverter SC_CMODE_ISO = new ConstantConverter(1, "SC_CMODE_ISO");
	@BelongingProperty(PropertyNames.getCapIsoEmvMode)
	public static final ConstantConverter SC_CMODE_EMV = new ConstantConverter(2, "SC_CMODE_EMV");

	// ///////////////////////////////////////////////////////////////////
	// "CapTransmissionProtocol" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapTransmissionProtocol)
	public static final ConstantConverter SC_CTRANS_PROTOCOL_T0 = new ConstantConverter(1,
			"SC_CTRANS_PROTOCOL_T0");
	@BelongingProperty(PropertyNames.getCapTransmissionProtocol)
	public static final ConstantConverter SC_CTRANS_PROTOCOL_T1 = new ConstantConverter(2,
			"SC_CTRANS_PROTOCOL_T1");

	// ///////////////////////////////////////////////////////////////////
	// "TransmissionProtocol" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getTransmissionProtocol)
	public static final ConstantConverter SC_TRANS_PROTOCOL_T0 = new ConstantConverter(1,
			"SC_TRANS_PROTOCOL_T0");
	@BelongingProperty(PropertyNames.getTransmissionProtocol)
	public static final ConstantConverter SC_TRANS_PROTOCOL_T1 = new ConstantConverter(2,
			"SC_TRANS_PROTOCOL_T1");
	
	
	public static int getConstantNumberFromString(String constant) {

		if (constant.equals(SmartCardRWConstantMapper.SC_CMODE_TRANS.getConstant())) {
			return SmartCardRWConstantMapper.SC_CMODE_TRANS.getContantNumber();
		}

		if (constant.equals(SmartCardRWConstantMapper.SC_CMODE_BLOCK.getConstant())) {
			return SmartCardRWConstantMapper.SC_CMODE_BLOCK.getContantNumber();
		}

		if (constant.equals(SmartCardRWConstantMapper.SC_CMODE_APDU.getConstant())) {
			return SmartCardRWConstantMapper.SC_CMODE_APDU.getContantNumber();
		}

		if (constant.equals(SmartCardRWConstantMapper.SC_CMODE_XML.getConstant())) {
			return SmartCardRWConstantMapper.SC_CMODE_XML.getContantNumber();
		}
		
		if (constant.equals(SmartCardRWConstantMapper.SC_MODE_TRANS.getConstant())) {
			return SmartCardRWConstantMapper.SC_MODE_TRANS.getContantNumber();
		}

		if (constant.equals(SmartCardRWConstantMapper.SC_MODE_BLOCK.getConstant())) {
			return SmartCardRWConstantMapper.SC_MODE_BLOCK.getContantNumber();
		}

		if (constant.equals(SmartCardRWConstantMapper.SC_MODE_APDU.getConstant())) {
			return SmartCardRWConstantMapper.SC_MODE_APDU.getContantNumber();
		}

		if (constant.equals(SmartCardRWConstantMapper.SC_MODE_XML.getConstant())) {
			return SmartCardRWConstantMapper.SC_MODE_XML.getContantNumber();
		}

		if (constant.equals(SmartCardRWConstantMapper.SC_READ_DATA.getConstant())) {
			return SmartCardRWConstantMapper.SC_READ_DATA.getContantNumber();
		}

		if (constant.equals(SmartCardRWConstantMapper.SC_READ_PROGRAM.getConstant())) {
			return SmartCardRWConstantMapper.SC_READ_PROGRAM.getContantNumber();
		}

		if (constant.equals(SmartCardRWConstantMapper.SC_EXECUTE_AND_READ_DATA.getConstant())) {
			return SmartCardRWConstantMapper.SC_EXECUTE_AND_READ_DATA.getContantNumber();
		}

		if (constant.equals(SmartCardRWConstantMapper.SC_XML_READ_BLOCK_DATA.getConstant())) {
			return SmartCardRWConstantMapper.SC_XML_READ_BLOCK_DATA.getContantNumber();
		}

		if (constant.equals(SmartCardRWConstantMapper.SC_STORE_DATA.getConstant())) {
			return SmartCardRWConstantMapper.SC_STORE_DATA.getContantNumber();
		}

		if (constant.equals(SmartCardRWConstantMapper.SC_STORE_PROGRAM.getConstant())) {
			return SmartCardRWConstantMapper.SC_STORE_PROGRAM.getContantNumber();
		}

		if (constant.equals(SmartCardRWConstantMapper.SC_EXECUTE_DATA.getConstant())) {
			return SmartCardRWConstantMapper.SC_EXECUTE_DATA.getContantNumber();
		}

		if (constant.equals(SmartCardRWConstantMapper.SC_XML_BLOCK_DATA.getConstant())) {
			return SmartCardRWConstantMapper.SC_XML_BLOCK_DATA.getContantNumber();
		}

		if (constant.equals(SmartCardRWConstantMapper.SC_SECURITY_FUSE.getConstant())) {
			return SmartCardRWConstantMapper.SC_SECURITY_FUSE.getContantNumber();
		}

		if (constant.equals(SmartCardRWConstantMapper.SC_RESET.getConstant())) {
			return SmartCardRWConstantMapper.SC_RESET.getContantNumber();
		}

		if (constant.equals(SmartCardRWConstantMapper.SC_MODE_ISO.getConstant())) {
			return SmartCardRWConstantMapper.SC_MODE_ISO.getContantNumber();
		}

		if (constant.equals(SmartCardRWConstantMapper.SC_MODE_EMV.getConstant())) {
			return SmartCardRWConstantMapper.SC_MODE_EMV.getContantNumber();
		}

		if (constant.equals(SmartCardRWConstantMapper.SC_CMODE_ISO.getConstant())) {
			return SmartCardRWConstantMapper.SC_CMODE_ISO.getContantNumber();
		}

		if (constant.equals(SmartCardRWConstantMapper.SC_CMODE_EMV.getConstant())) {
			return SmartCardRWConstantMapper.SC_CMODE_EMV.getContantNumber();
		}

		if (constant.equals(SmartCardRWConstantMapper.SC_CTRANS_PROTOCOL_T0.getConstant())) {
			return SmartCardRWConstantMapper.SC_CTRANS_PROTOCOL_T0.getContantNumber();
		}

		if (constant.equals(SmartCardRWConstantMapper.SC_CTRANS_PROTOCOL_T1.getConstant())) {
			return SmartCardRWConstantMapper.SC_CTRANS_PROTOCOL_T1.getContantNumber();
		}

		if (constant.equals(SmartCardRWConstantMapper.SC_TRANS_PROTOCOL_T0.getConstant())) {
			return SmartCardRWConstantMapper.SC_TRANS_PROTOCOL_T0.getContantNumber();
		}

		if (constant.equals(SmartCardRWConstantMapper.SC_TRANS_PROTOCOL_T1.getConstant())) {
			return SmartCardRWConstantMapper.SC_TRANS_PROTOCOL_T1.getContantNumber();
		}

		return Integer.parseInt(constant);
	}

	@Override
	public IMapWrapper getTheClass() {
		return this;
	}

}
