package com.bg.generted;

import bg.test.enump.COUNTRY;
import java.lang.String;

public class MapperCustomCOUNTRY {
    public COUNTRY asCOUNTRY(String s) {
        return COUNTRY.valueOf(""+s);
    }

    public String asString(COUNTRY enumP) {
        return enumP.name();
    }
}
