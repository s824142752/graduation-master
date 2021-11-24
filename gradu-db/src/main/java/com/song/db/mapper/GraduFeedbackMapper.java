package com.song.db.mapper;

import com.song.db.domain.GraduFeedback;
import com.song.db.domain.GraduFeedbackExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GraduFeedbackMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_feedback
     *
     * @mbg.generated
     */
    long countByExample(GraduFeedbackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_feedback
     *
     * @mbg.generated
     */
    int deleteByExample(GraduFeedbackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_feedback
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_feedback
     *
     * @mbg.generated
     */
    int insert(GraduFeedback record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_feedback
     *
     * @mbg.generated
     */
    int insertSelective(GraduFeedback record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_feedback
     *
     * @mbg.generated
     */
    GraduFeedback selectOneByExample(GraduFeedbackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_feedback
     *
     * @mbg.generated
     */
    GraduFeedback selectOneByExampleSelective(@Param("example") GraduFeedbackExample example, @Param("selective") GraduFeedback.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_feedback
     *
     * @mbg.generated
     */
    List<GraduFeedback> selectByExampleSelective(@Param("example") GraduFeedbackExample example, @Param("selective") GraduFeedback.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_feedback
     *
     * @mbg.generated
     */
    List<GraduFeedback> selectByExample(GraduFeedbackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_feedback
     *
     * @mbg.generated
     */
    GraduFeedback selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") GraduFeedback.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_feedback
     *
     * @mbg.generated
     */
    GraduFeedback selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_feedback
     *
     * @mbg.generated
     */
    GraduFeedback selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_feedback
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") GraduFeedback record, @Param("example") GraduFeedbackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_feedback
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") GraduFeedback record, @Param("example") GraduFeedbackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_feedback
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GraduFeedback record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_feedback
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(GraduFeedback record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_feedback
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") GraduFeedbackExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gradu_feedback
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}