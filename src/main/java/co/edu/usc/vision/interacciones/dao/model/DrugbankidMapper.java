package co.edu.usc.vision.interacciones.dao.model;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DrugbankidMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usc.drugbankid
     *
     * @mbggenerated
     */
    int countByExample(DrugbankidExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usc.drugbankid
     *
     * @mbggenerated
     */
    int deleteByExample(DrugbankidExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usc.drugbankid
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usc.drugbankid
     *
     * @mbggenerated
     */
    int insert(Drugbankid record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usc.drugbankid
     *
     * @mbggenerated
     */
    int insertSelective(Drugbankid record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usc.drugbankid
     *
     * @mbggenerated
     */
    List<Drugbankid> selectByExample(DrugbankidExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usc.drugbankid
     *
     * @mbggenerated
     */
    Drugbankid selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usc.drugbankid
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Drugbankid record, @Param("example") DrugbankidExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usc.drugbankid
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Drugbankid record, @Param("example") DrugbankidExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usc.drugbankid
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Drugbankid record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usc.drugbankid
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Drugbankid record);
}