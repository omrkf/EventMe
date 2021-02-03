/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Asano
 */
public class Event {

    private int evid;
    private String evName;
    private int evCode;
    private String evDateStart;
    private String evDateEnd;
    private String subCertif;
    private String textCertif;
    private String textAttend;
    private String subAttend;

    public Event() {
    }

    public int getEvid() {
        return evid;
    }

    public void setEvid(int evid) {
        this.evid = evid;
    }

    public String getEvName() {
        return evName;
    }

    public void setEvName(String evName) {
        this.evName = evName;
    }

    public int getEvCode() {
        return evCode;
    }

    public void setEvCode(int evCode) {
        this.evCode = evCode;
    }

    public String getEvDateStart() {
        return evDateStart;
    }

    public void setEvDateStart(String evDateStart) {
        this.evDateStart = evDateStart;
    }

    public String getEvDateEnd() {
        return evDateEnd;
    }

    public void setEvDateEnd(String evDateEnd) {
        this.evDateEnd = evDateEnd;
    }

    public String getSubCertif() {
        return subCertif;
    }

    public void setSubCertif(String subCertif) {
        this.subCertif = subCertif;
    }

    public String getTextCertif() {
        return textCertif;
    }

    public void setTextCertif(String textCertif) {
        this.textCertif = textCertif;
    }

    public String getTextAttend() {
        return textAttend;
    }

    public void setTextAttend(String textAttend) {
        this.textAttend = textAttend;
    }

    public String getSubAttend() {
        return subAttend;
    }

    public void setSubAttend(String subAttend) {
        this.subAttend = subAttend;
    }

    @Override
    public String toString() {
        return "Event{" + ""
                + "evid=" + evid + ",\n"
                + " evName=" + evName + ",\n"
                + " evCode=" + evCode + ",\n"
                + " evDateStart=" + evDateStart + ",\n"
                + " evDateEnd=" + evDateEnd + ",\n"
                + " subCertif=" + subCertif + ",\n"
                + " textCertif=" + textCertif + ",\n"
                + " textAttend=" + textAttend + ",\n"
                + " subAttend=" + subAttend + "}\n\n";
    }

}
