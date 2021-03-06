package com.song.db.mapper;

import com.song.db.domain.GraduSearchHistory;
import com.song.db.domain.GraduSearchHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GraduSearchHistoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_search_history
     *
     * @mbg.generated
     */
    long countByExample(GraduSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_search_history
     *
     * @mbg.generated
     */
    int deleteByExample(GraduSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_search_history
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_search_history
     *
     * @mbg.generated
     */
    int insert(GraduSearchHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_search_history
     *
     * @mbg.generated
     */
    int insertSelective(GraduSearchHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_search_history
     *
     * @mbg.generated
     */
    GraduSearchHistory selectOneByExample(GraduSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_search_history
     *
     * @mbg.generated
     */
    GraduSearchHistory selectOneByExampleSelective(@Param("example") GraduSearchHistoryExample example, @Param("selective") GraduSearchHistory.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_search_history
     *
     * @mbg.generated
     */
    List<GraduSearchHistory> selectByExampleSelective(@Param("example") GraduSearchHistoryExample example, @Param("selective") GraduSearchHistory.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_search_history
     *
     * @mbg.generated
     */
    List<GraduSearchHistory> selectByExample(GraduSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_search_history
     *
     * @mbg.generated
     */
    GraduSearchHistory selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") GraduSearchHistory.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_search_history
     *
     * @mbg.generated
     */
    GraduSearchHistory selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_search_history
     *
     * @mbg.generated
     */
    GraduSearchHistory selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_search_history
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") GraduSearchHistory record, @Param("example") GraduSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_search_history
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") GraduSearchHistory record, @Param("example") GraduSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_search_history
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GraduSearchHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_search_history
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GraduSearchHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_search_history
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") GraduSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_search_history
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}