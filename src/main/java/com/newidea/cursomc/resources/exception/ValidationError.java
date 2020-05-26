package com.newidea.cursomc.resources.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }

    public List<FieldMessage> getErros() {
        return errors;
    }

    public void setList(List<FieldMessage> list) {
        this.errors = list;
    }

    public void addError(String fieldName,String message){
        errors.add(new FieldMessage(fieldName, message));
    }
}
