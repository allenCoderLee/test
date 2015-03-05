package com.ljd.java.beans;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import com.ljd.java.beans.model.User;

public class BeanTest {

	public BeanTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		User user = new User();
		System.out.println(user);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "ld");
		map.put("sex", "man");
		map.put("age", 20);
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(User.class);
			BeanDescriptor bs = beanInfo.getBeanDescriptor();
			Enumeration<String> attrs = bs.attributeNames();
			while(attrs.hasMoreElements()) {
				System.out.print(attrs.nextElement() + ",");
			}
			System.out.println();
			System.out.println("beanInfo display name:" + bs.getDisplayName() + "| name:" + bs.getName());
			System.out.println("short description:" + bs.getShortDescription());
			
			PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
			System.out.println(properties.length);
			for (PropertyDescriptor p : properties) {
				Method setter = p.getWriteMethod();
				if(setter != null && map.get(p.getName()) != null)
					setter.invoke(user, map.get(p.getName()));
				System.out.println(p.getName() + ":" + p.getDisplayName());
			}
			System.out.println(user);
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
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
		
	}
}
