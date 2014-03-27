package com.gwccnet.opencsv;

import java.beans.PropertyEditorSupport;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)
public @interface PropertyEditor
{
	Class<? extends PropertyEditorSupport> editorClass();
}
