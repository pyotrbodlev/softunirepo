package hell.entities.miscellaneous;

import hell.interfaces.Inventory;
import hell.interfaces.Item;
import hell.interfaces.Recipe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;

public class HeroInventoryTest {

    private Inventory heroIncentory;

    @Before
    public void setUp() throws Exception {
        this.heroIncentory = new HeroInventory();
    }

    @Test
    public void getTotalStrengthBonus() {
        Item mock = Mockito.mock(Item.class);
        Item mock1 = Mockito.mock(Item.class);
        Mockito.when(mock.getStrengthBonus()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(mock1.getStrengthBonus()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(mock.getName()).thenReturn("Item 1");
        Mockito.when(mock1.getName()).thenReturn("Item 2");

        this.heroIncentory.addCommonItem(mock);
        this.heroIncentory.addCommonItem(mock1);

        long expected = (long) Integer.MAX_VALUE + Integer.MAX_VALUE;
        long actual = this.heroIncentory.getTotalStrengthBonus();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getTotalAgilityBonus() {
        Item mock = Mockito.mock(Item.class);
        Item mock1 = Mockito.mock(Item.class);
        Mockito.when(mock.getAgilityBonus()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(mock1.getAgilityBonus()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(mock.getName()).thenReturn("Item 1");
        Mockito.when(mock1.getName()).thenReturn("Item 2");

        this.heroIncentory.addCommonItem(mock);
        this.heroIncentory.addCommonItem(mock1);

        long expected = (long) Integer.MAX_VALUE + Integer.MAX_VALUE;
        long actual = this.heroIncentory.getTotalAgilityBonus();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getTotalIntelligenceBonus() {
        Item mock = Mockito.mock(Item.class);
        Item mock1 = Mockito.mock(Item.class);
        Mockito.when(mock.getIntelligenceBonus()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(mock1.getIntelligenceBonus()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(mock.getName()).thenReturn("Item 1");
        Mockito.when(mock1.getName()).thenReturn("Item 2");

        this.heroIncentory.addCommonItem(mock);
        this.heroIncentory.addCommonItem(mock1);

        long expected = (long) Integer.MAX_VALUE + Integer.MAX_VALUE;
        long actual = this.heroIncentory.getTotalIntelligenceBonus();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getTotalHitPointsBonus() {
        Item mock = Mockito.mock(Item.class);
        Item mock1 = Mockito.mock(Item.class);
        Mockito.when(mock.getHitPointsBonus()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(mock1.getHitPointsBonus()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(mock.getName()).thenReturn("Item 1");
        Mockito.when(mock1.getName()).thenReturn("Item 2");

        this.heroIncentory.addCommonItem(mock);
        this.heroIncentory.addCommonItem(mock1);

        long expected = (long) Integer.MAX_VALUE + Integer.MAX_VALUE;
        long actual = this.heroIncentory.getTotalHitPointsBonus();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getTotalDamageBonus() {
        Item mock = Mockito.mock(Item.class);
        Item mock1 = Mockito.mock(Item.class);
        Mockito.when(mock.getDamageBonus()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(mock1.getDamageBonus()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(mock.getName()).thenReturn("Item 1");
        Mockito.when(mock1.getName()).thenReturn("Item 2");

        this.heroIncentory.addCommonItem(mock);
        this.heroIncentory.addCommonItem(mock1);

        long expected = (long) Integer.MAX_VALUE + Integer.MAX_VALUE;
        long actual = this.heroIncentory.getTotalDamageBonus();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addCommonItem() throws NoSuchFieldException, IllegalAccessException {
        Item mock = Mockito.mock(Item.class);
        this.heroIncentory.addCommonItem(mock);

        Field commonItems = HeroInventory.class.getDeclaredField("commonItems");
        commonItems.setAccessible(true);
        Map<String, Item> map = (Map<String, Item>) commonItems.get(this.heroIncentory);

        int expected = 1;
        int actual = map.size();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addRecipeItem() throws NoSuchFieldException, IllegalAccessException {
        Recipe mock = Mockito.mock(Recipe.class);
        Mockito.when(mock.getRequiredItems()).thenReturn(new ArrayList<>(){{add("Pesho");}});
        this.heroIncentory.addRecipeItem(mock);

        Field commonItems = HeroInventory.class.getDeclaredField("recipeItems");
        commonItems.setAccessible(true);
        Map<String, Item> map = (Map<String, Item>) commonItems.get(this.heroIncentory);

        int expected = 1;
        int actual = map.size();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addEmptyRecipeItem() throws NoSuchFieldException, IllegalAccessException {
        Recipe mock = Mockito.mock(Recipe.class);
        this.heroIncentory.addRecipeItem(mock);

        Field commonItems = HeroInventory.class.getDeclaredField("commonItems");
        commonItems.setAccessible(true);
        Map<String, Item> map = (Map<String, Item>) commonItems.get(this.heroIncentory);

        int expected = 1;
        int actual = map.size();

        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testCombineItems() throws NoSuchFieldException, IllegalAccessException {
        Item item1 = Mockito.mock(Item.class);
        Item item2 = Mockito.mock(Item.class);

        Mockito.when(item1.getName()).thenReturn("Item 1");
        Mockito.when(item2.getName()).thenReturn("Item 2");

        this.heroIncentory.addCommonItem(item1);
        this.heroIncentory.addCommonItem(item2);

        Recipe recipe = Mockito.mock(Recipe.class);
        Mockito.when(recipe.getRequiredItems()).thenReturn(new ArrayList<>(){{add("Item 1"); add("Item 2");}});

        this.heroIncentory.addRecipeItem(recipe);

        Field commonItems = HeroInventory.class.getDeclaredField("commonItems");
        commonItems.setAccessible(true);
        Map<String, Item> map = (Map<String, Item>) commonItems.get(this.heroIncentory);

        int expected = 1;
        int actual = map.size();

        Assert.assertEquals(expected, actual);
    }

}