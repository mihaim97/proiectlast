package com.ubb.ro.proiect1.util;

import java.util.List;

public class SingleResultFromList {

    public static <T> T getSingleResult(List<T> list) {
        if(list.size() == 1) {
            return list.get(0);
        } else {
            return null;
        }
    }

}
