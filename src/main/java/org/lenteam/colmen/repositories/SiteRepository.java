package org.lenteam.colmen.repositories;

import org.lenteam.colmen.entities.SiteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends CrudRepository<SiteEntity, Long>{
}
