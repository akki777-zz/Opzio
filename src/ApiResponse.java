public class ApiResponse {
    public Data result = new Data();
    public int errFlag;

    public static class Data {
        public Inner data = new Inner();
    }

    public static class Inner {
        public String firstName;
    }
}
