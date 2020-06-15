package com.bi.dbpedia.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LabelMapper {

    @Select("select label from resource_label")
    List<String> selectResourceLabels();

    @Select("select label from predicate_label")
    List<String> selectPredicateLabels();
}
