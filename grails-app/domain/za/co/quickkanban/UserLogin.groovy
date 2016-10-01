package za.co.quickkanban

class UserLogin {
    String userId
    String password
    String fullName
    String emailAddress
    String cell
    boolean isDissabled
    boolean isAdministrator
    Date lastChanged

    static constraints = {
        userId(blank:false, nullable:false, unique:true, maxSize:15)
        password(blank:false, nullable:false, password:true, maxSize:15)
        fullName(blank:false, nullable:false, maxSize:25)
        cell(blank:false, nullable:false, maxSize:12)
        emailAddress(email: true,blank:false, nullable: false)
    }

    String toString(){return "${userId}:${fullName}"}
}
