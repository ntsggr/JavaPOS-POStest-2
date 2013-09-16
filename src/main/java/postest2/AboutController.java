package postest2;

import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;

public class AboutController implements Initializable {

	@FXML
	public Hyperlink hyperlinkOldVersion;
	@FXML
	public Hyperlink hyperlinkNewVersion;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	public void handleHyperlinkOldVersion(ActionEvent e) {
		try {
			openURL("https://sourceforge.net/projects/postest/");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleHyperlinkNewVersion(ActionEvent e) {
		try {
			openURL("https://sourceforge.net/projects/javapospostest2/");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void openURL(String url) throws Exception {

		java.awt.Desktop.getDesktop().browse(new URI(url));

	}

}
