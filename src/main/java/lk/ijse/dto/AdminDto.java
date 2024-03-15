package lk.ijse.dto;

public class AdminDto {
    private String adminId;
    private String userName;
    private String password;
    private String confirmPassword;

    public AdminDto() {
    }

    public AdminDto(String adminId, String userName, String password, String confirmPassword) {
        this.adminId = adminId;
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public AdminDto(String userName, String password, String confirmPassword) {
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public AdminDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "AdminDto{" +
                "adminId='" + adminId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
