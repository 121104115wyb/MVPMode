package com.renogy.mvpmode.data.bean.main;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * @author Create by 17474 on 2021/4/20.
 * Email： lishuwentimor1994@163.com
 * Describe：搜索的数据类
 */
public class SearchData implements Serializable {

    private String name;
    private int id;

    public static SearchData of(String name, int id) {
        return new SearchData(name, id);
    }

    public SearchData(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    @Override
    public String toString() {
        return "SearchData{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
