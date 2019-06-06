package Tests;

import Controller.Controller;
import Model.Ship;
import Model.WarField;
import Model.WarFieldHandler;
import View.ScorePanel;
import junit.framework.TestCase;
import Model.Base;
import org.junit.Assert;

import static org.easymock.EasyMock.mock;

public class ModelTests extends TestCase {

  public void testBaseGettingHP() {
    Base testBase = new Base(100);
    testBase.setDisplayPanel(mock(ScorePanel.class));
    Assert.assertEquals(100, testBase.getHp());
  }

  public void testBaseSettingHP() {
    Base testBase = new Base(100);
    testBase.setDisplayPanel(mock(ScorePanel.class));
    testBase.setHP(90);
    Assert.assertEquals(90, testBase.getHp());
  }

  public void testBaseLosingHP() {
    Base testBase = new Base(100);
    testBase.setDisplayPanel(mock(ScorePanel.class));
    testBase.takeDamage(2);
    Assert.assertEquals(98, testBase.getHp());
  }

  public void testBaseGettingDestroyed() {
    Base testBase = new Base(2);
    testBase.setDisplayPanel(mock(ScorePanel.class));
    testBase.setController(mock(Controller.class));
    testBase.takeDamage(2);
    Assert.assertEquals(0, testBase.getHp());
  }

  public void testBaseRegeneratingHP() {
    Base testBase = new Base(100);
    testBase.setDisplayPanel(mock(ScorePanel.class));
    testBase.setHP(90);
    testBase.regenerate(10);
    Assert.assertEquals(100, testBase.getHp());
  }

  public void testBaseRegeneratingToFullHP() {
    Base testBase = new Base(100);
    testBase.setDisplayPanel(mock(ScorePanel.class));
    testBase.setHP(90);
    testBase.regenerate(1000);
    Assert.assertEquals(100, testBase.getHp());
  }

  public void testShipTakingDmg() {
    Ship enemy = new Ship(2, 2, 2, 2, mock(Base.class));
    enemy.destroyArmor(2);
    Assert.assertEquals(0, enemy.getArmor());
  }

  public void testGettingShipRow() {
    Ship enemy = new Ship(2, 3, 2, 2, mock(Base.class));
    Assert.assertEquals(3, enemy.getRow());
  }

  public void testGettingShipColumn() {
    Ship testEnemy = new Ship(2, 2, 3, 2, mock(Base.class));
    Assert.assertEquals(3, testEnemy.getColumn());
  }

  public void testShipTimer() {
    Base testBase = new Base(100);
    Ship testEnemy = new Ship(2, 2, 3, 2, testBase);
    testEnemy.startTimer();
    Assert.assertNotNull(testEnemy.getTimer());
    Assert.assertNotNull(testEnemy.getTimer().getActionListeners());
    testEnemy.stopTimer();
    Assert.assertNull(testEnemy.getTimer());
  }

  public void testRowFindingWhenNoRowsAreFree() {
    WarField testField = new WarField(1, 0);
    Assert.assertEquals(-1, testField.findRow());
  }

  public void testRowFinding() {
    WarField testField = new WarField(1, 1);
    Assert.assertEquals(0, testField.findRow());
  }

  public void testRowSaving() {
    WarField testField = new WarField(2, 2);
    testField.saveRow(0);
    Assert.assertTrue(testField.checkRow(0));
  }

  public void testColumnSaving() {
    WarField testField = new WarField(1, 1);
    testField.saveColumn(0);
    Assert.assertTrue(testField.checkColumn(0, 0));
  }

  public void testRowReturning() {
    WarField testField = new WarField(2, 2);
    testField.saveRow(0);
    testField.returnRow(0);
    Assert.assertTrue(testField.checkColumn(0, -1));
  }

  public void testRowChecking() {
    WarField testField = new WarField(2, 2);
    Assert.assertTrue(testField.checkColumn(0, -1) && testField.checkColumn(1, -1));
  }

  public void testShipCreatingWhenNoRowsAreFree(){
    WarFieldHandler testHandler = new WarFieldHandler(new WarField(2, 0), mock(Base.class));
    Assert.assertNull(testHandler.setShip(2, 2));
  }

  public void testShipCreating() {
    WarFieldHandler testHandler = new WarFieldHandler(new WarField(2, 2), mock(Base.class));
    Ship testEnemy = testHandler.setShip(2, 2);
    Assert.assertNotNull(testEnemy);
    Assert.assertTrue(testEnemy.getArmor() == 2 && testEnemy.getFirepower() == 2);
    Assert.assertTrue(testEnemy.getRow() >= 0 && testEnemy.getRow() < 2);
    Assert.assertTrue(testEnemy.getColumn() >= 0 && testEnemy.getRow() < 2);
  }

  public void testBaseGetting() {
    Base testBase = new Base(100);
    WarFieldHandler testHandler = new WarFieldHandler(mock(WarField.class), testBase);
    Assert.assertEquals(testBase, testHandler.getBase());
  }
}