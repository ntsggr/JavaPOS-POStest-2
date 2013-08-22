package postest2;

public class KeylockConstantMapper {
	// ///////////////////////////////////////////////////////////////////
	// "KeyPosition" Property Constants
	// "WaitForKeylockChange" Method: "KeyPosition" Parameter
	// "StatusUpdateEvent" Event: "status" Parameter
	// ///////////////////////////////////////////////////////////////////

	public static final ConstantConverter LOCK_KP_ANY = new ConstantConverter(0, "LOCK_KP_ANY"); // WaitForKeylockChange Only
	public static final ConstantConverter LOCK_KP_ELECTRONIC = new ConstantConverter(0, "LOCK_KP_ELECTRONIC"); // StatusUpdateEvent Only
													// (1.11)
	public static final ConstantConverter LOCK_KP_LOCK = new ConstantConverter(1, "LOCK_KP_LOCK");
	public static final ConstantConverter LOCK_KP_NORM = new ConstantConverter(2, "LOCK_KP_NORM");
	public static final ConstantConverter LOCK_KP_SUPR = new ConstantConverter(3, "LOCK_KP_SUPR");
	
	public static int getConstantNumberFromString(String constant){

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
	
}
