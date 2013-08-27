package postest2;


public class SmartCardRWConstantMapper {
	
	 //###################################################################
	  //#### Smart Card Constants
	  //###################################################################

	  /////////////////////////////////////////////////////////////////////
	  // "InterfaceMode" Property Constants
	  /////////////////////////////////////////////////////////////////////

	  public static final ConstantConverter SC_MODE_TRANS = new ConstantConverter( 1, "SC_MODE_TRANS");  // Simple Transaction Command and Data Mode.
	  public static final ConstantConverter SC_MODE_BLOCK = new ConstantConverter( 2, "SC_MODE_BLOCK");  // Block Data Mode.
	  public static final ConstantConverter SC_MODE_APDU  = new ConstantConverter( 4, "SC_MODE_APDU");  // Same as Block Data Mode except APDU Standard Defines the Commands and data.
	  public static final ConstantConverter SC_MODE_XML   = new ConstantConverter( 8, "SC_MODE_XML");  // XML Block Data Mode.


	  /////////////////////////////////////////////////////////////////////
	  // "readData" Method, "action" Parameter Constants
	  /////////////////////////////////////////////////////////////////////

	  public static final ConstantConverter SC_READ_DATA             = new ConstantConverter( 11, "SC_READ_DATA");
	  public static final ConstantConverter SC_READ_PROGRAM          = new ConstantConverter( 12, "SC_READ_PROGRAM");
	  public static final ConstantConverter SC_EXECUTE_AND_READ_DATA = new ConstantConverter( 13, "SC_EXECUTE_AND_READ_DATA");
	  public static final ConstantConverter SC_XML_READ_BLOCK_DATA   = new ConstantConverter( 14, "SC_XML_READ_BLOCK_DATA");


	  /////////////////////////////////////////////////////////////////////
	  // "writeData" Method, "action" Parameter Constants
	  /////////////////////////////////////////////////////////////////////

	  public static final ConstantConverter SC_STORE_DATA     = new ConstantConverter( 21, "SC_STORE_DATA");
	  public static final ConstantConverter SC_STORE_PROGRAM  = new ConstantConverter( 22, "SC_STORE_PROGRAM");
	  public static final ConstantConverter SC_EXECUTE_DATA   = new ConstantConverter( 23, "SC_EXECUTE_DATA");
	  public static final ConstantConverter SC_XML_BLOCK_DATA = new ConstantConverter( 24, "SC_XML_BLOCK_DATA");
	  public static final ConstantConverter SC_SECURITY_FUSE  = new ConstantConverter( 25, "SC_SECURITY_FUSE");
	  public static final ConstantConverter SC_RESET          = new ConstantConverter( 26, "SC_RESET");

	  
	  public static int getConstantNumberFromString(String constant){
		  
	      if(constant.equals(SmartCardRWConstantMapper.SC_MODE_TRANS.getConstant())) {
			  return SmartCardRWConstantMapper.SC_MODE_TRANS.getContantNumber();
		  }
		  
		  if(constant.equals(SmartCardRWConstantMapper.SC_MODE_BLOCK.getConstant())) {
			  return SmartCardRWConstantMapper.SC_MODE_BLOCK.getContantNumber();
		  }

  
		  if(constant.equals(SmartCardRWConstantMapper.SC_MODE_APDU.getConstant())) {
			  return SmartCardRWConstantMapper.SC_MODE_APDU.getContantNumber();
		  }

		  if(constant.equals(SmartCardRWConstantMapper.SC_MODE_XML.getConstant())) {
			  return SmartCardRWConstantMapper.SC_MODE_XML.getContantNumber();
		  }
		  
	      if(constant.equals(SmartCardRWConstantMapper.SC_READ_DATA.getConstant())) {
			  return SmartCardRWConstantMapper.SC_READ_DATA.getContantNumber();
		  }
		  
		  if(constant.equals(SmartCardRWConstantMapper.SC_READ_PROGRAM.getConstant())) {
			  return SmartCardRWConstantMapper.SC_READ_PROGRAM.getContantNumber();
		  }

  
		  if(constant.equals(SmartCardRWConstantMapper.SC_EXECUTE_AND_READ_DATA.getConstant())) {
			  return SmartCardRWConstantMapper.SC_EXECUTE_AND_READ_DATA.getContantNumber();
		  }

		  if(constant.equals(SmartCardRWConstantMapper.SC_XML_READ_BLOCK_DATA.getConstant())) {
			  return SmartCardRWConstantMapper.SC_XML_READ_BLOCK_DATA.getContantNumber();
		  }
		  
	      if(constant.equals(SmartCardRWConstantMapper.SC_STORE_DATA.getConstant())) {
			  return SmartCardRWConstantMapper.SC_STORE_DATA.getContantNumber();
		  }
		  
		  if(constant.equals(SmartCardRWConstantMapper.SC_STORE_PROGRAM.getConstant())) {
			  return SmartCardRWConstantMapper.SC_STORE_PROGRAM.getContantNumber();
		  }

  
		  if(constant.equals(SmartCardRWConstantMapper.SC_EXECUTE_DATA.getConstant())) {
			  return SmartCardRWConstantMapper.SC_EXECUTE_DATA.getContantNumber();
		  }

		  if(constant.equals(SmartCardRWConstantMapper.SC_XML_BLOCK_DATA.getConstant())) {
			  return SmartCardRWConstantMapper.SC_XML_BLOCK_DATA.getContantNumber();
		  }

  
		  if(constant.equals(SmartCardRWConstantMapper.SC_SECURITY_FUSE.getConstant())) {
			  return SmartCardRWConstantMapper.SC_SECURITY_FUSE.getContantNumber();
		  }

		  if(constant.equals(SmartCardRWConstantMapper.SC_RESET.getConstant())) {
			  return SmartCardRWConstantMapper.SC_RESET.getContantNumber();
		  }
		  
		  return Integer.parseInt(constant);
	}
	  
}
