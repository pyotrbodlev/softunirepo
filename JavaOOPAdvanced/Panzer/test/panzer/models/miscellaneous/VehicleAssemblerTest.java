package panzer.models.miscellaneous;

import org.junit.Before;
import org.junit.Test;
import panzer.contracts.Assembler;
import panzer.contracts.Part;
import panzer.contracts.Vehicle;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class VehicleAssemblerTest {

    private Assembler assembler;

    @Before
    public void before(){
        this.assembler = new VehicleAssembler();
    }

    @Test
    public void testFields() throws NoSuchFieldException, IllegalAccessException {
        List<Part> parts = new ArrayList<>();

        Field arsenalPartsField = VehicleAssembler.class.getDeclaredField("arsenalParts");
        arsenalPartsField.setAccessible(true);
        List<Part> listOfArsenalParts = (List<Part>) arsenalPartsField.get(this.assembler);

        Field shellPartsField = VehicleAssembler.class.getDeclaredField("shellParts");
        shellPartsField.setAccessible(true);
        List<Part> listOfShellParts = (List<Part>) shellPartsField.get(this.assembler);

        Field endurancePartsField = VehicleAssembler.class.getDeclaredField("enduranceParts");
        endurancePartsField.setAccessible(true);
        List<Part> listOfEndurancePartsField = (List<Part>) endurancePartsField.get(this.assembler);

        parts.addAll(listOfArsenalParts);
        parts.addAll(listOfShellParts);
        parts.addAll(listOfEndurancePartsField);
    }

    @Test
    public void test() throws NoSuchFieldException {
        Field[] fields = VehicleAssembler.class.getDeclaredFields();

        for (Field field : fields) {
            System.out.println(field.toString());
        }

    }

}