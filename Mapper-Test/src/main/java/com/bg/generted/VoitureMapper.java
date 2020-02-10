package com.bg.generted;

import bg.test.p2.Voiture;
import fr.xebia.extras.selma.CollectionMappingStrategy;
import fr.xebia.extras.selma.Mapper;

@Mapper(
        withIgnoreFields = {"toto","tata"},
        withEnums = {},
        withCollectionStrategy = CollectionMappingStrategy.ALLOW_GETTER,
        withCustom = {}
)
public interface VoitureMapper {
    Voiture map(bg.test.p1.Voiture c1);

    bg.test.p1.Voiture map(Voiture c2);
}
