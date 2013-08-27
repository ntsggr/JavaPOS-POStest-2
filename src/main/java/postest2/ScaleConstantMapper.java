package postest2;


public class ScaleConstantMapper {
	
    /////////////////////////////////////////////////////////////////////
    // "StatusNotify" Property Constants
    /////////////////////////////////////////////////////////////////////

    public static final ConstantConverter SCAL_SN_DISABLED = new ConstantConverter(1, "SCAL_SN_DISABLED");
    public static final ConstantConverter SCAL_SN_ENABLED  = new ConstantConverter(2, "SCAL_SN_ENABLED");

    
    public static int getConstantNumberFromString(String constant){
		  
	      if(constant.equals(ScaleConstantMapper.SCAL_SN_DISABLED.getConstant())) {
			  return ScaleConstantMapper.SCAL_SN_DISABLED.getContantNumber();
		  }
		  
		  if(constant.equals(ScaleConstantMapper.SCAL_SN_ENABLED.getConstant())) {
			  return ScaleConstantMapper.SCAL_SN_ENABLED.getContantNumber();
		  }

		  return Integer.parseInt(constant);
	}
	  
    
    
}
