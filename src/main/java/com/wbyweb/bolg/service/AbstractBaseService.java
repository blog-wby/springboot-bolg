package com.wbyweb.bolg.service;


import com.wbyweb.bolg.mapper.IBaseMapper;
import org.springframework.transaction.annotation.Transactional;


public abstract class AbstractBaseService<T> implements IBaseMapper<T> {

    //获取mapper实例
    protected abstract IBaseMapper<T> getMapper();

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return getMapper().deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int insert(T record) {
        return getMapper().insert(record);
    }

    @Override
    public int insertSelective(T record) {
        return getMapper().insertSelective(record);
    }

    @Override
    public T selectByPrimaryKey(Integer id) {
        return getMapper().selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        return getMapper().updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(T record) {
        return getMapper().updateByPrimaryKey(record);
    }


}
