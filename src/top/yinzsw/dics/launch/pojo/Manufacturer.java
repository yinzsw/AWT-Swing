package top.yinzsw.dics.launch.pojo;

public class Manufacturer {
    private int iFacturerId;
    private String vFacturerName;
    private String cContanctMan;
    private String cContanctPhone;
    private String cContanctAddress;
    private String vDescription;
    private String cPostalCode;
    private String cSimplifiedCode;
    private String vBusinessScope;

    public Manufacturer() {
    }

    public Manufacturer(int iFacturerId, String vFacturerName, String cContanctMan, String cContanctPhone, String cContanctAddress, String vDescription, String cPostalCode, String cSimplifiedCode, String vBusinessScope) {
        this.iFacturerId = iFacturerId;
        this.vFacturerName = vFacturerName;
        this.cContanctMan = cContanctMan;
        this.cContanctPhone = cContanctPhone;
        this.cContanctAddress = cContanctAddress;
        this.vDescription = vDescription;
        this.cPostalCode = cPostalCode;
        this.cSimplifiedCode = cSimplifiedCode;
        this.vBusinessScope = vBusinessScope;
    }

    public int getiFacturerId() {
        return iFacturerId;
    }

    public void setiFacturerId(int iFacturerId) {
        this.iFacturerId = iFacturerId;
    }

    public String getvFacturerName() {
        return vFacturerName;
    }

    public void setvFacturerName(String vFacturerName) {
        this.vFacturerName = vFacturerName;
    }

    public String getcContanctMan() {
        return cContanctMan;
    }

    public void setcContanctMan(String cContanctMan) {
        this.cContanctMan = cContanctMan;
    }

    public String getcContanctPhone() {
        return cContanctPhone;
    }

    public void setcContanctPhone(String cContanctPhone) {
        this.cContanctPhone = cContanctPhone;
    }

    public String getcContanctAddress() {
        return cContanctAddress;
    }

    public void setcContanctAddress(String cContanctAddress) {
        this.cContanctAddress = cContanctAddress;
    }

    public String getvDescription() {
        return vDescription;
    }

    public void setvDescription(String vDescription) {
        this.vDescription = vDescription;
    }

    public String getcPostalCode() {
        return cPostalCode;
    }

    public void setcPostalCode(String cPostalCode) {
        this.cPostalCode = cPostalCode;
    }

    public String getcSimplifiedCode() {
        return cSimplifiedCode;
    }

    public void setcSimplifiedCode(String cSimplifiedCode) {
        this.cSimplifiedCode = cSimplifiedCode;
    }

    public String getvBusinessScope() {
        return vBusinessScope;
    }

    public void setvBusinessScope(String vBusinessScope) {
        this.vBusinessScope = vBusinessScope;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "iFacturerId=" + iFacturerId +
                ", vFacturerName='" + vFacturerName + '\'' +
                ", cContanctMan='" + cContanctMan + '\'' +
                ", cContanctPhone='" + cContanctPhone + '\'' +
                ", cContanctAddress='" + cContanctAddress + '\'' +
                ", vDescription='" + vDescription + '\'' +
                ", cPostalCode='" + cPostalCode + '\'' +
                ", cSimplifiedCode='" + cSimplifiedCode + '\'' +
                ", vBusinessScope='" + vBusinessScope + '\'' +
                '}';
    }
}
