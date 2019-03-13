package hell.entities.heroes;


import hell.entities.items.CommonItem;
import hell.interfaces.Hero;
import hell.interfaces.Item;
import hell.interfaces.Manager;
import hell.manager.ProgramManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class BaseHeroTest {

    private Hero hero;
    private Manager manager;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() throws Exception {
        this.hero = new Assassin("Pesho");
        Hero hero1 = new Barbarian("Ivan");
        this.manager = new ProgramManager();
        Field[] declaredFields = ProgramManager.class.getDeclaredFields();

        declaredFields[1].setAccessible(true);
        Map<String, Hero> map = (Map<String, Hero>) declaredFields[1].get(this.manager);
        map.put("Pesho", this.hero);
        map.put("Ivan", hero1);
        declaredFields[1].setAccessible(false);
    }

    @Test
    public void getItems() throws IllegalAccessException {
        Assert.assertEquals(0, this.hero.getItems().size());
    }

    @Test
    public void testAddingItem() throws IllegalAccessException {
        Item mockItem = Mockito.mock(CommonItem.class);
        Mockito.when(mockItem.getAgilityBonus()).thenReturn(100);
        this.hero.addItem(mockItem);


        Assert.assertEquals(200, this.hero.getAgility());
    }

    @Test
    public void checkRecipes() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<String> commands = Arrays.asList("Recipe Pesho 0 0 0 0 0 Item3 Item2".split(" "));
        String recipeCommand = (String) ProgramManager.class.getMethod("recipeCommand", List.class).invoke(this.manager, commands);

        List<String> item1Args = Arrays.asList("Item1 Pesho 1 1 1 1 1".split(" "));
        List<String> item2Args = Arrays.asList("Item2 Pesho 1 1 1 1 1".split(" "));
        List<String> item3Args = Arrays.asList("Item3 Pesho 1 1 1 1 1".split(" "));

        String item1Command = (String) ProgramManager.class.getMethod("itemCommand", List.class).invoke(this.manager, item1Args);
        String item2Command = (String) ProgramManager.class.getMethod("itemCommand", List.class).invoke(this.manager, item2Args);
        String item3Command = (String) ProgramManager.class.getMethod("itemCommand", List.class).invoke(this.manager, item3Args);

        System.out.println(item1Command);
        System.out.println(item2Command);
        System.out.println(item3Command);

        Collection<Item> items = this.hero.getItems();
        Assert.assertEquals(2, items.size());
    }

    @Test
    public void testTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<String> commands2 = Arrays.asList("Item Knife Ivan 0 10 0 0 30".split(" "));
        List<String> commands3 = Arrays.asList("Item Stick Ivan 0 0 10 0 5".split(" "));
        List<String> commands4 = Arrays.asList("Recipe Spear Ivan 25 10 10 100 50 Knife Stick".split(" "));
        List<String> commands5 = Arrays.asList("Inspect Ivan".split(" "));
        List<String> commands6 = Arrays.asList("Inspect Pesho".split(" "));

        System.out.println((String) ProgramManager.class.getMethod("itemCommand", List.class).invoke(this.manager, commands2));
        System.out.println((String) ProgramManager.class.getMethod("itemCommand", List.class).invoke(this.manager, commands3));
        System.out.println((String) ProgramManager.class.getMethod("recipeCommand", List.class).invoke(this.manager, commands4));
        System.out.println((String) ProgramManager.class.getMethod("inspectCommand", List.class).invoke(this.manager, commands5));
        System.out.println((String) ProgramManager.class.getMethod("inspectCommand", List.class).invoke(this.manager, commands6));

    }
}