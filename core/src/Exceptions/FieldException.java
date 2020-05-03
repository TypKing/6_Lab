/*
 * Copyright (c) 2020. Ivannikov Alexandr Romanovich.
 * The text of this program is protected by copyright. Using it for commercial or personal purposes is prohibited
 * All rights reserved.
 */

package Exceptions;

public class FieldException extends RuntimeException{
    public FieldException(){
        super("Недопустимое значение поля!");
    }
}
