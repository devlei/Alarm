package phonealarm.iss.com.alarm.bean.infocollection;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import phonealarm.iss.com.alarm.bean.uploadalarm.UpLoadFileBean;

/**
 * Created by zhaocuilong on 2017/9/26.
 */
@XStreamAlias("information")
public class InfoCollectBean implements Serializable {

    @XStreamAlias("SHANGBAOID")
    private String SHANGBAOID;

    @XStreamAlias("HOUSEADRESS")
    private String HOUSEADRESS;

    @XStreamAlias("FUZENAME")
    private String FUZENAME;

    @XStreamAlias("FUZERENCARD")
    private String FUZERENCARD;

    @XStreamAlias("FUZETELE")
    private String FUZETELE;

    @XStreamAlias("FUZERENHOUSE")
    private String FUZERENHOUSE;

    @XStreamAlias("STARTTIME")
    private String STARTTIME;

    @XStreamAlias("ENDTIME")
    private String ENDTIME;

    @XStreamImplicit(itemFieldName = "RZR_LIST")
    private List<LiablePerson> rzeList = new ArrayList<LiablePerson>();

    public String getSHANGBAOID() {
        return SHANGBAOID;
    }

    public void setSHANGBAOID(String SHANGBAOID) {
        this.SHANGBAOID = SHANGBAOID;
    }

    public String getHOUSEADRESS() {
        return HOUSEADRESS;
    }

    public void setHOUSEADRESS(String HOUSEADRESS) {
        this.HOUSEADRESS = HOUSEADRESS;
    }

    public String getFUZENAME() {
        return FUZENAME;
    }

    public void setFUZENAME(String FUZENAME) {
        this.FUZENAME = FUZENAME;
    }

    public String getFUZERENCARD() {
        return FUZERENCARD;
    }

    public void setFUZERENCARD(String FUZERENCARD) {
        this.FUZERENCARD = FUZERENCARD;
    }

    public String getFUZETELE() {
        return FUZETELE;
    }

    public void setFUZETELE(String FUZETELE) {
        this.FUZETELE = FUZETELE;
    }

    public String getFUZERENHOUSE() {
        return FUZERENHOUSE;
    }

    public void setFUZERENHOUSE(String FUZERENHOUSE) {
        this.FUZERENHOUSE = FUZERENHOUSE;
    }

    public String getSTARTTIME() {
        return STARTTIME;
    }

    public void setSTARTTIME(String STARTTIME) {
        this.STARTTIME = STARTTIME;
    }

    public String getENDTIME() {
        return ENDTIME;
    }

    public void setENDTIME(String ENDTIME) {
        this.ENDTIME = ENDTIME;
    }

    public List<LiablePerson> getRzeList() {
        return rzeList;
    }

    public void setRzeList(List<LiablePerson> rzeList) {
        this.rzeList = rzeList;
    }

    @Override
    public String toString() {
        return "InfoCollectBean{" +
                "SHANGBAOID='" + SHANGBAOID + '\'' +
                ", HOUSEADRESS='" + HOUSEADRESS + '\'' +
                ", FUZENAME='" + FUZENAME + '\'' +
                ", FUZERENCARD='" + FUZERENCARD + '\'' +
                ", FUZETELE='" + FUZETELE + '\'' +
                ", FUZERENHOUSE='" + FUZERENHOUSE + '\'' +
                ", STARTTIME='" + STARTTIME + '\'' +
                ", ENDTIME='" + ENDTIME + '\'' +
                ", rzeList=" + rzeList +
                '}';
    }
}
