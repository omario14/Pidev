package tn.esprit.spring.entities;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class SMS {
	
	public static final String ACCOUNT_SID = "AC696cc20dd45129f670d302766f548f9a";
    public static final String AUTH_TOKEN = "b83426d4d9afddc23938be82def7ef09";
    String message="hellow";
    String phone="+21695592018";
    public SMS(String message,String phone) {
    	this.message=message;
    	this.phone=phone;
		// TODO Auto-generated constructor stub
	}
	
	public  void send() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message messageToSend = Message.creator(
                new com.twilio.type.PhoneNumber(phone),
                new com.twilio.type.PhoneNumber("+12526658743"),
                message)
            .create();

        System.out.println(messageToSend.getSid());
    }

}
