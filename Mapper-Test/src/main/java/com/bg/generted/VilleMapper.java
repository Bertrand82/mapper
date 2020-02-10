package com.bg.generted;

import bg.test.p2.Ville;
import fr.xebia.extras.selma.CollectionMappingStrategy;
import fr.xebia.extras.selma.Mapper;

@Mapper(
        withIgnoreFields = {"toto","tata"},
        withEnums = {},
        withCollectionStrategy = CollectionMappingStrategy.ALLOW_GETTER,
        withCustom = {MapperCustomCOUNTRY.class}
)
public interface VilleMapper {
    Ville map(bg.test.p1.Ville c1);

    bg.test.p1.Ville map(Ville c2);
}
