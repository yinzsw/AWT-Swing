package top.yinzsw.dics.launch.pojo;

import java.util.Date;

public class MedicineOutput {
    private int iOutputId;
    private int iMedicineId;
    private int iQuantity;
    private int iPharmacyId;
    private Date dOutputDate;
    private String vMedicineName;
    private String vPharmacyName;

    public MedicineOutput() {
    }

    public MedicineOutput(int iOutputId, int iMedicineId, int iQuantity, int iPharmacyId, Date dOutputDate, String vMedicineName, String vPharmacyName) {
        this.iOutputId = iOutputId;
        this.iMedicineId = iMedicineId;
        this.iQuantity = iQuantity;
        this.iPharmacyId = iPharmacyId;
        this.dOutputDate = dOutputDate;
        this.vMedicineName = vMedicineName;
        this.vPharmacyName = vPharmacyName;
    }

    public int getiOutputId() {
        return iOutputId;
    }

    public void setiOutputId(int iOutputId) {
        this.iOutputId = iOutputId;
    }

    public int getiMedicineId() {
        return iMedicineId;
    }

    public void setiMedicineId(int iMedicineId) {
        this.iMedicineId = iMedicineId;
    }

    public int getiQuantity() {
        return iQuantity;
    }

    public void setiQuantity(int iQuantity) {
        this.iQuantity = iQuantity;
    }

    public int getiPharmacyId() {
        return iPharmacyId;
    }

    public void setiPharmacyId(int iPharmacyId) {
        this.iPharmacyId = iPharmacyId;
    }

    public Date getdOutputDate() {
        return dOutputDate;
    }

    public void setdOutputDate(Date dOutputDate) {
        this.dOutputDate = dOutputDate;
    }

    public String getvMedicineName() {
        return vMedicineName;
    }

    public void setvMedicineName(String vMedicineName) {
        this.vMedicineName = vMedicineName;
    }

    public String getvPharmacyName() {
        return vPharmacyName;
    }

    public void setvPharmacyName(String vPharmacyName) {
        this.vPharmacyName = vPharmacyName;
    }

    @Override
    public String toString() {
        return "MedicineOutput{" +
                "iOutputId=" + iOutputId +
                ", iMedicineId=" + iMedicineId +
                ", iQuantity=" + iQuantity +
                ", iPharmacyId=" + iPharmacyId +
                ", dOutputDate=" + dOutputDate +
                ", vMedicineName='" + vMedicineName + '\'' +
                ", vPharmacyName='" + vPharmacyName + '\'' +
                '}';
    }
}
