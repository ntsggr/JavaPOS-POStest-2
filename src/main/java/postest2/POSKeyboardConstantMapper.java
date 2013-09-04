package postest2;

public class POSKeyboardConstantMapper implements IMapWrapper {

	// ///////////////////////////////////////////////////////////////////
	// "EventTypes" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getEventTypes)
	public static final ConstantConverter KBD_ET_DOWN = new ConstantConverter(1, "KBD_ET_DOWN");
	public static final ConstantConverter KBD_ET_DOWN_UP = new ConstantConverter(2, "KBD_ET_DOWN_UP");

	// ///////////////////////////////////////////////////////////////////
	// "POSKeyEventType" Property Constants
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter KBD_KET_KEYDOWN = new ConstantConverter(1, "KBD_KET_KEYDOWN");
	public static final ConstantConverter KBD_KET_KEYUP = new ConstantConverter(2, "KBD_KET_KEYUP");

	
	public static int getConstantNumberFromString(String constant) {

		if (constant.equals(POSKeyboardConstantMapper.KBD_ET_DOWN.getConstant())) {
			return POSKeyboardConstantMapper.KBD_ET_DOWN.getContantNumber();
		}
		
		if (constant.equals(POSKeyboardConstantMapper.KBD_ET_DOWN_UP.getConstant())) {
			return POSKeyboardConstantMapper.KBD_ET_DOWN_UP.getContantNumber();
		}
		
		if (constant.equals(POSKeyboardConstantMapper.KBD_KET_KEYDOWN.getConstant())) {
			return POSKeyboardConstantMapper.KBD_KET_KEYDOWN.getContantNumber();
		}
		
		if (constant.equals(POSKeyboardConstantMapper.KBD_KET_KEYUP.getConstant())) {
			return POSKeyboardConstantMapper.KBD_KET_KEYUP.getContantNumber();
		}

		return Integer.parseInt(constant);
	}

	@Override
	public IMapWrapper getTheClass() {
		return this;
	}

}
