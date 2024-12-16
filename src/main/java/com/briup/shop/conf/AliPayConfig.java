package com.briup.shop.conf;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

public class AliPayConfig {
	/*
	 * 服务网关  ---  沙箱环境
	 */

	private static String serverURL = "https://openapi.alipaydev.com/gateway.do";
	
	/*
	 * 应用id,
	 */

	private static String APP_ID = "2016101600701331";
	
	/*
	 * 用户私钥,可以替换成你们的自己的私钥
	 * 
	 */

	private static String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCRDoIg1vR+WDTYY2Y6dPE1GQGqHEbqASCJDxWnwMrMXJ7mPn9LanFzGODkxlkemh/R53lnoMdBJqlxEnvLWeaSjkl3aIAzKqcI2xOcybDsPM8GolP+KAPwy/Ryk6/r1hZb6Fa6zOXLf7nMGjA8+Hb7jzqHa5d2fwmDcV5oA4Yv0R+TS3LDiv7GjTt9A2LJdtf5v7LXja3IF1OBqayaaxc8ei293mW5Qtq6C4GnwIAS/kHffWkMLECaaKIjsCkmRj4f6p5xdCA9ixEtpXFIWiAGxdllOCn1FSLv2qoMyVBy5ON6ooAk08lfpSpF3Ta8NqRf91cs6+k5hgyQI5ijdi2zAgMBAAECggEAQOirFiLZ+QdjbkjcX+AgZ1AvTH2mxeOHOY2EdTJyDQyNFh51O7v/1C8Xw4d2LZluD7FyxApP3zl7rNtGhfJOmNoOyd37L+owIGhX2FrwTmXPhfpxd1NUoBXD5tlraS69hpbq6F/znCIlc81sNHxCUcL/v8VHW4DxMt4yDn01OIPAmxOasxOnxxp5w7pINSD7LeUm8f3zemcDoia6mQtVLv7//7Av+lAKQw1jKGti7Oqx+UBaymxi/OI9jNYF2GgqhOPaO6bntxRidOsnkCS+dFeGbLgdUTOa+Z726dP89gzrrDg5rHPvVyE+WQLsFfCfymxM2I3/OhbbHhhc0GoYcQKBgQDPlR5T51hs8wdtq+0OR4DRrPV40h2U4U1A38v2OYspBc9O6qViUBCLeysv+6eorBNDkSj4U/OZ2ITGWtRfvgQIp58lCFvdqYgA+3B0lnQ+SH+UqClIe9gE6JO4sRw7EfOH7wHyXaMqZMZ8zkSPdmAJ1b2IvjUuD2QHgp7kW8PQTwKBgQCy4+8FaqRFpXs3Xx10M2K0IXQ78v5SelkGqarCwj5hmLuI9MbwkYJNH6C7WkwbQxzvgFRM3qNZElR7b9tTrZZ+Dglh6kfgRGYBx/kQXBoqgKUL1nk9eHZmADTwIQqNN1/JoaBT6dB6AplaU1ukdFM+xEsY2jNxJ89kxY6qUlsvXQKBgFWF8WXOJc9VXZUgxV0htoAsi0IJB2kEXijBS64ZTS1MmHnCEPttUzO0PjnZq9zBcHpg/wk4xV94JlP2rhL4HX9XWCkQxZCQvVjyPwW5yxCyG+YqVj/nzjtPezUXuzLXFYfGv/2T+JqJxIE9FmAo/C5xIlKJUy2z55l0hnQ9HnLfAoGAcfxYzgvAdnxWAddCbktbxklWxeg0cxtwZJVxsD2TAB8Qx9iodptQ+pF/mkGUIv7ljhooHP7z+Ip8eFay2yPNs65//fsJJ3aNxO7F3c3CAnkMMXnMsQVz89SH5sY28fppt5GcFjvy0fub+XkJ+MUCUglDjKdkheKG/MABArgNUCUCgYAFqO0Wid+ncAslqGziKaFZ+nHiVwh0iNw5tQs5/us8L/jPl6r6xITHLFByg+N3iOip/lGe+XS9TLCaw/H9vZANcKAj3rw+27jN1/mvulqiLMXX5bvRpLVpCQznp2zKCQmHdNRhngIc5tSceJKgGsglRDgi76Xe8Pl1XOmTxqYQMA==";
	
	/*
	 * 发送数据的格式,  目前为json
	 * 
	 */

	private static String format = "json";
	
	/*
	 * 设置字符集的编码格式 utf-8
	 */

	private static String CHARSET = "utf-8";
	
	/*
	 * 支付宝公钥,  可以替换成你们自己的
	 */

	private static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkQ6CINb0flg02GNmOnTxNRkBqhxG6gEgiQ8Vp8DKzFye5j5/S2pxcxjg5MZZHpof0ed5Z6DHQSapcRJ7y1nmko5Jd2iAMyqnCNsTnMmw7DzPBqJT/igD8Mv0cpOv69YWW+hWuszly3+5zBowPPh2+486h2uXdn8Jg3FeaAOGL9Efk0tyw4r+xo07fQNiyXbX+b+y142tyBdTgamsmmsXPHotvd5luULauguBp8CAEv5B331pDCxAmmiiI7ApJkY+H+qecXQgPYsRLaVxSFogBsXZZTgp9RUi79qqDMlQcuTjeqKAJNPJX6UqRd02vDakX/dXLOvpOYYMkCOYo3YtswIDAQAB";
	
	/*
	 * 支付宝签名  目前采用的是RSA2
	 */
	
	private static String signType = "RSA2";
	/*
	 * 页面跳转同步通知页面   http      ?12345464.外网可以访问的地址
	 */



	public static AlipayClient getAlipayClient() {
		return new DefaultAlipayClient(serverURL,APP_ID,APP_PRIVATE_KEY,format,
				CHARSET,ALIPAY_PUBLIC_KEY,signType);
	}
}
