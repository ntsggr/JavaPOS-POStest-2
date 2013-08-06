/*
 * Copyright 2013 NTS New Technology Systems GmbH. All Rights reserved.
 * NTS PROPRIETARY/CONFIDENTIAL. Use is subject to NTS License Agreement.
 * Address: Doernbacher Strasse 126, A-4073 Wilhering, Austria
 * Homepage: www.ntswincash.com
 */
package postest2;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import jpos.JposException;
import jpos.LineDisplay;
import jpos.profile.JposDevCats;

public class LineDisplayController implements Initializable {

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
	private Button buttonStatistics;
	@FXML
	private Button buttonClose;
	@FXML
	private Button buttonFirmware;
	@FXML
	private Label statusLabel;
	@FXML
	private CheckBox freezeEvents;

	@FXML
	private TabPane functionTab;

	// Display Text
	@FXML
	private ComboBox<Integer> row;
	@FXML
	private ComboBox<Integer> column;
	@FXML
	private ComboBox<String> attribute;
	@FXML
	private TextField displayText;

	@FXML
	private TextField blinkRate;
	@FXML
	private TextField intercharacterWait;

	// Misc
	@FXML
	private ComboBox<Integer> descriptors;
	@FXML
	private ComboBox<String> descriptor_attribute;
	@FXML
	private ComboBox<String> scrollText_direction;
	@FXML
	private TextField scrollText_Units;
	@FXML
	private TextField readCharacterField;
	@FXML
	private TextField glypeCode;
	@FXML
	private TextField glyphBinaryPath;
	@FXML
	private ComboBox<Integer> characterSet;
	@FXML
	private Slider deviceBrightness;

	// Window
	@FXML
	private TextField viewportRow;
	@FXML
	private TextField viewportColumn;
	@FXML
	private TextField viewportHeight;
	@FXML
	private TextField viewportWidth;
	@FXML
	private TextField windowHeight;
	@FXML
	private TextField windowWidth;
	@FXML
	private ListView<String> openWindowsListView;

	// Display Marquee
	@FXML
	private TextField marqueeRepeatWait;
	@FXML
	private TextField marqueeUnitWait;
	@FXML
	private ComboBox<String> marqueeType;
	@FXML
	private ComboBox<String> marqueeFormat;

	// Display Bitmap
	@FXML
	private ComboBox<String> alignmentX;
	@FXML
	private ComboBox<String> alignmentY;
	@FXML
	private ComboBox<String> bitmapWidth;
	@FXML
	private TextField bitmapPath;
	
	//Screen Mode
	@FXML
	private TabPane setScreenModeTab;
	@FXML
	private ComboBox<String> screenMode;

	private int currentWindow = 0;
	
	private LineDisplay display;
	private ObservableList<String> windowList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpLogicalNameComboBox();
		setUpAttribute();
		display = new LineDisplay();
		//setScreenModeTab.setDisable(true);
	}

	private void setUpLogicalNameComboBox() {
		logicalName.setItems(LogicalNameGetter.getLogicalNamesByCategory(JposDevCats.LINEDISPLAY_DEVCAT
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
				display.open(logicalName.getValue());
				buttonClaim.setDisable(false);
			} else {
				JOptionPane.showMessageDialog(null, "Choose a device!", "Logical name is empty",
						JOptionPane.WARNING_MESSAGE);
			}

		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to claim \"" + logicalName.getSelectionModel().getSelectedItem() + "\"\nException: " + je.getMessage(), "Failed",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// Requests exclusive access to the device
	@FXML
	public void handleClaim(ActionEvent e) {
		try {
			display.claim(0);
			deviceEnabled.setDisable(false);
			buttonRelease.setDisable(false);
			setScreenModeTab.setDisable(false);
			setUpScreenMode();
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to claim \"" + logicalName.getSelectionModel().getSelectedItem() + "\"\nException: " + je.getMessage(), "Failed",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				display.setDeviceEnabled(true);
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
				
				windowList.clear();

				windowList.add("0");
				openWindowsListView.setItems(windowList);
				
			} else {
				display.setDeviceEnabled(false);
			}
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
		}
	}

	// Releases exclusive access to the device. The device is also disabled.
	@FXML
	public void handleRelease(ActionEvent e) {
		try {
			display.release();
			if (deviceEnabled.isSelected()) {
				deviceEnabled.setSelected(false);
				functionTab.setVisible(false);

			}
			deviceEnabled.setDisable(true);
			setScreenModeTab.setDisable(true);
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to release \"" + logicalName.getSelectionModel().getSelectedItem() + "\"\nException: " + je.getMessage(), "Failed",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// Releases the device and its resources. Also the device is released.
	@FXML
	public void handleClose(ActionEvent e) {
		try {
			display.close();
			if (!deviceEnabled.isDisable()) {
				deviceEnabled.setSelected(false);
				functionTab.setVisible(false);
			}
			setScreenModeTab.setDisable(true);
			buttonClaim.setDisable(true);
			deviceEnabled.setDisable(true);
			buttonRelease.setDisable(true);
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to close \"" + logicalName.getSelectionModel().getSelectedItem() + "\"\nException: " + je.getMessage(), "Failed",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleStatistics(ActionEvent e) {
		//TODO implement
	}

	@FXML
	public void handleFirmware(ActionEvent e) {
		//TODO implement
	}

	@FXML
	public void handleDisplayTextAt(ActionEvent e) {
		if (row.getSelectionModel().getSelectedItem() == null){
			JOptionPane.showMessageDialog(null, "Row is not selected!", "Logical name is empty",
					JOptionPane.WARNING_MESSAGE);
		}
		try {
			display.displayTextAt(row.getSelectionModel().getSelectedIndex(), column.getSelectionModel()
					.getSelectedItem(), displayText.getText(), attribute.getSelectionModel()
					.getSelectedIndex());
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
			display.displayText(displayText.getText(), attribute.getSelectionModel().getSelectedIndex());
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
			display.clearText();
		} catch (JposException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

	@FXML
	public void handleMoveCursor(ActionEvent e) {
		try {
			display.setCursorColumn(column.getSelectionModel().getSelectedIndex());
			display.setCursorRow(row.getSelectionModel().getSelectedIndex());
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
		if(blinkRate.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Param blinkRate is not set!", "Invalid Parameter", JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				display.setBlinkRate(Integer.parseInt(blinkRate.getText()));
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
		if (intercharacterWait.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Param ICharWait is not set!", "Invalid Parameter", JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				display.setInterCharacterWait(Integer.parseInt(intercharacterWait.getText()));
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
	public void handleAddWindow(ActionEvent e) {
		if(viewportRow.getText().equals("")||viewportColumn.getText().equals("")||viewportHeight.getText().equals("")||viewportWidth.getText().equals("")||windowHeight.getText().equals("")||windowWidth.getText().equals("")){
			JOptionPane.showMessageDialog(null, "One of the params is not set!", "Invalid Parameter", JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				FXCollections.sort(windowList);
				int num = 0;
				for (String s : windowList){
					if(s.equals(""+num)){
						num++;
					}
					if(num == 4){
						JOptionPane.showMessageDialog(null, "Too many open Windows!", "Invalid Parameter", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				
				windowList.add("" + num);

				FXCollections.sort(windowList);
				display.createWindow(Integer.parseInt(viewportRow.getText()),
						Integer.parseInt(viewportColumn.getText()), Integer.parseInt(viewportHeight.getText()),
						Integer.parseInt(viewportWidth.getText()), Integer.parseInt(windowHeight.getText()),
						Integer.parseInt(windowWidth.getText()));
		
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (JposException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			
		}
	}

	
	/*
	@FXML
	public void handleAddWindow(ActionEvent e) {
		if(viewportRow.getText().equals("")||viewportColumn.getText().equals("")||viewportHeight.getText().equals("")||viewportWidth.getText().equals("")||windowHeight.getText().equals("")||windowWidth.getText().equals("")){
			JOptionPane.showMessageDialog(null, "One of the params is not set!", "Invalid Parameter", JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				display.createWindow(Integer.parseInt(viewportRow.getText()),
						Integer.parseInt(viewportColumn.getText()), Integer.parseInt(viewportHeight.getText()),
						Integer.parseInt(viewportWidth.getText()), Integer.parseInt(windowHeight.getText()),
						Integer.parseInt(windowWidth.getText()));
				
				openWindowCount++;
				windowList.add("" + openWindowCount);
				/*
				openWindowCount++;
				openWindows.getItems().add(Integer.valueOf(openWindowCount));
				*/
	/*
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (JposException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			
		}
	}
	*/
	@FXML
	public void handleDeleteWindow(ActionEvent e) {
		try {
			display.destroyWindow();
			windowList.remove("" + currentWindow);

			FXCollections.sort(windowList);
		} catch (JposException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		
	}

	@FXML
	public void handleRefreshWindow(ActionEvent e) {
		if(openWindowsListView.getSelectionModel().getSelectedItem() == null){
			JOptionPane.showMessageDialog(null, "Choose a valid window!", "Invalid Parameter",
					JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				display.refreshWindow(Integer.parseInt(openWindowsListView.getSelectionModel().getSelectedItem()));
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
		if (scrollText_direction.getSelectionModel().getSelectedItem().equals("")){
			JOptionPane.showMessageDialog(null, "Choose a valid scroll direction!", "Invalid Parameter",
					JOptionPane.WARNING_MESSAGE);
		} else if (scrollText_Units.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Choose a valid unit!", "Invalid Parameter",
					JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				display.scrollText(scrollText_direction.getSelectionModel().getSelectedIndex(),
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
			display.setDeviceBrightness((int) (deviceBrightness.getValue()));
		} catch (JposException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

	@FXML
	public void handleSetDescriptor(ActionEvent e) {
		try {
			display.setDescriptor(descriptors.getSelectionModel().getSelectedIndex(), descriptor_attribute
					.getSelectionModel().getSelectedIndex());
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
			display.clearDescriptors();
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
			display.readCharacterAtCursor(help);
		} catch (JposException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		readCharacterField.setText("" + help[0]);

	}

	@FXML
	public void handleDefineGlyph(ActionEvent e) throws JposException {
		if (glyphBinaryPath.getText() == ""){
			JOptionPane.showMessageDialog(null, "Choose a valid glyph path", "Invalid Parameter",
					JOptionPane.WARNING_MESSAGE);
		} else if (glypeCode.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Choose a valid Glyph Code", "Invalid Parameter",
					JOptionPane.WARNING_MESSAGE);
		} else {
			byte[] bytes = getBytesFromFile(glyphBinaryPath.getText());
			display.defineGlyph(Integer.parseInt(glypeCode.getText()), bytes);
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
			display.setCharacterSet(characterSet.getSelectionModel().getSelectedItem());
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (JposException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

	@FXML
	public void handleSetScreenMode(ActionEvent e) {
		try {
			display.setScreenMode(screenMode.getSelectionModel().getSelectedIndex());
		} catch (JposException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleSetMarqueeType(ActionEvent e) {
		try {
			display.setMarqueeType(marqueeType.getSelectionModel().getSelectedIndex());
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
			display.setMarqueeFormat(marqueeFormat.getSelectionModel().getSelectedIndex());
			
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
		if(marqueeRepeatWait.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Param marqueeRepeatWait is false", "Invalid Parameter!",
					JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				display.setMarqueeRepeatWait(Integer.parseInt(marqueeRepeatWait.getText()));
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
		if(marqueeUnitWait.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Param marqueeUnitWait is false", "Invalid Parameter!",
					JOptionPane.WARNING_MESSAGE);
		} else {
			try {
				display.setMarqueeUnitWait(Integer.parseInt(marqueeUnitWait.getText()));
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
		if (bitmapPath.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Param bitmapPath is not set");
		} else {
			// Calculate Bitmap Width
			int newBitmapWidth = 0;
			if (bitmapWidth.getSelectionModel().getSelectedItem().equals(LineDisplayConstantMapper.DISP_BM_ASIS.getConstant())) {
				newBitmapWidth = LineDisplayConstantMapper.DISP_BM_ASIS.getContantNumber();
			} else {
				newBitmapWidth = Integer.parseInt(bitmapWidth.getSelectionModel().getSelectedItem());
			}
	
			// Calculate AlignmentX
			int newAlignmentX = 0;
			if (alignmentX.getSelectionModel().getSelectedItem().equals(LineDisplayConstantMapper.DISP_BM_LEFT.getConstant())) {
				newAlignmentX = LineDisplayConstantMapper.DISP_BM_LEFT.getContantNumber();
	
			} else if (alignmentX.getSelectionModel().getSelectedItem()
					.equals(LineDisplayConstantMapper.DISP_BM_CENTER.getConstant())) {
	
				newAlignmentX = LineDisplayConstantMapper.DISP_BM_CENTER.getContantNumber();
			} else if (alignmentX.getSelectionModel().getSelectedItem()
					.equals(LineDisplayConstantMapper.DISP_BM_RIGHT.getConstant())) {
	
				newAlignmentX = LineDisplayConstantMapper.DISP_BM_RIGHT.getContantNumber();
			} else {
				newAlignmentX = Integer.parseInt(alignmentX.getSelectionModel().getSelectedItem());
			}
	
			// Calculate AlignmentX
			int newAlignmentY = 0;
			if (alignmentY.getSelectionModel().getSelectedItem().equals(LineDisplayConstantMapper.DISP_BM_BOTTOM.getConstant())) {
	
				newAlignmentY = LineDisplayConstantMapper.DISP_BM_BOTTOM.getContantNumber();
	
			} else if (alignmentY.getSelectionModel().getSelectedItem()
					.equals(LineDisplayConstantMapper.DISP_BM_CENTER.getConstant())) {
	
				newAlignmentY = LineDisplayConstantMapper.DISP_BM_CENTER.getContantNumber();
	
			} else if (alignmentY.getSelectionModel().getSelectedItem()
					.equals(LineDisplayConstantMapper.DISP_BM_TOP.getConstant())) {
	
				newAlignmentY = LineDisplayConstantMapper.DISP_BM_TOP.getContantNumber();
	
			} else {
	
				newAlignmentY = Integer.parseInt(alignmentY.getSelectionModel().getSelectedItem());
	
			}
	
			try {
				display.displayBitmap(bitmapPath.getText(), newBitmapWidth, newAlignmentX, newAlignmentY);
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			} catch (JposException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
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

	/* ************************************************************************
	 * ************************ Set all ComboBox Values ***********************
	 * ***********************************************************************
	 */

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
			for (int i = 0; i < display.getRows(); i++) {
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
			for (int i = 0; i < display.getColumns(); i++) {
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
			descriptors.getItems().add(display.getDeviceDescriptors());
			descriptors.setValue(display.getDeviceDescriptors());
		} catch (JposException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error occured when getting Descriptors", "Error occured!",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void setUpDescriptorAttribute() {
		descriptor_attribute.getItems().clear();
		descriptor_attribute.getItems().add(LineDisplayConstantMapper.DISP_SD_OFF.getContantNumber(),
				LineDisplayConstantMapper.DISP_SD_OFF.getConstant());
		descriptor_attribute.getItems().add(LineDisplayConstantMapper.DISP_SD_ON.getContantNumber(),
				LineDisplayConstantMapper.DISP_SD_ON.getConstant());
		descriptor_attribute.getItems().add(LineDisplayConstantMapper.DISP_SD_BLINK.getContantNumber(),
				LineDisplayConstantMapper.DISP_SD_BLINK.getConstant());
		descriptor_attribute.setValue(LineDisplayConstantMapper.DISP_SD_OFF.getConstant());
	}

	private void setUpScrollTextDirection() {
		scrollText_direction.getItems().clear();
		
		//Need for correct Index
		scrollText_direction.getItems().add(0, "");
		scrollText_direction.getItems().add(LineDisplayConstantMapper.DISP_ST_UP.getContantNumber(),
				LineDisplayConstantMapper.DISP_ST_UP.getConstant());
		scrollText_direction.getItems().add(LineDisplayConstantMapper.DISP_ST_DOWN.getContantNumber(),
				LineDisplayConstantMapper.DISP_ST_DOWN.getConstant());
		scrollText_direction.getItems().add(LineDisplayConstantMapper.DISP_ST_LEFT.getContantNumber(),
				LineDisplayConstantMapper.DISP_ST_LEFT.getConstant());
		scrollText_direction.getItems().add(LineDisplayConstantMapper.DISP_ST_RIGHT.getContantNumber(),
				LineDisplayConstantMapper.DISP_ST_RIGHT.getConstant());
		
		scrollText_direction.setValue(LineDisplayConstantMapper.DISP_ST_UP.getConstant());

	}

	private void setUpCharacterSet() {
		characterSet.getItems().clear();
		try {
			for (int i = 0; i < display.getCharacterSetList().split(",").length; i++) {
				characterSet.getItems().add(Integer.parseInt((display.getCharacterSetList().split(","))[i]));
				if(i == 0) {
					characterSet.setValue(Integer.parseInt((display.getCharacterSetList().split(","))[i]));
				}
			}
			
		} catch (JposException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error occured when getting the CharacterSetList",
					"Error occured!", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void setUpScreenMode() {
		screenMode.getItems().clear();
		try {
			for (int i = 0; i < display.getScreenModeList().split(",").length; i++) {
				screenMode.getItems().add((display.getScreenModeList().split(","))[i]);
				if (i == 0){
					screenMode.setValue((display.getScreenModeList().split(","))[i]);
				}
			}
		} catch (JposException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error occured when getting the ScreenModeList",
					"Error occured!", JOptionPane.WARNING_MESSAGE);
		}
	}

	private void setUpMarqueeType() {
		marqueeType.getItems().clear();
		marqueeType.getItems().add(LineDisplayConstantMapper.DISP_MT_NONE.getContantNumber(),
				LineDisplayConstantMapper.DISP_MT_NONE.getConstant());
		marqueeType.getItems().add(LineDisplayConstantMapper.DISP_MT_UP.getContantNumber(),
				LineDisplayConstantMapper.DISP_MT_UP.getConstant());
		marqueeType.getItems().add(LineDisplayConstantMapper.DISP_MT_DOWN.getContantNumber(),
				LineDisplayConstantMapper.DISP_MT_DOWN.getConstant());
		marqueeType.getItems().add(LineDisplayConstantMapper.DISP_MT_LEFT.getContantNumber(),
				LineDisplayConstantMapper.DISP_MT_LEFT.getConstant());
		marqueeType.getItems().add(LineDisplayConstantMapper.DISP_MT_RIGHT.getContantNumber(),
				LineDisplayConstantMapper.DISP_MT_RIGHT.getConstant());
		marqueeType.getItems().add(LineDisplayConstantMapper.DISP_MT_INIT.getContantNumber(),
				LineDisplayConstantMapper.DISP_MT_INIT.getConstant());
		
		marqueeType.setValue(LineDisplayConstantMapper.DISP_MT_NONE.getConstant());
	}

	private void setUpMarqueeFormat() {
		marqueeFormat.getItems().clear();
		marqueeFormat.getItems().add(LineDisplayConstantMapper.DISP_MF_WALK.getContantNumber(),
				LineDisplayConstantMapper.DISP_MF_WALK.getConstant());
		marqueeFormat.getItems().add(LineDisplayConstantMapper.DISP_MF_PLACE.getContantNumber(),
				LineDisplayConstantMapper.DISP_MF_PLACE.getConstant());
		
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
}
