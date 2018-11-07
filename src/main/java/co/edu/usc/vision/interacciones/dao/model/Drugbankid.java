package co.edu.usc.vision.interacciones.dao.model;

import java.io.Serializable;

public class Drugbankid implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table usc.drugbankid
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column usc.drugbankid.id
     *
     * @mbggenerated
     */
    private Integer id;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column usc.drugbankid.drugbank_id
     *
     * @mbggenerated
     */
    private String drugbankId;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column usc.drugbankid.atc
     *
     * @mbggenerated
     */
    private String atc;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column usc.drugbankid.id
     *
     * @return the value of usc.drugbankid.id
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column usc.drugbankid.id
     *
     * @param id the value for usc.drugbankid.id
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column usc.drugbankid.drugbank_id
     *
     * @return the value of usc.drugbankid.drugbank_id
     * @mbggenerated
     */
    public String getDrugbankId() {
        return drugbankId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column usc.drugbankid.drugbank_id
     *
     * @param drugbankId the value for usc.drugbankid.drugbank_id
     * @mbggenerated
     */
    public void setDrugbankId(String drugbankId) {
        this.drugbankId = drugbankId == null ? null : drugbankId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column usc.drugbankid.atc
     *
     * @return the value of usc.drugbankid.atc
     * @mbggenerated
     */
    public String getAtc() {
        return atc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column usc.drugbankid.atc
     *
     * @param atc the value for usc.drugbankid.atc
     * @mbggenerated
     */
    public void setAtc(String atc) {
        this.atc = atc == null ? null : atc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usc.drugbankid
     *
     * @mbggenerated
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
        Drugbankid other = (Drugbankid) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getDrugbankId() == null ? other.getDrugbankId() == null : this.getDrugbankId().equals(other.getDrugbankId()))
                && (this.getAtc() == null ? other.getAtc() == null : this.getAtc().equals(other.getAtc()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table usc.drugbankid
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDrugbankId() == null) ? 0 : getDrugbankId().hashCode());
        result = prime * result + ((getAtc() == null) ? 0 : getAtc().hashCode());
        return result;
    }
}