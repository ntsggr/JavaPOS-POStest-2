package postest2;

public class POSPrinterConstantMapper implements IMapWrapper {


	// ###################################################################
	// #### POS Printer Constants
	// ###################################################################

	// ///////////////////////////////////////////////////////////////////
	// "CapXxxCartridgeSensor" and "XxxCartridgeState" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapCartridge)
	public static final ConstantConverter PTR_CART_UNKNOWN = new ConstantConverter(0x10000000,
			"PTR_CART_UNKNOWN");
	@BelongingProperty(PropertyNames.getCapCartridge)
	public static final ConstantConverter PTR_CART_OK = new ConstantConverter(0x00000000, "PTR_CART_OK");
	@BelongingProperty(PropertyNames.getCapCartridge)
	public static final ConstantConverter PTR_CART_REMOVED = new ConstantConverter(0x00000001,
			"PTR_CART_REMOVED");
	@BelongingProperty(PropertyNames.getCapCartridge)
	public static final ConstantConverter PTR_CART_EMPTY = new ConstantConverter(0x00000002, "PTR_CART_EMPTY");
	@BelongingProperty(PropertyNames.getCapCartridge)
	public static final ConstantConverter PTR_CART_NEAREND = new ConstantConverter(0x00000004,
			"PTR_CART_NEAREND");
	@BelongingProperty(PropertyNames.getCapCartridge)
	public static final ConstantConverter PTR_CART_CLEANING = new ConstantConverter(0x00000008,
			"PTR_CART_CLEANING");

	// ///////////////////////////////////////////////////////////////////
	// "CartridgeNotify" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCartridgeNotify)
	public static final ConstantConverter PTR_CN_DISABLED = new ConstantConverter(0x00000000,
			"PTR_CN_DISABLED");
	@BelongingProperty(PropertyNames.getCartridgeNotify)
	public static final ConstantConverter PTR_CN_ENABLED = new ConstantConverter(0x00000001, "PTR_CN_ENABLED");

	// ///////////////////////////////////////////////////////////////////
	// "ErrorLevel" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getErrorLevel)
	public static final ConstantConverter PTR_EL_NONE = new ConstantConverter(1, "PTR_EL_NONE");
	@BelongingProperty(PropertyNames.getErrorLevel)
	public static final ConstantConverter PTR_EL_RECOVERABLE = new ConstantConverter(2, "PTR_EL_RECOVERABLE");
	@BelongingProperty(PropertyNames.getErrorLevel)
	public static final ConstantConverter PTR_EL_FATAL = new ConstantConverter(3, "PTR_EL_FATAL");

	// ///////////////////////////////////////////////////////////////////
	// Printer Station Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getPTRStation)
	public static final ConstantConverter PTR_S_JOURNAL = new ConstantConverter(1, "PTR_S_JOURNAL");
	@BelongingProperty(PropertyNames.getPTRStation)
	public static final ConstantConverter PTR_S_RECEIPT = new ConstantConverter(2, "PTR_S_RECEIPT");
	@BelongingProperty(PropertyNames.getPTRStation)
	public static final ConstantConverter PTR_S_SLIP = new ConstantConverter(4, "PTR_S_SLIP");

	@BelongingProperty(PropertyNames.getPTRStation)
	public static final ConstantConverter PTR_S_JOURNAL_RECEIPT = new ConstantConverter(
			PTR_S_JOURNAL.getContantNumber() | PTR_S_RECEIPT.getContantNumber(), "PTR_S_JOURNAL_RECEIPT");
	@BelongingProperty(PropertyNames.getPTRStation)
	public static final ConstantConverter PTR_S_JOURNAL_SLIP = new ConstantConverter(
			PTR_S_JOURNAL.getContantNumber() | PTR_S_SLIP.getContantNumber(), "PTR_S_JOURNAL_SLIP");
	@BelongingProperty(PropertyNames.getPTRStation)
	public static final ConstantConverter PTR_S_RECEIPT_SLIP = new ConstantConverter(
			PTR_S_RECEIPT.getContantNumber() | PTR_S_SLIP.getContantNumber(), "PTR_S_RECEIPT_SLIP");

	@BelongingProperty(PropertyNames.getPTRStation)
	public static final ConstantConverter PTR_TWO_RECEIPT_JOURNAL = new ConstantConverter(
			0x8000 + PTR_S_JOURNAL_RECEIPT.getContantNumber(), "PTR_TWO_RECEIPT_JOURNAL");
	@BelongingProperty(PropertyNames.getPTRStation)
	public static final ConstantConverter PTR_TWO_SLIP_JOURNAL = new ConstantConverter(
			0x8000 + PTR_S_JOURNAL_SLIP.getContantNumber(), "PTR_TWO_SLIP_JOURNAL");
	@BelongingProperty(PropertyNames.getPTRStation)
	public static final ConstantConverter PTR_TWO_SLIP_RECEIPT = new ConstantConverter(
			0x8000 + PTR_S_RECEIPT_SLIP.getContantNumber(), "PTR_TWO_SLIP_RECEIPT");

	// ///////////////////////////////////////////////////////////////////
	// "CapCharacterSet" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapCharacterSet)
	public static final ConstantConverter PTR_CCS_ALPHA = new ConstantConverter(1, "PTR_CCS_ALPHA");
	@BelongingProperty(PropertyNames.getCapCharacterSet)
	public static final ConstantConverter PTR_CCS_ASCII = new ConstantConverter(998, "PTR_CCS_ASCII");
	@BelongingProperty(PropertyNames.getCapCharacterSet)
	public static final ConstantConverter PTR_CCS_KANA = new ConstantConverter(10, "PTR_CCS_KANA");
	@BelongingProperty(PropertyNames.getCapCharacterSet)
	public static final ConstantConverter PTR_CCS_KANJI = new ConstantConverter(11, "PTR_CCS_KANJI");
	@BelongingProperty(PropertyNames.getCapCharacterSet)
	public static final ConstantConverter PTR_CCS_UNICODE = new ConstantConverter(997, "PTR_CCS_UNICODE");

	// ///////////////////////////////////////////////////////////////////
	// "CharacterSet" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCharacterSet)
	public static final ConstantConverter PTR_CS_UNICODE = new ConstantConverter(997, "PTR_CS_UNICODE");
	@BelongingProperty(PropertyNames.getCharacterSet)
	public static final ConstantConverter PTR_CS_ASCII = new ConstantConverter(998, "PTR_CS_ASCII");
	@BelongingProperty(PropertyNames.getCharacterSet)
	public static final ConstantConverter PTR_CS_ANSI = new ConstantConverter(999, "PTR_CS_ANSI");

	// ///////////////////////////////////////////////////////////////////
	// "MapMode" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getMapMode)
	public static final ConstantConverter PTR_MM_DOTS = new ConstantConverter(1, "PTR_MM_DOTS");
	@BelongingProperty(PropertyNames.getMapMode)
	public static final ConstantConverter PTR_MM_TWIPS = new ConstantConverter(2, "PTR_MM_TWIPS");
	@BelongingProperty(PropertyNames.getMapMode)
	public static final ConstantConverter PTR_MM_ENGLISH = new ConstantConverter(3, "PTR_MM_ENGLISH");
	@BelongingProperty(PropertyNames.getMapMode)
	public static final ConstantConverter PTR_MM_METRIC = new ConstantConverter(4, "PTR_MM_METRIC");

	// ///////////////////////////////////////////////////////////////////
	// "CapXxxColor" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapColor)
	public static final ConstantConverter PTR_COLOR_PRIMARY = new ConstantConverter(0x00000001,
			"PTR_COLOR_PRIMARY");
	@BelongingProperty(PropertyNames.getCapColor)
	public static final ConstantConverter PTR_COLOR_CUSTOM1 = new ConstantConverter(0x00000002,
			"PTR_COLOR_CUSTOM1");
	@BelongingProperty(PropertyNames.getCapColor)
	public static final ConstantConverter PTR_COLOR_CUSTOM2 = new ConstantConverter(0x00000004,
			"PTR_COLOR_CUSTOM2");
	@BelongingProperty(PropertyNames.getCapColor)
	public static final ConstantConverter PTR_COLOR_CUSTOM3 = new ConstantConverter(0x00000008,
			"PTR_COLOR_CUSTOM3");
	@BelongingProperty(PropertyNames.getCapColor)
	public static final ConstantConverter PTR_COLOR_CUSTOM4 = new ConstantConverter(0x00000010,
			"PTR_COLOR_CUSTOM4");
	@BelongingProperty(PropertyNames.getCapColor)
	public static final ConstantConverter PTR_COLOR_CUSTOM5 = new ConstantConverter(0x00000020,
			"PTR_COLOR_CUSTOM5");
	@BelongingProperty(PropertyNames.getCapColor)
	public static final ConstantConverter PTR_COLOR_CUSTOM6 = new ConstantConverter(0x00000040,
			"PTR_COLOR_CUSTOM6");
	@BelongingProperty(PropertyNames.getCapColor)
	public static final ConstantConverter PTR_COLOR_CYAN = new ConstantConverter(0x00000100, "PTR_COLOR_CYAN");
	@BelongingProperty(PropertyNames.getCapColor)
	public static final ConstantConverter PTR_COLOR_MAGENTA = new ConstantConverter(0x00000200,
			"PTR_COLOR_MAGENTA");
	@BelongingProperty(PropertyNames.getCapColor)
	public static final ConstantConverter PTR_COLOR_YELLOW = new ConstantConverter(0x00000400,
			"PTR_COLOR_YELLOW");
	@BelongingProperty(PropertyNames.getCapColor)
	public static final ConstantConverter PTR_COLOR_FULL = new ConstantConverter(0x80000000, "PTR_COLOR_FULL");

	// ///////////////////////////////////////////////////////////////////
	// "PageModeDescriptor" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getPageModeDescriptor)
	public static final ConstantConverter PTR_PM_BITMAP = new ConstantConverter(0x00000001, "PTR_PM_BITMAP");
	@BelongingProperty(PropertyNames.getPageModeDescriptor)
	public static final ConstantConverter PTR_PM_BARCODE = new ConstantConverter(0x00000002, "PTR_PM_BARCODE");
	@BelongingProperty(PropertyNames.getPageModeDescriptor)
	public static final ConstantConverter PTR_PM_BM_ROTATE = new ConstantConverter(0x00000004,
			"PTR_PM_BM_ROTATE");
	@BelongingProperty(PropertyNames.getPageModeDescriptor)
	public static final ConstantConverter PTR_PM_BC_ROTATE = new ConstantConverter(0x00000008,
			"PTR_PM_BC_ROTATE");
	@BelongingProperty(PropertyNames.getPageModeDescriptor)
	public static final ConstantConverter PTR_PM_OPAQUE = new ConstantConverter(0x00000010, "PTR_PM_OPAQUE");

	// ///////////////////////////////////////////////////////////////////
	// "PageModePrintDirection" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getPageModePrintDirection)
	public static final ConstantConverter PTR_PD_LEFT_TO_RIGHT = new ConstantConverter(1,
			"PTR_PD_LEFT_TO_RIGHT");
	@BelongingProperty(PropertyNames.getPageModePrintDirection)
	public static final ConstantConverter PTR_PD_BOTTOM_TO_TOP = new ConstantConverter(2,
			"PTR_PD_BOTTOM_TO_TOP");
	@BelongingProperty(PropertyNames.getPageModePrintDirection)
	public static final ConstantConverter PTR_PD_RIGHT_TO_LEFT = new ConstantConverter(3,
			"PTR_PD_RIGHT_TO_LEFT");
	@BelongingProperty(PropertyNames.getPageModePrintDirection)
	public static final ConstantConverter PTR_PD_TOP_TO_BOTTOM = new ConstantConverter(4,
			"PTR_PD_TOP_TO_BOTTOM");

	// ///////////////////////////////////////////////////////////////////
	// "clearPrintArea" and "pageModePrint" Method Constant
	// ///////////////////////////////////////////////////////////////////

	public static final ConstantConverter PTR_PM_PAGE_MODE = new ConstantConverter(1, "PTR_PM_PAGE_MODE");
	public static final ConstantConverter PTR_PM_PRINT_SAVE = new ConstantConverter(2, "PTR_PM_PRINT_SAVE");
	public static final ConstantConverter PTR_PM_NORMAL = new ConstantConverter(3, "PTR_PM_NORMAL");
	public static final ConstantConverter PTR_PM_CANCEL = new ConstantConverter(4, "PTR_PM_CANCEL");

	// ///////////////////////////////////////////////////////////////////
	// "CutPaper" Method Constant
	// ///////////////////////////////////////////////////////////////////

	public static final ConstantConverter PTR_CP_FULLCUT = new ConstantConverter(100, "PTR_CP_FULLCUT");

	// ///////////////////////////////////////////////////////////////////
	// "PrintBarCode" Method Constants:
	// ///////////////////////////////////////////////////////////////////

	// "Alignment" Parameter
	// Either the distance from the left-most prConstantConverter column to the
	// start of the bar code, or one of the following:

	public static final ConstantConverter PTR_BC_LEFT = new ConstantConverter(-1, "PTR_BC_LEFT");
	public static final ConstantConverter PTR_BC_CENTER = new ConstantConverter(-2, "PTR_BC_CENTER");
	public static final ConstantConverter PTR_BC_RIGHT = new ConstantConverter(-3, "PTR_BC_RIGHT");

	// "TextPosition" Parameter

	public static final ConstantConverter PTR_BC_TEXT_NONE = new ConstantConverter(-11, "PTR_BC_TEXT_NONE");
	public static final ConstantConverter PTR_BC_TEXT_ABOVE = new ConstantConverter(-12, "PTR_BC_TEXT_ABOVE");
	public static final ConstantConverter PTR_BC_TEXT_BELOW = new ConstantConverter(-13, "PTR_BC_TEXT_BELOW");

	// "Symbology" Parameter:

	// One dimensional symbologies
	public static final ConstantConverter PTR_BCS_UPCA = new ConstantConverter(101, "PTR_BCS_UPCA"); // Digits
	public static final ConstantConverter PTR_BCS_UPCE = new ConstantConverter(102, "PTR_BCS_UPCE"); // Digits
	public static final ConstantConverter PTR_BCS_JAN8 = new ConstantConverter(103, "PTR_BCS_JAN8"); // =
																										// new
																										// ConstantConverter(EAN
																										// 8
	public static final ConstantConverter PTR_BCS_EAN8 = new ConstantConverter(103, "PTR_BCS_EAN8"); // =
																										// new
																										// ConstantConverter(JAN
																										// 8
																										// (added
																										// in
																										// 1.2)
	public static final ConstantConverter PTR_BCS_JAN13 = new ConstantConverter(104, "PTR_BCS_JAN13"); // =
																										// new
																										// ConstantConverter(EAN
																										// 13
	public static final ConstantConverter PTR_BCS_EAN13 = new ConstantConverter(104, "PTR_BCS_EAN13"); // =
																										// new
																										// ConstantConverter(JAN
																										// 13
																										// (added
																										// in
																										// 1.2)
	public static final ConstantConverter PTR_BCS_TF = new ConstantConverter(105, "PTR_BCS_TF"); // (Discrete
																									// 2
																									// of
																									// 5)
																									// Digits
	public static final ConstantConverter PTR_BCS_ITF = new ConstantConverter(106, "PTR_BCS_ITF"); // (Interleaved
																									// 2
																									// of
																									// 5)
																									// Digits
	public static final ConstantConverter PTR_BCS_Codabar = new ConstantConverter(107, "PTR_BCS_Codabar"); // Digits,
	// -,
	// $,
	// :,
	// /,
	// .,
	// +,
	// "asdfasdf");
	// 4 start/stop characters
	// (a, b, c, d)
	public static final ConstantConverter PTR_BCS_Code39 = new ConstantConverter(108, "PTR_BCS_Code39"); // Alpha,
	// Digits,
	// Space,
	// -,
	// .,
	// $, /, +, %, "asdfasdf"); start/stop (*)
	// Also has Full ASCII feature
	public static final ConstantConverter PTR_BCS_Code93 = new ConstantConverter(109, "PTR_BCS_Code93"); // Same
	// characters
	// as
	// Code
	// 39
	public static final ConstantConverter PTR_BCS_Code128 = new ConstantConverter(110, "PTR_BCS_Code128"); // 128
	// data
	// characters
	// (The following were added in Release 1.2)
	public static final ConstantConverter PTR_BCS_UPCA_S = new ConstantConverter(111, "PTR_BCS_UPCA_S"); // UPC-A
	// with
	// supplemental
	// barcode
	public static final ConstantConverter PTR_BCS_UPCE_S = new ConstantConverter(112, "PTR_BCS_UPCE_S"); // UPC-E
	// with
	// supplemental
	// barcode
	public static final ConstantConverter PTR_BCS_UPCD1 = new ConstantConverter(113, "PTR_BCS_UPCD1"); // UPC-D1
	public static final ConstantConverter PTR_BCS_UPCD2 = new ConstantConverter(114, "PTR_BCS_UPCD2"); // UPC-D2
	public static final ConstantConverter PTR_BCS_UPCD3 = new ConstantConverter(115, "PTR_BCS_UPCD3"); // UPC-D3
	public static final ConstantConverter PTR_BCS_UPCD4 = new ConstantConverter(116, "PTR_BCS_UPCD4"); // UPC-D4
	public static final ConstantConverter PTR_BCS_UPCD5 = new ConstantConverter(117, "PTR_BCS_UPCD5"); // UPC-D5
	public static final ConstantConverter PTR_BCS_EAN8_S = new ConstantConverter(118, "PTR_BCS_EAN8_S"); // EAN
	// 8
	// with
	// supplemental
	// barcode
	public static final ConstantConverter PTR_BCS_EAN13_S = new ConstantConverter(119, "PTR_BCS_EAN13_S"); // EAN
	// 13
	// with
	// supplemental
	// barcode
	public static final ConstantConverter PTR_BCS_EAN128 = new ConstantConverter(120, "PTR_BCS_EAN128"); // EAN
	// 128
	public static final ConstantConverter PTR_BCS_OCRA = new ConstantConverter(121, "PTR_BCS_OCRA"); // OCR
																										// "A"
	public static final ConstantConverter PTR_BCS_OCRB = new ConstantConverter(122, "PTR_BCS_OCRB"); // OCR
																										// "B"

	// Added in Release 1.8
	public static final ConstantConverter PTR_BCS_Code128_Parsed = new ConstantConverter(123,
			"PTR_BCS_Code128_Parsed");
	// The followings RSS have been deprecated in 1.12. Use the GS1DATABAR
	// constants below instead.
	public static final ConstantConverter PTR_BCS_RSS14 = new ConstantConverter(131, "PTR_BCS_RSS14"); // Reduced
																										// Space
																										// Symbology
																										// -
																										// 14
																										// digit
																										// GTIN
	public static final ConstantConverter PTR_BCS_RSS_EXPANDED = new ConstantConverter(132,
			"PTR_BCS_RSS_EXPANDED"); // RSS
	// -
	// 14
	// digit
	// GTIN
	// plus
	// additional
	// fields

	// Added in Release 1.12
	public static final ConstantConverter PTR_BCS_GS1DATABAR = new ConstantConverter(131,
			"PTR_BCS_GS1DATABAR"); // GS1
	// DataBar
	// Omnidirectional
	public static final ConstantConverter PTR_BCS_GS1DATABAR_E = new ConstantConverter(132,
			"PTR_BCS_GS1DATABAR_E"); // GS1
	// DataBar
	// Expanded
	public static final ConstantConverter PTR_BCS_GS1DATABAR_S = new ConstantConverter(133,
			"PTR_BCS_GS1DATABAR_S"); // GS1
	// DataBar
	// Stacked
	// Omnidirectional
	public static final ConstantConverter PTR_BCS_GS1DATABAR_E_S = new ConstantConverter(134,
			"PTR_BCS_GS1DATABAR_E_S"); // GS1
	// DataBar
	// Expanded
	// Stacked

	// Two dimensional symbologies
	public static final ConstantConverter PTR_BCS_PDF417 = new ConstantConverter(201, "PTR_BCS_PDF417");
	public static final ConstantConverter PTR_BCS_MAXICODE = new ConstantConverter(202, "PTR_BCS_MAXICODE");

	// Added in Release 1.13
	public static final ConstantConverter PTR_BCS_DATAMATRIX = new ConstantConverter(203,
			"PTR_BCS_DATAMATRIX"); // Data
	// Matrix
	public static final ConstantConverter PTR_BCS_QRCODE = new ConstantConverter(204, "PTR_BCS_QRCODE"); // QR
	// Code
	public static final ConstantConverter PTR_BCS_UQRCODE = new ConstantConverter(205, "PTR_BCS_UQRCODE"); // Micro
	// QR
	// Code
	public static final ConstantConverter PTR_BCS_AZTEC = new ConstantConverter(206, "PTR_BCS_AZTEC"); // Aztec
	public static final ConstantConverter PTR_BCS_UPDF417 = new ConstantConverter(207, "PTR_BCS_UPDF417"); // Micro
	// PDF
	// 417

	// Start of Printer-Specific bar code symbologies
	public static final ConstantConverter PTR_BCS_OTHER = new ConstantConverter(501, "PTR_BCS_OTHER");

	// ///////////////////////////////////////////////////////////////////
	// "PrintBitmap" and "PrintMemoryBitmap" Method Constants:
	// ///////////////////////////////////////////////////////////////////

	// "Width" Parameter
	// Either bitmap width or:
	public static final ConstantConverter PTR_BM_ASIS = new ConstantConverter(-11, "PTR_BM_ASIS"); // One
																									// pixel
																									// per
																									// printer
																									// dot

	// "Alignment" Parameter
	// Either the distance from the left-most prConstantConverter column to the
	// start
	// of the bitmap, or one of the following:
	public static final ConstantConverter PTR_BM_LEFT = new ConstantConverter(-1, "PTR_BM_LEFT");
	public static final ConstantConverter PTR_BM_CENTER = new ConstantConverter(-2, "PTR_BM_CENTER");
	public static final ConstantConverter PTR_BM_RIGHT = new ConstantConverter(-3, "PTR_BM_RIGHT");

	// "Type" Parameter ("PrintMemoryBitmap" only)
	public static final ConstantConverter PTR_BMT_BMP = new ConstantConverter(1, "PTR_BMT_BMP");
	public static final ConstantConverter PTR_BMT_JPEG = new ConstantConverter(2, "PTR_BMT_JPEG");
	public static final ConstantConverter PTR_BMT_GIF = new ConstantConverter(3, "PTR_BMT_GIF");

	// ///////////////////////////////////////////////////////////////////
	// "RotatePrint" Method: "Rotation" Parameter Constants
	// "RotateSpecial" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getRotateSpecial)
	public static final ConstantConverter PTR_RP_NORMAL = new ConstantConverter(0x0001, "PTR_RP_NORMAL");
	@BelongingProperty(PropertyNames.getRotateSpecial)
	public static final ConstantConverter PTR_RP_RIGHT90 = new ConstantConverter(0x0101, "PTR_RP_RIGHT90");
	@BelongingProperty(PropertyNames.getRotateSpecial)
	public static final ConstantConverter PTR_RP_LEFT90 = new ConstantConverter(0x0102, "PTR_RP_LEFT90");
	@BelongingProperty(PropertyNames.getRotateSpecial)
	public static final ConstantConverter PTR_RP_ROTATE180 = new ConstantConverter(0x0103, "PTR_RP_ROTATE180");

	// Version 1.7. One of the following values can be
	// ORed with one of the above values.
	public static final ConstantConverter PTR_RP_BARCODE = new ConstantConverter(0x1000, "PTR_RP_BARCODE");
	public static final ConstantConverter PTR_RP_BITMAP = new ConstantConverter(0x2000, "PTR_RP_BITMAP");

	// ///////////////////////////////////////////////////////////////////
	// "SetLogo" Method: "Location" Parameter Constants
	// ///////////////////////////////////////////////////////////////////

	public static final ConstantConverter PTR_L_TOP = new ConstantConverter(1, "PTR_L_TOP");
	public static final ConstantConverter PTR_L_BOTTOM = new ConstantConverter(2, "PTR_L_BOTTOM");

	// ///////////////////////////////////////////////////////////////////
	// "TransactionPrint" Method: "Control" Parameter Constants
	// ///////////////////////////////////////////////////////////////////

	public static final ConstantConverter PTR_TP_TRANSACTION = new ConstantConverter(11, "PTR_TP_TRANSACTION");
	public static final ConstantConverter PTR_TP_NORMAL = new ConstantConverter(12, "PTR_TP_NORMAL");

	// ///////////////////////////////////////////////////////////////////
	// "MarkFeed" Method: "Type" Parameter Constants
	// "CapRecMarkFeed" Property Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapRecMarkFeed)
	public static final ConstantConverter PTR_MF_TO_TAKEUP = new ConstantConverter(1, "PTR_MF_TO_TAKEUP");
	@BelongingProperty(PropertyNames.getCapRecMarkFeed)
	public static final ConstantConverter PTR_MF_TO_CUTTER = new ConstantConverter(2, "PTR_MF_TO_CUTTER");
	@BelongingProperty(PropertyNames.getCapRecMarkFeed)
	public static final ConstantConverter PTR_MF_TO_CURRENT_TOF = new ConstantConverter(4,
			"PTR_MF_TO_CURRENT_TOF");
	@BelongingProperty(PropertyNames.getCapRecMarkFeed)
	public static final ConstantConverter PTR_MF_TO_NEXT_TOF = new ConstantConverter(8, "PTR_MF_TO_NEXT_TOF");

	// ///////////////////////////////////////////////////////////////////
	// "ChangePrintSide" Method: "Side" Parameter Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getSlpPrintSide)
	public static final ConstantConverter PTR_PS_UNKNOWN = new ConstantConverter(0, "PTR_PS_UNKNOWN");
	@BelongingProperty(PropertyNames.getSlpPrintSide)
	public static final ConstantConverter PTR_PS_SIDE1 = new ConstantConverter(1, "PTR_PS_SIDE1");
	@BelongingProperty(PropertyNames.getSlpPrintSide)
	public static final ConstantConverter PTR_PS_SIDE2 = new ConstantConverter(2, "PTR_PS_SIDE2");
	@BelongingProperty(PropertyNames.getSlpPrintSide)
	public static final ConstantConverter PTR_PS_OPPOSITE = new ConstantConverter(3, "PTR_PS_OPPOSITE");

	// ///////////////////////////////////////////////////////////////////
	// "drawRuledLine" Method: "lineDirection" Parameter Constants
	// ///////////////////////////////////////////////////////////////////
	@BelongingProperty(PropertyNames.getCapRuledLine)
	public static final ConstantConverter PTR_RL_HORIZONTAL = new ConstantConverter(1, "PTR_RL_HORIZONTAL"); // Added
	// in
	// 1.13
	@BelongingProperty(PropertyNames.getCapRuledLine)
	public static final ConstantConverter PTR_RL_VERTICAL = new ConstantConverter(2, "PTR_RL_VERTICAL"); // Added
	// in
	// 1.13

	// ///////////////////////////////////////////////////////////////////
	// "drawRuledLine" Method: "lineStyle" Parameter Constants
	// ///////////////////////////////////////////////////////////////////

	public static final ConstantConverter PTR_LS_SINGLE_SOLID_LINE = new ConstantConverter(1,
			"PTR_LS_SINGLE_SOLID_LINE"); // Added
	// in
	// 1.13
	public static final ConstantConverter PTR_LS_DOUBLE_SOLID_LINE = new ConstantConverter(2,
			"PTR_LS_DOUBLE_SOLID_LINE"); // Added
	// in
	// 1.13
	public static final ConstantConverter PTR_LS_BROKEN_LINE = new ConstantConverter(3, "PTR_LS_BROKEN_LINE"); // Added
	// in
	// 1.13
	public static final ConstantConverter PTR_LS_CHAIN_LINE = new ConstantConverter(4, "PTR_LS_CHAIN_LINE"); // Added

	/**
	 * Get Constant Number from String - Needed because ComboBoxes just hold the
	 * String and not the Object
	 * 
	 * @param constant
	 * @return
	 */
	public static int getConstantNumberFromString(String constant){
		
		if(constant.equals(POSPrinterConstantMapper.PTR_CN_DISABLED.getConstant())) {
			return POSPrinterConstantMapper.PTR_CN_DISABLED.getContantNumber();
		}

		if(constant.equals(POSPrinterConstantMapper.PTR_CN_ENABLED.getConstant())) {
			return POSPrinterConstantMapper.PTR_CN_ENABLED.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_S_JOURNAL.getConstant())) {
			return POSPrinterConstantMapper.PTR_S_JOURNAL.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_S_RECEIPT.getConstant())) {
			return POSPrinterConstantMapper.PTR_S_RECEIPT.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_S_SLIP.getConstant())) {
			return POSPrinterConstantMapper.PTR_S_SLIP.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_S_JOURNAL_RECEIPT.getConstant())) {
			return POSPrinterConstantMapper.PTR_S_JOURNAL_RECEIPT.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_S_JOURNAL_SLIP.getConstant())) {
			return POSPrinterConstantMapper.PTR_S_JOURNAL_SLIP.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_S_RECEIPT_SLIP.getConstant())) {
			return POSPrinterConstantMapper.PTR_S_RECEIPT_SLIP.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_TWO_RECEIPT_JOURNAL.getConstant())) {
			return POSPrinterConstantMapper.PTR_TWO_RECEIPT_JOURNAL.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_TWO_SLIP_JOURNAL.getConstant())) {
			return POSPrinterConstantMapper.PTR_TWO_SLIP_JOURNAL.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_TWO_SLIP_RECEIPT.getConstant())) {
			return POSPrinterConstantMapper.PTR_TWO_SLIP_RECEIPT.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_CCS_ALPHA.getConstant())) {
			return POSPrinterConstantMapper.PTR_CCS_ALPHA.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_CCS_ASCII.getConstant())) {
			return POSPrinterConstantMapper.PTR_CCS_ASCII.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_CCS_KANA.getConstant())) {
			return POSPrinterConstantMapper.PTR_CCS_KANA.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_CCS_KANJI.getConstant())) {
			return POSPrinterConstantMapper.PTR_CCS_KANJI.getContantNumber();
		}
		
		if (constant.equals(POSPrinterConstantMapper.PTR_CCS_UNICODE.getConstant())) {
			return POSPrinterConstantMapper.PTR_CCS_UNICODE.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_CS_UNICODE.getConstant())) {
			return POSPrinterConstantMapper.PTR_CS_UNICODE.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_CS_ASCII.getConstant())) {
			return POSPrinterConstantMapper.PTR_CS_ASCII.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_CS_ANSI.getConstant())) {
			return POSPrinterConstantMapper.PTR_CS_ANSI.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_MM_DOTS.getConstant())) {
			return POSPrinterConstantMapper.PTR_MM_DOTS.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_MM_TWIPS.getConstant())) {
			return POSPrinterConstantMapper.PTR_MM_TWIPS.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_MM_ENGLISH.getConstant())) {
			return POSPrinterConstantMapper.PTR_MM_ENGLISH.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_MM_METRIC.getConstant())) {
			return POSPrinterConstantMapper.PTR_MM_METRIC.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_COLOR_PRIMARY.getConstant())) {
			return POSPrinterConstantMapper.PTR_COLOR_PRIMARY.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_COLOR_CUSTOM1.getConstant())) {
			return POSPrinterConstantMapper.PTR_COLOR_CUSTOM1.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_COLOR_CUSTOM2.getConstant())) {
			return POSPrinterConstantMapper.PTR_COLOR_CUSTOM2.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_COLOR_CUSTOM3.getConstant())) {
			return POSPrinterConstantMapper.PTR_COLOR_CUSTOM3.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_COLOR_CUSTOM4.getConstant())) {
			return POSPrinterConstantMapper.PTR_COLOR_CUSTOM4.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_COLOR_CUSTOM5.getConstant())) {
			return POSPrinterConstantMapper.PTR_COLOR_CUSTOM5.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_COLOR_CUSTOM6.getConstant())) {
			return POSPrinterConstantMapper.PTR_COLOR_CUSTOM6.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_COLOR_CYAN.getConstant())) {
			return POSPrinterConstantMapper.PTR_COLOR_CYAN.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_COLOR_MAGENTA.getConstant())) {
			return POSPrinterConstantMapper.PTR_COLOR_MAGENTA.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_COLOR_YELLOW.getConstant())) {
			return POSPrinterConstantMapper.PTR_COLOR_YELLOW.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_COLOR_FULL.getConstant())) {
			return POSPrinterConstantMapper.PTR_COLOR_FULL.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_PM_BITMAP.getConstant())) {
			return POSPrinterConstantMapper.PTR_PM_BITMAP.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_PM_BARCODE.getConstant())) {
			return POSPrinterConstantMapper.PTR_PM_BARCODE.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_PM_BM_ROTATE.getConstant())) {
			return POSPrinterConstantMapper.PTR_PM_BM_ROTATE.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_PM_BC_ROTATE.getConstant())) {
			return POSPrinterConstantMapper.PTR_PM_BC_ROTATE.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_PM_OPAQUE.getConstant())) {
			return POSPrinterConstantMapper.PTR_PM_OPAQUE.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_PD_LEFT_TO_RIGHT.getConstant())) {
			return POSPrinterConstantMapper.PTR_PD_LEFT_TO_RIGHT.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_PD_BOTTOM_TO_TOP.getConstant())) {
			return POSPrinterConstantMapper.PTR_PD_BOTTOM_TO_TOP.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_PD_RIGHT_TO_LEFT.getConstant())) {
			return POSPrinterConstantMapper.PTR_PD_RIGHT_TO_LEFT.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_PD_TOP_TO_BOTTOM.getConstant())) {
			return POSPrinterConstantMapper.PTR_PD_TOP_TO_BOTTOM.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_PM_PAGE_MODE.getConstant())) {
			return POSPrinterConstantMapper.PTR_PM_PAGE_MODE.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_PM_PRINT_SAVE.getConstant())) {
			return POSPrinterConstantMapper.PTR_PM_PRINT_SAVE.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_PM_NORMAL.getConstant())) {
			return POSPrinterConstantMapper.PTR_PM_NORMAL.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_PM_CANCEL.getConstant())) {
			return POSPrinterConstantMapper.PTR_PM_CANCEL.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_CP_FULLCUT.getConstant())) {
			return POSPrinterConstantMapper.PTR_CP_FULLCUT.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BC_LEFT.getConstant())) {
			return POSPrinterConstantMapper.PTR_BC_LEFT.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BC_CENTER.getConstant())) {
			return POSPrinterConstantMapper.PTR_BC_CENTER.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BC_RIGHT.getConstant())) {
			return POSPrinterConstantMapper.PTR_BC_RIGHT.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BC_TEXT_NONE.getConstant())) {
			return POSPrinterConstantMapper.PTR_BC_TEXT_NONE.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BC_TEXT_ABOVE.getConstant())) {
			return POSPrinterConstantMapper.PTR_BC_TEXT_ABOVE.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BC_TEXT_BELOW.getConstant())) {
			return POSPrinterConstantMapper.PTR_BC_TEXT_BELOW.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_UPCA.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_UPCA.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_UPCE.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_UPCE.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_JAN8.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_JAN8.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_EAN8.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_EAN8.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_JAN13.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_JAN13.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_EAN13.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_EAN13.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_TF.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_TF.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_ITF.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_ITF.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_Codabar.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_Codabar.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_Code39.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_Code39.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_Code93.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_Code93.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_Code128.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_Code128.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_UPCA_S.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_UPCA_S.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_UPCE_S.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_UPCE_S.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_UPCD1.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_UPCD1.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_UPCD2.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_UPCD2.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_UPCD3.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_UPCD3.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_UPCD4.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_UPCD4.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_UPCD5.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_UPCD5.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_EAN8_S.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_EAN8_S.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_EAN13_S.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_EAN13_S.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_EAN128.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_EAN128.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_OCRA.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_OCRA.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_OCRB.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_OCRB.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_Code128_Parsed.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_Code128_Parsed.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_RSS14.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_RSS14.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_RSS_EXPANDED.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_RSS_EXPANDED.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_GS1DATABAR.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_GS1DATABAR.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_GS1DATABAR_E.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_GS1DATABAR_E.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_GS1DATABAR_S.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_GS1DATABAR_S.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_GS1DATABAR_E_S.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_GS1DATABAR_E_S.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_PDF417.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_PDF417.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_MAXICODE.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_MAXICODE.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_DATAMATRIX.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_DATAMATRIX.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_QRCODE.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_QRCODE.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_UQRCODE.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_UQRCODE.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_AZTEC.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_AZTEC.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_UPDF417.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_UPDF417.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BCS_OTHER.getConstant())) {
			return POSPrinterConstantMapper.PTR_BCS_OTHER.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BM_ASIS.getConstant())) {
			return POSPrinterConstantMapper.PTR_BM_ASIS.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BM_LEFT.getConstant())) {
			return POSPrinterConstantMapper.PTR_BM_LEFT.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BM_CENTER.getConstant())) {
			return POSPrinterConstantMapper.PTR_BM_CENTER.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BM_RIGHT.getConstant())) {
			return POSPrinterConstantMapper.PTR_BM_RIGHT.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BMT_BMP.getConstant())) {
			return POSPrinterConstantMapper.PTR_BMT_BMP.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BMT_JPEG.getConstant())) {
			return POSPrinterConstantMapper.PTR_BMT_JPEG.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_BMT_GIF.getConstant())) {
			return POSPrinterConstantMapper.PTR_BMT_GIF.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_RP_NORMAL.getConstant())) {
			return POSPrinterConstantMapper.PTR_RP_NORMAL.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_RP_RIGHT90.getConstant())) {
			return POSPrinterConstantMapper.PTR_RP_RIGHT90.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_RP_LEFT90.getConstant())) {
			return POSPrinterConstantMapper.PTR_RP_LEFT90.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_RP_ROTATE180.getConstant())) {
			return POSPrinterConstantMapper.PTR_RP_ROTATE180.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_RP_BARCODE.getConstant())) {
			return POSPrinterConstantMapper.PTR_RP_BARCODE.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_RP_BITMAP.getConstant())) {
			return POSPrinterConstantMapper.PTR_RP_BITMAP.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_L_TOP.getConstant())) {
			return POSPrinterConstantMapper.PTR_L_TOP.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_L_BOTTOM.getConstant())) {
			return POSPrinterConstantMapper.PTR_L_BOTTOM.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_TP_TRANSACTION.getConstant())) {
			return POSPrinterConstantMapper.PTR_TP_TRANSACTION.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_TP_NORMAL.getConstant())) {
			return POSPrinterConstantMapper.PTR_TP_NORMAL.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_MF_TO_TAKEUP.getConstant())) {
			return POSPrinterConstantMapper.PTR_MF_TO_TAKEUP.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_MF_TO_CUTTER.getConstant())) {
			return POSPrinterConstantMapper.PTR_MF_TO_CUTTER.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_MF_TO_CURRENT_TOF.getConstant())) {
			return POSPrinterConstantMapper.PTR_MF_TO_CURRENT_TOF.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_MF_TO_NEXT_TOF.getConstant())) {
			return POSPrinterConstantMapper.PTR_MF_TO_NEXT_TOF.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_PS_UNKNOWN.getConstant())) {
			return POSPrinterConstantMapper.PTR_PS_UNKNOWN.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_PS_SIDE1.getConstant())) {
			return POSPrinterConstantMapper.PTR_PS_SIDE1.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_PS_SIDE2.getConstant())) {
			return POSPrinterConstantMapper.PTR_PS_SIDE2.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_PS_OPPOSITE.getConstant())) {
			return POSPrinterConstantMapper.PTR_PS_OPPOSITE.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_RL_HORIZONTAL.getConstant())) {
			return POSPrinterConstantMapper.PTR_RL_HORIZONTAL.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_RL_VERTICAL.getConstant())) {
			return POSPrinterConstantMapper.PTR_RL_VERTICAL.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_LS_SINGLE_SOLID_LINE.getConstant())) {
			return POSPrinterConstantMapper.PTR_LS_SINGLE_SOLID_LINE.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_LS_DOUBLE_SOLID_LINE.getConstant())) {
			return POSPrinterConstantMapper.PTR_LS_DOUBLE_SOLID_LINE.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_LS_BROKEN_LINE.getConstant())) {
			return POSPrinterConstantMapper.PTR_LS_BROKEN_LINE.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_LS_CHAIN_LINE.getConstant())) {
			return POSPrinterConstantMapper.PTR_LS_CHAIN_LINE.getContantNumber();
		}
		
		if (constant.equals(POSPrinterConstantMapper.PTR_EL_NONE.getConstant())) {
			return POSPrinterConstantMapper.PTR_EL_NONE.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_EL_RECOVERABLE.getConstant())) {
			return POSPrinterConstantMapper.PTR_EL_RECOVERABLE.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_EL_FATAL.getConstant())) {
			return POSPrinterConstantMapper.PTR_EL_FATAL.getContantNumber();
		}
		
		if (constant.equals(POSPrinterConstantMapper.PTR_CN_DISABLED.getConstant())) {
			return POSPrinterConstantMapper.PTR_CN_DISABLED.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_CN_ENABLED.getConstant())) {
			return POSPrinterConstantMapper.PTR_CN_ENABLED.getContantNumber();
		}
		
		if (constant.equals(POSPrinterConstantMapper.PTR_CART_UNKNOWN.getConstant())) {
			return POSPrinterConstantMapper.PTR_CART_UNKNOWN.getContantNumber();
		}
		
		if (constant.equals(POSPrinterConstantMapper.PTR_CART_OK.getConstant())) {
			return POSPrinterConstantMapper.PTR_CART_OK.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_CART_REMOVED.getConstant())) {
			return POSPrinterConstantMapper.PTR_CART_REMOVED.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_CART_EMPTY.getConstant())) {
			return POSPrinterConstantMapper.PTR_CART_EMPTY.getContantNumber();
		}
		
		if (constant.equals(POSPrinterConstantMapper.PTR_CART_NEAREND.getConstant())) {
			return POSPrinterConstantMapper.PTR_CART_NEAREND.getContantNumber();
		}

		if (constant.equals(POSPrinterConstantMapper.PTR_CART_CLEANING.getConstant())) {
			return POSPrinterConstantMapper.PTR_CART_CLEANING.getContantNumber();
		}
		
		// Return Int value - for example for Bitmap width (User can specify a size)
		return Integer.parseInt(constant);

	}

	public static String getPageModeDescriptorConstantName(int constantNumber) {
		if (constantNumber == POSPrinterConstantMapper.PTR_PM_BITMAP.getContantNumber()) {
			return POSPrinterConstantMapper.PTR_PM_BITMAP.getConstant();
		}

		if (constantNumber == POSPrinterConstantMapper.PTR_PM_BARCODE.getContantNumber()) {
			return POSPrinterConstantMapper.PTR_PM_BARCODE.getConstant();
		}

		if (constantNumber == POSPrinterConstantMapper.PTR_PM_BM_ROTATE.getContantNumber()) {
			return POSPrinterConstantMapper.PTR_PM_BM_ROTATE.getConstant();
		}

		if (constantNumber == POSPrinterConstantMapper.PTR_PM_BC_ROTATE.getContantNumber()) {
			return POSPrinterConstantMapper.PTR_PM_BC_ROTATE.getConstant();
		}

		if (constantNumber == POSPrinterConstantMapper.PTR_PM_OPAQUE.getContantNumber()) {
			return POSPrinterConstantMapper.PTR_PM_OPAQUE.getConstant();
		}
		return "" + constantNumber;
	}

	@Override
	public IMapWrapper getTheClass() {
		return this;
	}
}
