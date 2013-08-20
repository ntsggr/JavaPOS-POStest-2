package postest2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DeviceProperties {

	public static String getProperties(Object object) {

		String properties = "";

		try {
			Method[] methods = Class.forName(object.getClass().getName()).getMethods();

			for (Method method : methods) {
				if (isGetter(method)) {
					properties += method.getName().substring(3) + ": " + method.invoke(object);
					properties += "\n";
				}
			}

		} catch (SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;

	}

	public static boolean isGetter(Method method) {
		if (!method.getName().startsWith("get"))
			return false;
		if (method.getParameterTypes().length != 0)
			return false;
		if (void.class.equals(method.getReturnType()))
			return false;
		return true;
	}
}
