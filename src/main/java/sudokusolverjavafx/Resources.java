package sudokusolverjavafx;

public class Resources {


    public static String get(String fileName){


        return Resources.class.getClassLoader().getResource(fileName).toExternalForm();


    }


}
