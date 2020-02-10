package com.bg.generted;

import bg.test.p1.AbstractVehicule;
import bg.test.p2.Velo;
import bg.test.p2.Voiture;
import fr.xebia.extras.selma.Selma;

public class MapCustomAbstractVehicule {
    private VeloMapper veloMapper =  Selma.builder(VeloMapper.class).build();

    private VoitureMapper voitureMapper =  Selma.builder(VoitureMapper.class).build();

    public AbstractVehicule asAbstractVehicule(bg.test.p2.AbstractVehicule o) {
        if (o == null) return null;;
        if (o instanceof bg.test.p2.Velo) return veloMapper.map((Velo)o);;
        if (o instanceof bg.test.p2.Voiture) return voitureMapper.map((Voiture)o);;
        return null;
    }

    public bg.test.p2.AbstractVehicule asAbstractVehicule(AbstractVehicule o) {
        return null;
    }
}
