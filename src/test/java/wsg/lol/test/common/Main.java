package wsg.lol.test.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main.
 *
 * @author Kingen
 */
public class Main {

    public static void main(String[] args) {
        String log = "2019-12-14 14:36:44 Delay-Scheduler-5 ERROR: Failed to handle Summoner event of 125176TnT.";
        String regex = "^([\\d-]{10})\\s([\\d:]{8})\\s([\\S]*)\\s([\\S]*):\\s\\S*$";
        String re = "^\\sERROR:\\s$";
        Pattern compile = Pattern.compile(re);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(log);
        System.out.println(matcher.find());
    }
}
