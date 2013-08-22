package postest2;


public class LightsConstantMapper { 
	// ///////////////////////////////////////////////////////////////////
	// "CapAlarm" Property Constants
	// ///////////////////////////////////////////////////////////////////

	public static final ConstantConverter LGT_ALARM_NOALARM = new ConstantConverter(0x00000001, "LGT_ALARM_NOALARM");
	public static final ConstantConverter LGT_ALARM_SLOW = new ConstantConverter(0x00000010, "LGT_ALARM_SLOW");
	public static final ConstantConverter LGT_ALARM_MEDIUM = new ConstantConverter(0x00000020, "LGT_ALARM_MEDIUM");
	public static final ConstantConverter LGT_ALARM_FAST = new ConstantConverter(0x00000040, "LGT_ALARM_FAST");
	public static final ConstantConverter LGT_ALARM_CUSTOM1 = new ConstantConverter(0x00010000, "LGT_ALARM_CUSTOM1");
	public static final ConstantConverter LGT_ALARM_CUSTOM2 = new ConstantConverter(0x00020000, "LGT_ALARM_CUSTOM2");

	// ///////////////////////////////////////////////////////////////////
	// "CapColor" Property Constants
	// ///////////////////////////////////////////////////////////////////

	public static final ConstantConverter LGT_COLOR_PRIMARY = new ConstantConverter(0x00000001, "LGT_COLOR_PRIMARY");
	public static final ConstantConverter LGT_COLOR_CUSTOM1 = new ConstantConverter(0x00010000, "LGT_COLOR_CUSTOM1");
	public static final ConstantConverter LGT_COLOR_CUSTOM2 = new ConstantConverter(0x00020000, "LGT_COLOR_CUSTOM2");
	public static final ConstantConverter LGT_COLOR_CUSTOM3 = new ConstantConverter(0x00040000, "LGT_COLOR_CUSTOM3");
	public static final ConstantConverter LGT_COLOR_CUSTOM4 = new ConstantConverter(0x00080000, "LGT_COLOR_CUSTOM4");
	public static final ConstantConverter LGT_COLOR_CUSTOM5 = new ConstantConverter(0x00100000, "LGT_COLOR_CUSTOM5");
	
	public static int getConstantNumberFromString(String constant){

		if(constant.equals(LightsConstantMapper.LGT_ALARM_CUSTOM1.getConstant())) {
			return LightsConstantMapper.LGT_ALARM_CUSTOM1.getContantNumber();
		}

		if(constant.equals(LightsConstantMapper.LGT_ALARM_CUSTOM2.getConstant())) {
			return LightsConstantMapper.LGT_ALARM_CUSTOM2.getContantNumber();
		}

		if(constant.equals(LightsConstantMapper.LGT_ALARM_FAST.getConstant())) {
			return LightsConstantMapper.LGT_ALARM_FAST.getContantNumber();
		}

		if(constant.equals(LightsConstantMapper.LGT_ALARM_MEDIUM.getConstant())) {
			return LightsConstantMapper.LGT_ALARM_MEDIUM.getContantNumber();
		}

		if(constant.equals(LightsConstantMapper.LGT_ALARM_NOALARM.getConstant())) {
			return LightsConstantMapper.LGT_ALARM_NOALARM.getContantNumber();
		}
		
		if(constant.equals(LightsConstantMapper.LGT_ALARM_SLOW.getConstant())) {
			return LightsConstantMapper.LGT_ALARM_SLOW.getContantNumber();
		}

		if(constant.equals(LightsConstantMapper.LGT_COLOR_CUSTOM1.getConstant())) {
			return LightsConstantMapper.LGT_COLOR_CUSTOM1.getContantNumber();
		}

		if(constant.equals(LightsConstantMapper.LGT_COLOR_CUSTOM2.getConstant())) {
			return LightsConstantMapper.LGT_COLOR_CUSTOM2.getContantNumber();
		}

		if(constant.equals(LightsConstantMapper.LGT_COLOR_CUSTOM3.getConstant())) {
			return LightsConstantMapper.LGT_COLOR_CUSTOM3.getContantNumber();
		}

		if(constant.equals(LightsConstantMapper.LGT_COLOR_CUSTOM4.getConstant())) {
			return LightsConstantMapper.LGT_COLOR_CUSTOM4.getContantNumber();
		}

		if(constant.equals(LightsConstantMapper.LGT_COLOR_CUSTOM5.getConstant())) {
			return LightsConstantMapper.LGT_COLOR_CUSTOM5.getContantNumber();
		}
		
		if(constant.equals(LightsConstantMapper.LGT_COLOR_PRIMARY.getConstant())) {
			return LightsConstantMapper.LGT_COLOR_PRIMARY.getContantNumber();
		}
		
		return Integer.parseInt(constant);
	}
	
	
}
