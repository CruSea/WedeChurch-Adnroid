package com.gcme.wedechurch.model;

import com.orm.SugarRecord;

/**
 * Created by kzone on 5/8/2017.
 */

public class Denomination extends SugarRecord<Denomination> {
    public Denomination(int denoid,int denoChurchs, String denominationName, String denoImageUrl,String denoEmail,String denoPhone) {
        this.denoId = denoid;
        this.denoChurchs = denoChurchs;
        this.denominationName = denominationName;
        this.denoImageUrl = denoImageUrl;
        this.denoemail = denoEmail;
        this.denophone = denoPhone;
    }

    public int getDenoId() {
        return denoId;
    }

    public void setDenoId(int denoId) {
        this.denoId = denoId;
    }

    public int getDenoChurchs() {
        return denoChurchs;
    }

    public void setDenoChurchs(int denoChurchs) {
        this.denoChurchs = denoChurchs;
    }

    public String getDenominationName() {
        return denominationName;
    }

    public void setDenominationName(String denominationName) {
        this.denominationName = denominationName;
    }

    public String getDenoImageUrl() {
        return denoImageUrl;
    }

    public String getDenoemail() {
        return denoemail;
    }

    public void setDenoemail(String denoemail) {
        this.denoemail = denoemail;
    }

    public String getDenophone() {
        return denophone;
    }

    public void setDenophone(String denophone) {
        this.denophone = denophone;
    }

    public void setDenoImageUrl(String denoImageUrl) {
        this.denoImageUrl = denoImageUrl;
    }

    int denoId,denoChurchs;
    String denominationName,denoImageUrl,denoemail,denophone;
}
