package org.lenteam.colmen.models;

import java.time.LocalDate;
import java.util.Map;

/**
 * @author Anton_Solovev
 * @since 9/1/2016
 */
public class DailyStatistic {

    private Map<LocalDate, Long> pagesByDate;
    private Long totalPages;

    public DailyStatistic(Map<LocalDate, Long> pagesByDate, Long totalPages) {
        this.pagesByDate = pagesByDate;
        this.totalPages = totalPages;
    }

    public Map<LocalDate, Long> getPagesByDate() {
        return pagesByDate;
    }

    public Long getTotalPages() {
        return totalPages;
    }
}
