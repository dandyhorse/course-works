package org.lenteam.colmen.services.assembler;

import org.lenteam.colmen.entities.SiteEntity;
import org.lenteam.colmen.models.Site;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Anton_Solovev
 * @since 9/10/2016
 */
@Component
public class SiteAssembler implements Assembler<Site, SiteEntity> {

    @Override
    public Site newModel(SiteEntity entity) {
        return new Site(entity.getId(), entity.getName());
    }

    @Override
    public SiteEntity newEntity(Site model) {
        return null;
    }

    @Override
    public Iterable<Site> newModelList(List<SiteEntity> entityList) {
        Stream<Site> stream = entityList.stream().map(this::newModel);
        return stream.collect(Collectors.toList());
    }


}
