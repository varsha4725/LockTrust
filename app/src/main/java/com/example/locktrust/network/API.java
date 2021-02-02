package com.example.locktrust.network;


import com.google.gson.JsonElement;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


/**
 * Created by Prashant  on 15-12-2017.
 */

public interface API {
/*
    @POST("App_api/get_staticpages_data")
    Call<ResponseCommon> getContentSignUp(@Body BodyCommon bodyCommon);

    @POST("App_api/get_staticpages_data")
    Call<ResponseCommon> getContent(@Body BodyCommon bodyCommon, @Header("Authorization") String header);

    @POST("App_api/login/")
    Call<ResponseLogin> getLoginUser(@Body BodyLogin bodyLogin);
    // Call<ResponseLogin> getLoginUser(@Body BodyLogin bodyLogin);

    @POST("App_api/signup/")
    Call<ResponseRegisterUser> getRegisterUser(@Body BodyRegisterUser bodyRegisterUser);

    @POST("App_api/getState/")
    Call<ResponseState> getState(@Body BodyState bodyState, @Header("Authorization") String header);

    @POST("App_api/getCity_byID/")
    Call<ResponseCity> getCity(@Body BodyCity bodyCity, @Header("Authorization") String header);

    @POST("App_api/contactUs/")
    Call<ResponseContactUs> getContactUs(@Body BodyContactUS bodyContactUS, @Header("Authorization") String header);

    @POST("App_api/addCounselling/")
    Call<ResponseContactUs> getCounsellingSubmit(@Body BodyCounseling bodyContactUS, @Header("Authorization") String header);

    @POST("App_api/forgotPassword/")
    Call<ResponseContactUs> getForgotPassword(@Body BodyForgotPassword bodyForgotPassword);


    @POST("App_api/facebooklogin/")
    Call<ResponseFacebookLogin> getFacebookUser(@Body BodyFacebookLogin bodyFacebookLogin);

    @POST("App_api/googlelogin/")
    Call<ResponseGoogleLogin> getGoogleUser(@Body BodyGoogleLogin bodyGoogleLogin);

    @POST("App_api/getHomeData/")
    Call<JsonElement> getHomeData(@Body BodyHome bodyHome,
                                  @Header("Authorization") String header);

    @POST("App_api/getSubjectList/")
    Call<JsonElement> getSubjectList(@Body BodyAllSubject bodyAllSubject, @Header("Authorization") String header);

    @POST("App_api/getCourseBySubjectID/")
    Call<JsonElement> getCoursesList(@Body BodyCourseList bodyCourseList, @Header("Authorization") String header);

    @POST("App_api/getCourseList/")
    Call<JsonElement> getAllCoursesList(@Body BodyAllCourcesList bodyCourseList, @Header("Authorization") String header);

    @POST("App_api/myAllCourse/")
    Call<JsonElement> getAllMyCoursesList(@Body BodyAllCourcesList bodyCourseList, @Header("Authorization") String header);

    @POST("App_api/myScheduledCourses/")
    Call<JsonElement> getScheduleCoursesList(@Body BodyAllCourcesList bodyCourseList, @Header("Authorization") String header);

    @POST("App_api/myUnscheduledCourses//")
    Call<JsonElement> getUnScheduleCoursesList(@Body BodyAllCourcesList bodyCourseList, @Header("Authorization") String header);

    @POST("App_api/tutorList/")
    Call<JsonElement> getAllTutorList(@Body BodyTutorList bodyTutorList, @Header("Authorization") String header);

    @POST("App_api/myTutors/")
    Call<JsonElement> getMyTutorList(@Body BodyAllCourcesList bodyTutorList, @Header("Authorization") String header);

    @POST("App_api/getCourseDetails/")
    Call<JsonElement> getCourseDetails(@Body BodyCourseDetails bodyCourseDetails, @Header("Authorization") String header);

    @POST("App_api/searchNow/")
    Call<JsonElement> getSearchResult(@Body BodySearch bodySearch, @Header("Authorization") String header);

    @POST("App_api/checkCouponCode/")
    Call<JsonElement> checkCouponCode(@Body BodyCheckCouponCode checkCouponCode, @Header("Authorization") String header);

    @POST("App_api/updateProfile/")
    Call<JsonElement> updateParentProfile(@Body BodyParentProfile bodyParentProfile, @Header("Authorization") String header);

    @POST("App_api/schedulingCourse/")
    Call<JsonElement> scheduleCourse(@Body BodyScheduleCourse bodyScheduleCourse, @Header("Authorization") String header);

    @POST("App_api/checkAvailability/")
    Call<JsonElement> checkAvailability(@Body BodyCheckAvailability bodyScheduleCourse, @Header("Authorization") String header);

    @POST("App_api/getTutorDetails/")
    Call<JsonElement> getTutorDetails(@Body BodyTutorDetails bodyTutorDetails, @Header("Authorization") String header);

    @POST("App_api/bookSlot")
    Call<JsonElement> bookSlot(@Body BodyBookSlots bodyBookSlots, @Header("Authorization") String header);

    @POST("App_api/filterOptions")
    Call<JsonElement> filterOptions(@Body BodyFilterOptions bodyFilterOptions, @Header("Authorization") String header);

    @POST("App_api/getInvoiceHistory")
    Call<JsonElement> getInvoiceList(@Body BodyFilterOptions bodyFilterOptions, @Header("Authorization") String header);

    @POST("App_api/getTransactionHistory")
    Call<JsonElement> getTransactionList(@Body BodyFilterOptions bodyFilterOptions, @Header("Authorization") String header);

    @POST("App_api/getMeetingDetails")
    Call<JsonElement> getMeetingDetails(@Body BodyFilterOptions bodyFilterOptions, @Header("Authorization") String header);

    @POST("App_api/addRemoveWishlist")
    Call<JsonElement> addWishList(@Body BodyAddWishCourse bodyFilterOptions, @Header("Authorization") String header);

    @POST("App_api/buydemocourse")
    Call<JsonElement> buyDemoCourse(@Body BodyAddWishCourse bodyFilterOptions, @Header("Authorization") String header);


    @POST("App_api/removeWishlist")
    Call<JsonElement> removeWishList(@Body BodyRemoveWishCourse bodyFilterOptions, @Header("Authorization") String header);

    @POST("App_api/wishlistListing/")
    Call<JsonElement> getWishList(@Body BodyGetWishCourses bodyGetWishCourses, @Header("Authorization") String header);

    @POST("App_api/userProfile")
    Call<JsonElement> getUserProfile(@Body BodyGetWishCourses bodyGetWishCourses, @Header("Authorization") String header);

    @POST("App_api/getInvoicePrint")
    Call<JsonElement> getInvoiceDetails(@Body BodyInvoiceDetails bodyInvoiceDetails, @Header("Authorization") String header);

    @POST("App_api/getInvoicePrint")
    Call<InvoicePrintResponse> getInvoicePrint(@Body InvoivePrintBody bodyInvoicePrint, @Header("Authorization") String header);

    @POST("App_api/getGetMyChild")
    Call<JsonElement> getChildren(@Body BodyFilterOptions bodyFilterOptions, @Header("Authorization") String header);

    @POST("App_api/getComplainType")
    Call<JsonElement> getComplaintType(@Body BodyComplintOption bodyComplintOption, @Header("Authorization") String header);

    @POST("App_api/addComplain")
    Call<ComplaintSubmitResponse> getComplaintSubmit(@Body BodyComplaintSubmit bodyComplaintSubmit, @Header("Authorization") String header);

    @POST("App_api/getPurchasedCourses")
    Call<JsonElement> getPurchasedCourses(@Body BodyRefundPayment bodyRefundPayment, @Header("Authorization") String header);

    @POST("App_api/addRefunds")
    Call<RefundSubmitResponse> getrefundSubmit(@Body BodyRefundSubmit bodyRefundSubmit, @Header("Authorization") String header);

    @POST("App_api/assignChild")
    Call<JsonElement> assignChildren(@Body BodyAssignChildren bodyRefundSubmit, @Header("Authorization") String header);


    @Multipart
    @POST("App_api/parentUploadTest")
    Call<ResponseUploadTest> uploadTest(@Part("siteUserID") RequestBody siteUserID,
                                        @Part("tutor_id") RequestBody tutor_id,
                                        @Part("user_type") RequestBody user_type,
                                        @Part("slot_id") RequestBody slot_id,
                                        @Part("transctionDetailsID") RequestBody transctionDetailsID,
                                        @Part("courseID") RequestBody courseID,
                                        @Part("parentTitle") RequestBody parentTitle,
                                        @Part MultipartBody.Part file, @Header("Authorization") String header);


    //-----------------------------children web api call-----------------------------------------------

    @POST("App_api/childLogin")
    Call<ResponseLogin> loginChildren(@Body BodyLogin bodyRefundSubmit);

    @POST("App_api/sendMailToTutor")
    Call<JsonElement> sendMail(@Body BodySendMail bodySendMail, @Header("Authorization") String header);*/
}
