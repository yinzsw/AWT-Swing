package top.yinzsw.dics.launch.pojo;

public class User {
    private int iTypeId;
    private String cTypeName;
    private int iUserId;
    private String vUserName;
    private String vUserPass;
    private String vUserRealName;

    public User() {
    }

    public User(int iTypeId, String cTypeName, int iUserId, String vUserName, String vUserPass, String vUserRealName) {
        this.iTypeId = iTypeId;
        this.cTypeName = cTypeName;
        this.iUserId = iUserId;
        this.vUserName = vUserName;
        this.vUserPass = vUserPass;
        this.vUserRealName = vUserRealName;
    }

    public int getiTypeId() {
        return iTypeId;
    }

    public void setiTypeId(int iTypeId) {
        this.iTypeId = iTypeId;
    }

    public String getcTypeName() {
        return cTypeName;
    }

    public void setcTypeName(String cTypeName) {
        this.cTypeName = cTypeName;
    }

    public int getiUserId() {
        return iUserId;
    }

    public void setiUserId(int iUserId) {
        this.iUserId = iUserId;
    }

    public String getvUserName() {
        return vUserName;
    }

    public void setvUserName(String vUserName) {
        this.vUserName = vUserName;
    }

    public String getvUserPass() {
        return vUserPass;
    }

    public void setvUserPass(String vUserPass) {
        this.vUserPass = vUserPass;
    }

    public String getvUserRealName() {
        return vUserRealName;
    }

    public void setvUserRealName(String vUserRealName) {
        this.vUserRealName = vUserRealName;
    }

    @Override
    public String toString() {
        return "User{" +
                "iTypeId=" + iTypeId +
                ", cTypeName='" + cTypeName + '\'' +
                ", iUserId=" + iUserId +
                ", vUserName='" + vUserName + '\'' +
                ", vUserPass='" + vUserPass + '\'' +
                ", vUserRealName='" + vUserRealName + '\'' +
                '}';
    }
}
