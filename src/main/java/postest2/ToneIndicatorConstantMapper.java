package postest2;

public class ToneIndicatorConstantMapper implements IMapWrapper {
	// ###################################################################
	// #### Tone Indicator Constants
	// ###################################################################

	// ///////////////////////////////////////////////////////////////////
	// "MelodyType" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getMelodyType)
	public static final ConstantConverter TONE_MT_NONE = new ConstantConverter(0, "TONE_MT_NONE");

	public static int getConstantNumberFromString(String constant) {

		if (constant.equals(ToneIndicatorConstantMapper.TONE_MT_NONE.getConstant())) {
			return ToneIndicatorConstantMapper.TONE_MT_NONE.getContantNumber();
		}
		return Integer.parseInt(constant);
	}

	@Override
	public IMapWrapper getTheClass() {
		return this;
	}

}
