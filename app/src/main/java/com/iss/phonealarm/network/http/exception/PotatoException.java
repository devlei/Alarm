package com.iss.phonealarm.network.http.exception;

/**
 * Created by wp on 2017/9/23.
 *
 * @description
 */

public class PotatoException extends Exception {

    public PotatoException(){

    }

    public PotatoException(String msg){
        super(msg);
    }

    public PotatoException(Throwable e){
        super(e);
    }


    public PotatoException(String msg, Throwable e){
        super(msg, e);
    }
}
