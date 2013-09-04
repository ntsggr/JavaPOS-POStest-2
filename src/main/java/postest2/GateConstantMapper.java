package postest2;

public class GateConstantMapper implements IMapWrapper {

	// ///////////////////////////////////////////////////////////////////
	// "GateStatus" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getGetStatus)
	public static final ConstantConverter GATE_GS_CLOSED = new ConstantConverter(1, "GATE_GS_CLOSED");
	@BelongingProperty(PropertyNames.getGetStatus)
	public static final ConstantConverter GATE_GS_OPEN = new ConstantConverter(2, "GATE_GS_OPEN");
	@BelongingProperty(PropertyNames.getGetStatus)
	public static final ConstantConverter GATE_GS_BLOCKED = new ConstantConverter(3, "GATE_GS_BLOCKED");
	@BelongingProperty(PropertyNames.getGetStatus)
	public static final ConstantConverter GATE_GS_MALFUNCTION = new ConstantConverter(4, "GATE_GS_MALFUNCTION");

	public static int getConstantNumberFromString(String constant) {

		if (constant.equals(GateConstantMapper.GATE_GS_CLOSED.getConstant())) {
			return GateConstantMapper.GATE_GS_CLOSED.getContantNumber();
		}

		if (constant.equals(GateConstantMapper.GATE_GS_OPEN.getConstant())) {
			return GateConstantMapper.GATE_GS_OPEN.getContantNumber();
		}

		if (constant.equals(GateConstantMapper.GATE_GS_BLOCKED.getConstant())) {
			return GateConstantMapper.GATE_GS_BLOCKED.getContantNumber();
		}

		if (constant.equals(GateConstantMapper.GATE_GS_MALFUNCTION.getConstant())) {
			return GateConstantMapper.GATE_GS_MALFUNCTION.getContantNumber();
		}

		return Integer.parseInt(constant);
	}
	
	@Override
	public IMapWrapper getTheClass() {
		return this;
	}

}
