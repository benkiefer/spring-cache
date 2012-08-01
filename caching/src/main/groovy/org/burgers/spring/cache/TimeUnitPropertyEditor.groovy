package org.burgers.spring.cache

import java.beans.PropertyEditorSupport

class TimeUnitPropertyEditor extends PropertyEditorSupport {
    public void setAsText(String text) {
        setValue(Enum.valueOf(TimeUnit, text));
    }
}
