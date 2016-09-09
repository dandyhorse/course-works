package org.lenteam.colmen.repositories;

import org.lenteam.colmen.entities.PageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends CrudRepository<PageEntity, Long> {
}
