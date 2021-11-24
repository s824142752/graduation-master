package com.song.db.mapper;

import com.song.db.domain.GraduAdmin;
import com.song.db.domain.GraduAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GraduAdminMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_admin
     *
     * @mbg.generated
     */
    long countByExample(GraduAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_admin
     *
     * @mbg.generated
     */
    int deleteByExample(GraduAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_admin
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_admin
     *
     * @mbg.generated
     */
    int insert(GraduAdmin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_admin
     *
     * @mbg.generated
     */
    int insertSelective(GraduAdmin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_admin
     *
     * @mbg.generated
     */
    GraduAdmin selectOneByExample(GraduAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_admin
     *
     * @mbg.generated
     */
    GraduAdmin selectOneByExampleSelective(@Param("example") GraduAdminExample example, @Param("selective") GraduAdmin.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_admin
     *
     * @mbg.generated
     */
    List<GraduAdmin> selectByExampleSelective(@Param("example") GraduAdminExample example, @Param("selective") GraduAdmin.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_admin
     *
     * @mbg.generated
     */
    List<GraduAdmin> selectByExample(GraduAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_admin
     *
     * @mbg.generated
     */
    GraduAdmin selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") GraduAdmin.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_admin
     *
     * @mbg.generated
     */
    GraduAdmin selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_admin
     *
     * @mbg.generated
     */
    GraduAdmin selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_admin
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") GraduAdmin record, @Param("example") GraduAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_admin
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") GraduAdmin record, @Param("example") GraduAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_admin
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GraduAdmin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_admin
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GraduAdmin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_admin
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") GraduAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_admin
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}