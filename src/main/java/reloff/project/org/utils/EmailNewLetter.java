package reloff.project.org.utils;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailNewLetter {

	@Autowired
	private JavaMailSender javaMailSender;

	private String plantilla = "<!DOCTYPE html>\r\n" + 
			"<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\r\n" + 
			"<head>\r\n" + 
			"    <meta charset=\"utf-8\"> <!-- utf-8 works for most cases -->\r\n" + 
			"    <meta name=\"viewport\" content=\"width=device-width\"> <!-- Forcing initial-scale shouldn't be necessary -->\r\n" + 
			"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> <!-- Use the latest (edge) version of IE rendering engine -->\r\n" + 
			"    <meta name=\"x-apple-disable-message-reformatting\">  <!-- Disable auto-scale in iOS 10 Mail entirely -->\r\n" + 
			"    <title></title> <!-- The title tag shows in email notifications, like Android 4.4. -->\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"    <link href=\"https://fonts.googleapis.com/css?family=Playfair+Display:400,400i,700,700i\" rel=\"stylesheet\">\r\n" + 
			"\r\n" + 
			"    <!-- CSS Reset : BEGIN -->\r\n" + 
			"<style>\r\n" + 
			"html,\r\n" + 
			"body {\r\n" + 
			"    margin: 0 auto !important;\r\n" + 
			"    padding: 0 !important;\r\n" + 
			"    height: 100% !important;\r\n" + 
			"    width: 100% !important;\r\n" + 
			"    background: #f1f1f1;\r\n" + 
			"}\r\n" + 
			"/* What it does: Stops email clients resizing small text. */\r\n" + 
			"* {\r\n" + 
			"    -ms-text-size-adjust: 100%;\r\n" + 
			"    -webkit-text-size-adjust: 100%;\r\n" + 
			"}\r\n" + 
			"/* What it does: Centers email on Android 4.4 */\r\n" + 
			"div[style*=\"margin: 16px 0\"] {\r\n" + 
			"    margin: 0 !important;\r\n" + 
			"}\r\n" + 
			"/* What it does: Stops Outlook from adding extra spacing to tables. */\r\n" + 
			"table,\r\n" + 
			"td {\r\n" + 
			"    mso-table-lspace: 0pt !important;\r\n" + 
			"    mso-table-rspace: 0pt !important;\r\n" + 
			"}\r\n" + 
			"/* What it does: Fixes webkit padding issue. */\r\n" + 
			"table {\r\n" + 
			"    border-spacing: 0 !important;\r\n" + 
			"    border-collapse: collapse !important;\r\n" + 
			"    table-layout: fixed !important;\r\n" + 
			"    margin: 0 auto !important;\r\n" + 
			"}\r\n" + 
			"/* What it does: Uses a better rendering method when resizing images in IE. */\r\n" + 
			"img {\r\n" + 
			"    -ms-interpolation-mode:bicubic;\r\n" + 
			"}\r\n" + 
			"/* What it does: Prevents Windows 10 Mail from underlining links despite inline CSS. Styles for underlined links should be inline. */\r\n" + 
			"a {\r\n" + 
			"    text-decoration: none;\r\n" + 
			"}\r\n" + 
			"/* What it does: A work-around for email clients meddling in triggered links. */\r\n" + 
			"*[x-apple-data-detectors],  /* iOS */\r\n" + 
			".unstyle-auto-detected-links *,\r\n" + 
			".aBn {\r\n" + 
			"    border-bottom: 0 !important;\r\n" + 
			"    cursor: default !important;\r\n" + 
			"    color: inherit !important;\r\n" + 
			"    text-decoration: none !important;\r\n" + 
			"    font-size: inherit !important;\r\n" + 
			"    font-family: inherit !important;\r\n" + 
			"    font-weight: inherit !important;\r\n" + 
			"    line-height: inherit !important;\r\n" + 
			"}\r\n" + 
			"/* What it does: Prevents Gmail from displaying a download button on large, non-linked images. */\r\n" + 
			".a6S {\r\n" + 
			"    display: none !important;\r\n" + 
			"    opacity: 0.01 !important;\r\n" + 
			"}\r\n" + 
			"/* What it does: Prevents Gmail from changing the text color in conversation threads. */\r\n" + 
			".im {\r\n" + 
			"    color: inherit !important;\r\n" + 
			"}\r\n" + 
			"/* If the above doesn't work, add a .g-img class to any image in question. */\r\n" + 
			"img.g-img + div {\r\n" + 
			"    display: none !important;\r\n" + 
			"}\r\n" + 
			"/* What it does: Removes right gutter in Gmail iOS app: https://github.com/TedGoas/Cerberus/issues/89  */\r\n" + 
			"/* Create one of these media queries for each additional viewport size you'd like to fix */\r\n" + 
			"/* iPhone 4, 4S, 5, 5S, 5C, and 5SE */\r\n" + 
			"@media only screen and (min-device-width: 320px) and (max-device-width: 374px) {\r\n" + 
			"    u ~ div .email-container {\r\n" + 
			"        min-width: 320px !important;\r\n" + 
			"    }\r\n" + 
			"}\r\n" + 
			"/* iPhone 6, 6S, 7, 8, and X */\r\n" + 
			"@media only screen and (min-device-width: 375px) and (max-device-width: 413px) {\r\n" + 
			"    u ~ div .email-container {\r\n" + 
			"        min-width: 375px !important;\r\n" + 
			"    }\r\n" + 
			"}\r\n" + 
			"/* iPhone 6+, 7+, and 8+ */\r\n" + 
			"@media only screen and (min-device-width: 414px) {\r\n" + 
			"    u ~ div .email-container {\r\n" + 
			"        min-width: 414px !important;\r\n" + 
			"    }\r\n" + 
			"}\r\n" + 
			"</style>\r\n" + 
			"\r\n" + 
			"    <!-- CSS Reset : END -->\r\n" + 
			"\r\n" + 
			"    <!-- Progressive Enhancements : BEGIN -->\r\n" + 
			"<style>\r\n" + 
			".primary{\r\n" + 
			"	background: #f3a333;\r\n" + 
			"}\r\n" + 
			".bg_white{\r\n" + 
			"	background: #ffffff;\r\n" + 
			"}\r\n" + 
			".bg_light{\r\n" + 
			"	background: #fafafa;\r\n" + 
			"}\r\n" + 
			".bg_black{\r\n" + 
			"	background: #000000;\r\n" + 
			"}\r\n" + 
			".bg_dark{\r\n" + 
			"	background: rgba(0,0,0,.8);\r\n" + 
			"}\r\n" + 
			".email-section{\r\n" + 
			"	padding:2.5em;\r\n" + 
			"}\r\n" + 
			"/*BUTTON*/\r\n" + 
			".btn{\r\n" + 
			"	padding: 10px 15px;\r\n" + 
			"}\r\n" + 
			".btn.btn-primary{\r\n" + 
			"	border-radius: 30px;\r\n" + 
			"	background: #f3a333;\r\n" + 
			"	color: #ffffff;\r\n" + 
			"}\r\n" + 
			"h1,h2,h3,h4,h5,h6{\r\n" + 
			"	font-family: 'Playfair Display', serif;\r\n" + 
			"	color: #000000;\r\n" + 
			"	margin-top: 0;\r\n" + 
			"}\r\n" + 
			"body{\r\n" + 
			"	font-family: 'Montserrat', sans-serif;\r\n" + 
			"	font-weight: 400;\r\n" + 
			"	font-size: 15px;\r\n" + 
			"	line-height: 1.8;\r\n" + 
			"	color: rgba(0,0,0,.4);\r\n" + 
			"}\r\n" + 
			"a{\r\n" + 
			"	color: #f3a333;\r\n" + 
			"}\r\n" + 
			"table{\r\n" + 
			"}\r\n" + 
			"/*LOGO*/\r\n" + 
			".logo h1{\r\n" + 
			"	margin: 0;\r\n" + 
			"}\r\n" + 
			".logo h1 a{\r\n" + 
			"	color: #000;\r\n" + 
			"	font-size: 20px;\r\n" + 
			"	font-weight: 700;\r\n" + 
			"	text-transform: uppercase;\r\n" + 
			"	font-family: 'Montserrat', sans-serif;\r\n" + 
			"}\r\n" + 
			"/*HERO*/\r\n" + 
			".hero{\r\n" + 
			"	position: relative;\r\n" + 
			"}\r\n" + 
			".hero img{\r\n" + 
			"}\r\n" + 
			".hero .text{\r\n" + 
			"	color: rgba(255,255,255,.8);\r\n" + 
			"}\r\n" + 
			".hero .text h2{\r\n" + 
			"	color: #ffffff;\r\n" + 
			"	font-size: 30px;\r\n" + 
			"	margin-bottom: 0;\r\n" + 
			"}\r\n" + 
			"/*HEADING SECTION*/\r\n" + 
			".heading-section{\r\n" + 
			"}\r\n" + 
			".heading-section h2{\r\n" + 
			"	color: #000000;\r\n" + 
			"	font-size: 28px;\r\n" + 
			"	margin-top: 0;\r\n" + 
			"	line-height: 1.4;\r\n" + 
			"}\r\n" + 
			".heading-section .subheading{\r\n" + 
			"	margin-bottom: 20px !important;\r\n" + 
			"	display: inline-block;\r\n" + 
			"	font-size: 13px;\r\n" + 
			"	text-transform: uppercase;\r\n" + 
			"	letter-spacing: 2px;\r\n" + 
			"	color: rgba(0,0,0,.4);\r\n" + 
			"	position: relative;\r\n" + 
			"}\r\n" + 
			".heading-section .subheading::after{\r\n" + 
			"	position: absolute;\r\n" + 
			"	left: 0;\r\n" + 
			"	right: 0;\r\n" + 
			"	bottom: -10px;\r\n" + 
			"	content: '';\r\n" + 
			"	width: 100%;\r\n" + 
			"	height: 2px;\r\n" + 
			"	background: #f3a333;\r\n" + 
			"	margin: 0 auto;\r\n" + 
			"}\r\n" + 
			".heading-section-white{\r\n" + 
			"	color: rgba(255,255,255,.8);\r\n" + 
			"}\r\n" + 
			".heading-section-white h2{\r\n" + 
			"	font-size: 28px;\r\n" + 
			"	font-family: \r\n" + 
			"	line-height: 1;\r\n" + 
			"	padding-bottom: 0;\r\n" + 
			"}\r\n" + 
			".heading-section-white h2{\r\n" + 
			"	color: #ffffff;\r\n" + 
			"}\r\n" + 
			".heading-section-white .subheading{\r\n" + 
			"	margin-bottom: 0;\r\n" + 
			"	display: inline-block;\r\n" + 
			"	font-size: 13px;\r\n" + 
			"	text-transform: uppercase;\r\n" + 
			"	letter-spacing: 2px;\r\n" + 
			"	color: rgba(255,255,255,.4);\r\n" + 
			"}\r\n" + 
			".icon{\r\n" + 
			"	text-align: center;\r\n" + 
			"}\r\n" + 
			".icon img{\r\n" + 
			"}\r\n" + 
			"/*SERVICES*/\r\n" + 
			".text-services{\r\n" + 
			"	padding: 10px 10px 0; \r\n" + 
			"	text-align: center;\r\n" + 
			"}\r\n" + 
			".text-services h3{\r\n" + 
			"	font-size: 20px;\r\n" + 
			"}\r\n" + 
			"/*BLOG*/\r\n" + 
			".text-services .meta{\r\n" + 
			"	text-transform: uppercase;\r\n" + 
			"	font-size: 14px;\r\n" + 
			"}\r\n" + 
			"/*TESTIMONY*/\r\n" + 
			".text-testimony .name{\r\n" + 
			"	margin: 0;\r\n" + 
			"}\r\n" + 
			".text-testimony .position{\r\n" + 
			"	color: rgba(0,0,0,.3);\r\n" + 
			"}\r\n" + 
			"/*VIDEO*/\r\n" + 
			".img{\r\n" + 
			"	width: 100%;\r\n" + 
			"	height: auto;\r\n" + 
			"	position: relative;\r\n" + 
			"}\r\n" + 
			".img .icon{\r\n" + 
			"	position: absolute;\r\n" + 
			"	top: 50%;\r\n" + 
			"	left: 0;\r\n" + 
			"	right: 0;\r\n" + 
			"	bottom: 0;\r\n" + 
			"	margin-top: -25px;\r\n" + 
			"}\r\n" + 
			".img .icon a{\r\n" + 
			"	display: block;\r\n" + 
			"	width: 60px;\r\n" + 
			"	position: absolute;\r\n" + 
			"	top: 0;\r\n" + 
			"	left: 50%;\r\n" + 
			"	margin-left: -25px;\r\n" + 
			"}\r\n" + 
			"/*COUNTER*/\r\n" + 
			".counter-text{\r\n" + 
			"	text-align: center;\r\n" + 
			"}\r\n" + 
			".counter-text .num{\r\n" + 
			"	display: block;\r\n" + 
			"	color: #ffffff;\r\n" + 
			"	font-size: 34px;\r\n" + 
			"	font-weight: 700;\r\n" + 
			"}\r\n" + 
			".counter-text .name{\r\n" + 
			"	display: block;\r\n" + 
			"	color: rgba(255,255,255,.9);\r\n" + 
			"	font-size: 13px;\r\n" + 
			"}\r\n" + 
			"/*FOOTER*/\r\n" + 
			".footer{\r\n" + 
			"	color: rgba(255,255,255,.5);\r\n" + 
			"}\r\n" + 
			".footer .heading{\r\n" + 
			"	color: #ffffff;\r\n" + 
			"	font-size: 20px;\r\n" + 
			"}\r\n" + 
			".footer ul{\r\n" + 
			"	margin: 0;\r\n" + 
			"	padding: 0;\r\n" + 
			"}\r\n" + 
			".footer ul li{\r\n" + 
			"	list-style: none;\r\n" + 
			"	margin-bottom: 10px;\r\n" + 
			"}\r\n" + 
			".footer ul li a{\r\n" + 
			"	color: rgba(255,255,255,1);\r\n" + 
			"}\r\n" + 
			"@media screen and (max-width: 500px) {\r\n" + 
			"	.icon{\r\n" + 
			"		text-align: left;\r\n" + 
			"	}\r\n" + 
			"	.text-services{\r\n" + 
			"		padding-left: 0;\r\n" + 
			"		padding-right: 20px;\r\n" + 
			"		text-align: left;\r\n" + 
			"	}\r\n" + 
			"}\r\n" + 
			"</style>\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"</head>\r\n" + 
			"\r\n" + 
			"<body width=\"100%\" style=\"margin: 0; padding: 0 !important; mso-line-height-rule: exactly; background-color: #222222;\">\r\n" + 
			"	<center style=\"width: 100%; background-color: #f1f1f1;\">\r\n" + 
			"    <div style=\"display: none; font-size: 1px;max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden; mso-hide: all; font-family: sans-serif;\">\r\n" + 
			"      &zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;\r\n" + 
			"    </div>\r\n" + 
			"    <div style=\"max-width: 600px; margin: 0 auto;\" class=\"email-container\">\r\n" + 
			"    	<!-- BEGIN BODY -->\r\n" + 
			"      <table align=\"center\" role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"margin: auto;\">\r\n" + 
			"      	<tr>\r\n" + 
			"          <td class=\"bg_white logo\" style=\"padding: 1em 2.5em; text-align: center\">\r\n" + 
			"            <h1><a href=\"#\">Reloff</a></h1>\r\n" + 
			"          </td>\r\n" + 
			"	      </tr>\r\n" + 
			"	      <tr>\r\n" + 
			"		      <td class=\"bg_white\">\r\n" + 
			"		        <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\r\n" + 
			"		          <tr>\r\n" + 
			"		            <td class=\"bg_dark email-section\" style=\"text-align:center;\">\r\n" + 
			"		            	<div class=\"heading-section heading-section-white\">\r\n" + 
			"		            		<span class=\"subheading\">Letter</span>\r\n" + 
			"		              	<h2>A letter has been generated</h2>\r\n" + 
			"		              	<p>A pre-approval letter has been generated with your personal data... Your verification code is:</p>\r\n" + 
			"						<h2>xxxxx</h2>\r\n" + 
			"		            	</div>\r\n" + 
			"		            </td>\r\n" + 
			"		          </tr><!-- end: tr -->\r\n" + 
			"		         \r\n" + 
			"		          <tr>\r\n" + 
			"		            <td valign=\"top\" width=\"50%\" style=\"padding-top: 20px;\">\r\n" + 
			"                        <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\r\n" + 
			"                          <tr>\r\n" + 
			"                            <td class=\"text-testimony\" style=\"text-align: center;\">\r\n" + 
			"                            	<h3 class=\"name\">Noel Veitia</h3>\r\n" + 
			"								<span class=\"position\">NMLS: 277729</span>\r\n" + 
			"                             	<p>Your Home Financing Partner.</p>\r\n" + 
			"								<p> Mortgage Loan Originator.</p>\r\n" + 
			"                             	<p><a href=\"http://localhost:8080/verify\" class=\"btn btn-primary\">CHECK CODE</a></p>\r\n" + 
			"                            </td>\r\n" + 
			"                          </tr>\r\n" + 
			"                        </table>\r\n" + 
			"                      </td>\r\n" + 
			"                      \r\n" + 
			"                    </tr>\r\n" + 
			"		            	</table>\r\n" + 
			"		            </td>\r\n" + 
			"		          </tr><!-- end: tr -->\r\n" + 
			"		          \r\n" + 
			"		        </table>\r\n" + 
			"\r\n" + 
			"		      </td>\r\n" + 
			"		    </tr><!-- end:tr -->\r\n" + 
			"      <!-- 1 Column Text + Button : END -->\r\n" + 
			"      </table>\r\n" + 
			"    </div>\r\n" + 
			"  </center>\r\n" + 
			"</body>\r\n" + 
			"</html>";

	public void enviar(String email, String subject, String code) {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());

			String html = plantilla.replaceAll("xxxxx", code);

			helper.setTo(email);
			helper.setText(html, true);
			helper.setSubject(subject);

			javaMailSender.send(message);

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
