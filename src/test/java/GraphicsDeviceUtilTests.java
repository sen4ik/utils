import lombok.extern.slf4j.Slf4j;
import org.sen4ik.utils.GraphicsDeviceUtil;
import org.testng.annotations.Test;

import java.awt.*;

import static org.testng.Assert.assertNull;

@Slf4j
public class GraphicsDeviceUtilTests {

    @Test
    public void getDisplayBounds(){
        Rectangle db = GraphicsDeviceUtil.getDisplayBounds(0);
        log.info(db.toString());
    }

    @Test
    public void getDisplayBounds_negative(){
        Rectangle db = GraphicsDeviceUtil.getDisplayBounds(1);
        assertNull(db);
    }

}
