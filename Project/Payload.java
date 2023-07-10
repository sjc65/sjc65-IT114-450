package Project;
import java.io.Serializable;
//----------------
import java.text.SimpleDateFormat;
import java.util.Date;
//----------------
public class Payload implements Serializable {
    //read https://www.baeldung.com/java-serial-version-uid
    private static final long serialVersionUID = 1L;//change this if the class changes
    

    /**
     * Determines how to process the data on the receiver's side
     */
    private PayloadType payloadType;
    public PayloadType getPayloadType() {
        return payloadType;
    }
    public void setPayloadType(PayloadType payloadType) {
        this.payloadType = payloadType;
    }

    /**
     * Who the payload is from
     */
    private String clientName;
    public String getClientName() {
        return clientName;
    }
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * Generic text based message
     */
    private String message;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * Generic number for example sake
     */
    private int number;
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
//---------------------------------------------------------------------------
    /**
     * Displays time stamp of the message
     */
    private long timeStamp;
    private String format;
    public String getStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(timeStamp);
        format = sdf.format(date);
        return format;
    }
    public void setStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
//----------------------------------------------------------------------------
    @Override
    public String toString() {
	return String.format("Type[%s], Number[%s], Message[%s], Timestamp[%s]", getPayloadType().toString(), getNumber(),
		getMessage(), getStamp());
    }
}
