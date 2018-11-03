import Exceptions.*;
import Exceptions.NumberFormatException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws NoSuchParameterException, ConfigNotInitialisedException, SectionNotFoundException, FileNotFoundException, IOException, NumberFormatException {
        Ini ini = new Ini("lab3.ini");
        System.out.println(ini);
        String checkString = ini.getString("COMMON", "DiskCachePath");
        System.out.println("String value = " + checkString);
        int checkInt = ini.getInteger("COMMON", "StatisterTimeMs");
        System.out.println("Integer value = " + checkInt);
        double checkDouble = ini.getDouble("ADC_DEV", "BufferLenSeconds");
        System.out.println("Double value = " + checkDouble);
    }
}
