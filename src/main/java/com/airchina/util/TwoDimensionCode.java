package com.airchina.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import sun.misc.BASE64Decoder;
import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.exception.DecodingFailedException;

import com.swetake.util.Qrcode;

public class TwoDimensionCode {

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param imgPath
	 *            图片路径
	 */
	public void encoderQRCode(String content, String imgPath) {
		this.encoderQRCode(content, imgPath, "png", 7);
	}

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param output
	 *            输出流
	 */
	public void encoderQRCode(String content, OutputStream output) {
		this.encoderQRCode(content, output, "png", 7);
	}

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param imgPath
	 *            图片路径
	 * @param imgType
	 *            图片类型
	 */
	public void encoderQRCode(String content, String imgPath, String imgType) {
		this.encoderQRCode(content, imgPath, imgType, 7);
	}

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param output
	 *            输出流
	 * @param imgType
	 *            图片类型
	 */
	public void encoderQRCode(String content, OutputStream output,
			String imgType) {
		this.encoderQRCode(content, output, imgType, 10);
	}

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param imgPath
	 *            图片路径
	 * @param imgType
	 *            图片类型
	 * @param size
	 *            二维码尺寸
	 */
	public void encoderQRCode(String content, String imgPath, String imgType,
			int size) {
		try {
			BufferedImage bufImg = this.qRCodeCommon(content, imgType, size);

			File imgFile = new File(imgPath);
			// 生成二维码QRCode图片
			ImageIO.write(bufImg, imgType, imgFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 *            存储内容
	 * @param output
	 *            输出流
	 * @param imgType
	 *            图片类型
	 * @param size
	 *            二维码尺寸
	 */
	public void encoderQRCode(String content, OutputStream output,
			String imgType, int size) {
		try {
			BufferedImage bufImg = this.qRCodeCommon(content, imgType, size);
			// 生成二维码QRCode图片
			ImageIO.write(bufImg, imgType, output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成二维码(QRCode)图片的公共方法
	 * 
	 * @param content
	 *            存储内容
	 * @param imgType
	 *            图片类型
	 * @param size
	 *            二维码尺寸
	 * @return
	 */
	private BufferedImage qRCodeCommon(String content, String imgType, int size) {
		BufferedImage bufImg = null;
		try {
			Qrcode qrcodeHandler = new Qrcode();
			// 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
			qrcodeHandler.setQrcodeErrorCorrect('M');
			qrcodeHandler.setQrcodeEncodeMode('B');
			// 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
			qrcodeHandler.setQrcodeVersion(size);
			// 获得内容的字节数组，设置编码格式
			byte[] contentBytes = content.getBytes("utf-8");
			// 图片尺寸
			int imgSize = 67 + 12 * (size - 1);
			bufImg = new BufferedImage(imgSize, imgSize,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D gs = bufImg.createGraphics();
			// 设置背景颜色
			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, imgSize, imgSize);

			// 设定图像颜色> BLACK
			gs.setColor(Color.BLACK);
			// 设置偏移量，不设置可能导致解析出错
			int pixoff = 2;
			// 输出内容> 二维码
			if (contentBytes.length > 0 && contentBytes.length < 800) {
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			} else {
				throw new Exception("QRCode content bytes length = "
						+ contentBytes.length + " not in [0, 800].");
			}
			gs.dispose();
			bufImg.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bufImg;
	}

	/**
	 * 解析二维码（QRCode）
	 * 
	 * @param imgPath
	 *            图片路径
	 * @return
	 */
	public String decoderQRCode(String imgPath) {
		// QRCode 二维码图片的文件
		File imageFile = new File(imgPath);
		BufferedImage bufImg = null;
		String content = null;
		try {
			bufImg = ImageIO.read(imageFile);
			QRCodeDecoder decoder = new QRCodeDecoder();
			content = new String(decoder.decode(new TwoDimensionCodeImage(
					bufImg)), "utf-8");
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} catch (DecodingFailedException dfe) {
			System.out.println("Error: " + dfe.getMessage());
			dfe.printStackTrace();
		}
		return content;
	}

	/**
	 * 解析二维码（QRCode）
	 * 
	 * @param input
	 *            输入流
	 * @return
	 */
	public String decoderQRCode(InputStream input) {
		BufferedImage bufImg = null;
		String content = null;
		try {
			bufImg = ImageIO.read(input);
			QRCodeDecoder decoder = new QRCodeDecoder();
			content = new String(decoder.decode(new TwoDimensionCodeImage(
					bufImg)), "utf-8");
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} catch (DecodingFailedException dfe) {
			System.out.println("Error: " + dfe.getMessage());
			dfe.printStackTrace();
		}
		return content;
	}

	/**
	 * 解析二维码（QRCode）
	 * 
	 * @param imgPath
	 *            图片路径
	 * @return
	 */
	public String decoderQRCodeBase64(String base64) {
		// // QRCode 二维码图片的文件
		// File imageFile = new File(imgPath);
		BufferedImage bufImg = null;
		String content = null;

		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(base64);
			for (int i = 0; i < b.length; ++i)
				if (b[i] < 0)
					b[i] += 256;
			ByteArrayInputStream bais = new ByteArrayInputStream(b);

			bufImg = ImageIO.read(bais);
			QRCodeDecoder decoder1 = new QRCodeDecoder();
			content = new String(decoder1.decode(new TwoDimensionCodeImage(
					bufImg)), "utf-8");
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} catch (DecodingFailedException dfe) {
			System.out.println("Error: " + dfe.getMessage());
			dfe.printStackTrace();
		}
		return content;
	}

	public static void main(String[] args) {
		TwoDimensionCode handler = new TwoDimensionCode();

		// String imgPath = "/Users/tanyanbing/Desktop/1459832788.jpg";
		// String decoderContent = handler.decoderQRCode(imgPath);

		
		
//		String baseString="iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAIAAAAiOjnJAAAEI0lEQVR42u3cUVbDMAwEwN7/0nAD\nHrZWtpLOfgKFNpk81I3dz49IQz4OgYAlYAlYImAJWAKWCFgCloAlApaAJWCJgCVgCVgiYAlYApYI\nWAKWgCUCloAlYImAJWAJWCJgCVgClghYApaAJQKWgCVgiYAlXwzrU8sfv2fpr//n9/znh5cevvTE\n4kcMLLDAAgusabCKj1o6E0s/ExcWvxj6HgUWWGCBBdYVWMV5JT42nTyjSxxTRwwssMACC6x3wLpb\nUqSGv/iEBxZYYIEF1hfOWKnzV6wkUnW/GQsssMACS/O+/ajiHeK9Dj3+Aq+0HmCBBRZYYF2BlVqP\n9T1fsR4LLLDAAusdsPqAphCnJqr4HPaYUwMWWGCBBVauhj5Qyu+98z/ZKaTWooEFFlhggTW/ee9b\no5yivyc1Bb3vegMLLLDAAmuasKUj2Ddspa6T4l/vYw0WWGCBBdY0WH1lenFoS5GNFwfqBrDAAgus\nd9QNqTa8+JWxF0zx6gILLLDAAmv+jNU39xR3pe6d9SVqS1ZSvQxYYIEFFlhDYBWPcvHcXFmRHJ/M\nihcVWGCBBRZY0xgVJ7PijNXnqU/hqGVYYIEFFlhgBf/3p957p6qN4iR0YEiyHgsssMAC64l1Q/wG\nat8unQMj49Lxib9ksMACCyywxiZ1h7i42yd1PzhetYy69wwWWGCBBVZ9tEqdyJMTXgposdoACyyw\nwALr0bD28BX7gr7NsalKwtJksMACC6zXw9obU/pK5/hemr61VtZjgQUWWGC9o3lPtQMnK+8h34qv\n/QILLLDAAmuIsDi1sTJSFYkZCyywwALre+qGVO/QV0zv3WDu230EFlhggQXWg2asvvlg707zFWqp\n1uN7b0KDBRZYYL20bkhtYU195kKq2kh9usTJqxQssMACC6wDdcNemV48/Vf25BRBDN+uAxZYYIEF\nVr0CSBE5WSXEP0LiKRMVWGCBBRZYwVOSmk6K65biU05q2Lpy/wAssMACC6xpvcOV5Vx7TyP+cuLV\nBlhggQUWWGPrhr2FRycHsvhf3xtG1Q1ggQUWWI+GtTeLFHvtvYVQ8dkofhBG7VwFCyywwALrFtBU\ncV+8BopD0l7dMGrqAgsssMACq/4+/+TapiVPxXmuOH7NLBfAAgsssMAKzkapkqL49j7+VONz4ahb\nzmCBBRZYYLU278VT0jeHFZ98qoKfOYeBBRZYYIF1BlZqtXGqAii+5L1XARZYYIEFFljbO1Xiq51O\njnF9TwMssMACC6wHNe99HfreRqC+b+21FZp3sMACC6z5sE6uxyq+vT8w5dyd8MACCyywwBIBS8AS\nsETAErAELBGwBCwBSwQsAUvAEgFLwBKwRMASsAQsEbAELAFLBCwBS8ASAUvAErBEwBKwBCwRsAQs\nAUsELAFLwBIBS27mFx3jEPeIIu7PAAAAAElFTkSuQmCC";
		String baseString = "iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAIAAAAiOjnJAAAEJUlEQVR42u3cW47jMAwEwNz/0tkDDLCIyaYsy9Wf80piVwCmJc3nKzKQj0sgYAlYApYIWAKWgCUCloAlYImAJWAJWCJgCVgClghYApaAJQKWgCVgiYAlYAlYImAJWAKWCFgCloAlApaAJWCJgCVgCVgiYMmLYX16+eXv/P2Z/zyN2leaj/7L84lfMbDAAgsssHaD1fyt5oW7BKIp7JcHXXDFwAILLLDA2hZWfFqa8xS/o5c4pq4YWGCBBRZYZ8BqTjkpK7X3wKVhCyywwAILLLCC5UJtuIm36mCBBRZYYIE18Rk+tYbdnNWaL/CW1gMssMACC6xbYKX2Y73nK/ZjgQUWWGCdAWsOaApxaqKKz2GPuTVggQUWWGDlaujUsNWsLWqNeapTaG7VAgsssMACa/+6Id6zN69yc4tVs3Op6bEIDRZYYIH1xLqhuVQ8d2tTp0lTtzbOGiywwAILrP1hpQaO1FHYBWdim5un1Q1ggQUWWGc076k5o9nOr1xaaBYQ9mOBBRZYYJ0xY62caZorAXNVfpMIWGCBBRZYT4RVu8qpmuCWHckL3mYLhkiwwAILLLDijOZ+PXVk9JZvzXU3YIEFFlhg7QYr9dl75RmY1Kw29xYCCyywwAJr/7ohvoC6oGWITznxuv+9MxZYYIEF1pNhLbgBza1aqfXglMIFR4PAAgsssMC6ZcaqXabUDqQFB1bnfG91chUssMACC6zbm+V479A8FTO3Z7rZTYAFFlhggfXdIwtWiGtPo7k6Hl97nntQsMACCyywHjRjper12rizybc072CBBRZYp85YKWqpWWS34zpmLLDAAgusM2Cl/lNDfM147u0x9+S3WnsGCyywwAJr8S1JMZq7N7uV8mCBBRZYYO0PK369avPcytt/i0KwwAILLLB2qxtqvUN82/G2nUIKKFhggQUWWLvBil/BlVe5ufK94HDsnUMzWGCBBRZYbVhznhb8w4jaodZLXUlqZAQLLLDAAuvUwr2msNnX14Sl9kyDBRZYYIF1xoxVG7/mzvY0qcUHTXUDWGCBBdZhsOZa7ObfqRUHC0qK+PMBCyywwALr++TUPpY3m+65Jefa2nzqtYMFFlhggXVv3TA3YzXHnXgXv+D5qBvAAgsssJ5YN8yVFL88aPwTe60fT70rwAILLLDAOqx5T92S1L6uSyPjpZdcm0H3nLrAAgsssMBaA2vuxEt8/EodFkr9MFhggQUWWKfCau7ljfcXc2NckyxYYIEFFlhnNO+1j+6XHit1Tibeg+y5IxkssMACC6yrRFbux0r143MrxM2tyVstS4MFFlhggSUvD1gCloAlYImAJWAJWCJgCVgClghYApaAJQKWgCVgiYAlYAlYImAJWAKWCFgCloAlApaAJWCJgCVgCVgiYAlYApYIWAKWgCVyOf8Axd6JjNz21hwAAAAASUVORK5CYII=";
		String decoderContent = handler.decoderQRCodeBase64(baseString);

		// String encoderContent ="";
		// for(int i=0;i<40;i++)
		// encoderContent+="赵";
		//

		//
		// handler.encoderQRCode(encoderContent, imgPath, "png",10);
		//
		// System.out.println("=============编码成功！图片为于："+imgPath+"===============");

		System.out.println("============解析结果如下：===============");
		System.out.println(decoderContent);
		System.out.println("=========解码成功===========");
	}

}