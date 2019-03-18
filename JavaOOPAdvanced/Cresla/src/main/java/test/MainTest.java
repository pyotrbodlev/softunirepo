package test;

import cresla.entities.reactors.CryoReactor;

import java.util.HashMap;
import java.util.Map;

public class MainTest {
    public static void main(String[] args) {
        Map<Integer, CryoReactor> cryoReactorMap = new HashMap<>();

        cryoReactorMap.put(1, new CryoReactor(10, 1));
        cryoReactorMap.put(2, new CryoReactor(10, 1));
        cryoReactorMap.put(3, new CryoReactor(10, 1));
        cryoReactorMap.put(4, new CryoReactor(10, 1));

        CryoReactor cryoReactor = cryoReactorMap.get(1);
        cryoReactor = new CryoReactor(10 ,3);


    }
}
