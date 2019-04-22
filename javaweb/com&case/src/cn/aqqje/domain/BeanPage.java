package cn.aqqje.domain;

import java.util.List;

/*分页对象*/
public class BeanPage<T> {
    private int totalCount; /*总记录数*/
    private int totalPage; /*总页数*/
    private int rows; /*每页记录数*/
    private int currentPage; /*当前页*/
    private List<T> list; /*当前页实体*/

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "BeanPage{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", rows=" + rows +
                ", currentPage=" + currentPage +
                ", list=" + list +
                '}';
    }
}
