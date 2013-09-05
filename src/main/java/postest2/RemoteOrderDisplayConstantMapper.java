package postest2;


public class RemoteOrderDisplayConstantMapper implements IMapWrapper {

	
	 /////////////////////////////////////////////////////////////////////
    // "CurrentUnitID" and "UnitsOnline" Properties
    //  and "Units" Parameter Constants
    /////////////////////////////////////////////////////////////////////

    public static final ConstantConverter ROD_UID_1  = new ConstantConverter( 0x00000001, "ROD_UID_1");
    public static final ConstantConverter ROD_UID_2  = new ConstantConverter( 0x00000002, "ROD_UID_2");
    public static final ConstantConverter ROD_UID_3  = new ConstantConverter( 0x00000004, "ROD_UID_3");
    public static final ConstantConverter ROD_UID_4  = new ConstantConverter( 0x00000008, "ROD_UID_4");
    public static final ConstantConverter ROD_UID_5  = new ConstantConverter( 0x00000010, "ROD_UID_5");
    public static final ConstantConverter ROD_UID_6  = new ConstantConverter( 0x00000020, "ROD_UID_6");
    public static final ConstantConverter ROD_UID_7  = new ConstantConverter( 0x00000040, "ROD_UID_7");
    public static final ConstantConverter ROD_UID_8  = new ConstantConverter( 0x00000080, "ROD_UID_8");
    public static final ConstantConverter ROD_UID_9  = new ConstantConverter( 0x00000100, "ROD_UID_9");
    public static final ConstantConverter ROD_UID_10 = new ConstantConverter( 0x00000200, "ROD_UID_10");
    public static final ConstantConverter ROD_UID_11 = new ConstantConverter( 0x00000400, "ROD_UID_11");
    public static final ConstantConverter ROD_UID_12 = new ConstantConverter( 0x00000800, "ROD_UID_12");
    public static final ConstantConverter ROD_UID_13 = new ConstantConverter( 0x00001000, "ROD_UID_13");
    public static final ConstantConverter ROD_UID_14 = new ConstantConverter( 0x00002000, "ROD_UID_14");
    public static final ConstantConverter ROD_UID_15 = new ConstantConverter( 0x00004000, "ROD_UID_15");
    public static final ConstantConverter ROD_UID_16 = new ConstantConverter( 0x00008000, "ROD_UID_16");
    public static final ConstantConverter ROD_UID_17 = new ConstantConverter( 0x00010000, "ROD_UID_17");
    public static final ConstantConverter ROD_UID_18 = new ConstantConverter( 0x00020000, "ROD_UID_18");
    public static final ConstantConverter ROD_UID_19 = new ConstantConverter( 0x00040000, "ROD_UID_19");
    public static final ConstantConverter ROD_UID_20 = new ConstantConverter( 0x00080000, "ROD_UID_20");
    public static final ConstantConverter ROD_UID_21 = new ConstantConverter( 0x00100000, "ROD_UID_21");
    public static final ConstantConverter ROD_UID_22 = new ConstantConverter( 0x00200000, "ROD_UID_22");
    public static final ConstantConverter ROD_UID_23 = new ConstantConverter( 0x00400000, "ROD_UID_23");
    public static final ConstantConverter ROD_UID_24 = new ConstantConverter( 0x00800000, "ROD_UID_24");
    public static final ConstantConverter ROD_UID_25 = new ConstantConverter( 0x01000000, "ROD_UID_25");
    public static final ConstantConverter ROD_UID_26 = new ConstantConverter( 0x02000000, "ROD_UID_26");
    public static final ConstantConverter ROD_UID_27 = new ConstantConverter( 0x04000000, "ROD_UID_27");
    public static final ConstantConverter ROD_UID_28 = new ConstantConverter( 0x08000000, "ROD_UID_28");
    public static final ConstantConverter ROD_UID_29 = new ConstantConverter( 0x10000000, "ROD_UID_29");
    public static final ConstantConverter ROD_UID_30 = new ConstantConverter( 0x20000000, "ROD_UID_30");
    public static final ConstantConverter ROD_UID_31 = new ConstantConverter( 0x40000000, "ROD_UID_31");
    public static final ConstantConverter ROD_UID_32 = new ConstantConverter( 0x80000000, "ROD_UID_32");


    /////////////////////////////////////////////////////////////////////
    // Broadcast Methods: "Attribute" Parameter Constants
    /////////////////////////////////////////////////////////////////////

    public static final ConstantConverter ROD_ATTR_BLINK       = new ConstantConverter( 0x80, "ROD_ATTR_BLINK");

    public static final ConstantConverter ROD_ATTR_BG_BLACK    = new ConstantConverter( 0x00, "ROD_ATTR_BG_BLACK");
    public static final ConstantConverter ROD_ATTR_BG_BLUE     = new ConstantConverter( 0x10, "ROD_ATTR_BG_BLUE");
    public static final ConstantConverter ROD_ATTR_BG_GREEN    = new ConstantConverter( 0x20, "ROD_ATTR_BG_GREEN");
    public static final ConstantConverter ROD_ATTR_BG_CYAN     = new ConstantConverter( 0x30, "ROD_ATTR_BG_CYAN");
    public static final ConstantConverter ROD_ATTR_BG_RED      = new ConstantConverter( 0x40, "ROD_ATTR_BG_RED");
    public static final ConstantConverter ROD_ATTR_BG_MAGENTA  = new ConstantConverter( 0x50, "ROD_ATTR_BG_MAGENTA");
    public static final ConstantConverter ROD_ATTR_BG_BROWN    = new ConstantConverter( 0x60, "ROD_ATTR_BG_BROWN");
    public static final ConstantConverter ROD_ATTR_BG_GRAY     = new ConstantConverter( 0x70, "ROD_ATTR_BG_GRAY");

    public static final ConstantConverter ROD_ATTR_INTENSITY   = new ConstantConverter( 0x08, "ROD_ATTR_INTENSITY");

    public static final ConstantConverter ROD_ATTR_FG_BLACK    = new ConstantConverter( 0x00, "ROD_ATTR_FG_BLACK");
    public static final ConstantConverter ROD_ATTR_FG_BLUE     = new ConstantConverter( 0x01, "ROD_ATTR_FG_BLUE");
    public static final ConstantConverter ROD_ATTR_FG_GREEN    = new ConstantConverter( 0x02, "ROD_ATTR_FG_GREEN");
    public static final ConstantConverter ROD_ATTR_FG_CYAN     = new ConstantConverter( 0x03, "ROD_ATTR_FG_CYAN");
    public static final ConstantConverter ROD_ATTR_FG_RED      = new ConstantConverter( 0x04, "ROD_ATTR_FG_RED");
    public static final ConstantConverter ROD_ATTR_FG_MAGENTA  = new ConstantConverter( 0x05, "ROD_ATTR_FG_MAGENTA");
    public static final ConstantConverter ROD_ATTR_FG_BROWN    = new ConstantConverter( 0x06, "ROD_ATTR_FG_BROWN");
    public static final ConstantConverter ROD_ATTR_FG_GRAY     = new ConstantConverter( 0x07, "ROD_ATTR_FG_GRAY");


    /////////////////////////////////////////////////////////////////////
    // "DrawBox" Method: "BorderType" Parameter Constants
    /////////////////////////////////////////////////////////////////////

    public static final ConstantConverter ROD_BDR_SINGLE       = new ConstantConverter( 1, "ROD_BDR_SINGLE");
    public static final ConstantConverter ROD_BDR_DOUBLE       = new ConstantConverter( 2, "ROD_BDR_DOUBLE");
    public static final ConstantConverter ROD_BDR_SOLID        = new ConstantConverter( 3, "ROD_BDR_SOLID");


    /////////////////////////////////////////////////////////////////////
    // "ControlClock" Method: "Function" Parameter Constants
    /////////////////////////////////////////////////////////////////////

    public static final ConstantConverter ROD_CLK_START        = new ConstantConverter( 1, "ROD_CLK_START");
    public static final ConstantConverter ROD_CLK_PAUSE        = new ConstantConverter( 2, "ROD_CLK_PAUSE");
    public static final ConstantConverter ROD_CLK_RESUME       = new ConstantConverter( 3, "ROD_CLK_RESUME");
    public static final ConstantConverter ROD_CLK_MOVE         = new ConstantConverter( 4, "ROD_CLK_MOVE");
    public static final ConstantConverter ROD_CLK_STOP         = new ConstantConverter( 5, "ROD_CLK_STOP");


    /////////////////////////////////////////////////////////////////////
    // "ControlClock" Method: "Mode" Parameter Constants
    /////////////////////////////////////////////////////////////////////

    public static final ConstantConverter ROD_CLK_SHORT        = new ConstantConverter( 1, "ROD_CLK_SHORT");
    public static final ConstantConverter ROD_CLK_NORMAL       = new ConstantConverter( 2, "ROD_CLK_NORMAL");
    public static final ConstantConverter ROD_CLK_12_LONG      = new ConstantConverter( 3, "ROD_CLK_12_LONG");
    public static final ConstantConverter ROD_CLK_24_LONG      = new ConstantConverter( 4, "ROD_CLK_24_LONG");


    /////////////////////////////////////////////////////////////////////
    // "ControlCursor" Method: "Function" Parameter Constants
    /////////////////////////////////////////////////////////////////////

    public static final ConstantConverter ROD_CRS_LINE         = new ConstantConverter( 1, "ROD_CRS_LINE");
    public static final ConstantConverter ROD_CRS_LINE_BLINK   = new ConstantConverter( 2, "ROD_CRS_LINE_BLINK");
    public static final ConstantConverter ROD_CRS_BLOCK        = new ConstantConverter( 3, "ROD_CRS_BLOCK");
    public static final ConstantConverter ROD_CRS_BLOCK_BLINK  = new ConstantConverter( 4, "ROD_CRS_BLOCK_BLINK");
    public static final ConstantConverter ROD_CRS_OFF          = new ConstantConverter( 5, "ROD_CRS_OFF");


    /////////////////////////////////////////////////////////////////////
    // "SelectCharacterSet" Method: "CharacterSet" Parameter Constants
    /////////////////////////////////////////////////////////////////////
    @BelongingProperty(PropertyNames.getCharacterSet)
    public static final ConstantConverter ROD_CS_UNICODE       = new ConstantConverter( 997, "ROD_CS_UNICODE");
    @BelongingProperty(PropertyNames.getCharacterSet)
    public static final ConstantConverter ROD_CS_ASCII         = new ConstantConverter( 998, "ROD_CS_ASCII");
    @BelongingProperty(PropertyNames.getCharacterSet)
    public static final ConstantConverter ROD_CS_ANSI          = new ConstantConverter( 999, "ROD_CS_ANSI");


    /////////////////////////////////////////////////////////////////////
    // "TransactionDisplay" Method: "Function" Parameter Constants
    /////////////////////////////////////////////////////////////////////

    public static final ConstantConverter ROD_TD_TRANSACTION   = new ConstantConverter( 11, "ROD_TD_TRANSACTION");
    public static final ConstantConverter ROD_TD_NORMAL        = new ConstantConverter( 12, "ROD_TD_NORMAL");


    /////////////////////////////////////////////////////////////////////
    // "UpdateVideoRegionAttribute" Method: "Function" Parameter Constants
    /////////////////////////////////////////////////////////////////////

    public static final ConstantConverter ROD_UA_SET           = new ConstantConverter( 1, "ROD_UA_SET");
    public static final ConstantConverter ROD_UA_INTENSITY_ON  = new ConstantConverter( 2, "ROD_UA_INTENSITY_ON");
    public static final ConstantConverter ROD_UA_INTENSITY_OFF = new ConstantConverter( 3, "ROD_UA_INTENSITY_OFF");
    public static final ConstantConverter ROD_UA_REVERSE_ON    = new ConstantConverter( 4, "ROD_UA_REVERSE_ON");
    public static final ConstantConverter ROD_UA_REVERSE_OFF   = new ConstantConverter( 5, "ROD_UA_REVERSE_OFF");
    public static final ConstantConverter ROD_UA_BLINK_ON      = new ConstantConverter( 6, "ROD_UA_BLINK_ON");
    public static final ConstantConverter ROD_UA_BLINK_OFF     = new ConstantConverter( 7, "ROD_UA_BLINK_OFF");


    /////////////////////////////////////////////////////////////////////
    // "EventTypes" Property and "DataEvent" Event: "Status" Parameter Constants
    /////////////////////////////////////////////////////////////////////

    public static final ConstantConverter ROD_DE_TOUCH_UP      = new ConstantConverter( 0x01, "ROD_DE_TOUCH_UP");
    public static final ConstantConverter ROD_DE_TOUCH_DOWN    = new ConstantConverter( 0x02, "ROD_DE_TOUCH_DOWN");
    public static final ConstantConverter ROD_DE_TOUCH_MOVE    = new ConstantConverter( 0x04, "ROD_DE_TOUCH_MOVE");

	public static int getConstantNumberFromString(String constant){

		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_1.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_1.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_2.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_2.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_3.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_3.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_4.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_4.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_5.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_5.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_6.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_6.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_7.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_7.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_8.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_8.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_9.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_9.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_10.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_10.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_11.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_11.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_12.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_12.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_13.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_13.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_14.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_14.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_15.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_15.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_16.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_16.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_17.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_17.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_18.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_18.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_19.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_19.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_20.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_20.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_21.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_21.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_22.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_22.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_23.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_23.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_24.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_24.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_25.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_25.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_26.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_26.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_27.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_27.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_28.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_28.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_29.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_29.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_30.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_30.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_31.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_31.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UID_32.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UID_32.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_ATTR_BLINK.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_ATTR_BLINK.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_ATTR_BG_BLACK.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_ATTR_BG_BLACK.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_ATTR_BG_BLUE.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_ATTR_BG_BLUE.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_ATTR_BG_GREEN.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_ATTR_BG_GREEN.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_ATTR_BG_CYAN.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_ATTR_BG_CYAN.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_ATTR_BG_RED.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_ATTR_BG_RED.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_ATTR_BG_MAGENTA.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_ATTR_BG_MAGENTA.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_ATTR_BG_BROWN.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_ATTR_BG_BROWN.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_ATTR_BG_GRAY.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_ATTR_BG_GRAY.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_ATTR_INTENSITY.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_ATTR_INTENSITY.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_ATTR_FG_BLACK.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_ATTR_FG_BLACK.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_ATTR_FG_BLUE.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_ATTR_FG_BLUE.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_ATTR_FG_GREEN.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_ATTR_FG_GREEN.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_ATTR_FG_CYAN.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_ATTR_FG_CYAN.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_ATTR_FG_RED.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_ATTR_FG_RED.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_ATTR_FG_MAGENTA.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_ATTR_FG_MAGENTA.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_ATTR_FG_BROWN.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_ATTR_FG_BROWN.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_ATTR_FG_GRAY.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_ATTR_FG_GRAY.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_BDR_SINGLE.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_BDR_SINGLE.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_BDR_DOUBLE.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_BDR_DOUBLE.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_BDR_SOLID.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_BDR_SOLID.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_CLK_START.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_CLK_START.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_CLK_PAUSE.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_CLK_PAUSE.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_CLK_RESUME.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_CLK_RESUME.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_CLK_MOVE.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_CLK_MOVE.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_CLK_STOP.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_CLK_STOP.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_CLK_SHORT.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_CLK_SHORT.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_CLK_NORMAL.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_CLK_NORMAL.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_CLK_12_LONG.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_CLK_12_LONG.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_CLK_24_LONG.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_CLK_24_LONG.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_CRS_LINE.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_CRS_LINE.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_CRS_LINE_BLINK.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_CRS_LINE_BLINK.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_CRS_BLOCK.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_CRS_BLOCK.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_CRS_BLOCK_BLINK.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_CRS_BLOCK_BLINK.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_CRS_OFF.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_CRS_OFF.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_CS_UNICODE.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_CS_UNICODE.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_CS_ASCII.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_CS_ASCII.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_CS_ANSI.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_CS_ANSI.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_TD_TRANSACTION.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_TD_TRANSACTION.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_TD_NORMAL.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_TD_NORMAL.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UA_SET.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UA_SET.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UA_INTENSITY_ON.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UA_INTENSITY_ON.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UA_INTENSITY_OFF.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UA_INTENSITY_OFF.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UA_REVERSE_ON.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UA_REVERSE_ON.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UA_REVERSE_OFF.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UA_REVERSE_OFF.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UA_BLINK_ON.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UA_BLINK_ON.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_UA_BLINK_OFF.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_UA_BLINK_OFF.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_DE_TOUCH_UP.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_DE_TOUCH_UP.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_DE_TOUCH_DOWN.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_DE_TOUCH_DOWN.getContantNumber();
		}
		
		if(constant.equals(RemoteOrderDisplayConstantMapper.ROD_DE_TOUCH_MOVE.getConstant())) {
			return RemoteOrderDisplayConstantMapper.ROD_DE_TOUCH_MOVE.getContantNumber();
		}

		return Integer.parseInt(constant);
	}

	@Override
	public IMapWrapper getTheClass() {
		return this;
	}
}
