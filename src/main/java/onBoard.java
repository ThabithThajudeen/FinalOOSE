import org.example.Commuter;
import org.example.OnboardState;

public class onBoard extends OnboardState {
    @Override
    public void handleState(Commuter commuter) {
        System.out.println("Passenger is OnBoard");
    }

}
