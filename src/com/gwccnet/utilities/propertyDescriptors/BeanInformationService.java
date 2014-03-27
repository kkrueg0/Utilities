package com.gwccnet.utilities.propertyDescriptors;

import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.lang.reflect.Field;
import java.util.List;

public interface BeanInformationService
{

	public List<Field> getFields(Class<?> cls);
	public PropertyEditor getPropertyEditor(PropertyDescriptor desc) throws Exception;
	public PropertyDescriptor findDescriptor(String name,Class<?> bean) throws Exception; 
    public boolean matches(String name, PropertyDescriptor desc);

}