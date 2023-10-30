package pl.edu.uwm.student.aleksiejczyk.bartlomiej;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class HeaderUtils {
    public static String[] replaceKeys(String[] headerData, Map<String, String> namespaceMap){
        return Arrays.stream(headerData)
                .map(header -> namespaceMap.getOrDefault(header, header))
                .toArray(String[]::new);
    }
}
