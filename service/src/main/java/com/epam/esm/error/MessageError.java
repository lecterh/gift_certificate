package com.epam.esm.error;

public class MessageError {

    public static final String ERROR_0400150 = "Certificate is null";
    public static final String ERROR_0400151 = "Certificate name is empty";
    public static final String ERROR_0400152 = "Certificate name less than 3 symbols";
    public static final String ERROR_0400153 = "Certificate name more than 50 symbols";
    public static final String ERROR_0400154 = "Certificate description is null";
    public static final String ERROR_0400155 = "Certificate description less than 3 symbols";
    public static final String ERROR_0400156 = "Certificate description more than 500 symbols";
    public static final String ERROR_0400157 = "Certificate price is null";
    public static final String ERROR_0400158 = "Certificate price can't be negative";
    public static final String ERROR_0400159 = "Certificate duration is null";
    public static final String ERROR_0400160 = "Certificate with same name is exist";
    public static final String ERROR_0400161 = "Certificate with same name is not exist";

    public static final String ERROR_0500150 = "Tag is null";
    public static final String ERROR_0500151 = "Tag name is empty";
    public static final String ERROR_0500152 = "Tag name less then 3 symbols";
    public static final String ERROR_0500153 = "Tag name more than 50 symbols";
    public static final String ERROR_0500154 = "Tag with same name is not exist";
    public static final String ERROR_0500155 = "Incorrect tag's ID";
    public static final String ERROR_0500156 = "ID must contains only digit";

}
