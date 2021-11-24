package com.song.db.mapper;

import com.song.db.domain.GraduSystem;
import com.song.db.domain.GraduSystemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GraduSystemMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_system
     *
     * @mbg.generated
     */
    long countByExample(GraduSystemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_system
     *
     * @mbg.generated
     */
    int deleteByExample(GraduSystemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_system
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_system
     *
     * @mbg.generated
     */
    int insert(GraduSystem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_system
     *
     * @mbg.generated
     */
    int insertSelective(GraduSystem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_system
     *
     * @mbg.generated
     */
    GraduSystem selectOneByExample(GraduSystemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_system
     *
     * @mbg.generated
     */
    GraduSystem selectOneByExampleSelective(@Param("example") GraduSystemExample example, @Param("selective") GraduSystem.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_system
     *
     * @mbg.generated
     */
    List<GraduSystem> selectByExampleSelective(@Param("example") GraduSystemExample example, @Param("selective") GraduSystem.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_system
     *
     * @mbg.generated
     */
    List<GraduSystem> selectByExample(GraduSystemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_system
     *
     * @mbg.generated
     */
    GraduSystem selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") GraduSystem.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_system
     *
     * @mbg.generated
     */
    GraduSystem selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_system
     *
     * @mbg.generated
     */
    GraduSystem selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_system
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") GraduSystem record, @Param("example") GraduSystemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_system
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") GraduSystem record, @Param("example") GraduSystemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_system
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GraduSystem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_system
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GraduSystem record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_system
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") GraduSystemExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_system
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}