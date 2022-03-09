import com.intbyte.bdb.DataBuffer;
import com.intbyte.bdb.provider.HashKeyProvider;

public class Test {
    public static void main(String[] args) {
        DataBuffer dataBuffer = new DataBuffer(new HashKeyProvider());
        dataBuffer.putInt("hello", 100);
        dataBuffer.readBytes(dataBuffer.toBytes());
        dataBuffer.readBytes(dataBuffer.toBytes());
        System.out.println(dataBuffer.getInt("hello"));
    }
}
