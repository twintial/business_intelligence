package com.bi.dbpedia.dao;

import com.bi.dbpedia.model.DataTable;
import com.bi.dbpedia.model.Predicate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DataTableMapper {

    @Select("select subject, object, subject_uri as subjectUri, object_uri as objectUri," +
            " subject_label as subjectLabel, object_label as objectLabel from data_table")
    List<DataTable> selectNoun();

    @Select("select predicate, predicate_uri as predicateUri, predicate_label as predicateLabel" +
            " from data_table")
    List<Predicate> selectPred();
}
