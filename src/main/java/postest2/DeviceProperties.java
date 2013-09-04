package postest2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

public class DeviceProperties {

	public static String getProperties(Object object, IMapWrapper objectMap) {

		String properties = "";

		try {
			Method[] methods = Class.forName(object.getClass().getName()).getMethods();
			
			for (Method method : methods) {
				if (isGetter(method)) {
					String methodName = method.getName();
					if (method.getReturnType().equals(Integer.TYPE)) {
						if (objectMap != null) {
							ArrayList<String> al = BelongingPropertyChecker.invokeThis(objectMap, methodName);
							if (!al.isEmpty()) {
								properties += method.getName().substring(3) + ": ";
								Iterator<String> iterator = al.iterator();
								while (iterator.hasNext()) {
									String value = iterator.next().toString();
									properties += value + ", ";
								}
								properties += "\n";
							}
						}
					} else {
						properties += method.getName().substring(3) + ": " + method.invoke(object);
						properties += "\n";
					}
				}
			}

		} catch (SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
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
