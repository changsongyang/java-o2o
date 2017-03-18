package com.taotao.mapper;

import com.taotao.po.TbItemDescBak;
import com.taotao.po.TbItemDescBakExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbItemDescBakMapper {
    int countByExample(TbItemDescBakExample example);

    int deleteByExample(TbItemDescBakExample example);

    int insert(TbItemDescBak record);

    int insertSelective(TbItemDescBak record);

    List<TbItemDescBak> selectByExampleWithBLOBs(TbItemDescBakExample example);

    List<TbItemDescBak> selectByExample(TbItemDescBakExample example);

    int updateByExampleSelective(@Param("record") TbItemDescBak record, @Param("example") TbItemDescBakExample example);

    int updateByExampleWithBLOBs(@Param("record") TbItemDescBak record, @Param("example") TbItemDescBakExample example);

    int updateByExample(@Param("record") TbItemDescBak record, @Param("example") TbItemDescBakExample example);
}