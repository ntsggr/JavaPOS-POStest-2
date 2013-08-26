package postest2;

public class FiscalPrinterConstantMapper {

	// ///////////////////////////////////////////////////////////////////
	// "AdjustmentType" arguments in diverse methods
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter FPTR_AT_AMOUNT_DISCOUNT = new ConstantConverter(1,
			"FPTR_AT_AMOUNT_DISCOUNT");
	public static final ConstantConverter FPTR_AT_AMOUNT_SURCHARGE = new ConstantConverter(2,
			"FPTR_AT_AMOUNT_SURCHARGE");
	public static final ConstantConverter FPTR_AT_PERCENTAGE_DISCOUNT = new ConstantConverter(3,
			"FPTR_AT_PERCENTAGE_DISCOUNT");
	public static final ConstantConverter FPTR_AT_PERCENTAGE_SURCHARGE = new ConstantConverter(4,
			"FPTR_AT_PERCENTAGE_SURCHARGE");
	public static final ConstantConverter FPTR_AT_COUPON_AMOUNT_DISCOUNT = new ConstantConverter(5,
			"FPTR_AT_COUPON_AMOUNT_DISCOUNT"); // 1.11
	public static final ConstantConverter FPTR_AT_COUPON_PERCENTAGE_DISCOUNT = new ConstantConverter(6,
			"FPTR_AT_COUPON_PERCENTAGE_DISCOUNT"); // 1.11

	// ///////////////////////////////////////////////////////////////////
	// "ActualCurrency" Property Constants
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter FPTR_AC_BRC = new ConstantConverter(1, "FPTR_AC_BRC");
	public static final ConstantConverter FPTR_AC_BGL = new ConstantConverter(2, "FPTR_AC_BGL");
	public static final ConstantConverter FPTR_AC_EUR = new ConstantConverter(3, "FPTR_AC_EUR");
	public static final ConstantConverter FPTR_AC_GRD = new ConstantConverter(4, "FPTR_AC_GRD");
	public static final ConstantConverter FPTR_AC_HUF = new ConstantConverter(5, "FPTR_AC_HUF");
	public static final ConstantConverter FPTR_AC_ITL = new ConstantConverter(6, "FPTR_AC_ITL");
	public static final ConstantConverter FPTR_AC_PLZ = new ConstantConverter(7, "FPTR_AC_PLZ");
	public static final ConstantConverter FPTR_AC_ROL = new ConstantConverter(8, "FPTR_AC_ROL");
	public static final ConstantConverter FPTR_AC_RUR = new ConstantConverter(9, "FPTR_AC_RUR");
	public static final ConstantConverter FPTR_AC_TRL = new ConstantConverter(10, "FPTR_AC_TRL");
	public static final ConstantConverter FPTR_AC_CZK = new ConstantConverter(11, "FPTR_AC_CZK");
	public static final ConstantConverter FPTR_AC_UAH = new ConstantConverter(12, "FPTR_AC_UAH");

	// ///////////////////////////////////////////////////////////////////
	// "ReportType" argument in "PrintReport" method
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter FPTR_RT_ORDINAL = new ConstantConverter(1, "FPTR_RT_ORDINAL");
	public static final ConstantConverter FPTR_RT_DATE = new ConstantConverter(2, "FPTR_RT_DATE");
	public static final ConstantConverter FPTR_RT_EOD_ORDINAL = new ConstantConverter(3,
			"FPTR_RT_EOD_ORDINAL");

	// ///////////////////////////////////////////////////////////////////
	// Fiscal Printer Station Constants
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter FPTR_S_JOURNAL = new ConstantConverter(1, "FPTR_S_JOURNAL");
	public static final ConstantConverter FPTR_S_RECEIPT = new ConstantConverter(2, "FPTR_S_RECEIPT");
	public static final ConstantConverter FPTR_S_SLIP = new ConstantConverter(4, "FPTR_S_SLIP");

	// ///////////////////////////////////////////////////////////////////
	// "GetTotalizer" Method Constants
	// ///////////////////////////////////////////////////////////////////
	public static final ConstantConverter FPTR_GT_GROSS = new ConstantConverter(1, "FPTR_GT_GROSS");
	public static final ConstantConverter FPTR_GT_NET = new ConstantConverter(2, "FPTR_GT_NET");
	public static final ConstantConverter FPTR_GT_DISCOUNT = new ConstantConverter(3, "FPTR_GT_DISCOUNT");
	public static final ConstantConverter FPTR_GT_DISCOUNT_VOID = new ConstantConverter(4, "FPTR_GT_DISCOUNT_VOID");
	public static final ConstantConverter FPTR_GT_ITEM = new ConstantConverter(5, "FPTR_GT_ITEM");
	public static final ConstantConverter FPTR_GT_ITEM_VOID = new ConstantConverter(6, "FPTR_GT_ITEM_VOID");
	public static final ConstantConverter FPTR_GT_NOT_PAID = new ConstantConverter(7, "FPTR_GT_NOT_PAID");
	public static final ConstantConverter FPTR_GT_REFUND = new ConstantConverter(8, "FPTR_GT_REFUND");
	public static final ConstantConverter FPTR_GT_REFUND_VOID = new ConstantConverter(9, "FPTR_GT_REFUND_VOID");
	public static final ConstantConverter FPTR_GT_SUBTOTAL_DISCOUNT = new ConstantConverter(10, "FPTR_GT_SUBTOTAL_DISCOUNT");
	public static final ConstantConverter FPTR_GT_SUBTOTAL_DISCOUNT_VOID = new ConstantConverter(11, "FPTR_GT_SUBTOTAL_DISCOUNT_VOID");
	public static final ConstantConverter FPTR_GT_SUBTOTAL_SURCHARGES = new ConstantConverter(12, "FPTR_GT_SUBTOTAL_SURCHARGES");
	public static final ConstantConverter FPTR_GT_SUBTOTAL_SURCHARGES_VOID = new ConstantConverter(13, "FPTR_GT_SUBTOTAL_SURCHARGES_VOID");
	public static final ConstantConverter FPTR_GT_SURCHARGE = new ConstantConverter(14, "FPTR_GT_SURCHARGE");
	public static final ConstantConverter FPTR_GT_SURCHARGE_VOID = new ConstantConverter(15, "FPTR_GT_SURCHARGE_VOID");
	public static final ConstantConverter FPTR_GT_VAT = new ConstantConverter(16, "FPTR_GT_VAT");
	public static final ConstantConverter FPTR_GT_VAT_CATEGORY = new ConstantConverter(17, "FPTR_GT_VAT_CATEGORY");

	public static int getConstantNumberFromString(String constant) {

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_AT_AMOUNT_DISCOUNT.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_AT_AMOUNT_DISCOUNT.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_AT_AMOUNT_SURCHARGE.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_AT_AMOUNT_SURCHARGE.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_AT_PERCENTAGE_DISCOUNT.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_AT_PERCENTAGE_DISCOUNT.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_AT_PERCENTAGE_SURCHARGE.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_AT_PERCENTAGE_SURCHARGE.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_AT_COUPON_AMOUNT_DISCOUNT.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_AT_COUPON_AMOUNT_DISCOUNT.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_AT_COUPON_PERCENTAGE_DISCOUNT.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_AT_COUPON_PERCENTAGE_DISCOUNT.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_AC_BRC.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_AC_BRC.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_AC_BGL.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_AC_BGL.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_AC_EUR.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_AC_EUR.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_AC_GRD.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_AC_GRD.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_AC_HUF.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_AC_HUF.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_AC_ITL.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_AC_ITL.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_AC_PLZ.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_AC_PLZ.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_AC_ROL.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_AC_ROL.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_AC_RUR.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_AC_RUR.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_AC_TRL.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_AC_TRL.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_AC_CZK.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_AC_CZK.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_AC_UAH.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_AC_UAH.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_RT_ORDINAL.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_RT_ORDINAL.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_RT_DATE.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_RT_DATE.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_RT_EOD_ORDINAL.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_RT_EOD_ORDINAL.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_S_JOURNAL.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_S_JOURNAL.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_S_RECEIPT.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_S_RECEIPT.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_S_SLIP.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_S_SLIP.getContantNumber();
		}
		
		if (constant.equals(FiscalPrinterConstantMapper.FPTR_GT_GROSS.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_GT_GROSS.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_GT_NET.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_GT_NET.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_GT_DISCOUNT.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_GT_DISCOUNT.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_GT_DISCOUNT_VOID.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_GT_DISCOUNT_VOID.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_GT_ITEM.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_GT_ITEM.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_GT_ITEM_VOID.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_GT_ITEM_VOID.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_GT_NOT_PAID.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_GT_NOT_PAID.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_GT_REFUND.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_GT_REFUND.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_GT_REFUND_VOID.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_GT_REFUND_VOID.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_GT_SUBTOTAL_DISCOUNT.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_GT_SUBTOTAL_DISCOUNT.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_GT_SUBTOTAL_DISCOUNT_VOID.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_GT_SUBTOTAL_DISCOUNT_VOID.getContantNumber();
		}
		
		if (constant.equals(FiscalPrinterConstantMapper.FPTR_GT_SUBTOTAL_SURCHARGES.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_GT_SUBTOTAL_SURCHARGES.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_GT_SUBTOTAL_SURCHARGES_VOID.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_GT_SUBTOTAL_SURCHARGES_VOID.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_GT_SURCHARGE.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_GT_SURCHARGE.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_GT_SURCHARGE_VOID.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_GT_SURCHARGE_VOID.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_GT_VAT.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_GT_VAT.getContantNumber();
		}

		if (constant.equals(FiscalPrinterConstantMapper.FPTR_GT_VAT_CATEGORY.getConstant())) {
			return FiscalPrinterConstantMapper.FPTR_GT_VAT_CATEGORY.getContantNumber();
		}

		return Integer.parseInt(constant);
	}

}
