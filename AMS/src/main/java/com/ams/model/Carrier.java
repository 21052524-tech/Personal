package com.ams.model;

import java.util.Date;

public class Carrier {
    private int carrierId;
    private String carrierName;
    private int discountPercentageThirtyDaysAdvanceBooking;
    private int discountPercentageSixtyDaysAdvanceBooking;
    private int discountPercentageNinetyDaysAdvanceBooking;
    private int refundPercentageForTicketCancellation2DaysBeforeTravelDate;
    private int refundPercentageForTicketCancellation10DaysBeforeTravelDate;
    private int refundPercentageForTicketCancellation20DaysBeforeTravelDate;
    private int bulkBookingDiscount;
    private int silverUserDiscount;
    private int goldUserDiscount;
    private int platinumUserDiscount;
    private Date createdAt;
    private Date updatedAt;
    
    // Default constructor
    public Carrier() {}
    
    // Constructor without ID (for new carriers)
    public Carrier(String carrierName, int discountPercentageThirtyDaysAdvanceBooking,
                   int discountPercentageSixtyDaysAdvanceBooking, int discountPercentageNinetyDaysAdvanceBooking,
                   int refundPercentageForTicketCancellation2DaysBeforeTravelDate,
                   int refundPercentageForTicketCancellation10DaysBeforeTravelDate,
                   int refundPercentageForTicketCancellation20DaysBeforeTravelDate,
                   int bulkBookingDiscount, int silverUserDiscount, int goldUserDiscount, int platinumUserDiscount) {
        this.carrierName = carrierName;
        this.discountPercentageThirtyDaysAdvanceBooking = discountPercentageThirtyDaysAdvanceBooking;
        this.discountPercentageSixtyDaysAdvanceBooking = discountPercentageSixtyDaysAdvanceBooking;
        this.discountPercentageNinetyDaysAdvanceBooking = discountPercentageNinetyDaysAdvanceBooking;
        this.refundPercentageForTicketCancellation2DaysBeforeTravelDate = refundPercentageForTicketCancellation2DaysBeforeTravelDate;
        this.refundPercentageForTicketCancellation10DaysBeforeTravelDate = refundPercentageForTicketCancellation10DaysBeforeTravelDate;
        this.refundPercentageForTicketCancellation20DaysBeforeTravelDate = refundPercentageForTicketCancellation20DaysBeforeTravelDate;
        this.bulkBookingDiscount = bulkBookingDiscount;
        this.silverUserDiscount = silverUserDiscount;
        this.goldUserDiscount = goldUserDiscount;
        this.platinumUserDiscount = platinumUserDiscount;
    }
    
    // Getters and Setters
    public int getCarrierId() {
        return carrierId;
    }
    
    public void setCarrierId(int carrierId) {
        this.carrierId = carrierId;
    }
    
    public String getCarrierName() {
        return carrierName;
    }
    
    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }
    
    public int getDiscountPercentageThirtyDaysAdvanceBooking() {
        return discountPercentageThirtyDaysAdvanceBooking;
    }
    
    public void setDiscountPercentageThirtyDaysAdvanceBooking(int discountPercentageThirtyDaysAdvanceBooking) {
        this.discountPercentageThirtyDaysAdvanceBooking = discountPercentageThirtyDaysAdvanceBooking;
    }
    
    public int getDiscountPercentageSixtyDaysAdvanceBooking() {
        return discountPercentageSixtyDaysAdvanceBooking;
    }
    
    public void setDiscountPercentageSixtyDaysAdvanceBooking(int discountPercentageSixtyDaysAdvanceBooking) {
        this.discountPercentageSixtyDaysAdvanceBooking = discountPercentageSixtyDaysAdvanceBooking;
    }
    
    public int getDiscountPercentageNinetyDaysAdvanceBooking() {
        return discountPercentageNinetyDaysAdvanceBooking;
    }
    
    public void setDiscountPercentageNinetyDaysAdvanceBooking(int discountPercentageNinetyDaysAdvanceBooking) {
        this.discountPercentageNinetyDaysAdvanceBooking = discountPercentageNinetyDaysAdvanceBooking;
    }
    
    public int getRefundPercentageForTicketCancellation2DaysBeforeTravelDate() {
        return refundPercentageForTicketCancellation2DaysBeforeTravelDate;
    }
    
    public void setRefundPercentageForTicketCancellation2DaysBeforeTravelDate(int refundPercentageForTicketCancellation2DaysBeforeTravelDate) {
        this.refundPercentageForTicketCancellation2DaysBeforeTravelDate = refundPercentageForTicketCancellation2DaysBeforeTravelDate;
    }
    
    public int getRefundPercentageForTicketCancellation10DaysBeforeTravelDate() {
        return refundPercentageForTicketCancellation10DaysBeforeTravelDate;
    }
    
    public void setRefundPercentageForTicketCancellation10DaysBeforeTravelDate(int refundPercentageForTicketCancellation10DaysBeforeTravelDate) {
        this.refundPercentageForTicketCancellation10DaysBeforeTravelDate = refundPercentageForTicketCancellation10DaysBeforeTravelDate;
    }
    
    public int getRefundPercentageForTicketCancellation20DaysBeforeTravelDate() {
        return refundPercentageForTicketCancellation20DaysBeforeTravelDate;
    }
    
    public void setRefundPercentageForTicketCancellation20DaysBeforeTravelDate(int refundPercentageForTicketCancellation20DaysBeforeTravelDate) {
        this.refundPercentageForTicketCancellation20DaysBeforeTravelDate = refundPercentageForTicketCancellation20DaysBeforeTravelDate;
    }
    
    public int getBulkBookingDiscount() {
        return bulkBookingDiscount;
    }
    
    public void setBulkBookingDiscount(int bulkBookingDiscount) {
        this.bulkBookingDiscount = bulkBookingDiscount;
    }
    
    public int getSilverUserDiscount() {
        return silverUserDiscount;
    }
    
    public void setSilverUserDiscount(int silverUserDiscount) {
        this.silverUserDiscount = silverUserDiscount;
    }
    
    public int getGoldUserDiscount() {
        return goldUserDiscount;
    }
    
    public void setGoldUserDiscount(int goldUserDiscount) {
        this.goldUserDiscount = goldUserDiscount;
    }
    
    public int getPlatinumUserDiscount() {
        return platinumUserDiscount;
    }
    
    public void setPlatinumUserDiscount(int platinumUserDiscount) {
        this.platinumUserDiscount = platinumUserDiscount;
    }
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    public Date getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    // Utility methods
    public int getDiscountForDaysAdvance(int days) {
        if (days >= 90) {
            return discountPercentageNinetyDaysAdvanceBooking;
        } else if (days >= 60) {
            return discountPercentageSixtyDaysAdvanceBooking;
        } else if (days >= 30) {
            return discountPercentageThirtyDaysAdvanceBooking;
        }
        return 0;
    }
    
    public int getRefundPercentageForDays(int days) {
        if (days >= 20) {
            return refundPercentageForTicketCancellation20DaysBeforeTravelDate;
        } else if (days >= 10) {
            return refundPercentageForTicketCancellation10DaysBeforeTravelDate;
        } else if (days >= 2) {
            return refundPercentageForTicketCancellation2DaysBeforeTravelDate;
        }
        return 0;
    }
    
    public int getDiscountForCustomerCategory(String category) {
        if ("Silver".equals(category)) {
            return silverUserDiscount;
        } else if ("Gold".equals(category)) {
            return goldUserDiscount;
        } else if ("Platinum".equals(category)) {
            return platinumUserDiscount;
        }
        return 0;
    }
    
    @Override
    public String toString() {
        return "Carrier{" +
                "carrierId=" + carrierId +
                ", carrierName='" + carrierName + '\'' +
                ", discountPercentageThirtyDaysAdvanceBooking=" + discountPercentageThirtyDaysAdvanceBooking +
                ", discountPercentageSixtyDaysAdvanceBooking=" + discountPercentageSixtyDaysAdvanceBooking +
                ", discountPercentageNinetyDaysAdvanceBooking=" + discountPercentageNinetyDaysAdvanceBooking +
                '}';
    }
}