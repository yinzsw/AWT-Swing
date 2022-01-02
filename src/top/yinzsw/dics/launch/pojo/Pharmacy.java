package top.yinzsw.dics.launch.pojo;

public class Pharmacy {
    private int iPharmacyId;
    private String vPharmacyName;
    private String vPharmacyDescription;

    public Pharmacy() {
    }

    public Pharmacy(int iPharmacyId, String vPharmacyName, String vPharmacyDescription) {
        this.iPharmacyId = iPharmacyId;
        this.vPharmacyName = vPharmacyName;
        this.vPharmacyDescription = vPharmacyDescription;
    }

    public int getiPharmacyId() {
        return iPharmacyId;
    }

    public void setiPharmacyId(int iPharmacyId) {
        this.iPharmacyId = iPharmacyId;
    }

    public String getvPharmacyName() {
        return vPharmacyName;
    }

    public void setvPharmacyName(String vPharmacyName) {
        this.vPharmacyName = vPharmacyName;
    }

    public String getvPharmacyDescription() {
        return vPharmacyDescription;
    }

    public void setvPharmacyDescription(String vPharmacyDescription) {
        this.vPharmacyDescription = vPharmacyDescription;
    }

    @Override
    public String toString() {
        return "Pharmacy{" +
                "iPharmacyId=" + iPharmacyId +
                ", vPharmacyName='" + vPharmacyName + '\'' +
                ", vPharmacyDescription='" + vPharmacyDescription + '\'' +
                '}';
    }
}
