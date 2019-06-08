package pubsub;

import java.util.Date;

public class Publication
{
    private String patientName;

    private Date dateOfBirth;

    private double height;

    private String eyeColor;

    private int heartRate;

    public Publication(String patientName, Date dateOfBirth, double height, String eyeColor, int heartRate)
    {
        this.patientName = patientName;
        this.dateOfBirth = dateOfBirth;
        this.height = height;
        this.eyeColor = eyeColor;
        this.heartRate = heartRate;
    }

    public String getPatientName()
    {
        return patientName;
    }

    public void setPatientName(String patientName)
    {
        this.patientName = patientName;
    }

    public Date getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public double getHeight()
    {
        return height;
    }

    public void setHeight(double height)
    {
        this.height = height;
    }

    public String getEyeColor()
    {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor)
    {
        this.eyeColor = eyeColor;
    }

    public int getHeartRate()
    {
        return heartRate;
    }

    public void setHeartRate(int heartRate)
    {
        this.heartRate = heartRate;
    }

    public String getTopic()
    {
        // TODO Auto-generated method stub
        return null;
    }
}
