package com.qf.my.worker.provider.mapper;


import com.qf.my.common.po.TbWorker;

public interface TbWorkerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbWorker record);

    int insertSelective(TbWorker record);

    TbWorker selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbWorker record);

    int updateByPrimaryKey(TbWorker record);
}
