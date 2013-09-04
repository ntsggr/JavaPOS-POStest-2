package postest2;

public class MICRConstantMapper implements IMapWrapper {

	// ///////////////////////////////////////////////////////////////////
	// "CheckType" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCheckType)
	public static final ConstantConverter MICR_CT_PERSONAL = new ConstantConverter(1, "MICR_CT_PERSONAL");
	@BelongingProperty(PropertyNames.getCheckType)
	public static final ConstantConverter MICR_CT_BUSINESS = new ConstantConverter(2, "MICR_CT_BUSINESS");
	@BelongingProperty(PropertyNames.getCheckType)
	public static final ConstantConverter MICR_CT_UNKNOWN = new ConstantConverter(99, "MICR_CT_UNKNOWN");

	// ///////////////////////////////////////////////////////////////////
	// "CountryCode" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCountryCode)
	public static final ConstantConverter MICR_CC_USA = new ConstantConverter(1, "MICR_CC_USA");
	@BelongingProperty(PropertyNames.getCountryCode)
	public static final ConstantConverter MICR_CC_CANADA = new ConstantConverter(2, "MICR_CC_CANADA");
	@BelongingProperty(PropertyNames.getCountryCode)
	public static final ConstantConverter MICR_CC_MEXICO = new ConstantConverter(3, "MICR_CC_MEXICO");
	@BelongingProperty(PropertyNames.getCountryCode)
	public static final ConstantConverter MICR_CC_CMC7 = new ConstantConverter(4, "MICR_CC_CMC7");
	@BelongingProperty(PropertyNames.getCountryCode)
	public static final ConstantConverter MICR_CC_UNKNOWN = new ConstantConverter(99, "MICR_CC_UNKNOWN");
	

	public static int getConstantNumberFromString(String constant) {
		
		if (constant.equals(MICRConstantMapper.MICR_CC_USA.getConstant())) {
			return MICRConstantMapper.MICR_CC_USA.getContantNumber();
		}

		if (constant.equals(MICRConstantMapper.MICR_CC_CANADA.getConstant())) {
			return MICRConstantMapper.MICR_CC_CANADA.getContantNumber();
		}

		if (constant.equals(MICRConstantMapper.MICR_CC_MEXICO.getConstant())) {
			return MICRConstantMapper.MICR_CC_MEXICO.getContantNumber();
		}
		
		if (constant.equals(MICRConstantMapper.MICR_CC_CMC7.getConstant())) {
			return MICRConstantMapper.MICR_CC_CMC7.getContantNumber();
		}

		if (constant.equals(MICRConstantMapper.MICR_CC_UNKNOWN.getConstant())) {
			return MICRConstantMapper.MICR_CC_UNKNOWN.getContantNumber();
		}

		if (constant.equals(MICRConstantMapper.MICR_CT_PERSONAL.getConstant())) {
			return MICRConstantMapper.MICR_CT_PERSONAL.getContantNumber();
		}

		if (constant.equals(MICRConstantMapper.MICR_CT_BUSINESS.getConstant())) {
			return MICRConstantMapper.MICR_CT_BUSINESS.getContantNumber();
		}

		if (constant.equals(MICRConstantMapper.MICR_CT_UNKNOWN.getConstant())) {
			return MICRConstantMapper.MICR_CT_UNKNOWN.getContantNumber();
		}

		return Integer.parseInt(constant);
	}

	@Override
	public IMapWrapper getTheClass() {
		return this;
	}

}
