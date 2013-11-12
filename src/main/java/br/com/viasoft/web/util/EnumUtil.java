/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.viasoft.web.util;

import br.com.viasoft.model.enumeration.EnumLabel;

import javax.faces.model.SelectItem;


public class EnumUtil {
    
    public static SelectItem[] populaSelectItem(Object[] values) {
        SelectItem[] itens = new SelectItem[values.length];
        int i = 0;
        for (Object value : values) {
            EnumLabel item = (EnumLabel) value;
            itens[i++] = new SelectItem(item, item.getLabel());
        }
        return itens;
    }
    
}
