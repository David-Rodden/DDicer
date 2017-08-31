import node_structure.NFAHandler;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

@ScriptManifest(author = "Dungeonqueer", version = 0.1, info = "Gambles", name = "DDicer", logo = "what is this")
public class Main extends Script {
    private TimeOut timeOut;
    private NFAHandler nfaHandler;

    @Override
    public void onStart() throws InterruptedException {
        timeOut = new TimeOut(4);   //using a default seed of 4 without the use of a GUI
        nfaHandler = new NFAHandler(this);
        super.onStart();
    }

    @Override
    public int onLoop() throws InterruptedException {
        return timeOut.generateTimeout();
    }
}
