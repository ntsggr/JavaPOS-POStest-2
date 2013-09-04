package postest2;


public class LightsConstantMapper implements IMapWrapper { 
	
	// ///////////////////////////////////////////////////////////////////
	// "CapAlarm" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapAlarm)
	public static final ConstantConverter LGT_ALARM_NOALARM = new ConstantConverter(0x00000001, "LGT_ALARM_NOALARM");
	@BelongingProperty(PropertyNames.getCapAlarm)
	public static final ConstantConverter LGT_ALARM_SLOW = new ConstantConverter(0x00000010, "LGT_ALARM_SLOW");
	@BelongingProperty(PropertyNames.getCapAlarm)
	public static final ConstantConverter LGT_ALARM_MEDIUM = new ConstantConverter(0x00000020, "LGT_ALARM_MEDIUM");
	@BelongingProperty(PropertyNames.getCapAlarm)
	public static final ConstantConverter LGT_ALARM_FAST = new ConstantConverter(0x00000040, "LGT_ALARM_FAST");
	@BelongingProperty(PropertyNames.getCapAlarm)
	public static final ConstantConverter LGT_ALARM_CUSTOM1 = new ConstantConverter(0x00010000, "LGT_ALARM_CUSTOM1");
	@BelongingProperty(PropertyNames.getCapAlarm)
	public static final ConstantConverter LGT_ALARM_CUSTOM2 = new ConstantConverter(0x00020000, "LGT_ALARM_CUSTOM2");

	// ///////////////////////////////////////////////////////////////////
	// "CapColor" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapColor)
	public static final ConstantConverter LGT_COLOR_PRIMARY = new ConstantConverter(0x00000001, "LGT_COLOR_PRIMARY");
	@BelongingProperty(PropertyNames.getCapColor)
	public static final ConstantConverter LGT_COLOR_CUSTOM1 = new ConstantConverter(0x00010000, "LGT_COLOR_CUSTOM1");
	@BelongingProperty(PropertyNames.getCapColor)
	public static final ConstantConverter LGT_COLOR_CUSTOM2 = new ConstantConverter(0x00020000, "LGT_COLOR_CUSTOM2");
	@BelongingProperty(PropertyNames.getCapColor)
	public static final ConstantConverter LGT_COLOR_CUSTOM3 = new ConstantConverter(0x00040000, "LGT_COLOR_CUSTOM3");
	@BelongingProperty(PropertyNames.getCapColor)
	public static final ConstantConverter LGT_COLOR_CUSTOM4 = new ConstantConverter(0x00080000, "LGT_COLOR_CUSTOM4");
	@BelongingProperty(PropertyNames.getCapColor)
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

	@Override
	public IMapWrapper getTheClass() {
		return this;
	}
	
	
}
