import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class employeeRecord {
    String positionID;
    String positionStatus;
    Date timeIn;
    Date timeOut;
    int timecardHours;
    String payCycleStartDate;
    String payCycleEndDate;
    String employeeName;
    String fileNumber;

    public employeeRecord(String[] data) throws ParseException,NullPointerException {
        this.positionID = data[0];
        this.positionStatus = data[1];
        // Handle empty date fields gracefully
        this.timeIn = parseDate(data[2]);
        this.timeOut = parseDate(data[3]);


        this.timecardHours = parseTime(data[4]);
        this.payCycleStartDate = data[5];
        this.payCycleEndDate = data[6];
        this.employeeName = data[7];
        this.fileNumber = data[8];
    }

    private Date parseDate(String dateStr) throws ParseException {
        if (dateStr.isEmpty()) {
            return null; // Return null for empty date fields
        }
        return new SimpleDateFormat("MM/dd/yyyy hh:mm a").parse(dateStr);
    }


    private int parseTime(String timeStr) {
        if (timeStr.isEmpty()) {
            return 0;
        }
        String[] parts = timeStr.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

    public String getPositionID() {
        return positionID;
    }

    public void setPositionID(String positionID) {
        this.positionID = positionID;
    }

    public String getPositionStatus() {
        return positionStatus;
    }

    public void setPositionStatus(String positionStatus) {
        this.positionStatus = positionStatus;
    }

    public Date getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(Date timeIn) {
        this.timeIn = timeIn;
    }

    public Date getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Date timeOut) {
        this.timeOut = timeOut;
    }

    public int getTimecardHours() {
        return timecardHours;
    }

    public void setTimecardHours(int timecardHours) {
        this.timecardHours = timecardHours;
    }

    public String getPayCycleStartDate() {
        return payCycleStartDate;
    }

    public void setPayCycleStartDate(String payCycleStartDate) {
        this.payCycleStartDate = payCycleStartDate;
    }

    public String getPayCycleEndDate() {
        return payCycleEndDate;
    }

    public void setPayCycleEndDate(String payCycleEndDate) {
        this.payCycleEndDate = payCycleEndDate;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }
}