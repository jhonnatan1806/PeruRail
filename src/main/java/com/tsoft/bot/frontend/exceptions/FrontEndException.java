/*
    @author: Abraham Hernandez - TSOFT
*/
package com.tsoft.bot.frontend.exceptions;

import org.apache.commons.lang3.StringUtils;

public class FrontEndException extends Exception{
    private final String codigo;

    public FrontEndException(String codigo, String mensaje) {
        super(mensaje);
        this.codigo = StringUtils.trimToEmpty(codigo);
    }

    public String getCodigo() {
        return codigo;
    }
}
