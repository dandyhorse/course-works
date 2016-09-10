package org.lenteam.colmen.services.assembler;

import org.lenteam.colmen.entities.KeywordEntity;
import org.lenteam.colmen.models.Keyword;
import org.springframework.stereotype.Component;

/**
 * @author Anton_Solovev
 * @since 9/10/2016
 */
@Component
public class KeywordAssembler implements Assembler<Keyword, KeywordEntity> {
    @Override
    public Keyword newModel(KeywordEntity entity) {
        return null;
    }

    @Override
    public KeywordEntity newEntity(Keyword model) {
        return null;
    }

    @Override
    public Iterable<Keyword> newModelList(Iterable<KeywordEntity> entityList) {
        return null;
    }
}
