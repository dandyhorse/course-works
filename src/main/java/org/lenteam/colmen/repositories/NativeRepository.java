package org.lenteam.colmen.repositories;

import org.lenteam.colmen.entities.SiteEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Anton_Solovev
 * @since 9/14/2016
 */
@Repository
public interface NativeRepository extends org.springframework.data.repository.Repository<SiteEntity, Integer> {

    @SuppressWarnings("unsafe")
    @Query(value = "select r.PersonID, sum(r.Rank) " +
            "from Pages p, PersonPageRank r " +
            "where r.PageID = p.ID and " +
            "p.SiteID = :id " +
            "group by r.PersonID, p.SiteID", nativeQuery = true)
        // in Object[]: 1 - Integer 2 - BigInteger
    List<Object[]> sumPersonPageRanksBySite(@Param("id") Integer siteID);

}
