import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
try {            
Registry registry = LocateRegistry.getRegistry("localhost");

            Hello stub = (Hello) registry.lookup("HelloService");

            System.out.println(stub.sayHello());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}