/*
 * Copyright (c) 2020. Ivannikov Alexandr Romanovich.
 * The text of this program is protected by copyright. Using it for commercial or personal purposes is prohibited
 * All rights reserved.
 */

package Exceptions;

public class OverflowException extends RuntimeException {
    public OverflowException(){
        super("Количество уникальный значений ID закончилось, уменьште количество рабочих!");
    }
}
