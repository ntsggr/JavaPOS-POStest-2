package postest2;

import java.awt.Dimension;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jpos.JposException;
import jpos.ToneIndicator;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ToneIndicatorController extends CommonController implements Initializable {

	@FXML
	@RequiredState(JposState.ENABLED)
	public Pane functionPane;

	@FXML
	@RequiredState(JposState.ENABLED)
	public CheckBox asyncMode;

	@FXML
	public TextField interToneWait;
	@FXML
	public TextField tone1Duration;
	@FXML
	public TextField tone1Pitch;
	@FXML
	public TextField tone2Duration;
	@FXML
	public TextField tone2Pitch;
	@FXML
	public TextField sound_numberOfCycles;
	@FXML
	public TextField sound_interSoundWait;

	@FXML
	public ComboBox<String> melodyType;

	@FXML
	public Slider melodyVolume;
	@FXML
	public Slider tone1Volume;
	@FXML
	public Slider tone2Volume;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = new ToneIndicator();
		RequiredStateChecker.invokeThis(this, service);
		setUpLogicalNameComboBox("ToneIndicator");
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((ToneIndicator) service).setDeviceEnabled(true);
				setUpComboBoxes();
			} else {
				((ToneIndicator) service).setDeviceEnabled(false);
			}
			RequiredStateChecker.invokeThis(this, service);
		} catch (JposException je) {
			System.err.println("ToneIndicatorPanel: CheckBoxListener: Jpos Exception" + je);
		}
	}

	@Override
	@FXML
	public void handleOCE(ActionEvent e) {
		super.handleOCE(e);
		deviceEnabled.setSelected(true);
		handleDeviceEnable(e);
	}

	/**
	 * Shows statistics of device if they are supported by the device
	 */
	@Override
	@FXML
	public void handleInfo(ActionEvent e) {
		try {
			IMapWrapper ticm = new ToneIndicatorConstantMapper();
			String msg = DeviceProperties.getProperties(service, ticm);
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
			((ToneIndicator) service).retrieveStatistics(stats);
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

	@FXML
	public void handleAsyncMode(ActionEvent e) {
		try {
			((ToneIndicator) service).setAsyncMode(asyncMode.selectedProperty().getValue());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleSetInterToneWait(ActionEvent e) {
		if (interToneWait.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Parameter is not specified!");
		} else {
			try {
				((ToneIndicator) service).setInterToneWait(Integer.parseInt(interToneWait.getText()));
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
	public void handleSetMelodyType(ActionEvent e) {
		try {
			((ToneIndicator) service).setMelodyType(ToneIndicatorConstantMapper.getConstantNumberFromString(melodyType
					.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetMelodyVolume(ActionEvent e) {
		try {
			((ToneIndicator) service).setMelodyVolume((int) melodyVolume.getValue());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetTone1Duration(ActionEvent e) {
		if (tone1Duration.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Parameter is not specified!");
		} else {
			try {
				((ToneIndicator) service).setTone1Duration(Integer.parseInt(tone1Duration.getText()));
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
	public void handleSetTone1Pitch(ActionEvent e) {
		if (tone1Pitch.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Parameter is not specified!");
		} else {
			try {
				((ToneIndicator) service).setTone1Pitch(Integer.parseInt(tone1Pitch.getText()));
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
	public void handleSetTone1Volume(ActionEvent e) {
		try {
			((ToneIndicator) service).setTone1Volume((int) tone1Volume.getValue());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetTone2Duration(ActionEvent e) {
		if (tone2Duration.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Parameter is not specified!");
		} else {
			try {
				((ToneIndicator) service).setTone2Duration(Integer.parseInt(tone2Duration.getText()));
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
	public void handleSetTone2Pitch(ActionEvent e) {
		if (tone2Pitch.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Parameter is not specified!");
		} else {
			try {
				((ToneIndicator) service).setTone2Pitch(Integer.parseInt(tone2Pitch.getText()));
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
	public void handleSetTone2Volume(ActionEvent e) {
		try {
			((ToneIndicator) service).setTone2Volume((int) tone2Volume.getValue());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSound(ActionEvent e) {
		if (sound_interSoundWait.getText().isEmpty() || sound_numberOfCycles.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "One of the Parameters are not specified!");
		} else {
			try {
				((ToneIndicator) service).sound(Integer.parseInt(sound_numberOfCycles.getText()), Integer
						.parseInt(sound_interSoundWait.getText()));
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
	public void handleSoundImmediate(ActionEvent e) {
		try {
			((ToneIndicator) service).soundImmediate();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	/*
	 * Set up ComboBoxes
	 */

	// Not properly initialized due to missing values in the ToneIndicatorConst
	// Interface
	private void setUpMelodyType() {
		melodyType.getItems().clear();
		melodyType.getItems().add(ToneIndicatorConstantMapper.TONE_MT_NONE.getConstant());
		melodyType.setValue(ToneIndicatorConstantMapper.TONE_MT_NONE.getConstant());
	}

	private void setUpComboBoxes() {
		setUpMelodyType();
	}

}