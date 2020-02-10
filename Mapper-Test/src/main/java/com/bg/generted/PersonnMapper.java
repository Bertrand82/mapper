package com.bg.generted;

import bg.test.p2.Personn;
import fr.xebia.extras.selma.CollectionMappingStrategy;
import fr.xebia.extras.selma.Mapper;

@Mapper(
        withIgnoreFields = {"toto","tata"},
        withEnums = {},
        withCollectionStrategy = CollectionMappingStrategy.ALLOW_GETTER,
        withCustom = {}
)
public interface PersonnMapper {
    Personn map(bg.test.p1.Personn c1);

    bg.test.p1.Personn map(Personn c2);
}
