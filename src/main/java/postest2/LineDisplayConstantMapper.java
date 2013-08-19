package postest2;


public interface LineDisplayConstantMapper {
	
	
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
	//public static final ConstantConverter DISP_BM_CENTER      = new ConstantConverter(-2, "asdfasdfasdf");
	public static final ConstantConverter DISP_BM_BOTTOM        = new ConstantConverter(-3, "DISP_BM_BOTTOM");
	
	
}
