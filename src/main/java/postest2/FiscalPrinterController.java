package postest2;

import java.awt.Dimension;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jpos.FiscalPrinter;
import jpos.JposException;
import jpos.events.StatusUpdateEvent;
import jpos.events.StatusUpdateListener;
import jpos.profile.JposDevCats;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class FiscalPrinterController extends CommonController implements Initializable, StatusUpdateListener {

	@FXML
	// @RequiredState(JposState.ENABLED)
	private CheckBox asyncMode;
	@FXML
	// @RequiredState(JposState.ENABLED)
	public CheckBox flagWhenIdle;
	@FXML
	private CheckBox duplicateReceipt;
	@FXML
	private CheckBox checkTotal;

	// General Printing Settings Tab
	@FXML
	public TextField headerLine;
	@FXML
	public TextField trailerLine;
	@FXML
	public TextField vatID;
	@FXML
	public TextField vatValue;
	@FXML
	public TextField additionalHeader;
	@FXML
	public TextField additionalTrailer;
	@FXML
	public TextField date;
	@FXML
	public TextField changeDue;
	@FXML
	public Text textNumHeaderLines;
	@FXML
	public Text textNumTrailerLines;
	@FXML
	public CheckBox doubleWidthHeader;
	@FXML
	public CheckBox doubleWidthTrailer;
	@FXML
	public ComboBox<String> cbSetCurrency;
	@FXML
	public TextField itemName;

	// Fiscal Receipt Tab
	@FXML
	public ComboBox<String> adjustmentType;
	@FXML
	public ComboBox<String> cbVatID;
	@FXML
	public TextField receiptMessage;
	@FXML
	public TextField description;
	@FXML
	public TextField preLine;
	@FXML
	public TextField postLine;
	@FXML
	public TextField price;
	@FXML
	public TextField quantity;
	@FXML
	public TextField amount;
	@FXML
	public TextField vatInfo;
	@FXML
	public TextField vatAdjustment;
	@FXML
	public TextField unitPrice;
	@FXML
	public TextField unitName;
	@FXML
	public TextField unitAmount;
	@FXML
	public TextField specialTax;
	@FXML
	public TextField specialTaxName;
	@FXML
	public TextField taxID;

	// Fiscal Document Tab
	@FXML
	public TextField documentAmount;
	@FXML
	public TextArea documentText;

	// Fiscal Report Tab
	@FXML
	public ComboBox<String> reportType;
	@FXML
	public TextField reportFrom;
	@FXML
	public TextField reportTo;

	// Non Fiscal Printing Tab
	@FXML
	public ComboBox<String> station;
	@FXML
	public TextArea data;

	// Fiscal Printer Status Tab
	@FXML
	public RadioButton rbFirmNum;
	@FXML
	public RadioButton rbPrinterID;
	@FXML
	public RadioButton rbCurrentReceiptTotal;
	@FXML
	public RadioButton rbDailyTotal;
	@FXML
	public RadioButton rbPrinterGrandTotal;
	@FXML
	public RadioButton rbTotalNumVoidRec;
	@FXML
	public RadioButton rbNumDailySaleRec;
	@FXML
	public RadioButton rbNumFiscalRecPrinted;
	@FXML
	public RadioButton rbCurrentTotalRefund;
	@FXML
	public RadioButton rbNumDailyVoidSalesRec;
	@FXML
	public RadioButton rbNumDailyNonSalesRec;
	@FXML
	public RadioButton rbZREport;
	@FXML
	public final ToggleGroup groupStatusRB = new ToggleGroup();
	@FXML
	public TextArea textOutput;
	@FXML
	public ComboBox<String> cbStatusVatID;
	@FXML
	public ComboBox<String> itemTotalizer;
	@FXML
	public RadioButton dailyTotalizer;
	@FXML
	public RadioButton grandTotalizer;
	@FXML
	public final ToggleGroup groupTotalizer = new ToggleGroup();
	@FXML
	public TextArea output;

	// Direct IO Tab
	@FXML
	public TextField directNumber;
	@FXML
	public TextField directData;
	@FXML
	public TextField directString;

	// Variables used for fiscal receipt printing
	private long TOTAL = 0;
	private long amountFactorDecimal = 1;
	private int quantityFactorDecimal = 1;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = new FiscalPrinter();
		// RequiredStateChecker.invokeThis(this, service);

		setUpLogicalNameComboBox();
	}

	private void setUpLogicalNameComboBox() {
		logicalName.setItems(LogicalNameGetter.getLogicalNamesByCategory(JposDevCats.POSPRINTER_DEVCAT
				.toString()));
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((FiscalPrinter) service).setDeviceEnabled(true);

				((FiscalPrinter) service).setFiscalReceiptStation(jpos.FiscalPrinterConst.FPTR_S_RECEIPT);

				amountFactorDecimal = 1;
				quantityFactorDecimal = 1;

				int adp = ((FiscalPrinter) service).getAmountDecimalPlace();
				if (adp > 0) {
					for (int i = 1; i <= adp; i++)
						amountFactorDecimal = amountFactorDecimal * 10;
				}

				int qdp = ((FiscalPrinter) service).getQuantityDecimalPlaces();
				if (qdp > 0) {
					for (int i = 1; i <= qdp; i++)
						quantityFactorDecimal = quantityFactorDecimal * 10;
				}

				cbVatID.getItems().clear();
				cbVatID.getItems().add("");
				if (((FiscalPrinter) service).getCapHasVatTable()) {
					int numVatRates = ((FiscalPrinter) service).getNumVatRates();
					for (int i = 1; i <= numVatRates; i++) {
						cbVatID.getItems().add(String.valueOf(i));
						cbStatusVatID.getItems().add(String.valueOf(i));
					}
				}

				setUpCurrency();
				setUpAdjustmentType();
				setUpReportType();
				setUpStation();
				groupStatusRadiobuttons();
				groupTotalizers();
				setUpItemTotalizer();
			} else {
				((FiscalPrinter) service).setDeviceEnabled(false);
			}
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
		}
	}

	@Override
	@FXML
	public void handleOCE(ActionEvent e) {
		super.handleOCE(e);
		deviceEnabled.setSelected(true);
		handleDeviceEnable(e);
	}

	@FXML
	public void handleAsyncMode(ActionEvent e) {
		try {
			((FiscalPrinter) service).setAsyncMode(asyncMode.selectedProperty().getValue());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
		}
	}

	@FXML
	public void handleDuplicateReceipt(ActionEvent e) {
		try {
			((FiscalPrinter) service).setDuplicateReceipt(duplicateReceipt.isSelected());
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleCheckTotal(ActionEvent e) {
		try {
			((FiscalPrinter) service).setCheckTotal(checkTotal.isSelected());
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleFlagWhenIdle(ActionEvent e) {
		try {
			((FiscalPrinter) service).setFlagWhenIdle(flagWhenIdle.selectedProperty().getValue());
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
		}
	}

	@FXML
	public void handleBeginTraining() {
		try {
			((FiscalPrinter) service).beginTraining();
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
		}
	}

	@FXML
	public void handleClearOutput() {
		try {
			((FiscalPrinter) service).clearOutput();
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
		}
	}

	@FXML
	public void handleClearError() {
		try {
			((FiscalPrinter) service).clearError();
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
		}
	}

	@FXML
	public void handleEndTraining() {
		try {
			((FiscalPrinter) service).endTraining();
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
		}
	}

	/* ********** General Print Settings - Methods ********** */
	@FXML
	public void handleSetHeaderLine(ActionEvent e) {
		if (headerLine.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value");
		} else {
			try {
				((FiscalPrinter) service).setHeaderLine(headerLine.getText().length(), headerLine.getText(),
						doubleWidthHeader.isSelected());
			} catch (JposException jpe) {
				JOptionPane.showMessageDialog(null, jpe.getMessage());
				jpe.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetTrailerLine(ActionEvent e) {
		if (trailerLine.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value");
		} else {
			try {
				((FiscalPrinter) service).setTrailerLine(trailerLine.getText().length(),
						trailerLine.getText(), doubleWidthTrailer.isSelected());
			} catch (JposException jpe) {
				JOptionPane.showMessageDialog(null, jpe.getMessage());
				jpe.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetVatValue(ActionEvent e) {
		if (vatValue.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value");
		} else {
			try {
				((FiscalPrinter) service).setVatValue(Integer.parseInt(vatID.getText()), vatValue.getText());
			} catch (JposException jpe) {
				JOptionPane.showMessageDialog(null, jpe.getMessage());
				jpe.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetVatTable(ActionEvent e) {
		try {
			((FiscalPrinter) service).setVatTable();
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleSetAdditionalHeader(ActionEvent e) {
		if (additionalHeader.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value");
		} else {
			try {
				((FiscalPrinter) service).setAdditionalHeader(additionalHeader.getText());
			} catch (JposException jpe) {
				JOptionPane.showMessageDialog(null, jpe.getMessage());
				jpe.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetAdditionalTrailer(ActionEvent e) {
		if (additionalTrailer.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value");
		} else {
			try {
				((FiscalPrinter) service).setAdditionalTrailer(additionalTrailer.getText());
			} catch (JposException jpe) {
				JOptionPane.showMessageDialog(null, jpe.getMessage());
				jpe.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetDate(ActionEvent e) {
		if (date.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value");
		} else {
			try {
				((FiscalPrinter) service).setDate(date.getText());
			} catch (JposException jpe) {
				JOptionPane.showMessageDialog(null, jpe.getMessage());
				jpe.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetChangeDue(ActionEvent e) {
		if (changeDue.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value");
		} else {
			try {
				((FiscalPrinter) service).setChangeDue(changeDue.getText());
			} catch (JposException jpe) {
				JOptionPane.showMessageDialog(null, jpe.getMessage());
				jpe.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetCurrency(ActionEvent e) {
		try {
			((FiscalPrinter) service).setCurrency(FiscalPrinterConstantMapper
					.getConstantNumberFromString(cbSetCurrency.getSelectionModel().getSelectedItem()));
		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, jpe.getMessage());
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleBeginItemList(ActionEvent e) {
		try {
			((FiscalPrinter) service).beginItemList(Integer.parseInt(vatID.getText()));
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleVerifyItem(ActionEvent e) {
		if (itemName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value");
		} else {
			try {
				((FiscalPrinter) service).verifyItem(itemName.getText(), Integer.parseInt(vatID.getText()));
			} catch (JposException jpe) {
				jpe.printStackTrace();
			}
		}
	}

	@FXML
	public void handleEndItemList(ActionEvent e) {
		try {
			((FiscalPrinter) service).endItemList();
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	/* ********** Fiscal Receipt - Methods ********** */
	@FXML
	public void handleBeginFiscalReceipt(ActionEvent e) {
		try {
			TOTAL = 0;
			((FiscalPrinter) service).beginFiscalReceipt(true);
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleEndFiscalReceipt(ActionEvent e) {
		try {
			((FiscalPrinter) service).endFiscalReceipt(true);
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
		clearFields();
	}

	// Prints a message.
	@FXML
	public void handlePrintRecMessage(ActionEvent e) {
		try {
			((FiscalPrinter) service).printRecMessage(receiptMessage.getText());
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
		clearFields();
	}

	// Indicates a part of the receipt's total to not be paid.
	@FXML
	public void handlePrintRecNotPaid(ActionEvent e) {
		long lAmount = 0;
		if (!amount.getText().isEmpty())
			lAmount = (long) (Double.parseDouble(amount.getText()) * amountFactorDecimal);

		try {
			((FiscalPrinter) service).printRecNotPaid(description.getText(), lAmount);
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
		clearFields();
	}

	// Prints a cash-in or cash-out receipt amount.
	@FXML
	public void handlePrintRecCash(ActionEvent e) {
		long lAmount = 0;
		if (!amount.getText().isEmpty())
			lAmount = (long) (Double.parseDouble(amount.getText()) * amountFactorDecimal);

		try {
			((FiscalPrinter) service).printRecCash(lAmount);
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
		clearFields();
	}

	// Prints a receipt item for a sold item
	@FXML
	public void handlePrintRecItem(ActionEvent e) {
		long lPrice = (long) (Double.parseDouble(price.getText()) * amountFactorDecimal);

		long lUnitPrice = 0;
		if (!unitPrice.getText().isEmpty())
			lUnitPrice = (long) (Double.parseDouble(unitPrice.getText()) * amountFactorDecimal);

		int iQuantity = 1;
		if (!(quantity.getText().isEmpty() || quantity.getText().equals("0")))
			iQuantity = (int) (Double.parseDouble(quantity.getText()) * quantityFactorDecimal);

		int iVatInfo = 0;
		String sVatInfo = cbVatID.getSelectionModel().getSelectedItem();
		if (vatInfo.getText().length() > 0)
			iVatInfo = Integer.parseInt(vatInfo.getText());
		else
			iVatInfo = Integer.parseInt(sVatInfo);

		try {
			if (!preLine.getText().isEmpty())
				((FiscalPrinter) service).setPreLine(preLine.getText());

			if (!postLine.getText().isEmpty())
				((FiscalPrinter) service).setPostLine(postLine.getText());

			((FiscalPrinter) service).printRecItem(description.getText(), lPrice, iQuantity, iVatInfo,
					lUnitPrice, unitName.getText());
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}

		TOTAL = TOTAL + lPrice;
		clearFields();
	}

	// Cancels one or more items that has been added to the receipt and prints a
	// void description
	@FXML
	public void handlePrintRecItemVoid(ActionEvent e) {
		long lPrice = (long) (Double.parseDouble(price.getText()) * amountFactorDecimal);

		long lUnitPrice = 0;
		if (!unitPrice.getText().isEmpty())
			lUnitPrice = (long) (Double.parseDouble(unitPrice.getText()) * amountFactorDecimal);

		int iQuantity = 1;
		if (!(quantity.getText().isEmpty() || quantity.getText().equals("0")))
			iQuantity = (int) (Double.parseDouble(quantity.getText()) * quantityFactorDecimal);

		int iVatInfo = 0;
		String sVatInfo = cbVatID.getSelectionModel().getSelectedItem();
		if (vatInfo.getText().length() > 0)
			iVatInfo = Integer.parseInt(vatInfo.getText());
		else
			iVatInfo = Integer.parseInt(sVatInfo);

		try {
			if (!preLine.getText().isEmpty())
				((FiscalPrinter) service).setPreLine(preLine.getText());

			if (!postLine.getText().isEmpty())
				((FiscalPrinter) service).setPostLine(postLine.getText());

			((FiscalPrinter) service).printRecItemVoid(description.getText(), lPrice, iQuantity, iVatInfo,
					lUnitPrice, unitName.getText());
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}

		clearFields();
	}

	// Applies and prints an adjustment (discount or surcharge) to the last
	// receipt item sold.
	@FXML
	public void handlePrintRecItemAdjustment(ActionEvent e) {
		long lAmount = 0;
		if (adjustmentType.getSelectionModel().getSelectedItem()
				.equals(FiscalPrinterConstantMapper.FPTR_AT_PERCENTAGE_DISCOUNT.getConstant())
				|| adjustmentType.getSelectionModel().getSelectedItem()
						.equals(FiscalPrinterConstantMapper.FPTR_AT_PERCENTAGE_SURCHARGE.getConstant())) {
			lAmount = (long) (Double.parseDouble(amount.getText()) * 1);
		} else if (!amount.getText().isEmpty()) {
			lAmount = (long) (Double.parseDouble(amount.getText()) * amountFactorDecimal);
		}

		int iVatInfo = 0;
		String sVatInfo = cbVatID.getSelectionModel().getSelectedItem();
		if (vatInfo.getText().length() > 0)
			iVatInfo = Integer.parseInt(vatInfo.getText());
		else
			iVatInfo = Integer.parseInt(sVatInfo);

		try {
			if (!preLine.getText().isEmpty())
				((FiscalPrinter) service).setPreLine(preLine.getText());

			((FiscalPrinter) service).printRecItemAdjustment(FiscalPrinterConstantMapper
					.getConstantNumberFromString(adjustmentType.getSelectionModel().getSelectedItem()),
					description.getText(), lAmount, iVatInfo);
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}

		clearFields();
	}

	// Cancels an adjustment that has been added to the fiscal receipt before
	// and prints a cancellation line.
	@FXML
	public void handlePrintRecItemAdjustmentVoid(ActionEvent e) {
		long lAmount = 0;
		if (adjustmentType.getSelectionModel().getSelectedItem()
				.equals(FiscalPrinterConstantMapper.FPTR_AT_PERCENTAGE_DISCOUNT.getConstant())
				|| adjustmentType.getSelectionModel().getSelectedItem()
						.equals(FiscalPrinterConstantMapper.FPTR_AT_PERCENTAGE_SURCHARGE.getConstant())) {
			lAmount = (long) (Double.parseDouble(amount.getText()) * 1);
		} else if (!amount.getText().isEmpty()) {
			lAmount = (long) (Double.parseDouble(amount.getText()) * amountFactorDecimal);
		}

		int iVatInfo = 0;
		String sVatInfo = cbVatID.getSelectionModel().getSelectedItem();
		if (vatInfo.getText().length() > 0)
			iVatInfo = Integer.parseInt(vatInfo.getText());
		else
			iVatInfo = Integer.parseInt(sVatInfo);

		try {
			if (!preLine.getText().isEmpty())
				((FiscalPrinter) service).setPreLine(preLine.getText());

			((FiscalPrinter) service).printRecItemAdjustmentVoid(FiscalPrinterConstantMapper
					.getConstantNumberFromString(adjustmentType.getSelectionModel().getSelectedItem()),
					description.getText(), lAmount, iVatInfo);
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}

		clearFields();
	}

	// Prints a receipt fuel item
	@FXML
	public void handlePrintRecItemFuel(ActionEvent e) {
		long lPrice = (long) (Double.parseDouble(price.getText()) * amountFactorDecimal);

		int iQuantity = 1;
		if (!(quantity.getText().isEmpty() || quantity.getText().equals("0")))
			iQuantity = (int) (Double.parseDouble(quantity.getText()) * quantityFactorDecimal);

		int iVatInfo = 0;
		String sVatInfo = cbVatID.getSelectionModel().getSelectedItem();
		if (vatInfo.getText().length() > 0)
			iVatInfo = Integer.parseInt(vatInfo.getText());
		else
			iVatInfo = Integer.parseInt(sVatInfo);

		long iUnitPrice = 0;
		if (!unitPrice.getText().isEmpty())
			iUnitPrice = ((long) (Double.parseDouble(unitPrice.getText()))) * amountFactorDecimal;

		long lSpecialTax = 0;
		if (!specialTax.getText().isEmpty())
			lSpecialTax = (long) (Double.parseDouble(specialTax.getText()) * amountFactorDecimal);

		try {
			((FiscalPrinter) service).printRecItemFuel(description.getText(), lPrice, iQuantity, iVatInfo,
					iUnitPrice, unitName.getText(), lSpecialTax, specialTaxName.getText());
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}

		clearFields();
	}

	// Called to void a fuel item
	@FXML
	public void handlePrintRecItemFuelVoid(ActionEvent e) {
		long lPrice = (long) (Double.parseDouble(price.getText()) * amountFactorDecimal);

		int iVatInfo = 0;
		String sVatInfo = cbVatID.getSelectionModel().getSelectedItem();
		if (vatInfo.getText().length() > 0)
			iVatInfo = Integer.parseInt(vatInfo.getText());
		else
			iVatInfo = Integer.parseInt(sVatInfo);

		long lSpecialTax = 0;
		if (!specialTax.getText().isEmpty())
			lSpecialTax = (long) (Double.parseDouble(specialTax.getText()) * amountFactorDecimal);

		try {
			((FiscalPrinter) service).printRecItemFuelVoid(description.getText(), lPrice, iVatInfo,
					lSpecialTax);
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}

		clearFields();
	}

	// Process one or more iterm refunds.
	@FXML
	public void handlePrintRecItemRefund(ActionEvent e) {
		long lAmount = 0;
		if (!amount.getText().isEmpty())
			lAmount = (long) (Double.parseDouble(amount.getText()) * amountFactorDecimal);

		int iQuantity = 1;
		if (!(quantity.getText().isEmpty() || quantity.getText().equals("0")))
			iQuantity = (int) (Double.parseDouble(quantity.getText()) * quantityFactorDecimal);

		int iVatInfo = 0;
		String sVatInfo = cbVatID.getSelectionModel().getSelectedItem();
		if (vatInfo.getText().length() > 0)
			iVatInfo = Integer.parseInt(vatInfo.getText());
		else
			iVatInfo = Integer.parseInt(sVatInfo);

		long lUnitAmount = 0;
		if (!unitAmount.getText().isEmpty())
			lUnitAmount = (long) (Double.parseDouble(unitAmount.getText()) * amountFactorDecimal);

		try {
			if (!preLine.getText().isEmpty())
				((FiscalPrinter) service).setPreLine(preLine.getText());

			((FiscalPrinter) service).printRecItemRefund(description.getText(), lAmount, iQuantity, iVatInfo,
					lUnitAmount, unitName.getText());
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}

		clearFields();
	}

	// Processes a void of one or more item refunds.
	@FXML
	public void handlePrintRecItemRefundVoid(ActionEvent e) {
		long lAmount = 0;
		if (!amount.getText().isEmpty())
			lAmount = (long) (Double.parseDouble(amount.getText()) * amountFactorDecimal);

		int iQuantity = 1;
		if (!(quantity.getText().isEmpty() || quantity.getText().equals("0")))
			iQuantity = (int) (Double.parseDouble(quantity.getText()) * quantityFactorDecimal);

		int iVatInfo = 0;
		String sVatInfo = cbVatID.getSelectionModel().getSelectedItem();
		if (vatInfo.getText().length() > 0)
			iVatInfo = Integer.parseInt(vatInfo.getText());
		else
			iVatInfo = Integer.parseInt(sVatInfo);

		long lUnitAmount = 0;
		if (!unitAmount.getText().isEmpty())
			lUnitAmount = (long) (Double.parseDouble(unitAmount.getText()) * amountFactorDecimal);

		try {
			((FiscalPrinter) service).printRecItemRefundVoid(description.getText(), lAmount, iQuantity,
					iVatInfo, lUnitAmount, unitName.getText());
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}

		clearFields();
	}

	// Called to give an adjustment(discount or surcharge) for a package of some
	// item booked before.
	@FXML
	public void handlePrintRecPackageAdjustment(ActionEvent e) {
		try {
			((FiscalPrinter) service).printRecPackageAdjustment(FiscalPrinterConstantMapper
					.getConstantNumberFromString(adjustmentType.getSelectionModel().getSelectedItem()),
					description.getText(), vatAdjustment.getText());
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}

		clearFields();
	}

	// Called to void the adjustment(discount or surcharge) for a package of
	// some items.
	@FXML
	public void handlePrintRecPackageAdjustmentVoid(ActionEvent e) {
		try {
			if (!preLine.getText().isEmpty())
				((FiscalPrinter) service).setPreLine(preLine.getText());

			((FiscalPrinter) service).printRecPackageAdjustVoid(FiscalPrinterConstantMapper
					.getConstantNumberFromString(adjustmentType.getSelectionModel().getSelectedItem()),
					vatAdjustment.getText());
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}

		clearFields();
	}

	// Processes a refund.
	@FXML
	public void handlePrintRecRefund(ActionEvent e) {
		long lAmount = 0;
		if (!amount.getText().isEmpty())
			lAmount = (long) (Double.parseDouble(amount.getText()) * amountFactorDecimal);

		int iVatInfo = 0;
		String sVatInfo = cbVatID.getSelectionModel().getSelectedItem();
		if (vatInfo.getText().length() > 0)
			iVatInfo = Integer.parseInt(vatInfo.getText());
		else
			iVatInfo = Integer.parseInt(sVatInfo);

		try {
			if (!preLine.getText().isEmpty())
				((FiscalPrinter) service).setPreLine(preLine.getText());

			((FiscalPrinter) service).printRecRefund(description.getText(), lAmount, iVatInfo);
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}

		clearFields();
	}

	// Called to process a void of a refund.
	@FXML
	public void handlePrintRecRefundVoid(ActionEvent e) {
		long lAmount = 0;
		if (!amount.getText().isEmpty())
			lAmount = (long) (Double.parseDouble(amount.getText()) * amountFactorDecimal);

		int iVatInfo = 0;
		String sVatInfo = cbVatID.getSelectionModel().getSelectedItem();
		if (vatInfo.getText().length() > 0)
			iVatInfo = Integer.parseInt(vatInfo.getText());
		else
			iVatInfo = Integer.parseInt(sVatInfo);

		try {
			((FiscalPrinter) service).printRecRefundVoid(description.getText(), lAmount, iVatInfo);
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}

		clearFields();
	}

	// Checks and prints the current receipt subtotal.
	@FXML
	public void handlePrintRecSubtotal(ActionEvent e) {
		long lAmount = 0;
		if (!amount.getText().isEmpty())
			lAmount = (long) (Double.parseDouble(amount.getText()) * amountFactorDecimal);

		try {
			if (!postLine.getText().isEmpty())
				((FiscalPrinter) service).setPostLine(postLine.getText());

			((FiscalPrinter) service).printRecSubtotal(lAmount);
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}

		clearFields();
	}

	// Applies and prints an adjustment (discount or surcharge) to the current
	// receipt subtotal.
	@FXML
	public void handlePrintRecSubtotalAdjustment(ActionEvent e) {
		long lAmount = 0;
		if (adjustmentType.getSelectionModel().getSelectedItem()
				.equals(FiscalPrinterConstantMapper.FPTR_AT_PERCENTAGE_DISCOUNT.getConstant())
				|| adjustmentType.getSelectionModel().getSelectedItem()
						.equals(FiscalPrinterConstantMapper.FPTR_AT_PERCENTAGE_SURCHARGE.getConstant())) {
			lAmount = (long) (Double.parseDouble(amount.getText()) * 1);
		} else if (!amount.getText().isEmpty()) {
			lAmount = (long) (Double.parseDouble(amount.getText()) * amountFactorDecimal);
		}

		try {
			if (!preLine.getText().isEmpty())
				((FiscalPrinter) service).setPreLine(preLine.getText());

			((FiscalPrinter) service).printRecSubtotalAdjustment(FiscalPrinterConstantMapper
					.getConstantNumberFromString(adjustmentType.getSelectionModel().getSelectedItem()),
					description.getText(), lAmount);
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}

		clearFields();
	}

	// Called to void the adjustment for a package os some items.
	@FXML
	public void handlePrintRecSubtotalAdjustmentVoid(ActionEvent e) {
		long lAmount = 0;
		if (adjustmentType.getSelectionModel().getSelectedItem()
				.equals(FiscalPrinterConstantMapper.FPTR_AT_PERCENTAGE_DISCOUNT.getConstant())
				|| adjustmentType.getSelectionModel().getSelectedItem()
						.equals(FiscalPrinterConstantMapper.FPTR_AT_PERCENTAGE_SURCHARGE.getConstant())) {
			lAmount = (long) (Double.parseDouble(amount.getText()) * 1);
		} else if (!amount.getText().isEmpty()) {
			lAmount = (long) (Double.parseDouble(amount.getText()) * amountFactorDecimal);
		}

		try {
			((FiscalPrinter) service).printRecSubtotalAdjustVoid(FiscalPrinterConstantMapper
					.getConstantNumberFromString(adjustmentType.getSelectionModel().getSelectedItem()),
					lAmount);
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}

		clearFields();
	}

	// Cancels the current receipt.
	@FXML
	public void handlePrintRecVoid(ActionEvent e) {
		try {
			((FiscalPrinter) service).printRecVoid(description.getText());
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}

		clearFields();
	}

	// Cancels an item that has been added to the receipt and prints a void
	// description.
	@FXML
	public void handlePrintRecVoidItem(ActionEvent e) {
		long lPrice = (long) (Double.parseDouble(price.getText()) * amountFactorDecimal);

		long lAmount = 0;
		if (!amount.getText().isEmpty())
			lAmount = (long) (Double.parseDouble(amount.getText()) * amountFactorDecimal);

		int iQuantity = 1;
		if (!(quantity.getText().isEmpty() || quantity.getText().equals("0")))
			iQuantity = (int) (Double.parseDouble(quantity.getText()) * quantityFactorDecimal);

		int iVatInfo = 0;
		String sVatInfo = cbVatID.getSelectionModel().getSelectedItem();
		if (vatInfo.getText().length() > 0)
			iVatInfo = Integer.parseInt(vatInfo.getText());
		else
			iVatInfo = Integer.parseInt(sVatInfo);

		try {
			((FiscalPrinter) service).printRecVoidItem(description.getText(), lPrice, iQuantity,
					FiscalPrinterConstantMapper.getConstantNumberFromString(adjustmentType
							.getSelectionModel().getSelectedItem()), lAmount, iVatInfo);
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}

		clearFields();
	}

	// Prints the customer tax identification
	@FXML
	public void handlePrintRecTaxId(ActionEvent e) {
		try {
			((FiscalPrinter) service).printRecTaxID(taxID.getText());
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}

		clearFields();
	}

	// Prints the current receipt total
	@FXML
	public void handlePrintRecTotal(ActionEvent e) {
		long lPrice = (long) (Double.parseDouble(price.getText()) * amountFactorDecimal);

		long lAmount = 0;
		if (!amount.getText().isEmpty())
			lAmount = (long) (Double.parseDouble(amount.getText()) * amountFactorDecimal);

		long _TOTAL = TOTAL;
		if (lPrice < TOTAL)
			TOTAL = TOTAL - lPrice;

		if (lAmount > 0) {
			_TOTAL = lAmount * amountFactorDecimal;
		}

		try {
			if (!postLine.getText().isEmpty())
				((FiscalPrinter) service).setPostLine(postLine.getText());

			((FiscalPrinter) service).printRecTotal(_TOTAL, lPrice, description.getText());
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}

		clearFields();
	}

	/* ********** Fiscal Document - Methods ********** */
	@FXML
	public void handleBeginFiscalDocument(ActionEvent e) {
		try {
			((FiscalPrinter) service).beginFiscalDocument(Integer.parseInt(documentAmount.getText()));
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleEndFiscalDocument(ActionEvent e) {
		try {
			((FiscalPrinter) service).endFiscalDocument();
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleBeginInsertion(ActionEvent e) {
		try {
			// if 0 method begins insertion and then return the appropriate
			// status immediately.
			((FiscalPrinter) service).beginInsertion(0);
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleEndInsertion(ActionEvent e) {
		try {
			((FiscalPrinter) service).endInsertion();
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleBeginRemoval(ActionEvent e) {
		try {
			((FiscalPrinter) service).beginRemoval(0);
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleEndRemoval(ActionEvent e) {
		try {
			((FiscalPrinter) service).endRemoval();
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	// Prints a line of fiscal text to the slip station.
	@FXML
	public void handlePrintFiscalDocumentLine(ActionEvent e) {
		try {
			((FiscalPrinter) service).printFiscalDocumentLine(documentText.getText());
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	/* ********** Fiscal Report - Methods ********** */
	// Prints a report of the fiscal.
	@FXML
	public void handlePrintReport(ActionEvent e) {
		try {
			((FiscalPrinter) service).printReport(FiscalPrinterConstantMapper
					.getConstantNumberFromString(reportType.getSelectionModel().getSelectedItem()),
					reportFrom.getText(), reportTo.getText());
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	// Prints a report of all the daily fiscal activities on the receipt. No
	// data will be written to the fiscal EPROM.
	@FXML
	public void handlePrintXReport(ActionEvent e) {
		try {
			((FiscalPrinter) service).printXReport();
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	// Prints a report of all the daily fiscal activities on the receipt. Data
	// will be written to the fiscal EPROM.
	@FXML
	public void handlePrintZReport(ActionEvent e) {
		try {
			((FiscalPrinter) service).printZReport();
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	// Prints a report of totals for a range of dates on the receipt.
	@FXML
	public void handlePrintPeriodicReport(ActionEvent e) {
		try {
			((FiscalPrinter) service).printPeriodicTotalsReport(reportFrom.getText(), reportTo.getText());
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	/* ********** Non Fiscal Print - Methods ********** */
	@FXML
	public void handleBeginNonFiscal(ActionEvent e) {
		try {
			((FiscalPrinter) service).beginNonFiscal();
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	// Prints data on the Fiscal Printer station.
	@FXML
	public void handlePrintNormal(ActionEvent e) {
		try {
			((FiscalPrinter) service).printNormal(FiscalPrinterConstantMapper
					.getConstantNumberFromString(station.getSelectionModel().getSelectedItem()), data
					.getText());
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleEndNonFiscal(ActionEvent e) {
		try {
			((FiscalPrinter) service).endNonFiscal();
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	/* ********** Fiscal Printer Status - Methods ********** */
	@FXML
	public void handleGetData(ActionEvent e) {
		if (rbFirmNum.isSelected()) {
			textOutput.clear();
			textOutput.setText("" + jpos.FiscalPrinterConst.FPTR_GD_FIRMWARE);
		} else if (rbPrinterID.isSelected()) {
			textOutput.clear();
			textOutput.setText("" + jpos.FiscalPrinterConst.FPTR_GD_PRINTER_ID);
		} else if (rbCurrentReceiptTotal.isSelected()) {
			textOutput.clear();
			textOutput.setText("" + jpos.FiscalPrinterConst.FPTR_GD_CURRENT_TOTAL);
		} else if (rbDailyTotal.isSelected()) {
			textOutput.clear();
			textOutput.setText("" + jpos.FiscalPrinterConst.FPTR_GD_DAILY_TOTAL);
		} else if (rbPrinterGrandTotal.isSelected()) {
			textOutput.clear();
			textOutput.setText("" + jpos.FiscalPrinterConst.FPTR_GD_GRAND_TOTAL);
		} else if (rbTotalNumVoidRec.isSelected()) {
			textOutput.clear();
			textOutput.setText("" + jpos.FiscalPrinterConst.FPTR_GD_MID_VOID);
		} else if (rbNumDailySaleRec.isSelected()) {
			textOutput.clear();
			textOutput.setText("" + jpos.FiscalPrinterConst.FPTR_GD_FISCAL_REC);
		} else if (rbNumFiscalRecPrinted.isSelected()) {
			textOutput.clear();
			textOutput.setText("" + jpos.FiscalPrinterConst.FPTR_GD_RECEIPT_NUMBER);
		} else if (rbCurrentTotalRefund.isSelected()) {
			textOutput.clear();
			textOutput.setText("" + jpos.FiscalPrinterConst.FPTR_GD_REFUND);
		} else if (rbNumDailyVoidSalesRec.isSelected()) {
			textOutput.clear();
			textOutput.setText("" + jpos.FiscalPrinterConst.FPTR_GD_FISCAL_REC_VOID);
		} else if (rbNumDailyNonSalesRec.isSelected()) {
			textOutput.clear();
			textOutput.setText("" + jpos.FiscalPrinterConst.FPTR_GD_NONFISCAL_REC);
		} else if (rbZREport.isSelected()) {
			textOutput.clear();
			textOutput.setText("" + jpos.FiscalPrinterConst.FPTR_GD_Z_REPORT);
		}
	}

	// Forces the Fiscal Printer to return to Monitor state. It also cancels and
	// closes any operations.
	@FXML
	public void handleResetPrinter(ActionEvent e) {
		try {
			((FiscalPrinter) service).resetPrinter();
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleGetTotalizer(ActionEvent e) {
		try {
			String[] data = { "" };
			((FiscalPrinter) service).getTotalizer(FiscalPrinterConstantMapper
					.getConstantNumberFromString(cbStatusVatID.getSelectionModel().getSelectedItem()),
					FiscalPrinterConstantMapper.getConstantNumberFromString(itemTotalizer.getSelectionModel()
							.getSelectedItem()), data);
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleGetTrainingMode(ActionEvent e) {
		try {
			output.setText("" + ((FiscalPrinter) service).getTrainingModeActive());
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleGetErrorInfo(ActionEvent e) {
		try {
			output.setText("error level: " + ((FiscalPrinter) service).getErrorLevel() + "\nerror out id: "
					+ ((FiscalPrinter) service).getErrorOutID() + "\nerror state: "
					+ ((FiscalPrinter) service).getErrorState() + "\nerror station: "
					+ ((FiscalPrinter) service).getErrorStation() + "\nerror: "
					+ ((FiscalPrinter) service).getErrorString());
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleGetOutputID(ActionEvent e) {
		try {
			output.setText("" + ((FiscalPrinter) service).getOutputID());
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleGetPrinterStatus(ActionEvent e) {
		try {
			output.setText("" + ((FiscalPrinter) service).getPrinterState());
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleGetDayOpened(ActionEvent e) {
		try {
			output.setText("" + ((FiscalPrinter) service).getDayOpened());
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleGetRemFisMem(ActionEvent e) {
		try {
			output.setText("" + ((FiscalPrinter) service).getRemainingFiscalMemory());
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	/* ********** Direct IO - Methods ********** */
	@FXML
	public void handleDirectIO(ActionEvent e) {
		try {
			int directCommand = Integer.parseInt(directNumber.getText());
			int[] data = { 0 };
			data[0] = Integer.parseInt(directData.getText());
			String object = directString.getText();
			StringBuffer sb = new StringBuffer(object);
			((FiscalPrinter) service).directIO(directCommand, data, sb);
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}
	}

	@FXML
	public void handleClearFields(ActionEvent e) {
		directData.clear();
		directNumber.clear();
		directString.clear();
	}

	// ///////////////////////////////////////
	private void clearFields() {
		receiptMessage.clear();
		description.clear();
		preLine.clear();
		postLine.clear();
		price.clear();
		quantity.clear();
		amount.clear();
		vatInfo.clear();
		vatAdjustment.clear();
		unitPrice.clear();
		unitName.clear();
		unitAmount.clear();
		specialTax.clear();
		specialTaxName.clear();
	}

	private void setUpAdjustmentType() {
		adjustmentType.getItems().clear();
		adjustmentType.getItems().add(FiscalPrinterConstantMapper.FPTR_AT_AMOUNT_DISCOUNT.getConstant());
		adjustmentType.getItems().add(FiscalPrinterConstantMapper.FPTR_AT_AMOUNT_SURCHARGE.getConstant());
		adjustmentType.getItems().add(FiscalPrinterConstantMapper.FPTR_AT_PERCENTAGE_DISCOUNT.getConstant());
		adjustmentType.getItems().add(FiscalPrinterConstantMapper.FPTR_AT_PERCENTAGE_SURCHARGE.getConstant());
		adjustmentType.getItems().add(
				FiscalPrinterConstantMapper.FPTR_AT_COUPON_AMOUNT_DISCOUNT.getConstant());
		adjustmentType.getItems().add(
				FiscalPrinterConstantMapper.FPTR_AT_COUPON_PERCENTAGE_DISCOUNT.getConstant());
		adjustmentType.setValue(FiscalPrinterConstantMapper.FPTR_AT_AMOUNT_DISCOUNT.getConstant());
	}

	private void setUpCurrency() {
		cbSetCurrency.getItems().clear();
		adjustmentType.getItems().add(FiscalPrinterConstantMapper.FPTR_AC_BRC.getConstant());
		adjustmentType.getItems().add(FiscalPrinterConstantMapper.FPTR_AC_BGL.getConstant());
		adjustmentType.getItems().add(FiscalPrinterConstantMapper.FPTR_AC_EUR.getConstant());
		adjustmentType.getItems().add(FiscalPrinterConstantMapper.FPTR_AC_GRD.getConstant());
		adjustmentType.getItems().add(FiscalPrinterConstantMapper.FPTR_AC_HUF.getConstant());
		adjustmentType.getItems().add(FiscalPrinterConstantMapper.FPTR_AC_ITL.getConstant());
		adjustmentType.getItems().add(FiscalPrinterConstantMapper.FPTR_AC_PLZ.getConstant());
		adjustmentType.getItems().add(FiscalPrinterConstantMapper.FPTR_AC_ROL.getConstant());
		adjustmentType.getItems().add(FiscalPrinterConstantMapper.FPTR_AC_RUR.getConstant());
		adjustmentType.getItems().add(FiscalPrinterConstantMapper.FPTR_AC_TRL.getConstant());
		adjustmentType.getItems().add(FiscalPrinterConstantMapper.FPTR_AC_CZK.getConstant());
		adjustmentType.getItems().add(FiscalPrinterConstantMapper.FPTR_AC_UAH.getConstant());
	}

	private void setUpReportType() {
		reportType.getItems().clear();
		reportType.getItems().add(FiscalPrinterConstantMapper.FPTR_RT_ORDINAL.getConstant());
		reportType.getItems().add(FiscalPrinterConstantMapper.FPTR_RT_DATE.getConstant());
		reportType.getItems().add(FiscalPrinterConstantMapper.FPTR_RT_EOD_ORDINAL.getConstant());
	}

	private void setUpStation() {
		station.getItems().clear();
		station.getItems().add(FiscalPrinterConstantMapper.FPTR_S_JOURNAL.getConstant());
		station.getItems().add(FiscalPrinterConstantMapper.FPTR_S_RECEIPT.getConstant());
		station.getItems().add(FiscalPrinterConstantMapper.FPTR_S_SLIP.getConstant());
	}

	private void setUpItemTotalizer() {
		itemTotalizer.getItems().clear();
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_GROSS.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_NET.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_DISCOUNT.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_DISCOUNT_VOID.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_ITEM.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_ITEM_VOID.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_NOT_PAID.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_REFUND.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_REFUND_VOID.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_SUBTOTAL_DISCOUNT.getConstant());
		itemTotalizer.getItems()
				.add(FiscalPrinterConstantMapper.FPTR_GT_SUBTOTAL_DISCOUNT_VOID.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_SUBTOTAL_SURCHARGES.getConstant());
		itemTotalizer.getItems().add(
				FiscalPrinterConstantMapper.FPTR_GT_SUBTOTAL_SURCHARGES_VOID.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_SURCHARGE.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_SURCHARGE_VOID.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_VAT.getConstant());
		itemTotalizer.getItems().add(FiscalPrinterConstantMapper.FPTR_GT_VAT_CATEGORY.getConstant());
	}

	private void groupStatusRadiobuttons() {
		rbFirmNum.setToggleGroup(groupStatusRB);
		rbPrinterID.setToggleGroup(groupStatusRB);
		rbCurrentReceiptTotal.setToggleGroup(groupStatusRB);
		rbDailyTotal.setToggleGroup(groupStatusRB);
		rbPrinterGrandTotal.setToggleGroup(groupStatusRB);
		rbTotalNumVoidRec.setToggleGroup(groupStatusRB);
		rbNumDailySaleRec.setToggleGroup(groupStatusRB);
		rbNumFiscalRecPrinted.setToggleGroup(groupStatusRB);
		rbCurrentTotalRefund.setToggleGroup(groupStatusRB);
		rbNumDailyVoidSalesRec.setToggleGroup(groupStatusRB);
		rbNumDailyNonSalesRec.setToggleGroup(groupStatusRB);
		rbZREport.setToggleGroup(groupStatusRB);
		rbFirmNum.setSelected(true);
	}

	private void groupTotalizers() {
		dailyTotalizer.setToggleGroup(groupTotalizer);
		grandTotalizer.setToggleGroup(groupTotalizer);
		dailyTotalizer.setSelected(true);
	}

	// Shows statistics of device if they are supported by the device
	@Override
	@FXML
	public void handleInfo(ActionEvent e) {
		try {
			String msg = DeviceProperties.getProperties(service);

			JTextArea jta = new JTextArea(msg);
			@SuppressWarnings("serial")
			JScrollPane jsp = new JScrollPane(jta) {
				@Override
				public Dimension getPreferredSize() {
					return new Dimension(460, 390);
				}
			};
			JOptionPane.showMessageDialog(null, jsp, "Information", JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception jpe) {
			JOptionPane.showMessageDialog(null, "Exception in Info\nException: " + jpe.getMessage(),
					"Exception", JOptionPane.ERROR_MESSAGE);
			System.err.println("Jpos exception " + jpe);
		}
	}

	// Shows statistics of device if they are supported by the device
	@Override
	@FXML
	public void handleStatistics(ActionEvent e) {
		String[] stats = new String[] { "", "U_", "M_" };
		try {
			((FiscalPrinter) service).retrieveStatistics(stats);
			DOMParser parser = new DOMParser();
			parser.parse(new InputSource(new java.io.StringReader(stats[1])));

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new ByteArrayInputStream(stats[1].getBytes()));

			printStatistics(doc.getDocumentElement(), "");

			JOptionPane.showMessageDialog(null, statistics, "Statistics", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException saxe) {
			saxe.printStackTrace();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (JposException jpe) {
			jpe.printStackTrace();
			JOptionPane.showMessageDialog(null, "Statistics are not supported!", "Statistics",
					JOptionPane.ERROR_MESSAGE);
		}

		statistics = "";
	}

	@Override
	public void statusUpdateOccurred(StatusUpdateEvent sue) {
		statusLabel.setText("" + sue.getStatus());
	}

}
