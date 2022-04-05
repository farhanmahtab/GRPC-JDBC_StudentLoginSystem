package Student;

import com.project.grpc.register.Student;
import com.project.grpc.register.studentGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;
import java.util.logging.Logger ;

public class StudentClient {
    public static void main(String[] args) {

        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();

        studentGrpc.studentBlockingStub studentBlockingStub = new studentGrpc.studentBlockingStub(managedChannel);

        //Login Activation
        boolean authentication = false;
        Scanner input = new Scanner(System.in);
        while(!authentication){
            System.out.print("Enter Username: ");
            String name = input.next();
            System.out.print("Enter Password: ");
            String pass = input.next();

            Student.LoginRequest loginRequest = Student.LoginRequest.newBuilder().
                    setUserName(name).setPassword(pass).build();
            Student.Response response = studentBlockingStub.login(loginRequest);
            if(response.getResponseCode() == 200) {
                authentication = true;
            }
            System.out.println(response.getResponse());
        }

        //Registration
        System.out.print("Enter Registration N0.: ");
        long ID = input.nextLong();
        System.out.print("Enter  Username: ");
        String name = input.next();

        Student.RegisterRequest registerRequest = Student.RegisterRequest.newBuilder().
                setRegistrationID(ID).setStudentName(name).build();
        Student.RegResponse regResponse = studentBlockingStub.register(registerRequest);
        System.out.println(regResponse.getResponse());
    }
}
