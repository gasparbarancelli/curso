package br.com.viasoft.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("enumConverter")
public class EnumConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
        if (value == null || value.length() < 1) {
            return null;
        }

        int pos = value.indexOf('@');
        if (pos < 0) {
            throw new IllegalArgumentException(value + " do not point to an enum");
        }

        String className = value.substring(0, pos);
        Class clazz;
        int ordinal = Integer.parseInt(value.substring(pos + 1), 10);

        try {
            clazz = Class.forName(className, true, Thread.currentThread().getContextClassLoader());
            // if the clazz is not an enum it might be an enum which is inherited. In this case try to find the superclass.
            while (clazz != null && !clazz.isEnum()) {
                clazz = clazz.getSuperclass();
            }
            if (clazz == null) {
                throw new IllegalArgumentException("class " + className + " couldn't be treated as enum");
            }

            Enum[] enums = (Enum[]) clazz.getEnumConstants();
            if (enums.length >= ordinal) {
                return enums[ordinal];
            }
        } catch (ClassNotFoundException e1) {
            throw new RuntimeException(e1);
        }

        throw new IllegalArgumentException("ordinal " + ordinal + " not found in enum " + clazz);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
        if (value == null) {
            return "";
        }

        Enum<?> e = (Enum<?>) value;

        if (component instanceof UIInput || UIInput.COMPONENT_FAMILY.equals(component.getFamily())) {
            return e.getClass().getName() + "@" + Integer.toString(e.ordinal(), 10);
        }
        return e.name();
    }
    
}
