package org.lenteam.colmen.services;

import org.lenteam.colmen.entities.PageEntity;
import org.lenteam.colmen.entities.PersonEntity;
import org.lenteam.colmen.entities.PersonPageRankEntity;
import org.lenteam.colmen.entities.SiteEntity;
import org.lenteam.colmen.models.*;
import org.lenteam.colmen.repositories.NativeRepository;
import org.lenteam.colmen.repositories.PersonPageRankRepository;
import org.lenteam.colmen.repositories.PersonRepository;
import org.lenteam.colmen.repositories.SiteRepository;
import org.lenteam.colmen.services.assembler.Assembler;
import org.lenteam.colmen.services.interfaces.CommonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Rinat
 * @author Anton_Solovev
 */
@Component
public class UserService implements CommonUserService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private SiteRepository siteRepository;
    @Autowired
    private NativeRepository nativeRepository;
    @Autowired
    private PersonPageRankRepository rankRepository;
    @Autowired
    private Assembler<Site, SiteEntity> siteAssembler;
    @Autowired
    private Assembler<Person, PersonEntity> personAssembler;

    @Override
    public Iterable<Person> getAllPersons() {
        Iterable<PersonEntity> entities = personRepository.findAll();
        return personAssembler.newModelList(entities);
    }

    @Override
    public Iterable<Site> getAllSites() {
        Iterable<SiteEntity> entities = siteRepository.findAll();
        return siteAssembler.newModelList(entities);
    }

    @SuppressWarnings("unsafe")
    @Override
    public Iterable<StatisticPerson> getPersonsOnSite(Integer siteId) {
        List<Object[]> resultList = nativeRepository.sumPersonPageRanksBySite(siteId);
        List<StatisticPerson> persons = new ArrayList<>();
        persons.addAll(resultList.stream()
                .map(o -> {
                    Integer i = (Integer) o[0];
                    Number bigI = (Number) o[1];
                    long value = bigI.longValue();
                    return new StatisticPerson(i, value);
                })
                .collect(Collectors.toList()));
        return persons;
    }

    @Override
    public DailyStatistic getPersonStatisticOnSite(Integer personId, Integer siteId) {
        LocalDate now = LocalDate.now();
        LocalDate monthAgo = now.minusMonths(1);
        return getPersonStatisticOnSite(personId, siteId, monthAgo, now);
    }

    @Override
    public DailyStatistic getPersonStatisticOnSite(Integer personId, Integer siteId, LocalDate fromDate, LocalDate toDate) {
        String siteName = siteRepository.findOne(siteId).getName();
        String personName = personRepository.findOne(personId).getName();
        List<PersonPageRankEntity> ranks = rankRepository.personRanksOnSiteBetweenPeriod(personId,
                siteId, Date.valueOf(fromDate), Date.valueOf(toDate));
        Long totalPages = (long) ranks.size();

        List<PageStatistic> collect = ranks.stream()
                .map(r -> {
                    PageEntity p = r.getPage();
                    return new PageStatistic(p.getUrl(), p.getLastScanDate().toLocalDate(), r.getRank());
                })
                .collect(Collectors.toList());
        return new DailyStatistic(personName, siteName, collect, totalPages);
    }
}
