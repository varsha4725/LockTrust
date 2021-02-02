package com.example.locktrust.Singletones;

class locktrustSingltone {

    private int cntRemainingTutorials=0;
    private int MyCourseTabSelected=0;
    private String strRedirectFrom="";
    private static final locktrustSingltone ourInstance = new locktrustSingltone();

    public static locktrustSingltone getOurInstance() {
        return ourInstance;
    }

    public int getCntRemainingTutorials() {
        return cntRemainingTutorials;
    }

    public void setCntRemainingTutorials(int cntRemainingTutorials) {
        this.cntRemainingTutorials = cntRemainingTutorials;
    }

    public int getMyCourseTabSelected() {
        return MyCourseTabSelected;
    }

    public void setMyCourseTabSelected(int myCourseTabSelected) {
        MyCourseTabSelected = myCourseTabSelected;
    }

    public String getStrRedirectFrom() {
        return strRedirectFrom;
    }

    public void setStrRedirectFrom(String strRedirectFrom) {
        this.strRedirectFrom = strRedirectFrom;
    }
}
