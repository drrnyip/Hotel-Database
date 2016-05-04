import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.OutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javafx.scene.control.TextArea;

/**
 * Created by Darren on 5/4/2016.
 */
public class Systemout extends OutputStream{

        private TextArea output;

        public Systemout(TextArea ta)
        {
            this.output = ta;
        }

        @Override
        public void write(int i) throws IOException
        {
            output.appendText(String.valueOf((char) i));
        }
}
