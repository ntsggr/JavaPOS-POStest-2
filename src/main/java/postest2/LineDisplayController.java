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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
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
	private ComboBox<String> row;
	@FXML
	private ComboBox<String> column;
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
	private ComboBox<Integer> descriptor_attribute;
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
	private ComboBox<String> characterSet;
	@FXML
	private ComboBox<String> screenMode;
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
	private TableView<String> openWindows;

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
	private TextField alignmentX;
	@FXML
	private TextField alignmentY;
	@FXML
	private ComboBox<String> bitmapWidth;
	@FXML
	private TextField bitmapPath;

	public LineDisplay display;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpLogicalNameComboBox();
		display = new LineDisplay();

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
	public void handle(ActionEvent e) {

	}

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
					"Failed to claim \"" + "display" + "\"\nException: " + je.getMessage(), "Failed",
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
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to claim \"" + "display" + "\"\nException: " + je.getMessage(), "Failed",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				display.setDeviceEnabled(true);
				functionTab.setVisible(true);
			} else {
				display.setDeviceEnabled(false);
				functionTab.setVisible(false);
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
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to release \"" + logicalName + "\"\nException: " + je.getMessage(), "Failed",
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
			buttonClaim.setDisable(true);
			deviceEnabled.setDisable(true);
			buttonRelease.setDisable(true);
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to close \"" + logicalName + "\"\nException: " + je.getMessage(), "Failed",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleStatistics(ActionEvent e) {

	}

	@FXML
	public void handleFirmware(ActionEvent e) {

	}

	@FXML
	public void handleDisplayTextAt(ActionEvent e) {
		try {
			display.displayTextAt(Integer.parseInt(row.getSelectionModel().getSelectedItem()),
					Integer.parseInt(column.getSelectionModel().getSelectedItem()), displayText.getText(),
					Integer.parseInt(attribute.getSelectionModel().getSelectedItem()));
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

	@FXML
	public void handleDisplayText(ActionEvent e) {
		try {
			display.displayText(displayText.getText(),
					Integer.parseInt(attribute.getSelectionModel().getSelectedItem()));
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

	@FXML
	public void handleClearText(ActionEvent e) {
		try {
			display.clearText();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

	@FXML
	public void handleMoveCursor(ActionEvent e) {
		try {
			display.setCursorColumn(Integer.parseInt(column.getSelectionModel().getSelectedItem()));
			display.setCursorRow(Integer.parseInt(row.getSelectionModel().getSelectedItem()));
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

	@FXML
	public void handleSetBlinkRate(ActionEvent e) {
		try {
			display.setBlinkRate(Integer.parseInt(blinkRate.getText()));
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

	@FXML
	public void handleSetICharWait(ActionEvent e) {
		try {
			display.setInterCharacterWait(Integer.parseInt(intercharacterWait.getText()));
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

	@FXML
	public void handleAddWindow(ActionEvent e) {
		try {
			display.createWindow(Integer.parseInt(viewportRow.getText()),
					Integer.parseInt(viewportColumn.getText()), Integer.parseInt(viewportHeight.getText()),
					Integer.parseInt(viewportWidth.getText()), Integer.parseInt(windowHeight.getText()),
					Integer.parseInt(windowWidth.getText()));
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

	@FXML
	public void handleDeleteWindow(ActionEvent e) {
		try {
			display.destroyWindow();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleRefreshWindow(ActionEvent e) {
		try {
			display.refreshWindow(Integer.parseInt(openWindows.getSelectionModel().getSelectedItem()));
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

	@FXML
	public void handleScrollText(ActionEvent e) {
		try {
			display.scrollText(Integer.parseInt(scrollText_direction.getSelectionModel().getSelectedItem()),
					Integer.parseInt(scrollText_Units.getText()));
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

	@FXML
	public void handleSetDeviceBrightness(ActionEvent e) {
		try {
			display.setDeviceBrightness((int) (deviceBrightness.getValue()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

	@FXML
	public void handleSetDescriptor(ActionEvent e) {
		try {
			display.setDescriptor(descriptors.getSelectionModel().getSelectedIndex(), descriptor_attribute
					.getSelectionModel().getSelectedItem());
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

	@FXML
	public void handleClearAllDescriptors(ActionEvent e) {
		try {
			display.clearDescriptors();
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

	@FXML
	public void handleReadCharacter(ActionEvent e) {
		int[] help = new int[1];
		try {
			display.readCharacterAtCursor(help);
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
		readCharacterField.setText("" + help[0]);

	}

	@FXML
	public void handleDefineGlyph(ActionEvent e) throws JposException {
		byte[] bytes = getBytesFromFile(glyphBinaryPath.getText());
		display.defineGlyph(Integer.parseInt(glypeCode.getText()), bytes);

	}

	@FXML
	public void handleSelectGlyphBinary(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Glyph Binary");
		File f = chooser.showOpenDialog(null);
		glyphBinaryPath.setText(f.getAbsolutePath());

	}

	@FXML
	public void handleSetCharacterSet(ActionEvent e) {
		try {
			display.setCharacterSet(Integer.parseInt(characterSet.getSelectionModel().getSelectedItem()));
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

	@FXML
	public void handleSetScreenMode(ActionEvent e) {
		try {
			display.setScreenMode(screenMode.getSelectionModel().getSelectedIndex());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleSetMarqueeType(ActionEvent e) {
		try {
			display.setMarqueeType(Integer.parseInt(marqueeType.getSelectionModel().getSelectedItem()));
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

	@FXML
	public void handleSetMarqueeFormat(ActionEvent e) {
		try {
			display.setMarqueeFormat(Integer.parseInt(marqueeFormat.getSelectionModel().getSelectedItem()));
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

	@FXML
	public void handleSetMarqueeRepeatWait(ActionEvent e) {
		try {
			display.setMarqueeRepeatWait(Integer.parseInt(marqueeRepeatWait.getText()));
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

	}

	@FXML
	public void handleSetMarqueeUnitWait(ActionEvent e) {
		try {
			display.setMarqueeUnitWait(Integer.parseInt(marqueeUnitWait.getText()));
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleChooseBitmap(ActionEvent e) {

		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose Bitmap Path");
		File f = chooser.showOpenDialog(null);
		bitmapPath.setText(f.getAbsolutePath());

	}

	@FXML
	public void handleDisplayBitmap(ActionEvent e) {
		try {
			display.displayBitmap(bitmapPath.getText(),
					Integer.parseInt(bitmapWidth.getSelectionModel().getSelectedItem()),
					Integer.parseInt(alignmentX.getText()), Integer.parseInt(alignmentY.getText()));
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	private byte[] getBytesFromFile(String path) {
		byte[] bytes = null;
		BufferedImage originalImage = null;
		try {
			originalImage = ImageIO.read(new File(path));
		} catch (IOException e1) {
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
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}

		return bytes;
	}

}
