package com.bg.generted;

import bg.test.p2.AbstractVehicule;
import fr.xebia.extras.selma.CollectionMappingStrategy;
import fr.xebia.extras.selma.Mapper;

@Mapper(
        withIgnoreFields = {"toto","tata"},
        withEnums = {},
        withCollectionStrategy = CollectionMappingStrategy.ALLOW_GETTER,
        withCustom = {MapCustomAbstractVehicule.class}
)
public interface AbstractVehiculeMapper {
    AbstractVehicule map(bg.test.p1.AbstractVehicule c1);

    bg.test.p1.AbstractVehicule map(AbstractVehicule c2);
}
