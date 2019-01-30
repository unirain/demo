package jdk8.lamda;

class Lambda4 {
    public static int outerStaticNum;
    public  int outerNum;

    public void testScopes() {
        Converter<Integer, String> stringConverter1 = (from) -> {
            outerNum = 23;
            return String.valueOf(from);
        };

        Converter<Integer, String> stringConverter2 = (from) -> {
            outerStaticNum = 72;
            return String.valueOf(from);
        };
    }
    public  class test implements Converter<Integer,String>{
        @Override
        public String convert(Integer from) {
            outerStaticNum=77;
            return String.valueOf(from);
        }
    }
    int startsWith(String s) {
        return 1;
    }
}