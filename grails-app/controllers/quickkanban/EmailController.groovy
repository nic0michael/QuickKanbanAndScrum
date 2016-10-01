package quickkanban

class EmailController {

    def index() { }
    def mailService

    def send(String eTo,String eFrom,String eSubject,String eBody){
        println("EMAILCONTROLLER:SENDMAIL")
        println("========================")
        try {
            mailService.sendMail {
                to eTo
                from eFrom
                subject  eSubject
                body eBody
            }
            println("Email sent!")
        } catch (Exception e) {
            println("Fsailed to send eMail with error "+e.message+" "+e.cause);
            println(e.stackTrace);
        }
    }
}
