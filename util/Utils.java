package planar.weinvest.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import planar.weinvest.R;
import planar.weinvest.share.Share;

public class Utils {

	private static String TAG = Utils.class.getSimpleName();
	private static TransparentProgressDialog mProgressDialog;

	// TODO : Registration Selection
	public static String[] Gender = { "Male", "Female" };

	// TODO : Home City Selection
	public static String[] City = { "SINGAPORE", "THAILAND" };

	// TODO : Home Filter Selection
	public static String[] Filter = { "", "Popular", "Nearest", "Recent" };

	// TODO : Booking
	public static ArrayList<String> al_booking_date;
	public static ArrayList<String> al_booking_day;
	@SuppressLint("SimpleDateFormat")
	public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	@SuppressLint("SimpleDateFormat")
	public static SimpleDateFormat df1 = new SimpleDateFormat("EEEE");

	// TODO : food List
	public static ArrayList<String> al_food_id = new ArrayList<String>();
	public static ArrayList<String> al_food_qty = new ArrayList<String>();
	public static ArrayList<String> al_food_name = new ArrayList<String>();
	public static ArrayList<String> al_food_price = new ArrayList<String>();

	// TODO : Payment Activity
	public static String[] month_namenumber = { "January(01)", "February(02)",
			"March(03)", "April(04)", "May(05)", "June(06)", "July(07)",
			"August(08)", "September(09)", "October(10)", "November(11)",
			"December(12)" };

	// TODO : FAQ Activity
	public static String[] faq_head = {
			"What is WOOFR?",
			"Rules of entry",
			"Bookings & Payment",
			"What time should I arrive at my venue?",
			"How do i know if my reservation is confirmed?",
			"What should i show upon arrival at my venue?",
			"My id is expired or I do not have it with me. Can i still enter the venue?",
			"Cancellation & refund policies",
			"How do I change my password?",
			"How do I add or remove credit card info from my account?",};

	public static String[] faq_desc = {
			"  WOOFR gets you to best nightlife establishments in your city, with instant bookings, tickets and drinks purchases within a few taps on your smartphones ",
			"  Rules and regulations may vary from venue to venue. To facilitate ease of entry, we encourage that all guests should be of legal age for entry, dressed appropriately avoiding late arrival ",
			"  Payment is done in 2 simple steps; 1) Once you have chosen your table and drinks, 10% downpayment of your reflected bill is required to secure your table booking, 2) The remaining 90% of the bill to be paid directly to the club venue ",
			"  Required time of arrival will be indicated on the summary page just before your payment is made ",
			"  As soon as you've completed the booking process, a summary page will appear. This page shows all of your reservation details, including the booking number. Once booking is confirmed, we will send you a confirmation email with all of your booking information. You can check for your booking request under Manage > My Bookings ",
			"  Simply present your reservation confirmation via the app or from your registered email to gain entry. Also, to comply to all standard club rules, guests are required to bring along their ID, driver’s license or passport (all of which have to be valid) ",
			"  We are afraid not. An expired or invalid ID does not permit entry of guests ",
			"  At this point, there would be no refunds for any cancellations done ",
			"  There is a change password under ‘My Account’ tab. Alternatively, you can click on your profile picture and will automatically bring you to Edit Profile ",
			"  To add or remove credit card info from your payment settings: Click to ‘My Account' on the sidebar menu, screen will appear and an option of adding or removing card will be reflected on the screen. Click Add Credit / Debit card to add a new credit card. To remove a credit card, click Remove.\n" +
					"\n" +
					"Alternatively, you can simply click your profile picture on top of the sidebar menu and it will automatically bring you to Edit Profile, and an option of adding or removing card will be reflected on the screen. ",
			};

	public static String[] confirmation = {
			"My credit/debit card will be charged the deposit amount shown below.",
			"An email will be sent to me only after my booking is confirmed",
			"Any deposit made will be deducted off my final bill at the venue", };
	
	public static String[] time_interval = {/*"09:00 PM","09:15 PM","09:30 PM","09:45 PM",*/"10:00 PM","10:15 PM","10:30 PM","10:45 PM","11:00 PM","11:15 PM","11:30 PM","11:45 PM","12:00 AM","12:15 AM"/*,"12:30 AM","12:45 AM","01:00 AM","01:15 AM","01:30 AM","01:45 AM","02:00 AM","02:15 AM","02:30 AM","02:45 AM","03:00 AM","03:15 AM","03:30 AM","03:45 AM","04:00 AM"*/};
	

	@SuppressLint("SimpleDateFormat")
	public static CharSequence chageDateFormat(String start_date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		try {
			start_date = sdf1.format(sdf.parse(start_date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return start_date;
	}

	@SuppressLint("SimpleDateFormat")
	public static CharSequence chageCustomDateFormat(String input_format,
			String output_format, String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(input_format);
		SimpleDateFormat sdf1 = new SimpleDateFormat(output_format);
		try {
			date = sdf1.format(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date.toString();
	}

	public static String getCompleteAddress(Context ctx, Double lat, Double lng) {
		try {
			Geocoder geocoder = new Geocoder(ctx, Locale.getDefault());
			List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
			if (addresses != null) {
				Address returnedAddress = addresses.get(0);
				StringBuilder strReturnedAddress = new StringBuilder("");
				for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
					try {
						if (returnedAddress.getCountryName() != null)
							Share.UserAddress = returnedAddress
									.getCountryName();
						if ((Share.UserAddress == null)
								&& (returnedAddress.getAdminArea() != null))
							Share.UserAddress = returnedAddress.getAdminArea();
						if ((Share.UserAddress == null)
								&& (returnedAddress.getSubAdminArea() != null))
							Share.UserAddress = returnedAddress
									.getSubAdminArea();
					} catch (Exception e) {
						Log.e(TAG, "can't find locality");
						e.printStackTrace();
						Share.UserAddress = "City";
					}
					strReturnedAddress
							.append(returnedAddress.getAddressLine(i)).append(
									"\n");
				}
			} else {
				Share.UserAddress = "City";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Share.UserAddress;
	}

	@SuppressLint("DefaultLocale")
	public static CharSequence getDistance(Double cur_lat, Double cur_lng,
			Double club_lat, Double club_lng) {
		float dist = 0;
		if(cur_lat != 0) {
			try {
				Log.e(TAG, "cur_lat :" + cur_lat + "cur_lng :" + cur_lng
						+ "club_lat :" + club_lat + "club_lng :" + club_lng);
				double earthRadius = 6371000; // meters
				double dLat = Math.toRadians(club_lat - cur_lat);
				double dLng = Math.toRadians(club_lng - cur_lng);
				double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
						+ Math.cos(Math.toRadians(cur_lat))
						* Math.cos(Math.toRadians(club_lat)) * Math.sin(dLng / 2)
						* Math.sin(dLng / 2);
				double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
				dist = (float) (earthRadius * c * 0.001);
				dist += dist * 0.3;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return String.format("%.2f", dist)/* (dist) */;
		}
		else
			return "";
	}

	public static String Countryarray[] = { "Afghanistan", "Albania",
			"Algeria", "Andorra", "Angola", "Antigua and Barbuda", "Argentina",
			"Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas",
			"Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium",
			"Belize", "Benin", "Bhutan", "Bolivia", "Bosnia and Herzegovina",
			"Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso",
			"Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde",
			"Central African Republic", "Chad", "Chile", "China", "Colombi",
			"Comoros", "Congo (Brazzaville)", "Congo", "Costa Rica",
			"Cote d'Ivoire", "Croatia", "Cuba", "Cyprus", "Czech Republic",
			"Denmark", "Djibouti", "Dominica", "Dominican Republic",
			"East Timor (Timor Timur)", "Ecuador", "Egypt", "El Salvador",
			"Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Fiji",
			"Finland", "France", "Gabon", "Gambia, The", "Georgia", "Germany",
			"Ghana", "Greece", "Grenada", "Guatemala", "Guinea",
			"Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary",
			"Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland",
			"Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan",
			"Kenya", "Kiribati", "Korea, North", "Korea, South", "Kuwait",
			"Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia",
			"Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macedonia",
			"Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta",
			"Marshall Islands", "Mauritania", "Mauritius", "Mexico",
			"Micronesia", "Moldova", "Monaco", "Mongolia", "Morocco",
			"Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal",
			"Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria",
			"Norway", "Oman", "Pakistan", "Palau", "Panama",
			"Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland",
			"Portugal", "Qatar", "Romania", "Russia", "Rwanda",
			"Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent", "Samoa",
			"San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal",
			"Serbia and Montenegro", "Seychelles", "Sierra Leone", "Singapore",
			"Slovakia", "Slovenia", "Solomon Islands", "Somalia",
			"South Africa", "Spain", "Sri Lanka", "Sudan", "Suriname",
			"Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan",
			"Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga",
			"Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan",
			"Tuvalu", "Uganda", "Ukraine", "United Arab Emirates",
			"United Kingdom", "United States", "Uruguay", "Uzbekistan",
			"Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen",
			"Zambia", "Zimbabwe" };


	public static CharSequence setTimeArrival(int progress) {
		if (progress < 4) {
			progress = progress + 21;
		} else {
			progress = progress - 3;
		}
		float time_hour = (progress);
		time_hour = Float.parseFloat(new DecimalFormat("##.##")
				.format(time_hour));
		String time_interval = (String) Utils.chageCustomDateFormat("HH:mm",
				"hh:mm a", String.valueOf(time_hour).replace(".", ":"));

		return time_interval;
	}

	public static void hideDialog() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
	}

	public static void showProgressDialog(Context ctx) {
		if (mProgressDialog == null)
			mProgressDialog = new TransparentProgressDialog(ctx,
					R.drawable.logo);
		;
		// mProgressDialog.setIcon(R.drawable.appicon);
		if (!mProgressDialog.isShowing())
			mProgressDialog.show();
	}

}
