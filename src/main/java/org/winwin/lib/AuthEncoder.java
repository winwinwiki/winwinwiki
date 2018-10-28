package org.winwin.lib;

public class AuthEncoder {
	
	public static final String AUTH_HEADER = "user-auth-id";
	
	private static long BASE = 1000000;
	
	public static String encode(Long index) {
		Long time = System.currentTimeMillis();
		Long value = time*BASE + index;
		return Long.toString(value,36);
	}
	
	private static Long decodeValue(String s) {
		return Long.valueOf(s, 36)%BASE;
	}

	private static Long decodeValue(String s, Long limits) {
		long value = Long.valueOf(s, 36);
		long timevalue = value/BASE;
		Long time = System.currentTimeMillis();
		Long timeDiff = (time - timevalue );
		if( timeDiff > 0 && timeDiff <limits ) {
			return decodeValue(s);
		}
		throw new RuntimeException("Cookie incorrect. Refresh your authentication");
	}
	
	public static Long validateAuthHeader(String authHeader) {
		Long timeValue = 2L * 3600 * 1000; 
		return decodeValue(authHeader,timeValue);
	}
	
	public static void main(String[] args) {
		System.out.println(AuthEncoder.encode(1L));
	}
	
}
