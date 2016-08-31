package org.lenteam.colmen.models;

import java.time.LocalDate;
import java.util.Map;

/**
 * @author Anton_Solovev
 * @since 9/1/2016
 */
public class DailyStatistic {

    private Map<LocalDate, String> pagesByDate;
    private Long totalPages;

    public DailyStatistic(Map<LocalDate, String> pagesByDate, Long totalPages) {
        this.pagesByDate = pagesByDate;
        this.totalPages = totalPages;
    }

    public Map<LocalDate, String> getPagesByDate() {
        return pagesByDate;
    }

    public Long getTotalPages() {
        return totalPages;
    }
}
