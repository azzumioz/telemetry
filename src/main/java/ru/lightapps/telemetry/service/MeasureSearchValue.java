package ru.lightapps.telemetry.service;

import org.springframework.stereotype.Service;

@Service
public class MeasureSearchValue {
    private Integer pageNumber;
    private Integer pageSize;
    private String sortColumn;
    private String sortDirection;

    public MeasureSearchValue() {
    }

    public MeasureSearchValue(Integer pageNumber, Integer pageSize, String sortColumn, String sortDirection) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sortColumn = sortColumn;
        this.sortDirection = sortDirection;
    }

    public MeasureSearchValue(Integer pageNumber, Integer pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }
}
