package com.gwccnet.utilities.propertyDescriptors;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CachingBeanInformationServiceImpl implements BeanInformationService
{
	private static Map <String,List<Field>> fieldsByClass = new HashMap<String,List<Field>>();
	private static Map <Class<?>, PropertyEditor> editorMap = null;
	private static Map<String,Map<String, PropertyDescriptor>> descriptorMaps = null;
	/* (non-Javadoc)
	 * @see com.gwccnet.common.csv.BeanInformationService#getFields(java.lang.Class)
	 */
		public List<Field> getFields(Class<?> cls)
		{
			if (!fieldsByClass.containsKey(cls.getName()))
			{
				List<Field> fields = new ArrayList<Field>();
				Collections.addAll(fields, cls.getDeclaredFields());
				fieldsByClass.put(cls.getName(), fields);
			}
			return fieldsByClass.get(cls.getName());
		}
	  
		public PropertyDescriptor findDescriptor(String name,Class<?> bean) throws IntrospectionException 
	    {
	    	Map<String, PropertyDescriptor> descriptorMap = null;
	    	if (getDescriptorMaps().containsKey(bean.getName()))
	    	{
	    		descriptorMap = getDescriptorMaps().get(bean.getName());
	    	}
	    	if (descriptorMap == null || !descriptorMap.containsKey(name))
	        {
	        	descriptorMap = loadDescriptorMap(bean); //lazy load descriptors
	        	getDescriptorMaps().put(bean.getName(), descriptorMap);
	        }
	        return descriptorMap.get(name.toUpperCase().trim());
	    }
	    public boolean matches(String name, PropertyDescriptor desc) 
	    {
	        return desc.getName().equals(name.trim());
	    }
	    
	    public Map<String, PropertyDescriptor> loadDescriptorMap(Class<?> cls) throws IntrospectionException
	    {
	       Map<String, PropertyDescriptor> map = new HashMap<String, PropertyDescriptor>();
	       
	       PropertyDescriptor[] descriptors;
	       descriptors = loadDescriptors(cls);
	       for (PropertyDescriptor descriptor : descriptors)
	       {
	          map.put(descriptor.getName().toUpperCase().trim(), descriptor);
	       }
	       
	       return map;
	    }
	    private PropertyDescriptor[] loadDescriptors(Class<?> cls) throws IntrospectionException 
	    {
	        BeanInfo beanInfo = Introspector.getBeanInfo(cls);
	        return beanInfo.getPropertyDescriptors();
	    }
	    protected PropertyEditor getPropertyEditorValue(Class<?> cls)
	    {
	       if (editorMap == null)
	       {
	          editorMap = new HashMap<Class<?>, PropertyEditor>();
	       }
	       
	       PropertyEditor editor = editorMap.get(cls);
	       
	       if (editor == null)
	       {
	          editor = PropertyEditorManager.findEditor(cls);
	          addEditorToMap(cls, editor);
	       }
	       
	       return editor;
	    }

	   private void addEditorToMap(Class<?> cls, PropertyEditor editor)
	   {
	      if (editor != null)
	       {   
	          editorMap.put(cls, editor);
	       }
	   }


	    /*
	     * Attempt to find custom property editor on descriptor first, else try the propery editor manager.
	     */
	    public PropertyEditor getPropertyEditor(PropertyDescriptor desc) throws InstantiationException, IllegalAccessException {
	        Class<?> cls = desc.getPropertyEditorClass();
	        if (null != cls) return (PropertyEditor) cls.newInstance();
	        return getPropertyEditorValue(desc.getPropertyType());
	    }

		public static Map<String, Map<String, PropertyDescriptor>> getDescriptorMaps()
		{
			if (descriptorMaps == null)
			{
				descriptorMaps = new HashMap<String,Map<String,PropertyDescriptor>>();
			}
			return descriptorMaps;
		}

		


}
