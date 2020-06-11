package com.example.washing_machine;

public class Users {

    String Name,Ldap,Wing,Room,Phone;

    public Users() {
    }

    public Users(String Name,String Ldap,String Wing,String Room,String Phone) {
        this.Name=Name;
        this.Ldap=Ldap;
        this.Wing=Wing;
        this.Room=Room;
        this.Phone=Phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLdap() {
        return Ldap;
    }

    public void setLdap(String ldap) {
        Ldap = ldap;
    }

    public String getWing() {
        return Wing;
    }

    public void setWing(String wing) {
        Wing = wing;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
