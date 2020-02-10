package com.bg.generted;

import bg.test.p2.Velo;

public class MapperCustomTYPE_VELO {
    public Velo.TYPE_VELO asTYPE_VELO(bg.test.p1.Velo.TYPE_VELO s) {
        return Velo.TYPE_VELO.valueOf(""+s);
    }

    public bg.test.p1.Velo.TYPE_VELO asTYPE_VELO(Velo.TYPE_VELO enumP) {
        return bg.test.p1.Velo.TYPE_VELO.valueOf(enumP.name());
    }
}
