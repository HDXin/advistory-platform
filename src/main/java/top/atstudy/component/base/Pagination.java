package top.atstudy.component.base;

import org.apache.commons.lang3.StringUtils;
import top.atstudy.component.enums.EnumOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-16
 * Time: 21:42
 */
public class Pagination implements Serializable {
    private static final int MAX_LIMIT = 1000;
    private Integer offset = Integer.valueOf(0);
    private Integer limit = Integer.valueOf(16);
    private long total = 0L;
    private long startPage = 0L;
    /** @deprecated */
    @Deprecated
    private String sortField;
    /** @deprecated */
    @Deprecated
    private EnumOrder sortOrder;
    /** @deprecated */
    @Deprecated
    private String dbSortColumn;
    private String sortBy;

    public Pagination() {
        this.sortOrder = EnumOrder.DESC;
    }

    public Pagination(Integer offset, Integer limit) {
        this.sortOrder = EnumOrder.DESC;
        this.setOffset(offset);
        this.setLimit(limit);
    }

    public Pagination(Pagination pagination) {
        this.sortOrder = EnumOrder.DESC;
        if (pagination == null) {
            throw new NullPointerException("pagination is null");
        } else {
            this.offset = pagination.offset;
            this.limit = pagination.limit;
            this.total = pagination.total;
            this.sortField = pagination.sortField;
            this.sortBy = pagination.getSortBy();
            if (pagination.getSortOrder() != null) {
                this.sortOrder = pagination.getSortOrder();
            }

        }
    }

    public Integer getOffset() {
        return this.offset;
    }

    public void setOffset(Integer offset) {
        if (offset != null && offset.intValue() >= 0) {
            this.offset = offset;
        }

    }

    public Integer getLimit() {
        return this.limit;
    }

    public void setLimit(Integer limit) {
        if (limit != null && limit.intValue() > 0) {
            if (limit.intValue() > 1000) {
                limit = Integer.valueOf(1000);
            }

            this.limit = limit;
        }

    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long total) {
        if (total >= 0L) {
            this.total = total;
        }

    }

    /** @deprecated */
    @Deprecated
    public String getSortField() {
        return this.sortField;
    }

    /** @deprecated */
    @Deprecated
    public void setSortField(String sortField) {
        this.sortField = sortField;
        this.dbSortColumn = SortField.convertFieldToDBColumn(this.sortField);
    }

    /** @deprecated */
    @Deprecated
    public EnumOrder getSortOrder() {
        return this.sortOrder;
    }

    /** @deprecated */
    @Deprecated
    public void setSortOrder(EnumOrder sortOrder) {
        this.sortOrder = sortOrder;
    }

    public void setStartPage(Long startPage) {
        this.startPage = startPage.longValue();
        Integer calculateOffset = Math.toIntExact((startPage.longValue() - 1L) * (long)this.limit.intValue());
        if (this.offset == null || this.offset.intValue() < calculateOffset.intValue()) {
            this.offset = calculateOffset;
        }

    }

    public Long getStartPage() {
        return (long)(this.offset.intValue() / this.limit.intValue() + 1);
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortBy() {
        return this.sortBy;
    }

    /** @deprecated */
    @Deprecated
    public String getDbSortColumn() {
        return this.dbSortColumn;
    }

    /** @deprecated */
    @Deprecated
    public Pagination setDbSortColumn(String dbSortColumn) {
        this.dbSortColumn = dbSortColumn == null ? null : dbSortColumn.trim();
        return this;
    }

    public List<SortField> buildSortFields() {
        List<SortField> list = new ArrayList();
        if (StringUtils.isNotBlank(this.sortField)) {
            list.add(new SortField(this.sortField, this.sortOrder == null ? EnumOrder.DESC : this.sortOrder));
        }

        if (StringUtils.isBlank(this.sortBy)) {
            return list;
        } else {
            String[] sorts = StringUtils.split(this.sortBy, ";");
            String[] var3 = sorts;
            int var4 = sorts.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String sort = var3[var5];
                if (!StringUtils.isBlank(sort)) {
                    EnumOrder order = EnumOrder.ASC;
                    String[] items = StringUtils.split(sort, ":");
                    if (items.length <= 0 || !StringUtils.isBlank(items[0])) {
                        if (items.length > 1 && StringUtils.isNotBlank(items[1])) {
                            order = EnumOrder.valueOf(StringUtils.upperCase(StringUtils.trim(items[1])));
                        }

                        list.add(new SortField(StringUtils.trim(items[0]), order));
                    }
                }
            }

            return list;
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Pagination [offset=").append(this.offset).append(", limit=").append(this.limit).append(", total=").append(this.total).append(", sortField=").append(this.sortField).append(", sortOrder=").append(this.sortOrder).append(", sortby=").append(this.sortBy).append("]");
        return builder.toString();
    }
}