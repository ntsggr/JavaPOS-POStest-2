/*
 * Copyright 2013 NTS New Technology Systems GmbH. All Rights reserved.
 * NTS PROPRIETARY/CONFIDENTIAL. Use is subject to NTS License Agreement.
 * Address: Doernbacher Strasse 126, A-4073 Wilhering, Austria
 * Homepage: www.ntswincash.com
 */
package postest2;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class POSTest2 extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("gui/MainWindow.fxml"));
		
		Scene scene = new Scene(root, 1000, 800);
		primaryStage.setTitle("JavaPOS POStest 2");

		primaryStage.setScene(scene);

		primaryStage.show();

	}

	@Override
	public void stop() throws Exception {
		// TODO Close Connections

	}

	public static void main(String[] args) {
		launch(args);
	}
	
	/*
	public JposServiceInstance getServiceClass(String logicalName) {
		String service = "";
		String factory = "";

		SimpleEntryRegistry reg = new SimpleEntryRegistry(new SimpleXmlRegPopulator());
		reg.load();
		Enumeration entriesEnum = reg.getEntries();

		while (entriesEnum.hasMoreElements()) {
			JposEntry entry = (JposEntry) entriesEnum.nextElement();
			if (entry.getLogicalName().equals(logicalName)) {
				service = entry.getPropertyValue(JposEntry.SERVICE_CLASS_PROP_NAME).toString();
				factory = entry.getPropertyValue(JposEntry.SI_FACTORY_CLASS_PROP_NAME).toString();

				Class[] paramString = new Class[1];
				paramString[0] = String.class;

				Class[] paramJposEntry = new Class[1];
				paramJposEntry[0] = JposEntry.class;

				JposServiceInstance jposService = null;
				try {
					Class clazz = Class.forName(factory);
					Object serviceFactory = clazz.newInstance();
					Method method = clazz.getMethod("createInstance", String.class, JposEntry.class);

					switch (entry.getPropertyValue(JposEntry.DEVICE_CATEGORY_PROP_NAME).toString()) {
					case "POSPrinter":
						jposService = (EscPosPrinterService) method.invoke(serviceFactory, service, entry);
						break;
					case "LineDisplay":
						jposService = (LineDisplayService) method.invoke(serviceFactory, service, entry);
						break;
					case "CashDrawer":
						jposService = (CashDrawerService) method.invoke(serviceFactory, service, entry);
						break;
					default:
						break;
					}
					System.out.println(": " + jposService.toString());
					return jposService;

				} catch (ClassNotFoundException e1) {
					System.out.println(e1);
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
			}
		}

		return null;
	}
	*/

}
