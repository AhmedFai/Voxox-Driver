package com.example.faizan.voxoxdriver;





import com.example.faizan.voxoxdriver.AchievementsPOJO.AchievementsBean;
import com.example.faizan.voxoxdriver.BookingDetailsPOJO.BookingDetailsBean;
import com.example.faizan.voxoxdriver.CurrentOperatorBillPOJO.CurrentBillBean;
import com.example.faizan.voxoxdriver.EarningByDayPOJO.EarningByDayBean;
import com.example.faizan.voxoxdriver.EarningDetailsByDayPOJO.EarningDetailsBean;
import com.example.faizan.voxoxdriver.GetBookingStatus.BookingStatusBean;
import com.example.faizan.voxoxdriver.GetProfilePOJO.GetProfileBean;
import com.example.faizan.voxoxdriver.IncentiveSchemePOJO.IncentiveSchemeBean;
import com.example.faizan.voxoxdriver.IncentivesEarnedPojo.IncentivesEarnedBean;
import com.example.faizan.voxoxdriver.OperatorBillPOJO.OperatorBillBean;
import com.example.faizan.voxoxdriver.ScorePOJO.ScoreBean;
import com.example.faizan.voxoxdriver.StartRidePOJO.StartRideBean;
import com.example.faizan.voxoxdriver.TodaySummaryPOJO.SummaryBean;
import com.example.faizan.voxoxdriver.acceptDenyPOJO.acceptDenyBean;
import com.example.faizan.voxoxdriver.currentBalancePOJO.cuyrrentBalanceBean;
import com.example.faizan.voxoxdriver.driverNotificationPOJO.notificationBean;
import com.example.faizan.voxoxdriver.driverStatusPOJO.StatusBean;
import com.example.faizan.voxoxdriver.finishRidePOJO.finishRideBean;
import com.example.faizan.voxoxdriver.loginPOJO.loginBean;
import com.example.faizan.voxoxdriver.rideStatusPOJO.rideStatusBean;

import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by USER on 11/30/2017.
 */

public interface Allapi {






    @Multipart
    @POST("cab/api/driver_login.php")
    Call<loginBean> login(
            @Part("phone") String m,
            @Part("password") String n

    );

    @Multipart
    @POST("cab/api/driver_get_notification.php")
    Call<notificationBean> notify(
            @Part("driverId") String m,
            @Part("latitude") String n,
            @Part("longitude") String o

    );


    @Multipart
    @POST("cab/api/update_driver_status.php")
    Call<StatusBean> status(
            @Part("driverId") String m,
            @Part("status") String n

    );

    @Multipart
    @POST("cab/api/driver_accept_deny.php")
    Call<acceptDenyBean> accept(
            @Part("driverid") String m,
            @Part("notificationId") String o,
            @Part("status") String n

    );

    @Multipart
    @POST("cab/api/get_driver_status.php")
    Call<rideStatusBean> rideStatus(
            @Part("driverId") String m,
            @Part("bookingId") String o,
            @Part("latitude") String n,
            @Part("longitude") String p

    );


    @Multipart
    @POST("cab/api/driver_start_ride.php")
    Call<StartRideBean> rideStart(
            @Part("driverId") String m,
            @Part("bookingId") String o,
            @Part("latitude") String n,
            @Part("longitude") String p

    );

    @Multipart
    @POST("cab/api/driver_finished_ride.php")
    Call<finishRideBean> rideFinish(
            @Part("driverId") String m,
            @Part("bookingId") String o,
            @Part("latitude") String n,
            @Part("longitude") String p

    );


    @Multipart
    @POST("cab/api/driver_get_profile.php")
    Call<GetProfileBean> profile(
            @Part("driverId") String m
    );

    @Multipart
    @POST("cab/api/incentive-scheme.php")
    Call<IncentiveSchemeBean> scheme(
            @Part("driverId") String m
    );

    @Multipart
    @POST("cab/api/driver_incentive_history.php")
    Call<IncentivesEarnedBean> earned(
            @Part("driverId") String m
    );


    @Multipart
    @POST("cab/api/driver-operator-bill.php")
    Call<OperatorBillBean> operatorBill(
            @Part("driverId") String m
    );

    @Multipart
    @POST("cab/api/driver-operator-billById.php")
    Call<BookingDetailsBean> details(
            @Part("billId") String m
    );

    @Multipart
    @POST("cab/api/driver-earn-list.php")
    Call<EarningByDayBean> earningByDay(
            @Part("driverId") String m
    );

    @Multipart
    @POST("cab/api/driver-earn-byday.php")
    Call<EarningDetailsBean> earningDetails(
            @Part("driverId") String m,
            @Part("dayId") String n
    );


    @Multipart
    @POST("cab/api/driver-achievement.php")
    Call<AchievementsBean> achieve(
            @Part("driverId") String m
    );

    @Multipart
    @POST("cab/api/driver-voxox-score.php")
    Call<ScoreBean> score(
            @Part("driverId") String m
    );

    @Multipart
    @POST("cab/api/driver-today-summary.php")
    Call<SummaryBean> summary(
            @Part("driverId") String m,
            @Part("date") String n
    );


    @Multipart
    @POST("cab/api/driver-current-operator-bill.php")
    Call<CurrentBillBean> current(
            @Part("driverId") String m
    );

    @Multipart
    @POST("cab/api/driver-current-balance.php")
    Call<cuyrrentBalanceBean> currentBalamce(
            @Part("driverId") String m
    );

    @Multipart
    @POST("cab/api/get_booking_status.php")
    Call<BookingStatusBean> getBooking(
            @Part("driverId") String m
    );

}
