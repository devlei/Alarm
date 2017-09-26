package phonealarm.iss.com.alarm.bean.suspect;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * 嫌疑犯信息
 *
 * @author Administrator
 */
@XStreamAlias("suspectInfo")
public class SuspectInfo implements Serializable {

    @XStreamAlias("suspect_id")
    private String suspect_id;

    @XStreamAlias("suspect_name")
    private String suspect_name;

    @XStreamAlias("suspect_sex")
    private String suspect_sex;

    @XStreamAlias("suspect_birth")
    private String suspect_birth;

    @XStreamAlias("suspect_card")
    private String suspect_card;

    @XStreamAlias("suspect_rank")
    private String suspect_rank;

    @XStreamAlias("suspect_feature")
    private String suspect_feature;

    @XStreamAlias("suspect_case")
    private String suspect_case;

    @XStreamAlias("suspect_award")
    private String suspect_award;

    @XStreamAlias("suspect_fanzui")
    private String suspect_fanzui;

    @XStreamAlias("suspect_purl")
    private String suspect_purl;

    @XStreamAlias("pursuit_time")
    private String pursuit_time;

    @XStreamAlias("suspect_adress")
    private String suspect_adress;

    public String getSuspect_id() {
        return suspect_id;
    }

    public void setSuspect_id(String suspect_id) {
        this.suspect_id = suspect_id;
    }

    public String getSuspect_name() {
        return suspect_name;
    }

    public String getSuspect_sex() {
        return suspect_sex;
    }

    public void setSuspect_sex(String suspect_sex) {
        this.suspect_sex = suspect_sex;
    }

    public void setSuspect_name(String suspect_name) {
        this.suspect_name = suspect_name;
    }

    public String getSuspect_birth() {
        return suspect_birth;
    }

    public void setSuspect_birth(String suspect_birth) {
        this.suspect_birth = suspect_birth;
    }

    public String getSuspect_card() {
        return suspect_card;
    }

    public void setSuspect_card(String suspect_card) {
        this.suspect_card = suspect_card;
    }

    public String getSuspect_rank() {
        return suspect_rank;
    }

    public void setSuspect_rank(String suspect_rank) {
        this.suspect_rank = suspect_rank;
    }

    public String getSuspect_feature() {
        return suspect_feature;
    }

    public void setSuspect_feature(String suspect_feature) {
        this.suspect_feature = suspect_feature;
    }

    public String getSuspect_case() {
        return suspect_case;
    }

    public void setSuspect_case(String suspect_case) {
        this.suspect_case = suspect_case;
    }

    public String getSuspect_award() {
        return suspect_award;
    }

    public void setSuspect_award(String suspect_award) {
        this.suspect_award = suspect_award;
    }

    public String getSuspect_fanzui() {
        return suspect_fanzui;
    }

    public void setSuspect_fanzui(String suspect_fanzui) {
        this.suspect_fanzui = suspect_fanzui;
    }

    public String getSuspect_purl() {
        return suspect_purl;
    }

    public void setSuspect_purl(String suspect_purl) {
        this.suspect_purl = suspect_purl;
    }

    public String getPursuit_time() {
        return pursuit_time;
    }

    public void setPursuit_time(String pursuit_time) {
        this.pursuit_time = pursuit_time;
    }

    public String getSuspect_adress() {
        return suspect_adress;
    }

    public void setSuspect_adress(String suspect_adress) {
        this.suspect_adress = suspect_adress;
    }

    @Override
    public String toString() {
        return "SuspectInfo{" +
                "suspect_id='" + suspect_id + '\'' +
                ", suspect_name='" + suspect_name + '\'' +
                ", suspect_sex='" + suspect_sex + '\'' +
                ", suspect_birth='" + suspect_birth + '\'' +
                ", suspect_card='" + suspect_card + '\'' +
                ", suspect_rank='" + suspect_rank + '\'' +
                ", suspect_feature='" + suspect_feature + '\'' +
                ", suspect_case='" + suspect_case + '\'' +
                ", suspect_award='" + suspect_award + '\'' +
                ", suspect_fanzui='" + suspect_fanzui + '\'' +
                ", suspect_purl='" + suspect_purl + '\'' +
                ", pursuit_time='" + pursuit_time + '\'' +
                ", suspect_adress='" + suspect_adress + '\'' +
                '}';
    }
}
