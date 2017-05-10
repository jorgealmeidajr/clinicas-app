package clinicas.utils;

import java.security.MessageDigest;

public abstract class CriptografiaUtils {
	
	private CriptografiaUtils() {
		
	}

	public static String converterStringParaMD5(String valor) {
		String resultado = "";
		
		try {
			// Instanciamos o nosso HASH MD5
			MessageDigest digest = MessageDigest.getInstance("MD5");

			// Converter String para um array de bytes em MD5
			byte[] valorMD5 = digest.digest(valor.getBytes("UTF-8"));

			// Convertemos os bytes para hexadecimal
			StringBuilder sb = new StringBuilder();
			for (byte b : valorMD5) {
				sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
			}

			resultado = sb.toString();

		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return resultado;
	}
	
}
