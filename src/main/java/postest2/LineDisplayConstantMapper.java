package postest2;

public class LineDisplayConstantMapper {
	
	
	//###################################################################
	//#### Line Display Constants
	//###################################################################
	
	/////////////////////////////////////////////////////////////////////
	// "CapBlink" Property Constants
	/////////////////////////////////////////////////////////////////////
	public static final ConstantConverter DISP_CB_NOBLINK      = new ConstantConverter(0, "DISP_CB_NOBLINK");
	public static final ConstantConverter DISP_CB_BLINKALL     = new ConstantConverter(1, "DISP_CB_BLINKALL");
	public static final ConstantConverter DISP_CB_BLINKEACH    = new ConstantConverter(2, "DISP_CB_BLINKEACH");
	
	/////////////////////////////////////////////////////////////////////
	// "CapCharacterSet" Property Constants
	/////////////////////////////////////////////////////////////////////
	public static final ConstantConverter DISP_CCS_NUMERIC     = new ConstantConverter(0, "DISP_CCS_NUMERIC");
	public static final ConstantConverter DISP_CCS_ALPHA       = new ConstantConverter(1, "DISP_CCS_ALPHA");
	public static final ConstantConverter DISP_CCS_ASCII       = new ConstantConverter(998, "DISP_CCS_ASCII");
	public static final ConstantConverter DISP_CCS_KANA        = new ConstantConverter(10, "DISP_CCS_KANA");
	public static final ConstantConverter DISP_CCS_KANJI       = new ConstantConverter(11, "DISP_CCS_KANJI");
	public static final ConstantConverter DISP_CCS_UNICODE     = new ConstantConverter(997, "DISP_CCS_UNICODE");
	
	
	/////////////////////////////////////////////////////////////////////
	// "CapCursorType" Property Constants
	/////////////////////////////////////////////////////////////////////
	public static final ConstantConverter DISP_CCT_NONE        = new ConstantConverter(0x00000000, "DISP_CCT_NONE");
	public static final ConstantConverter DISP_CCT_FIXED       = new ConstantConverter(0x00000001, "DISP_CCT_FIXED");
	public static final ConstantConverter DISP_CCT_BLOCK       = new ConstantConverter(0x00000002, "DISP_CCT_BLOCK");
	public static final ConstantConverter DISP_CCT_HALFBLOCK   = new ConstantConverter(0x00000004, "DISP_CCT_HALFBLOCK");
	public static final ConstantConverter DISP_CCT_UNDERLINE   = new ConstantConverter(0x00000008, "DISP_CCT_UNDERLINE");
	public static final ConstantConverter DISP_CCT_REVERSE     = new ConstantConverter(0x00000010, "DISP_CCT_REVERSE");
	public static final ConstantConverter DISP_CCT_OTHER       = new ConstantConverter(0x00000020, "DISP_CCT_OTHER");
	
	// Added in Release 1.8
	public static final ConstantConverter DISP_CCT_BLINK       = new ConstantConverter(  0x00000040, "DISP_CCT_BLINK");
	
	
	/////////////////////////////////////////////////////////////////////
	// "CapReadBack" Property Constants
	/////////////////////////////////////////////////////////////////////
	public static final ConstantConverter DISP_CRB_NONE        = new ConstantConverter(  0x00000000, "DISP_CRB_NONE");
	public static final ConstantConverter DISP_CRB_SINGLE      = new ConstantConverter(  0x00000001, "DISP_CRB_SINGLE");
	
	
	/////////////////////////////////////////////////////////////////////
	// "CapReverse" Property Constants
	/////////////////////////////////////////////////////////////////////
	public static final ConstantConverter DISP_CR_NONE          = new ConstantConverter(  0x00000000, "DISP_CR_NONE");
	public static final ConstantConverter DISP_CR_REVERSEALL    = new ConstantConverter(  0x00000001, "DISP_CR_REVERSEALL");
	public static final ConstantConverter DISP_CR_REVERSEEACH   = new ConstantConverter(  0x00000002, "DISP_CR_REVERSEEACH");
	
	
	/////////////////////////////////////////////////////////////////////
	// "CharacterSet" Property Constants
	/////////////////////////////////////////////////////////////////////
	public static final ConstantConverter DISP_CS_UNICODE      = new ConstantConverter(997, "DISP_CS_UNICODE");
	public static final ConstantConverter DISP_CS_ASCII        = new ConstantConverter(998, "DISP_CS_ASCII");
	public static final ConstantConverter DISP_CS_ANSI         = new ConstantConverter(999, "DISP_CS_ANSI");
	
	
	/////////////////////////////////////////////////////////////////////
	// "CursorType" Property Constants
	/////////////////////////////////////////////////////////////////////
	public static final ConstantConverter DISP_CT_NONE        = new ConstantConverter(  0, "DISP_CT_NONE");
	public static final ConstantConverter DISP_CT_FIXED       = new ConstantConverter(  1, "DISP_CT_FIXED");
	public static final ConstantConverter DISP_CT_BLOCK       = new ConstantConverter(  2, "DISP_CT_BLOCK");
	public static final ConstantConverter DISP_CT_HALFBLOCK   = new ConstantConverter(  3, "DISP_CT_HALFBLOCK");
	public static final ConstantConverter DISP_CT_UNDERLINE   = new ConstantConverter(  4, "DISP_CT_UNDERLINE");
	public static final ConstantConverter DISP_CT_REVERSE     = new ConstantConverter(  5, "DISP_CT_REVERSE");
	public static final ConstantConverter DISP_CT_OTHER       = new ConstantConverter(  6, "DISP_CT_OTHER");
	
	// Added in Release 1.8
	public static final ConstantConverter DISP_CT_BLINK       = new ConstantConverter(  0x10000000, "DISP_CT_BLINK");
	
	
	/////////////////////////////////////////////////////////////////////
	// "MarqueeType" Property Constants
	/////////////////////////////////////////////////////////////////////
	public static final ConstantConverter DISP_MT_NONE         = new ConstantConverter(0, "DISP_MT_NONE");
	public static final ConstantConverter DISP_MT_UP           = new ConstantConverter(1, "DISP_MT_UP");
	public static final ConstantConverter DISP_MT_DOWN         = new ConstantConverter(2, "DISP_MT_DOWN");
	public static final ConstantConverter DISP_MT_LEFT         = new ConstantConverter(3, "DISP_MT_LEFT");
	public static final ConstantConverter DISP_MT_RIGHT        = new ConstantConverter(4, "DISP_MT_RIGHT");
	public static final ConstantConverter DISP_MT_INIT         = new ConstantConverter(5, "DISP_MT_INIT");
	
	
	/////////////////////////////////////////////////////////////////////
	// "MarqueeFormat" Property Constants
	/////////////////////////////////////////////////////////////////////
	public static final ConstantConverter DISP_MF_WALK         = new ConstantConverter(0, "DISP_MF_WALK");
	public static final ConstantConverter DISP_MF_PLACE        = new ConstantConverter(1, "DISP_MF_PLACE");
	
	
	/////////////////////////////////////////////////////////////////////
	// "DefineGlyph" Method: "GlyphType" Parameter Constants
	/////////////////////////////////////////////////////////////////////
	public static final ConstantConverter DISP_GT_SINGLE       = new ConstantConverter(1, "DISP_GT_SINGLE");
	
	
	/////////////////////////////////////////////////////////////////////
	// "DisplayText" Method: "Attribute" Property Constants
	// "DisplayTextAt" Method: "Attribute" Property Constants
	/////////////////////////////////////////////////////////////////////
	public static final ConstantConverter DISP_DT_NORMAL        = new ConstantConverter(0, "DISP_DT_NORMAL");
	public static final ConstantConverter DISP_DT_BLINK         = new ConstantConverter(1, "DISP_DT_BLINK");
	public static final ConstantConverter DISP_DT_REVERSE       = new ConstantConverter(2, "DISP_DT_REVERSE");
	public static final ConstantConverter DISP_DT_BLINK_REVERSE = new ConstantConverter(3, "DISP_DT_BLINK_REVERSE");
	
	
	/////////////////////////////////////////////////////////////////////
	// "ScrollText" Method: "Direction" Parameter Constants
	/////////////////////////////////////////////////////////////////////
	public static final ConstantConverter DISP_ST_UP           = new ConstantConverter(1, "DISP_ST_UP");
	public static final ConstantConverter DISP_ST_DOWN         = new ConstantConverter(2, "DISP_ST_DOWN");
	public static final ConstantConverter DISP_ST_LEFT         = new ConstantConverter(3, "DISP_ST_LEFT");
	public static final ConstantConverter DISP_ST_RIGHT        = new ConstantConverter(4, "DISP_ST_RIGHT");
	
	
	/////////////////////////////////////////////////////////////////////
	// "SetDescriptor" Method: "Attribute" Parameter Constants
	/////////////////////////////////////////////////////////////////////
	public static final ConstantConverter DISP_SD_OFF          = new ConstantConverter(0, "DISP_SD_OFF");
	public static final ConstantConverter DISP_SD_ON           = new ConstantConverter(1, "DISP_SD_ON");
	public static final ConstantConverter DISP_SD_BLINK        = new ConstantConverter(2, "DISP_SD_BLINK");
	
	
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	// The following were added in Release 1.7
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////////////////////////////////////////
	// "displayBitmap" and "setBitmap" Method Constants:
	/////////////////////////////////////////////////////////////////////
	
	//   "Width" Parameter
	
	public static final ConstantConverter DISP_BM_ASIS          = new ConstantConverter(-11, "DISP_BM_ASIS");
	
	//   "AlignmentX" Parameter
	
	public static final ConstantConverter DISP_BM_LEFT          = new ConstantConverter(-1, "DISP_BM_LEFT");
	public static final ConstantConverter DISP_BM_CENTER        = new ConstantConverter(-2, "DISP_BM_CENTER");
	public static final ConstantConverter DISP_BM_RIGHT         = new ConstantConverter(-3, "DISP_BM_RIGHT");
	
	//   "AlignmentY" Parameter
	
	public static final ConstantConverter DISP_BM_TOP           = new ConstantConverter(-1, "DISP_BM_TOP");
	public static final ConstantConverter DISP_BM_BOTTOM        = new ConstantConverter(-3, "DISP_BM_BOTTOM");
	
	/**
	 * Get Constant Number from String - Needed because ComboBoxes just hold the
	 * String and not the Object
	 * 
	 * @param constant
	 * @return
	 */
	public static int getConstantNumberFromString(String constant){
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CB_NOBLINK.getConstant())) {
			return LineDisplayConstantMapper.DISP_CB_NOBLINK.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CB_BLINKALL.getConstant())) {
			return LineDisplayConstantMapper.DISP_CB_BLINKALL.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CB_BLINKEACH.getConstant())) {
			return LineDisplayConstantMapper.DISP_CB_BLINKEACH.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CCS_NUMERIC.getConstant())) {
			return LineDisplayConstantMapper.DISP_CCS_NUMERIC.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CCS_ALPHA.getConstant())) {
			return LineDisplayConstantMapper.DISP_CCS_ALPHA.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CCS_ASCII.getConstant())) {
			return LineDisplayConstantMapper.DISP_CCS_ASCII.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CCS_KANA.getConstant())) {
			return LineDisplayConstantMapper.DISP_CCS_KANA.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CCS_KANJI.getConstant())) {
			return LineDisplayConstantMapper.DISP_CCS_KANJI.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CCS_UNICODE.getConstant())) {
			return LineDisplayConstantMapper.DISP_CCS_UNICODE.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CCT_NONE.getConstant())) {
			return LineDisplayConstantMapper.DISP_CCT_NONE.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CCT_FIXED.getConstant())) {
			return LineDisplayConstantMapper.DISP_CCT_FIXED.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CCT_BLOCK.getConstant())) {
			return LineDisplayConstantMapper.DISP_CCT_BLOCK.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CCT_HALFBLOCK.getConstant())) {
			return LineDisplayConstantMapper.DISP_CCT_HALFBLOCK.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CCT_UNDERLINE.getConstant())) {
			return LineDisplayConstantMapper.DISP_CCT_UNDERLINE.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CCT_REVERSE.getConstant())) {
			return LineDisplayConstantMapper.DISP_CCT_REVERSE.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CCT_OTHER.getConstant())) {
			return LineDisplayConstantMapper.DISP_CCT_OTHER.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CCT_BLINK.getConstant())) {
			return LineDisplayConstantMapper.DISP_CCT_BLINK.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CRB_NONE.getConstant())) {
			return LineDisplayConstantMapper.DISP_CRB_NONE.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CRB_SINGLE.getConstant())) {
			return LineDisplayConstantMapper.DISP_CRB_SINGLE.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CR_NONE.getConstant())) {
			return LineDisplayConstantMapper.DISP_CR_NONE.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CR_REVERSEALL.getConstant())) {
			return LineDisplayConstantMapper.DISP_CR_REVERSEALL.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CR_REVERSEEACH.getConstant())) {
			return LineDisplayConstantMapper.DISP_CR_REVERSEEACH.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CS_UNICODE.getConstant())) {
			return LineDisplayConstantMapper.DISP_CS_UNICODE.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CS_ASCII.getConstant())) {
			return LineDisplayConstantMapper.DISP_CS_ASCII.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CS_ANSI.getConstant())) {
			return LineDisplayConstantMapper.DISP_CS_ANSI.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CT_NONE.getConstant())) {
			return LineDisplayConstantMapper.DISP_CT_NONE.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CT_FIXED.getConstant())) {
			return LineDisplayConstantMapper.DISP_CT_FIXED.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CT_BLOCK.getConstant())) {
			return LineDisplayConstantMapper.DISP_CT_BLOCK.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CT_HALFBLOCK.getConstant())) {
			return LineDisplayConstantMapper.DISP_CT_HALFBLOCK.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CT_UNDERLINE.getConstant())) {
			return LineDisplayConstantMapper.DISP_CT_UNDERLINE.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CT_REVERSE.getConstant())) {
			return LineDisplayConstantMapper.DISP_CT_REVERSE.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CT_OTHER.getConstant())) {
			return LineDisplayConstantMapper.DISP_CT_OTHER.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_CT_BLINK.getConstant())) {
			return LineDisplayConstantMapper.DISP_CT_BLINK.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_MT_NONE.getConstant())) {
			return LineDisplayConstantMapper.DISP_MT_NONE.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_MT_UP.getConstant())) {
			return LineDisplayConstantMapper.DISP_MT_UP.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_MT_DOWN.getConstant())) {
			return LineDisplayConstantMapper.DISP_MT_DOWN.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_MT_LEFT.getConstant())) {
			return LineDisplayConstantMapper.DISP_MT_LEFT.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_MT_RIGHT.getConstant())) {
			return LineDisplayConstantMapper.DISP_MT_RIGHT.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_MT_INIT.getConstant())) {
			return LineDisplayConstantMapper.DISP_MT_INIT.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_MF_WALK.getConstant())) {
			return LineDisplayConstantMapper.DISP_MF_WALK.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_MF_PLACE.getConstant())) {
			return LineDisplayConstantMapper.DISP_MF_PLACE.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_GT_SINGLE.getConstant())) {
			return LineDisplayConstantMapper.DISP_GT_SINGLE.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_DT_NORMAL.getConstant())) {
			return LineDisplayConstantMapper.DISP_DT_NORMAL.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_DT_BLINK.getConstant())) {
			return LineDisplayConstantMapper.DISP_DT_BLINK.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_DT_REVERSE.getConstant())) {
			return LineDisplayConstantMapper.DISP_DT_REVERSE.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_DT_BLINK_REVERSE.getConstant())) {
			return LineDisplayConstantMapper.DISP_DT_BLINK_REVERSE.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_ST_UP.getConstant())) {
			return LineDisplayConstantMapper.DISP_ST_UP.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_ST_DOWN.getConstant())) {
			return LineDisplayConstantMapper.DISP_ST_DOWN.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_ST_LEFT.getConstant())) {
			return LineDisplayConstantMapper.DISP_ST_LEFT.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_ST_RIGHT.getConstant())) {
			return LineDisplayConstantMapper.DISP_ST_RIGHT.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_SD_OFF.getConstant())) {
			return LineDisplayConstantMapper.DISP_SD_OFF.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_SD_ON.getConstant())) {
			return LineDisplayConstantMapper.DISP_SD_ON.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_SD_BLINK.getConstant())) {
			return LineDisplayConstantMapper.DISP_SD_BLINK.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_BM_ASIS.getConstant())) {
			return LineDisplayConstantMapper.DISP_BM_ASIS.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_BM_LEFT.getConstant())) {
			return LineDisplayConstantMapper.DISP_BM_LEFT.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_BM_CENTER.getConstant())) {
			return LineDisplayConstantMapper.DISP_BM_CENTER.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_BM_RIGHT.getConstant())) {
			return LineDisplayConstantMapper.DISP_BM_RIGHT.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_BM_TOP.getConstant())) {
			return LineDisplayConstantMapper.DISP_BM_TOP.getContantNumber();
		}
		
		if(constant.equals(LineDisplayConstantMapper.DISP_BM_BOTTOM.getConstant())) {
			return LineDisplayConstantMapper.DISP_BM_BOTTOM.getContantNumber();
		}
		
		return Integer.parseInt(constant);

	}
	
}
