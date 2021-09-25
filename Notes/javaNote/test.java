package javaNote;

import java.util.logging.Logger;

public class test {
   static final Logger logger  = Logger.getLogger(test.class.getName());
    public static void main(String[] args) throws Exception{
        String s = "Hi %s, your score is %d!";
        assert s == null;
        logger.info(String.format(s,"mars",99));
    }
}
