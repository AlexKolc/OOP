import Exceptions.*;

public class Main {
    public static void main(String[] args) throws NoSuchParameterException, ConfigNotInitialisedException, SectionNotFoundException, FileNotFoundException {
        IniParser iniParser = new IniParser();
        iniParser.initialize("lab3.ini");
        System.out.println(iniParser);
        //System.out.println(iniParser);
        String checkString = iniParser.getString("COMMON", "DiskCachePath");
        System.out.println("String value = " + checkString);
        int checkInt = iniParser.getInteger("COMMON", "StatisterTimeMs");
        System.out.println("Integer value = " + checkInt);
        double checkDouble = iniParser.getDouble("ADC_DEV", "BufferLenSeconds");
        System.out.println("Double value = " + checkDouble);
    }
}
