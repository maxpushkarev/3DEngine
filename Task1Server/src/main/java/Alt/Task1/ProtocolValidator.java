package Alt.Task1;

import java.util.regex.Pattern;

public class ProtocolValidator
{
    private ProtocolValidator() {
        throw new AssertionError();
    }
    
    private static Pattern pattern = Pattern.compile(
    		"[0-9]+;([\\-\\+]?[0-9]*(\\.[0-9]+)?,){2}[\\-\\+]?[0-9]*(\\.[0-9]+)?;([\\-\\+]?[0-9]*(\\.[0-9]+)?,){2}[\\-\\+]?[0-9]*(\\.[0-9]+)?"
    		);
    
    public static boolean CheckMessage(String input)
    {
		return pattern.matcher(input).matches();
    }
}