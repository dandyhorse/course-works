package org.lenteam.colmen.models;

import java.time.LocalDate;

public class Page {

    private String url;
    private LocalDate lastScanDate;

    public Page(String url, LocalDate lastScanDate) {
        this.url = url;
        this.lastScanDate = lastScanDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDate getLastScanDate() {
        return lastScanDate;
    }

    public void setLastScanDate(LocalDate lastScanDate) {
        this.lastScanDate = lastScanDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Page page = (Page) o;

        if (url != null ? !url.equals(page.url) : page.url != null) return false;
        return lastScanDate != null ? lastScanDate.equals(page.lastScanDate) : page.lastScanDate == null;

    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (lastScanDate != null ? lastScanDate.hashCode() : 0);
        return result;
    }
}
