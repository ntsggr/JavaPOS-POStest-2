/*
 * Copyright 2013 NTS New Technology Systems GmbH. All Rights reserved.
 * NTS PROPRIETARY/CONFIDENTIAL. Use is subject to NTS License Agreement.
 * Address: Doernbacher Strasse 126, A-4073 Wilhering, Austria
 * Homepage: www.ntswincash.com
 */
package postest2;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import jpos.JposConst;
import jpos.JposException;
import jpos.LineDisplayConst;
import jpos.POSPrinter;
import jpos.POSPrinterConst;
import jpos.profile.JposDevCats;

public class POSPrinterController implements Initializable {

	// Common
	@FXML
	private ComboBox<String> logicalName;
	@FXML
	private CheckBox deviceEnabled;
	@FXML
	private Button buttonOpen;
	@FXML
	private Button buttonClaim;
	@FXML
	private Button buttonRelease;
	@FXML
	private Button buttonInfo;
	@FXML
	private Button buttonStatistics;
	@FXML
	private Button buttonClose;
	@FXML
	private Button buttonFirmware;
	@FXML
	private Text statusLabel;
	@FXML
	private CheckBox freezeEvents;
	@FXML
	private ComboBox<String> rotationMode;
	@FXML
	private ComboBox<String> mapMode;
	@FXML
	private CheckBox asyncMode;
	@FXML
	private CheckBox flagWhenIdle;
	@FXML
	private TextArea deviceMessages;

	// Stations
	@FXML
	private RadioButton rbReceipt;
	@FXML
	private RadioButton rbJournal;
	@FXML
	private RadioButton rbSlip;

	// Group for the radiobuttons
	@FXML
	private final ToggleGroup group = new ToggleGroup();

	// TabPane
	@FXML
	private TabPane functionTab;

	// General Printing
	@FXML
	private ComboBox<String> transactionPrint;
	@FXML
	private TextArea printNormalData;

	@FXML
	private Slider cutPaperPercentage;

	// Barcode
	@FXML
	private TextField barcodeHeight;
	@FXML
	private TextField barcodeWidth;
	@FXML
	private TextField barcodeData;
	@FXML
	private ComboBox<String> barcodeSymbology;
	@FXML
	private ComboBox<String> barcodeTextPosition;
	@FXML
	private ComboBox<String> barcodeAlignment;

	// Bitmap
	@FXML
	private TextField bitmapPath;
	@FXML
	private ComboBox<String> bitmapWidth;
	@FXML
	private ComboBox<String> bitmapAlignment;
	@FXML
	private ComboBox<Integer> bitmapNumber;

	// Draw Line
	@FXML
	private TextField drawLineStartPosX;
	@FXML
	private TextField drawLineStartPosY;
	@FXML
	private TextField drawLineEndPosX;
	@FXML
	private TextField drawLineEndPosY;
	@FXML
	private TextField drawLineWidth;
	@FXML
	private ComboBox<String> drawLineDirection;
	@FXML
	private ComboBox<String> drawLineStyle;
	@FXML
	private ComboBox<String> drawLineColor;

	// Print2Normal
	@FXML
	private TextArea print2NormalFirstData;
	@FXML
	private TextArea print2NormalSecondData;
	@FXML
	private ComboBox<String> print2NormalStation;

	// PageMode
	@FXML
	private TextField pageModeHorizontalPosition;
	@FXML
	private TextField pageModeVerticalPosition;
	@FXML
	private TextField pageModePrintAreaStartPosX;
	@FXML
	private TextField pageModePrintAreaStartPosY;
	@FXML
	private TextField pageModePrintAreaEndPosX;
	@FXML
	private TextField pageModePrintAreaEndPosY;
	@FXML
	private ComboBox<String> pageModePrintDirection;
	@FXML
	private ComboBox<String> pageModePrintStation;
	@FXML
	private ComboBox<String> pageModePrint;
	@FXML
	private Label pageModeArea;
	@FXML
	private Label pageModeDescriptor;

	// Misc
	@FXML
	private TextField lineChars;
	@FXML
	private TextField lineHeight;
	@FXML
	private ComboBox<String> printSide;
	@FXML
	private ComboBox<String> markFeed;
	@FXML
	private ComboBox<Integer> characterSet;
	@FXML
	private ComboBox<Boolean> mapCharacterSet;
	@FXML
	private ComboBox<String> currentCartridge;
	@FXML
	private TextField lineSpacing;

	private ArrayList<Integer> printNormalEscapeSequenceList;
	private ArrayList<Integer> print2NormalFirstEscapeSequenceList;
	private ArrayList<Integer> print2NormalSecondEscapeSequenceList;

	// Service
	private POSPrinter printer;

	final char ESC = (char) 0x1B;

	// Letter Quality
	private boolean jrnLetterQuality = false;
	private boolean recLetterQuality = false;
	private boolean slpLetterQuality = false;

	private static String statistics = "";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		printer = new POSPrinter();

		printNormalEscapeSequenceList = new ArrayList<Integer>();
		print2NormalFirstEscapeSequenceList = new ArrayList<Integer>();
		print2NormalSecondEscapeSequenceList = new ArrayList<Integer>();

		setUpLogicalNameComboBox();
		// Group the Radiobuttons
		rbReceipt.setToggleGroup(group);
		rbJournal.setToggleGroup(group);
		rbSlip.setToggleGroup(group);
		rbReceipt.setSelected(true);

		printNormalData.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {

				if (arg2.length() > arg1.length()) {
					updateInsertsEscSequencesToPrintNormalData((arg2.length() - arg1.length()));

				} else {
					updateDeletesEscSequencesToPrintNormalData(arg1.length() - arg2.length());
				}
			}
		});

		print2NormalFirstData.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {

				if (arg2.length() > arg1.length()) {
					updateInsertsEscSequencesToPrint2NormalDataFirst(arg2.length() - arg1.length());
				} else {
					updateDeletesEscSequencesToPrint2NormalDataFirst(arg1.length() - arg2.length());
				}
			}
		});

		print2NormalSecondData.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {

				if (arg2.length() > arg1.length()) {
					updateInsertsEscSequencesToPrint2NormalDataSecond(arg2.length() - arg1.length());
				} else {
					updateDeletesEscSequencesToPrint2NormalDataSecond(arg1.length() - arg2.length());
				}
			}
		});
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
	public void handleOpen(ActionEvent e) {
		try {
			if (logicalName.getValue() != null && !logicalName.getValue().isEmpty()) {
				printer.open(logicalName.getValue());
				buttonClaim.setDisable(false);
				System.out.println(printer.getState());
				setStatusLabel();
			} else {
				JOptionPane.showMessageDialog(null, "Choose a device!", "Logical name is empty",
						JOptionPane.WARNING_MESSAGE);
			}

		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to claim \"" + logicalName.getSelectionModel().getSelectedItem()
							+ "\"\nException: " + je.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Requests exclusive access to the device
	@FXML
	public void handleClaim(ActionEvent e) {
		try {
			printer.claim(0);
			deviceEnabled.setDisable(false);
			buttonRelease.setDisable(false);

		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to claim \"" + logicalName.getSelectionModel().getSelectedItem()
							+ "\"\nException: " + je.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		deviceMessages.setText("");
		try {
			if (deviceEnabled.isSelected()) {
				printer.setDeviceEnabled(true);
				setUpCheckboxes();
				setUpPageModeLabels();
			} else {
				printer.setDeviceEnabled(false);
			}
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
		}
	}

	// Releases exclusive access to the device. The device is also disabled.
	@FXML
	public void handleRelease(ActionEvent e) {
		try {
			printer.release();
			if (deviceEnabled.isSelected()) {
				deviceEnabled.setSelected(false);
				functionTab.setVisible(false);
			}
			deviceEnabled.setDisable(true);
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to release \"" + logicalName.getSelectionModel().getSelectedItem()
							+ "\"\nException: " + je.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Releases the device and its resources. Also the device is released.
	@FXML
	public void handleClose(ActionEvent e) {
		try {
			printer.close();
			if (!deviceEnabled.isDisable()) {
				deviceEnabled.setSelected(false);
				functionTab.setVisible(false);
			}
			buttonClaim.setDisable(true);
			deviceEnabled.setDisable(true);
			buttonRelease.setDisable(true);
			setStatusLabel();
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to close \"" + logicalName.getSelectionModel().getSelectedItem()
							+ "\"\nException: " + je.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Shows information of device
	@FXML
	public void handleInfo(ActionEvent e) {
		try {
			String ver = new Integer(printer.getDeviceServiceVersion()).toString();
			String msg = "Service Description: " + printer.getDeviceServiceDescription();
			msg = msg + "\nService Version: v" + new Integer(ver.substring(0, 1)) + "."
					+ new Integer(ver.substring(1, 4)) + "." + new Integer(ver.substring(4, 7));
			ver = new Integer(printer.getDeviceControlVersion()).toString();
			msg += "\n\nControl Description: " + printer.getDeviceControlDescription();
			msg += "\nControl Version: v" + new Integer(ver.substring(0, 1)) + "."
					+ new Integer(ver.substring(1, 4)) + "." + new Integer(ver.substring(4, 7));
			msg += "\n\nPhysical Device Name: " + printer.getPhysicalDeviceName();
			msg += "\nPhysical Device Description: " + printer.getPhysicalDeviceDescription();

			msg += "\n\nProperties:\n------------------------";

			msg += "\nCapStatisticsReporting: " + (printer.getCapStatisticsReporting());

			msg += "\nCapUpdateFirmware: " + (printer.getCapUpdateFirmware());

			msg += "\nCapCompareFirmwareVersion: " + (printer.getCapCompareFirmwareVersion());

			msg += "\nCapPowerReporting: "
					+ (printer.getCapPowerReporting() == JposConst.JPOS_PR_ADVANCED ? "Advanced" : (printer
							.getCapPowerReporting() == JposConst.JPOS_PR_STANDARD ? "Standard" : "None"));

			msg = msg + "\nCapCharacterSet: ";
			int charSet = printer.getCapCharacterSet();
			switch (charSet) {
			case LineDisplayConst.DISP_CCS_NUMERIC:
				msg = msg + "DISP_CCS_NUMERIC";
				break;
			case LineDisplayConst.DISP_CCS_ALPHA:
				msg = msg + "DISP_CCS_ALPHA";
				break;
			case LineDisplayConst.DISP_CCS_ASCII:
				msg = msg + "DISP_CCS_ASCII";
				break;
			case LineDisplayConst.DISP_CCS_KANA:
				msg = msg + "DISP_CCS_KANA";
				break;
			case LineDisplayConst.DISP_CCS_KANJI:
				msg = msg + "DISP_CCS_KANJI";
				break;
			case LineDisplayConst.DISP_CCS_UNICODE:
				msg = msg + "DISP_CCS_UNICODE";
				break;
			}

			msg = msg + "\nCapMapCharacterSet: " + printer.getCapMapCharacterSet();

			JOptionPane.showMessageDialog(null, msg, "Info", JOptionPane.INFORMATION_MESSAGE);

		} catch (JposException jpe) {
			JOptionPane.showMessageDialog(null, "Exception in Info\nException: " + jpe.getMessage(),
					"Exception", JOptionPane.ERROR_MESSAGE);
			System.err.println("Jpos exception " + jpe);
		}
	}

	// Shows statistics of device
	@FXML
	public void handleStatistics(ActionEvent e) {
		String[] stats = new String[] { "", "U_", "M_" };
		try {
			printer.retrieveStatistics(stats);
		} catch (JposException jpe) {
			jpe.printStackTrace();
		}

		try {
			DOMParser parser = new DOMParser();
			parser.parse(new InputSource(new java.io.StringReader(stats[1])));

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new ByteArrayInputStream(stats[1].getBytes()));

			printStatistics(doc.getDocumentElement(), "");

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException saxe) {
			saxe.printStackTrace();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}

		JOptionPane.showMessageDialog(null, statistics, "Statistics", JOptionPane.INFORMATION_MESSAGE);
		statistics = "";
	}

	// Method to parse the String XML and print the data
	private static void printStatistics(Node e, String tab) {
		if (e.getNodeType() == Node.TEXT_NODE) {
			statistics += tab + e.getNodeValue() + "\n";
			return;
		}

		if (!(e.getNodeName().equals("Name") || e.getNodeName().equals("Value")
				|| e.getNodeName().equals("UPOSStat") || e.getNodeName().equals("Event")
				|| e.getNodeName().equals("Equipment") || e.getNodeName().equals("Parameter")))
			statistics += tab + e.getNodeName();

		if (e.getNodeValue() != null) {
			statistics += tab + " " + e.getNodeValue();
		}

		NodeList childs = e.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			printStatistics(childs.item(i), " ");
		}
	}

	@FXML
	public void handleFirmware(ActionEvent e) {
		try {
			FirmwareUpdateDlg dlg = new FirmwareUpdateDlg(printer);
			dlg.setVisible(true);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Exception: " + e2.getMessage(), "Failed",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleFreezeEvents(ActionEvent e) {
		try {
			printer.setFreezeEvents(freezeEvents.selectedProperty().getValue());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleAsyncMode(ActionEvent e) {
		try {
			printer.setAsyncMode(asyncMode.selectedProperty().getValue());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleFlagWhenIdle(ActionEvent e) {
		try {
			printer.setFlagWhenIdle(flagWhenIdle.selectedProperty().getValue());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleSetMapMode(ActionEvent e) {
		if (mapMode.getSelectionModel().getSelectedItem() != null) {
			try {
				printer.setMapMode(POSPrinterConstantMapper.getConstantNumberFromString(mapMode
						.getSelectionModel().getSelectedItem()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleRotatePrint(ActionEvent e) {
		if (rotationMode.getSelectionModel().getSelectedItem() != null) {
			try {
				printer.rotatePrint(this.getSelectedStation(), POSPrinterConstantMapper
						.getConstantNumberFromString(rotationMode.getSelectionModel().getSelectedItem()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	/* General Printing */

	@FXML
	public void handleBeginInsertion(ActionEvent e) {
		try {
			printer.beginInsertion(0);
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleEndInsertion(ActionEvent e) {
		try {
			printer.endInsertion();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleBeginRemoval(ActionEvent e) {
		try {
			printer.beginRemoval(0);
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleEndRemoval(ActionEvent e) {
		try {
			printer.endRemoval();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handlePrintNormal(ActionEvent e) {
		System.out.println(addEscSequencesToPrintNormalData());
		try {
			printer.printNormal(getSelectedStation(), addEscSequencesToPrintNormalData());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handlePrintImmediate(ActionEvent e) {
		try {
			printer.printImmediate(getSelectedStation(), addEscSequencesToPrintNormalData());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleSetTopLogo(ActionEvent e) {
		try {
			printer.setLogo(POSPrinterConst.PTR_L_TOP, addEscSequencesToPrintNormalData());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleSetBottomLogo(ActionEvent e) {
		try {
			printer.setLogo(POSPrinterConst.PTR_L_BOTTOM, addEscSequencesToPrintNormalData());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleTransactionPrint(ActionEvent e) {
		try {
			printer.transactionPrint(this.getSelectedStation(), POSPrinterConstantMapper
					.getConstantNumberFromString(transactionPrint.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleAddEscapeSequenceNormal(ActionEvent e) {
		printNormalEscapeSequenceList.add(printNormalData.getCaretPosition());
		String text = printNormalData.getText();
		String first = text.substring(0, printNormalData.getCaretPosition());
		String second = text.substring(printNormalData.getCaretPosition(), printNormalData.lengthProperty()
				.getValue());

		printNormalData.setText(first + "|" + second);
		printNormalData.positionCaret(printNormalData.getLength() - 1);
	}

	@FXML
	public void handleSetLetterQuality(ActionEvent e) {
		if (rbJournal.isSelected()) {
			try {
				jrnLetterQuality = (!jrnLetterQuality);
				printer.setJrnLetterQuality(jrnLetterQuality);
				deviceMessages.setText(deviceMessages.getText() + "\nSet Journal Letter Quality to "
						+ jrnLetterQuality);
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}

		if (rbReceipt.isSelected()) {
			try {
				recLetterQuality = (!recLetterQuality);
				printer.setRecLetterQuality(recLetterQuality);
				deviceMessages.setText(deviceMessages.getText() + "\nSet Receipt Letter Quality to "
						+ recLetterQuality);
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}

		if (rbSlip.isSelected()) {
			try {
				slpLetterQuality = (!slpLetterQuality);
				printer.setSlpLetterQuality(slpLetterQuality);
				deviceMessages.setText(deviceMessages.getText() + "\nSet Slip Letter Quality to "
						+ slpLetterQuality);
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleCutPaper(ActionEvent e) {
		try {
			printer.cutPaper((int) cutPaperPercentage.getValue());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleValidateData(ActionEvent e) {
		try {
			printer.validateData(this.getSelectedStation(), addEscSequencesToPrintNormalData());
		} catch (JposException e1) {
			deviceMessages.setText(deviceMessages.getText() + "\n" + e1.getMessage());
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	/* Print2Normal */

	@FXML
	public void handleAddEscapeSequence2NormalFirst(ActionEvent e) {
		print2NormalFirstEscapeSequenceList.add(print2NormalFirstData.getCaretPosition());
		String text = print2NormalFirstData.getText();
		String first = text.substring(0, print2NormalFirstData.getCaretPosition());
		String second = text.substring(print2NormalFirstData.getCaretPosition(), print2NormalFirstData
				.lengthProperty().getValue());

		print2NormalFirstData.setText(first + "|" + second);
		print2NormalFirstData.positionCaret(print2NormalFirstData.getLength() - 1);

	}

	@FXML
	public void handleAddEscapeSequence2NormalSecond(ActionEvent e) {
		print2NormalSecondEscapeSequenceList.add(print2NormalSecondData.getCaretPosition());
		String text = print2NormalSecondData.getText();
		String first = text.substring(0, print2NormalSecondData.getCaretPosition());
		String second = text.substring(print2NormalSecondData.getCaretPosition(), print2NormalSecondData
				.lengthProperty().getValue());

		print2NormalSecondData.setText(first + "|" + second);
		print2NormalSecondData.positionCaret(print2NormalSecondData.getLength() - 1);

	}

	@FXML
	public void handlePrint2Normal(ActionEvent e) {
		try {
			printer.printTwoNormal(POSPrinterConstantMapper.getConstantNumberFromString(print2NormalStation
					.getSelectionModel().getSelectedItem()), addEscSequencesToPrint2NormalDataFirst(),
					addEscSequencesToPrint2NormalDataSecond());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	/* Barcode */

	@FXML
	public void handlePrintBarcode(ActionEvent e) {
		if (barcodeData.getText().equals("") || barcodeHeight.getText().equals("")
				|| barcodeWidth.getText().equals("")) {

			JOptionPane.showMessageDialog(null, "One of the parameter is not specified");
		} else {
			try {
				printer.printBarCode(this.getSelectedStation(), barcodeData.getText(),
						POSPrinterConstantMapper.getConstantNumberFromString(barcodeSymbology
								.getSelectionModel().getSelectedItem()), Integer.parseInt(barcodeHeight
								.getText()), Integer.parseInt(barcodeWidth.getText()),
						POSPrinterConstantMapper.getConstantNumberFromString(barcodeAlignment
								.getSelectionModel().getSelectedItem()), POSPrinterConstantMapper
								.getConstantNumberFromString(barcodeTextPosition.getSelectionModel()
										.getSelectedItem()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	/* Bitmap */

	@FXML
	public void handlePrintBitmap(ActionEvent e) {
		if (bitmapPath.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Bitmap Path is not specified!");
		} else {
			try {
				printer.printBitmap(this.getSelectedStation(), bitmapPath.getText(), POSPrinterConstantMapper
						.getConstantNumberFromString(bitmapWidth.getSelectionModel().getSelectedItem()),
						POSPrinterConstantMapper.getConstantNumberFromString(bitmapAlignment
								.getSelectionModel().getSelectedItem()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handlePrintMemoryBitmap(ActionEvent e) {
		if (bitmapPath.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Binary Path is not specified!");
		} else {
			try {
				printer.printMemoryBitmap(this.getSelectedStation(), getBytesFromFile(bitmapPath.getText()),
						POSPrinterConstantMapper.getConstantNumberFromString(bitmapWidth.getSelectionModel()
								.getSelectedItem()), getTypeFromFile(bitmapPath.getText()),
						POSPrinterConstantMapper.getConstantNumberFromString(bitmapAlignment
								.getSelectionModel().getSelectedItem()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleSetBitmap(ActionEvent e) {
		if (bitmapPath.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Bitmap Path is not specified!");
		} else {
			try {
				printer.setBitmap(bitmapNumber.getSelectionModel().getSelectedItem(), getSelectedStation(),
						bitmapPath.getText(), POSPrinterConstantMapper
								.getConstantNumberFromString(bitmapWidth.getSelectionModel()
										.getSelectedItem()), POSPrinterConstantMapper
								.getConstantNumberFromString(bitmapAlignment.getSelectionModel()
										.getSelectedItem()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleRotateSpecial(ActionEvent e) {
		try {
			printer.setRotateSpecial(POSPrinterConstantMapper.getConstantNumberFromString(rotationMode
					.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	/* Draw Ruled Line */

	@FXML
	public void handleDrawRuledLine(ActionEvent e) {

		if (drawLineStartPosX.getText().equals("") || drawLineStartPosY.getText().equals("")
				|| drawLineEndPosX.getText().equals("") || drawLineEndPosY.getText().equals("")
				|| drawLineWidth.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "One of the Parameter is not specified!");
		} else {
			String position = drawLineStartPosX.getText() + "," + drawLineStartPosY.getText() + ","
					+ drawLineEndPosX.getText() + "," + drawLineEndPosY.getText();
			try {
				printer.drawRuledLine(getSelectedStation(), position,
						POSPrinterConstantMapper.getConstantNumberFromString(drawLineDirection
								.getSelectionModel().getSelectedItem()), Integer.parseInt(drawLineWidth
								.getText()), POSPrinterConstantMapper
								.getConstantNumberFromString(drawLineStyle.getSelectionModel()
										.getSelectedItem()), POSPrinterConstantMapper
								.getConstantNumberFromString(drawLineColor.getSelectionModel()
										.getSelectedItem()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	/* Page Mode */

	@FXML
	public void handleSetHorizontalPosition(ActionEvent e) {
		if (pageModeHorizontalPosition.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Horizontal Position is not specified!");
		} else {
			try {
				printer.setPageModeHorizontalPosition(Integer.parseInt(pageModeHorizontalPosition.getText()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleSetVerticalPosition(ActionEvent e) {
		if (pageModeVerticalPosition.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Vertical Position is not specified!");
		} else {
			try {
				printer.setPageModeVerticalPosition(Integer.parseInt(pageModeVerticalPosition.getText()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleSetPrintArea(ActionEvent e) {
		if (pageModePrintAreaStartPosX.getText().equals("")
				|| pageModePrintAreaStartPosY.getText().equals("")
				|| pageModePrintAreaEndPosX.getText().equals("")
				|| pageModePrintAreaEndPosX.getText().equals("")) {

			JOptionPane.showMessageDialog(null, "One of the Parameter is not specified!");
		} else {
			String area = pageModePrintAreaStartPosX.getText() + "," + pageModePrintAreaStartPosY.getText()
					+ "," + pageModePrintAreaEndPosX.getText() + "," + pageModePrintAreaEndPosX.getText();
			try {
				printer.setPageModePrintArea(area);
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleSetPrintDirection(ActionEvent e) {
		try {
			printer.setPageModePrintDirection(POSPrinterConstantMapper
					.getConstantNumberFromString(pageModePrintDirection.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleSetPageModeStation(ActionEvent e) {
		try {
			printer.setPageModeStation(POSPrinterConstantMapper
					.getConstantNumberFromString(pageModePrintStation.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleClearPrintArea(ActionEvent e) {
		try {
			printer.clearPrintArea();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handlePageModePrint(ActionEvent e) {
		try {
			printer.pageModePrint(POSPrinterConstantMapper.getConstantNumberFromString(pageModePrint
					.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	/* Misc */

	@FXML
	public void handleChangePrintSide(ActionEvent e) {
		try {
			printer.changePrintSide(POSPrinterConstantMapper.getConstantNumberFromString(printSide
					.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleMarkFeed(ActionEvent e) {
		try {
			printer.markFeed(POSPrinterConstantMapper.getConstantNumberFromString(markFeed
					.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleSetCharacterSet(ActionEvent e) {
		try {
			printer.setCharacterSet(characterSet.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleSetMapCharacterSet(ActionEvent e) {
		try {
			printer.setMapCharacterSet(mapCharacterSet.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleSetLineSpacing(ActionEvent e) {
		if (lineSpacing.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Line Spacing is not specified!");
		} else {

			if (rbJournal.isSelected()) {
				try {
					printer.setJrnLineSpacing(Integer.parseInt(lineSpacing.getText()));
				} catch (JposException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}

			if (rbReceipt.isSelected()) {
				try {
					printer.setRecLineSpacing(Integer.parseInt(lineSpacing.getText()));
				} catch (JposException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}

			if (rbSlip.isSelected()) {
				try {
					printer.setSlpLineSpacing(Integer.parseInt(lineSpacing.getText()));
				} catch (JposException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		}
	}

	@FXML
	public void handleSetLineChars(ActionEvent e) {
		if (lineChars.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Line Chars is not specified!");
		} else {
			if (rbJournal.isSelected()) {
				try {
					printer.setJrnLineChars(Integer.parseInt(lineChars.getText()));
				} catch (JposException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}

			if (rbReceipt.isSelected()) {
				try {
					printer.setRecLineChars(Integer.parseInt(lineChars.getText()));
				} catch (JposException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}

			if (rbSlip.isSelected()) {
				try {
					printer.setSlpLineChars(Integer.parseInt(lineChars.getText()));
				} catch (JposException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		}
	}

	@FXML
	public void handleSetLineHeight(ActionEvent e) {
		if (lineHeight.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Line Height is not specified!");
		} else {
			if (rbJournal.isSelected()) {
				try {
					printer.setJrnLineHeight(Integer.parseInt(lineHeight.getText()));
				} catch (JposException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}

			if (rbReceipt.isSelected()) {
				try {
					printer.setRecLineHeight(Integer.parseInt(lineHeight.getText()));
				} catch (JposException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}

			if (rbSlip.isSelected()) {
				try {
					printer.setSlpLineHeight(Integer.parseInt(lineHeight.getText()));
				} catch (JposException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		}
	}

	@FXML
	public void handleSetCurrentCartridge(ActionEvent e) {
		if (rbJournal.isSelected()) {
			try {
				printer.setJrnCurrentCartridge(POSPrinterConstantMapper
						.getConstantNumberFromString(currentCartridge.getSelectionModel().getSelectedItem()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}

		if (rbReceipt.isSelected()) {
			try {
				printer.setRecCurrentCartridge(POSPrinterConstantMapper
						.getConstantNumberFromString(currentCartridge.getSelectionModel().getSelectedItem()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}

		if (rbSlip.isSelected()) {
			try {
				printer.setSlpCurrentCartridge(POSPrinterConstantMapper
						.getConstantNumberFromString(currentCartridge.getSelectionModel().getSelectedItem()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleChooseBitmap(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Image");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			bitmapPath.setText(f.getAbsolutePath());
		}
	}

	private byte[] getBytesFromFile(String path) {
		byte[] bytes = null;
		BufferedImage originalImage = null;
		try {
			originalImage = ImageIO.read(new File(path));
		} catch (IOException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		if (originalImage == null) {
			JOptionPane.showMessageDialog(null, "Image has a Bad Format!");
			return null;
		}

		// change Imgage Format
		BufferedImage newBufferedImage = new BufferedImage(originalImage.getWidth(),
				originalImage.getHeight(), BufferedImage.TYPE_BYTE_BINARY);

		newBufferedImage.createGraphics().drawImage(originalImage, 0, 0, Color.WHITE, null);

		// convert BufferedImage to byte array
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(newBufferedImage, "bmp", baos);
			baos.flush();

			bytes = baos.toByteArray();
			baos.close();
		} catch (IOException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

		return bytes;
	}

	private int getTypeFromFile(String path) throws IOException {
		String extension = "";

		int i = path.lastIndexOf('.');
		if (i > 0) {
			extension = path.substring(i + 1);
		}

		if (extension.toLowerCase().equals("bmp")) {
			return POSPrinterConstantMapper.getConstantNumberFromString(POSPrinterConstantMapper.PTR_BMT_BMP
					.getConstant());
		}

		if (extension.toLowerCase().equals("gif")) {
			return POSPrinterConstantMapper.getConstantNumberFromString(POSPrinterConstantMapper.PTR_BMT_GIF
					.getConstant());
		}

		if (extension.toLowerCase().equals("jpeg") || extension.toLowerCase().equals("jpg")) {
			return POSPrinterConstantMapper.getConstantNumberFromString(POSPrinterConstantMapper.PTR_BMT_JPEG
					.getConstant());
		}

		throw new IOException("Image has a bad format!");
	}

	/*
	 * Initialize ComboBoxes
	 */

	private void setUpRotationMode() {
		rotationMode.getItems().clear();
		rotationMode.getItems().add(POSPrinterConstantMapper.PTR_RP_NORMAL.getConstant());
		rotationMode.getItems().add(POSPrinterConstantMapper.PTR_RP_RIGHT90.getConstant());
		rotationMode.getItems().add(POSPrinterConstantMapper.PTR_RP_LEFT90.getConstant());
		rotationMode.getItems().add(POSPrinterConstantMapper.PTR_RP_ROTATE180.getConstant());
		rotationMode.setValue(POSPrinterConstantMapper.PTR_RP_NORMAL.getConstant());
	}

	private void setUpMapMode() {
		mapMode.getItems().clear();
		mapMode.getItems().add(POSPrinterConstantMapper.PTR_MM_DOTS.getConstant());
		mapMode.getItems().add(POSPrinterConstantMapper.PTR_MM_ENGLISH.getConstant());
		mapMode.getItems().add(POSPrinterConstantMapper.PTR_MM_METRIC.getConstant());
		mapMode.getItems().add(POSPrinterConstantMapper.PTR_MM_TWIPS.getConstant());
		mapMode.setValue(POSPrinterConstantMapper.PTR_MM_DOTS.getConstant());
	}

	private void setUpTransactionPrint() {
		transactionPrint.getItems().clear();
		transactionPrint.getItems().add(POSPrinterConstantMapper.PTR_TP_NORMAL.getConstant());
		transactionPrint.getItems().add(POSPrinterConstantMapper.PTR_TP_TRANSACTION.getConstant());
		transactionPrint.setValue(POSPrinterConstantMapper.PTR_TP_NORMAL.getConstant());
	}

	private void setUpBarcodeSymbology() {
		barcodeSymbology.getItems().clear();
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_UPCA.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_UPCE.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_JAN8.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_EAN8.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_JAN13.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_EAN13.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_TF.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_ITF.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_Codabar.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_Code39.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_Code93.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_Code128.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_UPCA_S.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_UPCE_S.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_UPCD1.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_UPCD2.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_UPCD3.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_UPCD4.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_UPCD5.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_EAN8_S.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_EAN13_S.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_EAN128.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_OCRA.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_Code128_Parsed.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_RSS14.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_RSS_EXPANDED.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_GS1DATABAR.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_GS1DATABAR_E.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_GS1DATABAR_S.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_GS1DATABAR_E_S.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_PDF417.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_MAXICODE.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_DATAMATRIX.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_QRCODE.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_UQRCODE.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_AZTEC.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_UPDF417.getConstant());
		barcodeSymbology.getItems().add(POSPrinterConstantMapper.PTR_BCS_OTHER.getConstant());
		barcodeSymbology.setValue(POSPrinterConstantMapper.PTR_BCS_UPCA.getConstant());

	}

	private void setUpBarcodeTextPosition() {
		barcodeTextPosition.getItems().clear();
		barcodeTextPosition.getItems().add(POSPrinterConstantMapper.PTR_BC_TEXT_NONE.getConstant());
		barcodeTextPosition.getItems().add(POSPrinterConstantMapper.PTR_BC_TEXT_ABOVE.getConstant());
		barcodeTextPosition.getItems().add(POSPrinterConstantMapper.PTR_BC_TEXT_BELOW.getConstant());
		barcodeTextPosition.setValue(POSPrinterConstantMapper.PTR_BC_TEXT_NONE.getConstant());
	}

	private void setUpBarcodeAlignment() {
		barcodeAlignment.getItems().clear();
		barcodeAlignment.getItems().add(POSPrinterConstantMapper.PTR_BM_LEFT.getConstant());
		barcodeAlignment.getItems().add(POSPrinterConstantMapper.PTR_BM_CENTER.getConstant());
		barcodeAlignment.getItems().add(POSPrinterConstantMapper.PTR_BM_RIGHT.getConstant());
		barcodeAlignment.setValue(POSPrinterConstantMapper.PTR_BM_LEFT.getConstant());
	}

	private void setUpBitmapWidth() {
		bitmapWidth.getItems().clear();
		bitmapWidth.getItems().add(POSPrinterConstantMapper.PTR_BM_ASIS.getConstant());
		bitmapWidth.setValue(POSPrinterConstantMapper.PTR_BM_ASIS.getConstant());
	}

	private void setUpBitmapAlignment() {
		bitmapAlignment.getItems().clear();
		bitmapAlignment.getItems().add(POSPrinterConstantMapper.PTR_BM_LEFT.getConstant());
		bitmapAlignment.getItems().add(POSPrinterConstantMapper.PTR_BM_CENTER.getConstant());
		bitmapAlignment.getItems().add(POSPrinterConstantMapper.PTR_BM_RIGHT.getConstant());
		bitmapAlignment.setValue(POSPrinterConstantMapper.PTR_BM_LEFT.getConstant());
	}

	private void setUpBitmapNumber() {
		bitmapNumber.getItems().clear();
		for (int i = 0; i < 100; i++) {
			bitmapNumber.getItems().add(i);
		}
		bitmapNumber.setValue(0);
	}

	private void setUpDrawLineDirection() {
		drawLineDirection.getItems().clear();
		drawLineDirection.getItems().add(POSPrinterConstantMapper.PTR_RL_HORIZONTAL.getConstant());
		drawLineDirection.getItems().add(POSPrinterConstantMapper.PTR_RL_VERTICAL.getConstant());
		drawLineDirection.setValue(POSPrinterConstantMapper.PTR_RL_HORIZONTAL.getConstant());
	}

	private void setUpDrawLineStyle() {
		drawLineStyle.getItems().clear();
		drawLineStyle.getItems().add(POSPrinterConstantMapper.PTR_LS_SINGLE_SOLID_LINE.getConstant());
		drawLineStyle.getItems().add(POSPrinterConstantMapper.PTR_LS_DOUBLE_SOLID_LINE.getConstant());
		drawLineStyle.getItems().add(POSPrinterConstantMapper.PTR_LS_BROKEN_LINE.getConstant());
		drawLineStyle.getItems().add(POSPrinterConstantMapper.PTR_LS_CHAIN_LINE.getConstant());
		drawLineStyle.setValue(POSPrinterConstantMapper.PTR_LS_SINGLE_SOLID_LINE.getConstant());
	}

	private void setUpDrawLineColor() {
		drawLineColor.getItems().clear();
		drawLineColor.getItems().add(POSPrinterConstantMapper.PTR_COLOR_PRIMARY.getConstant());
		drawLineColor.getItems().add(POSPrinterConstantMapper.PTR_COLOR_CUSTOM1.getConstant());
		drawLineColor.getItems().add(POSPrinterConstantMapper.PTR_COLOR_CUSTOM2.getConstant());
		drawLineColor.getItems().add(POSPrinterConstantMapper.PTR_COLOR_CUSTOM3.getConstant());
		drawLineColor.getItems().add(POSPrinterConstantMapper.PTR_COLOR_CUSTOM4.getConstant());
		drawLineColor.getItems().add(POSPrinterConstantMapper.PTR_COLOR_CUSTOM5.getConstant());
		drawLineColor.getItems().add(POSPrinterConstantMapper.PTR_COLOR_CUSTOM6.getConstant());
		drawLineColor.getItems().add(POSPrinterConstantMapper.PTR_COLOR_CYAN.getConstant());
		drawLineColor.getItems().add(POSPrinterConstantMapper.PTR_COLOR_MAGENTA.getConstant());
		drawLineColor.getItems().add(POSPrinterConstantMapper.PTR_COLOR_YELLOW.getConstant());
		drawLineColor.getItems().add(POSPrinterConstantMapper.PTR_COLOR_FULL.getConstant());
		drawLineColor.setValue(POSPrinterConstantMapper.PTR_COLOR_PRIMARY.getConstant());
	}

	private void setUpPrint2NormalStation() {
		print2NormalStation.getItems().clear();
		print2NormalStation.getItems().add(POSPrinterConstantMapper.PTR_S_RECEIPT_SLIP.getConstant());
		print2NormalStation.getItems().add(POSPrinterConstantMapper.PTR_S_JOURNAL_RECEIPT.getConstant());
		print2NormalStation.getItems().add(POSPrinterConstantMapper.PTR_S_JOURNAL_SLIP.getConstant());
		print2NormalStation.setValue(POSPrinterConstantMapper.PTR_S_RECEIPT_SLIP.getConstant());
	}

	private void setUpPageModePrintDirection() {
		pageModePrintDirection.getItems().clear();
		pageModePrintDirection.getItems().add(POSPrinterConstantMapper.PTR_PD_LEFT_TO_RIGHT.getConstant());
		pageModePrintDirection.getItems().add(POSPrinterConstantMapper.PTR_PD_BOTTOM_TO_TOP.getConstant());
		pageModePrintDirection.getItems().add(POSPrinterConstantMapper.PTR_PD_RIGHT_TO_LEFT.getConstant());
		pageModePrintDirection.getItems().add(POSPrinterConstantMapper.PTR_PD_TOP_TO_BOTTOM.getConstant());
		pageModePrintDirection.setValue(POSPrinterConstantMapper.PTR_PD_LEFT_TO_RIGHT.getConstant());
	}

	private void setUpPageModePrintStation() {
		pageModePrintStation.getItems().clear();
		pageModePrintStation.getItems().add(POSPrinterConstantMapper.PTR_S_RECEIPT.getConstant());
		pageModePrintStation.getItems().add(POSPrinterConstantMapper.PTR_S_SLIP.getConstant());
		pageModePrintStation.setValue(POSPrinterConstantMapper.PTR_S_RECEIPT.getConstant());
	}

	private void setUpPageModePrintCommands() {
		pageModePrint.getItems().clear();
		pageModePrint.getItems().add(POSPrinterConstantMapper.PTR_PM_PAGE_MODE.getConstant());
		pageModePrint.getItems().add(POSPrinterConstantMapper.PTR_PM_PRINT_SAVE.getConstant());
		pageModePrint.getItems().add(POSPrinterConstantMapper.PTR_PM_NORMAL.getConstant());
		pageModePrint.getItems().add(POSPrinterConstantMapper.PTR_PM_CANCEL.getConstant());
		pageModePrint.setValue(POSPrinterConstantMapper.PTR_PM_PAGE_MODE.getConstant());
	}

	private void setUpPrintSide() {
		printSide.getItems().clear();
		printSide.getItems().add(POSPrinterConstantMapper.PTR_PS_UNKNOWN.getConstant());
		printSide.getItems().add(POSPrinterConstantMapper.PTR_PS_SIDE1.getConstant());
		printSide.getItems().add(POSPrinterConstantMapper.PTR_PS_SIDE2.getConstant());
		printSide.getItems().add(POSPrinterConstantMapper.PTR_PS_OPPOSITE.getConstant());
		printSide.setValue(POSPrinterConstantMapper.PTR_PS_UNKNOWN.getConstant());
	}

	private void setUpMarkFeed() {
		markFeed.getItems().clear();
		markFeed.getItems().add(POSPrinterConstantMapper.PTR_MF_TO_TAKEUP.getConstant());
		markFeed.getItems().add(POSPrinterConstantMapper.PTR_MF_TO_CUTTER.getConstant());
		markFeed.getItems().add(POSPrinterConstantMapper.PTR_MF_TO_CURRENT_TOF.getConstant());
		markFeed.getItems().add(POSPrinterConstantMapper.PTR_MF_TO_NEXT_TOF.getConstant());
		markFeed.setValue(POSPrinterConstantMapper.PTR_MF_TO_TAKEUP.getConstant());
	}

	private void setUpCharacterSet() {
		characterSet.getItems().clear();
		try {
			for (int i = 0; i < printer.getCharacterSetList().split(",").length; i++) {
				characterSet.getItems().add(Integer.parseInt((printer.getCharacterSetList().split(","))[i]));
				if (i == 0) {
					characterSet.setValue(Integer.parseInt((printer.getCharacterSetList().split(","))[i]));
				}
			}

		} catch (JposException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error occured when getting the CharacterSetList",
					"Error occured!", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void setUpMapCharacterSet() {
		mapCharacterSet.getItems().clear();
		mapCharacterSet.getItems().add(true);
		mapCharacterSet.getItems().add(false);
		mapCharacterSet.setValue(true);
	}

	private void setUpCurrentCartridge() {
		currentCartridge.getItems().clear();
		currentCartridge.getItems().add(POSPrinterConstantMapper.PTR_COLOR_PRIMARY.getConstant());
		currentCartridge.getItems().add(POSPrinterConstantMapper.PTR_COLOR_CUSTOM1.getConstant());
		currentCartridge.getItems().add(POSPrinterConstantMapper.PTR_COLOR_CUSTOM2.getConstant());
		currentCartridge.getItems().add(POSPrinterConstantMapper.PTR_COLOR_CUSTOM3.getConstant());
		currentCartridge.getItems().add(POSPrinterConstantMapper.PTR_COLOR_CUSTOM4.getConstant());
		currentCartridge.getItems().add(POSPrinterConstantMapper.PTR_COLOR_CUSTOM5.getConstant());
		currentCartridge.getItems().add(POSPrinterConstantMapper.PTR_COLOR_CUSTOM6.getConstant());
		currentCartridge.getItems().add(POSPrinterConstantMapper.PTR_COLOR_CYAN.getConstant());
		currentCartridge.getItems().add(POSPrinterConstantMapper.PTR_COLOR_MAGENTA.getConstant());
		currentCartridge.getItems().add(POSPrinterConstantMapper.PTR_COLOR_YELLOW.getConstant());
		currentCartridge.setValue(POSPrinterConstantMapper.PTR_COLOR_PRIMARY.getConstant());
	}

	private void setUpCheckboxes() {
		setUpRotationMode();
		setUpMapMode();
		setUpTransactionPrint();
		setUpBarcodeSymbology();
		setUpBarcodeTextPosition();
		setUpBarcodeAlignment();
		setUpBitmapWidth();
		setUpBitmapAlignment();
		setUpBitmapNumber();
		setUpDrawLineDirection();
		setUpDrawLineStyle();
		setUpDrawLineColor();
		setUpPrint2NormalStation();
		setUpPageModePrintDirection();
		setUpPageModePrintStation();
		setUpPageModePrintCommands();
		setUpPrintSide();
		setUpMarkFeed();
		setUpCharacterSet();
		setUpCurrentCartridge();
		setUpMapCharacterSet();
	}

	private int getSelectedStation() throws JposException {
		if (rbJournal.isSelected()) {
			return POSPrinterConst.PTR_S_JOURNAL;
		}

		if (rbReceipt.isSelected()) {
			return POSPrinterConst.PTR_S_RECEIPT;
		}

		if (rbSlip.isSelected()) {
			return POSPrinterConst.PTR_S_SLIP;
		}
		throw new JposException(JposConst.JPOS_E_FAILURE, "No Station is selected!");
	}

	private void setUpPageModeLabels() {
		try {
			pageModeArea.setText(printer.getPageModeArea());
			pageModeDescriptor.setText(POSPrinterConstantMapper.getPageModeDescriptorConstantName(printer
					.getPageModeDescriptor()));
		} catch (JposException e) {
		}
	}

	private String addEscSequencesToPrintNormalData() {
		String ret = printNormalData.getText();
		if (!printNormalEscapeSequenceList.isEmpty()) {
			int i = 0;
			for (int num : printNormalEscapeSequenceList) {
				num += i;
				ret = ret.substring(0, num) + ESC + ret.substring(num, ret.length());
				i++;
			}
		}
		return ret;
	}

	/**
	 * Compare Length with ArrayList pos Increment Index if > Cursor Pos
	 */
	private void updateInsertsEscSequencesToPrintNormalData(int diff) {
		int cursorPos = printNormalData.getCaretPosition();
		for (int pos : printNormalEscapeSequenceList) {
			if (pos > cursorPos) {
				printNormalEscapeSequenceList.remove((Object) pos);
				printNormalEscapeSequenceList.add(0, pos + diff);
			}
			if (printNormalEscapeSequenceList.isEmpty()) {
				break;
			}
		}
	}

	/**
	 * Compare Length with ArrayList pos Decrement Index if > Cursor Pos
	 */
	private void updateDeletesEscSequencesToPrintNormalData(int diff) {
		// Compare Length with ArrayList pos
		// Decrement Index -1 > Cursor Pos
		int cursorPos = printNormalData.getCaretPosition();
		for (int pos : printNormalEscapeSequenceList) {
			if (pos > printNormalData.getText().length()) {
				printNormalEscapeSequenceList.remove((Object) pos);
				System.out.println("Removed: " + pos);
				if (printNormalEscapeSequenceList.isEmpty()) {
					break;
				}
				continue;
			}
			if (pos == cursorPos - 1) {
				printNormalEscapeSequenceList.remove((Object) pos);
				if (printNormalEscapeSequenceList.isEmpty()) {
					break;
				}
			}
			if (pos > cursorPos) {
				printNormalEscapeSequenceList.remove((Object) pos);
				printNormalEscapeSequenceList.add(0, pos - diff);
			}
			if (printNormalEscapeSequenceList.isEmpty()) {
				break;
			}
		}
	}

	/* Print 2 Normal First */

	private String addEscSequencesToPrint2NormalDataFirst() {

		String ret = print2NormalFirstData.getText();
		if (!print2NormalFirstEscapeSequenceList.isEmpty()) {
			int i = 0;
			for (int num : print2NormalFirstEscapeSequenceList) {
				num += i;
				ret = ret.substring(0, num) + ESC + ret.substring(num, ret.length());
				i++;
			}
		}
		return ret;
	}

	/**
	 * Compare Length with ArrayList pos Increment Index if > Cursor Pos
	 */
	private void updateInsertsEscSequencesToPrint2NormalDataFirst(int diff) {
		int cursorPos = print2NormalFirstData.getCaretPosition();
		for (int pos : print2NormalFirstEscapeSequenceList) {
			if (pos > cursorPos) {
				print2NormalFirstEscapeSequenceList.remove((Object) pos);
				print2NormalFirstEscapeSequenceList.add(0, pos + diff);
			}
			if (printNormalEscapeSequenceList.isEmpty()) {
				break;
			}
		}
	}

	/**
	 * Compare Length with ArrayList pos Decrement Index if > Cursor Pos
	 */
	private void updateDeletesEscSequencesToPrint2NormalDataFirst(int diff) {
		// Compare Length with ArrayList pos
		// Decrement Index -1 > Cursor Pos
		int cursorPos = print2NormalFirstData.getCaretPosition();
		for (int pos : print2NormalFirstEscapeSequenceList) {
			if (pos > print2NormalFirstData.getText().length()) {
				print2NormalFirstEscapeSequenceList.remove((Object) pos);
				System.out.println("Removed: " + pos);
				if (print2NormalFirstEscapeSequenceList.isEmpty()) {
					break;
				}
				continue;
			}
			if (pos == cursorPos - 1) {
				print2NormalFirstEscapeSequenceList.remove((Object) pos);
				if (print2NormalFirstEscapeSequenceList.isEmpty()) {
					break;
				}
			}
			if (pos > cursorPos) {
				print2NormalFirstEscapeSequenceList.remove((Object) pos);
				print2NormalFirstEscapeSequenceList.add(0, pos - diff);
			}
			if (print2NormalFirstEscapeSequenceList.isEmpty()) {
				break;
			}
		}
	}

	/* Print 2 Normal Second */

	private String addEscSequencesToPrint2NormalDataSecond() {
		String ret = print2NormalSecondData.getText();
		if (!print2NormalSecondEscapeSequenceList.isEmpty()) {
			int i = 0;
			for (int num : print2NormalSecondEscapeSequenceList) {
				num += i;
				ret = ret.substring(0, num) + ESC + ret.substring(num, ret.length());
				i++;
			}
		}
		return ret;
	}

	/**
	 * Compare Length with ArrayList pos Increment Index if > Cursor Pos
	 */
	private void updateInsertsEscSequencesToPrint2NormalDataSecond(int diff) {
		int cursorPos = print2NormalSecondData.getCaretPosition();
		for (int pos : print2NormalSecondEscapeSequenceList) {
			if (pos > cursorPos) {
				print2NormalSecondEscapeSequenceList.remove((Object) pos);
				print2NormalSecondEscapeSequenceList.add(0, pos + diff);
			}
			if (print2NormalSecondEscapeSequenceList.isEmpty()) {
				break;
			}
		}
	}

	/**
	 * Compare Length with ArrayList pos Decrement Index if > Cursor Pos
	 */
	private void updateDeletesEscSequencesToPrint2NormalDataSecond(int diff) {
		// Compare Length with ArrayList pos
		// Decrement Index -1 > Cursor Pos
		int cursorPos = print2NormalSecondData.getCaretPosition();
		for (int pos : print2NormalSecondEscapeSequenceList) {
			if (pos > print2NormalSecondData.getText().length()) {
				print2NormalSecondEscapeSequenceList.remove((Object) pos);
				System.out.println("Removed: " + pos);
				if (print2NormalSecondEscapeSequenceList.isEmpty()) {
					break;
				}
				continue;
			}

			if (pos == cursorPos - 1) {
				print2NormalSecondEscapeSequenceList.remove((Object) pos);
				if (print2NormalSecondEscapeSequenceList.isEmpty()) {
					break;
				}
			}

			if (pos > cursorPos) {
				print2NormalSecondEscapeSequenceList.remove((Object) pos);
				print2NormalSecondEscapeSequenceList.add(0, pos - diff);
			}

			if (print2NormalSecondEscapeSequenceList.isEmpty()) {
				break;
			}
		}
	}

	/**
	 * Set StatusLabel
	 */
	private void setStatusLabel() {
		if (printer.getState() == JposConst.JPOS_S_IDLE) {
			statusLabel.setText("JPOS_S_IDLE");
		}

		if (printer.getState() == JposConst.JPOS_S_CLOSED) {
			statusLabel.setText("JPOS_S_CLOSED");
		}

		if (printer.getState() == JposConst.JPOS_S_BUSY) {
			statusLabel.setText("JPOS_S_BUSY");
		}

		if (printer.getState() == JposConst.JPOS_S_ERROR) {
			statusLabel.setText("JPOS_S_ERROR");
		}
	}

}
