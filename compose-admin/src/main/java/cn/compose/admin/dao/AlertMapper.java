package cn.compose.admin.dao;

import cn.compose.admin.entity.Alert;
import cn.compose.admin.entity.AlertExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;

@Resource
public interface AlertMapper {
    long countByExample(AlertExample example);

    int deleteByExample(AlertExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Alert record);

    int insertSelective(Alert record);

    List<Alert> selectByExampleWithBLOBs(AlertExample example);

    List<Alert> selectByExample(AlertExample example);

    Alert selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Alert record, @Param("example") AlertExample example);

    int updateByExampleWithBLOBs(@Param("record") Alert record, @Param("example") AlertExample example);

    int updateByExample(@Param("record") Alert record, @Param("example") AlertExample example);

    int updateByPrimaryKeySelective(Alert record);

    int updateByPrimaryKeyWithBLOBs(Alert record);

    int updateByPrimaryKey(Alert record);
}