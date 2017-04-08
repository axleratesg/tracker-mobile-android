package planar.weinvest.share;

import android.content.SharedPreferences;
import android.graphics.Bitmap;

public class Share {

	// TODO : All Screen
	public static int screenWidth;
	public static int screenHeight;
	public static SharedPreferences splogin;
	public static SharedPreferences.Editor spediterlogin;
	public static Double CUR_LAT = 0.0, CUR_LNG = 0.0;
	public static String locale = "";
	public static String pusk_key = "";
	public static String current_date = "";
	public static String current_day = "";
	public static String current_date_with_time = "";

	// TODO : Registation
	public static final String IMAGE_KEY = "user_image";
	public static int selected_gender = 0;
	public static int selected_country = 0;

	// TODO : Login With Facebook
	public static Bitmap userProfileBitmap;

	public static String unreadmsg = "0";

	public static String UserAddress = "";

}
