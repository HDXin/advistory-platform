package top.atstudy.component.base;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: hdxin
 * Date: 2018-07-16
 * Time: 21:44
 */
public class Page<T> extends Pagination {
    private List<T> items = new LinkedList();

    public Page() {
    }

    public Page(Pagination pagination) {
        super(pagination);
    }

    public List<T> getItems() {
        return this.items;
    }

    public Page<T> setItems(List<T> items) {
        this.items = items;
        return this;
    }

    public Page<T> addItem(T obj) {
        this.items.add(obj);
        return this;
    }

    public String toString() {
        return "Page [items=" + this.items + ", super=" + super.toString() + "]";
    }
}
