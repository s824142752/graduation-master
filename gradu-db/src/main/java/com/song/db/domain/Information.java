package com.song.db.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Information {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column information.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column information.create_time
     *
     * @mbg.generated
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column information.end_time
     *
     * @mbg.generated
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime endTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column information.username
     *
     * @mbg.generated
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column information.status
     *
     * @mbg.generated
     */
    private Byte status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column information.desc
     *
     * @mbg.generated
     */
    private String desc;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column information.img1
     *
     * @mbg.generated
     */
    private String img1;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column information.img2
     *
     * @mbg.generated
     */
    private String[] img2;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column information.img3
     *
     * @mbg.generated
     */
    private String img3;

    private Long duration;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column information.duration
     *
     * @return the value of information.duration
     *
     * @mbg.generated
     */
    public Long getDuration() {
        return duration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column information.duration
     *
     * @param duration the value for information.duration
     *
     * @mbg.generated
     */
    public void setDuration(Long duration) {
        this.duration = duration;
    }

    private GraduUser graduUsers;

    public GraduUser getGraduUser() {
        return graduUsers;
    }

    public void setGraduUser(GraduUser graduUsers) {
        this.graduUsers = graduUsers;
    }

    public LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column information.id
     *
     * @return the value of information.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column information.id
     *
     * @param id the value for information.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column information.create_time
     *
     * @return the value of information.create_time
     *
     * @mbg.generated
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column information.create_time
     *
     * @param createTime the value for information.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column information.end_time
     *
     * @return the value of information.end_time
     *
     * @mbg.generated
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column information.end_time
     *
     * @param endTime the value for information.end_time
     *
     * @mbg.generated
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column information.username
     *
     * @return the value of information.username
     *
     * @mbg.generated
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column information.username
     *
     * @param username the value for information.username
     *
     * @mbg.generated
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column information.status
     *
     * @return the value of information.status
     *
     * @mbg.generated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column information.status
     *
     * @param status the value for information.status
     *
     * @mbg.generated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column information.desc
     *
     * @return the value of information.desc
     *
     * @mbg.generated
     */
    public String getDesc() {
        return desc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column information.desc
     *
     * @param desc the value for information.desc
     *
     * @mbg.generated
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column information.img1
     *
     * @return the value of information.img1
     *
     * @mbg.generated
     */
    public String getImg1() {
        return img1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column information.img1
     *
     * @param img1 the value for information.img1
     *
     * @mbg.generated
     */
    public void setImg1(String img1) {
        this.img1 = img1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column information.img2
     *
     * @return the value of information.img2
     *
     * @mbg.generated
     */
    public String[] getImg2() {
        return img2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column information.img2
     *
     * @param img2 the value for information.img2
     *
     * @mbg.generated
     */
    public void setImg2(String[] img2) {
        this.img2 = img2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column information.img3
     *
     * @return the value of information.img3
     *
     * @mbg.generated
     */
    public String getImg3() {
        return img3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column information.img3
     *
     * @param img3 the value for information.img3
     *
     * @mbg.generated
     */
    public void setImg3(String img3) {
        this.img3 = img3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table information
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createTime=").append(createTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", duration=").append(duration);
        sb.append(", username=").append(username);
        sb.append(", status=").append(status);
        sb.append(", desc=").append(desc);
        sb.append(", img1=").append(img1);
        sb.append(", img2=").append(img2);
        sb.append(", img3=").append(img3);
        sb.append(", graduUser=").append(graduUsers);
        sb.append(", date=").append(date);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table information
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Information other = (Information) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getDuration() == null ? other.getDuration() == null : this.getDuration().equals(other.getDuration()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getDesc() == null ? other.getDesc() == null : this.getDesc().equals(other.getDesc()))
            && (this.getImg1() == null ? other.getImg1() == null : this.getImg1().equals(other.getImg1()))
            && (this.getImg2() == null ? other.getImg2() == null : this.getImg2().equals(other.getImg2()))
            && (this.getImg3() == null ? other.getImg3() == null : this.getImg3().equals(other.getImg3()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table information
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getDuration() == null) ? 0 : getDuration().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getDesc() == null) ? 0 : getDesc().hashCode());
        result = prime * result + ((getImg1() == null) ? 0 : getImg1().hashCode());
        result = prime * result + ((getImg2() == null) ? 0 : getImg2().hashCode());
        result = prime * result + ((getImg3() == null) ? 0 : getImg3().hashCode());
        return result;
    }



    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table information
     *
     * @mbg.generated
     */
    public enum Column {
        id("id", "id", "BIGINT", false),
        createTime("create_time", "createTime", "TIMESTAMP", false),
        endTime("end_time", "endTime", "TIMESTAMP", false),
        duration("duration", "duration", "BIGINT", false),
        username("username", "username", "VARCHAR", false),
        status("status", "status", "TINYINT", true),
        desc("desc", "desc", "VARCHAR", true),
        img1("img1", "img1", "VARCHAR", false),
        img2("img2", "img2", "VARCHAR", false),
        img3("img3", "img3", "VARCHAR", false);

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table information
         *
         * @mbg.generated
         */
        private static final String BEGINNING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table information
         *
         * @mbg.generated
         */
        private static final String ENDING_DELIMITER = "`";

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table information
         *
         * @mbg.generated
         */
        private final String column;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table information
         *
         * @mbg.generated
         */
        private final boolean isColumnNameDelimited;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table information
         *
         * @mbg.generated
         */
        private final String javaProperty;

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table information
         *
         * @mbg.generated
         */
        private final String jdbcType;



        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table information
         *
         * @mbg.generated
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table information
         *
         * @mbg.generated
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table information
         *
         * @mbg.generated
         */
        public String getJavaProperty() {
            return this.javaProperty;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table information
         *
         * @mbg.generated
         */
        public String getJdbcType() {
            return this.jdbcType;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table information
         *
         * @mbg.generated
         */
        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table information
         *
         * @mbg.generated
         */
        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table information
         *
         * @mbg.generated
         */
        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table information
         *
         * @mbg.generated
         */
        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table information
         *
         * @mbg.generated
         */
        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table information
         *
         * @mbg.generated
         */
        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}