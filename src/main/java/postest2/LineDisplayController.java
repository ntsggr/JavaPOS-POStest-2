package postest2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jpos.JposException;
import jpos.LineDisplay;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class LineDisplayController extends CommonController implements Initializable {

	@FXML
	@RequiredState(JposState.ENABLED)
	public TabPane functionTab;

	// Display Text
	@FXML
	public ComboBox<Integer> row;
	@FXML
	public ComboBox<Integer> column;
	@FXML
	public ComboBox<String> attribute;
	@FXML
	public TextArea displayText;

	@FXML
	public TextField blinkRate;
	@FXML
	public TextField intercharacterWait;

	// Misc
	@FXML
	public ComboBox<Integer> descriptors;
	@FXML
	public ComboBox<String> descriptor_attribute;
	@FXML
	public ComboBox<String> scrollText_direction;
	@FXML
	public TextField scrollText_Units;
	@FXML
	public TextField readCharacterField;
	@FXML
	public TextField glypeCode;
	@FXML
	public TextField glyphBinaryPath;
	@FXML
	public ComboBox<Integer> characterSet;
	@FXML
	public Slider deviceBrightness;

	// Window
	@FXML
	public TextField viewportRow;
	@FXML
	public TextField viewportColumn;
	@FXML
	public TextField viewportHeight;
	@FXML
	public TextField viewportWidth;
	@FXML
	public TextField windowHeight;
	@FXML
	public TextField windowWidth;
	@FXML
	public ListView<String> openWindowsListView;

	// Display Marquee
	@FXML
	public TextField marqueeRepeatWait;
	@FXML
	public TextField marqueeUnitWait;
	@FXML
	public ComboBox<String> marqueeType;
	@FXML
	public ComboBox<String> marqueeFormat;

	// Display Bitmap
	@FXML
	public ComboBox<String> alignmentX;
	@FXML
	public ComboBox<String> alignmentY;
	@FXML
	public ComboBox<String> bitmapWidth;
	@FXML
	public TextField bitmapPath;

	// Cursor Control
	@FXML
	public TextField cursorColumn;
	@FXML
	public TextField cursorRow;

	@FXML
	public ComboBox<Boolean> mapCharacterSet;
	@FXML
	public ComboBox<String> cursorType;
	@FXML
	public ComboBox<Boolean> cursorUpdate;
	
	// Screen Mode
	@FXML
	@RequiredState(JposState.CLAIMED)
	public TabPane notEnabledTab;

	@FXML
	public ComboBox<String> screenMode;

	// OpenWindow-Counter
	private int currentWindow = 0;

	// List for ListView openWindowsListView
	private ObservableList<String> windowList = FXCollections.observableArrayList();

	// Holds position of ESC-Characters.
	// Need because Textarea delete ESC everytime it changes
	private ArrayList<Integer> displayTextEscapeSequenceList;

	// Escape-Character
	final char ESC = (char) 0x1B;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpLogicalNameComboBox("LineDisplay");
		service = new LineDisplay();
		RequiredStateChecker.invokeThis(this, service);
		displayTextEscapeSequenceList = new ArrayList<Integer>();

		/*
		 * Add ChangeListener to update EscCharacterPosititon List
		 */
		displayText.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {

				if (arg2.length() > arg1.length()) {
					updateInsertsEscSequencesToDisplayTextData((arg2.length() - arg1.length()));

				} else {
					updateDeletesEscSequencesToDisplayTextData(arg1.length() - arg2.length());
				}
			}
		});

	}

	/* ************************************************************************
	 * ************************ Action Handler ********************************
	 * ************************************************************************
	 */

	/**
	 * Shows statistics of device if they are supported by the device
	 */
	@Override
	@FXML
	public void handleInfo(ActionEvent e) {
		try {
			String msg = DeviceProperties.getProperties(service, null);
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
			((LineDisplay) service).retrieveStatistics(stats);
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
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (JposException jpe) {
			jpe.printStackTrace();
			JOptionPane.showMessageDialog(null, "Statistics are not supported!", "Statistics",
					JOptionPane.ERROR_MESSAGE);
		}

		statistics = "";
	}

	/**
	 * Need this to set the ComboBox for Screen mode. (only available if device
	 * is claimed but not enabled)
	 */
	@FXML
	@Override
	public void handleClaim(ActionEvent e) {
		setUpScreenMode();
		deviceEnabled.setSelected(true);
		super.handleClaim(e);
	}

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((LineDisplay) service).setDeviceEnabled(true);
				setUpComboBoxes();

				windowList.clear();

				windowList.add("0");
				openWindowsListView.setItems(windowList);

			} else {
				((LineDisplay) service).setDeviceEnabled(false);
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
		setUpScreenMode();
		deviceEnabled.setSelected(true);
		handleDeviceEnable(e);
	}

	@FXML
	public void handleDisplayTextAt(ActionEvent e) {
		if (row.getSelectionModel().getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "Row is not selected!", "Logical name is empty",
					JOptionPane.WARNING_MESSAGE);
		}
		try {
			((LineDisplay) service).displayTextAt(row.getSelectionModel().getSelectedItem(), column.getSelectionModel()
					.getSelectedItem(), addEscSequencesToDisplayTextData(), LineDisplayConstantMapper
					.getConstantNumberFromString(attribute.getSelectionModel().getSelectedItem()));
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (JposException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

	@FXML
	public void handleDisplayText(ActionEvent e) {
		try {
			((LineDisplay) service).displayText(addEscSequencesToDisplayTextData(), LineDisplayConstantMapper
					.getConstantNumberFromString(attribute.getSelectionModel().getSelectedItem()));
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (JposException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleClearText(ActionEvent e) {
		try {
			((LineDisplay) service).clearText();
		} catch (JposException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleMoveCursor(ActionEvent e) {
		try {
			((LineDisplay) service).setCursorColumn(column.getSelectionModel().getSelectedItem());
			((LineDisplay) service).setCursorRow(row.getSelectionModel().getSelectedItem());
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (JposException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

	@FXML
	public void handleSetBlinkRate(ActionEvent e) {
		if (blinkRate.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Param blinkRate is not set!", "Invalid Parameter",
					JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				((LineDisplay) service).setBlinkRate(Integer.parseInt(blinkRate.getText()));
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (JposException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleSetICharWait(ActionEvent e) {
		if (intercharacterWait.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Param ICharWait is not set!", "Invalid Parameter",
					JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				((LineDisplay) service).setInterCharacterWait(Integer.parseInt(intercharacterWait.getText()));
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (JposException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleAddEscapeSequence(ActionEvent e) {
		displayTextEscapeSequenceList.add(displayText.getCaretPosition());
		String text = displayText.getText();
		String first = text.substring(0, displayText.getCaretPosition());
		String second = text.substring(displayText.getCaretPosition(), displayText.lengthProperty().getValue());

		displayText.setText(first + "|" + second);
		displayText.positionCaret(displayText.getLength());

		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleAddWindow(ActionEvent e) {
		if (viewportRow.getText().equals("") || viewportColumn.getText().equals("")
				|| viewportHeight.getText().equals("") || viewportWidth.getText().equals("")
				|| windowHeight.getText().equals("") || windowWidth.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "One of the params is not set!", "Invalid Parameter",
					JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				FXCollections.sort(windowList);
				int num = 0;
				for (String s : windowList) {
					if (s.equals("" + num)) {
						num++;
					}
					if (num == 4) {
						JOptionPane.showMessageDialog(null, "Too many open Windows!", "Invalid Parameter",
								JOptionPane.WARNING_MESSAGE);
						return;
					}
				}

				windowList.add("" + num);

				FXCollections.sort(windowList);
				((LineDisplay) service).createWindow(Integer.parseInt(viewportRow.getText()), Integer
						.parseInt(viewportColumn.getText()), Integer.parseInt(viewportHeight.getText()), Integer
						.parseInt(viewportWidth.getText()), Integer.parseInt(windowHeight.getText()), Integer
						.parseInt(windowWidth.getText()));

			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (JposException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}

		}
	}

	@FXML
	public void handleDeleteWindow(ActionEvent e) {
		try {
			((LineDisplay) service).destroyWindow();
			windowList.remove("" + currentWindow);
			FXCollections.sort(windowList);
		} catch (JposException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleRefreshWindow(ActionEvent e) {
		if (openWindowsListView.getSelectionModel().getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "Choose a valid window!", "Invalid Parameter",
					JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				((LineDisplay) service).refreshWindow(Integer.parseInt(openWindowsListView.getSelectionModel()
						.getSelectedItem()));
				currentWindow = Integer.parseInt(openWindowsListView.getSelectionModel().getSelectedItem());
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (JposException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleScrollText(ActionEvent e) {
		if (scrollText_direction.getSelectionModel().getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Choose a valid scroll direction!", "Invalid Parameter",
					JOptionPane.WARNING_MESSAGE);
		} else if (scrollText_Units.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Choose a valid unit!", "Invalid Parameter",
					JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				((LineDisplay) service).scrollText(LineDisplayConstantMapper
						.getConstantNumberFromString(scrollText_direction.getSelectionModel().getSelectedItem()),
						Integer.parseInt(scrollText_Units.getText()));
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (JposException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleSetDeviceBrightness(ActionEvent e) {
		try {
			((LineDisplay) service).setDeviceBrightness((int) (deviceBrightness.getValue()));
		} catch (JposException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

	@FXML
	public void handleSetDescriptor(ActionEvent e) {
		try {
			((LineDisplay) service).setDescriptor(descriptors.getSelectionModel().getSelectedItem(),
					LineDisplayConstantMapper.getConstantNumberFromString(descriptor_attribute.getSelectionModel()
							.getSelectedItem()));
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (JposException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleClearAllDescriptors(ActionEvent e) {
		try {
			((LineDisplay) service).clearDescriptors();
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (JposException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleReadCharacter(ActionEvent e) {
		int[] help = new int[1];
		try {
			((LineDisplay) service).readCharacterAtCursor(help);
		} catch (JposException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		readCharacterField.setText("" + help[0]);

	}

	@FXML
	public void handleDefineGlyph(ActionEvent e) throws JposException {
		if (glyphBinaryPath.getText() == "") {
			JOptionPane.showMessageDialog(null, "Choose a valid glyph path", "Invalid Parameter",
					JOptionPane.WARNING_MESSAGE);
		} else if (glypeCode.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Choose a valid Glyph Code", "Invalid Parameter",
					JOptionPane.WARNING_MESSAGE);
		} else {
			byte[] bytes = getBytesFromFile(glyphBinaryPath.getText());
			((LineDisplay) service).defineGlyph(Integer.parseInt(glypeCode.getText()), bytes);
		}
	}

	@FXML
	public void handleSelectGlyphBinary(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Glyph Binary");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			glyphBinaryPath.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleSetCharacterSet(ActionEvent e) {
		try {
			((LineDisplay) service).setCharacterSet(characterSet.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

	@FXML
	public void handleSetMapCharacterSet(ActionEvent e) {
		try {
			((LineDisplay) service).setMapCharacterSet(mapCharacterSet.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

	@FXML
	public void handleSetScreenMode(ActionEvent e) {
		try {
			// Add 1 to the Index, because the index starts with 0 but the first
			// Item in the List should be number 1
			((LineDisplay) service).setScreenMode(screenMode.getSelectionModel().getSelectedIndex() + 1);
		} catch (JposException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleSetMarqueeType(ActionEvent e) {
		try {
			((LineDisplay) service).setMarqueeType(LineDisplayConstantMapper.getConstantNumberFromString(marqueeType
					.getSelectionModel().getSelectedItem()));
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (JposException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

	@FXML
	public void handleSetMarqueeFormat(ActionEvent e) {
		try {
			((LineDisplay) service).setMarqueeFormat(LineDisplayConstantMapper
					.getConstantNumberFromString(marqueeFormat.getSelectionModel().getSelectedItem()));

		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (JposException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleSetMarqueeRepeatWait(ActionEvent e) {
		if (marqueeRepeatWait.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Param marqueeRepeatWait is false", "Invalid Parameter!",
					JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				((LineDisplay) service).setMarqueeRepeatWait(Integer.parseInt(marqueeRepeatWait.getText()));
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (JposException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleSetMarqueeUnitWait(ActionEvent e) {
		if (marqueeUnitWait.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Param marqueeUnitWait is false", "Invalid Parameter!",
					JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				((LineDisplay) service).setMarqueeUnitWait(Integer.parseInt(marqueeUnitWait.getText()));
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (JposException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleChooseBitmap(ActionEvent e) {

		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Bitmap Path");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			bitmapPath.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleDisplayBitmap(ActionEvent e) {
		if (bitmapPath.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Param bitmapPath is not set");
		} else {
			try {
				((LineDisplay) service).displayBitmap(bitmapPath.getText(), LineDisplayConstantMapper
						.getConstantNumberFromString(bitmapWidth.getSelectionModel().getSelectedItem()),
						LineDisplayConstantMapper.getConstantNumberFromString(alignmentX.getSelectionModel()
								.getSelectedItem()), LineDisplayConstantMapper.getConstantNumberFromString(alignmentY
								.getSelectionModel().getSelectedItem()));
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (JposException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleSetCursorColumn(ActionEvent e) {
		if (cursorColumn.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				((LineDisplay) service).setCursorColumn(Integer.parseInt(cursorColumn.getText()));
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (JposException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleSetCursorRow(ActionEvent e) {
		if (cursorRow.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				((LineDisplay) service).setCursorRow(Integer.parseInt(cursorRow.getText()));
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (JposException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}

	@FXML
	public void handleSetCursorType(ActionEvent e) {
		try {
			((LineDisplay) service).setCursorType(LineDisplayConstantMapper.getConstantNumberFromString(cursorType
					.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

	@FXML
	public void handleSetCursorUpdate(ActionEvent e) {
		try {
			((LineDisplay) service).setCursorUpdate(cursorUpdate.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	/* ************************************************************************
	 * ************************ Set all ComboBox Values ***********************
	 * ************************************************************************
	 */

	private void setUpComboBoxes() {
		setUpAttribute();
		setUpRow();
		setUpColumns();
		setUpDescriptors();
		setUpDescriptorAttribute();
		setUpScrollTextDirection();
		setUpCharacterSet();
		setUpMarqueeType();
		setUpMarqueeFormat();
		setUpBitmapWidth();
		setUpAlignmentX();
		setUpAlignmentY();
		setUpCursorType();
		setUpCursorUpdate();
	}

	private void setUpAttribute() {
		attribute.getItems().clear();
		attribute.getItems().add(LineDisplayConstantMapper.DISP_DT_NORMAL.getContantNumber(),
				LineDisplayConstantMapper.DISP_DT_NORMAL.getConstant());
		attribute.getItems().add(LineDisplayConstantMapper.DISP_DT_BLINK.getContantNumber(),
				LineDisplayConstantMapper.DISP_DT_BLINK.getConstant());
		attribute.getItems().add(LineDisplayConstantMapper.DISP_DT_REVERSE.getContantNumber(),
				LineDisplayConstantMapper.DISP_DT_REVERSE.getConstant());
		attribute.getItems().add(LineDisplayConstantMapper.DISP_DT_BLINK_REVERSE.getContantNumber(),
				LineDisplayConstantMapper.DISP_DT_BLINK_REVERSE.getConstant());
		attribute.setValue(LineDisplayConstantMapper.DISP_DT_NORMAL.getConstant());

	}

	private void setUpRow() {
		row.getItems().clear();
		try {
			for (int i = 0; i < ((LineDisplay) service).getRows(); i++) {
				row.getItems().add(i);
			}
		} catch (JposException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error occured when getting Rows", "Error occured!",
					JOptionPane.WARNING_MESSAGE);
		}
		row.setValue(0);
	}

	private void setUpColumns() {
		column.getItems().clear();
		try {
			for (int i = 0; i < ((LineDisplay) service).getColumns(); i++) {
				column.getItems().add(i);
			}
		} catch (JposException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error occured when getting Columns", "Error occured!",
					JOptionPane.WARNING_MESSAGE);
		}
		column.setValue(0);
	}

	private void setUpDescriptors() {
		descriptors.getItems().clear();
		try {
			descriptors.getItems().add(((LineDisplay) service).getDeviceDescriptors());
			descriptors.setValue(((LineDisplay) service).getDeviceDescriptors());
		} catch (JposException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error occured when getting Descriptors", "Error occured!",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void setUpDescriptorAttribute() {
		descriptor_attribute.getItems().clear();
		descriptor_attribute.getItems().add(LineDisplayConstantMapper.DISP_SD_OFF.getConstant());
		descriptor_attribute.getItems().add(LineDisplayConstantMapper.DISP_SD_ON.getConstant());
		descriptor_attribute.getItems().add(LineDisplayConstantMapper.DISP_SD_BLINK.getConstant());
		descriptor_attribute.setValue(LineDisplayConstantMapper.DISP_SD_OFF.getConstant());
	}

	private void setUpScrollTextDirection() {
		scrollText_direction.getItems().clear();
		scrollText_direction.getItems().add(LineDisplayConstantMapper.DISP_ST_UP.getConstant());
		scrollText_direction.getItems().add(LineDisplayConstantMapper.DISP_ST_DOWN.getConstant());
		scrollText_direction.getItems().add(LineDisplayConstantMapper.DISP_ST_LEFT.getConstant());
		scrollText_direction.getItems().add(LineDisplayConstantMapper.DISP_ST_RIGHT.getConstant());
		scrollText_direction.setValue(LineDisplayConstantMapper.DISP_ST_UP.getConstant());

	}

	private void setUpCharacterSet() {
		characterSet.getItems().clear();
		try {
			for (int i = 0; i < ((LineDisplay) service).getCharacterSetList().split(",").length; i++) {
				characterSet.getItems().add(
						Integer.parseInt((((LineDisplay) service).getCharacterSetList().split(","))[i]));
				if (i == 0) {
					characterSet.setValue(Integer
							.parseInt((((LineDisplay) service).getCharacterSetList().split(","))[i]));
				}
			}

		} catch (JposException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error occured when getting the CharacterSetList", "Error occured!",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void setUpScreenMode() {
		screenMode.getItems().clear();
		try {
			for (int i = 0; i < ((LineDisplay) service).getScreenModeList().split(",").length; i++) {
				screenMode.getItems().add((((LineDisplay) service).getScreenModeList().split(","))[i]);
				if (i == 0) {
					screenMode.setValue((((LineDisplay) service).getScreenModeList().split(","))[i]);
				}
			}
		} catch (JposException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error occured when getting the ScreenModeList", "Error occured!",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void setUpMarqueeType() {
		marqueeType.getItems().clear();
		marqueeType.getItems().add(LineDisplayConstantMapper.DISP_MT_NONE.getConstant());
		marqueeType.getItems().add(LineDisplayConstantMapper.DISP_MT_UP.getConstant());
		marqueeType.getItems().add(LineDisplayConstantMapper.DISP_MT_DOWN.getConstant());
		marqueeType.getItems().add(LineDisplayConstantMapper.DISP_MT_LEFT.getConstant());
		marqueeType.getItems().add(LineDisplayConstantMapper.DISP_MT_RIGHT.getConstant());
		marqueeType.getItems().add(LineDisplayConstantMapper.DISP_MT_INIT.getConstant());
		marqueeType.setValue(LineDisplayConstantMapper.DISP_MT_NONE.getConstant());
	}

	private void setUpMarqueeFormat() {
		marqueeFormat.getItems().clear();
		marqueeFormat.getItems().add(LineDisplayConstantMapper.DISP_MF_WALK.getConstant());
		marqueeFormat.getItems().add(LineDisplayConstantMapper.DISP_MF_PLACE.getConstant());
		marqueeFormat.setValue(LineDisplayConstantMapper.DISP_MF_WALK.getConstant());
	}

	private void setUpBitmapWidth() {
		bitmapWidth.getItems().clear();
		bitmapWidth.getItems().add(LineDisplayConstantMapper.DISP_BM_ASIS.getConstant());

		bitmapWidth.setValue(LineDisplayConstantMapper.DISP_BM_ASIS.getConstant());
	}

	private void setUpAlignmentX() {
		alignmentX.getItems().clear();
		alignmentX.getItems().add(LineDisplayConstantMapper.DISP_BM_LEFT.getConstant());
		alignmentX.getItems().add(LineDisplayConstantMapper.DISP_BM_CENTER.getConstant());
		alignmentX.getItems().add(LineDisplayConstantMapper.DISP_BM_RIGHT.getConstant());

		alignmentX.setValue(LineDisplayConstantMapper.DISP_BM_RIGHT.getConstant());
	}

	private void setUpAlignmentY() {
		alignmentY.getItems().clear();
		alignmentY.getItems().add(LineDisplayConstantMapper.DISP_BM_TOP.getConstant());
		alignmentY.getItems().add(LineDisplayConstantMapper.DISP_BM_CENTER.getConstant());
		alignmentY.getItems().add(LineDisplayConstantMapper.DISP_BM_BOTTOM.getConstant());

		alignmentY.setValue(LineDisplayConstantMapper.DISP_BM_BOTTOM.getConstant());
	}

	private void setUpCursorType() {
		cursorType.getItems().clear();
		cursorType.getItems().add(LineDisplayConstantMapper.DISP_CT_BLINK.getConstant());
		cursorType.getItems().add(LineDisplayConstantMapper.DISP_CT_BLOCK.getConstant());
		cursorType.getItems().add(LineDisplayConstantMapper.DISP_CT_FIXED.getConstant());
		cursorType.getItems().add(LineDisplayConstantMapper.DISP_CT_HALFBLOCK.getConstant());
		cursorType.getItems().add(LineDisplayConstantMapper.DISP_CT_NONE.getConstant());
		cursorType.getItems().add(LineDisplayConstantMapper.DISP_CT_OTHER.getConstant());
		cursorType.getItems().add(LineDisplayConstantMapper.DISP_CT_REVERSE.getConstant());
		cursorType.getItems().add(LineDisplayConstantMapper.DISP_CT_UNDERLINE.getConstant());
		cursorType.setValue(LineDisplayConstantMapper.DISP_CT_NONE.getConstant());
	}

	private void setUpCursorUpdate() {
		cursorUpdate.getItems().clear();
		cursorUpdate.getItems().add(true);
		cursorUpdate.getItems().add(false);
		cursorUpdate.setValue(true);
	}

	/**
	 * This Method gets a Byte Array from a File to print it with
	 * displayMemoryBitmap
	 * 
	 * @param path
	 *            to Binary File
	 * @return byte[] containing the data from the File
	 */
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
	 * Adds the ESC-Character to the printNormal-String and returns it.
	 * Necessary because TextField deletes all ESC-Characters on a change
	 * 
	 * @return the Correct String containing the ESC-characters
	 */
	private String addEscSequencesToDisplayTextData() {
		String ret = displayText.getText();
		synchronized (displayTextEscapeSequenceList) {
			if (!displayTextEscapeSequenceList.isEmpty()) {
				int i = 0;
				for (int num : displayTextEscapeSequenceList) {
					if (num < ret.length()) {
						num += i;
						ret = ret.substring(0, num) + ESC + ret.substring(num, ret.length());
						i++;
					}
				}
			}
		}
		return ret;
	}

	/**
	 * Updates the Positions in the EscapeCharacterList of PrintNormal if
	 * something was added to printNormalData
	 */
	private void updateInsertsEscSequencesToDisplayTextData(int diff) {
		int cursorPos = displayText.getCaretPosition();
		ListIterator<Integer> pos = displayTextEscapeSequenceList.listIterator();

		while (pos.hasNext()) {
			int i = pos.next();
			if (i > cursorPos) {
				pos.remove();
				pos.add((i + diff));
			}
			if (displayTextEscapeSequenceList.isEmpty()) {
				break;
			}
		}

	}

	/**
	 * Updates the Positions in the EscapeCharacterList of PrintNormal if
	 * something was deleted from printNormalData
	 */
	private void updateDeletesEscSequencesToDisplayTextData(int diff) {
		// Compare Length with ArrayList pos
		// Decrement Index -1 > Cursor Pos
		int cursorPos = displayText.getCaretPosition();

		ListIterator<Integer> pos = displayTextEscapeSequenceList.listIterator();
		while (pos.hasNext()) {
			int i = pos.next();
			if (i > displayText.getText().length()) {
				pos.remove();
				if (displayTextEscapeSequenceList.isEmpty()) {
					break;
				}
				continue;
			}
			if (i == cursorPos - 1) {
				pos.remove();
				if (displayTextEscapeSequenceList.isEmpty()) {
					break;
				}
			}
			if (i > cursorPos) {
				pos.remove();
				pos.add(i - diff);
			}
			if (displayTextEscapeSequenceList.isEmpty()) {
				break;
			}
		}
	}

}
