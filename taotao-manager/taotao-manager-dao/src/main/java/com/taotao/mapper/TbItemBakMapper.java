package com.taotao.mapper;

import com.taotao.po.TbItemBak;
import com.taotao.po.TbItemBakExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbItemBakMapper {
    int countByExample(TbItemBakExample example);

    int deleteByExample(TbItemBakExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbItemBak record);

    int insertSelective(TbItemBak record);

    List<TbItemBak> selectByExample(TbItemBakExample example);

    TbItemBak selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbItemBak record, @Param("example") TbItemBakExample example);

    int updateByExample(@Param("record") TbItemBak record, @Param("example") TbItemBakExample example);

    int updateByPrimaryKeySelective(TbItemBak record);

    int updateByPrimaryKey(TbItemBak record);
}