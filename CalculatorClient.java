import CalcModule.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;

public class CalculatorClient {
    public static void main(String args[]) {
        try {
            ORB orb = ORB.init(args, null);

            org.omg.CORBA.Object objRef =
                    orb.resolve_initial_references("NameService");

            NamingContextExt ncRef =
                    NamingContextExtHelper.narrow(objRef);

            Calculator calcObj =
                    CalculatorHelper.narrow(
                            ncRef.resolve_str("Calculator"));

            System.out.println("Addition: " + calcObj.add(10, 5));
            System.out.println("Subtraction: " + calcObj.sub(10, 5));
            System.out.println("Multiplication: " + calcObj.mul(10, 5));
            System.out.println("Division: " + calcObj.div(10, 5));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}