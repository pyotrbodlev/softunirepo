package panzer.factories;

import panzer.contracts.Part;
import panzer.models.parts.ArsenalPart;
import panzer.models.parts.EndurancePart;
import panzer.models.parts.ShellPart;

import java.math.BigDecimal;
import java.util.List;

public final class PartFactory {

    public static Part createPart(List<String> args){
        String partType = args.get(2);

        String model = args.get(3);
        double weight = Double.parseDouble(args.get(4));
        BigDecimal price = BigDecimal.valueOf(Long.parseLong(args.get(5)));
        int additionalParameter = Integer.parseInt(args.get(6));

        switch (partType){
            case "Arsenal":
                return new ArsenalPart(model, weight, price, additionalParameter);
            case "Endurance":
                return new EndurancePart(model, weight, price, additionalParameter);
            case "Shell":
                return new ShellPart(model, weight, price, additionalParameter);
        }

        return null;
    }
}
