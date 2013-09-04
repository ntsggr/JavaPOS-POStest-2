package postest2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jpos.JposConst;
import jpos.JposException;
import jpos.POSPrinter;
import jpos.POSPrinterConst;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class POSPrinterController extends CommonController implements Initializable {

	// Common
	@FXML
	@RequiredState(JposState.ENABLED)
	public ComboBox<String> rotationMode;
	@FXML
	@RequiredState(JposState.ENABLED)
	public ComboBox<String> mapMode;
	@FXML
	@RequiredState(JposState.OPENED)
	public CheckBox asyncMode;
	@FXML
	@RequiredState(JposState.ENABLED)
	public CheckBox flagWhenIdle;
	@FXML
	@RequiredState(JposState.ENABLED)
	public TextArea deviceMessages;

	// Stations
	@FXML
	@RequiredState(JposState.ENABLED)
	public RadioButton rbReceipt;
	@FXML
	@RequiredState(JposState.ENABLED)
	public RadioButton rbJournal;
	@FXML
	@RequiredState(JposState.ENABLED)
	public RadioButton rbSlip;

	// Group for the radiobuttons
	@FXML
	public final ToggleGroup group = new ToggleGroup();

	// TabPane
	@FXML
	@RequiredState(JposState.ENABLED)
	public TabPane functionTab;

	// General Printing
	@FXML
	public ComboBox<String> transactionPrint;
	@FXML
	public TextArea printNormalData;

	@FXML
	public Slider cutPaperPercentage;

	// Barcode
	@FXML
	public TextField barcodeHeight;
	@FXML
	public TextField barcodeWidth;
	@FXML
	public TextField barcodeData;
	@FXML
	public ComboBox<String> barcodeSymbology;
	@FXML
	public ComboBox<String> barcodeTextPosition;
	@FXML
	public ComboBox<String> barcodeAlignment;

	// Bitmap
	@FXML
	public TextField bitmapPath;
	@FXML
	public ComboBox<String> bitmapWidth;
	@FXML
	public ComboBox<String> bitmapAlignment;
	@FXML
	public ComboBox<Integer> bitmapNumber;

	// Draw Line
	@FXML
	public TextField drawLineStartPosX;
	@FXML
	public TextField drawLineStartPosY;
	@FXML
	public TextField drawLineEndPosX;
	@FXML
	public TextField drawLineEndPosY;
	@FXML
	public TextField drawLineWidth;
	@FXML
	public ComboBox<String> drawLineDirection;
	@FXML
	public ComboBox<String> drawLineStyle;
	@FXML
	public ComboBox<String> drawLineColor;

	// Print2Normal
	@FXML
	public TextArea print2NormalFirstData;
	@FXML
	public TextArea print2NormalSecondData;
	@FXML
	public ComboBox<String> print2NormalStation;

	// PageMode
	@FXML
	public TextField pageModeHorizontalPosition;
	@FXML
	public TextField pageModeVerticalPosition;
	@FXML
	public TextField pageModePrintAreaStartPosX;
	@FXML
	public TextField pageModePrintAreaStartPosY;
	@FXML
	public TextField pageModePrintAreaEndPosX;
	@FXML
	public TextField pageModePrintAreaEndPosY;
	@FXML
	public ComboBox<String> pageModePrintDirection;
	@FXML
	public ComboBox<String> pageModePrintStation;
	@FXML
	public ComboBox<String> pageModePrint;
	@FXML
	public Label pageModeArea;
	@FXML
	public Label pageModeDescriptor;

	// Misc
	@FXML
	public TextField lineChars;
	@FXML
	public TextField lineHeight;
	@FXML
	public ComboBox<String> printSide;
	@FXML
	public ComboBox<String> markFeed;
	@FXML
	public ComboBox<Integer> characterSet;
	@FXML
	public ComboBox<Boolean> mapCharacterSet;
	@FXML
	public ComboBox<String> currentCartridge;
	@FXML
	public TextField lineSpacing;
	@FXML
	public ComboBox<String> cartridgeNotify;

	// DirectIO
	@FXML
	public TextField directIO_command;
	@FXML
	public TextField directIO_data;
	@FXML
	public TextField directIO_object;

	// Holds position of ESC-Characters.
	// Need because Textarea delete ESC everytime it changes
	private ArrayList<Integer> printNormalEscapeSequenceList;
	private ArrayList<Integer> print2NormalFirstEscapeSequenceList;
	private ArrayList<Integer> print2NormalSecondEscapeSequenceList;

	// Escape-Character
	final char ESC = (char) 0x1B;

	// Letter Quality
	private boolean jrnLetterQuality = false;
	private boolean recLetterQuality = false;
	private boolean slpLetterQuality = false;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = new POSPrinter();

		RequiredStateChecker.invokeThis(this, service);

		printNormalEscapeSequenceList = new ArrayList<Integer>();
		print2NormalFirstEscapeSequenceList = new ArrayList<Integer>();
		print2NormalSecondEscapeSequenceList = new ArrayList<Integer>();

		setUpLogicalNameComboBox("POSPrinter");
		// Group the Radiobuttons
		rbReceipt.setToggleGroup(group);
		rbJournal.setToggleGroup(group);
		rbSlip.setToggleGroup(group);
		rbReceipt.setSelected(true);

		/*
		 * Add ChangeListener to update EscCharacterPosititon List
		 */
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

	/* ************************************************************************
	 * ************************ Action Handler ********************************
	 * ************************************************************************
	 */

	/**
	 * Shows information of device
	 */
	@Override
	@FXML
	public void handleInfo(ActionEvent e) {
		try {
			IMapWrapper ppcm = new POSPrinterConstantMapper();
			String msg = DeviceProperties.getProperties(service, ppcm);

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
			JOptionPane.showMessageDialog(null, "Exception in Info\nException: " + jpe.getMessage(), "Exception",
					JOptionPane.ERROR_MESSAGE);
			System.err.println("Jpos exception " + jpe);
		}
	}

	/**
	 * Shows statistics of device if they are supported by the device
	 */
	@Override
	@FXML
	public void handleStatistics(ActionEvent e) {
		String[] stats = new String[] { "", "U_", "M_" };
		try {
			((POSPrinter) service).retrieveStatistics(stats);
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

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		deviceMessages.setText("");
		try {
			if (deviceEnabled.isSelected()) {
				service.setDeviceEnabled(true);
				setUpCheckboxes();
				setUpPageModeLabels();
			} else {
				service.setDeviceEnabled(false);
			}
			RequiredStateChecker.invokeThis(this, service);
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
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
			((POSPrinter) service).setAsyncMode(asyncMode.selectedProperty().getValue());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleFlagWhenIdle(ActionEvent e) {
		try {
			((POSPrinter) service).setFlagWhenIdle(flagWhenIdle.selectedProperty().getValue());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetMapMode(ActionEvent e) {
		if (mapMode.getSelectionModel().getSelectedItem() != null) {
			try {
				((POSPrinter) service).setMapMode(POSPrinterConstantMapper.getConstantNumberFromString(mapMode
						.getSelectionModel().getSelectedItem()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleRotatePrint(ActionEvent e) {
		if (rotationMode.getSelectionModel().getSelectedItem() != null) {
			try {
				((POSPrinter) service).rotatePrint(this.getSelectedStation(), POSPrinterConstantMapper
						.getConstantNumberFromString(rotationMode.getSelectionModel().getSelectedItem()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	/* General Printing */

	@FXML
	public void handleBeginInsertion(ActionEvent e) {
		try {
			((POSPrinter) service).beginInsertion(0);
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleEndInsertion(ActionEvent e) {
		try {
			((POSPrinter) service).endInsertion();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleBeginRemoval(ActionEvent e) {
		try {
			((POSPrinter) service).beginRemoval(0);
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleEndRemoval(ActionEvent e) {
		try {
			((POSPrinter) service).endRemoval();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handlePrintNormal(ActionEvent e) {
		System.out.println(addEscSequencesToPrintNormalData());
		try {
			((POSPrinter) service).printNormal(getSelectedStation(), addEscSequencesToPrintNormalData());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handlePrintImmediate(ActionEvent e) {
		try {
			((POSPrinter) service).printImmediate(getSelectedStation(), addEscSequencesToPrintNormalData());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetTopLogo(ActionEvent e) {
		try {
			((POSPrinter) service).setLogo(POSPrinterConst.PTR_L_TOP, addEscSequencesToPrintNormalData());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetBottomLogo(ActionEvent e) {
		try {
			((POSPrinter) service).setLogo(POSPrinterConst.PTR_L_BOTTOM, addEscSequencesToPrintNormalData());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleTransactionPrint(ActionEvent e) {
		try {
			((POSPrinter) service).transactionPrint(this.getSelectedStation(), POSPrinterConstantMapper
					.getConstantNumberFromString(transactionPrint.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleAddEscapeSequenceNormal(ActionEvent e) {
		printNormalEscapeSequenceList.add(printNormalData.getCaretPosition());
		String text = printNormalData.getText();
		String first = text.substring(0, printNormalData.getCaretPosition());
		String second = text.substring(printNormalData.getCaretPosition(), printNormalData.lengthProperty().getValue());

		printNormalData.setText(first + "|" + second);
		printNormalData.positionCaret(printNormalData.getLength() - 1);
	}

	@FXML
	public void handleSetLetterQuality(ActionEvent e) {
		if (rbJournal.isSelected()) {
			try {
				jrnLetterQuality = (!jrnLetterQuality);
				((POSPrinter) service).setJrnLetterQuality(jrnLetterQuality);
				deviceMessages
						.setText(deviceMessages.getText() + "\nSet Journal Letter Quality to " + jrnLetterQuality);
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}

		if (rbReceipt.isSelected()) {
			try {
				recLetterQuality = (!recLetterQuality);
				((POSPrinter) service).setRecLetterQuality(recLetterQuality);
				deviceMessages
						.setText(deviceMessages.getText() + "\nSet Receipt Letter Quality to " + recLetterQuality);
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}

		if (rbSlip.isSelected()) {
			try {
				slpLetterQuality = (!slpLetterQuality);
				((POSPrinter) service).setSlpLetterQuality(slpLetterQuality);
				deviceMessages.setText(deviceMessages.getText() + "\nSet Slip Letter Quality to " + slpLetterQuality);
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleCutPaper(ActionEvent e) {
		try {
			((POSPrinter) service).cutPaper((int) cutPaperPercentage.getValue());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleValidateData(ActionEvent e) {
		try {
			((POSPrinter) service).validateData(this.getSelectedStation(), addEscSequencesToPrintNormalData());
		} catch (JposException e1) {
			deviceMessages.setText(deviceMessages.getText() + "\n" + e1.getMessage());
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	/* Print2Normal */

	@FXML
	public void handleAddEscapeSequence2NormalFirst(ActionEvent e) {
		print2NormalFirstEscapeSequenceList.add(print2NormalFirstData.getCaretPosition());
		String text = print2NormalFirstData.getText();
		String first = text.substring(0, print2NormalFirstData.getCaretPosition());
		String second = text.substring(print2NormalFirstData.getCaretPosition(), print2NormalFirstData.lengthProperty()
				.getValue());

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
			((POSPrinter) service).printTwoNormal(POSPrinterConstantMapper
					.getConstantNumberFromString(print2NormalStation.getSelectionModel().getSelectedItem()),
					addEscSequencesToPrint2NormalDataFirst(), addEscSequencesToPrint2NormalDataSecond());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	/* Barcode */

	@FXML
	public void handlePrintBarcode(ActionEvent e) {
		if (barcodeData.getText().equals("") || barcodeHeight.getText().equals("") || barcodeWidth.getText().equals("")) {

			JOptionPane.showMessageDialog(null, "One of the parameters is not specified");
		} else {
			try {
				((POSPrinter) service).printBarCode(this.getSelectedStation(), barcodeData.getText(),
						POSPrinterConstantMapper.getConstantNumberFromString(barcodeSymbology.getSelectionModel()
								.getSelectedItem()), Integer.parseInt(barcodeHeight.getText()), Integer
								.parseInt(barcodeWidth.getText()), POSPrinterConstantMapper
								.getConstantNumberFromString(barcodeAlignment.getSelectionModel().getSelectedItem()),
						POSPrinterConstantMapper.getConstantNumberFromString(barcodeTextPosition.getSelectionModel()
								.getSelectedItem()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
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
				((POSPrinter) service).printBitmap(this.getSelectedStation(), bitmapPath.getText(),
						POSPrinterConstantMapper.getConstantNumberFromString(bitmapWidth.getSelectionModel()
								.getSelectedItem()), POSPrinterConstantMapper
								.getConstantNumberFromString(bitmapAlignment.getSelectionModel().getSelectedItem()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handlePrintMemoryBitmap(ActionEvent e) {
		if (bitmapPath.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Binary Path is not specified!");
		} else {
			try {
				((POSPrinter) service).printMemoryBitmap(this.getSelectedStation(), getBytesFromImage(bitmapPath
						.getText()), POSPrinterConstantMapper.getConstantNumberFromString(bitmapWidth
						.getSelectionModel().getSelectedItem()), getTypeFromFile(bitmapPath.getText()),
						POSPrinterConstantMapper.getConstantNumberFromString(bitmapAlignment.getSelectionModel()
								.getSelectedItem()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetBitmap(ActionEvent e) {
		if (bitmapPath.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Bitmap Path is not specified!");
		} else {
			try {
				((POSPrinter) service).setBitmap(bitmapNumber.getSelectionModel().getSelectedItem(),
						getSelectedStation(), bitmapPath.getText(), POSPrinterConstantMapper
								.getConstantNumberFromString(bitmapWidth.getSelectionModel().getSelectedItem()),
						POSPrinterConstantMapper.getConstantNumberFromString(bitmapAlignment.getSelectionModel()
								.getSelectedItem()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleRotateSpecial(ActionEvent e) {
		try {
			((POSPrinter) service).setRotateSpecial(POSPrinterConstantMapper.getConstantNumberFromString(rotationMode
					.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
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
				((POSPrinter) service).drawRuledLine(getSelectedStation(), position, POSPrinterConstantMapper
						.getConstantNumberFromString(drawLineDirection.getSelectionModel().getSelectedItem()), Integer
						.parseInt(drawLineWidth.getText()), POSPrinterConstantMapper
						.getConstantNumberFromString(drawLineStyle.getSelectionModel().getSelectedItem()),
						POSPrinterConstantMapper.getConstantNumberFromString(drawLineColor.getSelectionModel()
								.getSelectedItem()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
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
				((POSPrinter) service).setPageModeHorizontalPosition(Integer.parseInt(pageModeHorizontalPosition
						.getText()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetVerticalPosition(ActionEvent e) {
		if (pageModeVerticalPosition.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Vertical Position is not specified!");
		} else {
			try {
				((POSPrinter) service)
						.setPageModeVerticalPosition(Integer.parseInt(pageModeVerticalPosition.getText()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetPrintArea(ActionEvent e) {
		if (pageModePrintAreaStartPosX.getText().equals("") || pageModePrintAreaStartPosY.getText().equals("")
				|| pageModePrintAreaEndPosX.getText().equals("") || pageModePrintAreaEndPosX.getText().equals("")) {

			JOptionPane.showMessageDialog(null, "One of the Parameter is not specified!");
		} else {
			String area = pageModePrintAreaStartPosX.getText() + "," + pageModePrintAreaStartPosY.getText() + ","
					+ pageModePrintAreaEndPosX.getText() + "," + pageModePrintAreaEndPosX.getText();
			try {
				((POSPrinter) service).setPageModePrintArea(area);
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetPrintDirection(ActionEvent e) {
		try {
			((POSPrinter) service).setPageModePrintDirection(POSPrinterConstantMapper
					.getConstantNumberFromString(pageModePrintDirection.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetPageModeStation(ActionEvent e) {
		try {
			((POSPrinter) service).setPageModeStation(POSPrinterConstantMapper
					.getConstantNumberFromString(pageModePrintStation.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleClearPrintArea(ActionEvent e) {
		try {
			((POSPrinter) service).clearPrintArea();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handlePageModePrint(ActionEvent e) {
		try {
			((POSPrinter) service).pageModePrint(POSPrinterConstantMapper.getConstantNumberFromString(pageModePrint
					.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	/* Misc */

	@FXML
	public void handleChangePrintSide(ActionEvent e) {
		try {
			((POSPrinter) service).changePrintSide(POSPrinterConstantMapper.getConstantNumberFromString(printSide
					.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleMarkFeed(ActionEvent e) {
		try {
			((POSPrinter) service).markFeed(POSPrinterConstantMapper.getConstantNumberFromString(markFeed
					.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetCharacterSet(ActionEvent e) {
		try {
			((POSPrinter) service).setCharacterSet(characterSet.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetMapCharacterSet(ActionEvent e) {
		try {
			((POSPrinter) service).setMapCharacterSet(mapCharacterSet.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetLineSpacing(ActionEvent e) {
		if (lineSpacing.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Line Spacing is not specified!");
		} else {

			if (rbJournal.isSelected()) {
				try {
					((POSPrinter) service).setJrnLineSpacing(Integer.parseInt(lineSpacing.getText()));
				} catch (JposException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}
			}

			if (rbReceipt.isSelected()) {
				try {
					((POSPrinter) service).setRecLineSpacing(Integer.parseInt(lineSpacing.getText()));
				} catch (JposException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}
			}

			if (rbSlip.isSelected()) {
				try {
					((POSPrinter) service).setSlpLineSpacing(Integer.parseInt(lineSpacing.getText()));
				} catch (JposException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
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
					((POSPrinter) service).setJrnLineChars(Integer.parseInt(lineChars.getText()));
				} catch (JposException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}
			}

			if (rbReceipt.isSelected()) {
				try {
					((POSPrinter) service).setRecLineChars(Integer.parseInt(lineChars.getText()));
				} catch (JposException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}
			}

			if (rbSlip.isSelected()) {
				try {
					((POSPrinter) service).setSlpLineChars(Integer.parseInt(lineChars.getText()));
				} catch (JposException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
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
					((POSPrinter) service).setJrnLineHeight(Integer.parseInt(lineHeight.getText()));
				} catch (JposException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}
			}

			if (rbReceipt.isSelected()) {
				try {
					((POSPrinter) service).setRecLineHeight(Integer.parseInt(lineHeight.getText()));
				} catch (JposException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}
			}

			if (rbSlip.isSelected()) {
				try {
					((POSPrinter) service).setSlpLineHeight(Integer.parseInt(lineHeight.getText()));
				} catch (JposException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
					e1.printStackTrace();
				}
			}
		}
	}

	@FXML
	public void handleSetCurrentCartridge(ActionEvent e) {
		if (rbJournal.isSelected()) {
			try {
				((POSPrinter) service).setJrnCurrentCartridge(POSPrinterConstantMapper
						.getConstantNumberFromString(currentCartridge.getSelectionModel().getSelectedItem()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}

		if (rbReceipt.isSelected()) {
			try {
				((POSPrinter) service).setRecCurrentCartridge(POSPrinterConstantMapper
						.getConstantNumberFromString(currentCartridge.getSelectionModel().getSelectedItem()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}

		if (rbSlip.isSelected()) {
			try {
				((POSPrinter) service).setSlpCurrentCartridge(POSPrinterConstantMapper
						.getConstantNumberFromString(currentCartridge.getSelectionModel().getSelectedItem()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
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
	
	
	@FXML
	public void handleBrowseDirectIOData(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose DirectIOData");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			directIO_data.setText(f.getAbsolutePath());
		}
	}
	
	@FXML
	public void handleBrowseDirectIOObject(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose DirectIOObject");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			directIO_object.setText(f.getAbsolutePath());
		}
	}
	
	@FXML
	public void handleDirectIO(ActionEvent e) {
		if (directIO_command.getText().isEmpty() || directIO_data.getText().isEmpty()
				|| directIO_object.getText().isEmpty()) {

			JOptionPane.showMessageDialog(null, "One of the Parameter is not specified!");
		} else {
			try {
				//Reads content from File
				byte[] dataArrByte = readBytesFromFile(directIO_data.getText());
				int[] dataArrInt = new int[dataArrByte.length];
				for(int i = 0; i < dataArrByte.length; i++){
					dataArrInt[i] = dataArrByte[i];
				}
				
				byte[] objectArr = readBytesFromFile(directIO_object.getText());
				
				//Execute DirectIO
				((POSPrinter) service).directIO(Integer.parseInt(directIO_command.getText()), dataArrInt, objectArr);
				
				//Write changes to the files
				dataArrByte = new byte[dataArrInt.length];
				for(int i = 0; i < dataArrByte.length; i++){
					dataArrByte[i] = (byte) dataArrInt[i];
				}
				if(dataArrByte != null){
					writeBytesToFile(dataArrByte, directIO_data.getText());
				}
				
				if(objectArr != null){
					writeBytesToFile(objectArr, directIO_object.getText());
				}
				
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}
	

	/**
	 * This Method gets a Byte Array from a File to print it with
	 * printMemoryBitmap
	 * 
	 * @param path
	 *            to Binary File
	 * @return byte[] containing the data from the File
	 */
	private byte[] getBytesFromImage(String path) {
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
		BufferedImage newBufferedImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(),
				BufferedImage.TYPE_BYTE_BINARY);

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
	
	/**
	 * Read the given binary file, and return its contents as a byte array.
	 * 
	 */
	private static byte[] readBytesFromFile(String aInputFileName) {
		File file = new File(aInputFileName);
		byte[] result = new byte[(int) file.length()];
		try {
			InputStream input = null;
			try {
				int totalBytesRead = 0;
				input = new BufferedInputStream(new FileInputStream(file));
				while (totalBytesRead < result.length) {
					int bytesRemaining = result.length - totalBytesRead;
					// input.read() returns -1, 0, or more :
					int bytesRead = input.read(result, totalBytesRead, bytesRemaining);
					if (bytesRead > 0) {
						totalBytesRead = totalBytesRead + bytesRead;
					}
				}
			} finally {
				input.close();
			}
		} catch (FileNotFoundException ex) {

			JOptionPane.showMessageDialog(null, ex.getMessage());
			ex.printStackTrace();
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			ex.printStackTrace();

		}
		return result;
	}

	/**
	 * Write a byte array to the given file. Writing binary data is
	 * significantly simpler than reading it.
	 */
	private static void writeBytesToFile(byte[] aInput, String aOutputFileName) {
		try {
			OutputStream output = null;
			try {
				output = new BufferedOutputStream(new FileOutputStream(aOutputFileName));
				output.write(aInput);
			} finally {
				output.close();
			}
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			ex.printStackTrace();
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			ex.printStackTrace();
		}
	}


	/**
	 * This Method returns the Type of the selected file and returns it, if it
	 * is a valid type. Otherwise an Exception is thrown
	 * 
	 * @param path
	 *            to File
	 * @return
	 * @throws IOException
	 */
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

	/**
	 * Sets the CharacterSetComboBox Values corresponding to the allowed Values
	 * for this device.
	 */
	private void setUpCharacterSet() {
		characterSet.getItems().clear();
		try {
			for (int i = 0; i < ((POSPrinter) service).getCharacterSetList().split(",").length; i++) {
				characterSet.getItems().add(
						Integer.parseInt((((POSPrinter) service).getCharacterSetList().split(","))[i]));
				if (i == 0) {
					characterSet.setValue(Integer
							.parseInt((((POSPrinter) service).getCharacterSetList().split(","))[i]));
				}
			}

		} catch (JposException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error occured when getting the CharacterSetList", "Error occured!",
					JOptionPane.WARNING_MESSAGE);
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

	private void setUpCartridgeNotify() {
		cartridgeNotify.getItems().clear();
		cartridgeNotify.getItems().add(POSPrinterConstantMapper.PTR_CN_ENABLED.getConstant());
		cartridgeNotify.getItems().add(POSPrinterConstantMapper.PTR_CN_ENABLED.getConstant());
		cartridgeNotify.setValue(POSPrinterConstantMapper.PTR_CN_ENABLED.getConstant());
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
		setUpCartridgeNotify();
	}

	/**
	 * Gets the Constant for the currently selected station
	 * 
	 * @return
	 * @throws JposException
	 */
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

	/**
	 * Initialize the PageMode Properties PageModeArea and PageModeDescriptor
	 */
	private void setUpPageModeLabels() {
		try {
			pageModeArea.setText(((POSPrinter) service).getPageModeArea());
			pageModeDescriptor.setText(POSPrinterConstantMapper
					.getPageModeDescriptorConstantName(((POSPrinter) service).getPageModeDescriptor()));
		} catch (JposException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Adds the ESC-Character to the printNormal-String and returns it.
	 * Necessary because TextField deletes all ESC-Characters on a change
	 * 
	 * @return the Correct String containing the ESC-characters
	 */
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
	 * Updates the Positions in the EscapeCharacterList of PrintNormal if
	 * something was added to printNormalData
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
	 * Updates the Positions in the EscapeCharacterList of PrintNormal if
	 * something was deleted from printNormalData
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
	/**
	 * Adds the ESC-Character to the print2NormalFirst-String and returns it.
	 * Necessary because TextField deletes all ESC-Characters on a change
	 * 
	 * @return the Correct String containing the ESC-characters
	 */
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
	 * Updates the Positions in the EscapeCharacterList of Print2NormalFirst if
	 * something was added to print2NormalDataFirst
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
	 * Updates the Positions in the EscapeCharacterList of Print2NormalFirst if
	 * something was deleted from print2NormalDataFirst
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

	/**
	 * Adds the ESC-Character to the print2NormalSecond-String and returns it.
	 * Necessary because TextField deletes all ESC-Characters on a change
	 * 
	 * @return the Correct String containing the ESC-characters
	 */
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
	 * Updates the Positions in the EscapeCharacterList of Print2NormalSecond if
	 * something was added to print2NormalDataSecond
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
	 * Updates the Positions in the EscapeCharacterList of Print2NormalSecond if
	 * something was deleted from print2NormalDataSecond
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

}
