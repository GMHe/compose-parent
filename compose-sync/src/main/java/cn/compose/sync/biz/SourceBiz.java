package cn.compose.sync.biz;


import cn.compose.sync.dao.SourceMapper;
import cn.compose.sync.entity.Source;
import cn.compose.sync.entity.SourceExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SourceBiz
 * @Description 类描述
 * @Author hgm
 * @Date 2021/11/18 14:09
 * @Version 1.0
 **/
@Service
@Slf4j
public class SourceBiz {
    @Resource
    private SourceMapper sourceMapper;

    public List<Source> getSources(){
        SourceExample sourceExample=new SourceExample();
        return sourceMapper.selectByExample(sourceExample);

    }

    public List<Source> getSourcesBySign(String sign){
        SourceExample sourceExample=new SourceExample();
        sourceExample.createCriteria().andSignEqualTo(sign);
        return sourceMapper.selectByExample(sourceExample);
    }
}
