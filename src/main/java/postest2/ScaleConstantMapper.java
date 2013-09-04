package postest2;

public class ScaleConstantMapper implements IMapWrapper {

	// ///////////////////////////////////////////////////////////////////
	// "StatusNotify" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getStatusNotify)
	public static final ConstantConverter SCAL_SN_DISABLED = new ConstantConverter(1, "SCAL_SN_DISABLED");
	@BelongingProperty(PropertyNames.getStatusNotify)
	public static final ConstantConverter SCAL_SN_ENABLED = new ConstantConverter(2, "SCAL_SN_ENABLED");

	// ///////////////////////////////////////////////////////////////////
	// "WeightUnit" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getWeightUnit)
	public static final ConstantConverter SCAL_WU_GRAM = new ConstantConverter(1, "SCAL_WU_GRAM");
	@BelongingProperty(PropertyNames.getWeightUnit)
	public static final ConstantConverter SCAL_WU_KILOGRAM = new ConstantConverter(2, "SCAL_WU_KILOGRAM");
	@BelongingProperty(PropertyNames.getWeightUnit)
	public static final ConstantConverter SCAL_WU_OUNCE = new ConstantConverter(3, "SCAL_WU_OUNCE");
	@BelongingProperty(PropertyNames.getWeightUnit)
	public static final ConstantConverter SCAL_WU_POUND = new ConstantConverter(4, "SCAL_WU_POUND");
	

	public static int getConstantNumberFromString(String constant) {
		
		if (constant.equals(ScaleConstantMapper.SCAL_WU_GRAM.getConstant())) {
			return ScaleConstantMapper.SCAL_WU_GRAM.getContantNumber();
		}

		if (constant.equals(ScaleConstantMapper.SCAL_WU_KILOGRAM.getConstant())) {
			return ScaleConstantMapper.SCAL_WU_KILOGRAM.getContantNumber();
		}
		
		if (constant.equals(ScaleConstantMapper.SCAL_WU_OUNCE.getConstant())) {
			return ScaleConstantMapper.SCAL_WU_OUNCE.getContantNumber();
		}

		if (constant.equals(ScaleConstantMapper.SCAL_WU_POUND.getConstant())) {
			return ScaleConstantMapper.SCAL_WU_POUND.getContantNumber();
		}

		if (constant.equals(ScaleConstantMapper.SCAL_SN_DISABLED.getConstant())) {
			return ScaleConstantMapper.SCAL_SN_DISABLED.getContantNumber();
		}

		if (constant.equals(ScaleConstantMapper.SCAL_SN_ENABLED.getConstant())) {
			return ScaleConstantMapper.SCAL_SN_ENABLED.getContantNumber();
		}

		return Integer.parseInt(constant);
	}

	@Override
	public IMapWrapper getTheClass() {
		return this;
	}

}
