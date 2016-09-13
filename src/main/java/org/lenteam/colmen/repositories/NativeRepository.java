package org.lenteam.colmen.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Anton_Solovev
 * @since 9/14/2016
 */
public interface NativeRepository extends CrudRepository {
    @SuppressWarnings("unsafe")
    @Query(value = "select r.PersonID, sum(r.Rank) " +
            "from Pages p, PersonPageRank r " +
            "where r.PageID = p.ID and " +
            "p.SiteID = :id " +
            "group by r.PersonID, p.SiteID", nativeQuery = true)
        // in Object[]: 1 - Integer 2 - BigInteger
    List<Object[]> sumPersonPageRanksBySite(@Param("id") Integer siteID);
}
