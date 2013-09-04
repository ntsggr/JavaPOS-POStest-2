package postest2;

public class KeylockConstantMapper implements IMapWrapper {
	
	// ///////////////////////////////////////////////////////////////////
	// "KeyPosition" Property Constants
	// "WaitForKeylockChange" Method: "KeyPosition" Parameter
	// "StatusUpdateEvent" Event: "status" Parameter
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter LOCK_KP_ANY = new ConstantConverter(0, "LOCK_KP_ANY"); // WaitForKeylockChange Only
	public static final ConstantConverter LOCK_KP_ELECTRONIC = new ConstantConverter(0, "LOCK_KP_ELECTRONIC"); // StatusUpdateEvent Only
	@BelongingProperty(PropertyNames.getDeviceStatus)
	public static final ConstantConverter LOCK_KP_LOCK = new ConstantConverter(1, "LOCK_KP_LOCK");
	@BelongingProperty(PropertyNames.getDeviceStatus)
	public static final ConstantConverter LOCK_KP_NORM = new ConstantConverter(2, "LOCK_KP_NORM");
	@BelongingProperty(PropertyNames.getDeviceStatus)
	public static final ConstantConverter LOCK_KP_SUPR = new ConstantConverter(3, "LOCK_KP_SUPR");

	// ///////////////////////////////////////////////////////////////////
	// "CapKeylockType" Property Constants Added in 1.11
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapKeylockType)
	public static final ConstantConverter LOCK_KT_STANDARD = new ConstantConverter(1, "LOCK_KT_STANDARD");
	@BelongingProperty(PropertyNames.getCapKeylockType)
	public static final ConstantConverter LOCK_KT_ELECTRONIC = new ConstantConverter(2, "LOCK_KT_ELECTRONIC");

	public static int getConstantNumberFromString(String constant){
		
		if(constant.equals(KeylockConstantMapper.LOCK_KT_STANDARD.getConstant())) {
			return KeylockConstantMapper.LOCK_KT_STANDARD.getContantNumber();
		}

		if(constant.equals(KeylockConstantMapper.LOCK_KT_ELECTRONIC.getConstant())) {
			return KeylockConstantMapper.LOCK_KT_ELECTRONIC.getContantNumber();
		}

		if(constant.equals(KeylockConstantMapper.LOCK_KP_ANY.getConstant())) {
			return KeylockConstantMapper.LOCK_KP_ANY.getContantNumber();
		}

		if(constant.equals(KeylockConstantMapper.LOCK_KP_ELECTRONIC.getConstant())) {
			return KeylockConstantMapper.LOCK_KP_ELECTRONIC.getContantNumber();
		}

		if(constant.equals(KeylockConstantMapper.LOCK_KP_LOCK.getConstant())) {
			return KeylockConstantMapper.LOCK_KP_LOCK.getContantNumber();
		}

		if(constant.equals(KeylockConstantMapper.LOCK_KP_NORM.getConstant())) {
			return KeylockConstantMapper.LOCK_KP_NORM.getContantNumber();
		}

		if(constant.equals(KeylockConstantMapper.LOCK_KP_SUPR.getConstant())) {
			return KeylockConstantMapper.LOCK_KP_SUPR.getContantNumber();
		}
		
		return Integer.parseInt(constant);
	}

	@Override
	public IMapWrapper getTheClass() {
		return this;
	}
	
}
