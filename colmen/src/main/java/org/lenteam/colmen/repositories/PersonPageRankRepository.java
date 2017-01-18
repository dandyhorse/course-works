package org.lenteam.colmen.repositories;

import org.lenteam.colmen.entities.PersonPageRankEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * @author Rinat
 */
@Repository
public interface PersonPageRankRepository extends CrudRepository<PersonPageRankEntity, Integer> {

    @Query(value = "select r " +
            "from PersonPageRankEntity r, PageEntity p " +
            "where r.page = p and " +
            "PersonID = :pId and " +
            "SiteID = :sId and " +
            "p.lastScanDate between :fromDate and :toDate")
    List<PersonPageRankEntity> personRanksOnSiteBetweenPeriod(@Param("pId") Integer personId,
                                                              @Param("sId") Integer siteId,
                                                              @Param("fromDate") Date from,
                                                              @Param("toDate") Date to);

    //    select P.Url,P.LastScanDate,R.Rank from PersonPageRank R,Pages P where R.PageID = P.ID and PersonID='1' and SiteID=1;
//    select r.* from PersonPageRank r, Pages p where r.PageID = p.ID and PersonID = 1 and SiteID = 1;

}
