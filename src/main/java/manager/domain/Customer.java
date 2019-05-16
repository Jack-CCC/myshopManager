package manager.domain;

public class Customer {

    private String id;
    private Boolean isVip;
    private String username;

    public Customer() {
    }

    public Customer(String id, Boolean isVip, String username) {
        this.id = id;
        this.isVip = isVip;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIsVip() {
        return isVip;
    }

    public void setIsVip(String isVip) {
        if(isVip.equals("true")){
            this.isVip=true;
        }else{
            this.isVip = false;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
