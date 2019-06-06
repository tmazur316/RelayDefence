package Tests;

import Controller.Controller;
import View.View;
import junit.framework.TestCase;
import org.junit.Assert;

import javax.swing.*;

import static org.easymock.EasyMock.mock;

public class ControllerTests extends TestCase{

  public void testSettingTimers(){
    Controller testController = new Controller(mock(View.class));
    testController.startTimers();
    for(Timer t: testController.getTimers()){
      Assert.assertNotNull(t);
      Assert.assertNotNull(t.getActionListeners());
    }
  }

  public void testStoppingTimers() {
    Controller testController = new Controller(mock(View.class));
    testController.startTimers();
    testController.stopAssault();
    for (Timer t : testController.getTimers()) {
      Assert.assertNull(t);
    }
  }
}
