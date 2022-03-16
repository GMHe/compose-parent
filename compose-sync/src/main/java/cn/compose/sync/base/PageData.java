package cn.compose.sync.base;

import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class PageData {
    private Integer total;
    private Integer pageNum;
    private Integer pageSize;
    private List<Object> list;

    public static <T> PageData of(Integer total, Integer pageNum, Integer pageSize, List<T> list) {
        PageData pageData = new PageData();
        pageData.setTotal(total);
        pageData.setPageNum(pageNum);
        pageData.setPageSize(pageSize);
        pageData.setList(list == null ? Collections.emptyList() : (List<Object>) list);
        return pageData;
    }

    public static <T> PageData of(PageInfo<T> pageInfo) {
        return of((int) pageInfo.getTotal(), pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getList());
    }
}
