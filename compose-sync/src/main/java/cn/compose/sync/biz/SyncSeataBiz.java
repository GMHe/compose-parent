package cn.compose.sync.biz;

import cn.compose.sync.base.ApiCodes;
import cn.compose.sync.base.Response;
import cn.compose.sync.base.ResponseBuilder;
import cn.compose.sync.biz.ibiz.ISyncSeataBiz;
import cn.compose.sync.dao.StorageMapper;
import cn.compose.sync.entity.Storage;
import cn.compose.sync.entity.StorageExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @ClassName SyncSeataBiz
 * @Description 类描述
 * @Author hgm
 * @Date 2021/11/15 14:42
 * @Version 1.0
 **/
@Service
@Slf4j
public class SyncSeataBiz implements ISyncSeataBiz {
    @Resource
    private StorageMapper storageMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Response deduct(Long id, Long num){
        try {

            Storage storage = storageMapper.selectByPrimaryKey(id);
            if (!Objects.isNull(storage)) {

                Long stNum = storage.getNum() - num;
                //检查是否还有库存
                if (stNum <= 0) {
                    return ResponseBuilder.error(ApiCodes.INTERNAL_ERROR, "库存不足！");
                }

                storage.setNum(stNum);
                StorageExample storageExample = new StorageExample();
                storageExample.createCriteria().andIdEqualTo(id);
                storageMapper.updateByExample(storage, storageExample);
                return ResponseBuilder.ok("ok", "扣减库存成功！");
            }
        }catch (Exception e){
            log.error("操作库存数据异常：",e);
        }
        return ResponseBuilder.error(ApiCodes.INTERNAL_ERROR,"操作库存数据异常！");
    }

    @Override
    public Storage getStorageById(Long id){
        return storageMapper.selectByPrimaryKey(id);
    }
}
