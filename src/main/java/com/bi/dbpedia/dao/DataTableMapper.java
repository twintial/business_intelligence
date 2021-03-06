package com.bi.dbpedia.dao;

import com.bi.dbpedia.model.Noun;
import com.bi.dbpedia.model.Predicate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface DataTableMapper {

    @Select("select subject, object, subject_uri as subjectUri, object_uri as objectUri," +
            " subject_label as subjectLabel, object_label as objectLabel from data_table")
    List<Noun> selectNoun();

    @Select("select predicate, predicate_uri as predicateUri, predicate_label as predicateLabel" +
            " from data_table")
    List<Predicate> selectPred();

    long insertResourceLabel(Set<String> labels);
    long insertPredicateLabel(Set<String> labels);
}
