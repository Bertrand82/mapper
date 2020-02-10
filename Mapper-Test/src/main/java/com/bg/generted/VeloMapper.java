package com.bg.generted;

import bg.test.p2.Velo;
import fr.xebia.extras.selma.CollectionMappingStrategy;
import fr.xebia.extras.selma.Mapper;

@Mapper(
        withIgnoreFields = {"toto","tata"},
        withEnums = {},
        withCollectionStrategy = CollectionMappingStrategy.ALLOW_GETTER,
        withCustom = {MapperCustomTYPE_VELO.class,MapperCustomTYPE_VELO.class}
)
public interface VeloMapper {
    Velo map(bg.test.p1.Velo c1);

    bg.test.p1.Velo map(Velo c2);
}
